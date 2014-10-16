package com.voxcast.Activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;

import com.voxcast.R;
import com.voxcast.Fragment.GettingStartedFragment;
import com.voxcast.Fragment.LoginFragment;
import com.voxcast.constant.Constant;

public class BaseActivity extends ActionBarActivity {

	

	Fragment fragment = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().hide();
		setContentView(R.layout.activity_main);
	/*	if (savedInstanceState == null) {

			

		//	timeSplash();

		}*/
	}



}
