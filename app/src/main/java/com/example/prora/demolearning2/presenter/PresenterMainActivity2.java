package com.example.prora.demolearning2.presenter;

import com.example.prora.demolearning2.adapter.List2Line;
import com.example.prora.demolearning2.interfaceMVP.MVP_Main_Activity;
import com.example.prora.demolearning2.interfaceMVP.MVP_Main_Activity2;
import com.example.prora.demolearning2.model.ModelMainActivity;
import com.example.prora.demolearning2.model.ModelMainActivity2;

import java.util.ArrayList;

public class PresenterMainActivity2 implements MVP_Main_Activity2.RequiredPresenterOps, MVP_Main_Activity2
		.ProvidedPresenterOps{

	private MVP_Main_Activity2.RequiredViewOps mView;
	private MVP_Main_Activity2.ProvidedModelOps mModel;

	public PresenterMainActivity2() {
		mModel = new ModelMainActivity2();
	}

	@Override
	public void setView(MVP_Main_Activity2.RequiredViewOps requiredViewOps) {
		this.mView = requiredViewOps;
	}

	@Override
	public void setModel(ModelMainActivity2 mainModel) {
		this.mModel = mainModel;
	}

	@Override
	public ArrayList<List2Line> getListItemsAction() {
		return mModel.getListItemsAction();
	}

	@Override
	public void clickAction(int possition) {
		mModel.clickAction(possition);
	}

}
