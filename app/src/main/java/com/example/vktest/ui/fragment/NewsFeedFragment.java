package com.example.vktest.ui.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.View;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.vktest.MyApplication;
import com.example.vktest.R;
import com.example.vktest.common.utils.VkListHelper;
import com.example.vktest.model.WallItem;
import com.example.vktest.model.view.BaseViewModel;
import com.example.vktest.model.view.NewsItemBodyViewModel;
import com.example.vktest.model.view.NewsItemFooterViewModel;
import com.example.vktest.model.view.NewsItemHeaderViewModel;
import com.example.vktest.mvp.presenter.BaseFeedPresenter;
import com.example.vktest.mvp.presenter.NewsFeedPresenter;
import com.example.vktest.rest.api.WallApi;
import com.example.vktest.rest.model.request.WallGetRequestModel;
import com.example.vktest.rest.model.response.GetWallResponse;
import com.example.vktest.ui.activity.CreatePostActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFeedFragment extends BaseFeedFragment {

    public static final int YOUR_GROUP_ID = -86529522;
    @Inject
    WallApi mWallApi;

    @InjectPresenter
    NewsFeedPresenter mPresenter;

    @Override
    protected BaseFeedPresenter onCreateFeedPresenter() {
        return mPresenter;
    }

    public NewsFeedFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyApplication.getApplicationComponent().inject(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

      /*  mWallApi.get(new WallGetRequestModel(YOUR_GROUP_ID).toMap())
                .flatMap(full -> Observable.fromIterable(VkListHelper.getWallList(full.response)))
                .flatMap(wallItem -> {
                    List<BaseViewModel> baseItems = new ArrayList<>();
                    baseItems.add(new NewsItemHeaderViewModel(wallItem));
                    baseItems.add(new NewsItemBodyViewModel(wallItem));
                    baseItems.add(new NewsItemFooterViewModel(wallItem));
                    return Observable.fromIterable(baseItems);
                })
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(objects -> mAdapter.addItems(objects));*/
    }

    @Override
    public int onCreateToolbarTitle() {
        return R.string.screen_name_news;
    }

    @Override
    public void onResume() {
        super.onResume();
        getBaseActivity().mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), CreatePostActivity.class);
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    public boolean needFab() {
        return true;
    }
}