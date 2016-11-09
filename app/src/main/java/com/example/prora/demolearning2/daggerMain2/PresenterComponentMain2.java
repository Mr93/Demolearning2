package com.example.prora.demolearning2.daggerMain2;

import com.example.prora.demolearning2.Main2Activity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by prora on 11/9/2016.
 */

@Singleton
@Component(modules = {PresenterModuleMain2.class})
public interface PresenterComponentMain2 {
	void inject(Main2Activity main2Activity);
}
