package com.example.prora.demolearning2.state;


public class NullState implements IState {

	public NullState(){
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
