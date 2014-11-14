package com.voxcast.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class HeaderViewGroup extends RelativeLayout {

	private float translateY;

	public HeaderViewGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public HeaderViewGroup(Context context, AttributeSet attrs, int def) {
		super(context, attrs, def);
	}

	public void handleScrollY(float scrollY) {
		translateY = scrollY;
		postInvalidate();
	}

	@Override
	protected void dispatchDraw(Canvas canvas) {
		canvas.save();
		canvas.translate(0, translateY);
		super.dispatchDraw(canvas);
		canvas.restore();
	}

}
