package com.example.vktest.ui.activity;

//активити, которое открывается при клике на уведомлении

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.vktest.MyApplication;
import com.example.vktest.R;
import com.example.vktest.common.manager.MyFragmentManager;
import com.example.vktest.model.Place;
import com.example.vktest.ui.fragment.OpenedPostFragment;

import javax.inject.Inject;

public class OpenedPostFromPushActivity extends BaseActivity{

    @Inject
    MyFragmentManager myFragmentManager;
    private Place place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getApplicationComponent().inject(this);

        //из интента получаем place
        Bundle bundle = getIntent().getBundleExtra(Place.PLACE);

        Place place = new Place(bundle);
        //открываем пост по id из place
        myFragmentManager.addFragment(this,
                OpenedPostFragment.newInstance(Integer.valueOf(place.getPostId())),
                R.id.main_wrapper);
    }

    @Override
    protected int getMainContentLayout() {
        return R.layout.activity_opened_post_from_push;
    }

    @Override
    public void onBackPressed() {

        Log.d("BACKSTACK", String.valueOf(getSupportFragmentManager().getBackStackEntryCount()));

        //управляем стеком переходов назад, из открытого поста выходим в приложение
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
            super.onBackPressed();
        } else {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

    }


}
