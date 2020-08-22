package com.example.vktest.di.module;

import com.example.vktest.rest.RestClient;
import com.example.vktest.rest.api.AccountApi;
import com.example.vktest.rest.api.BoardApi;
import com.example.vktest.rest.api.GroupsApi;
import com.example.vktest.rest.api.UsersApi;
import com.example.vktest.rest.api.VideoApi;
import com.example.vktest.rest.api.WallApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RestModule {
    private RestClient mRestClient;


    public RestModule() {
        mRestClient = new RestClient();
    }

    @Provides
    @Singleton
    public RestClient provideRestClient() {
        return mRestClient;
    }

    @Provides
    @Singleton
    public WallApi provideWallApi() {
        return mRestClient.createService(WallApi.class);
    }

    @Provides
    @Singleton
    public UsersApi provideUsersApi() {
        return mRestClient.createService(UsersApi.class);
    }

    @Provides
    @Singleton
    public GroupsApi provideGroupsApi() {
        return mRestClient.createService(GroupsApi.class);
    }

    @Provides
    @Singleton
    public BoardApi provideBoardApi() {
        return mRestClient.createService(BoardApi.class);
    }

    @Provides
    @Singleton
    public VideoApi provideVideoApi() {
        return mRestClient.createService(VideoApi.class);
    }

    @Provides
    @Singleton
    public AccountApi provideAccountApi() {
        return mRestClient.createService(AccountApi.class);
    }
}
