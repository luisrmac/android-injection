package com.enhanceit.injection.data;

import com.enhanceit.injection.di.Injector;
import com.enhanceit.injection.model.RandomUser;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RandomService {

    private RandomAPI randomAPI;

    public RandomService(RandomAPI randomAPI) {
        this.randomAPI = randomAPI;
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

    public interface RandomUserCallback {
        void onRandomUser(RandomUser user);
        void onError(String error);
    }

    // RandomService.Callback
}
