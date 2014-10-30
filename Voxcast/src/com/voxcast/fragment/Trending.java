package com.voxcast.fragment;

import com.voxcast.R;
import com.voxcast.adapter.PostAdapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Trending extends BaseFragment {

	private View fragment1View;
	// Button homeFragmentBtn;
	Context context;
	private TextView headerText;

	public Trending() {

	}

	public static Trending newInstance() {
		return new Trending();
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
		// Each row in the list stores country name, currency and flag
		fragment1View = inflater.inflate(R.layout.mypost, container,
						false);
		
				ListView list = (ListView) fragment1View.findViewById(R.id.list);

				PostAdapter ga = new PostAdapter(getActivity());
				list.setAdapter(ga);
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
