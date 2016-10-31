package com.example.prora.demolearning2;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by RUBYCELL on 10/17/2016.
 */

public abstract class BackupTemplate {

	private static final String TAG = BackupTemplate.class.getName();
	String fileName = "";
	String filePath = Environment.getExternalStorageDirectory().getAbsolutePath();
	Context context;

	public BackupTemplate() {

	}

	public final void backup(Context context){
		this.context = context;
		new BackupAsync().execute();
	}

	abstract Cursor getData();

	abstract byte[] writeDataToFile(Cursor cursor);

	private void deleteOldFile() {
		File dFile = new File(filePath + "/" + fileName);
		if (dFile.exists()) {
			dFile.delete();
		}
	}

	private void writeToFile(byte[] bytes){
		FileOutputStream fileOutputStream;
		String path = filePath + "/" + fileName;
		try {
			fileOutputStream = new FileOutputStream(new File(path), true);
			fileOutputStream.write(bytes);
			fileOutputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getFilePath() {
		return filePath + "/" + fileName;
	}

	private class BackupAsync extends AsyncTask<Void, Integer, String>{
		Cursor cursor;
		boolean cancel = false;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			cursor = getData();
			if(cursor!=null){
				cursor.moveToFirst();
				showDialogBackup();
			}else {
				cancel = true;
			}
		}

		private void showDialogBackup() {
			MaterialBuilderFacade materialBuilderFacade = new MaterialBuilderFacade(context);
			materialBuilderFacade.title("Backup");
			materialBuilderFacade.content("Loading");
			materialBuilderFacade.progress(false, cursor.getCount(), true);
			materialBuilderFacade.autoDismiss(false);
			materialBuilderFacade.canceledOnTouchOutside(false);
			materialBuilderFacade.cancelable(false);
			MaterialFacade.getInstance().showDialog(materialBuilderFacade);
		}

		@Override
		protected void onPostExecute(String value) {
			super.onPostExecute(value);
			MaterialFacade.getInstance().finishDeterminateProgressDialogs(value);
			Log.d(TAG, "onPostExecute: done");
		}

		@Override
		protected String doInBackground(Void... params) {
			int count = 0;
			if(!cancel){
				deleteOldFile();
				byte[] data;
				for (int i = 0; i < cursor.getCount(); i++){
					data = writeDataToFile(cursor);
					cursor.moveToNext();
					writeToFile(data);
					count++;
					publishProgress(count);
				}
			}
			return "Finish";
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
			MaterialFacade.getInstance().updateDeterminateProgressDialogs(values[0]);
		}
	}
}
