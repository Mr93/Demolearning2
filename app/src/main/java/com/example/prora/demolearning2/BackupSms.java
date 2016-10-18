package com.example.prora.demolearning2;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.Telephony;
import android.util.Log;
import android.util.Xml;

import com.afollestad.materialdialogs.MaterialDialog;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by prora on 10/17/2016.
 */

public class BackupSms extends BackupTemplate {

	Cursor sCursor;
	MaterialDialog progressDialog;
	int maxSms;

	private static BackupSms instance;
	private BackupSms(Context context) {
		super(context);
		fileName = "Sms.xml";
	}
	public static BackupSms getInstance(Context context){
		if (instance == null){
			instance = new BackupSms(context);
		}
		return instance;
	}

	@Override
	void getData() {
		if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			sCursor = context.getContentResolver().query(Telephony.Sms.CONTENT_URI,null,null,null,null);
		}else{
			sCursor = context.getContentResolver().query(Uri.parse("content://sms"), null, null, null, null);
		}
	}

	@Override
	void writeDataToFile() {
		if(sCursor != null){
			sCursor.moveToFirst();
			maxSms = sCursor.getCount();
			progressDialog = MaterialFacade.getInstance().getDeterminateProgressDialogs(context, maxSms, "Backup Sms");
			new backupAsyncTask().execute();
		}
	}

	class backupAsyncTask extends AsyncTask<Integer,Integer,String> {

		@Override
		protected String doInBackground(Integer... params) {
			int count = 0;
			FileOutputStream fileOutputStream = null;
			XmlSerializer xmlSerializer;
			String path = filePath + "/" + fileName;
			try {
				fileOutputStream = new FileOutputStream(new File(path), true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				xmlSerializer = Xml.newSerializer();
				StringWriter writer = new StringWriter();
				xmlSerializer.setOutput(writer);
				xmlSerializer.startDocument("UTF-8", true);
				xmlSerializer.startTag(null, "smsData");
				xmlSerializer.attribute(null,"count",String.valueOf(sCursor.getCount()));
				for (int i = 0; i < sCursor.getCount(); i++) {
					if (sCursor.getString(sCursor.getColumnIndex("address")) != null) {
						writeData(xmlSerializer);
						count ++;
						publishProgress(count);
						sCursor.moveToNext();
					}
				}
				xmlSerializer.endTag(null, "smsData");
				xmlSerializer.endDocument();
				xmlSerializer.flush();
				String dataWrite = writer.toString();
				fileOutputStream.write(dataWrite.getBytes());
				fileOutputStream.close();
			}
			catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "Backup completed";
		}

		private void writeData(XmlSerializer xmlSerializer) throws IOException {
			xmlSerializer.startTag(null, "item");
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
				for(int j=0;j<sCursor.getColumnCount();j++){
					if(sCursor.getColumnName(j).equals(Telephony.Sms.ADDRESS)||
							sCursor.getColumnName(j).equals(Telephony.Sms._ID)||
							sCursor.getColumnName(j).equals(Telephony.Sms.THREAD_ID)||
							sCursor.getColumnName(j).equals(Telephony.Sms.BODY)||
							sCursor.getColumnName(j).equals(Telephony.Sms.DATE)||
							sCursor.getColumnName(j).equals(Telephony.Sms.TYPE)||
							sCursor.getColumnName(j).equals(Telephony.Sms.READ)){
						xmlSerializer.attribute(null,sCursor.getColumnName(j),(sCursor.getString(j)!=null)?sCursor.getString(j):"");
					}
				}
			} else {
				for(int j=0;j<sCursor.getColumnCount();j++){
					if(sCursor.getColumnName(j).equals("address")||
							sCursor.getColumnName(j).equals("date")||
							sCursor.getColumnName(j).equals("_id")||
							sCursor.getColumnName(j).equals("thread_id")||
							sCursor.getColumnName(j).equals("type")||
							sCursor.getColumnName(j).equals("read")){
						xmlSerializer.attribute(null,sCursor.getColumnName(j),(sCursor.getString(j)!=null)?sCursor.getString(j):"");
					}

					if(sCursor.getColumnName(j).equals("body")){
						try{
							xmlSerializer.attribute(null,sCursor.getColumnName(j),(sCursor.getString(j) != null) ? sCursor.getString(j):"");
						}catch (Exception e){
							xmlSerializer.attribute(null, "body","error" );
							Log.d("error", sCursor.getString(sCursor.getColumnIndex("body")));
						}
					}

				}
			}
			xmlSerializer.endTag(null, "item");
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
			Log.d("check_value"," " + values[0]);
			progressDialog.setProgress(values[0]);
		}
	}
}
