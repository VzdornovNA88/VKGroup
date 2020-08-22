package com.example.vktest.ui.fragment;

import android.os.Bundle;

import com.example.vktest.R;

import androidx.annotation.Nullable;

public class MyPostsFragment extends NewsFeedFragment {
    public MyPostsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.setEnableIdFiltering(true);
    }

    @Override
    public int onCreateToolbarTitle() {
        return R.string.screen_name_my_posts;
    }
}
