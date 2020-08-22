package com.example.vktest.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.example.vktest.MyApplication;
import com.example.vktest.consts.ApiConstants;
import com.example.vktest.model.Member;
import com.example.vktest.model.Topic;
import com.example.vktest.model.view.BaseViewModel;
import com.example.vktest.model.view.TopicViewModel;
import com.example.vktest.mvp.view.BaseFeedView;
import com.example.vktest.rest.api.BoardApi;
import com.example.vktest.rest.model.request.BoardGetTopicsRequestModel;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

@InjectViewState
public class BoardPresenter extends BaseFeedPresenter<BaseFeedView> {
    @Inject
    BoardApi mBoardApi;

    public BoardPresenter() {
        MyApplication.getApplicationComponent().inject(this);
    }

    @Override
    public Observable<BaseViewModel> onCreateLoadDataObservable(int count, int offset) {
        return mBoardApi.getTopics(new BoardGetTopicsRequestModel(
                ApiConstants.MY_GROUP_ID, count, offset).toMap())
                .flatMap(baseItemResponseFull -> Observable.fromIterable(baseItemResponseFull.response.getItems()))
                .doOnNext(topic -> topic.setGroupId(ApiConstants.MY_GROUP_ID))
                .doOnNext(this::saveToDb)
                .map(TopicViewModel::new);
    }

    @Override
    public Observable<BaseViewModel> onCreateRestoreDataObservable() {
        return Observable.fromCallable(getListFromRealmCallable())
                .flatMap(Observable::fromIterable)
                .map(TopicViewModel::new);
    }


    public Callable<List<Topic>> getListFromRealmCallable() {
        return () -> {
            String[] sortFields = {Member.ID};
            Sort[] sortOrder = {Sort.DESCENDING};

            Realm realm = Realm.getDefaultInstance();
            RealmResults<Topic> results = realm.where(Topic.class)
                    .equalTo("groupId", ApiConstants.MY_GROUP_ID)
                    .findAllSorted(sortFields, sortOrder);

            return realm.copyFromRealm(results);
        };
    }
}
