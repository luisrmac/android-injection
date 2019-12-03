package com.enhanceit.injection.di;

import com.enhanceit.injection.data.RandomAPI;
import com.enhanceit.injection.data.RandomService;
import com.enhanceit.injection.network.NetworkManager;


// inversion of control
public class Injector {

    public static RandomService provideRandomService() {
        return new RandomService(provideRandomAPI());
    }

    private static RandomAPI provideRandomAPI() {
        return provideNetworkManager()
                .provideRetrofitClient(RandomAPI.BASE_URL)
                .create(RandomAPI.class);
    }

    private static NetworkManager provideNetworkManager() {
        return new NetworkManager();
    }

}
