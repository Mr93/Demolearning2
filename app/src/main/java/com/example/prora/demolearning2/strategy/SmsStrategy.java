package com.example.prora.demolearning2.strategy;

import android.content.Context;
import android.util.Log;

import com.example.prora.demolearning2.BackupContact;
import com.example.prora.demolearning2.BackupSms;

import java.io.Serializable;

public class SmsStrategy implements IStrategy {

    Context context;

    public SmsStrategy(Context context){
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