package com.voxcast.Fragment;

import com.voxcast.R;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

@SuppressLint("NewApi")
public class BaseFragment extends Fragment {



	private FragmentTransaction FragmentManager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		if (savedInstanceState == null) {
		
			FragmentManager = getFragmentManager().beginTransaction();
			
			FragmentManager.setCustomAnimations(android.R.anim.slide_in_left,
					android.R.anim.slide_out_right);
			
			
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

		
		FragmentManager.replace(R.id.layout_frames,
				new LoginFragment());

		if (isNextFragmentNeedsTobeAdded) {
			FragmentManager.addToBackStack(fragmentTagToBeAddedToBackStack);
		}

		FragmentManager.commit();
	}


}
