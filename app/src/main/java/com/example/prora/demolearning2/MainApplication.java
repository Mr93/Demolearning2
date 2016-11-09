package com.example.prora.demolearning2;

import android.app.Application;
import com.example.prora.demolearning2.daggerMain1.DaggerPresenterComponent;
import com.example.prora.demolearning2.daggerMain1.PresenterComponent;
import com.example.prora.demolearning2.daggerMain1.PresenterModule;
import com.example.prora.demolearning2.interfaceMVP.MVP_Main_Activity;

/**
 * Created by DatNhung on 11/7/2016.
 */

public class MainApplication extends Application{

	private PresenterComponent presenterComponent;
	@Override
	public void onCreate() {
		super.onCreate();
	}

	public PresenterComponent getComponent(MVP_Main_Activity.RequiredViewOps requiredViewOps) {
		presenterComponent = DaggerPresenterComponent.builder()
				.presenterModule(new PresenterModule(requiredViewOps))
				.build();
		return presenterComponent;
	}
}
