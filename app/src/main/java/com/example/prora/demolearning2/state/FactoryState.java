package com.example.prora.demolearning2.state;


import android.content.Context;

public class FactoryState {

	private static FactoryState instance;
	Context context;

	private FactoryState() {
	}

	public static FactoryState getInstance(Context context){
		if (instance == null) {
			instance = new FactoryState();
		}
		instance.setContext(context);
		return instance;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public IState getState(String type){
		IState strategy = NullState.getInstance();
		if (type.equals("Contacts")){
		strategy = ContactState.getInstance(context);
		}else if (type.equals("Sms")){
		strategy = SmsState.getInstance(context);
		}
		return strategy;
		}
	}
