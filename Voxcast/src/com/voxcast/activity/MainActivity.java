package com.voxcast.activity;

import org.json.JSONObject;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.algo.o2.fb.FacebookFragment.onTokenFetched;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.o2.googlesdk.GoogleFragment;
import com.o2.googlesdk.GoogleFragment.OnGoogleTokenFetched;
import com.voxcast.R;
import com.voxcast.constant.Constant;
import com.voxcast.fragment.LogoFragment;
import com.voxcast.model.LoginResponse;
import com.voxcast.utilities.AppPreference;
import com.voxcast.utilities.HttpRequest;
import com.voxcast.utilities.ServerListner;

public class MainActivity extends BaseActivity implements onTokenFetched,
		OnGoogleTokenFetched {

	private AnimationDrawable drawable;

	private FragmentTransaction fragmentTransaction;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState == null) {
			fragment = new LogoFragment();
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

	@Override
	public void onSuccess(String fbtoken) {
		// TODO Auto-generated method stub

		Log.d("fbtoken Token ", fbtoken);
		if (fbtoken != null && !fbtoken.isEmpty()) {
			GetRequestCall(fbtoken, "fb");
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
					Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
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
						AppPreference.getInstance(getApplicationContext())
						.setLogin(true);
						
						Intent intent = new Intent(MainActivity.this,
								HomeActivity.class);
						startActivity(intent);

					}

				}

			});
		}
		// TODO Auto-generated method stub

	}

	@Override
	public void onTokenRetrieved(String gptoken) {
		// TODO Auto-generated method stub
		if (gptoken != null && !gptoken.isEmpty()) {
			Log.d("gptoken Token ", gptoken);
			GetRequestCall(gptoken, "gp");
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub

		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
		case Constant.REQ_CODE_LINKEDIN:
			super.onActivityResult(requestCode, resultCode, data);
			String linkedintoken = data.getStringExtra("linkedintoken");
			if (linkedintoken != null && !linkedintoken.isEmpty()) {
				Log.d("linkedintoken Token ", linkedintoken);
				GetRequestCall(linkedintoken, "li");
			}
			break;
		case 0:
			FragmentManager fragmentmanager = getSupportFragmentManager();
			GoogleFragment frag = (GoogleFragment) fragmentmanager
					.findFragmentByTag("google_fragment");
			frag.onActivityResult(requestCode, resultCode, data);
			break;
		default:
			break;

		}

	}

}
