package com.voxcast.adapter;

import java.util.ArrayList;

import com.voxcast.R;
import com.voxcast.view.RoundedImageView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class PostAdapter extends BaseAdapter {

	private Context mContext;
	private LayoutInflater infalter;

	private boolean isActionMultiplePick;
	private RoundedImageView image;

	public PostAdapter(Context c) {
		infalter = (LayoutInflater) c
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mContext = c;

	}

	@Override
	public int getCount() {
		return 2;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public void setMultiplePick(boolean isMultiplePick) {
		this.isActionMultiplePick = isMultiplePick;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null) {

			convertView = infalter.inflate(R.layout.home_fragment, null);
			image = (RoundedImageView) convertView
					.findViewById(R.id.homeScrnProfImg);
			image.setImageResource(R.drawable.circle_img_pic);

		}
		return convertView;

	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}
}
