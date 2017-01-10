package com.example.prora.demolearning2;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.prora.demolearning2.adapter.List2Line;
import com.example.prora.demolearning2.adapter.List2LineAdapter;
import com.example.prora.demolearning2.interfaceMVP.MVP_Main_Activity;
import com.example.prora.demolearning2.presenter.PresenterMainActivity;

import javax.inject.Inject;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MVP_Main_Activity.RequiredViewOps {

	private static final String TAG = MainActivity.class.getSimpleName();
	private final int PERMISSION_REQUEST = 1;
	ListView listViewMain;
	List2LineAdapter list2LineAdapter;
	ArrayList<List2Line> list2Lines;
	@Inject
	MVP_Main_Activity.ProvidedPresenterOps mPresenter;
	Context context;
	EditText editText;
	Button btnSubmit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.context = this;
		getRuntimePermission();
		listViewMain = (ListView) findViewById(R.id.listViewFirstActivity);
		editText = (EditText) findViewById(R.id.edit_input);
		btnSubmit = (Button) findViewById(R.id.btn_submit);
//		((MainApplication) getApplication()).getComponent(this).inject(this);
		StateMainPresenter stateMainPresenter = StateMainPresenter.getInstance(MainActivity.class.getName());
		Log.d(TAG, "onCreate: "+ MainActivity.class.getName());
		MVP_Main_Activity.ProvidedPresenterOps providedPresenterOps = stateMainPresenter.getProvidedPresenterSaved();
		if (providedPresenterOps == null){
			Log.d(TAG, "onCreate: vao lan dau");
			((MainApplication) getApplication()).getComponent(this).inject(this);
			stateMainPresenter.setProvidedPresenterOps(mPresenter);
		}else{
			Log.d(TAG, "onCreate: da vao roi");
			mPresenter = providedPresenterOps;
			mPresenter.setView(this);
		}
		
		
		list2Lines = mPresenter.getListItems();
		list2LineAdapter = new List2LineAdapter(MainActivity.this,R.layout.item_list_two_line_text_app,0,list2Lines);
		listViewMain.setAdapter(list2LineAdapter);
		listViewMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			if (position > 1) {
				Toast.makeText(MainActivity.this, "Click = " + position, Toast.LENGTH_SHORT).show();
			}else {
				mPresenter.clickItem(list2Lines.get(position).getLabel());
			}
			}
		});
		btnSubmit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Toast.makeText(MainActivity.this, "Text = " + editText.getText().toString(), Toast.LENGTH_SHORT).show();
			}
		});
		findViewById(R.id.btn_bottom).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(MainActivity.this, "Click bottom", Toast.LENGTH_SHORT).show();
				boolean hasCallPhonePermission = ContextCompat.checkSelfPermission(MainActivity.this,
						Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED;
				if (hasCallPhonePermission)
					startActivity(createCallIntentFromNumber());
				else
					Toast.makeText(MainActivity.this, "Ban phai can quyen de goi", Toast.LENGTH_SHORT).show();
			}
		});
	}

	private Intent createCallIntentFromNumber() {
		final Intent intentToCall = new Intent(Intent.ACTION_CALL);
		String number = editText.getText().toString();
		intentToCall.setData(Uri.parse("tel:" + number));
		return intentToCall;
	}


	@TargetApi(23)
	public void getRuntimePermission() {
		int permissionContactCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);
		int permissionSmsCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS);
		int permissionStorageCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
		int permissionCallPhone = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
		if (permissionDenied(permissionContactCheck) || permissionDenied(permissionSmsCheck)
				|| permissionDenied(permissionStorageCheck) || permissionDenied(permissionCallPhone) ) {
			if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)
					||ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_SMS)
					||ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)
					||ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)){
				showDialogPermission();
			}else {
				ArrayList<String> listPermission = new ArrayList<>();
				if(permissionDenied(permissionContactCheck))listPermission.add(Manifest.permission.READ_CONTACTS);
				if(permissionDenied(permissionSmsCheck))listPermission.add(Manifest.permission.READ_SMS);
				if(permissionDenied(permissionSmsCheck))listPermission.add(Manifest.permission.CALL_PHONE);
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

	@Override
	public Context getContext() {
		return context;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.test_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.notify:
				Toast.makeText(this, "You have selected Notify Menu", Toast.LENGTH_SHORT).show();
				return true;
			case R.id.webpage:
				Toast.makeText(this, "You have selected Web Menu", Toast.LENGTH_SHORT).show();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
}
