package com.example.panktitestapplication.view;

import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.panktitestapplication.Utils.ApiCallInterface;
import com.example.panktitestapplication.database.RoomUserData;
import com.example.panktitestapplication.database.UserDataDao;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import io.reactivex.Observable;


public class Repository {

    private ApiCallInterface apiCallInterface;
    private final UserDataDao userDao;
    private LiveData<List<RoomUserData>> mAllUsers;


    public Repository(ApiCallInterface apiCallInterface, UserDataDao userDao) {
        this.apiCallInterface = apiCallInterface;
        this.userDao = userDao;
    }


    public Observable<ArrayList<RoomUserData>> executeUserData() {
        return apiCallInterface.getUserData();
    }

    public  LiveData<List<RoomUserData>> getUser() {
        mAllUsers = userDao.findAll();
        return mAllUsers;

        // refreshUser(userLogin); // try to refresh data if possible from Github Api
//        return userDao.getAllUser(); // return a LiveData directly from the database.
    }

    public void addUsers(List<RoomUserData> user) {
        new AddUser().execute(user);
    }
 public void delete(RoomUserData user) {
        new DeleteUser().execute(user);
    }

    public class AddUser extends AsyncTask<List<RoomUserData>, Void, Void> {
        @Override
        protected Void doInBackground(List<RoomUserData>... users) {
            for(int i=0;i<users.length;i++) {
                userDao.insert(users[0].get(i));
            }
            return null;
        }
    }

    public class DeleteUser extends AsyncTask<RoomUserData, Void, Void> {
        @Override
        protected Void doInBackground(RoomUserData... users) {
            userDao.delete(users[0]);
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
