package com.example.vktest.rest.api;

import com.example.vktest.model.CommentItem;
import com.example.vktest.model.Topic;
import com.example.vktest.rest.model.response.BaseItemResponse;
import com.example.vktest.rest.model.response.Full;
import com.example.vktest.rest.model.response.ItemsWithSendersResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface BoardApi {
    @GET(ApiMethods.BOARD_GET_TOPICS)
    Observable<Full<BaseItemResponse<Topic>>> getTopics(@QueryMap Map<String, String> map);

    @GET(ApiMethods.BOARD_GET_COMMENTS)
    Observable<Full<ItemsWithSendersResponse<CommentItem>>> getComments(@QueryMap Map<String, String> map);
}
