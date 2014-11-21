package com.voxcast.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.voxcast.R;
import com.voxcast.constant.Constant;
import com.voxcast.utilities.AppPreference;

public class LogoFragment extends BaseFragment /* implements OnClickListener */{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater
				.inflate(R.layout.fragment_splash, container, false);
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		timeSplash();
	}

	private void timeSplash() {

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {

				try {
					Fragment fragment = null;
					if (AppPreference.getInstance(getActivity())
							.isGetStartedScreenShown()) {
						fragment = new LoginFragment();
					} else {
						fragment = new GettingStartedFragment();
						AppPreference.getInstance(getActivity())
								.setGetStartedScreenShown();
					}
					if (!getActivity().isFinishing()) {
						replaceFragment(R.id.layout_frames, "",
								"Getting Started", fragment, false, true);
					}
				} catch (Exception e) {

				}

			}

		}, Constant.SPLASH_DISPLAY_TIME);
	}
	/*
	 * @Override public void onClick(View v) { replaceFragment("",
	 * "Getting Started", new GettingStartedFragment(), false); }
	 */
}
