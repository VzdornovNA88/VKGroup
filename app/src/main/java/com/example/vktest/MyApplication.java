package com.example.vktest;

import android.app.Application;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.vktest.di.component.ApplicationComponent;
import com.example.vktest.di.component.DaggerApplicationComponent;
import com.example.vktest.di.module.ApplicationModule;
import com.mikepenz.materialdrawer.util.AbstractDrawerImageLoader;
import com.mikepenz.materialdrawer.util.DrawerImageLoader;
import com.vk.sdk.VKSdk;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends Application {

    private static ApplicationComponent sApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        initComponent();

        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);

        VKSdk.initialize(this);

        DrawerImageLoader.init(new AbstractDrawerImageLoader() {
            @Override
            public void set(ImageView imageView, Uri uri, Drawable placeholder, String tag) {
                Glide.with(imageView.getContext()).load(uri).into(imageView);
            }
        });
    }

    private void initComponent() {

        sApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();
    }

    public static ApplicationComponent getApplicationComponent() {

        return sApplicationComponent;
    }
}
