package com.voxcast.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.Keyframe;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.voxcast.R;
import com.voxcast.fragment.CreatePostFragment;
import com.voxcast.fragment.MyProfileFragment;
import com.voxcast.fragment.NotificationFragment;
import com.voxcast.fragment.TabFragment;
import com.voxcast.view.TabFactory;

public class HomeActivity extends BaseActivity {

	private TabHost tHost;

	private void addTab(String labelId, int drawableId) {
		View view = ((LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE))
				.inflate(R.layout.tabfragment_image, null);
		((ImageView) view.findViewById(R.id.icon)).setImageResource(drawableId);
		TabSpec tabSpec = tHost.newTabSpec(labelId).setIndicator(view)
				.setContent(new TabFactory(this));
		tHost.addTab(tabSpec);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		System.out.println("HomeActivity.onConfigurationChanged()");
	}

	private String previousTag = "";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		System.out.println("HomeActivity.onCreate()");
		tHost = (TabHost) findViewById(android.R.id.tabhost);
		tHost.setup();

		// rel_tab = (TabWidget) findViewById(android.R.id.tabs);

		addTab("home", R.drawable.ic_home);
		addTab("createpost", R.drawable.ic_edit);
		addTab("notification", R.drawable.ic_alert);
		addTab("profile", R.drawable.ic_profile);

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
					tHost.setCurrentTabByTag(previousTag);
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
		RelativeLayout layout = (RelativeLayout) findViewById(R.id.rel_tab);
		// FrameLayout frameLayout = (FrameLayout)
		// findViewById(android.R.id.tabcontent);
		// LayoutParams params = (LayoutParams) frameLayout.getLayoutParams();
		// params.addRule(RelativeLayout.ABOVE, 0);
		LayoutTransition layoutTransition = layout.getLayoutTransition();
		setLayoutTransition(layoutTransition);

	}

	private void setLayoutTransition(LayoutTransition layoutTransition) {

		layoutTransition.setStartDelay(LayoutTransition.CHANGE_APPEARING, 0);
		layoutTransition.setStartDelay(LayoutTransition.CHANGE_DISAPPEARING, 0);
		layoutTransition.setStartDelay(LayoutTransition.APPEARING, 0);
		layoutTransition.setStartDelay(LayoutTransition.DISAPPEARING, 0);

		layoutTransition.setDuration(LayoutTransition.CHANGE_APPEARING, 200);
		layoutTransition.setDuration(LayoutTransition.CHANGE_DISAPPEARING, 200);
		layoutTransition.setDuration(LayoutTransition.APPEARING, 200);
		layoutTransition.setDuration(LayoutTransition.DISAPPEARING, 200);

		layoutTransition.setStagger(LayoutTransition.CHANGE_APPEARING, 0);
		layoutTransition.setStagger(LayoutTransition.CHANGE_DISAPPEARING, 0);

		ObjectAnimator animator = ObjectAnimator.ofFloat(this, "translationY",
				100, 0).setDuration(
				layoutTransition.getDuration(LayoutTransition.APPEARING));

		layoutTransition.setAnimator(LayoutTransition.APPEARING, animator);

		animator = ObjectAnimator.ofFloat(this, "translationY", 0, 100)
				.setDuration(
						layoutTransition
								.getDuration(LayoutTransition.DISAPPEARING));

		layoutTransition.setAnimator(LayoutTransition.DISAPPEARING, animator);

		PropertyValuesHolder pvhLeft = PropertyValuesHolder.ofInt("left", 0, 1);
		PropertyValuesHolder pvhTop = PropertyValuesHolder.ofInt("top", 0, 1);
		PropertyValuesHolder pvhRight = PropertyValuesHolder.ofInt("right", 0,
				1);
		PropertyValuesHolder pvhBottom = PropertyValuesHolder.ofInt("bottom",
				0, 1);
		PropertyValuesHolder pvhScrollX = PropertyValuesHolder.ofInt("height",
				0, 1);
		PropertyValuesHolder pvhScrollY = PropertyValuesHolder.ofInt("width",
				0, 1);

		ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(
				this, pvhLeft, pvhTop, pvhRight, pvhBottom, pvhScrollX,
				pvhScrollY).setDuration(200);
		layoutTransition.setAnimator(LayoutTransition.CHANGE_APPEARING,
				objectAnimator);

	}

	public void makeBarInVisible() {
		View findViewById = findViewById(android.R.id.tabs);
		findViewById.setVisibility(View.GONE);
		tHost.requestLayout();
	}

	public void makeBarVisible() {
		View findViewById = findViewById(android.R.id.tabs);
		findViewById.setVisibility(View.VISIBLE);
		tHost.requestLayout();
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

	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		// TODO Auto-generated method stub
		super.onActivityResult(arg0, arg1, arg2);
	}
}
