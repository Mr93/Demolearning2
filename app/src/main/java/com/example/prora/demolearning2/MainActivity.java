package com.example.prora.demolearning2;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

	private final int PERMISSION_REQUEST = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getRuntimePermission();
	}

	@TargetApi(23)
	public void getRuntimePermission() {
		int permissionContactCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);
		int permissionSmsCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS);
		int permissionStorageCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
		if (permissionDenied(permissionContactCheck) || permissionDenied(permissionSmsCheck)
				|| permissionDenied(permissionStorageCheck)) {
			if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)
					||ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_SMS)
					||ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)){
				MaterialFacade.getInstance().showDialogNeedPermission(this, "Permission required", "We need " +
						"permission to backup contact + sms, pls go to setting and set permission for us");
			}else {
				ArrayList<String> listPermission = new ArrayList<>();
				if(permissionDenied(permissionContactCheck))listPermission.add(Manifest.permission.READ_CONTACTS);
				if(permissionDenied(permissionSmsCheck))listPermission.add(Manifest.permission.READ_SMS);
				if(permissionDenied(permissionStorageCheck))listPermission.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
				ActivityCompat.requestPermissions(this,  listPermission.toArray(new String[listPermission.size()])
						, PERMISSION_REQUEST);
			}
		}
	}

	private boolean permissionDenied(int value) {
		return value == PackageManager.PERMISSION_DENIED;
	}

	/**
	 * Callback for the result from requesting permissions. This method
	 * is invoked for every call on {@link #requestPermissions(String[], int)}.
	 * <p>
	 * <strong>Note:</strong> It is possible that the permissions request interaction
	 * with the user is interrupted. In this case you will receive empty permissions
	 * and results arrays which should be treated as a cancellation.
	 * </p>
	 *
	 * @param requestCode  The request code passed in {@link #requestPermissions(String[], int)}.
	 * @param permissions  The requested permissions. Never null.
	 * @param grantResults The grant results for the corresponding permissions
	 *                     which is either {@link PackageManager#PERMISSION_GRANTED}
	 *                     or {@link PackageManager#PERMISSION_DENIED}. Never null.
	 * @see #requestPermissions(String[], int)
	 */
	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		switch (requestCode){
			case PERMISSION_REQUEST:{
				if(grantResults.length > 0
						&& grantResults[0] == PackageManager.PERMISSION_GRANTED){
					Toast.makeText(this, "Granted", Toast.LENGTH_SHORT).show();
				}else {
					getRuntimePermission();
				}
				return;
			}
		}
	}
}
