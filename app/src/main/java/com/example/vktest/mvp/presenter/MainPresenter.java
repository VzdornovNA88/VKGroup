package com.example.vktest.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.vktest.CurrentUser;
import com.example.vktest.MyApplication;
import com.example.vktest.common.manager.MyFragmentManager;
import com.example.vktest.common.manager.NetworkManager;
import com.example.vktest.model.Profile;
import com.example.vktest.mvp.view.MainView;
import com.example.vktest.rest.api.UsersApi;
import com.example.vktest.rest.model.request.UsersGetRequestModel;
import com.example.vktest.ui.activity.SettingActivity;
import com.example.vktest.ui.fragment.BaseFragment;
import com.example.vktest.ui.fragment.BoardFragment;
import com.example.vktest.ui.fragment.GroupRulesFragment;
import com.example.vktest.ui.fragment.InfoFragment;
import com.example.vktest.ui.fragment.MembersFragment;
import com.example.vktest.ui.fragment.MyPostsFragment;
import com.example.vktest.ui.fragment.NewsFeedFragment;

import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmObject;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    @Inject
    UsersApi mUserApi;
    @Inject
    NetworkManager mNetworkManager;
    @Inject
    MyFragmentManager myFragmentManager;

    public MainPresenter() {
        MyApplication.getApplicationComponent().inject(this);
    }

    public void checkAuth() {
        if (!CurrentUser.isAuthorized()) {
            getViewState().startSignIn();
        } else {
            getCurrentUser();
            getViewState().signedIn();
        }
    }

    public Observable<Profile> getProfileFromNetwork() {
        return mUserApi.get(new UsersGetRequestModel(CurrentUser.getId()).toMap())
                .flatMap(listFull -> Observable.fromIterable(listFull.response))
                .doOnNext(this::saveToDb);
    }

    private Observable<Profile> getProfileFromDb() {
        return Observable.fromCallable(getListFromRealmCallable());
    }

    public void saveToDb(RealmObject item) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> realm1.copyToRealmOrUpdate(item));
    }

    public Callable<Profile> getListFromRealmCallable() {
        return () -> {
            Realm realm = Realm.getDefaultInstance();
            Profile realmResults = realm.where(Profile.class)
                    .equalTo("id", Integer.parseInt(CurrentUser.getId()))
                    .findFirst();
            return realm.copyFromRealm(realmResults);
        };
    }

    private void getCurrentUser() {
        mNetworkManager.getNetworkObservable()
                .flatMap(aBoolean -> {
                    if (!CurrentUser.isAuthorized()) {
                        getViewState().startSignIn();
                    }

                    return aBoolean
                            ? getProfileFromNetwork()
                            : getProfileFromDb();
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(profile -> {
                    getViewState().showCurrentUser(profile);
                }, error -> {
                    error.printStackTrace();
                });
    }

    public void drawerItemClick(int id) {
        BaseFragment fragment = null;

        switch (id) {
            case 1:
                fragment = new NewsFeedFragment();
                break;
            case 2:
                fragment = new MyPostsFragment();
                break;
            case 3:
                getViewState().startActivityFromDrawer(SettingActivity.class);
                return;
            case 4:
                fragment = new MembersFragment();
                break;
            case 5:
                fragment = new BoardFragment();
                break;
            case 6:
                fragment = new InfoFragment();
                break;
            case 7:
                fragment = new GroupRulesFragment();
                break;
        }

        if (fragment != null && !myFragmentManager.isAlreadyContains(fragment)) {
            getViewState().showFragmentFromDrawer(fragment);
        }
    }
}
