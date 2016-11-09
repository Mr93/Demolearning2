package com.example.prora.demolearning2.state;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.example.prora.demolearning2.BackupContact;
import com.example.prora.demolearning2.ViewDefault;


public class ContactState extends IState {

	private static IState instance;
	private ContactState(){
		backupTemplate = BackupContact.getInstance();
		viewTemplate = ViewDefault.getInstances();
	}

	public static IState getInstance(Context context) {
		if (instance == null) {
			instance = new ContactState();
		}
		instance.setContext(context);
		return instance;
	}

	@Override
	public String getName() {
		return "CONTACT";
	}

	@Override
	public void backup() {
		Log.d("datnd", "backup: contact backup");
		backupTemplate.backup(context);
	}

	@Override
	public void view() {
		Log.d("datnd", "backup: contact view");
		viewTemplate.view(Environment.getExternalStorageDirectory().getAbsolutePath() +"/" + "Contacts.vcf");
	}
}
