package com.voxcast.listeners;

import android.content.Context;
import android.view.OrientationEventListener;

public class OrientationListener extends OrientationEventListener {

	private int key;

	public OrientationListener(Context context, int rate) {
		super(context, rate);
	}

	@Override
	public void onOrientationChanged(int orientation) {

		// switch (key) {
		// case value:
		//
		// break;
		//
		// default:
		// break;
		// }

	}

	public void enable(int key) {
		super.enable();
		this.key = key;
	}

}
