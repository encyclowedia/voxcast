package com.voxcast.fragment;

import java.util.ArrayList;

import com.voxcast.R;
import com.voxcast.adapter.CommentAdapter;

import android.app.Activity;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class CommentFragment extends Fragment {
	
	/** Items entered by the user is stored in this ArrayList variable */
	ArrayList<String> list = new ArrayList<String>();
	
	/** Declaring an ArrayAdapter to set items to ListView */
	CommentAdapter adapter;
	
	
    /** Called when the activity is first created. */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		final View view = inflater.inflate(R.layout.comment, container, false);

    	
     
        
        /** Setting a custom layout for the list activity */
      
        final ListView listview=(ListView)view.findViewById(R.id.list);
        /** Reference to the button of the layout main.xml */
        TextView Post = (TextView) view.findViewById(R.id.Post);
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");
        /** Defining the ArrayAdapter to set items to ListView */
        adapter = new CommentAdapter(getActivity(), list);
        listview.setAdapter(adapter);        
        /** Defining a click event listener for the button "Add" */
        OnClickListener listener = new OnClickListener() {			
			@Override
			public void onClick(View v) {								
				EditText edit = (EditText) view.findViewById(R.id.txtItem);
				list.add(edit.getText().toString());
				edit.setText("");				
				adapter.notifyDataSetChanged();
				listview.setSelection(adapter.getCount() - 1);
			}
		};
		
		/** Setting the event listener for the add button */
		Post.setOnClickListener(listener);
        
        /** Setting the adapter to the ListView */
       
    
		return view;
	}

}