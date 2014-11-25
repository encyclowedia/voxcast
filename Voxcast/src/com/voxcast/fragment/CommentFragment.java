package com.voxcast.fragment;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.voxcast.R;
import com.voxcast.adapter.CommentAdapter;

public class CommentFragment extends BaseFragment implements OnClickListener {

	/** Items entered by the user is stored in this ArrayList variable */
	private ArrayList<String> list = new ArrayList<String>();

	/** Declaring an ArrayAdapter to set items to ListView */
	private CommentAdapter adapter;
	int layoutBottomh;

	protected int flag = 0;
	private ListView listview;
	private View view;

	/** Called when the activity is first created. */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_comment, container, false);

		listview = (ListView) view.findViewById(R.id.list);

		/** Reference to the button of the layout main.xml */
		Button btn_post = (Button) view.findViewById(R.id.btn_post);
		btn_post.setOnClickListener(this);

		TextView btn_close = (TextView) view.findViewById(R.id.headerComment);
		btn_close.setOnClickListener(this);

		list.add("a");
		list.add("a");
		list.add("a");
		list.add("a");

		/** Defining the ArrayAdapter to set items to ListView */
		adapter = new CommentAdapter(getActivity(), list);
		listview.setAdapter(adapter);

		/** Setting the event listener for the add button */

		/** Setting the adapter to the ListView */

		return view;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.headerComment:
			popCurrentFragmentOut();
			break;

		case R.id.btn_post:
			postComment();
			break;
		}

	}

	private void postComment() {
		EditText edit = (EditText) view.findViewById(R.id.txtItem);
		list.add(edit.getText().toString());
		edit.setText("");
		adapter.notifyDataSetChanged();
		listview.setSelection(adapter.getCount() - 1);
	}

}