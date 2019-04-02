package com.example.panktitestapplication.di;

import com.example.panktitestapplication.view.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class, UtilsModule.class})
@Singleton
public interface AppComponent {

    void doInjection(MainActivity mainActivity);

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