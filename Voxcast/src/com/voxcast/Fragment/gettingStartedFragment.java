package com.voxcast.Fragment;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.voxcast.R;

public class gettingStartedFragment extends BaseFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.started, container, false);
		TextView featurestext = (TextView) view.findViewById(R.id.featurestext);
		featurestext.setText(Html.fromHtml(getResources().getText(
				R.string.featurestext)
				+ ""));

		return view;
	}

	@Override
	public void onStart() {
		super.onStart();
	}

}
