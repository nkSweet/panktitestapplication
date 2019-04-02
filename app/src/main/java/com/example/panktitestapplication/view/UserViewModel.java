package com.example.panktitestapplication.view;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.panktitestapplication.Utils.ApiResponse;
import com.example.panktitestapplication.database.RoomUserData;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class UserViewModel extends ViewModel {

    private Repository repository;
    private final CompositeDisposable disposables = new CompositeDisposable();
    private final MutableLiveData<ApiResponse> responseLiveData = new MutableLiveData<>();
    private LiveData<List<RoomUserData>> allUsers;


    public UserViewModel(Repository repository) {
        this.repository = repository;
        allUsers = repository.getUser();
    }

    public LiveData<List<RoomUserData>> getAllUsers() {
        return allUsers;
    }

    public void addUser(List<RoomUserData> users) {
        repository.addUsers(users);
    }

    public void deleteUser(RoomUserData note) {
        repository.delete(note);
    }
    public MutableLiveData<ApiResponse> UserDataResponse() {
        return responseLiveData;
    }

    public void callUserDetailsApi() {

        disposables.add(repository.executeUserData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((d) -> responseLiveData.setValue(ApiResponse.loading()))
                .subscribe(
                        result -> responseLiveData.setValue(ApiResponse.success(result)),
                        throwable -> responseLiveData.setValue(ApiResponse.error(throwable))
                ));

    }

    @Override
    protected void onCleared() {
        disposables.clear();
    }
}
