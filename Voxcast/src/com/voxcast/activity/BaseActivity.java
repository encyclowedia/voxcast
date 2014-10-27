package com.voxcast.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;

import com.voxcast.R;
import com.voxcast.constant.Constant;
import com.voxcast.fragment.GettingStartedFragment;
import com.voxcast.fragment.LoginFragment;

public class BaseActivity extends ActionBarActivity {

	Fragment fragment = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().hide();
		setContentView(R.layout.activity_main);
	}
 
}
