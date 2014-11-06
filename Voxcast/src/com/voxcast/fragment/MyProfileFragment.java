package com.voxcast.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.voxcast.R;
import com.voxcast.activity.SettingsActivity;
import com.voxcast.utilities.RoundAnimation;
import com.voxcast.view.CircularProgressView;

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
	public void onResume() {
		super.onResume();

	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		CircularProgressView circularProgressView = (CircularProgressView) view
				.findViewById(R.id.cpv_1);
		RoundAnimation animation = new RoundAnimation(circularProgressView, 50);
		animation.setDuration(1000);
		circularProgressView.setAnimation(animation);

		circularProgressView = (CircularProgressView) view
				.findViewById(R.id.cpv_2);
		animation = new RoundAnimation(circularProgressView, 60);
		animation.setDuration(1000);
		circularProgressView.setAnimation(animation);

		circularProgressView = (CircularProgressView) view
				.findViewById(R.id.cpv_3);
		animation = new RoundAnimation(circularProgressView, 70);
		animation.setDuration(1000);
		circularProgressView.setAnimation(animation);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ib_myprofile_settings_icon:
			/*
			 * Intent i = new Intent(getActivity(), SettingsActivity.class);
			 * startActivity(i);
			 */

			replaceFragment(R.id.overlayFragmentContainer, "profile",
					"Settings", new SettingsActivity(), true, false);
			break;

		default:
			break;
		}

	}
}
