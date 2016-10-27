package com.example.prora.demolearning2.state;

import android.content.Context;
import android.util.Log;

import com.example.prora.demolearning2.BackupContact;


public class ContactState extends IState {

	Context context;

	public ContactState(Context context){
		this.context = context;
		backupTemplate = BackupContact.getInstance(context);
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
	}
}
