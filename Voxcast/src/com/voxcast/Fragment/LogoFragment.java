package com.voxcast.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.voxcast.R;

public class LogoFragment extends BaseFragment implements OnClickListener {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater
				.inflate(R.layout.fragment_splash, container, false);
		View relLogo = view.findViewById(R.id.relLogo);

		relLogo.setOnClickListener(this);

		return view;
	}

	@Override
	public void onClick(View v) {
		replaceFragment("", "Getting Started", gettingStartedFragment.class,
				false);
	}

}
