package com.example.panktitestapplication.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface UserDataDao   {

    // Dao method to get all notes
    @Query("SELECT * FROM RoomUserData")
    LiveData<List<RoomUserData>> getAllNotes();

    // Dao method to insert note
    @Insert(onConflict = REPLACE)
    void insertNote(RoomUserData note);

    // Dao method to delete note
    @Delete
    void deleteNote(RoomUserData note);


    @Query("SELECT * FROM RoomUserData WHERE id=:id")
    LiveData<RoomUserData> findById(int id);

    @Query("SELECT * FROM RoomUserData")
    LiveData<List<RoomUserData>> findAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(RoomUserData product);

    @Delete
    int delete(RoomUserData product);
}
