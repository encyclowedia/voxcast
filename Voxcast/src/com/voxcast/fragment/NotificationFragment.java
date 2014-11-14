package com.voxcast.fragment;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

import com.voxcast.R;
import com.voxcast.activity.HomeActivity;
import com.voxcast.adapter.NotificationAdapter;
import com.voxcast.model.NotificationModel;

public class NotificationFragment extends BaseFragment {

	private ArrayList<NotificationModel> notificationModelArrayList = new ArrayList<NotificationModel>();
	private ListView listView;
	protected int top;

	public NotificationFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_notification, null);
		return view;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
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

			listView.setOnScrollListener(new OnScrollListener() {

				@Override
				public void onScrollStateChanged(AbsListView view,
						int scrollState) {

				}

				@Override
				public void onScroll(AbsListView view, int firstVisibleItem,
						int visibleItemCount, int totalItemCount) {
					View childAt = view.getChildAt(0);
					if (childAt == null)
						return;
					int top = childAt.getTop();

					int diff = NotificationFragment.this.top - top;

					if (diff < 0 && Math.abs(diff) < childAt.getHeight() / 2) {
						((HomeActivity) getActivity()).makeBarVisible();
					} else if (diff > 0
							&& Math.abs(diff) < childAt.getHeight() / 2) {
						((HomeActivity) getActivity()).makeBarInVisible();
					}

					NotificationFragment.this.top = top;
				}
			});
		}
	}

}
