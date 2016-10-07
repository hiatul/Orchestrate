package com.example.eventmanagement;

import java.util.List;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.widget.SimpleCursorAdapter;


public class CreateEvent extends AdminBaseActivity{
      private LoginDataBaseAdapter logindatabaseadapter;
    ListView listView;
    Button btn;
    List list;
    private String[] navMenuTitles;
	private TypedArray navMenuIcons;
	String picturePath="";
	 String id1,id2;
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
 
    setContentView(R.layout.create_event);
    Bundle extras = getIntent().getExtras();
	   
	   if(extras !=null)
	      {
	  id1 = extras.getString("image");
	 id2 = extras.getString("e_name");
	      }
    navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items); // load
	// titles
	// from
	// strings.xml

navMenuIcons = getResources()
.obtainTypedArray(R.array.nav_drawer_icons_admin);// load icons from
// strings.xml

set(navMenuTitles, navMenuIcons);
    logindatabaseadapter = new LoginDataBaseAdapter(this);
    logindatabaseadapter.open();
    btn = (Button) findViewById(R.id.Eventcreate);
  
    btn.setOnClickListener(new View.OnClickListener() {
		 
		public void onClick(View v) {
		 Intent intent = new Intent(getApplicationContext(),NewEvent.class);
			
		  startActivity(intent);
		  finish();
		}
});
 // displayListView(); 
 populateListView();
}

private void populateListView() {
	
	Cursor cursor= logindatabaseadapter.getAllRows();
	String[] fromFieldNames = new String[] {LoginDataBaseAdapter.EVENT_NAME, 
	LoginDataBaseAdapter.DATE, LoginDataBaseAdapter.SHORT_DESCRIPTION, LoginDataBaseAdapter.SOCIETY};
	int[] toViewIDs = new int[] {R.id.textUser, R.id.textCreatedAt, R.id.textSociety, R.id.textText };
	SimpleCursorAdapter dataAdapter;
	dataAdapter = new SimpleCursorAdapter(getBaseContext(), R.layout.event, cursor, fromFieldNames, toViewIDs,0);
	//d = new SimpleCursorAdapter(null, 0, cursor, fromFieldNames, toViewIDs, 0);
	 listView = (ListView) findViewById(R.id.list_data);
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
		   Cursor cursor = logindatabaseadapter.getData(s);
	 // Cursor res = (Cursor) listView.getItemAtPosition(position);
	 String EventName = cursor.getString(cursor.getColumnIndex("ID"));
	 String Event = cursor.getString(cursor.getColumnIndex("EVENT_NAME"));
	 SharedPreferences user=PreferenceManager.getDefaultSharedPreferences(CreateEvent.this);
     user.edit().putString("event_name", EventName).commit();
	 Intent intent = new Intent(getApplicationContext(),EventDescription.class);
	
	 Toast.makeText(getApplicationContext(), Event, Toast.LENGTH_LONG).show();
	   
	  intent.putExtra("db1_id", EventName);
	  intent.putExtra("image", id1);
	  intent.putExtra("e_name", id2);
	   startActivity(intent);
	  /*SharedPreferences user=PreferenceManager.getDefaultSharedPreferences(CreateEvent.this);
   	  String abc=user.getString("user_name","hello");		
   	 user.edit().putString("user_image", abc).commit();*/

      
       
			   }
			  };
			  public boolean onKeyDown(int keyCode, KeyEvent event) {
				    if (keyCode == KeyEvent.KEYCODE_BACK) {
				        exitByBackKey();

				        //moveTaskToBack(false);

				        return true;
				    }
				    return super.onKeyDown(keyCode, event);
				}

				protected void exitByBackKey() {

				    AlertDialog alertbox = new AlertDialog.Builder(this)
				    .setMessage("Do you want to exit application?")
				    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

				        // do something when the button is clicked
				        public void onClick(DialogInterface arg0, int arg1) {

				            finish();
				            //close();


				        }
				    })
				    .setNegativeButton("No", new DialogInterface.OnClickListener() {

				        // do something when the button is clicked
				        public void onClick(DialogInterface arg0, int arg1) {
				                       }
				    })
				      .show();

				}
			  
			  
         }
 

