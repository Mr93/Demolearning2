package com.example.prora.demolearning2.daggerMain1;

import com.example.prora.demolearning2.MainActivity;
import dagger.Component;

@Component(modules = {PresenterModule.class})
public interface PresenterComponent {
	void inject(MainActivity activity);
}
