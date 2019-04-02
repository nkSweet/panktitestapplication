package com.example.panktitestapplication.database;

import android.arch.lifecycle.LiveData;

import java.util.List;

public interface UserRepository {


    LiveData<List<RoomUserData>> findAll();

    long insert(RoomUserData userData);

    int delete(RoomUserData userData);
}
