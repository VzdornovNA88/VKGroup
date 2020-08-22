package com.example.vktest.rest.api;

import com.example.vktest.rest.model.response.Full;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface AccountApi {
    @GET(ApiMethods.ACCOUNT_REGISTER_DEVICE)
    Observable<Full<Integer>> registerDevice(@QueryMap Map<String, String> map);
}
