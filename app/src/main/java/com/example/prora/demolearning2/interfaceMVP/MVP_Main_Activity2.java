package com.example.prora.demolearning2.interfaceMVP;

import android.content.Context;

import com.example.prora.demolearning2.adapter.List2Line;
import com.example.prora.demolearning2.model.ModelMainActivity2;
import com.example.prora.demolearning2.state.IState;

import java.util.ArrayList;

public interface MVP_Main_Activity2 {

    interface RequiredViewOps {
	    void changeState(String text);
	    Context getContext();
    }

	interface RequiredPresenterOps {
		void notifyViewChangeState(String type);
		Context getContext();
	}

    interface ProvidedPresenterOps {
	    void setView(MVP_Main_Activity2.RequiredViewOps requiredViewOps);
	    void setModel(ModelMainActivity2 mainModel);
	    ArrayList<List2Line> getListItemsAction();
	    void clickAction(int possition);
	    void setState(String type);
	    String getStateName();
	    void choseChangeStateMenuItem();
    }

    interface ProvidedModelOps {
	    ArrayList<List2Line> getListItemsAction();
	    void doAction(int position);
	    void setState(String type);
	    String getStateName();
	    void showDialogChangeState();
    }

}
