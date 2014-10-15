package com.voxcast.Activity;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import com.voxcast.R;
import com.voxcast.Fragment.LogoFragment;

@SuppressLint("NewApi")
public class BaseActivity extends ActionBarActivity {

	private FragmentTransaction FragmentTransaction;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().hide();
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			Fragment newFragment = new LogoFragment();
			FragmentTransaction = getFragmentManager().beginTransaction();
			// FragmentTransaction.setCustomAnimations(
			// android.R.anim.slide_in_left,
			// android.R.anim.slide_out_right);

			FragmentTransaction.add(R.id.layout_frames, newFragment).commit();
		}
	}

}
