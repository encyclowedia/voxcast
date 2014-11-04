package com.voxcast.activity;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;
import android.graphics.BitmapFactory.Options;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.ViewGroup;
import android.widget.ListView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.PauseOnScrollListener;
import com.voxcast.R;

public class BaseActivity extends ActionBarActivity {

	protected Fragment fragment = null;
	protected static final String STATE_PAUSE_ON_SCROLL = "STATE_PAUSE_ON_SCROLL";
	protected static final String STATE_PAUSE_ON_FLING = "STATE_PAUSE_ON_FLING";
	protected boolean pauseOnScroll = false;
	protected boolean pauseOnFling = true;

	protected ImageLoader imageLoader = ImageLoader.getInstance();
	protected ViewGroup listView;
	protected DisplayImageOptions options;

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
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		pauseOnScroll = savedInstanceState.getBoolean(STATE_PAUSE_ON_SCROLL,
				true);
		pauseOnFling = savedInstanceState
				.getBoolean(STATE_PAUSE_ON_FLING, true);
	}

	public void setListView(ViewGroup listView) {
		this.listView = listView;
		Options option = new Options();
		// option.

		options = new DisplayImageOptions.Builder().decodingOptions(option)

		.showImageOnLoading(android.R.drawable.btn_star)
		/*
		 * .showImageForEmptyUri(android.R.color.transparent)
		 * .showImageOnFail(android.R.color.transparent)
		 */

		.cacheInMemory(true).cacheOnDisc(true)/* .considerExifParams(true) */
		/* .displayer(new FadeInBitmapDisplayer(400)) */.build();
		applyScrollListener();
	}

	private void applyScrollListener() {
		if (listView == null) {
			return;
		}

		if (listView instanceof StickyListHeadersListView) {
			((StickyListHeadersListView) listView)
					.setOnScrollListener(new PauseOnScrollListener(imageLoader,
							pauseOnScroll, pauseOnFling));
		} else if (listView instanceof ListView) {
			((ListView) listView)
					.setOnScrollListener(new PauseOnScrollListener(imageLoader,
							pauseOnScroll, pauseOnFling));
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putBoolean(STATE_PAUSE_ON_SCROLL, pauseOnScroll);
		outState.putBoolean(STATE_PAUSE_ON_FLING, pauseOnFling);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().hide();
		setContentView(R.layout.base_activity_layout);

	}

}
