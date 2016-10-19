package com.example.prora.demolearning2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.prora.demolearning2.adapter.List2Line;
import com.example.prora.demolearning2.adapter.List2LineAdapter;
import com.example.prora.demolearning2.strategy.FactoryStrategy;
import com.example.prora.demolearning2.strategy.IStrategy;
import com.example.prora.demolearning2.strategy.NullStrategy;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity{

	IStrategy strategy = new NullStrategy();
	ListView listViewMain;
	List2LineAdapter list2LineAdapter;
	ArrayList<List2Line> list2Lines;
	String[] label = {"Backup","View"};
	String[] summary = {"Backup", "View"};
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
		String key_strategy = getResources().getString(R.string.strategy);
		if (intent.hasExtra(key_strategy)){
			type = intent.getStringExtra("Strategy");
			strategy = FactoryStrategy.getInstance(this).getStrategy(type);
			if (strategy != null){
				setTitle(strategy.getName());
				for (int i = 0; i< summary.length; i++) {
					summary[i] = summary[i] + " your " + type;
				}
			}
		}
		initListview();
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
						strategy.backup();
						break;
					case 1:
						strategy.view();
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

}
