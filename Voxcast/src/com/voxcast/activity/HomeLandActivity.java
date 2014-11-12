package com.voxcast.activity;

import android.content.Intent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.OrientationEventListener;

import com.voxcast.R;
import com.voxcast.adapter.PostPagerFragment;

public class HomeLandActivity extends BaseActivity {

	private String[] stringArrayExtra;
	private OrientationEventListener eventListener;
	private boolean isTriggered;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_home_land);
		stringArrayExtra = getIntent().getStringArrayExtra("LIST");
		int intExtra = getIntent().getIntExtra("POSITION", 0);

		final ViewPager pager = (ViewPager) findViewById(R.id.viewPager);
		pager.setAdapter(new PostPagerFragment(getSupportFragmentManager(),
				stringArrayExtra));
		pager.setCurrentItem(intExtra);

		eventListener = new OrientationEventListener(this,
				SensorManager.SENSOR_DELAY_NORMAL) {

			@Override
			public void onOrientationChanged(int orientation) {
				System.out.println("HomeLandActivity" + orientation);
				if (isTriggered) {
					return;
				}

				if (orientation > 330 || (orientation < 30 && orientation >= 0)) {
					isTriggered = true;

					pager.postDelayed(new Runnable() {

						@Override
						public void run() {
							Intent data = new Intent();
							data.putExtra("POSITION", pager.getCurrentItem());
							setResult(RESULT_OK, data);
							finish();
						}
					}, 200);
				}

			}
		};

	}

	@Override
	protected void onResume() {
		super.onResume();
		if (eventListener != null) {
			eventListener.enable();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (eventListener != null) {
			eventListener.disable();
		}
	}

	@Override
	protected void onStop() {
		super.onStop();
		setResult(RESULT_OK);
	}

}
