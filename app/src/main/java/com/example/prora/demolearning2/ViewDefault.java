package com.example.prora.demolearning2;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.util.Log;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import java.io.File;
import java.util.List;

/**
 * Created by RUBYCELL on 10/27/2016.
 */

public class ViewDefault extends ViewTemplate {

	private static final String TAG = ViewDefault.class.getName();
	private static ViewDefault instances;

	private ViewDefault() {

	}

	public static ViewDefault getInstances() {
		if (instances == null) {
			instances = new ViewDefault();
		}
		return instances;
	}


	@Override
	void simpleView(String filePath) {
		PackageManager pm = context.getPackageManager();
		Intent myIntent = new Intent(android.content.Intent.ACTION_VIEW);
		File file = new File(filePath);
		if (file.exists()) {
			String extension = fileExt(filePath);
			Log.d(TAG, "simpleView: " + extension);
			String mimetype = android.webkit.MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
			Log.d(TAG, "simpleView: " + mimetype);
			myIntent.setDataAndType(Uri.fromFile(file), mimetype);
			List<ResolveInfo> infos = pm.queryIntentActivities(myIntent, 0);
			if (!infos.isEmpty()) {
				context.startActivity(Intent.createChooser(myIntent, "Chooser"));
			} else {
				Toast.makeText(context, "No app can open this file", Toast.LENGTH_SHORT).show();
			}
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
			Log.d(TAG, "fileExt: " + url);
			Log.d(TAG, "fileExt: " + ext);
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
