package com.example.panktitestapplication.di;

import android.app.Application;

import com.example.panktitestapplication.MainActivity;
import com.example.panktitestapplication.MyApplication;
import com.example.panktitestapplication.database.MyDatabase;
import com.example.panktitestapplication.database.ProductRepository;
import com.example.panktitestapplication.database.UserDataDao;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Component(modules = {AppModule.class, UtilsModule.class})
@Singleton
public interface AppComponent {

    void doInjection(MainActivity mainActivity);
//    UserDataDao productDao();
//
//    MyDatabase demoDatabase();
//
//    ProductRepository productRepository();


}


//@Singleton
//@Component(modules={AppModule.class, UtilsModule.class})
//public interface AppComponent {
//
//    @Component.Builder
//    interface Builder {
//        @BindsInstance
//        Builder application(Application application);
//        AppComponent build();
//    }
//
//    void inject(MyApplication app);
//}