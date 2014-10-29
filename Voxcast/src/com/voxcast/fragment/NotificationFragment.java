package com.voxcast.fragment;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.voxcast.R;
import com.voxcast.adapter.NotificationAdapter;
import com.voxcast.model.NotificationModel;

public class NotificationFragment extends BaseFragment {

	private ArrayList<NotificationModel> notificationModelArrayList = new ArrayList<NotificationModel>();
	private ListView listView;

	public NotificationFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_notification, null);
		return view;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		listView = (ListView) view.findViewById(R.id.listView1);
		initializationUI();
	}

	private void initializationUI() {
		// TODO Auto-generated method stub
		setAsyncTask(getActivity());
	}

	private void setAsyncTask(Activity notificationActivity) {
		new LoadNotificationAsyncTask(notificationActivity,
				notificationModelArrayList).execute();

	}

	private class LoadNotificationAsyncTask extends AsyncTask<Void, Void, Void> {

		private ProgressDialog dialog;
		ArrayList<NotificationModel> notificationModelArrayList;

		public LoadNotificationAsyncTask(Activity activity,
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

				for (int i = 0; i < 100; i++)
					notificationModelArrayList.add(new NotificationModel());
			} catch (Exception e) {
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {

			dialog.dismiss();
			listView.setAdapter(new NotificationAdapter(getActivity(), 0,
					notificationModelArrayList));
		}
	}

}
