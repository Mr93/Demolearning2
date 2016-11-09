package com.example.prora.demolearning2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by RUBYCELL on 10/27/2016.
 */

public abstract class ViewTemplate {

	Context context;

	public final void view(String filePath) {
		simpleView(filePath);
	}

	abstract void simpleView(String filePath);

	public void setContext(Context context) {
		this.context = context;
	}


}
