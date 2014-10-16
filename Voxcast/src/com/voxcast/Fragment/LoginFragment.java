package com.voxcast.Fragment;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.voxcast.R;

public class LoginFragment extends BaseFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.login, container, false);

		TextView tv_loginFrag_termservice = (TextView) v
				.findViewById(R.id.tv_loginFrag_termservice);

		TextView tv_loginFrag_termservic = (TextView) v
				.findViewById(R.id.tv_loginFrag_termservic);
		String text = "By continuing, you accept the <a href='http://www.google.com'>Terms of Service</a>";
		String text1 = "and <a href='http://www.google.com'>Privacy Policy</a>";
		tv_loginFrag_termservice.setMovementMethod(LinkMovementMethod
				.getInstance());
		tv_loginFrag_termservice.setText(Html.fromHtml(text));
		tv_loginFrag_termservic.setMovementMethod(LinkMovementMethod
				.getInstance());
		tv_loginFrag_termservic.setText(Html.fromHtml(text1));
		return v;
	}
}
