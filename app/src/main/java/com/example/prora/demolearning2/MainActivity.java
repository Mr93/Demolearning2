package com.example.prora.demolearning2;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.prora.demolearning2.adapter.List2Line;
import com.example.prora.demolearning2.adapter.List2LineAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

	private final int PERMISSION_REQUEST = 1;
	ListView listViewMain;
	List2LineAdapter list2LineAdapter;
	ArrayList<List2Line> list2Lines;
	String[] label = {"Contacts","Sms"};
	String[] summary = {"Backup and restore your contacts",
			"Backup and restore your sms"};
	int[] idImageview = {
			R.drawable.ic_contact_mail_black_48dp,
			R.drawable.ic_message_text_black_48dp
	};


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getRuntimePermission();
		listViewMain = (ListView) findViewById(R.id.listViewFirstActivity);
		list2LineAdapter = new List2LineAdapter(MainActivity.this,R.layout.item_list_two_line_text_app,0,getContentList());
		listViewMain.setAdapter(list2LineAdapter);
		listViewMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(MainActivity.this, Main2Activity.class);
				intent.putExtra(getResources().getString(R.string.state), label[position]);
				startActivity(intent);
			}
		});
	}

	private ArrayList<List2Line> getContentList (){
		list2Lines = new ArrayList<>();
		for(int i = 0;i<label.length;i++){
			list2Lines.add(new List2Line(label[i],summary[i], idImageview[i]));
		}
		return list2Lines;
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
				showDialogPermission();
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

	private void showDialogPermission() {
		MaterialBuilderFacade materialBuilderFacade = new MaterialBuilderFacade(this);
		materialBuilderFacade.title("Permission required");
		materialBuilderFacade.content("We need " +
						"permission to backup contact + sms, pls go to setting and set permission for us");
		materialBuilderFacade.autoDismiss(false);
		materialBuilderFacade.canceledOnTouchOutside(false);
		materialBuilderFacade.dismissListener(new DialogInterface.OnDismissListener() {
					@Override
					public void onDismiss(DialogInterface dialog) {
						System.exit(0);
					}
				});
		materialBuilderFacade.positiveText("Ok");
		materialBuilderFacade.onPositive(new MaterialDialog.SingleButtonCallback() {
					@Override
					public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
						dialog.dismiss();
					}
				});
		MaterialFacade.getInstance().showDialog(materialBuilderFacade);
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
