package com.example.prora.demolearning2.strategy;


import android.content.Context;

public class FactoryStrategy {

	private static FactoryStrategy instance;
	Context context;

	private FactoryStrategy(Context context) {
		this.context = context;
	}

	public static FactoryStrategy getInstance(Context context){
		if (instance == null) {
			instance = new FactoryStrategy(context);
		}
		instance.setContext(context);
		return instance;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public IStrategy getStrategy(String type){
		IStrategy strategy = new NullStrategy();
		if (type.equals("Contacts")){
			strategy = new ContactStrategy(context);
		}else if (type.equals("Sms")){
			strategy = new SmsStrategy(context);
		}
		return strategy;
	}
}
