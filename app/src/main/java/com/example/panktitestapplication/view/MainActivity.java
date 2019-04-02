package com.example.panktitestapplication.view;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.panktitestapplication.MyApplication;
import com.example.panktitestapplication.R;
import com.example.panktitestapplication.Utils.ApiResponse;
import com.example.panktitestapplication.Utils.ViewModelFactory;
import com.example.panktitestapplication.database.RoomUserData;

import java.util.ArrayList;

import javax.inject.Inject;

import com.example.panktitestapplication.view.adapter.UserListRvAdapter;

public class MainActivity extends AppCompatActivity {
    @Inject
    ViewModelFactory viewModelFactory;

      RecyclerView rvUserDetails;
     UserViewModel userViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvUserDetails = (RecyclerView) findViewById(R.id.rv_userdetails);
        rvUserDetails.setLayoutManager(new LinearLayoutManager(this));

        ((MyApplication) getApplication()).getAppComponent().doInjection(this);

        userViewModel = ViewModelProviders.of(this, viewModelFactory).get(UserViewModel.class);

        userViewModel.UserDataResponse().observe(this, this::consumeResponse);

        userViewModel.callUserDetailsApi();

    }
    private void consumeResponse(ApiResponse apiResponse) {

        switch (apiResponse.status) {

            case LOADING:
                Log.e("LOADING ", "response");
//                progressDialog.show();
                break;

            case SUCCESS:
//                progressDialog.dismiss();
                renderSuccessResponse(apiResponse.data);
                break;

            case ERROR:
//                progressDialog.dismiss();
                Log.e("ERROR ", "response");
                Toast.makeText(MainActivity.this,"ERROR ", Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }
    }


    private void renderSuccessResponse(ArrayList<RoomUserData> response) {
        if (response != null) {
            if (response.size() > 0) {
//                Log.e("responsisJsonNulle=",""+response.size());
                rvUserDetails.setAdapter(new UserListRvAdapter(response,userViewModel));
                userViewModel.addUser(response);

            } else {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        } else {
            Log.e("null ", "response");
        }
    }
}
