package com.example.prora.demolearning2;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import java.io.File;

/**
 * Created by RUBYCELL on 10/17/2016.
 */

public abstract class BackupTemplate {

	String fileName = "";
	String filePath = Environment.getExternalStorageDirectory().getAbsolutePath();

	public BackupTemplate() {

	}

	public final void backup(){
		deleteOldFile();
		getData();
		writeDataToFile();
	}

	abstract void getData();

	abstract void writeDataToFile();

	private void deleteOldFile() {
		File dFile = new File(filePath + "/" + fileName);
		if (dFile.exists()) {
			dFile.delete();
		}
	}

	public String getFilePath() {
		return filePath + "/" + fileName;
	}

}
