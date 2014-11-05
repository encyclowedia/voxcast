package com.voxcast.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.voxcast.R;
import com.voxcast.fragment.BaseFragment;
import com.voxcast.fragment.MyProfileFragment;

public class SettingsActivity extends BaseFragment implements OnClickListener {

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
			System.out.println("aaaaaaaaaa  ");

			replaceFragment(android.R.id.tabcontent, "profile", "Settings",
					new MyProfileFragment(), false , false);
			// getActivity().finish();

			break;
		case R.id.bt_settings_about:

			replaceFragment(R.id.overlayFragmentContainer, "Settings",
					"MyProfile", new AboutActivity(), false, false);
			/*
			 * Intent i = new Intent(SettingsActivity.this,
			 * AboutActivity.class); startActivity(i);
			 */

			break;
		case R.id.bt_settings_termscondition:

			replaceFragment(R.id.overlayFragmentContainer, "Settings",
					"MyProfile", new AboutActivity(), false, false);

			/*
			 * i = new Intent(SettingsActivity.this,
			 * TermsAndConditionsActivity.class); startActivity(i);
			 */

			break;

		default:
			break;
		}

	}

}
