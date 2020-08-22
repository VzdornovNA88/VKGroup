package com.example.vktest.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.example.vktest.CurrentUser;
import com.example.vktest.MyApplication;
import com.example.vktest.common.utils.VkListHelper;
import com.example.vktest.consts.ApiConstants;
import com.example.vktest.model.WallItem;
import com.example.vktest.model.view.BaseViewModel;
import com.example.vktest.model.view.NewsItemBodyViewModel;
import com.example.vktest.model.view.NewsItemFooterViewModel;
import com.example.vktest.model.view.NewsItemHeaderViewModel;
import com.example.vktest.mvp.view.BaseFeedView;
import com.example.vktest.rest.api.WallApi;
import com.example.vktest.rest.model.request.WallGetRequestModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

@InjectViewState
public class NewsFeedPresenter extends BaseFeedPresenter<BaseFeedView> {

    @Inject
    WallApi mWallApi;
    private boolean enableIdFiltering = false;

    public void setEnableIdFiltering(boolean enableIdFiltering) {
        this.enableIdFiltering = enableIdFiltering;
    }

    public NewsFeedPresenter() {
        MyApplication.getApplicationComponent().inject(this);
    }

    @Override
    public Observable<BaseViewModel> onCreateLoadDataObservable(int count, int offset) {
        return mWallApi.get(new WallGetRequestModel(ApiConstants.MY_GROUP_ID, count, offset).toMap())
                .flatMap(full -> Observable.fromIterable(VkListHelper.getWallList(full.response)))
                .compose(applyFilter())
                .doOnNext(this::saveToDb)
                .flatMap(wallItem -> {
                    List<BaseViewModel> baseItems = new ArrayList<>();
                    baseItems.add(new NewsItemHeaderViewModel(wallItem));
                    baseItems.add(new NewsItemBodyViewModel(wallItem));
                    baseItems.add(new NewsItemFooterViewModel(wallItem));
                    return Observable.fromIterable(baseItems);
                });
    }

    @Override
    public Observable<BaseViewModel> onCreateRestoreDataObservable() {
        return Observable.fromCallable(getListFromRealmCallable())
                .flatMap(Observable::fromIterable)
                .compose(applyFilter())
                .flatMap(wallItem -> Observable.fromIterable(parsePojoModel(wallItem)));
    }

    private List<BaseViewModel> parsePojoModel(WallItem wallItem) {
        List<BaseViewModel> baseItems = new ArrayList<>();
        baseItems.add(new NewsItemHeaderViewModel(wallItem));
        baseItems.add(new NewsItemBodyViewModel(wallItem));
        baseItems.add(new NewsItemFooterViewModel(wallItem));
        return baseItems;
    }

    public Callable<List<WallItem>> getListFromRealmCallable() {
        return () -> {
            String[] sortFields = {"date"};
            Sort[] sortOrder = {Sort.DESCENDING};
            Realm realm = Realm.getDefaultInstance();
            RealmResults<WallItem> realmResults = realm.where(WallItem.class)
                    .findAllSorted(sortFields, sortOrder);
            return realm.copyFromRealm(realmResults);
        };
    }

    protected ObservableTransformer<WallItem, WallItem> applyFilter() {
        if (enableIdFiltering && CurrentUser.getId() != null) {
            return baseItemObservable -> baseItemObservable.
                    filter(wallItem -> CurrentUser.getId().equals(String.valueOf(wallItem.getFromId())));
        } else {
            return baseItemObservable -> baseItemObservable;
        }
    }
}
