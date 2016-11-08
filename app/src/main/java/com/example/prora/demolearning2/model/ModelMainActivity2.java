package com.example.prora.demolearning2.model;

import com.example.prora.demolearning2.R;
import com.example.prora.demolearning2.adapter.List2Line;
import com.example.prora.demolearning2.interfaceMVP.MVP_Main_Activity;
import com.example.prora.demolearning2.interfaceMVP.MVP_Main_Activity2;

import java.util.ArrayList;

/**
 * Created by DatNhung on 11/7/2016.
 */

public class ModelMainActivity2 implements MVP_Main_Activity2.ProvidedModelOps {
	String[] label = {"Backup", "View"};
	String[] summary = {"Backup", "View"};
	int[] idImageview = {
			R.drawable.ic_backup_black_24dp,
			R.drawable.ic_visibility_black_24dp
	};

	public ModelMainActivity2() {
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
	public void clickAction(int possition) {

	}
}
