package com.example.prora.demolearning2.daggerMain2;

import android.content.Context;

import com.example.prora.demolearning2.interfaceMVP.MVP_Main_Activity2;
import com.example.prora.demolearning2.model.ModelMainActivity2;
import com.example.prora.demolearning2.presenter.PresenterMainActivity2;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by prora on 11/9/2016.
 */
@Module
public class PresenterModuleMain2 {
	MVP_Main_Activity2.ProvidedPresenterOps providedPresenterOps;

	public PresenterModuleMain2(MVP_Main_Activity2.RequiredViewOps requiredViewOps) {
		this.providedPresenterOps =  new PresenterMainActivity2();
		providedPresenterOps.setView(requiredViewOps);
		providedPresenterOps.setModel(new ModelMainActivity2((PresenterMainActivity2)this.providedPresenterOps));
	}

	@Provides
	@Singleton
	MVP_Main_Activity2.ProvidedPresenterOps providedPresenterOps(){
		return this.providedPresenterOps;
	}
}
