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

	public MaterialDialog getIndeterminateProgressDialogs(Context context){
		return new MaterialDialog.Builder(context)
				.title("Backup")
				.content("Loading")
				.progress(true, 0)
				.show();
	}

	public void getDeterminateProgressDialogs(Context context, int max, String title){
		materialDialog = new MaterialDialog.Builder(context)
				.title(title)
				.content("Loading")
				.progress(false, max, true)
				.autoDismiss(false)
				.canceledOnTouchOutside(false)
				.cancelable(false)
				.show();
	}

	public void updateDeterminateProgressDialogs(final int progress){
		materialDialog.setProgress(progress);
	}

	public void finishDeterminateProgressDialogs(String value){
		materialDialog.setContent(value);
		materialDialog.setCancelable(true);
	}

	public void showDialogNeedPermission(final Context context, String title, final String content){
		materialDialog = new MaterialDialog.Builder(context)
				.title(title)
				.content(content)
				.autoDismiss(false)
				.canceledOnTouchOutside(false)
				.dismissListener(new DialogInterface.OnDismissListener() {
					@Override
					public void onDismiss(DialogInterface dialog) {
						Log.d(TAG, "onDismiss: ");
						System.exit(0);
					}
				})
				.positiveText("Ok")
				.onPositive(new MaterialDialog.SingleButtonCallback() {
					@Override
					public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
						dialog.dismiss();
					}
				})
				.show();
	}

	public void showDialogChangeIState(final Context context) {
		materialDialog = new MaterialDialog.Builder(context)
				.title(R.string.select)
				.items(R.array.list_state)
				.itemsCallback(new MaterialDialog.ListCallback() {
					@Override
					public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
						if (text != null && !text.toString().equalsIgnoreCase("")) {
							((Main2Activity) context).changeState(text.toString());
						}
					}
				})
				.show();
	}
}
