package com.enhanceit.injection.network;

import com.enhanceit.injection.data.RandomAPI;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class RadomAPIModule {

    @Provides
    @Singleton
    RandomAPI providesRandomAPI(Retrofit retrofit) {
        return retrofit.create(RandomAPI.class);
    }

}
