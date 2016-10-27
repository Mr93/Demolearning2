package com.example.prora.demolearning2.state;

import android.content.Context;
import android.util.Log;

import com.example.prora.demolearning2.BackupContact;
import com.example.prora.demolearning2.ViewDefault;


public class ContactState extends IState {

	Context context;

	public ContactState(Context context){
		this.context = context;
		backupTemplate = BackupContact.getInstance(context);
		viewTemplate = ViewDefault.getInstances(context);
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
