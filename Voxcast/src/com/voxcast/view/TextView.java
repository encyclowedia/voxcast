package com.voxcast.view;

import com.voxcast.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class TextView extends android.widget.TextView {

	public TextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.TextView);

		String typeface = a.getString(R.styleable.TextView_ctypeface);
		if (typeface != null) {
			FontManager.getInstance(context).setTypeFace(this, typeface);
		}

	}

	// private Typeface findTypeface(String initPath, String typeface) {
	// AssetManager assets = getContext().getAssets();
	// try {
	// String[] list = assets.list(initPath);
	// for (int i = 0; i < list.length; i++) {
	// if (list[i].equalsIgnoreCase(typeface)) {
	// return Typeface.createFromAsset(assets,
	// (initPath.equalsIgnoreCase("") ? ""
	// : (initPath + File.separator)) + typeface);
	// } else {
	// try {
	// Typeface string = findTypeface(
	// (initPath.equalsIgnoreCase("") ? ""
	// : (initPath + File.separator))
	// + list[i], typeface);
	// if (string != null) {
	// return string;
	// }
	// } catch (Exception exception) {
	// }
	// }
	// }
	// } catch (IOException e) {
	// }
	// return getTypeface();
	// }

}
