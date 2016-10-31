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

	private static BackupSms instance;
	private BackupSms() {
		super();
		fileName = "Sms.xml";
	}
	public static BackupSms getInstance(){
		if (instance == null){
			instance = new BackupSms();
		}
		return instance;
	}

	@Override
	Cursor getData() {
		Cursor cursor;
		if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			cursor = context.getContentResolver().query(Telephony.Sms.CONTENT_URI,null,null,null,null);
		}else{
			cursor = context.getContentResolver().query(Uri.parse("content://sms"), null, null, null, null);
		}
		return cursor;
	}

	@Override
	byte[] writeDataToFile(Cursor cursor) {
		XmlSerializer xmlSerializer;
		String dataWrite = "";
		try {
			xmlSerializer = Xml.newSerializer();
			StringWriter writer = new StringWriter();
			xmlSerializer.setOutput(writer);
			if(cursor.isFirst()){
				xmlSerializer.startDocument("UTF-8", true);

			}
			if (cursor.getString(cursor.getColumnIndex("address")) != null) {
				writeData(xmlSerializer, cursor);
			}
			if(cursor.isLast()){
				xmlSerializer.endDocument();
			}
			xmlSerializer.flush();
			dataWrite = writer.toString();
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
		return dataWrite.getBytes();
	}

	private void writeData(XmlSerializer xmlSerializer, Cursor cursor) throws IOException {
		xmlSerializer.startTag(null, "item");
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			for(int j=0;j<cursor.getColumnCount();j++){
				if(cursor.getColumnName(j).equals(Telephony.Sms.ADDRESS)||
						cursor.getColumnName(j).equals(Telephony.Sms._ID)||
						cursor.getColumnName(j).equals(Telephony.Sms.THREAD_ID)||
						cursor.getColumnName(j).equals(Telephony.Sms.BODY)||
						cursor.getColumnName(j).equals(Telephony.Sms.DATE)||
						cursor.getColumnName(j).equals(Telephony.Sms.TYPE)||
						cursor.getColumnName(j).equals(Telephony.Sms.READ)){
					xmlSerializer.attribute(null,cursor.getColumnName(j),(cursor.getString(j)!=null)?cursor.getString(j):"");
				}
			}
		} else {
			for(int j=0;j<cursor.getColumnCount();j++){
				if(cursor.getColumnName(j).equals("address")||
						cursor.getColumnName(j).equals("date")||
						cursor.getColumnName(j).equals("_id")||
						cursor.getColumnName(j).equals("thread_id")||
						cursor.getColumnName(j).equals("type")||
						cursor.getColumnName(j).equals("read")){
					xmlSerializer.attribute(null,cursor.getColumnName(j),(cursor.getString(j)!=null)?cursor.getString(j):"");
				}

				if(cursor.getColumnName(j).equals("body")){
					try{
						xmlSerializer.attribute(null,cursor.getColumnName(j),(cursor.getString(j) != null) ? cursor.getString(j):"");
					}catch (Exception e){
						xmlSerializer.attribute(null, "body","error" );
						Log.d("error", cursor.getString(cursor.getColumnIndex("body")));
					}
				}

			}
		}
		xmlSerializer.endTag(null, "item");
	}
}
