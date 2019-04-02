package com.example.panktitestapplication.database;

import android.arch.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

public class UserDataSource implements UserRepository {

    private UserDataDao userDataDao;

    @Inject
    public UserDataSource(UserDataDao productDao) {
        this.userDataDao = productDao;
    }

    @Override
    public LiveData<List<RoomUserData>> findAll() {
        return userDataDao.findAll();
    }

    @Override
    public long insert(RoomUserData product) {
        return userDataDao.insert(product);
    }

    @Override
    public int delete(RoomUserData product) {
        return userDataDao.delete(product);
    }
}
