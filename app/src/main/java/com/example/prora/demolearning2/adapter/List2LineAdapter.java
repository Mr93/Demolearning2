package com.example.prora.demolearning2.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.prora.demolearning2.R;

import java.util.ArrayList;
import java.util.Collection;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.runner.lifecycle.Stage.RESUMED;

/**
 * Created by Dendimon on 12/17/2015.
 */
public class List2LineAdapter extends ArrayAdapter<List2Line> {
    Context mContext;
    ArrayList<List2Line> items;
    boolean tempCheck;
    int layoutItem;
    int layoutParent;
    Typeface myTypeface;
	String textQuote = "";

    public List2LineAdapter(Context Context, int TextViewResourceID, int LayoutParrent, ArrayList<List2Line> objects){
        super(Context,TextViewResourceID,objects);
        mContext = Context;
        items = objects;
        //tempCheck = TempCheck;
        layoutItem = TextViewResourceID;
        layoutParent = LayoutParrent;
        myTypeface = Typeface.createFromAsset(mContext.getAssets(), "fonts/Roboto_Regular.ttf");
    }

	public void setItems(ArrayList<List2Line> items) {
		this.items = items;
	}

	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v == null){
            v = LayoutInflater.from(mContext).inflate(layoutItem,null);
        }
        List2Line list2Line = items.get(position);
        if(list2Line!=null){
            if(layoutItem == R.layout.item_list_two_line_text_app){
                TextView txtLabel = (TextView) v.findViewById(R.id.txt_item_list_2_line_label);
                TextView txtSummary = (TextView) v.findViewById(R.id.txt_item_list_2_line_content);
                ImageView imageView = (ImageView) v.findViewById(R.id.imgList2LineIcon);
                if(txtLabel != null){
                    if(list2Line.getLabel()!=null){
                        txtLabel.setText(list2Line.getLabel());
                        txtLabel.setTypeface(myTypeface);
                        txtLabel.setTextSize(16);
                    }
                }
                if(txtSummary!=null){
                    if(list2Line.getSummary()!=null){
                        txtSummary.setText(list2Line.getSummary() + textQuote);
                        txtSummary.setTypeface(myTypeface);
                        txtSummary.setTextSize(14);
                    }
                }
				imageView.setImageResource(list2Line.getIdImageView());
            }
        }
        Log.d("view",v.toString());
        return v;

    }


	public String getTextQuote() {
		return textQuote;
	}

	public void setTextQuote(String textQuote) {
		this.textQuote = textQuote;
	}

	private boolean isPackageInstalled(String packagename, Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            pm.getPackageInfo(packagename, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}
