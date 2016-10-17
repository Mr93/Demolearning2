package com.example.prora.demolearning2;

import android.annotation.SuppressLint;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.CursorAdapter;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by RUBYCELL on 10/17/2016.
 */

public class BackupContact extends BackupTemplate {

	//http://stackoverflow.com/questions/38872165/how-to-create-a-folder-for-backup-contacts-in-android
	private static final String TAG = BackupContact.class.getName();
	Cursor cursor;

	public BackupContact(Context context) {
		super(context);
	}

	@Override
	protected void getData() {
		cursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();
			for (int i = 0; i < cursor.getCount(); i++) {
				Log.d(TAG, "getData: " + cursor);
				for(int j = 0; j < cursor.getColumnCount(); j++){
					Log.d(TAG, "getData: ===========================");
					Log.d(TAG, "getData: " + cursor.getColumnName(j));
					Log.d(TAG, "getData: " + cursor.getString(j));
				}
				cursor.moveToNext();
			}

		} else {
			Log.d("TAG", "No Contacts in Your Phone");
		}
	}

	@Override
	protected void writeDataToFile() {

	}
}
