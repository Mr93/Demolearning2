package com.example.prora.demolearning2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.prora.demolearning2.adapter.List2Line;
import com.example.prora.demolearning2.adapter.List2LineAdapter;
import com.example.prora.demolearning2.interfaceMVP.MVP_Main_Activity2;
import com.example.prora.demolearning2.model.ModelMainActivity2;
import com.example.prora.demolearning2.presenter.PresenterMainActivity2;

import java.util.ArrayList;

import javax.inject.Inject;

public class Main2Activity extends AppCompatActivity implements MVP_Main_Activity2.RequiredViewOps{

	ListView listViewMain;
	List2LineAdapter list2LineAdapter;
	ArrayList<List2Line> list2Lines;
	String type;
	StateMainPresenter stateMainPresenter;
	@Inject
	MVP_Main_Activity2.ProvidedPresenterOps mPresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);
	}

	private void createPresenter() {
		stateMainPresenter = StateMainPresenter.getInstance(MainActivity.class.getName());
		if(stateMainPresenter.getProvidedPresenterSavedMain2() == null){
			((MainApplication) getApplication()).getPresenterComponentMain2(this).inject(this);
		}else {
			mPresenter = stateMainPresenter.getProvidedPresenterSavedMain2();
			mPresenter.setView(this);
		}
		Intent intent = getIntent();
		String key_state = getResources().getString(R.string.state);
		if (intent.hasExtra(key_state)) {
			type = intent.getStringExtra(key_state);
			mPresenter.setState(type);
		}
		setTitle(mPresenter.getStateName());
		initListview();
	}


	public void initListview() {
		listViewMain = (ListView) findViewById(R.id.listViewFirstActivity);
		list2Lines = mPresenter.getListItemsAction();
		list2LineAdapter = new List2LineAdapter(Main2Activity.this, R.layout.item_list_two_line_text_app, 0, list2Lines);
		setTextQuoteAdapter();
		listViewMain.setAdapter(list2LineAdapter);
		listViewMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				mPresenter.clickAction(position);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.change_state_item:
				mPresenter.choseChangeStateMenuItem();
				break;
			default:
				break;
		}
		return true;
	}

	public void changeState(String text) {
		type = text;
		mPresenter.setState(type);
		setTitle(mPresenter.getStateName());
		list2Lines = mPresenter.getListItemsAction();
		setTextQuoteAdapter();
		list2LineAdapter.setItems(list2Lines);
		list2LineAdapter.notifyDataSetChanged();
	}

	@Override
	public Context getContext() {
		return this;
	}

	public void setTextQuoteAdapter() {
		String textQuote = " your " + type;
		list2LineAdapter.setTextQuote(textQuote.toLowerCase());
	}

	@Override
	protected void onStop() {
		super.onStop();
		stateMainPresenter.setProvidedPresenterOpsMain2(mPresenter);
	}

	@Override
	protected void onStart() {
		super.onStart();
		createPresenter();
	}
}
