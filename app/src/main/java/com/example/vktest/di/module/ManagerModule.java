package com.example.vktest.di.module;

import com.example.vktest.common.manager.MyFragmentManager;
import com.example.vktest.common.manager.NetworkManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ManagerModule {
    @Provides
    @Singleton
    MyFragmentManager provideMyFragmentManager() {
        return new MyFragmentManager();
    }

    @Provides
    @Singleton
    NetworkManager provideNetworkManager() {
        return new NetworkManager();
    }
}
