package com.voxcast.fragment;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import com.voxcast.R;
import com.voxcast.activity.BaseActivity;
import com.voxcast.activity.HomeActivity;
import com.voxcast.adapter.PostAdapter;
import com.voxcast.listeners.OnPostClickListener;
import com.voxcast.listeners.OnScrollListener;

public class Trending extends BaseFragment implements OnPostClickListener {

	private View fragment1View;
	// Button homeFragmentBtn;
	Context context;
	private TextView headerText;
	protected int top;

	public Trending() {

	}

	public static Trending newInstance() {
		return new Trending();
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		context = getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// if (fragment1View == null)
		// Each row in the list stores country name, currency and flag
		fragment1View = inflater.inflate(R.layout.mypost, container, false);

		StickyListHeadersListView list = (StickyListHeadersListView) fragment1View
				.findViewById(R.id.list);
		((BaseActivity) getActivity()).setListView(list);
		setListenerToListView();
		String[] strings = new String[] { "a", "b", "a", "c", "d", "c" };

		PostAdapter ga = new PostAdapter(getActivity(), strings, this);
		list.setAdapter(ga);
		return fragment1View;
	}

	private void setListenerToListView() {
		StickyListHeadersListView list = (StickyListHeadersListView) fragment1View
				.findViewById(R.id.list);
		OnScrollListener listener = (OnScrollListener) list
				.getOnScrollListener();
		listener.setCustomListener(new AbsListView.OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {

			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				View childAt = view.getChildAt(/*
												 * view.getFirstVisiblePosition()
												 * % view.getChildCount()
												 */0);
				int top = childAt.getTop();

				int diff = Trending.this.top - top;

				if (diff < 0 && Math.abs(diff) < childAt.getHeight() / 2) {
					((HomeActivity) getActivity()).makeBarVisible();
				} else if (diff > 0 && Math.abs(diff) < childAt.getHeight() / 2) {
					((HomeActivity) getActivity()).makeBarInVisible();
				}

				Trending.this.top = top;

				// if(top<)

			}
		});
	}

	private void initViews() {
		/*
		 * homeFragmentBtn = (Button)
		 * fragment1View.findViewById(R.id.fragment1Btn);
		 * 
		 * homeFragmentBtn.setOnClickListener(new View.OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { // TODO Auto-generated method
		 * stub ((MainActivity) context)
		 * .changeViews(MainActivity.HOME_FRAGMENT); } });
		 */
	}

	@Override
	public void onPostCheckedChanged(BaseAdapter adapter, View convertView,
			int position, CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPostClick(BaseAdapter adapter, View convertView, View view,
			int position) {
		// TODO Auto-generated method stub

	}

}
