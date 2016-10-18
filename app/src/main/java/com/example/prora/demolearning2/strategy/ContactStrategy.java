package com.example.prora.demolearning2.strategy;

import android.util.Log;

import java.io.Serializable;


public class ContactStrategy implements IStrategy, Serializable {

	@Override
	public String getName() {
		return "CONTACT";
	}

	@Override
	public void backup() {
		Log.d("datnd", "backup: contact backup");
	}

	@Override
	public void view() {
		Log.d("datnd", "backup: contact view");
	}
}
