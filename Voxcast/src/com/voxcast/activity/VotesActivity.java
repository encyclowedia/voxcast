package com.voxcast.activity;

import java.util.ArrayList;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.voxcast.R;
import com.voxcast.model.DownvotesModel;

public class VotesActivity extends BaseActivity {

	ArrayList<DownvotesModel> downvotesModelArrayList;
	private TextView tv_loginFrag_termservice;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.downvote_activity);

		downvotesModelArrayList = new ArrayList<DownvotesModel>();
		initializationUI();

	}

	private void initializationUI() {
		// TODO Auto-generated method stub
		setAsyncTask(this);
	}

	private void setAsyncTask(VotesActivity downvotesActivity) {
		new LoadNotificationAsyncTask(downvotesActivity,
				downvotesModelArrayList).execute();

	}

	private class LoadNotificationAsyncTask extends AsyncTask<Void, Void, Void> {

		private ProgressDialog dialog;
		ArrayList<DownvotesModel> downvotesModelArrayList;

		public LoadNotificationAsyncTask(VotesActivity activity,
				ArrayList<DownvotesModel> downvotesModelArrayList) {
			this.downvotesModelArrayList = downvotesModelArrayList;

			dialog = new ProgressDialog(activity);
		}

		@Override
		protected void onPreExecute() {

			this.dialog.setMessage("Loading downvotes...");
			this.dialog.show();

			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... apps) {
			try {

				for (int i = 0; i < 100; i++)
					downvotesModelArrayList.add(new DownvotesModel());
			} catch (Exception e) {
				// TODO: handle exception
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {

			dialog.dismiss();

			ListView listView = (ListView) findViewById(R.id.listView1);
			listView.setAdapter(new CustomAdapter(VotesActivity.this,
					downvotesModelArrayList));
		}
	}

	public class CustomAdapter extends BaseAdapter {

		private LayoutInflater inflater = null;
		ArrayList<DownvotesModel> downvotesModelArrayList;

		/************* CustomAdapter Constructor *****************/
		public CustomAdapter(Context ctx,
				ArrayList<DownvotesModel> downvotesModelArrayList) {

			this.downvotesModelArrayList = downvotesModelArrayList;
			inflater = (LayoutInflater) ctx
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		/******** What is the size of Passed Arraylist Size ************/
		public int getCount() {

			return downvotesModelArrayList.size();
		}

		public Object getItem(int position) {
			return downvotesModelArrayList.get(position);
		}

		public long getItemId(int position) {
			return 0;
		}

		public View getView(int position, View convertView, ViewGroup parent) {

			View vi = convertView;
			ViewHolder holder;

			if (convertView == null) {

				vi = inflater.inflate(R.layout.notification_activity_custom,
						null);

				holder = new ViewHolder();
				holder.iv_notification_image = (ImageView) vi
						.findViewById(R.id.iv_notification_image);
				holder.tv_notification_msg = (TextView) vi
						.findViewById(R.id.tv_notification_msg);

				vi.setTag(holder);
			} else {
				holder = (ViewHolder) vi.getTag();

				/*
				 * holder.iv_notification_image
				 * .setImageBitmap(notificationModelArrayList
				 * .get(position).getImageBitmap());
				 */

				holder.tv_notification_msg
						.setText("iiisfdvbisdskdj ksjdvbsjd vjsdbv");

			}
			return vi;
		}
	}

	public static class ViewHolder {

		public TextView tv_notification_msg;
		public ImageView iv_notification_image;

	}

}
