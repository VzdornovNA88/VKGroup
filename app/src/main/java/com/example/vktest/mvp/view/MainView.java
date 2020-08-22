package com.example.vktest.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.example.vktest.model.Profile;
import com.example.vktest.ui.fragment.BaseFragment;

public interface MainView extends MvpView {
    void startSignIn();
    void signedIn();
    void showCurrentUser(Profile profile);
    void showFragmentFromDrawer(BaseFragment baseFragment);
    void startActivityFromDrawer(Class<?> act);
}
