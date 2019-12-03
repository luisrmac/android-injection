package com.enhanceit.injection.di;

import com.enhanceit.injection.MainActivity;
import com.enhanceit.injection.network.NetworkModule;
import com.enhanceit.injection.network.RadomAPIModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class, RadomAPIModule.class})
public interface ActivityComponent {

    MainActivity inject(MainActivity activity);

}
