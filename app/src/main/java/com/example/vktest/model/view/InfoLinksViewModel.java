package com.example.vktest.model.view;

import android.view.View;
import android.widget.RelativeLayout;

import com.example.vktest.MyApplication;
import com.example.vktest.R;
import com.example.vktest.ui.holder.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InfoLinksViewModel extends BaseViewModel {

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.InfoLinks;
    }

    @Override
    public InfoLinksViewHolder onCreateViewHolder(View view) {
        return new InfoLinksViewHolder(view);
    }


    public static class InfoLinksViewHolder extends BaseViewHolder<InfoLinksViewModel> {


        @BindView(R.id.rv_links)
        RelativeLayout rvLinks;

        public InfoLinksViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
            MyApplication.getApplicationComponent().inject(this);
        }

        @Override
        public void bindViewHolder(InfoLinksViewModel infoLinksViewModel) {

        }

        @Override
        public void unbindViewHolder() {

        }
    }
}
