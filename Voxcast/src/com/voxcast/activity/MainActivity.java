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
			// setTransition(false);
			fragmentTransaction.add(R.id.layout_frames, fragment).commit();
			timeSplash();

		}

	}

	private void setTransition() {

		fragmentTransaction.setCustomAnimations(
				R.anim.fragment_animation_fade_in,
				R.anim.fragment_animation_fade_out);

	}

	public boolean getFlagPref() {
		SharedPreferences prefs = getSharedPreferences(Constant.MY_PREF_NAME,
				MODE_PRIVATE);

		return prefs.getBoolean(Constant.MY_PREF_SPLASH_KEY, false);
	}

	public void setFlagPref() {
		SharedPreferences.Editor editor = null;
		editor = getSharedPreferences(Constant.MY_PREF_NAME, MODE_PRIVATE)
				.edit();
		editor.putBoolean(Constant.MY_PREF_SPLASH_KEY, true);
		editor.commit();

	}

	private void timeSplash() {

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {

				try {

					fragmentTransaction = getFragmentManager().beginTransaction();
					if (getFlagPref()) {

						fragment = new LoginFragment();

					} else {

						fragment = new GettingStartedFragment();
						setFlagPref();
					}
					setTransition();
					if (!isFinishing()) {
						fragmentTransaction.replace(R.id.layout_frames, fragment);
						fragmentTransaction.commitAllowingStateLoss();

					}
				} catch (Exception e) {

				}
				

			}

		}, Constant.SPLASH_DISPLAY_TIME);
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
