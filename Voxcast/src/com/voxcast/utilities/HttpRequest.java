/**
 * Author: Ravi Tamada
 * URL: www.androidhive.info
 * twitter: http://twitter.com/ravitamada
 * */
package com.voxcast.utilities;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpHost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.voxcast.activity.VoxCastApp;
import com.voxcast.constant.Constant;

public class HttpRequest {

	public static void getGetRequest(String url, final Context context,
			final ServerListner listner) {

		try {
			JsonObjectRequest sr = new JsonObjectRequest(Request.Method.GET,
					url, null, new Response.Listener<JSONObject>() {

						@Override
						public void onResponse(JSONObject response) {

							listner.onSuccess(response);

						}

					}, new ErrorListener() {

						@Override
						public void onErrorResponse(VolleyError response) {
							listner.onFailure(response);
						}

					});
			// add it to the RequestQueue

			VoxCastApp.getInstance().addToRequestQueue(sr);
		} catch (Exception e) {
			Toast.makeText(context, "Please try again Later", Toast.LENGTH_LONG)
					.show();

		}

	}

	public static void getPostRequest(String url, final Context context,
			final ServerListner listner) throws JSONException {
		System.out.println(url);
		JSONObject par = new JSONObject();
		// par.put("uname",repo.getParams().getUsername());
		// par.put("password",repo.getParams().getPassword());
		// par.put("appId",repo.getParams().getAppId());
		// par.put("deviceInfo",repo.getParams().getDeviceId());
		// System.out.println(par);
		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
				Request.Method.POST, url, par,
				new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {

						listner.onSuccess(response);

					}

				}, new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError response) {
						listner.onFailure(response);
					}

				}) {
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				Map<String, String> params = new HashMap<String, String>();
				params.put("Auth", "algo9sum5xyab45tild;asdasd");
				params.put("Content-type", "application/json");
				System.out.println(params);
				return params;
			}
		};
		VoxCastApp.getInstance().addToRequestQueue(jsonObjectRequest);

	}

}
