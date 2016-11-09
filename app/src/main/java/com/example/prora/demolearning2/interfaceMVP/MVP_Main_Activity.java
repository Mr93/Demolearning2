package com.example.prora.demolearning2.interfaceMVP;

import android.content.Context;
import com.example.prora.demolearning2.adapter.List2Line;
import com.example.prora.demolearning2.model.ModelMainActivity;

import java.util.ArrayList;

public interface MVP_Main_Activity {

    interface RequiredViewOps {
	    Context getContext();
    }

    interface ProvidedPresenterOps {
	    void setView(MVP_Main_Activity.RequiredViewOps requiredViewOps);
	    void setModel(ModelMainActivity mainModel);
	    ArrayList<List2Line> getListItems();
	    void clickItem(String type);
    }

    interface RequiredPresenterOps {
	    Context getContext();
    }

    interface ProvidedModelOps {
	    ArrayList<List2Line> getListItems();
	    void clickItem(String type, MVP_Main_Activity.RequiredPresenterOps requiredPresenterOps);
    }

}
