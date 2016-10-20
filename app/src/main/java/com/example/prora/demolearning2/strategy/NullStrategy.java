package com.example.prora.demolearning2.strategy;

import android.content.Context;
import android.util.Log;
import com.example.prora.demolearning2.BackupContact;


public class NullStrategy implements IStrategy {

	public NullStrategy(){
	}

	@Override
	public String getName() {
		return "";
	}

	@Override
	public void backup() {
	}

	@Override
	public void view() {
	}
}
