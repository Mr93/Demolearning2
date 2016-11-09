package com.example.prora.demolearning2.model;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.prora.demolearning2.MaterialBuilderFacade;
import com.example.prora.demolearning2.MaterialFacade;
import com.example.prora.demolearning2.R;
import com.example.prora.demolearning2.adapter.List2Line;
import com.example.prora.demolearning2.interfaceMVP.MVP_Main_Activity;
import com.example.prora.demolearning2.interfaceMVP.MVP_Main_Activity2;
import com.example.prora.demolearning2.state.FactoryState;
import com.example.prora.demolearning2.state.IState;
import com.example.prora.demolearning2.state.NullState;

import java.util.ArrayList;

/**
 * Created by DatNhung on 11/7/2016.
 */

public class ModelMainActivity2 implements MVP_Main_Activity2.ProvidedModelOps {

	MVP_Main_Activity2.RequiredPresenterOps presenterOps;

	IState state = NullState.getInstance();
	String[] label = {"Backup", "View"};
	String[] summary = {"Backup", "View"};
	int[] idImageview = {
			R.drawable.ic_backup_black_24dp,
			R.drawable.ic_visibility_black_24dp
	};

	public ModelMainActivity2(MVP_Main_Activity2.RequiredPresenterOps requiredPresenterOps) {
		this.presenterOps = requiredPresenterOps;
	}


	@Override
	public ArrayList<List2Line> getListItemsAction() {
		ArrayList<List2Line> list2Lines = new ArrayList<>();
		for (int i = 0; i < label.length; i++) {
			list2Lines.add(new List2Line(label[i], summary[i], idImageview[i]));
		}
		return list2Lines;
	}

	@Override
	public void doAction(int position) {
		switch (position){
			case 0:
				state.backup();
				break;
			case 1:
				state.view();
				break;
			default:
				break;
		}
	}

	@Override
	public void setState(String type) {
		state = FactoryState.getInstance(presenterOps.getContext()).getState(type);
	}

	@Override
	public String getStateName() {
		return state.getName();
	}

	@Override
	public void showDialogChangeState() {
		MaterialBuilderFacade materialBuilderFacade = new MaterialBuilderFacade(presenterOps.getContext());
		materialBuilderFacade.title(R.string.select);
		materialBuilderFacade.items(R.array.list_state);
		materialBuilderFacade.itemsCallback(new MaterialDialog.ListCallback() {
			@Override
			public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
				if (text != null && !text.toString().equalsIgnoreCase("")) {
					presenterOps.notifyViewChangeState(text.toString());
				}
			}
		});
		MaterialFacade.getInstance().showDialog(materialBuilderFacade);
	}
}
