package com.voxcast.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.voxcast.R;
import com.voxcast.activity.HomeActivity;

public class LoginFragment extends BaseFragment implements OnClickListener {

	private ImageButton ib_login_facebook, ib_login_gplus, ib_login_linkedin;
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

	private void setListenerUI() {
		ib_login_facebook.setOnClickListener(this);
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
		ib_login_facebook = (ImageButton) v
				.findViewById(R.id.ib_login_facebook);
		ib_login_gplus = (ImageButton) v.findViewById(R.id.ib_login_gplus);
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
		case R.id.ib_login_facebook:
		case R.id.ib_login_gplus:
		case R.id.ib_login_linkedin:
			Intent i = new Intent(getActivity(), HomeActivity.class);
			getActivity().startActivity(i);
			getActivity().finish();
			break;

		default:
			break;
		}

	}
}
