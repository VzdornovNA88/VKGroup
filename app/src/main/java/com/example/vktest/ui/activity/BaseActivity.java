package com.example.vktest.ui.activity;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.example.vktest.MyApplication;
import com.example.vktest.R;
import com.example.vktest.common.manager.MyFragmentManager;
import com.example.vktest.ui.fragment.BaseFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import javax.inject.Inject;

import androidx.annotation.LayoutRes;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseActivity extends MvpAppCompatActivity {

    @Inject
    MyFragmentManager myFragmentManager;

    @BindView(R.id.progress)
    protected ProgressBar mProgressBar;

    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @BindView(R.id.fab)
    public FloatingActionButton mFab;

    public ProgressBar getmProgressBar() {
        return mProgressBar;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyApplication.getApplicationComponent().inject(this);

        setContentView(R.layout.activity_base);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        FrameLayout parent = (FrameLayout) findViewById(R.id.main_wrapper);
        getLayoutInflater().inflate(getMainContentLayout(), parent);
    }

    public void fragmentOnScreen(BaseFragment baseFragment) {
        setToolbarTitle(baseFragment.createToolbarTitle(this));
        setupFabVisibility(baseFragment.needFab());
    }


    private void setToolbarTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    @LayoutRes
    protected abstract int getMainContentLayout();

    public void setContent(BaseFragment fragment) {
        myFragmentManager.setFragment(this, fragment, R.id.main_wrapper);
    }

    public void addContent(BaseFragment fragment) {
        myFragmentManager.addFragment(this, fragment, R.id.main_wrapper);
    }

    public boolean removeCurrentFragment() {
        return myFragmentManager.removeCurrentFragment(this);
    }

    public boolean removeFragment(BaseFragment fragment) {
        return myFragmentManager.removeFragment(this, fragment);
    }

    public void setupFabVisibility(boolean needFab) {
        if (mFab == null) return;

        if (needFab) {
            mFab.show();
        } else {
            mFab.hide();
        }
    }

    @Override
    public void onBackPressed() {
        removeCurrentFragment();
    }
}
