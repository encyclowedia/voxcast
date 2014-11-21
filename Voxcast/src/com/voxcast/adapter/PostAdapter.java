package com.voxcast.adapter;

import java.util.ArrayList;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.voxcast.R;
import com.voxcast.activity.HomeActivity;
import com.voxcast.listeners.OnPostClickListener;
import com.voxcast.model.Poster;
import com.voxcast.model.Result;
import com.voxcast.view.CircularImageView;

public class PostAdapter extends BaseAdapter implements
		StickyListHeadersAdapter, OnClickListener, OnCheckedChangeListener {

	private Context mContext;
	private LayoutInflater infalter;

	private boolean isActionMultiplePick;
	private CircularImageView image;
	private ArrayList<Result> arrayList;
	private OnPostClickListener<Result> postClickListener;

	public PostAdapter(Context context, ArrayList<Result> arrayList,
			OnPostClickListener<Result> postClickListener) {
		this.arrayList = arrayList;
		this.postClickListener = postClickListener;

		infalter = LayoutInflater.from(context);
		this.mContext = context;
	}

	@Override
	public int getCount() {
		return arrayList.size();
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
			setListenerToViews(convertView);
			setTags(convertView);
		}

		convertView.setTag("" + position);

		setListenerToRadio(convertView, null);
		setValuesToViews(position, convertView, parent);
		setListenerToRadio(convertView, this);
		return convertView;

	}

	private void setListenerToRadio(View convertView,
			OnCheckedChangeListener changeListener) {
		((RadioButton) convertView.findViewById(R.id.radio_upvotes))
				.setOnCheckedChangeListener(changeListener);
		((RadioButton) convertView.findViewById(R.id.radio_downvotes))
				.setOnCheckedChangeListener(changeListener);
	}

	private void setListenerToViews(View convertView) {
		convertView.findViewById(R.id.btn_downVotes).setOnClickListener(this);
		convertView.findViewById(R.id.btn_upVotes).setOnClickListener(this);

		convertView.findViewById(R.id.btn_comments).setOnClickListener(this);

		convertView.findViewById(R.id.btn_delete).setOnClickListener(this);

		convertView.findViewById(R.id.imageThumbnail1).setOnClickListener(this);
		convertView.findViewById(R.id.imageThumbnail2).setOnClickListener(this);
		convertView.findViewById(R.id.imageThumbnail3).setOnClickListener(this);
		convertView.findViewById(R.id.videoThumbnailLayout).setOnClickListener(
				this);
	}

	private void setTags(View convertView) {
		convertView.findViewById(R.id.btn_downVotes).setTag(convertView);
		convertView.findViewById(R.id.btn_upVotes).setTag(convertView);
		convertView.findViewById(R.id.btn_delete).setTag(convertView);
		convertView.findViewById(R.id.btn_comments).setTag(convertView);
		convertView.findViewById(R.id.radio_downvotes).setTag(convertView);
		convertView.findViewById(R.id.radio_upvotes).setTag(convertView);
		convertView.findViewById(R.id.btn_delete).setTag(convertView);

		convertView.findViewById(R.id.imageThumbnail1).setTag(convertView);
		convertView.findViewById(R.id.imageThumbnail2).setTag(convertView);
		convertView.findViewById(R.id.imageThumbnail3).setTag(convertView);
		convertView.findViewById(R.id.videoThumbnailLayout).setTag(convertView);
	}

	private void setValuesToViews(int position, View convertView,
			ViewGroup parent) {

		TextView txt_UserName = (TextView) convertView
				.findViewById(R.id.userName);
		Result result = arrayList.get(position);
		txt_UserName.setText(result.getPoster().getName());

		TextView txt_UserMsg = (TextView) convertView
				.findViewById(R.id.userMessage);
		txt_UserMsg.setText(result.getMsg());

		TextView txt_UserTime = (TextView) convertView
				.findViewById(R.id.userTime);
		txt_UserTime.setText(result.getTs());

		int size = result.getVid().size();
		ViewGroup group = (ViewGroup) convertView
				.findViewById(R.id.videoThumbnailLayout);
		if (size > 0) {
			group.setVisibility(View.VISIBLE);
			ImageView videoThumb = (ImageView) convertView
					.findViewById(R.id.videoThumbnail);
			ImageLoader.getInstance().displayImage(result.getVid().get(0),
					videoThumb, HomeActivity.getOptions());

		} else {
			group.setVisibility(View.GONE);
		}

		ViewGroup imageLayout = (ViewGroup) convertView
				.findViewById(R.id.layout);
		int index = 0;
		for (int i = 0; i < imageLayout.getChildCount(); i++) {
			View childAt = imageLayout.getChildAt(i);
			if (childAt instanceof ImageView) {
				if (result.getPics().size() <= index)
					childAt.setVisibility(View.GONE);
				else {
					childAt.setVisibility(View.VISIBLE);
					ImageLoader.getInstance().displayImage(
							result.getPics().get(index), ((ImageView) childAt));
				}
				index++;
			}
		}

		ImageView imageThumb1 = (ImageView) convertView
				.findViewById(R.id.imageThumbnail1);

		ImageView imageThumb2 = (ImageView) convertView
				.findViewById(R.id.imageThumbnail2);
		ImageView imageThumb3 = (ImageView) convertView
				.findViewById(R.id.imageThumbnail3);

		TextView txt_location = (TextView) convertView
				.findViewById(R.id.txt_location);
		txt_location.setText(result.getCity());

		RadioButton rb_downVotes = (RadioButton) convertView
				.findViewById(R.id.radio_downvotes);
		rb_downVotes.setText(result.getDownVoters().size() + "");
		rb_downVotes.setChecked(false);
		Poster poster = new Poster("" + 1, "", "Schnider Rose");
		if (result.getDownVoters().contains(poster)) {
			rb_downVotes.setChecked(true);
		}

		RadioButton rb_upvotes = (RadioButton) convertView
				.findViewById(R.id.radio_upvotes);
		rb_upvotes.setText(result.getUpVoters().size() + "");
		rb_upvotes.setChecked(false);
		if (result.getUpVoters().contains(poster)) {
			rb_upvotes.setChecked(true);
		}

		TextView btn_downVotes = (TextView) convertView
				.findViewById(R.id.btn_downVotes);
		btn_downVotes.setText(result.getDownVoters().size() + " downvotes");

		TextView btn_upVotes = (TextView) convertView
				.findViewById(R.id.btn_upVotes);
		btn_upVotes.setText(result.getUpVoters().size() + " upvotes");

		TextView btn_comments = (TextView) convertView
				.findViewById(R.id.btn_comments);
		btn_comments.setText(result.getComments().size() + "");

	}

	@Override
	public Result getItem(int position) {
		// TODO Auto-generated method stub
		return arrayList.get(position);
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
			postClickListener.onPostClick(arrayList, tag, view, position);
		}
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		View tag = (View) buttonView.getTag();
		int position = Integer.parseInt(tag.getTag().toString());
		if (postClickListener != null) {
			postClickListener.onPostCheckedChanged(arrayList, tag, position,
					buttonView, isChecked);
		}
	}

}
