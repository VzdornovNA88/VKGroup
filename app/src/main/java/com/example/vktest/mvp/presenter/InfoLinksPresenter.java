package com.example.vktest.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.example.vktest.MyApplication;
import com.example.vktest.consts.ApiConstants;
import com.example.vktest.model.Group;
import com.example.vktest.model.attachment.Link;
import com.example.vktest.model.view.BaseViewModel;
import com.example.vktest.model.view.LinkAttachmentViewModel;
import com.example.vktest.mvp.view.BaseFeedView;
import com.example.vktest.rest.api.GroupsApi;
import com.example.vktest.rest.model.request.GroupsGetByIdRequestModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.realm.Realm;

@InjectViewState
public class InfoLinksPresenter extends BaseFeedPresenter<BaseFeedView>{

    @Inject
    GroupsApi mGroupApi;

    public InfoLinksPresenter() {
        MyApplication.getApplicationComponent().inject(this);
    }

    @Override
    public Observable<BaseViewModel> onCreateLoadDataObservable(int count, int offset) {
        return mGroupApi.getById(new GroupsGetByIdRequestModel(ApiConstants.MY_GROUP_ID).toMap())
                .flatMap(listFull -> Observable.fromIterable(listFull.response))
                .doOnNext(this::saveToDb)
                .flatMap(group -> Observable.fromIterable(parsePojoModel(group)));
    }

    @Override
    public Observable<BaseViewModel> onCreateRestoreDataObservable() {
        return Observable.fromCallable(getListFromRealmCallable())
                .flatMap(group -> Observable.fromIterable(parsePojoModel(group)));
    }

    private List<BaseViewModel> parsePojoModel(Group group) {
        List<BaseViewModel> items = new ArrayList<>();

        for (Link link : group.getLinks()) {

            items.add(new LinkAttachmentViewModel(link));

        }

        return items;
    }

    public Callable<Group> getListFromRealmCallable() {
        return () -> {
            Realm realm = Realm.getDefaultInstance();
            Group result = realm.where(Group.class)
                    .equalTo("id", Math.abs(ApiConstants.MY_GROUP_ID))
                    .findFirst();
            return realm.copyFromRealm(result);
        };
    }
}
