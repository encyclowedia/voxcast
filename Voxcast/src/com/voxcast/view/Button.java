package com.voxcast.view;

import com.voxcast.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DrawFilter;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Typeface;
import android.graphics.Path.Direction;
import android.graphics.Path.FillType;
import android.util.AttributeSet;

public class Button extends android.widget.Button {

	private Path path = new Path();
	private int rc;

	public Button(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.TextView);

		String typeface = a.getString(R.styleable.TextView_ctypeface);
		if (typeface != null) {
			FontManager.getInstance(context).setTypeFace(this, typeface);
		}

		// TypedArray b = context.obtainStyledAttributes(attrs, R.styleable.BG);
		//
		// rc = b.getDimensionPixelSize(R.styleable.BG_rc, 0);
	}

	public void setRoundedCorner(int size) {
		rc = size;
		postInvalidate();
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.save();
		path.addRoundRect(new RectF(0, 0, getWidth(), getHeight()), rc, rc,
				Direction.CCW);
		// canvas.clipPath(path);
		super.draw(canvas);
		canvas.restore();
	}

}
