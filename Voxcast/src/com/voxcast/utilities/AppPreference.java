package com.voxcast.utilities;

import org.json.JSONObject;

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

	
	public String isLoginResponse() {
		// TODO Auto-generated method stub
		return preferences.getString(Constant.MY_PREF_LOGIN, null);
	}
	public void setLoginResponse(String response, String name) {
		// TODO Auto-generated method stub
		Editor editor = preferences.edit();
		editor.putString(Constant.MY_PREF_LOGIN, response);
		editor.putString(Constant.MY_PREF_LOGINTYPE, name);
		commit(editor);
	}

	
	public String getLoginTyppe() {
		// TODO Auto-generated method stub
		return preferences.getString(Constant.MY_PREF_LOGINTYPE, null);
	}

	public void clearUserData() {
		// TODO Auto-generated method stub
		Editor editor = preferences.edit();
		editor.remove(Constant.MY_PREF_LOGIN);
		editor.remove(Constant.MY_PREF_LOGINTYPE);
		commit(editor);
	}

	public void setLogin(boolean b) {
		// TODO Auto-generated method stub
		Editor editor = preferences.edit();
		editor.putBoolean(Constant.MY_PREF_ISLOGIN, b);
	
		commit(editor);	
	}
	public boolean isLogin() {
		// TODO Auto-generated method stub
		return preferences.getBoolean(Constant.MY_PREF_ISLOGIN, false);
	}
}
