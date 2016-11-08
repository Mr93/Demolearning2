package com.example.prora.demolearning2.adapter;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.prora.demolearning2.R;

import java.util.ArrayList;

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
//                if(imageView !=null){
//                    if(layoutParent == 0) {
//                        switch (position){
//                            case 0:
//                                imageView.setImageResource(R.drawable.ic_apps_black_24dp);
//                                break;
//                            case 1:
//                                imageView.setImageResource(R.drawable.ic_contact_mail_black_24dp);
//                                break;
//                            case 2:
//                                imageView.setImageResource(R.drawable.ic_message_text_black_24dp);
//                                break;
//                            case 3:
//                                imageView.setImageResource(R.drawable.ic_phone_log_black_24dp);
//                                break;
//                            case 4:
//                                imageView.setImageResource(R.drawable.ic_calendar_black_24dp);
//                                break;
//                            case 5:
//                                imageView.setImageResource(R.drawable.ic_bookmark_black_24dp);
//                                break;
//                        }
//                    }else if((layoutParent == 2)||(layoutParent == 4)){
//                        switch (position){
//                            case 0:
//                                imageView.setImageResource(R.drawable.ic_content_save_black_24dp);
//                                break;
//                            case 1:
//                                imageView.setImageResource(R.drawable.ic_backup_restore_black_24dp);
//                                break;
//                            case 2:
//                                imageView.setImageResource(R.drawable.ic_view_list_black_24dp);
//                                break;
//                            case 3:
//                                imageView.setImageResource(R.drawable.ic_cloud_upload_black_24dp);
//                                break;
//                            case 4:
//                                imageView.setImageResource(R.drawable.ic_cloud_download_black_24dp);
//                                v.setLayoutParams(new AbsListView.LayoutParams(-1,1));
//                                v.setVisibility(View.GONE);
//                               // v.setVisibility(View.GONE);
//                               // position++;
//
//                                break;
//                            case 5:
//                                imageView.setImageResource(R.drawable.ic_delete_black_24dp);
//                                break;
//                            case 6:
//                                imageView.setImageResource(R.drawable.ic_alert_black_24dp);
//                                break;
//                        }
//                    }else if(layoutParent == 3){
//                        switch (position){
//                            case 0:
//                                imageView.setImageResource(R.drawable.ic_content_save_black_24dp);
//                                break;
//                            case 1:
//                                imageView.setImageResource(R.drawable.ic_message_processing_black_24dp);
//                                break;
//                            case 2:
//                                imageView.setImageResource(R.drawable.ic_backup_restore_black_24dp);
//                                break;
//                            case 3:
//                                imageView.setImageResource(R.drawable.ic_view_list_black_24dp);
//                                break;
//                            case 4:
//                                imageView.setImageResource(R.drawable.ic_cloud_upload_black_24dp);
//                                break;
//                            case 5:
//                                imageView.setImageResource(R.drawable.ic_cloud_download_black_24dp);
//                                v.setLayoutParams(new AbsListView.LayoutParams(-1, 1));
//                                v.setVisibility(View.GONE);
//                                break;
//                            case 6:
//                                imageView.setImageResource(R.drawable.ic_delete_black_24dp);
//                                break;
//                            case 7:
//                                imageView.setImageResource(R.drawable.ic_alert_black_24dp);
//                                break;
//                        }
//                    }else if(layoutParent == 5){
//                        switch (position){
//                            case 0:
//                                imageView.setImageResource(R.drawable.ic_content_save_black_24dp);
//                                break;
//                            case 1:
//                                imageView.setImageResource(R.drawable.ic_backup_restore_black_24dp);
//                                break;
//                            case 2:
//                                imageView.setImageResource(R.drawable.ic_cloud_upload_black_24dp);
//                                break;
//                            case 3:
//                                imageView.setImageResource(R.drawable.ic_cloud_download_black_24dp);
//                                v.setLayoutParams(new AbsListView.LayoutParams(-1, 1));
//                                v.setVisibility(View.GONE);
//                                break;
//                            case 4:
//                                imageView.setImageResource(R.drawable.ic_delete_black_24dp);
//                                break;
//                            case 5:
//                                imageView.setImageResource(R.drawable.ic_alert_black_24dp);
//                                break;
//                        }
//                    }else if(layoutParent == 6){
//                        if(isPackageInstalled("com.android.chrome",mContext)){
//                            switch (position){
//                                case 0:
//                                    imageView.setImageResource(R.drawable.ic_content_save_black_24dp);
//                                    break;
//                                case 1:
//                                    imageView.setImageResource(R.drawable.ic_backup_restore_black_24dp);
//                                    break;
//                                case 2:
//                                    imageView.setImageResource(R.drawable.ic_google_chrome_black_24dp);
//                                    break;
//                                case 3:
//                                    imageView.setImageResource(R.drawable.ic_view_list_black_24dp);
//                                    break;
//                                case 4:
//                                    imageView.setImageResource(R.drawable.ic_cloud_upload_black_24dp);
//                                    break;
//                                case 5:
//                                    imageView.setImageResource(R.drawable.ic_cloud_download_black_24dp);
//                                    v.setLayoutParams(new AbsListView.LayoutParams(-1, 1));
//                                    v.setVisibility(View.GONE);
//                                    break;
//                                case 6:
//                                    imageView.setImageResource(R.drawable.ic_delete_black_24dp);
//                                    break;
//                                case 7:
//                                    imageView.setImageResource(R.drawable.ic_alert_black_24dp);
//                                    break;
//                            }
//                        }else {
//                            switch (position){
//                                case 0:
//                                    imageView.setImageResource(R.drawable.ic_content_save_black_24dp);
//                                    break;
//                                case 1:
//                                    imageView.setImageResource(R.drawable.ic_backup_restore_black_24dp);
//                                    break;
//                                case 2:
//                                    imageView.setImageResource(R.drawable.ic_view_list_black_24dp);
//                                    break;
//                                case 3:
//                                    imageView.setImageResource(R.drawable.ic_cloud_upload_black_24dp);
//                                    break;
//                                case 4:
//                                    imageView.setImageResource(R.drawable.ic_cloud_download_black_24dp);
//
//                                    //hide item
//                                    v.setLayoutParams(new AbsListView.LayoutParams(-1, 1));
//                                   // v.set
//                                    v.setVisibility(View.GONE);
//                                    break;
//                                case 5:
//                                    imageView.setImageResource(R.drawable.ic_delete_black_24dp);
//                                    break;
//                                case 6:
//                                    imageView.setImageResource(R.drawable.ic_alert_black_24dp);
//                                    break;
//                            }
//                        }
//                    }
//
//                }
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
