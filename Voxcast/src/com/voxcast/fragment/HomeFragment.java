package com.voxcast.fragment;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.voxcast.R;
import com.voxcast.adapter.PostAdapter;
import com.voxcast.view.RoundedImageView;

public class HomeFragment extends BaseFragment {
	// Button fragment1Btn;
	private View homeFragmentView;
	Context context;
	private RoundedImageView homeScrnProfImg;

	public HomeFragment() {
		// as per Android Fragment documentation, here is an empty constructor,
		// so it can be instantiated when restoring its activity's state
	}

	public static HomeFragment newInstance() {
		return new HomeFragment();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Each row in the list stores country name, currency and flag
		homeFragmentView = inflater.inflate(R.layout.mypost, container, false);

		StickyListHeadersListView list = (StickyListHeadersListView) homeFragmentView
				.findViewById(R.id.list);

		String[] strings = new String[] { "a", "b", "a", "c", "d", "c" };

		PostAdapter ga = new PostAdapter(getActivity(), strings);
		list.setAdapter(ga);
		return homeFragmentView;
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

}
