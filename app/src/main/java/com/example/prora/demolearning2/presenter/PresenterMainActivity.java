package com.example.prora.demolearning2.presenter;

import android.content.Context;
import com.example.prora.demolearning2.adapter.List2Line;
import com.example.prora.demolearning2.interfaceMVP.MVP_Main_Activity;
import com.example.prora.demolearning2.model.ModelMainActivity;

import java.util.ArrayList;

/**
 * Created by DatNhung on 11/7/2016.
 */

public class PresenterMainActivity implements MVP_Main_Activity.RequiredPresenterOps, MVP_Main_Activity.ProvidedPresenterOps{

	private MVP_Main_Activity.RequiredViewOps mView;
	private MVP_Main_Activity.ProvidedModelOps mModel;

	public PresenterMainActivity() {
		mModel = new ModelMainActivity();
	}

	@Override
	public void setView(MVP_Main_Activity.RequiredViewOps requiredViewOps) {
		this.mView = requiredViewOps;
	}

	@Override
	public void setModel(ModelMainActivity mainModel) {
		this.mModel = mainModel;
	}

	@Override
	public ArrayList<List2Line> getListItems() {
		return mModel.getListItems();
	}

	@Override
	public void clickItem(String type) {
		mModel.clickItem(type, this);
	}

	@Override
	public Context getContext() {
		return mView.getContext();
	}
}
