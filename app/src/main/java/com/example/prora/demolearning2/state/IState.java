package com.example.prora.demolearning2.state;

import android.content.Context;

import com.example.prora.demolearning2.BackupTemplate;
import com.example.prora.demolearning2.ViewTemplate;

public abstract class IState {
	Context context;
	BackupTemplate backupTemplate;
	ViewTemplate viewTemplate;
	public abstract String getName();
	public abstract void backup();
	public abstract void view();
	public void setContext(Context context) {
		this.context = context;
		viewTemplate.setContext(context);
	}

}
