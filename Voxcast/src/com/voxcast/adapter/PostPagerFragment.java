package com.voxcast.adapter;

import java.util.ArrayList;

import com.voxcast.R;
import com.voxcast.fragment.BaseFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PostPagerFragment extends FragmentStatePagerAdapter {

	private String[] strings;
	private ArrayList<PostFragment> arrayList = new ArrayList<PostPagerFragment.PostFragment>();

	public PostPagerFragment(FragmentManager fm, String[] strings) {
		super(fm);
		this.strings = strings;
		for (int i = 0; i < strings.length; i++) {
			arrayList.add(new PostFragment());
		}
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		return arrayList.get(arg0);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return strings.length;
	}

	public static class PostFragment extends BaseFragment {

		@Override
		public View onCreateView(LayoutInflater inflater,
				@Nullable ViewGroup container,
				@Nullable Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			return inflater.inflate(R.layout.layout_header_pager_land, null);
		}

	}

}
