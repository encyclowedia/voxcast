package com.voxcast.utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.voxcast.constant.Constant;

public class AppPreference {

	private static AppPreference preference;
	private Context context;
	private SharedPreferences preferences;

	private AppPreference() {
	}

	private AppPreference(Context context) {
		this.context = context;
		preferences = context.getSharedPreferences(Constant.MY_PREF_NAME,
				Context.MODE_PRIVATE);
	}

	public static AppPreference getInstance(Context context) {
		if (preference == null) {
			preference = new AppPreference(context);
		}
		return preference;
	}

	// @Nullable
	public static AppPreference get() {
		return preference;
	}

	public void clear() {
		Editor edit = preferences.edit();
		edit.clear();
		commit(edit);
	}

	private void commit(Editor editor) {
		editor.commit();
	}

	public boolean isGetStartedScreenShown() {
		return preferences.getBoolean(Constant.MY_PREF_SPLASH_KEY, false);
	}

	public void setGetStartedScreenShown() {
		Editor editor = preferences.edit();
		editor.putBoolean(Constant.MY_PREF_SPLASH_KEY, true);
		commit(editor);
	}
}
