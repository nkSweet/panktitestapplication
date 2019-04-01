package com.example.panktitestapplication.database;

import android.arch.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

public class ProductDataSource implements ProductRepository {

    private UserDataDao productDao;

    @Inject
    public ProductDataSource(UserDataDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public LiveData<RoomUserData> findById(int id) {
        return productDao.findById(id);
    }

    @Override
    public LiveData<List<RoomUserData>> findAll() {
        return productDao.findAll();
    }

    @Override
    public long insert(RoomUserData product) {
        return productDao.insert(product);
    }

    @Override
    public int delete(RoomUserData product) {
        return productDao.delete(product);
    }
}
