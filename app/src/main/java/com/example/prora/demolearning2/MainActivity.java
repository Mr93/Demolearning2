package com.example.prora.demolearning2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.example.prora.demolearning2.strategy.ContactStrategy;
import com.example.prora.demolearning2.strategy.IStrategy;
import com.example.prora.demolearning2.strategy.SmsStrategy;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

	IStrategy strategy;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.btnContact).setOnClickListener(this);
		findViewById(R.id.btnSms).setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		//Doan nay se get ra concreate Strategy sau
		int id = view.getId();
		Intent intent = new Intent(MainActivity.this, Main2Activity.class);
		switch (id){
			case R.id.btnContact:
				strategy = new ContactStrategy();
				intent.putExtra(getResources().getString(R.string.strategy), (Serializable) strategy);
				startActivity(intent);
				break;
			case R.id.btnSms:
				strategy = new SmsStrategy();
				intent.putExtra(getResources().getString(R.string.strategy), (Serializable) strategy);
				startActivity(intent);
				break;
			default:
				break;
		}
	}
}
