package com.voxcast.activity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.graphics.BitmapFactory.Options;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.util.Base64;
import android.view.ViewGroup;
import android.widget.ListView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import com.voxcast.R;
import com.voxcast.listeners.OnScrollListener;

public class BaseActivity extends ActionBarActivity {

	protected Fragment fragment = null;
	protected static final String STATE_PAUSE_ON_SCROLL = "STATE_PAUSE_ON_SCROLL";
	protected static final String STATE_PAUSE_ON_FLING = "STATE_PAUSE_ON_FLING";
	protected boolean pauseOnScroll = false;
	protected boolean pauseOnFling = true;

	protected ImageLoader imageLoader = ImageLoader.getInstance();
	protected ViewGroup listView;
	public static DisplayImageOptions options;

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
		if (options == null)
			options = new DisplayImageOptions.Builder().decodingOptions(option)
					.showImageOnLoading(R.drawable.ic_launcher)
					.considerExifParams(true)
					/*
					 * .showImageForEmptyUri(android.R.color.transparent)
					 * .showImageOnFail(android.R.color.transparent)
					 */
					.cacheInMemory(true).cacheOnDisk(true)/*
														 * .considerExifParams(true
														 * )
														 */
					/* .displayer(new FadeInBitmapDisplayer(400)) */.build();
		applyScrollListener();
	}

	public static DisplayImageOptions getOptions() {
		return options;
	}

	private void applyScrollListener() {
		if (listView == null) {
			return;
		}

		if (listView instanceof StickyListHeadersListView) {
			((StickyListHeadersListView) listView)
					.setOnScrollListener(new OnScrollListener(imageLoader,
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
		getSupportActionBar().hide();
		setContentView(R.layout.activity_base);
		System.out.println(getHashKey(this));

	}

	String getHashKey(Context context) {
		String key = "";
		try {
			PackageInfo info = getPackageManager().getPackageInfo(
					"com.voxcast", PackageManager.GET_SIGNATURES);

			for (Signature signature : info.signatures) {
				MessageDigest md = MessageDigest.getInstance("SHA");
				md.update(signature.toByteArray());
				// System.out.println("KeyHash:"
				// + Base64.encodeToString(md.digest(), Base64.DEFAULT));
			}

		} catch (NameNotFoundException e) {

		} catch (NoSuchAlgorithmException e) {

		}
		return key;
	}

}
