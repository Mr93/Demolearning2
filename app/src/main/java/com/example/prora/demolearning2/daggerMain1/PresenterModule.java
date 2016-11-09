package com.example.prora.demolearning2.daggerMain1;

import com.example.prora.demolearning2.interfaceMVP.MVP_Main_Activity;
import com.example.prora.demolearning2.model.ModelMainActivity;
import com.example.prora.demolearning2.presenter.PresenterMainActivity;
import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

	MVP_Main_Activity.ProvidedPresenterOps providedPresenterOps;

	public PresenterModule(MVP_Main_Activity.RequiredViewOps requiredViewOps){
		this.providedPresenterOps = new PresenterMainActivity();
		providedPresenterOps.setView(requiredViewOps);
		providedPresenterOps.setModel(new ModelMainActivity());
	}

	@Provides
	public MVP_Main_Activity.ProvidedPresenterOps getProvidedPresenterOps(){
		return providedPresenterOps;
	}
}
