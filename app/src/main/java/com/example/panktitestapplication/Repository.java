package com.example.panktitestapplication;

import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.panktitestapplication.Utils.ApiCallInterface;
import com.example.panktitestapplication.database.RoomUserData;
import com.example.panktitestapplication.database.UserDataDao;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;


public class Repository {

    private ApiCallInterface apiCallInterface;
    private final UserDataDao userDao;
    private final Executor executor;
    private LiveData<List<RoomUserData>> mAllUsers;


    public Repository(ApiCallInterface apiCallInterface, UserDataDao userDao, Executor executor) {
        this.apiCallInterface = apiCallInterface;
        this.userDao = userDao;
        this.executor = executor;
    }


    public Observable<ArrayList<RoomUserData>> executeUserData() {
        return apiCallInterface.getUserData();
    }

    public  LiveData<List<RoomUserData>> getUser() {
        mAllUsers = userDao.getAllNotes();
        return mAllUsers;

        // refreshUser(userLogin); // try to refresh data if possible from Github Api
//        return userDao.getAllNotes(); // return a LiveData directly from the database.
    }

    //method to add note
    public void addNote(RoomUserData note) {
        new AddNote().execute(note);
    }

    //Async task to add note
    public class AddNote extends AsyncTask<RoomUserData, Void, Void> {
        @Override
        protected Void doInBackground(RoomUserData... notes) {
            userDao.insertNote(notes[0]);
            return null;
        }
    }


//    private void refreshUser(final String userLogin) {
//        executor.execute(() -> {
//            // Check if user was fetched recently
//            boolean userExists = (userDao.hasUser(userLogin, getMaxRefreshTime(new Date())) != null);
//            // If            boolean userExists = (userDao.hasUser(userLogin, getMaxRefreshTime(new Date())) != null); user have to be updated
//            if (!userExists) {
//                webservice.getUser(userLogin).enqueue(new Callback<User>() {
//                    @Override
//                    public void onResponse(Call<User> call, Response<User> response) {
//                        Toast.makeText(App.context, "Data refreshed from network !", Toast.LENGTH_LONG).show();
//                        executor.execute(() -> {
//                            User user = response.body();
//                            user.setLastRefresh(new Date());
//                            userDao.save(user);
//                        });
//                    }
//
//                    @Override
//                    public void onFailure(Call<User> call, Throwable t) {
//                    }
//                });
//            }
//        });
//    }
}
