package com.example.vktest.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.example.vktest.model.WallItem;
import com.example.vktest.model.view.counter.LikeCounterViewModel;

public interface PostFooterView extends MvpView {
    void like(LikeCounterViewModel likes);

    void openComments(WallItem wallItem);
}