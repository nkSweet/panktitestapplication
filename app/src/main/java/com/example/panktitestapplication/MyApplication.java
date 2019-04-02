package com.example.panktitestapplication;

import android.app.Application;
import android.content.Context;

import com.example.panktitestapplication.di.AppComponent;
import com.example.panktitestapplication.di.AppModule;
import com.example.panktitestapplication.di.DaggerAppComponent;
import com.example.panktitestapplication.di.UtilsModule;


public class MyApplication extends Application {

    AppComponent appComponent;
    Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).utilsModule(new UtilsModule(context)).build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }
}
