package com.enhanceit.injection.network;

import com.enhanceit.injection.BuildConfig;
import com.enhanceit.injection.data.RandomAPI;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    Retrofit provideRetrofitClient(OkHttpClient client,
                                   RxJava2CallAdapterFactory callFactory,
                                   GsonConverterFactory converterFactory) {
        return new Retrofit.Builder()
                .baseUrl(RandomAPI.BASE_URL)
                .addCallAdapterFactory(callFactory)
                .addConverterFactory(converterFactory)
                .client(client)
                .build();
    }

    @Provides
    @Singleton
    OkHttpClient provideHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(provideHttpInterceptor())
                .build();
    }

    @Provides
    @Singleton
    HttpLoggingInterceptor provideHttpInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

        if (BuildConfig.DEBUG) {
            interceptor.level(HttpLoggingInterceptor.Level.BODY);
        } else {
            interceptor.level(HttpLoggingInterceptor.Level.NONE);
        }

        return interceptor;
    }

    @Provides
    @Singleton
    GsonConverterFactory provideConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    RxJava2CallAdapterFactory provideRxJavaCallAdapter() {
        return RxJava2CallAdapterFactory.create();
    }

}
