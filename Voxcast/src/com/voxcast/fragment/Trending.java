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
import com.voxcast.adapter.PostAdapter;

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
		fragment1View = inflater.inflate(R.layout.mypost, container, false);

		StickyListHeadersListView list = (StickyListHeadersListView) fragment1View
				.findViewById(R.id.list);

		String[] strings = new String[] { "a", "b", "a", "c", "d", "c" };

		PostAdapter ga = new PostAdapter(getActivity(), strings);
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
