package com.voxcast.activity;

import java.util.Arrays;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.algo.o2.fb.FacebookFragment;
import com.algo.o2.fb.FbLoginButton;
import com.algo.o2.fb.FacebookFragment.onTokenFetched;

import com.o2.googlesdk.GoogleFragment;
import com.o2.googlesdk.GoogleFragment.OnGoogleTokenFetched;
import com.voxcast.R;
import com.voxcast.constant.Constant;
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

//		super.onWindowFocusChanged(hasFocus);
//		if (hasFocus) {
			if (drawable != null) {
				drawable.stop();
				drawable = null;
//			}

			RelativeLayout frameLayout = (RelativeLayout) findViewById(R.id.layout_main);
			drawable = (AnimationDrawable) frameLayout.getBackground();
			drawable.start();
		}
	
			
	
	}

//	@Override
//	public void onWindowFocusChanged(boolean hasFocus) {}

	@Override
	public void onSuccess(String fbtoken) {
		// TODO Auto-generated method stub

		
			System.out.println("fbtoken Token "+ fbtoken);
		

	}

	@Override
	public void onTokenRetrieved(String gptoken) {
		// TODO Auto-generated method stub
		if (gptoken != null) {
			System.out.println("gplus Token "+gptoken);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		
	
			switch (requestCode) {
			case Constant.REQ_CODE_LINKEDIN:
				super.onActivityResult(requestCode, resultCode, data);
				String linkedintoken = data.getStringExtra("linkedintoken");
				if (linkedintoken != null) {
					System.out.println("linkedintoken Token "+linkedintoken);
				}
				break;
			case 0:
				 FragmentManager fragmentmanager = getSupportFragmentManager();
				 GoogleFragment frag = (GoogleFragment) fragmentmanager
				 .findFragmentByTag("google_fragment");
				 frag.onActivityResult(requestCode, resultCode, data);
				break;
			default:
				break;
				
		}

	}
}
