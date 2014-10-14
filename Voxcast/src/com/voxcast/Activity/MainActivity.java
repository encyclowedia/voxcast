package com.voxcast.Activity;

import com.voxcast.R;
import com.voxcast.Fragment.LogoFragment;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends BaseActivity {

	private AnimationDrawable drawable;
	private FragmentTransaction FragmentTransaction;

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);


	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		if (hasFocus) {
			if (drawable != null) {
				drawable.stop();
				drawable = null;
			}

			FrameLayout frameLayout = (FrameLayout) findViewById(R.id.layout_frames);
			drawable = (AnimationDrawable) frameLayout.getBackground();
			drawable.start();
		}
	}

	
	
	}


