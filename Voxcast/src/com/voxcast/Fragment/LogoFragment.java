package com.voxcast.Fragment;

import com.voxcast.R;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class LogoFragment extends BaseFragment implements OnClickListener {

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.logo, container, false);
		View relLogo = view.findViewById(R.id.relLogo);
	
	
		relLogo.setOnClickListener(this);
	
		
		return view;
	}

	@Override
	public void onClick(View v) {	
		replaceFragment("", "Getting Started", gettingStartedFragment.class, false);
		}

}
