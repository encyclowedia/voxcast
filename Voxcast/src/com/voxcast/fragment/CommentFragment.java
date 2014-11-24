package com.voxcast.fragment;

import java.util.ArrayList;

import com.voxcast.R;
import com.voxcast.adapter.CommentAdapter;

import android.app.Activity;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import android.widget.TextView;

public class CommentFragment extends Fragment implements AnimationListener {

	/** Items entered by the user is stored in this ArrayList variable */
	ArrayList<String> list = new ArrayList<String>();
	Handler handler = new Handler();
	/** Declaring an ArrayAdapter to set items to ListView */
	CommentAdapter adapter;
	int layoutBottomh;

	protected int flag = 0;
	private View layoutBottom_comment;
	private Animation abc_slide_in_top;
	private View layoutBottom;
	private ListView listview;
	private View layoutBottom_post;
	private RelativeLayout.LayoutParams layoutBottomparam;
	private RelativeLayout.LayoutParams layoutBottom_comment_param;
	private View listlayout;
	private View view;

	/** Called when the activity is first created. */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.comment, container, false);

		layoutBottom_comment = view.findViewById(R.id.comment);
		layoutBottom = view.findViewById(R.id.layoutBottom);
		layoutBottom_post = view.findViewById(R.id.layoutpost);
		listlayout = view.findViewById(R.id.listlayout);

		layoutBottomparam = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

		layoutBottom_comment_param = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		/** Setting a custom layout for the list activity */

		listview = (ListView) view.findViewById(R.id.list);
		/** Reference to the button of the layout main.xml */
		TextView Post = (TextView) view.findViewById(R.id.Post);
		list.add("a");
		list.add("a");
		list.add("a");
		list.add("a");
		/** Defining the ArrayAdapter to set items to ListView */
		adapter = new CommentAdapter(getActivity(), list);
		listview.setAdapter(adapter);
		/** Defining a click event listener for the button "Add" */
		OnClickListener listener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText edit = (EditText) view.findViewById(R.id.txtItem);
				list.add(edit.getText().toString());
				edit.setText("");
				adapter.notifyDataSetChanged();
				listview.setSelection(adapter.getCount() - 1);
			}
		};

		/** Setting the event listener for the add button */
		Post.setOnClickListener(listener);

		/** Setting the adapter to the ListView */

		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		Runnable runable = new Runnable() {

			@Override
			public void run() {

				anim(layoutBottom.getHeight()
						- layoutBottom_comment.getHeight());

			}

		};

		handler.postDelayed(runable, 500);
	}

	private void anim(int rawy) {

		TranslateAnimation transAnimation = new TranslateAnimation(0.0f, 0, 0,
				-rawy);
		transAnimation.setDuration(500);
		AnimationSet growAndShrink = new AnimationSet(true);
		growAndShrink.setInterpolator(new AccelerateInterpolator());
		growAndShrink.addAnimation(transAnimation);

		listview.startAnimation(growAndShrink);
		layoutBottom.startAnimation(growAndShrink);

		growAndShrink.setAnimationListener(this);
	}

	@Override
	public void onAnimationStart(Animation animation) {
		layoutBottom_comment.setBackgroundResource(android.R.color.white);
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		layoutBottomparam.height = 0;
		layoutBottom_post.setLayoutParams(layoutBottomparam);
		layoutBottom_comment.setLayoutParams(layoutBottom_comment_param);
		layoutBottom_comment.postDelayed(new Runnable() {

			@Override
			public void run() {
				layoutBottom_comment.setBackgroundResource(R.drawable.top_bg);
			}
		}, 100);
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
	}

}