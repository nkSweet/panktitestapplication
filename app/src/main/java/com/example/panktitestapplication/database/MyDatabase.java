package com.example.panktitestapplication.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {RoomUserData.class}, version = 1,exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {

//    // --- SINGLETON ---
//    private static volatile MyDatabase INSTANCE;

    // --- DAO ---
    public abstract UserDataDao userDao();



//    private static MyDatabase mInstance;
//
//    //method to get room database
//    public static MyDatabase getDatabase(Context context) {
//
//        if (mInstance == null)
//            mInstance = Room.databaseBuilder(context.getApplicationContext(),
//                    MyDatabase.class, "notes_db")
//                    .build();
//
//        return mInstance;
//    }
//
//    //method to remove instance
//    public static void closeDatabase() {
//        mInstance = null;
//    }

}