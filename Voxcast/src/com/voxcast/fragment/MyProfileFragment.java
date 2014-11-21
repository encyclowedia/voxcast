package com.voxcast.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.voxcast.R;
import com.voxcast.activity.HomeActivity;
import com.voxcast.constant.Constant;
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
		((HomeActivity) getActivity()).setOrientationEventListener(null);
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

		settext("Schnider Rose");
	}

	private void settext(String text) {
		View view = getView();

		if (view == null) {
			return;
		}
		TextView userName = (TextView) view.findViewById(R.id.txt_userName);
		userName.setText(text);

		userName = (TextView) view.findViewById(R.id.txt_userName1);
		userName.setText(text);

		userName = (TextView) view.findViewById(R.id.txt_userName2);
		userName.setText(text);

		userName = (TextView) view.findViewById(R.id.txt_userName3);
		userName.setText(text);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ib_myprofile_settings_icon:
			replaceFragment(R.id.overlayFragmentContainer,
					Constant.FRAGMENT_MYPROFILE, Constant.FRAGMENT_SETTING,
					new SettingFragment(), true, false);
			break;
		}

	}
}
