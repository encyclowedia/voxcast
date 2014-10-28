package com.voxcast.fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

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

	public void replaceFragment(String fragmentTagToBeAddedToBackStack,
			String fragmentTagToBeAdded, Fragment className,
			boolean isNextFragmentNeedsTobeAdded, boolean isCommitIsStateLoss) {
		replaceFragment(fragmentTagToBeAddedToBackStack, fragmentTagToBeAdded,
				className, R.anim.fragment_animation_fade_in,
				R.anim.fragment_animation_fade_out,
				isNextFragmentNeedsTobeAdded, isCommitIsStateLoss);
	}

	public void replaceFragment(String fragmentTagToBeAddedToBackStack,
			String fragmentTagToBeAdded, Fragment className, int enter,
			int exit, boolean isNextFragmentNeedsTobeAdded,
			boolean isCommitIsStateLoss) {
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
		fragmentTransaction.setCustomAnimations(enter, exit);
		fragmentTransaction.replace(R.id.layout_frames, className);

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
