package com.voxcast.utilities;

import com.voxcast.view.CircularProgressView;

import android.view.animation.Animation;
import android.view.animation.Transformation;

public class RoundAnimation extends Animation {

	private int progress;
	private CircularProgressView circularProgressView;

	public RoundAnimation(CircularProgressView circularProgressView,
			int progress) {
		this.circularProgressView = circularProgressView;

		this.progress = progress;

	}

	@Override
	protected void applyTransformation(float interpolatedTime, Transformation t) {
		super.applyTransformation(interpolatedTime, t);
		circularProgressView.setProgress((int) (progress * interpolatedTime));
	}

}
