package com.voxcast.fragment;

import java.util.ArrayList;
import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.voxcast.R;
import com.voxcast.activity.BaseActivity;
import com.voxcast.activity.HomeActivity;
import com.voxcast.activity.HomeLandActivity;
import com.voxcast.activity.VotesActivity;
import com.voxcast.adapter.PostAdapter;
import com.voxcast.listeners.OnDataChangeListener;
import com.voxcast.listeners.OnPostClickListener;
import com.voxcast.listeners.OnScrollListener;
import com.voxcast.model.Poster;
import com.voxcast.model.Result;
import com.voxcast.view.CircularImageView;

public class HomeFragment extends BaseFragment implements
		OnPostClickListener<Result>, OnDataChangeListener {
	// Button fragment1Btn;
	private View homeFragmentView;
	Context context;
	private CircularImageView homeScrnProfImg;
	private OrientationEventListener eventListener;
	private StickyListHeadersListView list;
	private Activity activity;

	public HomeFragment() {
		// as per Android Fragment documentation, here is an empty constructor,
		// so it can be instantiated when restoring its activity's state
	}

	private boolean isTriggered;
	private int pos;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getActivity() != null) {
			activity = getActivity();
		}

		Bundle arguments = getArguments();
		pos = arguments.getInt("pos");
		System.out.println("HomeFragment.onCreate() ---" + pos);

		eventListener = new OrientationEventListener(getActivity(),
				SensorManager.SENSOR_DELAY_NORMAL) {

			@Override
			public void onOrientationChanged(int orientation) {
				if (isTriggered) {
					return;
				}

				if ((orientation > 75 && orientation < 115)
						|| (orientation < 285 && orientation > 255)) {
					isTriggered = true;
					Intent intent = new Intent(activity, HomeLandActivity.class);

					if (list != null && isAdded()) {
						intent.putParcelableArrayListExtra("LIST",
								((HomeActivity) getActivity()).getArrayList());
						intent.putExtra("POSITION",
								list.getFirstVisiblePosition());
						startActivityForResult(intent, 1);
					}
				}

			}
		};

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == Activity.RESULT_OK) {

			if (list == null) {
				return;
			}
			if (data == null) {
				return;
			}
			list.setSelection(data.getIntExtra("POSITION", 0));
		}
	}

	public static HomeFragment newInstance(int pos) {
		HomeFragment homeFragment = new HomeFragment();
		Bundle args = new Bundle();
		args.putInt("pos", pos);
		homeFragment.setArguments(args);
		return homeFragment;
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		System.out.println("HomeFragment.setUserVisibleHint()"
				+ isVisibleToUser);
		if (!isVisibleToUser) {
			if (eventListener != null)
				eventListener.disable();
		} else {
			if (isAdded()) {
				if (eventListener != null) {
					eventListener.enable();
					((HomeActivity) getActivity())
							.setOrientationEventListener(eventListener);
				}
			}

			if (getOnDataChangeListener() == null
					|| !getOnDataChangeListener().equals(this)) {
				setOnDataChangeListener(this);
			}

			if (list != null) {

				PostAdapter postAdapter = (PostAdapter) list.getAdapter();
				if (postAdapter != null)
					postAdapter.notifyDataSetChanged();
			}
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		if (eventListener != null) {
			eventListener.enable();
			isTriggered = false;
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Each row in the list stores country name, currency and flag
		homeFragmentView = inflater.inflate(R.layout.mypost, container, false);

		list = (StickyListHeadersListView) homeFragmentView
				.findViewById(R.id.list);
		((BaseActivity) getActivity()).setListView(list);
		setListenerToListView();
		// strings = new String[] { "a", "b", "a", "c", "d", "c" };

		PostAdapter ga = new PostAdapter(getActivity(),
				((HomeActivity) getActivity()).getArrayList(), this);
		list.setAdapter(ga);
		return homeFragmentView;
	}

	private int top;

	private void setListenerToListView() {
		OnScrollListener listener = (OnScrollListener) list
				.getOnScrollListener();
		listener.setCustomListener(new AbsListView.OnScrollListener() {

			private View childAt;

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {

			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				if (childAt == null) {
					childAt = view.getChildAt(0);
				}
				if (childAt == null) {
					return;
				}

				int top = childAt.getTop();

				int diff = HomeFragment.this.top - top;

				if (diff < 0 && Math.abs(diff) < childAt.getHeight() / 2) {
					((HomeActivity) getActivity()).makeBarVisible();
				} else if (diff > 0 && Math.abs(diff) < childAt.getHeight() / 2) {
					((HomeActivity) getActivity()).makeBarInVisible();
				}

				HomeFragment.this.top = top;

				// if(top<)

			}

		});
	}

	@Override
	public void onPostCheckedChanged(ArrayList<Result> adapter,
			View convertView, int position, CompoundButton buttonView,
			boolean isChecked) {

		RadioButton downVotes = (RadioButton) convertView
				.findViewById(R.id.radio_downvotes);
		RadioButton upVotes = (RadioButton) convertView
				.findViewById(R.id.radio_upvotes);

		TextView txt_downVotes = (TextView) convertView
				.findViewById(R.id.btn_downVotes);
		TextView txt_upVotes = (TextView) convertView
				.findViewById(R.id.btn_upVotes);

		// TODO Change Below Line with Logged in User data.
		Poster poster = new Poster("" + 1, "", "Schnider Rose");
		ArrayList<Poster> upVoters = adapter.get(position).getUpVoters();
		ArrayList<Poster> downVoters = adapter.get(position).getDownVoters();

		switch (buttonView.getId()) {
		case R.id.radio_downvotes:
			if (!isChecked)
				break;
			int index = downVoters.indexOf(poster);
			if (index == -1) {
				downVoters.add(poster);
				upVotes.setChecked(false);
				index = upVoters.indexOf(poster);
				if (index != -1) {
					upVoters.remove(index);
				}
			}
			break;
		case R.id.radio_upvotes:
			if (!isChecked)
				break;
			index = upVoters.indexOf(poster);
			if (index == -1) {
				upVoters.add(poster);
				downVotes.setChecked(false);
				index = downVoters.indexOf(poster);
				if (index != -1) {
					downVoters.remove(index);
				}
			}
		}

		downVotes.setText(downVoters.size() + "");
		txt_downVotes.setText(downVoters.size() + " downvotes");
		upVotes.setText(upVoters.size() + "");
		txt_upVotes.setText(upVoters.size() + " upvotes");

	}

	@Override
	public void onPostClick(ArrayList<Result> adapter, View convertView,
			View view, int position) {
		switch (view.getId()) {
		case R.id.btn_upVotes:
			Result item = adapter.get(position);
			ArrayList<Poster> upVoters = item.getUpVoters();
			Intent intent = new Intent(getActivity(), VotesActivity.class);
			intent.putParcelableArrayListExtra(VotesActivity.KEY_VOTES,
					upVoters);
			intent.putExtra(VotesActivity.KEY_HEADER, "Up Votes");
			startActivity(intent);
			getActivity().overridePendingTransition(R.anim.animation_scaleout,
					R.anim.animation_scalein);
			break;

		case R.id.btn_downVotes:
			item = adapter.get(position);
			ArrayList<Poster> downVoters = item.getDownVoters();
			intent = new Intent(getActivity(), VotesActivity.class);
			intent.putParcelableArrayListExtra(VotesActivity.KEY_VOTES,
					downVoters);
			intent.putExtra(VotesActivity.KEY_HEADER, "Down Votes");
			startActivity(intent);
			getActivity().overridePendingTransition(R.anim.animation_scaleout,
					R.anim.animation_scalein);
			break;

		case R.id.imageThumbnail1:
			replaceFragment(
					R.id.overlayFragmentContainer,
					((HomeActivity) getActivity()).getPreviousTag(),
					"IMAGEVIEWER",
					ImageViewerFragment.getInstanceOf(adapter.get(position)
							.getPics().get(0), false), R.anim.abc_fade_in, 0,
					0, R.anim.abc_fade_out, true, false);
			break;
		case R.id.imageThumbnail2:
			replaceFragment(
					R.id.overlayFragmentContainer,
					((HomeActivity) getActivity()).getPreviousTag(),
					"IMAGEVIEWER",
					ImageViewerFragment.getInstanceOf(adapter.get(position)
							.getPics().get(1), false), R.anim.abc_fade_in, 0,
					0, R.anim.abc_fade_out, true, false);
			break;
		case R.id.imageThumbnail3:
			replaceFragment(
					R.id.overlayFragmentContainer,
					((HomeActivity) getActivity()).getPreviousTag(),
					"IMAGEVIEWER",
					ImageViewerFragment.getInstanceOf(adapter.get(position)
							.getPics().get(2), false), R.anim.abc_fade_in, 0,
					0, R.anim.abc_fade_out, true, false);

			break;

		case R.id.videoThumbnailLayout:
			replaceFragment(
					R.id.overlayFragmentContainer,
					((HomeActivity) getActivity()).getPreviousTag(),
					"IMAGEVIEWER",
					ImageViewerFragment.getInstanceOf(adapter.get(position)
							.getVid().get(0), true), R.anim.abc_fade_in, 0, 0,
					R.anim.abc_fade_out, true, false);

		}

	}

	@Override
	public void refreshData() {
		System.out.println("HomeFragment.refreshData()----" + pos);
		if (list != null) {
			PostAdapter postAdapter = (PostAdapter) list.getAdapter();
			if (postAdapter != null) {
				postAdapter.notifyDataSetChanged();
			}
			list.requestLayout();
		}
	}

}
