package com.example.prora.demolearning2.strategy;

import android.content.Context;
import android.util.Log;

import com.example.prora.demolearning2.BackupContact;

import java.io.Serializable;


public class ContactStrategy implements IStrategy {

	Context context;

	public ContactStrategy(Context context){
		this.context = context;
	}

	@Override
	public String getName() {
		return "CONTACT";
	}

	@Override
	public void backup() {
		Log.d("datnd", "backup: contact backup");
		BackupContact.getInstance(context).backup();
	}

	@Override
	public void view() {
		Log.d("datnd", "backup: contact view");
	}
}
