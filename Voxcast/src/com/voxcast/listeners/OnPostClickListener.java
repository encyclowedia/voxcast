package com.voxcast.listeners;

import java.util.ArrayList;

import android.os.Parcelable;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;

public interface OnPostClickListener<T> {

	public void onPostCheckedChanged(ArrayList<T> arrayList, View convertView,
			int position, CompoundButton buttonView, boolean isChecked);

	public void onPostClick(ArrayList<T> arrayList, View convertView,
			View view, int position);

}
