package com.voxcast.listeners;

import android.widget.AbsListView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.PauseOnScrollListener;

public class OnScrollListener extends PauseOnScrollListener {

	private android.widget.AbsListView.OnScrollListener customListener;

	public OnScrollListener(ImageLoader imageLoader, boolean pauseOnScroll,
			boolean pauseOnFling) {
		super(imageLoader, pauseOnScroll, pauseOnFling);

	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		super.onScrollStateChanged(view, scrollState);
		if (customListener != null) {
			customListener.onScrollStateChanged(view, scrollState);
		}
	}

	public void setCustomListener(
			android.widget.AbsListView.OnScrollListener customListener) {
		this.customListener = customListener;
	}

	public android.widget.AbsListView.OnScrollListener getCustomListener() {
		return customListener;
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		super.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
		if (customListener != null) {
			customListener.onScroll(view, firstVisibleItem, visibleItemCount,
					totalItemCount);
		}
	}

}
