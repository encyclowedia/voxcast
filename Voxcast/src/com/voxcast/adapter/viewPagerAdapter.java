package com.voxcast.adapter;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class viewPagerAdapter extends FragmentPagerAdapter {
	ArrayList<Fragment> fragmentList;

	public viewPagerAdapter(FragmentManager fm,
			ArrayList<Fragment> fragmentList) {
		super(fm);
		this.fragmentList = fragmentList;

	}

	@Override
	public Fragment getItem(int position) {
		// TODO Auto-generated method stub
		return fragmentList.get(position);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return fragmentList.size();
	}

}
