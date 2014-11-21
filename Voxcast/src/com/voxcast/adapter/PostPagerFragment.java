package com.voxcast.adapter;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.voxcast.R;
import com.voxcast.fragment.BaseFragment;
import com.voxcast.listeners.OnPostClickListener;
import com.voxcast.model.Poster;
import com.voxcast.model.Result;

public class PostPagerFragment extends FragmentStatePagerAdapter {

	private String[] strings;

	private ArrayList<Result> results;
	private ArrayList<PostFragment> arrayList = new ArrayList<PostPagerFragment.PostFragment>();

	public PostPagerFragment(FragmentManager fm, ArrayList<Result> strings) {
		super(fm);
		this.results = strings;
	}

	@Override
	public Fragment getItem(int arg0) {
		return PostFragment.instanceOf(results, arg0);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return results.size();
	}

	public static class PostFragment extends BaseFragment implements
			OnClickListener, OnCheckedChangeListener {

		private static final String KEY_POSTION = "postion";
		private static final String KEY_ARRAY = "array";
		private OnPostClickListener<Result> postClickListener;

		public static PostFragment instanceOf(ArrayList<Result> arrayList,
				int postion) {
			PostFragment postFragment = new PostFragment();
			Bundle bundle = new Bundle();
			bundle.putParcelableArrayList(KEY_ARRAY, arrayList);
			bundle.putInt(KEY_POSTION, postion);

			postFragment.setArguments(bundle);
			return postFragment;
		}

		private ArrayList<Result> result;
		private int position;

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			Bundle arguments = getArguments();
			if (arguments != null) {
				position = arguments.getInt(KEY_POSTION);
				result = arguments.getParcelableArrayList(KEY_ARRAY);
				if (getActivity() instanceof OnPostClickListener)
					postClickListener = (OnPostClickListener<Result>) getActivity();
			}
		}

		@Override
		public View onCreateView(LayoutInflater inflater,
				@Nullable ViewGroup container,
				@Nullable Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			ArrayList<String> comment_list = new ArrayList<String>();
			comment_list.add("a");
			comment_list.add("a");
			comment_list.add("a");
			comment_list.add("a");
			View view = inflater.inflate(R.layout.layout_header_pager_land,
					null);
			ListView ListView = (ListView) view.findViewById(R.id.list);

			CommentAdapter adapter = new CommentAdapter(getActivity(),
					comment_list);
			ListView.setAdapter(adapter);

			return view;
		}

		@Override
		public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
			super.onViewCreated(view, savedInstanceState);
			if (result == null) {
				return;
			}

			setTags(view);
			setListenerToViews(view);

			ImageView userImage = (ImageView) view.findViewById(R.id.userImage);

			TextView txt_name = (TextView) view.findViewById(R.id.userName);
			txt_name.setText(result.get(position).getPoster().getName());

			TextView txt_msg = (TextView) view.findViewById(R.id.userMessage);
			txt_msg.setText(result.get(position).getMsg());

			TextView txt_time = (TextView) view.findViewById(R.id.userTime);
			txt_time.setText(result.get(position).getTs());

			int size = result.get(position).getVid().size();
			ViewGroup group = (ViewGroup) view
					.findViewById(R.id.videoThumbnailLayout);
			if (size > 0) {
				group.setVisibility(View.VISIBLE);
				ImageView videoThumb = (ImageView) view
						.findViewById(R.id.videoThumbnail);
				ImageLoader.getInstance().displayImage(
						result.get(position).getVid().get(0), videoThumb);

			} else {
				group.setVisibility(View.GONE);
			}

			ViewGroup imageLayout = (ViewGroup) view.findViewById(R.id.layout);
			int index = 0;
			for (int i = 0; i < imageLayout.getChildCount(); i++) {
				View childAt = imageLayout.getChildAt(i);
				if (childAt instanceof ImageView) {
					if (result.get(position).getPics().size() <= index)
						childAt.setVisibility(View.GONE);
					else {
						childAt.setVisibility(View.VISIBLE);
						ImageLoader.getInstance().displayImage(
								result.get(position).getPics().get(index),
								((ImageView) childAt));
					}
					index++;
				}
			}

			TextView txt_location = (TextView) view
					.findViewById(R.id.txt_location);
			txt_location.setText(result.get(position).getCity());

			RadioButton rb_downVotes = (RadioButton) view
					.findViewById(R.id.radio_downvotes);
			rb_downVotes.setText(result.get(position).getDownVoters().size()
					+ "");

			Poster poster = new Poster("" + 1, "", "Schnider Rose");
			if (result.get(position).getDownVoters().contains(poster)) {
				rb_downVotes.setChecked(true);
			}

			RadioButton rb_upvotes = (RadioButton) view
					.findViewById(R.id.radio_upvotes);
			rb_upvotes.setText(result.get(position).getUpVoters().size() + "");
			if (result.get(position).getUpVoters().contains(poster)) {
				rb_upvotes.setChecked(true);
			}

			TextView txt_downvotes = (TextView) view
					.findViewById(R.id.btn_downVotes);
			txt_downvotes.setText(result.get(position).getDownVoters().size()
					+ " downvotes");

			TextView txt_upvotes = (TextView) view
					.findViewById(R.id.btn_upVotes);
			txt_upvotes.setText(result.get(position).getUpVoters().size()
					+ " upvotes");
			TextView txt_comments = (TextView) view
					.findViewById(R.id.btn_comments);
			txt_comments
					.setText(result.get(position).getComments().size() + "");
			setListenerToRadio(view, this);
		}

		private void setListenerToRadio(View convertView,
				OnCheckedChangeListener changeListener) {
			((RadioButton) convertView.findViewById(R.id.radio_upvotes))
					.setOnCheckedChangeListener(changeListener);
			((RadioButton) convertView.findViewById(R.id.radio_downvotes))
					.setOnCheckedChangeListener(changeListener);
		}

		private void setListenerToViews(View convertView) {
			convertView.findViewById(R.id.userImage).setOnClickListener(this);
			convertView.findViewById(R.id.btn_downVotes).setOnClickListener(
					this);
			convertView.findViewById(R.id.btn_upVotes).setOnClickListener(this);

			convertView.findViewById(R.id.btn_comments)
					.setOnClickListener(this);

			convertView.findViewById(R.id.btn_delete).setOnClickListener(this);

			convertView.findViewById(R.id.imageThumbnail1).setOnClickListener(
					this);
			convertView.findViewById(R.id.imageThumbnail2).setOnClickListener(
					this);
			convertView.findViewById(R.id.imageThumbnail3).setOnClickListener(
					this);
			convertView.findViewById(R.id.videoThumbnailLayout)
					.setOnClickListener(this);
		}

		private void setTags(View convertView) {
			convertView.findViewById(R.id.userImage).setTag(convertView);
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
			convertView.findViewById(R.id.videoThumbnailLayout).setTag(
					convertView);
		}

		@Override
		public void onClick(View view) {
			View tag = (View) view.getTag();
			if (postClickListener != null) {
				postClickListener.onPostClick(result, tag, view, position);
			}
		}

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			View tag = (View) buttonView.getTag();
			if (postClickListener != null) {
				postClickListener.onPostCheckedChanged(result, tag, position,
						buttonView, isChecked);
			}
		}
	}

}
