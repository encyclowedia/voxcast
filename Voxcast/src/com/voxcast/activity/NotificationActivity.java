package com.voxcast.activity;

import java.util.ArrayList;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.voxcast.R;
import com.voxcast.fragment.BaseFragment;
import com.voxcast.model.NotificationModel;
import com.voxcast.view.HorizontalListView;

public class NotificationActivity extends BaseFragment {

	ArrayList<NotificationModel> notificationModelArrayList;
	private TextView tv_loginFrag_termservice;
	ListView listView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.notification_activity, container,
				false);
		notificationModelArrayList = new ArrayList<NotificationModel>();

		listView = (ListView) view.findViewById(R.id.listView1);
		initializationUI();
		return view;
	}

	/*
	 * @Override public void onCreate(Bundle savedInstanceState) {
	 * super.onCreate(savedInstanceState);
	 * setContentView(R.layout.notification_activity);
	 * 
	 * notificationModelArrayList = new ArrayList<NotificationModel>();
	 * initializationUI();
	 * 
	 * }
	 */

	private void initializationUI() {
		// TODO Auto-generated method stub
		setAsyncTask(this);
	}

	private void setAsyncTask(NotificationActivity notificationActivity) {
		new LoadNotificationAsyncTask(notificationActivity,
				notificationModelArrayList).execute();

	}

	private class LoadNotificationAsyncTask extends AsyncTask<Void, Void, Void> {

		private ProgressDialog dialog;
		ArrayList<NotificationModel> notificationModelArrayList;

		public LoadNotificationAsyncTask(NotificationActivity activity,
				ArrayList<NotificationModel> notificationModelArrayList) {
			this.notificationModelArrayList = notificationModelArrayList;

			dialog = new ProgressDialog(getActivity());
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

				for (int i = 0; i < 100; i++)
					notificationModelArrayList.add(new NotificationModel());
			} catch (Exception e) {
				// TODO: handle exception
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {

			dialog.dismiss();

			listView.setAdapter(new CustomAdapter(getActivity(),
					notificationModelArrayList));
		}
	}

	public class CustomAdapter extends BaseAdapter {

		private LayoutInflater inflater = null;
		ArrayList<NotificationModel> notificationModelArrayList;

		/************* CustomAdapter Constructor *****************/
		public CustomAdapter(Context ctx,
				ArrayList<NotificationModel> notificationModelArrayList) {

			this.notificationModelArrayList = notificationModelArrayList;
			inflater = (LayoutInflater) ctx
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		/******** What is the size of Passed Arraylist Size ************/
		public int getCount() {

			return notificationModelArrayList.size();
		}

		public Object getItem(int position) {
			return notificationModelArrayList.get(position);
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
