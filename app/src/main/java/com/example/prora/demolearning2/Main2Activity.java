package com.example.prora.demolearning2;

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
import com.example.prora.demolearning2.presenter.PresenterMainActivity2;
import com.example.prora.demolearning2.state.FactoryState;
import com.example.prora.demolearning2.state.IState;
import com.example.prora.demolearning2.state.NullState;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity implements MVP_Main_Activity2.RequiredViewOps{

	IState state = NullState.getInstance();
	ListView listViewMain;
	List2LineAdapter list2LineAdapter;
	ArrayList<List2Line> list2Lines;
	String type;
	PresenterMainActivity2 mPresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);
		mPresenter = new PresenterMainActivity2();
		Intent intent = getIntent();
		String key_state = getResources().getString(R.string.state);
		if (intent.hasExtra(key_state)) {
			type = intent.getStringExtra(key_state);
			state = getState(type);
		}
		setTitle(state.getName());
		initListview();
	}

	private IState getState(String type) {
		return FactoryState.getInstance(this).getState(type);
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
				switch (position) {
					case 0:
						state.backup();
						break;
					case 1:
						state.view();
						break;
					default:
						break;
				}
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
				showDialogChangeState();
				break;
			default:
				break;
		}
		return true;
	}

	private void showDialogChangeState() {
		MaterialBuilderFacade materialBuilderFacade = new MaterialBuilderFacade(this);
		materialBuilderFacade.title(R.string.select);
		materialBuilderFacade.items(R.array.list_state);
		materialBuilderFacade.itemsCallback(new MaterialDialog.ListCallback() {
				@Override
				public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
					if (text != null && !text.toString().equalsIgnoreCase("")) {
						changeState(text.toString());
					}
				}
			});
		MaterialFacade.getInstance().showDialog(materialBuilderFacade);
	}

	public void changeState(String text) {
		type = text;
		state = getState(type);

		setTitle(state.getName());
		list2Lines = mPresenter.getListItemsAction();
		setTextQuoteAdapter();
		list2LineAdapter.setItems(list2Lines);
		list2LineAdapter.notifyDataSetChanged();
	}

	public void setTextQuoteAdapter() {
		String textQuote = " your " + type;
		list2LineAdapter.setTextQuote(textQuote.toLowerCase());
	}
}
