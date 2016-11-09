package com.example.prora.demolearning2.model;

import android.content.Context;
import android.content.Intent;
import com.example.prora.demolearning2.Main2Activity;
import com.example.prora.demolearning2.R;
import com.example.prora.demolearning2.adapter.List2Line;
import com.example.prora.demolearning2.interfaceMVP.MVP_Main_Activity;

import java.util.ArrayList;

/**
 * Created by DatNhung on 11/7/2016.
 */

public class ModelMainActivity implements MVP_Main_Activity.ProvidedModelOps {
	MVP_Main_Activity.RequiredPresenterOps requiredPresenterOps;
	ArrayList<List2Line> list2Lines;
	String[] label = {"Contacts","Sms"};
	String[] summary = {"Backup and restore your contacts",
			"Backup and restore your sms"};
	int[] idImageview = {
			R.drawable.ic_contact_mail_black_48dp,
			R.drawable.ic_message_text_black_48dp
	};

	public ModelMainActivity() {
	}

	@Override
	public ArrayList<List2Line> getListItems() {
		list2Lines = new ArrayList<>();
		for(int i = 0;i<label.length;i++){
			list2Lines.add(new List2Line(label[i],summary[i], idImageview[i]));
		}
		return list2Lines;
	}

	@Override
	public void clickItem(String type, MVP_Main_Activity.RequiredPresenterOps requiredPresenterOps) {
		this.requiredPresenterOps = requiredPresenterOps;
		Context context =requiredPresenterOps.getContext();
		Intent intent = new Intent(context, Main2Activity.class);
		intent.putExtra(context.getResources().getString(R.string.state), type);
		context.startActivity(intent);
	}
}
