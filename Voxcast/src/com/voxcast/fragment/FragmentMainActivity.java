package com.voxcast.fragment;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viewpagerindicator.CirclePageIndicator;
import com.voxcast.R;
import com.voxcast.adapter.MainFragmentAdapter;

public class FragmentMainActivity extends BaseFragment {
	private ViewPager viewPager;
	private MainFragmentAdapter mainFragmentAdapter;
	CirclePageIndicator mIndicator;
	public static final int HOME_FRAGMENT = 0;// 0 determines, this will be the
												// first home fragment
	public static final int FRAGMENT_1 = 1;// comes after swiping home fragment
	public static final int FRAGMENT_2 = 2;
	// private Button leftFragmentBtn, rightFragmentBtn;
	private ArrayList<Fragment> fragmentList = new ArrayList<Fragment>();
	// creating a fragment list to hold all the fragments
	private int currentPage = 0;
	private View mainfragment;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		HomeFragment homeFragment = HomeFragment.newInstance();
		fragmentList.add(homeFragment);

		// adding the fragment1 to the fragment lis
		Fragment1 fragment1 = Fragment1.newInstance();
		fragmentList.add(fragment1);

		// adding the fragment1 to the fragment lis
		Fragment2 fragment2 = Fragment2.newInstance();
		fragmentList.add(fragment2);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		// if (mainfragment == null) {
		mainfragment = inflater.inflate(R.layout.fragment_activity_main,
				container, false);
		viewPager = (ViewPager) mainfragment.findViewById(R.id.viewPager);
		mIndicator = (CirclePageIndicator) mainfragment
				.findViewById(R.id.indicator);
		mainFragmentAdapter = new MainFragmentAdapter(
				getChildFragmentManager(), fragmentList);
		viewPager.setOffscreenPageLimit(2);
		viewPager.setAdapter(mainFragmentAdapter);

		mIndicator.setViewPager(viewPager);

		// }

		return mainfragment;

	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

	}

	public void changeViews(int position) {
		viewPager.setCurrentItem(position, true);

	}

}
