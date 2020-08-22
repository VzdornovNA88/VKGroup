package com.example.vktest.model.view;

import android.view.View;

import com.example.vktest.model.attachment.Photo;
import com.example.vktest.model.view.BaseViewModel;
import com.example.vktest.ui.holder.BaseViewHolder;
import com.example.vktest.ui.holder.ImageAttachmentHolder;

public class ImageAttachmentViewModel extends BaseViewModel {

    private String mPhotoUrl;
    public boolean needClick = true;

    public ImageAttachmentViewModel(String url) {
        mPhotoUrl = url;

        needClick = false;
    }

    public ImageAttachmentViewModel(Photo photo) {
        mPhotoUrl = photo.getPhoto604();
    }


    @Override
    public LayoutTypes getType() {
        return LayoutTypes.AttachmentImage;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new ImageAttachmentHolder(view);
    }

    public String getPhotoUrl() {
        return mPhotoUrl;
    }
}
