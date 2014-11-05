package com.voxcast.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Im;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.voxcast.R;
import com.voxcast.adapter.NotificationAdapter;

import com.voxcast.model.NotificationModel;

public class Test extends Activity {

	private ListView listView;
	private ImageView imageView2;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_home_land);

		initializationUI();
	 
		
		listView.setOnItemClickListener(new OnItemClickListener()
		{
		    @Override 
		    public void onItemClick(AdapterView<?> arg0, View arg1,int position, long arg3)
		    { 
		        Toast.makeText(Test.this, "gdf " + position, Toast.LENGTH_SHORT).show();
		       
		    }
		});
	}

	private ArrayList<NotificationModel> notificationModelArrayList = new ArrayList<NotificationModel>();
	private LinearLayout ssss;

	private void initializationUI() {
		
		System.out.println("aaaaaaaa ");
		// TODO Auto-generated method stub
		imageView2 = (ImageView) findViewById(R.id.imageView2);
		ssss = (LinearLayout) findViewById(R.id.ssss);
		listView = (ListView) findViewById(R.id.lv_fragment_home);
		setAsyncTask(this);
	}

	private void setAsyncTask(Test notificationActivity) {
		new LoadNotificationAsyncTask(notificationActivity,
				notificationModelArrayList).execute();

	}

	private class LoadNotificationAsyncTask extends AsyncTask<Void, Void, Void> {

		private ProgressDialog dialog;
		ArrayList<NotificationModel> notificationModelArrayList;

		public LoadNotificationAsyncTask(Test activity,
				ArrayList<NotificationModel> notificationModelArrayList) {
			this.notificationModelArrayList = notificationModelArrayList;

			dialog = new ProgressDialog(activity);
		}

		@Override
		protected void onPreExecute() {

			this.dialog.setMessage("Loading notification...");
			this.dialog.show();

			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... apps) {
			try {

				for (int i = 0; i < 10; i++)
					notificationModelArrayList.add(new NotificationModel());
			} catch (Exception e) {
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {

			dialog.dismiss();
			listView.setAdapter(new NotificationAdapter(Test.this, 0,
					notificationModelArrayList));
		}
	}

}
