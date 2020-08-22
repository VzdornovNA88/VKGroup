package com.example.vktest.model.view;

import android.view.View;
import android.widget.RelativeLayout;

import com.example.vktest.MyApplication;
import com.example.vktest.R;
import com.example.vktest.ui.holder.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InfoContactsViewModel extends BaseViewModel {
    @Override
    public LayoutTypes getType() {
        return LayoutTypes.InfoContacts;
    }

    @Override
    public InfoContactsViewHolder onCreateViewHolder(View view) {
        return new InfoContactsViewHolder(view);
    }


    public static class InfoContactsViewHolder extends BaseViewHolder<InfoContactsViewModel> {
        @BindView(R.id.rv_contacts)
        RelativeLayout rvContacts;

        public InfoContactsViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
            MyApplication.getApplicationComponent().inject(this);
        }

        @Override
        public void bindViewHolder(InfoContactsViewModel infoContactsViewModel) {
        }

        @Override
        public void unbindViewHolder() {
        }
    }
}
