package com.example.panktitestapplication.Utils;


import com.example.panktitestapplication.database.RoomUserData;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
public class ApiResponse {
    public final Status status;

    @Nullable
    public final ArrayList<RoomUserData> data;

    @Nullable
    public final Throwable error;

    private ApiResponse(Status status, @Nullable ArrayList<RoomUserData> data, @Nullable Throwable error) {
        this.data = data;
        this.status = status;

        this.error = error;
    }

    public static ApiResponse loading() {
        return new ApiResponse(Status.LOADING, null, null);
    }

    public static ApiResponse success(@NonNull ArrayList<RoomUserData> data) {
        return new ApiResponse(Status.SUCCESS,data, null);
    }

    public static ApiResponse error(@NonNull Throwable error) {
        return new ApiResponse(Status.ERROR, null, error);
    }

}
