package com.voxcast.view;

import java.io.File;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class Utils {

	static Typeface findTypeface(Context context, String initPath,
			String typeface) {
		AssetManager assets = context.getAssets();
		return Typeface.createFromAsset(assets, (initPath + File.separator)
				+ typeface);
	}

	public static float getScreenDensity(Context context) {
		WindowManager manager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		manager.getDefaultDisplay().getMetrics(outMetrics);
		return outMetrics.density;
	}

}
