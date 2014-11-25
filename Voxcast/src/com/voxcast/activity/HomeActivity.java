package com.voxcast.activity;

import java.util.ArrayList;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.OrientationEventListener;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.voxcast.R;
import com.voxcast.constant.Constant;
import com.voxcast.fragment.CreatePostFragment;
import com.voxcast.fragment.MyProfileFragment;
import com.voxcast.fragment.NotificationFragment;
import com.voxcast.fragment.TabFragment;
import com.voxcast.model.Comment;
import com.voxcast.model.Poster;
import com.voxcast.model.Result;
import com.voxcast.view.TabFactory;

public class HomeActivity extends BaseActivity {

	private TabHost tHost;
	private OrientationEventListener orientationEventListener;
	private ArrayList<Result> arrayList = new ArrayList<Result>();

	{
		Poster poster = new Poster("-1", "", "Anonymous");
		ArrayList<Poster> upvoters = new ArrayList<Poster>();
		ArrayList<Poster> downVoters = new ArrayList<Poster>();
		ArrayList<String> pics = new ArrayList<String>();
		pics.add("");
		ArrayList<String> vid = new ArrayList<String>();
		vid.add("");
		ArrayList<Comment> comments = new ArrayList<Comment>();
		Comment comment = new Comment(poster, "Well Done!", "", null);
		comments.add(comment);
		Result result = new Result("", poster, "#", "Noida", upvoters,
				downVoters, "Just Now", pics, vid, comments);
		arrayList.add(0, result);
	}

	public ArrayList<Result> getArrayList() {
		return arrayList;
	}

	public void setArrayList(ArrayList<Result> arrayList) {
		this.arrayList = arrayList;
	}

	private void addTab(String labelId, int drawableId) {
		View view = ((LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE))
				.inflate(R.layout.layout_tab_button, null);
		((ImageView) view.findViewById(R.id.icon)).setImageResource(drawableId);
		TabSpec tabSpec = tHost.newTabSpec(labelId).setIndicator(view)
				.setContent(new TabFactory(this));
		tHost.addTab(tabSpec);
	}

	public void setOrientationEventListener(
			OrientationEventListener orientationEventListener) {
		this.orientationEventListener = orientationEventListener;
	}

	public OrientationEventListener getOrientationEventListener() {
		return orientationEventListener;
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
		if (getIntent().getBooleanExtra("EXIT", false)) {
			finish();
			return;
		}
		setContentView(R.layout.activity_home);
		tHost = (TabHost) findViewById(android.R.id.tabhost);
		tHost.setup();

		addTab("home", R.drawable.ic_home);
		addTab("createpost", R.drawable.ic_edit);
		addTab("notification", R.drawable.ic_alert);
		addTab("profile", R.drawable.ic_profile);

		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		ft.add(android.R.id.tabcontent, new TabFragment(),
				Constant.FRAGMENT_TAB);
		previousTag = Constant.FRAGMENT_TAB;
		ft.commit();

		TabHost.OnTabChangeListener tabChangeListener = new TabHost.OnTabChangeListener() {

			@Override
			public void onTabChanged(String tabId) {

				/** If current tab is android */
				if (tabId.equalsIgnoreCase(Constant.FRAGMENT_CREATEPOST)) {
					CreatePostFragment createPostFragment = new CreatePostFragment();
					replaceFragment(R.id.overlayFragmentContainer, previousTag,
							Constant.FRAGMENT_CREATEPOST, createPostFragment,
							R.anim.animation_pop_in, 0, 0,
							R.anim.animation_pop_out, true, false);
					tHost.setCurrentTabByTag(previousTag);
				} else if (tabId
						.equalsIgnoreCase(Constant.FRAGMENT_NOTIFICATION)) {
					setLayoutForHome(true);
					replaceFragment(android.R.id.tabcontent, previousTag,
							Constant.FRAGMENT_NOTIFICATION,
							new NotificationFragment(), false, false);
				} else if (tabId.equalsIgnoreCase(Constant.FRAGMENT_TAB)) {
					setLayoutForHome(true);
					replaceFragment(android.R.id.tabcontent, previousTag,
							Constant.FRAGMENT_TAB, new TabFragment(), false,
							false);

				} else {
					setLayoutForHome(false);
					replaceFragment(android.R.id.tabcontent, previousTag,
							Constant.FRAGMENT_MYPROFILE,
							new MyProfileFragment(), false, false);
				}
				if (!tabId.equalsIgnoreCase(Constant.FRAGMENT_CREATEPOST))
					previousTag = tabId;
			}
		};
		tHost.setOnTabChangedListener(tabChangeListener);
		RelativeLayout layout = (RelativeLayout) findViewById(R.id.rel_tab);
		setLayoutForHome(true);
		LayoutTransition layoutTransition = layout.getLayoutTransition();
		setLayoutTransition(layoutTransition);

	}

	private void setLayoutForHome(boolean isForHome) {
		FrameLayout frameLayout = (FrameLayout) findViewById(android.R.id.tabcontent);
		LayoutParams params = (LayoutParams) frameLayout.getLayoutParams();
		params.addRule(RelativeLayout.ABOVE, isForHome ? 0 : android.R.id.tabs);
		frameLayout.setLayoutParams(params);
	}

	private void setLayoutTransition(LayoutTransition layoutTransition) {

		layoutTransition.setStartDelay(LayoutTransition.APPEARING, 0);
		layoutTransition.setStartDelay(LayoutTransition.DISAPPEARING, 0);

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

}
