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

public class Mypost extends BaseFragment {

	private View MypostView;
	// Button fragmentRBtn;
	Context context;
	private TextView headerText;

	public Mypost() {

	}

	public static Mypost newInstance() {
		return new Mypost();
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		context = getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// if (fragment2View == null)
		MypostView = inflater.inflate(R.layout.mypost, container,
				false);
		
		ListView list = (ListView) MypostView.findViewById(R.id.list);

		PostAdapter ga = new PostAdapter(getActivity());
		list.setAdapter(ga);
		return MypostView;
	}

	private void initViews() {
		/*
		 * fragmentRBtn = (Button)
		 * fragment2View.findViewById(R.id.fragment1BtnR);
		 * 
		 * fragmentRBtn.setOnClickListener(new View.OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { // TODO Auto-generated method
		 * stub ((MainActivity) context) .changeViews(MainActivity.FRAGMENT_1);
		 * } });
		 */}

}
