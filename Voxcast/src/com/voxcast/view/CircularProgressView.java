package com.voxcast.view;

import com.voxcast.R;

import android.R.integer;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class CircularProgressView extends View {

	private int radius = 50;
	private int progress = 120;

	private int strokeWidth = 15;
	private int backgroundBarColor = Color.GRAY;
	private int progressBarColor = Color.RED;

	private Paint backgroundBarPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	private Paint progressBarPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

	public CircularProgressView(Context context, AttributeSet set) {
		super(context, set);
		parseAttributes(context.obtainStyledAttributes(set,
				R.styleable.CircularProgressView));

		initPaint(backgroundBarPaint);
		initPaint(progressBarPaint);

		initColor();
	}

	private void parseAttributes(TypedArray obtainStyledAttributes) {
		radius = (int) obtainStyledAttributes.getDimension(
				R.styleable.CircularProgressView_viewRadius, radius);

		strokeWidth = (int) obtainStyledAttributes.getDimension(
				R.styleable.CircularProgressView_sWidth, strokeWidth);

		backgroundBarColor = (int) obtainStyledAttributes.getColor(
				R.styleable.CircularProgressView_backColor, backgroundBarColor);

		progressBarColor = (int) obtainStyledAttributes.getColor(
				R.styleable.CircularProgressView_progressColor,
				progressBarColor);
	}

	private void initColor() {
		backgroundBarPaint.setColor(backgroundBarColor);
		progressBarPaint.setColor(progressBarColor);
	}

	private void initPaint(Paint paint) {
		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth(strokeWidth);
		paint.setStrokeCap(Cap.ROUND);
		paint.setStrokeJoin(Join.ROUND);
	}

	/**
	 * @param progress
	 *            ranges from 0-100
	 */
	public void setProgress(int progress) {
		if (progress < 0) {
			progress = 0;
		}

		if (progress > 100) {
			progress = 100;
		}

		this.progress = (int) ((progress / 100f) * 270);

		postInvalidate();
	}

	public int getProgress() {
		return (int) ((progress / 270f) * 100);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int size = radius * 2 + strokeWidth;
		setMeasuredDimension(size, size);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		int halfStroke = strokeWidth / 2;
		int dia = radius * 2;
		RectF oval = new RectF(halfStroke, halfStroke, dia + halfStroke, dia
				+ halfStroke);

		canvas.drawArc(oval, 90, -270, false, backgroundBarPaint);
		canvas.drawArc(oval, 90, -progress, false, progressBarPaint);
	}
}
