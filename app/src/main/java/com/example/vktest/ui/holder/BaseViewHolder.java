package com.example.vktest.ui.holder;

import android.view.View;

import com.example.vktest.model.view.BaseViewModel;

import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseViewHolder<Item extends BaseViewModel> extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindViewHolder(Item item);

    public abstract void unbindViewHolder();
}
