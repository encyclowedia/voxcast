package com.voxcast.utilities;

import org.json.JSONObject;

import com.android.volley.VolleyError;

public interface ServerListner {

	void onFailure(VolleyError error);

	void onSuccess(JSONObject response);

}
