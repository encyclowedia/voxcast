package com.voxcast.fragment;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.SensorManager;
import android.net.rtp.RtpStream;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;

import com.voxcast.R;
import com.voxcast.activity.BaseActivity;
import com.voxcast.activity.HomeLandActivity;
import com.voxcast.adapter.PostAdapter;
import com.voxcast.view.CircularImageView;

public class HomeFragment extends BaseFragment {
	// Button fragment1Btn;
	private View homeFragmentView;
	Context context;
	private CircularImageView homeScrnProfImg;
	private OrientationEventListener eventListener;
	private StickyListHeadersListView list;
	private Activity activity;
	private String[] strings;

	public HomeFragment() {
		// as per Android Fragment documentation, here is an empty constructor,
		// so it can be instantiated when restoring its activity's state
	}

	private boolean isTriggered;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getActivity() != null) {
			activity = getActivity();
		}
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
					intent.putExtra("LIST", strings);
					if (list != null) {
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

	public static HomeFragment newInstance() {
		return new HomeFragment();
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		if (!isVisibleToUser) {
			if (eventListener != null)
				eventListener.disable();
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
		strings = new String[] { "a", "b", "a", "c", "d", "c" };

		PostAdapter ga = new PostAdapter(getActivity(), strings);
		list.setAdapter(ga);
		return homeFragmentView;
	}

}
