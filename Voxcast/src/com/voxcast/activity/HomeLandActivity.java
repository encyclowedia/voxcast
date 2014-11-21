package com.voxcast.activity;

import java.util.ArrayList;

import android.content.Intent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.OrientationEventListener;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.voxcast.R;
import com.voxcast.adapter.PostPagerFragment;
import com.voxcast.listeners.OnPostClickListener;
import com.voxcast.model.Poster;
import com.voxcast.model.Result;

public class HomeLandActivity extends BaseActivity implements
		OnPostClickListener<Result> {

	private ArrayList<Result> stringArrayExtra;
	private OrientationEventListener eventListener;
	private boolean isTriggered;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_home_land);
		stringArrayExtra = getIntent().getParcelableArrayListExtra("LIST");
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
	public void onBackPressed() {
		Intent intent = new Intent(this, HomeActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtra("EXIT", true);
		startActivity(intent);
	}

	@Override
	protected void onStop() {
		super.onStop();
		setResult(RESULT_OK);
	}

	@Override
	public void onPostCheckedChanged(ArrayList<Result> arrayList,
			View convertView, int position, CompoundButton buttonView,
			boolean isChecked) {
		RadioButton downVotes = (RadioButton) convertView
				.findViewById(R.id.radio_downvotes);
		RadioButton upVotes = (RadioButton) convertView
				.findViewById(R.id.radio_upvotes);

		TextView txt_downVotes = (TextView) convertView
				.findViewById(R.id.btn_downVotes);
		TextView txt_upVotes = (TextView) convertView
				.findViewById(R.id.btn_upVotes);

		// TODO Change Below Line with Logged in User data.
		Poster poster = new Poster("" + 1, "", "Schnider Rose");
		ArrayList<Poster> upVoters = arrayList.get(position).getUpVoters();
		ArrayList<Poster> downVoters = arrayList.get(position).getDownVoters();

		switch (buttonView.getId()) {
		case R.id.radio_downvotes:
			if (!isChecked)
				break;
			int index = downVoters.indexOf(poster);
			if (index == -1) {
				downVoters.add(poster);
				upVotes.setChecked(false);
				index = upVoters.indexOf(poster);
				if (index != -1) {
					upVoters.remove(index);
				}
			}
			break;
		case R.id.radio_upvotes:
			if (!isChecked)
				break;
			index = upVoters.indexOf(poster);
			if (index == -1) {
				upVoters.add(poster);
				downVotes.setChecked(false);
				index = downVoters.indexOf(poster);
				if (index != -1) {
					downVoters.remove(index);
				}
			}
		}

		downVotes.setText(downVoters.size() + "");
		txt_downVotes.setText(downVoters.size() + " downvotes");
		upVotes.setText(upVoters.size() + "");
		txt_upVotes.setText(upVoters.size() + " upvotes");
	}

	@Override
	public void onPostClick(ArrayList<Result> arrayList, View convertView,
			View view, int position) {
		switch (view.getId()) {
		case R.id.btn_upVotes:
			Result item = arrayList.get(position);
			ArrayList<Poster> upVoters = item.getUpVoters();
			Intent intent = new Intent(this, VotesActivity.class);
			intent.putParcelableArrayListExtra(VotesActivity.KEY_VOTES,
					upVoters);
			intent.putExtra(VotesActivity.KEY_HEADER, "Up Votes");
			startActivity(intent);
			break;

		case R.id.btn_downVotes:
			item = arrayList.get(position);
			ArrayList<Poster> downVoters = item.getDownVoters();
			intent = new Intent(this, VotesActivity.class);
			intent.putParcelableArrayListExtra(VotesActivity.KEY_VOTES,
					downVoters);
			intent.putExtra(VotesActivity.KEY_HEADER, "Down Votes");
			startActivity(intent);
			break;
		}
	}

}
