package com.voxcast.view;

import se.emilsjolander.stickylistheaders.WrapperViewList.LifeCycleListener;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

public class HeaderViewGroup extends RelativeLayout {

	private View view;

	class PLifeCycle implements LifeCycleListener {
		@Override
		public void onDispatchDrawOccurred(Canvas canvas) {
			drawChild(canvas, view, 0);
		}
	}

	public void setView(View view) {
		this.view = view;
	}

	public HeaderViewGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public HeaderViewGroup(Context context, AttributeSet attrs, int def) {
		super(context, attrs, def);
	}

}
