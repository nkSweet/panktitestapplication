package com.example.panktitestapplication.di;


import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.panktitestapplication.Repository;
import com.example.panktitestapplication.Utils.ApiCallInterface;
import com.example.panktitestapplication.Utils.ViewModelFactory;
import com.example.panktitestapplication.database.MyDatabase;
import com.example.panktitestapplication.database.ProductDataSource;
import com.example.panktitestapplication.database.UserDataDao;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class UtilsModule {
    private MyDatabase demoDatabase;


    public UtilsModule(Context application) {
        demoDatabase = Room.databaseBuilder(application,
                MyDatabase.class, "MyDatabase.db")
                .build();;
    }

//    @Singleton
//    @Provides
//    MyDatabase provideDatabase(Application application) {
//        return Room.databaseBuilder(application,
//                MyDatabase.class, "MyDatabase.db")
//                .build();
//    }


    @Singleton
    @Provides
    MyDatabase providesRoomDatabase() {
        return demoDatabase;
    }



    @Provides
    @Singleton
    UserDataDao provideUserDao(MyDatabase database) { return database.userDao(); }


    @Singleton
    @Provides
    ProductDataSource productRepository(UserDataDao productDao) {
        return new ProductDataSource(productDao);
    }


    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder builder =
                new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return builder.setLenient().create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {

        return new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Provides
    @Singleton
    ApiCallInterface getApiCallInterface(Retrofit retrofit) {
        return retrofit.create(ApiCallInterface.class);
    }

    @Provides
    @Singleton
    OkHttpClient getRequestHeader() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .build();
            return chain.proceed(request);
        })
                .connectTimeout(100, TimeUnit.SECONDS)
                .writeTimeout(100, TimeUnit.SECONDS)
                .readTimeout(300, TimeUnit.SECONDS);

        return httpClient.build();
    }

    @Provides
    @Singleton
    Repository getRepository(ApiCallInterface apiCallInterface,UserDataDao userDao, Executor executor) {
        return new Repository(apiCallInterface,userDao,executor);
    }

    @Provides
    @Singleton
    ViewModelProvider.Factory getViewModelFactory(Repository myRepository) {
        return new ViewModelFactory(myRepository);
    }
}
