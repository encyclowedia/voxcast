package com.voxcast.fragment;

import java.util.Arrays;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.algo.o2.fb.FacebookFragment;
import com.algo.o2.fb.FbLoginButton;

import com.o2.googlesdk.GoogleFragment;
import com.o2.googlesdk.GoogleLogOutButton;
import com.o2.googlesdk.GoogleSignInButton;
import com.o2.linkedin.activity.LinkedinActivity;
import com.voxcast.R;
import com.voxcast.activity.CommentActivity;
import com.voxcast.activity.CreatePostActivityOLD;
import com.voxcast.activity.HomeActivity;
import com.voxcast.activity.MainActivity;
import com.voxcast.activity.CreatePostActivity;
import com.voxcast.constant.Constant;

public class LoginFragment extends BaseFragment implements OnClickListener {

	private ImageButton fbbutton;
	private GoogleSignInButton ib_login_gplus;
	private ImageButton ib_login_linkedin;
	private TextView tv_loginFrag_termservice, tv_loginFrag_policy;

	private GoogleSignInButton ib_login_gplus_clone;
	private ImageButton gpbutton;
	private FbLoginButton FbLoginButton;
	private FbLoginButton fb_login_button_clone;
	private GoogleLogOutButton ib_login_gplus_clone_signout;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_login, container, false);

		setUI(v);
		setListenerUI();
		setTextViewLink();

		return v;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

	private void setListenerUI() {

		fbbutton.setOnClickListener(this);
		 gpbutton.setOnClickListener(this);
		 ib_login_linkedin.setOnClickListener(this);

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		System.out.println();
	}

	private void setTextViewLink() {

		String text = "By continuing, you accept the <a href='http://www.google.com'>Terms of Service</a>";

		String text1 = "and <a href='http://www.google.com'>Privacy Policy</a>";

		tv_loginFrag_termservice.setMovementMethod(LinkMovementMethod
				.getInstance());

		tv_loginFrag_termservice.setLinkTextColor(Color.WHITE);

		tv_loginFrag_termservice.setText(Html.fromHtml(text));

		tv_loginFrag_policy.setLinkTextColor(Color.WHITE);
		tv_loginFrag_policy.setMovementMethod(LinkMovementMethod.getInstance());
		tv_loginFrag_policy.setText(Html.fromHtml(text1));
	}

	private void setUI(View v) {

		fbbutton = (ImageButton) v.findViewById(R.id.fb_login_image_button);
		fb_login_button_clone = (FbLoginButton) v
				.findViewById(R.id.fb_login_button);
		fbInit();

		 gpbutton = (ImageButton) v.findViewById(R.id.ib_login_gplus);
		 ib_login_gplus_clone = (GoogleSignInButton) v
		 .findViewById(R.id.ib_login_gplus_clone);
		 ib_login_gplus_clone_signout = (GoogleLogOutButton) v
		 .findViewById(R.id.ib_login_gplus_clone_signout);
		 gpInit();

		 ib_login_linkedin = (ImageButton) v
		 .findViewById(R.id.ib_login_linkedin);

		tv_loginFrag_termservice = (TextView) v
				.findViewById(R.id.tv_loginFrag_termservice);

		tv_loginFrag_policy = (TextView) v
				.findViewById(R.id.tv_loginFrag_policy);

	}

	private void gpInit() {
		// TODO Auto-generated method stub
		GoogleFragment GoogleFragment = new GoogleFragment(getActivity());

		getActivity().getSupportFragmentManager().beginTransaction()
				.add(GoogleFragment, "google_fragment").commit();
		ib_login_gplus_clone.setFragment(GoogleFragment);
		ib_login_gplus_clone_signout.setFragment(GoogleFragment);

		GoogleFragment.setLoginButton(ib_login_gplus_clone);
		GoogleFragment.seLogoutButton(ib_login_gplus_clone_signout);
	}

	private void fbInit() {
		// TODO Auto-generated method stub

//		fb_login_button_clone.setReadPermissions(Arrays.asList("email"));
//
//		FacebookFragment fragment = new FacebookFragment();
//		getFragmentManager().beginTransaction()
//				.add(fragment, "fbfragment").commit();
//		fb_login_button_clone.setFragment(fragment);
		
		Intent intent = new Intent(getActivity(),
				CommentActivity.class);
		startActivity(intent);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.fb_login_image_button:
			System.out.println("clicked");
			fb_login_button_clone.performClick();

			break;

		case R.id.ib_login_gplus:
			ib_login_gplus_clone.performClick();

			if (ib_login_gplus_clone.isEnabled()) {
				ib_login_gplus_clone.performClick();

			} else
				ib_login_gplus_clone_signout.performClick();

			break;

		case R.id.ib_login_linkedin:
			Intent intent = new Intent(getActivity(), LinkedinActivity.class);
			getActivity().startActivityForResult(intent,
					Constant.REQ_CODE_LINKEDIN);
			break;

		default:
			break;

		}

	}

}
