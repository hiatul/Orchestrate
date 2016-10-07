package com.example.eventmanagement;

import java.util.List;

import android.content.Intent;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ShowUsers extends AdminBaseActivity{
  TextView tv;
  private LoginDataBaseAdapter logindatabaseadapter;
  ListView listView;
  List list;
  private String[] navMenuTitles;
	private TypedArray navMenuIcons;

protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);

  setContentView(R.layout.users_list);
  tv = (TextView) findViewById(R.id.textViewusers);
  navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items); // load
	// titles
	// from
	// strings.xml

navMenuIcons = getResources()
.obtainTypedArray(R.array.nav_drawer_icons_admin);// load icons from
//strings.xml

set(navMenuTitles, navMenuIcons);
  logindatabaseadapter = new LoginDataBaseAdapter(this);
  logindatabaseadapter.open();
populateListView();
}

private void populateListView() {
	
	Cursor cursor= logindatabaseadapter.getAllUsers();
	String[] fromFieldNames = new String[] {LoginDataBaseAdapter.USER_NAME, 
	LoginDataBaseAdapter.USER_JOIN_DATE, LoginDataBaseAdapter.USER_EVENT_NAME, LoginDataBaseAdapter.USER_SOCIETY};
	int[] toViewIDs = new int[] {R.id.textUserName, R.id.textJoined, R.id.textEventName, R.id.textEventSocietyJoin};
	SimpleCursorAdapter dataAdapter;
	dataAdapter = new SimpleCursorAdapter(getBaseContext(), R.layout.user_display, cursor, fromFieldNames, toViewIDs,0);
	 listView = (ListView) findViewById(R.id.listUsers);
		// Assign adapter to ListView
	listView.setAdapter(dataAdapter);
	 listView.setOnItemClickListener(onItemClickListener);
}
	 
		
private OnItemClickListener onItemClickListener = new OnItemClickListener()
	  {
		  
	   @Override
	   public void onItemClick(AdapterView<?> listView, View view, 
	     int position, long id) {
		  String s = String.valueOf(id);
	   //Get the cursor, positioned to the corresponding row in the result set
		   Cursor cursor = logindatabaseadapter.getAllUserDetails(s);
	 // Cursor res = (Cursor) listView.getItemAtPosition(position);
	 String UserName = cursor.getString(cursor.getColumnIndex("USERNAME"));
	// Intent intent = new Intent();
	// String Event = cursor.getString(cursor.getColumnIndex("EVENT_NAME"));
	 Toast.makeText(getApplicationContext(), UserName, Toast.LENGTH_LONG).show();
	   
	 // intent.putExtra("user", UserName);
	
	 //  startActivity(intent);
					

			 
			   }
			  };
}
