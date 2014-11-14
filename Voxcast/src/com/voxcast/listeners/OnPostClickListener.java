package com.voxcast.listeners;

import android.view.View;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;

public interface OnPostClickListener {

	public void onPostCheckedChanged(BaseAdapter adapter, View convertView,
			int position, CompoundButton buttonView, boolean isChecked);

	public void onPostClick(BaseAdapter adapter, View convertView, View view,
			int position);

}
