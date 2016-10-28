package com.example.prora.demolearning2.state;

import android.content.Context;
import android.util.Log;

import com.example.prora.demolearning2.BackupContact;
import com.example.prora.demolearning2.ViewDefault;


public class ContactState extends IState {

	Context context;
	private static IState instance;
	private ContactState(Context context){
		this.context = context;
		backupTemplate = BackupContact.getInstance(context);
		viewTemplate = ViewDefault.getInstances(context);
	}

	public static IState getInstance(Context context) {
		if (instance == null) {
			instance = new ContactState(context);
		}
		return instance;
	}

	@Override
	public String getName() {
		return "CONTACT";
	}

	@Override
	public void backup() {
		Log.d("datnd", "backup: contact backup");
		backupTemplate.backup();
	}

	@Override
	public void view() {
		Log.d("datnd", "backup: contact view");
		viewTemplate.view(backupTemplate.getFilePath());
	}
}
