package com.example.prora.demolearning2.state;

import android.content.Context;
import android.util.Log;

import com.example.prora.demolearning2.BackupSms;

public class SmsState implements IState {

    Context context;

    public SmsState(Context context){
        this.context = context;
    }

	@Override
	public String getName() {
		return "SMS";
	}

	@Override
	public void backup() {
		Log.d("datnd", "sms:  backup");
        BackupSms.getInstance(context).backup();
	}

	@Override
	public void view() {
		Log.d("datnd", "sms : view");
	}
}