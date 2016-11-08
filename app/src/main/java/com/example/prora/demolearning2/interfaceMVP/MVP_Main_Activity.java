package com.example.prora.demolearning2.interfaceMVP;

import com.example.prora.demolearning2.adapter.List2Line;
import com.example.prora.demolearning2.model.ModelMainActivity;

import java.util.ArrayList;

public interface MVP_Main_Activity {

    interface RequiredViewOps {
    }

    interface ProvidedPresenterOps {
	    void setView(MVP_Main_Activity.RequiredViewOps requiredViewOps);
	    void setModel(ModelMainActivity mainModel);
	    ArrayList<List2Line> getListItems();
    }

    interface RequiredPresenterOps {
    }

    interface ProvidedModelOps {
	    ArrayList<List2Line> getListItems();
    }

}
