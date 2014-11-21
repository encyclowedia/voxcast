package com.voxcast.fragment;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.voxcast.R;
import com.voxcast.activity.HomeActivity;
import com.voxcast.listeners.OnDataChangeListener;

public class BaseFragment extends Fragment {

	private FragmentTransaction fragmentTransaction;
	private static OnDataChangeListener onDataChangeListener;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState == null) {
			fragmentTransaction = getFragmentManager().beginTransaction();
		}
	}

	public static void setOnDataChangeListener(
			OnDataChangeListener onDataChangeListener) {
		BaseFragment.onDataChangeListener = onDataChangeListener;
	}

	public static OnDataChangeListener getOnDataChangeListener() {
		return onDataChangeListener;
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

		if (fragmentTagToBeAddedToBackStack != null
				&& fragmentTagToBeAddedToBackStack
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

	protected final void showErrorMessage(String string) {
		AlertDialog.Builder builder = new Builder(getActivity());
		builder.setMessage(string);
		builder.setPositiveButton("OK", null);
		builder.show();
	}

	public final void popCurrentFragmentOut() {
		if (getActivity() instanceof HomeActivity)
			getFragmentManager().popBackStack(
					((HomeActivity) getActivity()).getPreviousTag(),
					getFragmentManager().POP_BACK_STACK_INCLUSIVE);
	}

}
