package com.example.vktest.rest.model.response;

import com.example.vktest.model.attachment.video.Video;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VideosResponse {
    public int count;
    @SerializedName("items")
    @Expose
    public List<Video> items;
}
