package com.example.prora.demolearning2;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.folderselector.FileChooserDialog;

/**
 * Created by prora on 10/17/2016.
 */

public class MaterialFacade {
	private static final String TAG = MaterialFacade.class.getName();
	private static MaterialFacade instance;
	private MaterialDialog materialDialog;

	private MaterialFacade() {

	}

	public static MaterialFacade getInstance(){
		if(instance == null){
			instance = new MaterialFacade();
		}
		return instance;
	}

	public void showDialog(MaterialBuilderFacade materialBuilderFacade){
		materialDialog = materialBuilderFacade.show() ;
	}

	public void updateDeterminateProgressDialogs(final int progress){
		materialDialog.setProgress(progress);
	}

	public void finishDeterminateProgressDialogs(String value){
		materialDialog.setContent(value);
		materialDialog.setCancelable(true);
	}
}
