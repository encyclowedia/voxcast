package com.voxcast.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.voxcast.R;

public class Fragment1 extends BaseFragment {

	private View fragment1View;
	// Button homeFragmentBtn;
	Context context;

	public Fragment1() {

	}

	public static Fragment1 newInstance() {
		return new Fragment1();
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		context = getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// if (fragment1View == null)
		fragment1View = inflater.inflate(R.layout.fragment_1, container, false);
		initViews();
		return fragment1View;
	}

	private void initViews() {
		/*
		 * homeFragmentBtn = (Button)
		 * fragment1View.findViewById(R.id.fragment1Btn);
		 * 
		 * homeFragmentBtn.setOnClickListener(new View.OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { // TODO Auto-generated method
		 * stub ((MainActivity) context)
		 * .changeViews(MainActivity.HOME_FRAGMENT); } });
		 */
	}

}
