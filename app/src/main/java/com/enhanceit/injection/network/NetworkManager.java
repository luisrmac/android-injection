package com.enhanceit.injection.network;

import com.enhanceit.injection.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkManager {

    public NetworkManager() {
        // constructor
    }

    public Retrofit provideRetrofitClient(String url) {
        return new Retrofit.Builder()
                .baseUrl(url)
                .addCallAdapterFactory(provideRxJavaCallAdapter())
                .addConverterFactory(provideConverterFactory())
                .client(provideHttpClient())
                .build();
    }

    private OkHttpClient provideHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(provideHttpInterceptor())
                .build();
    }

    private HttpLoggingInterceptor provideHttpInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

        if(BuildConfig.DEBUG) {
            interceptor.level(HttpLoggingInterceptor.Level.NONE);
        } else {
            interceptor.level(HttpLoggingInterceptor.Level.NONE);
        }

        return  interceptor;
    }

    private GsonConverterFactory provideConverterFactory() {
        return GsonConverterFactory.create();
    }

    private RxJava2CallAdapterFactory provideRxJavaCallAdapter() {
        return RxJava2CallAdapterFactory.create();
    }

}
