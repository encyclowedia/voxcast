package com.voxcast.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.google.android.gms.internal.go;
import com.google.gson.Gson;
import com.voxcast.R;
import com.voxcast.activity.HomeActivity;

import com.voxcast.constant.Constant;
import com.voxcast.model.LoginResponse;
import com.voxcast.utilities.AppPreference;

public class LogoFragment extends BaseFragment /* implements OnClickListener */{
	private String loginresp;
	private View relLogo;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater
				.inflate(R.layout.fragment_splash, container, false);
		relLogo=view.findViewById(R.id.relLogo);
		loginresp = AppPreference.getInstance(getActivity()).isLoginResponse();
		// relLogo.setOnClickListener(this);
		return view;
		
	}

	@Override
	public void onResume() {
		super.onResume();
//		if (!AppPreference.getInstance(getActivity()).isLogin()) {
//		
//			Fragment fragment;
//			if (loginresp != null && !loginresp.isEmpty()) {
//				fragment = new LogoutFragment();
//			} else {
//				fragment = new LoginFragment();
//			}
//			replaceFragment(R.id.layout_frames, "", "Getting Started",
//					fragment, false, true);
//			
//		} else {
//			
//			
//		
//		}
		relLogo.setVisibility(View.VISIBLE);
		timeSplash(Constant.SPLASH_DISPLAY_TIME);

	}

	private void timeSplash(int splashDisplayTime) {

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {

				DisplayLoginFragment();

			}

		}, splashDisplayTime);
		
	}

	/*
	 * @Override public void onClick(View v) { replaceFragment("",
	 * "Getting Started", new GettingStartedFragment(), false); }
	 */

	protected void DisplayLoginFragment() {
		// TODO Auto-generated method stub

		try {
			Fragment fragment = null;
			if (AppPreference.getInstance(getActivity())
					.isGetStartedScreenShown()) {

				if (AppPreference.getInstance(getActivity()).isLogin()) {

					getActivity().finish();

					Intent intent = new Intent(getActivity(),
							HomeActivity.class);
					startActivity(intent);
				} else {

					if (loginresp != null && !loginresp.isEmpty()) {
						fragment = new LogoutFragment();
					} else {
						fragment = new LoginFragment();
					}

				}

			} else {

				fragment = new GettingStartedFragment();
				AppPreference.getInstance(getActivity())
						.setGetStartedScreenShown();
			}

			if (!getActivity().isFinishing()) {
				replaceFragment(R.id.layout_frames, "", "Getting Started",
						fragment, false, true);
			}
		} catch (Exception e) {

		}

	}
}
