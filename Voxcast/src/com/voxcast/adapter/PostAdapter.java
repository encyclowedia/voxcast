package com.voxcast.adapter;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.voxcast.R;
import com.voxcast.activity.BaseActivity;
import com.voxcast.view.CircularImageView;

public class PostAdapter extends BaseAdapter implements
		StickyListHeadersAdapter {

	private Context mContext;
	private LayoutInflater infalter;

	private boolean isActionMultiplePick;
	private CircularImageView image;
	private String[] strings;

	public PostAdapter(Context context, String[] strings) {
		this.strings = strings;

		infalter = LayoutInflater.from(context);
		this.mContext = context;
	}

	@Override
	public int getCount() {
		return strings.length;
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
		}
		return convertView;

	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return strings[position];
	}

	@Override
	public View getHeaderView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			convertView = infalter.inflate(R.layout.layout_header, null);
			image = (CircularImageView) convertView.findViewById(R.id.userImage);
			image.setImageResource(R.drawable.circle_img_pic);
			// ImageLoader.getInstance().displayImage(
			// "drawable://" + R.drawable.circle_img_pic, image,
			// ((BaseActivity) mContext).options);
		}
		return convertView;
	}

	@Override
	public long getHeaderId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
}
