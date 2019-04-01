package com.example.panktitestapplication.database;

import android.arch.lifecycle.LiveData;

import java.util.List;

public interface ProductRepository {


    LiveData<RoomUserData> findById(int id);

    LiveData<List<RoomUserData>> findAll();

    long insert(RoomUserData product);

    int delete(RoomUserData product);
}
