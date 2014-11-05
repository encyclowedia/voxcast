package com.voxcast.utilities;

import java.io.File;

import android.content.Context;
import android.os.IBinder;
import android.view.inputmethod.InputMethodManager;

public class Utils {

	public static void hideKeyBoard(Context context, IBinder windowToken) {
		InputMethodManager imm = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(windowToken, 0);
	}

	public static File getExternalDirectory(Context context) {
		return getExternalCacheDirectory(context, "Image");
	}

	public static File getExternalCacheDirectory(Context context,
			String directoryInside) {
		File sdCard = context.getExternalCacheDir();
		File directory = new File(sdCard, directoryInside);
		if (!directory.exists())
			directory.mkdirs();
		return directory;
	}

}
