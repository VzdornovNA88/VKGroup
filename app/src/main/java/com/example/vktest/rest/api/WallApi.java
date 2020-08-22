package com.example.vktest.rest.api;

import com.example.vktest.model.CommentItem;
import com.example.vktest.rest.model.response.BaseItemResponse;
import com.example.vktest.rest.model.response.Full;
import com.example.vktest.rest.model.response.GetWallByIdResponse;
import com.example.vktest.rest.model.response.GetWallResponse;
import com.example.vktest.rest.model.response.ItemsWithSendersResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface WallApi {

    @GET(ApiMethods.WALL_GET)
    Observable<GetWallResponse> get(@QueryMap Map<String, String> map);

    @GET(ApiMethods.WALL_GET_BY_ID)
    Observable<GetWallByIdResponse> getById(@QueryMap Map<String, String> map);

    @GET(ApiMethods.WALL_GET_COMMENTS)
    Observable<Full<ItemsWithSendersResponse<CommentItem>>> getComments(@QueryMap Map<String, String> map);
}
