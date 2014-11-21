package com.voxcast.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.voxcast.R;
import com.voxcast.adapter.VotesAdapter;
import com.voxcast.model.Poster;

public class VotesActivity extends BaseActivity implements OnItemClickListener {

	public static final String KEY_VOTES = "VOTES";
	public static final String KEY_HEADER = "HEADER";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setWindowAnimations(R.style.animation);
		setContentView(R.layout.downvote_activity);

		ArrayList<Poster> parcelableArrayListExtra = getIntent()
				.getParcelableArrayListExtra(KEY_VOTES);
		initializationUI(parcelableArrayListExtra);

	}

	private void initializationUI(ArrayList<Poster> result) {
		String headerText = getIntent().getStringExtra(KEY_HEADER);

		ListView listView = (ListView) findViewById(R.id.listView1);
		listView.setAdapter(new VotesAdapter(VotesActivity.this, 0, result));
		listView.setOnItemClickListener(this);

		TextView txt_header = (TextView) findViewById(R.id.txt_header);
		txt_header.setText(headerText);
	}

	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.btn_back:
			onBackPressed();
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub

	}

}
