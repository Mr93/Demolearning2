package com.example.prora.demolearning2;


import com.example.prora.demolearning2.interfaceMVP.MVP_Main_Activity;

import java.util.HashMap;

public class StateMainPresenter {

	private static StateMainPresenter instance;
	private static String currentKey;
	private HashMap<String, MVP_Main_Activity.ProvidedPresenterOps> listProvidedPresenterOpsHashMap = new HashMap<>();
	private StateMainPresenter(){
	}
	public static StateMainPresenter getInstance(String key){
		if (instance == null){
			instance = new StateMainPresenter();
		}
		if (!key.equals(currentKey)){
			currentKey = key;
		}
		return instance;
	}

	public MVP_Main_Activity.ProvidedPresenterOps getProvidedPresenterSaved(){
		MVP_Main_Activity.ProvidedPresenterOps providedPresenterOps = listProvidedPresenterOpsHashMap.get(currentKey);
		return providedPresenterOps;
	}

	public void setProvidedPresenterOps(MVP_Main_Activity.ProvidedPresenterOps providedPresenterOps) {
		listProvidedPresenterOpsHashMap.put(currentKey, providedPresenterOps);
	}


}
