package com.voxcast;

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
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;

public class MainActivity extends ActionBarActivity {

	private AnimationDrawable drawable;

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().hide();
		}
		setContentView(R.layout.activity_main);
		if (savedInstanceState == null) {
			Fragment newFragment = new MyFragment();
			FragmentTransaction ft = getSupportFragmentManager()
					.beginTransaction();
			ft.setCustomAnimations(android.R.anim.slide_in_left,
					android.R.anim.slide_out_right);
			ft.add(R.id.layout_frames, newFragment).commit();
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

			FrameLayout frameLayout = (FrameLayout) findViewById(R.id.layout_frames);
			drawable = (AnimationDrawable) frameLayout.getBackground();
			drawable.start();
		}
	}

	public void replaceFragment(String fragmentTagToBeAddedToBackStack,
			String fragmentTagToBeAdded, Class className,
			boolean isNextFragmentNeedsTobeAdded) {
		if (fragmentTagToBeAddedToBackStack == null
				&& isNextFragmentNeedsTobeAdded) {
			return;
		}

		if (fragmentTagToBeAdded == null) {
			return;
		}

		if (fragmentTagToBeAddedToBackStack
				.equalsIgnoreCase(fragmentTagToBeAdded)) {
			return;
		}

		FragmentTransaction fragmentTransaction = getSupportFragmentManager()
				.beginTransaction();

		fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left,
				android.R.anim.slide_out_right);

		fragmentTransaction.replace(R.id.layout_frames,
				Fragment.instantiate(this, className.getName(), null),
				fragmentTagToBeAdded);

		if (isNextFragmentNeedsTobeAdded) {
			fragmentTransaction.addToBackStack(fragmentTagToBeAddedToBackStack);
		}

		fragmentTransaction.commit();
	}

	public class MyFragment extends Fragment {

		@Override
		public View onCreateView(LayoutInflater inflater,
				@Nullable ViewGroup container,
				@Nullable Bundle savedInstanceState) {

			System.out.println("MainActivity.MyFragment.onCreateView()");

			// Button button = new Button(getActivity());
			// button.setText("1");
			// button.setOnClickListener(new OnClickListener() {
			//
			// @Override
			// public void onClick(View v) {
			// replaceFragment("", "2", MyFragment1.class, false);
			// }
			// });
			// return button;
			return inflater.inflate(R.layout.layout_, null);
		}

		@Override
		public void onStart() {
			super.onStart();
		}

	}

}
