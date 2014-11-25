package com.voxcast.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.voxcast.R;
import com.voxcast.model.LoginResponse;
import com.voxcast.utilities.AppPreference;
import com.voxcast.utilities.Utils;
import com.voxcast.view.CircularImageView;

public class LogoutFragment extends BaseFragment
/* implements OnClickListener */implements OnClickListener {
	private TextView continuetext;
	private ImageView logintype_image;
	private String loginType;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_logout, container, false);

		View list_image = (CircularImageView) view
				.findViewById(R.id.list_image);
		View logoutbtn = view.findViewById(R.id.logoutbtn);
		continuetext = (TextView) view.findViewById(R.id.continuetext);

		logintype_image = (ImageView) view.findViewById(R.id.logintype);
		loginType = AppPreference.getInstance(getActivity()).getLoginTyppe();
		if (loginType.equals("fb")) {
			logintype_image.setImageResource(R.drawable.fbsmall);
		} else if (loginType.equals("gp")) {
			logintype_image.setImageResource(R.drawable.gsmall);
		} else {
			logintype_image.setImageResource(R.drawable.lnsmall);
		}
		logoutbtn.setOnClickListener(this);
		continuetext.setOnClickListener(this);
		LoginResponse userinfo = Utils.getUserData(getActivity());

		continuetext.setText("Continue as " + userinfo.getResult().getEml());

		return view;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		replaceFragment(R.id.layout_frames, "", "LoginFragment",
				new LoginFragment(), false, true);
		switch (v.getId()) {

		case R.id.logoutbtn:

			AppPreference.getInstance(getActivity()).clearUserData();
			break;
		case R.id.continuetext:

			// if (lognType.equals("fb")) {
			// ((MainActivity) getActivity()).startfacebooklogin();
			// }else if (lognType.equals("gp")) {
			// // ((MainActivity) getActivity()).signInWithGplus();
			// }else{
			//
			// }
			break;
		default:
			break;
		}

	}

	/*
	 * @Override public void onClick(View v) { replaceFragment("",
	 * "Getting Started", new GettingStartedFragment(), false); }
	 */
}
