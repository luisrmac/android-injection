package com.enhanceit.injection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.enhanceit.injection.data.RandomService;
import com.enhanceit.injection.di.DaggerActivityComponent;
import com.enhanceit.injection.model.RandomUser;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements RandomService.RandomUserCallback {

    private static final String TAG = "MainActivity_TAG";

    @Inject
    RandomService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaggerActivityComponent.builder()
                .build()
                .inject(this);
        service.getRandomUser(this);
    }


    @Override
    public void onRandomUser(RandomUser user) {
        Log.d(TAG, "onRandomUser: " + user);
    }

    @Override
    public void onError(String error) {
        Log.e(TAG, "onError: "+ error);
    }

}
