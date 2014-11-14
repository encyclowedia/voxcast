package com.voxcast.activity;

import java.util.ArrayList;

import com.voxcast.R;
import com.voxcast.adapter.CommentAdapter;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class CommentActivity extends Activity {
	
	/** Items entered by the user is stored in this ArrayList variable */
	ArrayList<String> list = new ArrayList<String>();
	
	/** Declaring an ArrayAdapter to set items to ListView */
	CommentAdapter adapter;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        
        /** Setting a custom layout for the list activity */
        setContentView(R.layout.comment);
        ListView listview=(ListView)findViewById(R.id.list);
        /** Reference to the button of the layout main.xml */
        Button btn = (Button) findViewById(R.id.btnAdd);
        
        /** Defining the ArrayAdapter to set items to ListView */
        adapter = new CommentAdapter(this, list);
        listview.setAdapter(adapter);        
        /** Defining a click event listener for the button "Add" */
        OnClickListener listener = new OnClickListener() {			
			@Override
			public void onClick(View v) {								
				EditText edit = (EditText) findViewById(R.id.txtItem);
				list.add(edit.getText().toString());
				edit.setText("");				
				adapter.notifyDataSetChanged();
			}
		};
		
		/** Setting the event listener for the add button */
        btn.setOnClickListener(listener);
        
        /** Setting the adapter to the ListView */
       
    }
}