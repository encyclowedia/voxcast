package com.voxcast.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;

import com.voxcast.R;
import com.voxcast.fragment.AndroidFragment;
import com.voxcast.fragment.AppleFragment;
import com.voxcast.fragment.FragmentMainActivity;
import com.voxcast.view.MyTabFactory;

public class HomeActivity extends BaseActivity {

	private FragmentTransaction fragmentTransaction;

	TabHost tHost;

	private TabWidget rel_tab;

	private void addTab(String labelId, int drawableId) {
		View view = ((LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE))
				.inflate(R.layout.tab, null);
		((ImageView) view.findViewById(R.id.icon)).setImageResource(drawableId);
		TabSpec tabSpec = tHost.newTabSpec(labelId).setIndicator(view)
				.setContent(new MyTabFactory(this));
		tHost.addTab(tabSpec);
	}

	private String previousTag = "";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		tHost = (TabHost) findViewById(android.R.id.tabhost);
		tHost.setup();

		// rel_tab = (TabWidget) findViewById(android.R.id.tabs);

		addTab("home", R.drawable.home);
		addTab("createpost", R.drawable.createpost);
		addTab("notification", R.drawable.notification);
		addTab("profile", R.drawable.profiles);
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		ft.add(android.R.id.tabcontent, new FragmentMainActivity(), "home");
		previousTag = "home";
		ft.commit();

		TabHost.OnTabChangeListener tabChangeListener = new TabHost.OnTabChangeListener() {

			@Override
			public void onTabChanged(String tabId) {
				FragmentManager fm = getSupportFragmentManager();
				CreatePostActivity androidFragment = (CreatePostActivity) fm
						.findFragmentByTag("createpost");
				NotificationActivity appleFragment = (NotificationActivity) fm
						.findFragmentByTag("notification");
				FragmentMainActivity FragmentMainActivity = (FragmentMainActivity) fm
						.findFragmentByTag("home");
				FragmentMainActivity profile = (FragmentMainActivity) fm
						.findFragmentByTag("profile");

				FragmentTransaction ft = fm.beginTransaction();

				/** Detaches the androidfragment if exists */
				if (androidFragment != null)
					ft.remove(androidFragment);

				/** Detaches the applefragment if exists */
				if (appleFragment != null)
					ft.remove(appleFragment);

				if (FragmentMainActivity != null)
					ft.remove(FragmentMainActivity);
				/** If current tab is android */
				if (tabId.equalsIgnoreCase("createpost")) {

					if (androidFragment == null) {
						/**
						 * Create AndroidFragment and adding to
						 * fragmenttransaction
						 */
						ft.addToBackStack(previousTag);
						ft.add(R.id.overlayFragmentContainer,
								new CreatePostActivity(), "createpost");
						// rel_tab.setVisibility(View.GONE);

					} else {
						/**
						 * Bring to the front, if already exists in the
						 * fragmenttransaction
						 */
						ft.addToBackStack(previousTag);
						ft.attach(androidFragment);
					}

				} else if (tabId.equalsIgnoreCase("notification"))

				{
					/** If current tab is apple */
					if (appleFragment == null) {
						/**
						 * Create AppleFragment and adding to
						 * fragmenttransaction
						 */

						ft.add(android.R.id.tabcontent,
								new NotificationActivity(), "notification");

					} else {
						/**
						 * Bring to the front, if already exists in the
						 * fragmenttransaction
						 */
						ft.attach(appleFragment);
					}
				} else if (tabId.equalsIgnoreCase("home"))

				{

					/** If current tab is apple */
					if (FragmentMainActivity == null) {
						/**
						 * Create AppleFragment and adding to
						 * fragmenttransaction
						 */
						ft.add(android.R.id.tabcontent,
								new FragmentMainActivity(), "home");
					} else {
						/**
						 * Bring to the front, if already exists in the
						 * fragmenttransaction
						 */
						ft.attach(FragmentMainActivity);
					}

				} else {

				}
				ft.commit();
				if (!tabId.equalsIgnoreCase("createpost"))
					previousTag = tabId;
			}
		};
		tHost.setOnTabChangedListener(tabChangeListener);
	}

	public String getPreviousTag() {
		return previousTag;
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		System.out.println("HomeActivity.onBackPressed() " + previousTag);
	}

}
