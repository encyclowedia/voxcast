package com.voxcast.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.voxcast.R;

public class SettingsActivity extends BaseActivity implements OnClickListener {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.settings_activity);

		findViewById(R.id.tv_settings_arow).setOnClickListener(this);
		findViewById(R.id.bt_settings_about).setOnClickListener(this);
		findViewById(R.id.bt_settings_termscondition).setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_settings_arow:

			finish();

			break;
		case R.id.bt_settings_about:

			Intent i = new Intent(SettingsActivity.this, AboutActivity.class);
			startActivity(i);

			break;
		case R.id.bt_settings_termscondition:
			i = new Intent(SettingsActivity.this,
					TermsAndConditionsActivity.class);
			startActivity(i);

			break;

		default:
			break;
		}

	}
}
