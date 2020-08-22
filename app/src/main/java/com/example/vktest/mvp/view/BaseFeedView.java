package com.example.vktest.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.example.vktest.model.view.BaseViewModel;

import java.util.List;

public interface BaseFeedView extends MvpView {
    void showRefreshing();

    void hideRefreshing();

    void showListProgress();

    void hideListProgress();

    void showError(String message);

    void setItems(List<BaseViewModel> items);

    void addItems(List<BaseViewModel> items);
}
