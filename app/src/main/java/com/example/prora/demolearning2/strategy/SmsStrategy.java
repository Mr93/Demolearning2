package com.example.prora.demolearning2.strategy;

import android.util.Log;

import java.io.Serializable;

public class SmsStrategy implements IStrategy, Serializable {

	@Override
	public String getName() {
		return "SMS";
	}

	@Override
	public void backup() {
		Log.d("datnd", "sms:  backup");
	}

	@Override
	public void view() {
		Log.d("datnd", "sms : view");
	}
}