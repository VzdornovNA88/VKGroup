package com.example.vktest.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.example.vktest.MyApplication;
import com.example.vktest.consts.ApiConstants;
import com.example.vktest.model.Member;
import com.example.vktest.model.Profile;
import com.example.vktest.model.view.BaseViewModel;
import com.example.vktest.model.view.MemberViewModel;
import com.example.vktest.mvp.view.BaseFeedView;
import com.example.vktest.rest.api.GroupsApi;
import com.example.vktest.rest.api.UsersApi;
import com.example.vktest.rest.model.request.GroupsGetByIdRequestModel;
import com.example.vktest.rest.model.request.UsersGetRequestModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.realm.Realm;

@InjectViewState
public class InfoContactsPresenter extends BaseFeedPresenter<BaseFeedView>{

    @Inject
    GroupsApi mGroupApi;

    @Inject
    UsersApi mUserApi;

    public InfoContactsPresenter() {
        MyApplication.getApplicationComponent().inject(this);
    }

    @Override
    public Observable<BaseViewModel> onCreateLoadDataObservable(int count, int offset) {
        return mGroupApi.getById(new GroupsGetByIdRequestModel(ApiConstants.MY_GROUP_ID).toMap())
                .flatMap(listFull -> Observable.fromIterable(listFull.response))
                .flatMap(group-> Observable.fromIterable(group.getContactsList()))
                .flatMap(contact -> mUserApi.get(new UsersGetRequestModel(String.valueOf(contact.getUserId())).toMap()))
                .flatMap(listFull -> Observable.fromIterable(listFull.response))
                .doOnNext(profile -> profile.setContact(true))
                .doOnNext(this::saveToDb)
                .flatMap(profile -> Observable.fromIterable(parsePojoModel(profile)));
    }

    @Override
    public Observable<BaseViewModel> onCreateRestoreDataObservable() {
        return Observable.fromCallable(getListFromRealmCallable())
                .flatMap(profile -> Observable.fromIterable(parsePojoModel(profile)));

    }

    private List<BaseViewModel> parsePojoModel(Profile profile) {
        List<BaseViewModel> items = new ArrayList<>();
        Member m = new Member(profile);
        items.add(new MemberViewModel(m));

        return items;
    }

    private List<BaseViewModel> parsePojoModel(List<Profile> profileList) {
        List<BaseViewModel> items = new ArrayList<>();
        for (Profile profile : profileList) {
            items.addAll(parsePojoModel(profile));
        }

        return items;
    }

    public Callable<List<Profile>> getListFromRealmCallable() {
        return () -> {
            Realm realm = Realm.getDefaultInstance();
            List<Profile> result = realm.where(Profile.class)
                    .equalTo("isContact", true)
                    .findAll();
            return realm.copyFromRealm(result);
        };
    }
}
