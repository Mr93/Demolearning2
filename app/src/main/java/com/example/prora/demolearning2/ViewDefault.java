package com.example.prora.demolearning2;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import java.io.File;

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
		File file = new File(filePath);

		MimeTypeMap myMime = MimeTypeMap.getSingleton();
		Intent newIntent = new Intent(Intent.ACTION_VIEW);
		String mimeType = myMime.getMimeTypeFromExtension(fileExt(filePath).substring(1));
		newIntent.setDataAndType(Uri.fromFile(file),mimeType);
		newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		try {
			context.startActivity(newIntent);
		} catch (ActivityNotFoundException e) {
			Toast.makeText(context, "No handler for this type of file.", Toast.LENGTH_LONG).show();
		}
	}

	private String fileExt(String url) {
		if (url.indexOf("?") > -1) {
			url = url.substring(0, url.indexOf("?"));
		}
		if (url.lastIndexOf(".") == -1) {
			return null;
		} else {
			String ext = url.substring(url.lastIndexOf(".") + 1);
			if (ext.indexOf("%") > -1) {
				ext = ext.substring(0, ext.indexOf("%"));
			}
			if (ext.indexOf("/") > -1) {
				ext = ext.substring(0, ext.indexOf("/"));
			}
			return ext.toLowerCase();

		}
	}
}
