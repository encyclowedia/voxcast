package com.voxcast.fragment;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.voxcast.R;
import com.voxcast.activity.BaseActivity;
import com.voxcast.adapter.PostAdapter;

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
		MypostView = inflater.inflate(R.layout.mypost, container, false);

		StickyListHeadersListView list = (StickyListHeadersListView) MypostView
				.findViewById(R.id.list);
		((BaseActivity) getActivity()).setListView(list);
		String[] strings = new String[] { "a", "b", "a", "c", "d", "c" };

		PostAdapter ga = new PostAdapter(getActivity(), strings);
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
