package com.example.prora.demolearning2.state;

import android.content.Context;
import android.util.Log;

import com.example.prora.demolearning2.BackupSms;
import com.example.prora.demolearning2.ViewDefault;

public class SmsState extends IState {

    Context context;
	private static IState instance;

    private SmsState(Context context){
        this.context = context;
	    backupTemplate = BackupSms.getInstance();
	    viewTemplate = ViewDefault.getInstances(context);
    }

	public static IState getInstance(Context context) {
		if (instance == null) {
			instance = new SmsState(context);
		}
		return instance;
	}

	@Override
	public String getName() {
		return "SMS";
	}

	@Override
	public void backup() {
		Log.d("datnd", "sms:  backup");
		backupTemplate.backup(context);
	}

	@Override
	public void view() {
		Log.d("datnd", "sms : view");
		viewTemplate.view(backupTemplate.getFilePath());
	}
}