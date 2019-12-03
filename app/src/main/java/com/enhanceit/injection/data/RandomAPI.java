package com.enhanceit.injection.data;

import com.enhanceit.injection.model.RandomUser;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface RandomAPI {

    @GET("api")
    Single<RandomUser> getRandomUser();


    String BASE_URL = "https://randomuser.me/";
}
