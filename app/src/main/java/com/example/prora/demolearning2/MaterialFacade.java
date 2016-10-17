package com.example.prora.demolearning2;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.folderselector.FileChooserDialog;

/**
 * Created by prora on 10/17/2016.
 */

public class MaterialFacade {
	private static MaterialFacade instance;

	private MaterialFacade() {

	}

	public static MaterialFacade getInstance(){
		if(instance == null){
			instance = new MaterialFacade();
		}
		return instance;
	}

	public MaterialDialog getIndeterminateProgressDialogs(Context context){
		return new MaterialDialog.Builder(context)
				.title("Backup")
				.content("Loading")
				.progress(true, 0)
				.show();
	}

	public MaterialDialog getDeterminateProgressDialogs(Context context, int max, String title){
		return new MaterialDialog.Builder(context)
				.title(title)
				.content("Loading")
				.progress(false, max, true)
				.autoDismiss(false)
				.canceledOnTouchOutside(false)
				.cancelable(false)
				.show();
	}
}
