package com.voxcast.adapter;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.voxcast.R;
import com.voxcast.activity.BaseActivity;
import com.voxcast.activity.VotesActivity;
import com.voxcast.listeners.OnPostClickListener;
import com.voxcast.view.CircularImageView;

public class PostAdapter extends BaseAdapter implements
		StickyListHeadersAdapter, OnClickListener, OnCheckedChangeListener {

	private Context mContext;
	private LayoutInflater infalter;

	private boolean isActionMultiplePick;
	private CircularImageView image;
	private String[] strings;
	private OnPostClickListener postClickListener;

	public PostAdapter(Context context, String[] strings,
			OnPostClickListener postClickListener) {
		this.strings = strings;
		this.postClickListener = postClickListener;

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
			convertView = infalter.inflate(R.layout.fragment_home, null);
			convertView.findViewById(R.id.btn_downVotes).setOnClickListener(
					this);
			convertView.findViewById(R.id.btn_upVotes).setOnClickListener(this);

			convertView.findViewById(R.id.btn_comments)
					.setOnClickListener(this);

			setTags(convertView);
		}

		convertView.setTag("" + position);

		((RadioButton) convertView.findViewById(R.id.radio_upvotes))
				.setOnCheckedChangeListener(null);
		((RadioButton) convertView.findViewById(R.id.radio_downvotes))
				.setOnCheckedChangeListener(null);
		setValuesToViews(position, convertView, parent);

		((RadioButton) convertView.findViewById(R.id.radio_upvotes))
				.setOnCheckedChangeListener(this);
		((RadioButton) convertView.findViewById(R.id.radio_downvotes))
				.setOnCheckedChangeListener(this);
		return convertView;

	}

	private void setTags(View convertView) {
		convertView.findViewById(R.id.btn_downVotes).setTag(convertView);
		convertView.findViewById(R.id.btn_upVotes).setTag(convertView);
		convertView.findViewById(R.id.btn_delete).setTag(convertView);
		convertView.findViewById(R.id.btn_comments).setTag(convertView);
		convertView.findViewById(R.id.radio_downvotes).setTag(convertView);
		convertView.findViewById(R.id.radio_upvotes).setTag(convertView);
	}

	private void setValuesToViews(int position, View convertView,
			ViewGroup parent) {

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
			image = (CircularImageView) convertView
					.findViewById(R.id.userImage);
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

	@Override
	public void onClick(View view) {
		View tag = (View) view.getTag();
		int position = Integer.parseInt(tag.getTag().toString());
		if (postClickListener != null) {
			postClickListener.onPostClick(this, tag, view, position);
		}
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		View tag = (View) buttonView.getTag();
		int position = Integer.parseInt(tag.getTag().toString());
		if (postClickListener != null) {
			postClickListener.onPostCheckedChanged(this, tag, position,
					buttonView, isChecked);
		}
	}

}
