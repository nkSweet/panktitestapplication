package com.example.panktitestapplication.Utils;

import com.example.panktitestapplication.database.RoomUserData;


import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiCallInterface {
    @GET("/users")
    Observable<ArrayList<RoomUserData>> getUserData();
}
