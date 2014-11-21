package com.voxcast.fragment;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.webkit.WebView.FindListener;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.internal.he;
import com.voxcast.R;
import com.voxcast.activity.BaseActivity;
import com.voxcast.activity.HomeActivity;
import com.voxcast.activity.HomeLandActivity;
import com.voxcast.adapter.PostAdapter;
import com.voxcast.listeners.OnPostClickListener;
import com.voxcast.listeners.OnScrollListener;
import com.voxcast.view.CircularImageView;

public class HomeFragment extends BaseFragment implements OnPostClickListener {
	// Button fragment1Btn;
	private View homeFragmentView;
	Context context;
	private CircularImageView homeScrnProfImg;
	private OrientationEventListener eventListener;
	private StickyListHeadersListView list;
	private Activity activity;
	private String[] strings;
	private final Handler handler = new Handler();

	public HomeFragment() {
		// as per Android Fragment documentation, here is an empty constructor,
		// so it can be instantiated when restoring its activity's state
	}

	private boolean isTriggered;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getActivity() != null) {
			activity = getActivity();
		}
		eventListener = new OrientationEventListener(getActivity(),
				SensorManager.SENSOR_DELAY_NORMAL) {

			@Override
			public void onOrientationChanged(int orientation) {
				if (isTriggered) {
					return;
				}

				if ((orientation > 75 && orientation < 115)
						|| (orientation < 285 && orientation > 255)) {
					isTriggered = true;
					Intent intent = new Intent(activity, HomeLandActivity.class);
					intent.putExtra("LIST", strings);
					if (list != null && isAdded()) {
						intent.putExtra("POSITION",
								list.getFirstVisiblePosition());
						startActivityForResult(intent, 1);
					}
				}

			}
		};

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == Activity.RESULT_OK) {

			if (list == null) {
				return;
			}
			if (data == null) {
				return;
			}
			list.setSelection(data.getIntExtra("POSITION", 0));
		}
	}

	public static HomeFragment newInstance() {
		return new HomeFragment();
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		if (!isVisibleToUser) {
			if (eventListener != null)
				eventListener.disable();
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		if (eventListener != null) {
			eventListener.enable();
			isTriggered = false;
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// Each row in the list stores country name, currency and flag
		homeFragmentView = inflater.inflate(R.layout.mypost, container, false);

		list = (StickyListHeadersListView) homeFragmentView
				.findViewById(R.id.list);
		((BaseActivity) getActivity()).setListView(list);
		setListenerToListView();
		strings = new String[] { "a", "b", "a", "c", "d", "c" };

		PostAdapter ga = new PostAdapter(getActivity(), strings, this);
		list.setAdapter(ga);
		return homeFragmentView;
	}

	private int top;

	private android.widget.FrameLayout.LayoutParams frame_layout_param;
	private Runnable fragmentRunnable;
	private int height;
	private int count;

	private void setListenerToListView() {
		OnScrollListener listener = (OnScrollListener) list
				.getOnScrollListener();
		listener.setCustomListener(new AbsListView.OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {

			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				View childAt = view.getChildAt(/*
												 * view.getFirstVisiblePosition()
												 * % view.getChildCount()
												 */0);
				int top = childAt.getTop();

				int diff = HomeFragment.this.top - top;

				if (diff < 0 && Math.abs(diff) < childAt.getHeight() / 2) {
					((HomeActivity) getActivity()).makeBarVisible();
				} else if (diff > 0 && Math.abs(diff) < childAt.getHeight() / 2) {
					((HomeActivity) getActivity()).makeBarInVisible();
				}

				HomeFragment.this.top = top;

				// if(top<)

			}

		});
	}

	@Override
	public void onPostCheckedChanged(BaseAdapter adapter, View convertView,
			int position, CompoundButton buttonView, boolean isChecked) {
		// TODO Add Likes And Dislikes
		System.out.println("asdaf");

	}

	@Override
	public void onPostClick(BaseAdapter adapter, final View convertView,
			View view, final int position) {

		// TODO OPEN LIKE AND DISLIKE , Comments , Images Videos etc.
		switch (view.getId()) {
		case R.id.btn_comments:


			final FrameLayout frame_container_layout = (FrameLayout) getActivity()
					.findViewById(R.id.overlayFragmentContainer);

			view.setOnTouchListener(new View.OnTouchListener() {

				private int count_margin;
				private int rawy;

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					if (event.getAction() == MotionEvent.ACTION_DOWN) {

						Fragment CommentFragment = new CommentFragment();
						replaceFragment(R.id.overlayFragmentContainer, "home",
								"CommentFragment", CommentFragment, 0, 0, 0, 0,
								true, false);

					

						rawy = (int) event.getRawY();

						// runfragmentRunnable(rawy,frame_container_layout);
						anim(frame_container_layout, rawy);
					}
					return true;
				}

			});

			// Intent intent = new Intent(activity, CommentActivity.class);
			// startActivity(intent);
			break;

		default:
			break;
		}
	}

	private void anim(FrameLayout frame_container_layout, int rawy) {

		TranslateAnimation transAnimation = new TranslateAnimation(0.0f, 0,
				rawy, 0);
		AlphaAnimation alphaAmin = new AlphaAnimation(0,0f);

		transAnimation.setDuration(850);
		alphaAmin.setDuration(1000);
		AnimationSet growAndShrink = new AnimationSet(true);

		growAndShrink.setInterpolator(new AccelerateDecelerateInterpolator());
		growAndShrink.addAnimation(transAnimation);
		growAndShrink.addAnimation(alphaAmin);
		frame_container_layout.startAnimation(growAndShrink);

	}

	

}
