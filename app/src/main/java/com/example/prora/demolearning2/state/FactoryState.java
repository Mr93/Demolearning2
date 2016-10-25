package com.example.prora.demolearning2.state;


import android.content.Context;

public class FactoryState {

	private static FactoryState instance;
	Context context;

	private FactoryState(Context context) {
		this.context = context;
	}

	public static FactoryState getInstance(Context context){
		if (instance == null) {
			instance = new FactoryState(context);
		}
		instance.setContext(context);
		return instance;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public IState getState(String type){
		IState strategy = new NullState();
		if (type.equals("Contacts")){
			strategy = new ContactState(context);
		}else if (type.equals("Sms")){
			strategy = new SmsState(context);
		}
		return strategy;
	}
}
