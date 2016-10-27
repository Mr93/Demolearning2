package com.example.prora.demolearning2.state;

import com.example.prora.demolearning2.BackupTemplate;

public abstract class IState {
	BackupTemplate backupTemplate;
	public abstract String getName();
	public abstract void backup();
	public abstract void view();
}
