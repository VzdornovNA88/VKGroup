package com.example.vktest.model.view;

import android.view.View;

import com.example.vktest.common.utils.Utils;
import com.example.vktest.model.attachment.Audio;
import com.example.vktest.ui.holder.AudioAttachmentHolder;
import com.example.vktest.ui.holder.BaseViewHolder;

public class AudioAttachmentViewModel extends BaseViewModel {

    private String mTitle;
    private String mArtist;

    private String mDuration;


    public AudioAttachmentViewModel(Audio audio) {
        if (audio.getTitle().equals("")) {
            mTitle = "Title";
        } else {
            mTitle = audio.getTitle();
        }

        if (audio.getArtist().equals("")) {
            mArtist = "Various Artist";
        } else {
            mArtist = audio.getArtist();
        }

        mDuration = Utils.parseDuration(audio.getDuration());
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.AttachmentAudio;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new AudioAttachmentHolder(view);
    }


    public String getTitle() {
        return mTitle;
    }

    public String getArtist() {
        return mArtist;
    }

    public String getDuration() {
        return mDuration;
    }
}
