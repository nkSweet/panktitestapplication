package com.example.panktitestapplication;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.panktitestapplication.Utils.ApiResponse;
import com.example.panktitestapplication.database.RoomUserData;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class UserViewModel extends ViewModel {

    private Repository repository;
    private final CompositeDisposable disposables = new CompositeDisposable();
    private final MutableLiveData<ApiResponse> responseLiveData = new MutableLiveData<>();
    private LiveData<List<RoomUserData>> mAllNotes;


    public UserViewModel(Repository repository) {
        this.repository = repository;
        mAllNotes = repository.getUser();
    }

    public LiveData<List<RoomUserData>> getAllUsers() {
        return mAllNotes;
    }

    public void addUser(RoomUserData note) {
        repository.addNote(note);
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
