package com.voxcast.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.voxcast.R;

public class GettingStartedFragment extends BaseFragment implements
		OnClickListener {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.getting_started_fragment,
				container, false);
		Button getting_started = (Button) view
				.findViewById(R.id.bt_getfrag_gettingstarted);
		getting_started.setOnClickListener(this);

		return view;
	}

	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public void onClick(View v) {
		replaceFragment(R.id.layout_frames, "", "Getting Started",
				new LoginFragment(), false, false);
	}
}
