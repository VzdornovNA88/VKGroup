package com.example.vktest.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.vktest.MyApplication;
import com.example.vktest.R;
import com.example.vktest.model.Place;
import com.example.vktest.mvp.presenter.BaseFeedPresenter;
import com.example.vktest.mvp.presenter.TopicCommentsPresenter;

import androidx.annotation.Nullable;
import butterknife.ButterKnife;

public class TopicCommentsFragment extends BaseFeedFragment {

    public static TopicCommentsFragment newInstance(Place place) {

        Bundle args = new Bundle();
        args.putAll(place.toBundle());

        TopicCommentsFragment fragment = new TopicCommentsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @InjectPresenter
    TopicCommentsPresenter mPresenter;


    Place mPlace;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getApplicationComponent().inject(this);
        setWithEndlessList(true);

        mPlace = new Place(getArguments());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }


    @Override
    protected BaseFeedPresenter onCreateFeedPresenter() {
        mPresenter.setPlace(mPlace);
        return mPresenter;
    }

    @Override
    public int onCreateToolbarTitle() {
        return R.string.screen_name_comments;
    }
}
