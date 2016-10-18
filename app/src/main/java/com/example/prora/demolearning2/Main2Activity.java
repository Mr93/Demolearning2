package com.example.prora.demolearning2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.example.prora.demolearning2.strategy.ContactStrategy;
import com.example.prora.demolearning2.strategy.IStrategy;
import com.example.prora.demolearning2.strategy.SmsStrategy;

import java.io.Serializable;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener{

	IStrategy strategy;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);
		findViewById(R.id.btnBackup).setOnClickListener(this);
		findViewById(R.id.btnView).setOnClickListener(this);

		Intent intent = getIntent();
		String key_strategy = getResources().getString(R.string.strategy);
		if (intent.hasExtra(key_strategy)){
			strategy = (IStrategy) intent.getSerializableExtra(key_strategy);
		}
		setTitle(strategy.getName());
	}

	@Override
	public void onClick(View view) {
		int id = view.getId();
		switch (id){
			case R.id.btnBackup:
				strategy.backup();
				break;
			case R.id.btnView:
				strategy.view();
				break;
			default:
				break;
		}
	}
}
