package com.voxcast.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.voxcast.R;
import com.voxcast.fragment.AndroidFragment;
import com.voxcast.fragment.AppleFragment;
import com.voxcast.fragment.TabFragment;
import com.voxcast.view.TabFactory;

public class HomeActivity extends BaseActivity {

	private FragmentTransaction fragmentTransaction;

	TabHost tHost;

	private void addTab(String labelId, int drawableId) {
		View view = ((LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE))
				.inflate(R.layout.tabfragment_image, null);
		((ImageView) view.findViewById(R.id.icon)).setImageResource(drawableId);
		TabSpec tabSpec = tHost.newTabSpec(labelId).setIndicator(view)
				.setContent(new TabFactory(this));
		tHost.addTab(tabSpec);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_activity);

		tHost = (TabHost) findViewById(android.R.id.tabhost);
		tHost.setup();

		addTab("home", R.drawable.home);
		addTab("createpost", R.drawable.createpost);
		addTab("notification", R.drawable.notification);
		addTab("profile", R.drawable.profiles);
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		ft.add(android.R.id.tabcontent, new TabFragment(), "home");
		ft.commit();

		TabHost.OnTabChangeListener tabChangeListener = new TabHost.OnTabChangeListener() {

			@Override
			public void onTabChanged(String tabId) {
				FragmentManager fm = getSupportFragmentManager();
				AndroidFragment androidFragment = (AndroidFragment) fm
						.findFragmentByTag("createpost");
				AppleFragment appleFragment = (AppleFragment) fm
						.findFragmentByTag("notification");
				TabFragment FragmentMainActivity = (TabFragment) fm
						.findFragmentByTag("home");
				TabFragment profile = (TabFragment) fm
						.findFragmentByTag("profile");

				FragmentTransaction ft = fm.beginTransaction();

				/** Detaches the androidfragment if exists */
				if (androidFragment != null)
					ft.detach(androidFragment);

				/** Detaches the applefragment if exists */
				if (appleFragment != null)
					ft.detach(appleFragment);

				if (FragmentMainActivity != null)
					ft.detach(FragmentMainActivity);
				/** If current tab is android */
				if (tabId.equalsIgnoreCase("createpost")) {

					if (androidFragment == null) {
						/**
						 * Create AndroidFragment and adding to
						 * fragmenttransaction
						 */
						ft.add(android.R.id.tabcontent, new AndroidFragment(),
								"createpost");
					} else {
						/**
						 * Bring to the front, if already exists in the
						 * fragmenttransaction
						 */
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
						ft.add(android.R.id.tabcontent, new AppleFragment(),
								"notification");
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
								new TabFragment(), "home");
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
			}
		};
		tHost.setOnTabChangedListener(tabChangeListener);
	}

}
