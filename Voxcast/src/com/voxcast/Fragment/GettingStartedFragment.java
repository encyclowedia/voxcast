package com.voxcast.Fragment;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.voxcast.R;

public class GettingStartedFragment extends BaseFragment implements
		OnClickListener {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.started, container, false);
		 
		Button getting_started = (Button) view.findViewById(R.id.bt_getfrag_gettingstarted);
		getting_started.setOnClickListener(this);
		
		return view;
	}

	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public void onClick(View v) {
		
		replaceFragment("", "Getting Started", LoginFragment.class,
				false);
	}

}
