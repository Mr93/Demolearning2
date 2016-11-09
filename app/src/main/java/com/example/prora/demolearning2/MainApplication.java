package com.example.prora.demolearning2;

import android.app.Application;
import com.example.prora.demolearning2.daggerMain1.DaggerPresenterComponent;
import com.example.prora.demolearning2.daggerMain1.PresenterComponent;
import com.example.prora.demolearning2.daggerMain1.PresenterModule;
import com.example.prora.demolearning2.daggerMain2.DaggerPresenterComponentMain2;
import com.example.prora.demolearning2.daggerMain2.PresenterComponentMain2;
import com.example.prora.demolearning2.daggerMain2.PresenterModuleMain2;
import com.example.prora.demolearning2.interfaceMVP.MVP_Main_Activity;
import com.example.prora.demolearning2.interfaceMVP.MVP_Main_Activity2;

/**
 * Created by DatNhung on 11/7/2016.
 */

public class MainApplication extends Application{

	private PresenterComponent presenterComponent;
	private PresenterComponentMain2 presenterComponentMain2;
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

	public PresenterComponentMain2 getPresenterComponentMain2(MVP_Main_Activity2.RequiredViewOps requiredViewOps){
		presenterComponentMain2 = DaggerPresenterComponentMain2.builder()
				.presenterModuleMain2(new PresenterModuleMain2(requiredViewOps))
				.build();
		return presenterComponentMain2;
	}
}
