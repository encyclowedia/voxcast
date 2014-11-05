package com.voxcast.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;

import com.voxcast.R;
import com.voxcast.fragment.BaseFragment;
import com.voxcast.fragment.MyProfileFragment;

public class AboutActivity extends BaseFragment implements OnClickListener {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.about_activity, container, false);

		view.findViewById(R.id.tv_settings_arow).setOnClickListener(this);
		return view;
	}

	/*
	 * @Override public void onCreate(Bundle savedInstanceState) {
	 * super.onCreate(savedInstanceState);
	 * 
	 * setContentView(R.layout.about_activity);
	 * 
	 * findViewById(R.id.tv_settings_arow).setOnClickListener(this);
	 * 
	 * }
	 */

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_settings_arow:
			replaceFragment(R.id.overlayFragmentContainer, "Settings",
					"MyProfile", new SettingsActivity(), false, false);

			break;

		default:
			break;
		}

	}
}
