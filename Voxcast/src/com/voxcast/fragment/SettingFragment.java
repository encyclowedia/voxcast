package com.voxcast.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.facebook.Session;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;
import com.voxcast.R;
import com.voxcast.activity.MainActivity;
import com.voxcast.constant.Constant;
import com.voxcast.utilities.AppPreference;
import com.voxcast.utilities.Utils;

public class SettingFragment extends BaseFragment implements OnClickListener {

	private Button bt_settings_logout;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_settings, container,
				false);

		view.findViewById(R.id.tv_settings_arow).setOnClickListener(this);
		view.findViewById(R.id.bt_settings_about).setOnClickListener(this);

		view.findViewById(R.id.bt_settings_termscondition).setOnClickListener(
				this);

		bt_settings_logout = (Button) view
				.findViewById(R.id.bt_settings_logout);
		bt_settings_logout.setOnClickListener(this);

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
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		String loginresp = AppPreference.getInstance(getActivity())
				.isLoginResponse();
		if (loginresp != null && !loginresp.isEmpty()) {
			bt_settings_logout.setText("Logout");
		} else {
			bt_settings_logout.setText("Login");
		}

	}

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

		case R.id.bt_settings_logout:

			String lognType = AppPreference.getInstance(getActivity())
					.getLoginTyppe();
			if (lognType.equals("fb")) {
				Session session = Session.getActiveSession();
				if (session == null) {
					session = Session.openActiveSessionFromCache(getActivity());
				}

				if (session != null && !session.isClosed()) {
					session.closeAndClearTokenInformation();
				}

			} else if (lognType.equals("gp")) {

				GoogleApiClient mGoogleApiClient = Utils.getmGoogleApiClient();
				if (mGoogleApiClient != null) {
					if (mGoogleApiClient.isConnected()) {
						Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
						mGoogleApiClient.disconnect();
						mGoogleApiClient.connect();
						AppPreference.getInstance(getActivity()).clearGptoken();
					}
				}

			}

			AppPreference.getInstance(getActivity()).setLogin(false);
			Intent intent = new Intent(getActivity(), MainActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			break;
		}

	}

}
