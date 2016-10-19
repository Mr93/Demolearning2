package com.example.prora.demolearning2;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.util.Log;

import com.afollestad.materialdialogs.MaterialDialog;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by RUBYCELL on 10/17/2016.
 */

public class BackupContact extends BackupTemplate {

	ArrayList<String> lCheck;
	MaterialDialog progressDialog;
	Cursor phones;
	Context context;

	//http://stackoverflow.com/questions/38872165/how-to-create-a-folder-for-backup-contacts-in-android
	private static final String TAG = BackupContact.class.getName();
	private static BackupContact instance;
	private BackupContact(Context context) {
		super();
		fileName = "Contacts.vcf";
	}

	public static BackupContact getInstance(Context context){
		if (instance == null){
			instance = new BackupContact(context);
		}
		instance.setContext(context);
		return instance;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	@Override
	protected void getData() {
		phones = context.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
		if(phones != null){
			phones.moveToFirst();
			Log.d(TAG, "getData: " + phones.getCount());
		}
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
	protected void writeDataToFile() {
		if(phones!=null){
			progressDialog = MaterialFacade.getInstance().getDeterminateProgressDialogs(context, phones.getCount(), "Backup contact");
			new BackupAsync().execute();
		}
	}

	class BackupAsync extends AsyncTask<Integer,Integer,String> {

		@Override
		protected String doInBackground(Integer... params) {
			int count = 0;
			AssetFileDescriptor fd;
			String path = filePath + "/" + fileName;
			for (int i = 0; i < phones.getCount(); i++) {
				String lookupKey = checkDuplicateContact();
				if (lookupKey == null) continue;
				Uri uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_VCARD_URI, lookupKey);
				try {
					fd = context.getContentResolver().openAssetFileDescriptor(uri, "r");
					writeData(path, fd);
					phones.moveToNext();
					count++;
					publishProgress(count);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			return "Backup completed";
		}

		private void writeData(String path, AssetFileDescriptor fd) throws IOException {
			FileInputStream fis = fd.createInputStream();
			byte[] buf = new byte[(int) fd.getDeclaredLength()];
			fis.read(buf);
			String VCard = new String(buf);
			FileOutputStream mFileOutputStream = new FileOutputStream(path, true);
			mFileOutputStream.write(VCard.toString().getBytes());
			mFileOutputStream.close();
			fis.close();
		}

		@Nullable
		private String checkDuplicateContact() {
			String lookupKey = phones.getString(phones.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY));
			lookupKey = lookupKey.trim();
			if (lCheck == null) {
				lCheck = new ArrayList<>();
				lCheck.add(lookupKey);
			} else {
				if (isDuplicate(lookupKey)) {
					phones.moveToNext();
					return null;
				} else {
					lCheck.add(lookupKey);
				}
			}
			return lookupKey;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(String s) {
			super.onPostExecute(s);
			progressDialog.setContent(s);
			progressDialog.setCancelable(true);
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
			progressDialog.setProgress(values[0]);
		}
	}

}
