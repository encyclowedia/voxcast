package com.voxcast.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;

import com.voxcast.R;
import com.voxcast.fragment.CreatePostFragment;
import com.voxcast.fragment.MyProfileFragment;
import com.voxcast.fragment.NotificationFragment;
import com.voxcast.fragment.TabFragment;
import com.voxcast.view.TabFactory;

public class HomeActivity extends BaseActivity {

	private FragmentTransaction fragmentTransaction;

	TabHost tHost;

	private TabWidget rel_tab;

	private void addTab(String labelId, int drawableId) {
		View view = ((LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE))
				.inflate(R.layout.tabfragment_image, null);
		((ImageView) view.findViewById(R.id.icon)).setImageResource(drawableId);
		TabSpec tabSpec = tHost.newTabSpec(labelId).setIndicator(view)
				.setContent(new TabFactory(this));
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
		ft.add(android.R.id.tabcontent, new TabFragment(), "home");
		previousTag = "home";
		ft.commit();

		TabHost.OnTabChangeListener tabChangeListener = new TabHost.OnTabChangeListener() {

			@Override
			public void onTabChanged(String tabId) {

				/** If current tab is android */
				if (tabId.equalsIgnoreCase("createpost")) {
					CreatePostFragment createPostFragment = new CreatePostFragment();
					replaceFragment(R.id.overlayFragmentContainer, previousTag,
							"createpost", createPostFragment,
							R.anim.animation_pop_in, 0, 0,
							R.anim.animation_pop_out, true, false);
				} else if (tabId.equalsIgnoreCase("notification")) {
					replaceFragment(android.R.id.tabcontent, previousTag,
							"notification", new NotificationFragment(), false,
							false);
				} else if (tabId.equalsIgnoreCase("home")) {
					replaceFragment(android.R.id.tabcontent, previousTag,
							"home", new TabFragment(), false, false);

				} else {
					replaceFragment(android.R.id.tabcontent, previousTag,
							"profile", new MyProfileFragment(), false, false);
				}
				if (!tabId.equalsIgnoreCase("createpost"))
					previousTag = tabId;
			}
		};
		tHost.setOnTabChangedListener(tabChangeListener);
	}
	
	public String getPreviousTag() {
		return previousTag;
	}

	public void setPreviousTag(String previousTag) {
		this.previousTag = previousTag;
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		System.out.println("HomeActivity.onBackPressed() " + previousTag);
	}
}