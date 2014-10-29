package com.voxcast.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.voxcast.R;
import com.voxcast.model.NotificationModel;

public class NotificationAdapter extends ArrayAdapter<NotificationModel> {
	private LayoutInflater inflater = null;

	public NotificationAdapter(Context context, int resource,
			List<NotificationModel> objects) {
		super(context, resource, objects);
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder;

		if (convertView == null) {
			convertView = inflater.inflate(
					R.layout.notification_activity_custom, null);

			holder = new ViewHolder();

			holder.iv_notification_image = (ImageView) convertView
					.findViewById(R.id.iv_notification_image);
			holder.tv_notification_msg = (TextView) convertView
					.findViewById(R.id.tv_notification_msg);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tv_notification_msg.setText("iiisfdvbisdskdj ksjdvbsjd vjsdbv");
		return convertView;
	}

	public static class ViewHolder {
		public TextView tv_notification_msg;
		public ImageView iv_notification_image;
	}

}
