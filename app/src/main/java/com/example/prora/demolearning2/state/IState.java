package com.example.prora.demolearning2.state;

import com.example.prora.demolearning2.BackupTemplate;
import com.example.prora.demolearning2.ViewTemplate;

public abstract class IState {
	BackupTemplate backupTemplate;
	ViewTemplate viewTemplate;
	public abstract String getName();
	public abstract void backup();
	public abstract void view();
}
