package com.voxcast.Fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.voxcast.R;

@SuppressLint("NewApi")
public class BaseFragment extends Fragment {

	private FragmentTransaction FragmentManager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState == null) {
			FragmentManager = getFragmentManager().beginTransaction();
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

		FragmentManager.replace(R.id.layout_frames, new LoginFragment());

		if (isNextFragmentNeedsTobeAdded) {
			FragmentManager.addToBackStack(fragmentTagToBeAddedToBackStack);
		}

		FragmentManager.commit();
	}

}
