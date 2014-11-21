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
import com.voxcast.model.Poster;

public class VotesAdapter extends ArrayAdapter<Poster> {
	private LayoutInflater inflater = null;

	public VotesAdapter(Context context, int resource, List<Poster> objects) {
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

			holder.img_userImage = (ImageView) convertView
					.findViewById(R.id.iv_notification_image);
			holder.txt_name = (TextView) convertView
					.findViewById(R.id.tv_notification_msg);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.txt_name.setText(getItem(position).getName());
		return convertView;
	}

	public static class ViewHolder {
		public TextView txt_name;
		public ImageView img_userImage;
	}

}
