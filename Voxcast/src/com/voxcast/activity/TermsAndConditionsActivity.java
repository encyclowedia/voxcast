package com.voxcast.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.voxcast.R;

public class TermsAndConditionsActivity extends BaseActivity implements
		OnClickListener {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.settings_activity);

		findViewById(R.id.tv_settings_arow).setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_settings_arow:

			finish();

			break;

		default:
			break;
		}

	}
}
