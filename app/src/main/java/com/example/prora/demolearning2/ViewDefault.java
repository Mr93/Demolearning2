package com.example.prora.demolearning2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by RUBYCELL on 10/27/2016.
 */

public class ViewDefault extends ViewTemplate {

	private static ViewDefault instances;
	private Context context;

	private ViewDefault() {

	}

	public static ViewDefault getInstances(Context context) {
		if (instances == null) {
			instances = new ViewDefault();
		}
		instances.setContext(context);
		return instances;
	}

	private void setContext(Context context) {
		this.context = context;
	}

	@Override
	void simpleView(String filePath) {
		Intent intent = new Intent(Intent.ACTION_EDIT);
		Uri uri = Uri.parse(filePath);
		intent.setDataAndType(uri, "text/plain");
		context.startActivity(intent);
	}
}
