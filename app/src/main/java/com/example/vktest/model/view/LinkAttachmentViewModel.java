package com.example.vktest.model.view;

import android.view.View;

import com.example.vktest.model.attachment.Link;
import com.example.vktest.ui.holder.BaseViewHolder;
import com.example.vktest.ui.holder.LinkAttachmentViewHolder;

public class LinkAttachmentViewModel extends BaseViewModel {

    private String mTitle;
    private String mUrl;

    public LinkAttachmentViewModel(Link link) {

        if (link.getTitle() == null || link.getTitle().equals("")) {
            if (link.getName() != null) {
                mTitle = link.getName();
            } else {
                mTitle = "Link";
            }
        } else {
            mTitle = link.getTitle();
        }

        mUrl = link.getUrl();
    }


    @Override
    public LayoutTypes getType() {
        return LayoutTypes.AttachmentLink;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new LinkAttachmentViewHolder(view);
    }


    public String getTitle() {
        return mTitle;
    }

    public String getUrl() {
        return mUrl;
    }
}
