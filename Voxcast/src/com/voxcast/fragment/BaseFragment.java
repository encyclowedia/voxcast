package com.voxcast.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.voxcast.R;

public class BaseFragment extends Fragment {

	private FragmentTransaction fragmentTransaction;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState == null) {
			fragmentTransaction = getFragmentManager().beginTransaction();
		}
	}

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
		FragmentTransaction fragmentTransaction = getActivity()
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

}
