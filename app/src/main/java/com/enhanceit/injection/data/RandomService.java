package com.enhanceit.injection.data;

import com.enhanceit.injection.model.RandomUser;
import com.enhanceit.injection.network.NetworkManager;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class RandomService {

    private RandomAPI randomAPI;

    public RandomService() {
        NetworkManager networkManager = new NetworkManager();
        Retrofit retrofit = networkManager.provideRetrofitClient(RandomAPI.BASE_URL);
        randomAPI = retrofit.create(RandomAPI.class);
    }

    public void getRandomUser(final RandomUserCallback callback) {
        randomAPI.getRandomUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<RandomUser>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(RandomUser randomUser) {
                        callback.onRandomUser(randomUser);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e.getMessage());
                    }
                });
    }

    interface RandomUserCallback {
        void onRandomUser(RandomUser user);
        void onError(String error);
    }

    // RandomService.Callback
}
