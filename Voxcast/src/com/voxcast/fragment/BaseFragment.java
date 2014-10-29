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

	public void replaceFragment(String fragmentTagToBeAddedToBackStack,
			String fragmentTagToBeAdded, Fragment className,
			boolean isNextFragmentNeedsTobeAdded, boolean isCommitIsStateLoss) {
		replaceFragment(fragmentTagToBeAddedToBackStack, fragmentTagToBeAdded,
				className, 0, 0, isNextFragmentNeedsTobeAdded,
				isCommitIsStateLoss);
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
