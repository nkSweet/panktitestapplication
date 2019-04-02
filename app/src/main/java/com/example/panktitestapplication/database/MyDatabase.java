package com.example.panktitestapplication.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {RoomUserData.class}, version = 1,exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {

    public abstract UserDataDao userDao();


}