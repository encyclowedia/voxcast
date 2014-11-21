package com.voxcast.activity;

import java.io.IOException;

import org.json.JSONObject;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.algo.o2.fb.FacebookFragment.onTokenFetched;
import com.android.volley.VolleyError;
import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.UserRecoverableAuthException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.plus.Plus;
import com.google.gson.Gson;

import com.voxcast.R;
import com.voxcast.constant.Constant;

import com.voxcast.fragment.CommentFragment;
import com.voxcast.fragment.LogoFragment;
import com.voxcast.model.LoginResponse;
import com.voxcast.utilities.AppPreference;
import com.voxcast.utilities.HttpRequest;
import com.voxcast.utilities.ServerListner;
import com.voxcast.utilities.Utils;

public class MainActivity extends BaseActivity implements onTokenFetched,
		ConnectionCallbacks, OnConnectionFailedListener {

	private AnimationDrawable drawable;

	private FragmentTransaction fragmentTransaction;

	private GoogleApiClient mGoogleApiClient;

	private ConnectionResult mConnectionResult;
	private static final String scopes = "oauth2:"
			+ "https://www.googleapis.com/auth/plus.login https://www.googleapis.com/auth/userinfo."
			+ "profile https://www.googleapis.com/auth/plus.me https://www.googleapis.com/auth/userinfo.email";
	private boolean mIntentInProgress;

	private boolean mSignInClicked;

	private Activity mactivity;

	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState == null) {
			fragment = new LogoFragment();
			fragmentTransaction = getSupportFragmentManager()
					.beginTransaction();
			fragmentTransaction.add(R.id.layout_frames, fragment).commit();

		}
		mactivity=this;
		mGoogleApiClient = new GoogleApiClient.Builder(this)
				.addConnectionCallbacks(this)
				.addOnConnectionFailedListener(this).addApi(Plus.API)
				.addScope(Plus.SCOPE_PLUS_LOGIN).build();
		Utils.setmGoogleApiClient(mGoogleApiClient);
	}

	protected void onStart() {
		super.onStart();
		mGoogleApiClient.connect();
	}

	protected void onStop() {
		super.onStop();
		if (mGoogleApiClient.isConnected()) {
			mGoogleApiClient.disconnect();
		}
	}

	private void resolveSignInError() {
		if (mConnectionResult!=null && mConnectionResult.hasResolution()) {
			try {
				mIntentInProgress = true;

				mConnectionResult.startResolutionForResult(this,
						Constant.RC_SIGN_IN);
				progressDialog.show();
			} catch (SendIntentException e) {
				mIntentInProgress = false;
				mGoogleApiClient.connect();
			}
		}
	}

	@Override
	public void onConnectionFailed(ConnectionResult result) {
		if (!result.hasResolution()) {
			GooglePlayServicesUtil.getErrorDialog(result.getErrorCode(), this,
					0).show();
			return;
		}

		if (!mIntentInProgress) {
			// Store the ConnectionResult for later usage
			mConnectionResult = result;

			if (mSignInClicked) {
				// The user has already clicked 'sign-in' so we attempt to
				// resolve all
				// errors until the user is signed in, or they cancel.
				resolveSignInError();
			}
			
		}
		if (progressDialog != null && progressDialog.isShowing()) {
			progressDialog.dismiss();
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
					Toast.makeText(getApplicationContext(), error.toString(),
							Toast.LENGTH_LONG).show();
				}

				@Override
				public void onSuccess(JSONObject response) {
					// TODO Auto-generated method stub

					LoginResponse resp = new Gson().fromJson(
							response.toString(), LoginResponse.class);
					if (resp.getStatusCode() == 1) {
						finish();

						AppPreference.getInstance(getApplicationContext())
								.setLoginResponse(response.toString(), name);
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
	public void createprogress() {
		// TODO Auto-generated method stub
		progressDialog = new ProgressDialog(this);
		progressDialog.setCancelable(false);
		progressDialog.setMessage("Loading ...");
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub

		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
		case Constant.REQ_CODE_LINKEDIN:
			super.onActivityResult(requestCode, resultCode, data);

			if (data != null) {
				String linkedintoken = data.getStringExtra("linkedintoken");
				if (linkedintoken != null && !linkedintoken.isEmpty()) {
					Log.d("linkedintoken Token ", linkedintoken);
					GetRequestCall(linkedintoken, "li");
				}
			}

			break;
		case Constant.RC_SIGN_IN:

			if (resultCode != RESULT_OK) {
				mSignInClicked = false;
			}

			mIntentInProgress = false;

			if (!mGoogleApiClient.isConnecting()) {
				mGoogleApiClient.connect();
			}

		default:
			break;

		}

	}

	@Override
	public void onConnected(Bundle connectionHint) {
		// TODO Auto-generated method stub
		
		if (AppPreference.getInstance(getApplicationContext())
					.getGPToken()==null) {
			System.out.println("connected");
			mSignInClicked = false;
			if (progressDialog != null && progressDialog.isShowing()) {
				progressDialog.dismiss();
			}
			new RetrieveTokenTask().execute();
		}
		
		
	}

	@Override
	public void onConnectionSuspended(int cause) {
		// TODO Auto-generated method stub
		mGoogleApiClient.connect();
		if (progressDialog != null && progressDialog.isShowing()) {
			progressDialog.dismiss();
		}
	}
	public void signInWithGplus() {
		if (!mGoogleApiClient.isConnecting()) {
			mSignInClicked = true;
			resolveSignInError();
		}
	}
	private class RetrieveTokenTask extends AsyncTask<Void, Void, String> {

		@Override
		protected String doInBackground(Void... params) {
			String token = null;
			try {
				
				token = GoogleAuthUtil.getToken(mactivity,
						Plus.AccountApi.getAccountName(mGoogleApiClient),
						scopes);
				if (token != null && !token.toString().isEmpty()) {
					GetRequestCall(token.toString(), "gp");
					AppPreference.getInstance(getApplicationContext())
					.setgpToken(token);
				}
			} catch (UserRecoverableAuthException e) {
				mactivity.startActivityForResult(e.getIntent(), Constant.RC_SIGN_IN);
			} catch (IOException e) {
			} catch (GoogleAuthException e) {
			}
			return token;
		}

		
	}
	public void startfacebooklogin() {
		// TODO Auto-generated method stub
		
		Fragment lf = getFragmentManager().findFragmentByTag("LoginFragment");
		
	}
	

}
