package com.voxcast.activity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RelativeLayout;

import com.algo.o2.fb.FacebookFragment.onTokenFetched;

import com.o2.googlesdk.GoogleFragment;
import com.o2.googlesdk.GoogleFragment.OnGoogleTokenFetched;
import com.voxcast.R;
import com.voxcast.fragment.LogoFragment;

public class MainActivity extends BaseActivity implements onTokenFetched,
		OnGoogleTokenFetched {

	private AnimationDrawable drawable;

	private FragmentTransaction fragmentTransaction;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState == null) {
			fragment = new LogoFragment();
			fragmentTransaction = getSupportFragmentManager()
					.beginTransaction();
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

	@Override
	public void onSuccess(String fbtoken) {
		// TODO Auto-generated method stub

		System.out.println(fbtoken);
	}

	@Override
	public void onTokenRetrieved(String gptoken) {
		// TODO Auto-generated method stub
		if (gptoken != null) {
			System.out.println(gptoken);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub

		if (requestCode == 1 && resultCode == RESULT_OK) {
			super.onActivityResult(requestCode, resultCode, data);
			String token = data.getStringExtra("linkedintoken");
			System.out.println(token);
		} else {
			FragmentManager fragmentmanager = getSupportFragmentManager();
			GoogleFragment frag = (GoogleFragment) fragmentmanager
					.findFragmentByTag("google_fragment");
			frag.onActivityResult(requestCode, resultCode, data);
		}
	}

}
