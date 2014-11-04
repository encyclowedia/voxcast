package com.voxcast.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.voxcast.R;
import com.voxcast.activity.SettingsActivity;

public class MyProfileFragment extends BaseFragment implements OnClickListener {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.my_profile_fragment, null);

		view.findViewById(R.id.ib_myprofile_settings_icon).setOnClickListener(
				this);
		return view;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ib_myprofile_settings_icon:
			Intent i = new Intent(getActivity(), SettingsActivity.class);
			startActivity(i);

			break;

		default:
			break;
		}

	}

}
