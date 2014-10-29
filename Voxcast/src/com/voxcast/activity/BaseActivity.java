package com.voxcast.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;

import com.voxcast.R;

public class BaseActivity extends ActionBarActivity {

	Fragment fragment = null;

	public void replaceFragment(int containerId,
			String fragmentTagToBeAddedToBackStack,
			String fragmentTagToBeAdded, Fragment className,
			boolean isNextFragmentNeedsTobeAdded, boolean isCommitIsStateLoss) {
		replaceFragment(containerId, fragmentTagToBeAddedToBackStack,
				fragmentTagToBeAdded, className,
				R.anim.fragment_animation_fade_in,
				R.anim.fragment_animation_fade_out, 0, 0,
				isNextFragmentNeedsTobeAdded, isCommitIsStateLoss);
	}

	public void replaceFragment(int containerId,
			String fragmentTagToBeAddedToBackStack,
			String fragmentTagToBeAdded, Fragment className, int enter,
			int exit, int popEnter, int popExit,
			boolean isNextFragmentNeedsTobeAdded, boolean isCommitIsStateLoss) {
		FragmentTransaction fragmentTransaction = this
				.getSupportFragmentManager().beginTransaction();
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
		fragmentTransaction.setCustomAnimations(enter, exit, popEnter, popExit);
		fragmentTransaction.replace(containerId, className);

		if (isNextFragmentNeedsTobeAdded) {
			fragmentTransaction.addToBackStack(fragmentTagToBeAddedToBackStack);
		}
		if (isCommitIsStateLoss) {
			fragmentTransaction.commitAllowingStateLoss();
		} else {
			fragmentTransaction.commit();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().hide();
		setContentView(R.layout.activity_main);
	}

}
