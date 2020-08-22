package com.example.vktest.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.vktest.R;
import com.example.vktest.mvp.presenter.BaseFeedPresenter;
import com.example.vktest.mvp.presenter.InfoPresenter;

import androidx.annotation.Nullable;
import butterknife.ButterKnife;

public class InfoFragment extends BaseFeedFragment {

    @InjectPresenter
    InfoPresenter mPresenter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setWithEndlessList(false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }


    @Override
    protected BaseFeedPresenter onCreateFeedPresenter() {
        return mPresenter;
    }

    @Override
    public int onCreateToolbarTitle() {
        return R.string.screen_name_info;
    }
}
