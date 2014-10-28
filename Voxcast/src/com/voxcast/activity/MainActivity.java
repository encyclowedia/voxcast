package com.voxcast.activity;

import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;

import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.voxcast.R;
import com.voxcast.constant.Constant;
import com.voxcast.fragment.GettingStartedFragment;
import com.voxcast.fragment.LoginFragment;
import com.voxcast.fragment.LogoFragment;

public class MainActivity extends BaseActivity {

	private AnimationDrawable drawable;

	private FragmentTransaction fragmentTransaction;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState == null) {
			fragment = new LogoFragment();
			fragmentTransaction = getFragmentManager().beginTransaction();
			fragmentTransaction.add(R.id.layout_frames, fragment).commit();
		}
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		if (hasFocus) {
			if (drawable != null) {
				drawable.stop();
				drawable = null;
			}

			RelativeLayout frameLayout = (RelativeLayout) findViewById(R.id.layout_main);
			drawable = (AnimationDrawable) frameLayout.getBackground();
			drawable.start();
		}
	}

}
