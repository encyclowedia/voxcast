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
import com.algo.o2.fb.FacebookFragment.onTokenFetched;
import com.algo.o2.fb.FbLoginButton;

import com.o2.googlesdk.GoogleFragment;
import com.o2.googlesdk.GoogleSignInButton;
import com.o2.linkedin.activity.LinkedinActivity;
import com.voxcast.R;
import com.voxcast.activity.CreatePostActivityOLD;
import com.voxcast.activity.HomeActivity;
import com.voxcast.activity.MainActivity;
import com.voxcast.activity.CreatePostActivity;

public class LoginFragment extends BaseFragment implements OnClickListener {

	FbLoginButton button;
	GoogleSignInButton ib_login_gplus;
	private ImageButton ib_login_linkedin;
	private TextView tv_loginFrag_termservice, tv_loginFrag_policy;


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

		button.setOnClickListener(this);
		ib_login_gplus.setOnClickListener(this);
		ib_login_linkedin.setOnClickListener(this);

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

		button = (FbLoginButton) v.findViewById(R.id.fb_login_button);
		
		
		
		
//		FragmentManager fm = getActivity().getSupportFragmentManager();
//		FragmentTransaction ft = fm.beginTransaction();
//		replaceFragment("", "home", new FacebookFragment(), false, false);
////		ft.add(android.R.id.tabcontent, , );
//		ft.commit();


		
		
		ib_login_gplus = (GoogleSignInButton) v.findViewById(R.id.ib_login_gplus);

	
		
		
		ib_login_linkedin = (ImageButton) v
				.findViewById(R.id.ib_login_linkedin);

		tv_loginFrag_termservice = (TextView) v
				.findViewById(R.id.tv_loginFrag_termservice);

		tv_loginFrag_policy = (TextView) v
				.findViewById(R.id.tv_loginFrag_policy);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.fb_login_button:
			button.setText("");
			
			button.setBackgroundResource(R.drawable.login_facebook_ic);
			button.setReadPermissions(Arrays.asList("email"));

			FacebookFragment fragment = new FacebookFragment();
			getActivity().getSupportFragmentManager().beginTransaction()
					.add(fragment, "fbfragment").commit();
			button.setFragment(fragment);
			break;
			
		case R.id.ib_login_gplus:
			GoogleFragment GoogleFragment = new GoogleFragment(getActivity()); 
			
			getActivity().getSupportFragmentManager().beginTransaction() .add(GoogleFragment, "google_fragment").commit(); 
			ib_login_gplus.setFragment(GoogleFragment); 
			break;
			
			
		case R.id.ib_login_linkedin:
			Intent intent = new Intent(getActivity(),LinkedinActivity.class);
			startActivityForResult(intent, 1);
			break;

		default:
			break;

		}

	}

}
