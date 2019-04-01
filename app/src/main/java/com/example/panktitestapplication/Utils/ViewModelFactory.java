package com.example.panktitestapplication.Utils;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.hardware.usb.UsbRequest;
import android.support.annotation.NonNull;


import com.example.panktitestapplication.Repository;
import com.example.panktitestapplication.UserViewModel;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton

public class ViewModelFactory implements ViewModelProvider.Factory {

    private Repository repository;

    @Inject
    public ViewModelFactory(Repository repository) {
        this.repository = repository;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(UserViewModel.class)) {
            return (T) new UserViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown class name");
    }
}
