package com.voxcast.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.voxcast.R;
import com.voxcast.activity.HomeActivity;
import com.voxcast.constant.Constant;

public class SettingFragment extends BaseFragment implements OnClickListener {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.settings_activity, container,
				false);

		view.findViewById(R.id.tv_settings_arow).setOnClickListener(this);
		view.findViewById(R.id.bt_settings_about).setOnClickListener(this);

		view.findViewById(R.id.bt_settings_termscondition).setOnClickListener(
				this);

		return view;
	}

	/*
	 * @Override public void onCreate(Bundle savedInstanceState) {
	 * super.onCreate(savedInstanceState);
	 * 
	 * setContentView(R.layout.settings_activity);
	 * 
	 * findViewById(R.id.tv_settings_arow).setOnClickListener(this);
	 * findViewById(R.id.bt_settings_about).setOnClickListener(this);
	 * findViewById(R.id.bt_settings_termscondition).setOnClickListener(this);
	 * 
	 * }
	 */

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_settings_arow:
			popCurrentFragmentOut();
			break;
		case R.id.bt_settings_about:

			replaceFragment(R.id.overlayFragmentContainer,
					Constant.FRAGMENT_SETTING, Constant.FRAGMENT_ABOUTUS,
					new AboutUsFragment(), true, false);

			break;
		case R.id.bt_settings_termscondition:

			replaceFragment(R.id.overlayFragmentContainer,
					Constant.FRAGMENT_SETTING, Constant.FRAGMENT_ABOUTUS,
					new AboutUsFragment(), true, false);

			break;

		default:
			break;
		}

	}

}
