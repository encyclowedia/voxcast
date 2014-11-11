package com.voxcast.fragment;


import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.viewpagerindicator.CirclePageIndicator;
import com.voxcast.R;
import com.voxcast.adapter.viewPagerAdapter;

public class TabFragment extends BaseFragment {
	private ViewPager viewPager;
	private viewPagerAdapter mainFragmentAdapter;
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
	private TextView headerText;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		HomeFragment homeFragment = HomeFragment.newInstance();
		fragmentList.add(homeFragment);

		// adding the fragment1 to the fragment lis
		Trending fragment1 = Trending.newInstance();
		fragmentList.add(fragment1);

		// adding the fragment1 to the fragment lis
		Mypost fragment2 = Mypost.newInstance();
		fragmentList.add(fragment2);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		// if (mainfragment == null) {
		mainfragment = inflater.inflate(R.layout.tabfragment,
				container, false);

		headerText = (TextView) mainfragment.findViewById(R.id.text);	
		viewPager = (ViewPager) mainfragment.findViewById(R.id.viewPager);
		mIndicator = (CirclePageIndicator) mainfragment
				.findViewById(R.id.indicator);
		mainFragmentAdapter = new viewPagerAdapter(
				getChildFragmentManager(), fragmentList);
		viewPager.setOffscreenPageLimit(2);
		viewPager.setAdapter(mainFragmentAdapter);
		mIndicator.setOnPageChangeListener(new OnPageChangeListener() {

		    @Override
		    public void onPageSelected(int position) {      
		       if (position==0) {
		    	   headerText.setText("Home");
			} else if (position==1) {
				   headerText.setText("Trending");
			}else{
				   headerText.setText("My Post");
			}
		    }

		    @Override
		    public void onPageScrolled(int position, float offset, int offsetPixel) {
		    	
		    }

		    @Override
		    public void onPageScrollStateChanged(int state) {

		    }
		});
		mIndicator.setViewPager(viewPager);

		
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
