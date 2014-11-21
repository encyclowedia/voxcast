package com.voxcast.activity;

import org.json.JSONObject;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.RelativeLayout;

import com.algo.o2.fb.FacebookFragment.onTokenFetched;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.voxcast.R;
import com.voxcast.constant.Constant;
import com.voxcast.fragment.LoginFragment;
import com.voxcast.fragment.LogoFragment;
import com.voxcast.fragment.LogoutFragment;
import com.voxcast.model.LoginResponse;
import com.voxcast.utilities.AppPreference;
import com.voxcast.utilities.HttpRequest;
import com.voxcast.utilities.ServerListner;

public class LogoutActivity extends BaseActivity implements onTokenFetched {

	private AnimationDrawable drawable;

	private FragmentTransaction fragmentTransaction;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState == null) {
	
			fragment = new LogoutFragment();
			
			fragmentTransaction = getSupportFragmentManager()
					.beginTransaction();
			fragmentTransaction.add(R.id.layout_frames, fragment).commit();

		}

	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		if (hasFocus) {
			if (drawable != null) {
				drawable.stop();
				drawable = null;
			}

			RelativeLayout frameLayout = (RelativeLayout) findViewById(R.id.layout_main);
			drawable = (AnimationDrawable) frameLayout.getBackground();
			drawable.start();
		}
	}

	


	
	private void GetRequestCall(String fbtoken, final String name) {
		if (!fbtoken.isEmpty()) {
			String uri = String.format(Constant.URL + "/login/" + name
					+ "?accessToken=%1$s", fbtoken);
			HttpRequest.getGetRequest(uri, this, new ServerListner() {

				@Override
				public void onFailure(VolleyError error) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onSuccess(JSONObject response) {
					// TODO Auto-generated method stub

					LoginResponse resp = new Gson().fromJson(
							response.toString(), LoginResponse.class);
					if (resp.getStatusCode() == 1) {
						finish();
						
						AppPreference.getInstance(getApplicationContext())
						.setLoginResponse(response.toString(),name);
					
						finish();
//						Intent intent = new Intent(LogoutActivity.this,
//								HomeActivity.class);
//						startActivity(intent);

					}

				}

			});
		}
		// TODO Auto-generated method stub

	}

	

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub

		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
		case Constant.REQ_CODE_LINKEDIN:
			super.onActivityResult(requestCode, resultCode, data);
			String linkedintoken = data.getStringExtra("linkedintoken");
			if (linkedintoken != null) {
				Log.d("linkedintoken Token ", linkedintoken);

			}
			break;
	
		default:
			break;

		}

	}

	@Override
	public void onSuccess(String fbtoken) {
		// TODO Auto-generated method stub
		Log.d("fbtoken Token ", fbtoken);
		if (fbtoken != null && !fbtoken.isEmpty()) {
			GetRequestCall(fbtoken, "fb");
		}
	}

}
