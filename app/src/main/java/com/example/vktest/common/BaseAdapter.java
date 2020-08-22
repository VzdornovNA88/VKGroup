package com.example.vktest.common;

import android.view.ViewGroup;

import com.example.vktest.model.view.BaseViewModel;
import com.example.vktest.ui.holder.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import androidx.collection.ArrayMap;
import androidx.recyclerview.widget.RecyclerView;

public class BaseAdapter extends RecyclerView.Adapter<BaseViewHolder<BaseViewModel>> {

    private List<BaseViewModel> list = new ArrayList<>();

    private ArrayMap<Integer, BaseViewModel> mTypeInstances = new ArrayMap<>();

    @Override
    public BaseViewHolder<BaseViewModel> onCreateViewHolder(ViewGroup parent, int viewType) {
        return mTypeInstances.get(viewType).createViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<BaseViewModel> holder, int position) {
        holder.bindViewHolder(getItem(position));
    }

    @Override
    public void onViewRecycled(BaseViewHolder<BaseViewModel> holder) {
        super.onViewRecycled(holder);
        holder.unbindViewHolder();
    }

    public void registerTypeInstance(BaseViewModel item) {
        if (!mTypeInstances.containsKey(item.getType())) {
            mTypeInstances.put(item.getType().getValue(), item);
        }
    }


    public int getRealItemCount() {
        int count = 0;
        for (int i = 0; i < getItemCount(); i++) {
            if (!getItem(i).isItemDecorator()) {
                count += 1;
            }
        }
        return count;
    }

    public void setItems(List<BaseViewModel> items) {
        clearList();
        addItems(items);
    }

    public void addItems(List<? extends BaseViewModel> newItems) {

        for (BaseViewModel newItem : newItems) {
            registerTypeInstance(newItem);
        }

        list.addAll(newItems);

        notifyDataSetChanged();
    }


    public void clearList() {
        list.clear();
    }


    @Override
    public int getItemViewType(int position) {
        return getItem(position).getType().getValue();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public BaseViewModel getItem(int position) {
        return list.get(position);
    }

    public void insertItem(BaseViewModel newItem) {
        registerTypeInstance(newItem);

        list.add(newItem);
        notifyItemInserted(getItemCount() - 1);
    }
}
