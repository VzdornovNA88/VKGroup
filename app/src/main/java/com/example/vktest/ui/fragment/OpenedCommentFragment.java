package com.example.vktest.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.vktest.MyApplication;
import com.example.vktest.R;
import com.example.vktest.mvp.presenter.BaseFeedPresenter;
import com.example.vktest.mvp.presenter.OpenedCommentPresenter;

import androidx.annotation.Nullable;
import butterknife.ButterKnife;

public class OpenedCommentFragment extends BaseFeedFragment {

    int id;
    @InjectPresenter
    OpenedCommentPresenter mPresenter;

    public static OpenedCommentFragment newInstance(int id) {
        Bundle args = new Bundle();
        args.putInt("id", id);
        OpenedCommentFragment fragment = new OpenedCommentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected BaseFeedPresenter onCreateFeedPresenter() {
        mPresenter.setId(id);
        return mPresenter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getApplicationComponent().inject(this);

        setWithEndlessList(false);

        if (getArguments() != null) {
            this.id = getArguments().getInt("id");
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    protected int getMainContentLayout() {
        return R.layout.fragment_opened_wall_item;
    }

    @Override
    public int onCreateToolbarTitle() {
        return R.string.screen_name_opened_comment;
    }



}
