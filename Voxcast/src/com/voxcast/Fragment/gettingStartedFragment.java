package com.voxcast.Fragment;



import com.voxcast.R;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class gettingStartedFragment extends BaseFragment {

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.started, container, false);
		TextView featurestext = (TextView) view.findViewById(R.id.featurestext);
		featurestext.setText(Html.fromHtml(getResources().getText(R.string.featurestext) + ""));
		
		return view;
	}

	@Override
	public void onStart() {
		super.onStart();
	}

}
