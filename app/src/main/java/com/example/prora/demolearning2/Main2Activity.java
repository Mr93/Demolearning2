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
import com.example.prora.demolearning2.state.FactoryState;
import com.example.prora.demolearning2.state.IState;
import com.example.prora.demolearning2.state.NullState;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity{

	IState state = new NullState();
	ListView listViewMain;
	List2LineAdapter list2LineAdapter;
	ArrayList<List2Line> list2Lines;
	String[] label = {"Backup","View"};
	String[] summary = {"Backup", "View"};
	String[] rootSummary = {"Backup", "View"};
	int[] idImageview = {
		R.drawable.ic_backup_black_24dp,
		R.drawable.ic_visibility_black_24dp
	};
	String type;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);

		Intent intent = getIntent();
		String key_state = getResources().getString(R.string.state);
		if (intent.hasExtra(key_state)){
			type = intent.getStringExtra(key_state);
			state = getState(type);
			updateLayoutWithState();
		}
		initListview();
	}

	private IState getState(String type) {
		return FactoryState.getInstance(this).getState(type);
	}

	private void updateLayoutWithState() {
		if (state != null){
			setTitle(state.getName());
			for (int i = 0; i< summary.length; i++) {
				summary[i] = rootSummary[i] + " your " + type;
			}
		}
	}


	public void initListview() {
		listViewMain = (ListView) findViewById(R.id.listViewFirstActivity);
		list2LineAdapter = new List2LineAdapter(Main2Activity.this,R.layout.item_list_two_line_text_app,0,getContentList());
		listViewMain.setAdapter(list2LineAdapter);
		listViewMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				switch (position){
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

	private ArrayList<List2Line> getContentList (){
		list2Lines = new ArrayList<>();
		for(int i = 0;i<label.length;i++){
			list2Lines.add(new List2Line(label[i],summary[i], idImageview[i]));
		}
		return list2Lines;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()){
			case R.id.change_state_item:
				showDialogChangeIState();
				break;
			default:
				break;
		}
		return true;
	}

	public void showDialogChangeIState(){
		new MaterialDialog.Builder(Main2Activity.this)
				.title(R.string.select)
				.items(R.array.list_state)
				.itemsCallback(new MaterialDialog.ListCallback() {
					@Override
					public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
						type = (String) text;
						state = getState(type);
						updateLayoutWithState();
						list2LineAdapter.setItems(getContentList());
						list2LineAdapter.notifyDataSetChanged();
					}
				})
				.show();
	}
}
