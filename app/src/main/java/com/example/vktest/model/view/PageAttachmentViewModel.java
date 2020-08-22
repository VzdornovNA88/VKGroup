package com.example.vktest.model.view;

import android.view.View;

import com.example.vktest.model.attachment.Page;
import com.example.vktest.ui.holder.BaseViewHolder;
import com.example.vktest.ui.holder.PageAttachmentHolder;

public class PageAttachmentViewModel extends BaseViewModel {

    private String mTitle;
    private String mUrl;

    public PageAttachmentViewModel(Page page) {
        mUrl = page.getUrl();
        mTitle = page.getTitle();
    }

    public String getTitle() {
        return mTitle;
    }


    @Override
    public LayoutTypes getType() {
        return LayoutTypes.AttachmentPage;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new PageAttachmentHolder(view);
    }


    public String getmUrl() {
        return mUrl;
    }
}
