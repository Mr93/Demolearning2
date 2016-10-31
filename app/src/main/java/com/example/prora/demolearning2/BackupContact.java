package com.example.prora.demolearning2;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by RUBYCELL on 10/17/2016.
 */

public class BackupContact extends BackupTemplate {

	private ArrayList<String> lCheck;

	//http://stackoverflow.com/questions/38872165/how-to-create-a-folder-for-backup-contacts-in-android
	private static final String TAG = BackupContact.class.getName();
	private static BackupContact instance;

	private BackupContact() {
		super();
		fileName = "Contacts.vcf";
	}

	public static BackupContact getInstance(){
		if (instance == null){
			instance = new BackupContact();
		}
		return instance;
	}


	@Override
	protected Cursor getData() {
		Cursor cursor = context.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
		return cursor;
	}

	public boolean isDuplicate(String lkKey) {
		for (String s : lCheck) {
			if (s.equals(lkKey)) {
				return true;
			}
		}
		return false;
	}

	@Override
	protected byte[] writeDataToFile(Cursor cursor) {
		byte[] data = null;
		AssetFileDescriptor fd;
		String path = filePath + "/" + fileName;
		lCheck = new ArrayList<>();
		String lookupKey = checkDuplicateContact(cursor);
		if (lookupKey != null) {
			Uri uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_VCARD_URI, lookupKey);
			try {
				fd = context.getContentResolver().openAssetFileDescriptor(uri, "r");
				//writeData(path, fd);
				FileInputStream fis = fd.createInputStream();
				byte[] buf = new byte[(int) fd.getDeclaredLength()];
				fis.read(buf);
				String VCard = new String(buf);
				data = VCard.toString().getBytes();
				fis.close();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return data;
	}

	@Nullable
	private String checkDuplicateContact(Cursor cursor) {
		String lookupKey = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY));
		lookupKey = lookupKey.trim();
		if (isDuplicate(lookupKey)) {
			return null;
		} else {
			lCheck.add(lookupKey);
		}
		return lookupKey;
	}
}
