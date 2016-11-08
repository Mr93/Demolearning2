package com.example.prora.demolearning2.interfaceMVP;

import com.example.prora.demolearning2.adapter.List2Line;
import com.example.prora.demolearning2.model.ModelMainActivity2;

import java.util.ArrayList;

public interface MVP_Main_Activity2 {

    interface RequiredViewOps {
    }

	interface RequiredPresenterOps {
	}

    interface ProvidedPresenterOps {
	    void setView(MVP_Main_Activity2.RequiredViewOps requiredViewOps);
	    void setModel(ModelMainActivity2 mainModel);
	    ArrayList<List2Line> getListItemsAction();
	    void clickAction(int possition);
    }

    interface ProvidedModelOps {
	    ArrayList<List2Line> getListItemsAction();
	    void clickAction(int possition);
    }

}
