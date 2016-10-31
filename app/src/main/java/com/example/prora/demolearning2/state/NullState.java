package com.example.prora.demolearning2.state;


public class NullState extends IState {

	private static IState instance;

	private NullState() {
	}

	public static IState getInstance() {
		if (instance == null) {
			instance = new NullState();
		}
		return instance;
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
