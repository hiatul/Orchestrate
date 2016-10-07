package com.example.eventmanagement;



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
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.widget.SimpleCursorAdapter;


public class UserJoinEvent extends BaseActivity {
      private LoginDataBaseAdapter logindatabaseadapter;
    ListView listView;
    TextView tv;
String event_name="";
String event="";
private String[] navMenuTitles;
private TypedArray navMenuIcons;
SimpleCursorAdapter dataAdapter;

@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.user_join_event);
    
    navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items_users); // load
	// titles
	// from
	// strings.xml

navMenuIcons = getResources()
.obtainTypedArray(R.array.nav_drawer_icons);// load icons from
// strings.xml

set(navMenuTitles, navMenuIcons);
    logindatabaseadapter = new LoginDataBaseAdapter(this);
    logindatabaseadapter.open();
    tv = (TextView) findViewById(R.id.timelineEvent);
    SharedPreferences user=PreferenceManager.getDefaultSharedPreferences(UserJoinEvent.this);
	String abc=user.getString("event_name","hello");
  //  Toast.makeText(getApplicationContext(), abc, Toast.LENGTH_LONG).show();

    Intent intentObject = getIntent();
    event = intentObject.getStringExtra("u_name");
    if(abc!=null)
    //if(event!=null)
    	
	
 populateListView();
}

private void populateListView() {
	Cursor cursor =  logindatabaseadapter.getAllEventRows();
	String[] fromFieldNames = new String[] {LoginDataBaseAdapter.EVENT_NAME, 
			LoginDataBaseAdapter.DATE, LoginDataBaseAdapter.SHORT_DESCRIPTION, LoginDataBaseAdapter.SOCIETY};
	int[] toViewIDs = new int[] {R.id.textUser1, R.id.textCreatedAt1, R.id.textText1, R.id.textSociety1};
	 
	dataAdapter = new SimpleCursorAdapter(getBaseContext(), R.layout.user_event, cursor, fromFieldNames, toViewIDs,0);
	 listView = (ListView) findViewById(R.id.list_data_user1);
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
		 	 Intent intent = new Intent(getApplicationContext(),UserEventDescription.class);
		 	 String Event = cursor.getString(cursor.getColumnIndex("EVENT_NAME"));
		 	 Toast.makeText(getApplicationContext(), Event, Toast.LENGTH_LONG).show();
		 //	Images image_One = logindatabaseadapter.retriveImgDetails(event_name);
		 	  intent.putExtra("db1_id", EventName);
		 	  intent.putExtra("e_name", Event);
		 	  intent.putExtra("u_name", event);
		 	 // intent.putExtra("image", image_One);
		 	  startActivity(intent);
		 					

		 			 
		 			   }
		 			  };
	@Override
	    public boolean onCreateOptionsMenu(Menu menu) {
		 // Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
	    SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
	    SearchView searchView = (SearchView) menu.findItem(R.id.action_search) .getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        SearchView.OnQueryTextListener textChangeListener = new SearchView.OnQueryTextListener()
        {
        	@Override
            public boolean onQueryTextChange(String newText)
            {
                // this is your adapter that will be filtered
                dataAdapter.getFilter().filter(newText);
                System.out.println("on text chnge text: "+newText);
                return true;
            }
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                // this is your adapter that will be filtered
               dataAdapter.getFilter().filter(query);
                System.out.println("on query submit: "+query);
                return true;
            }
        };
        searchView.setOnQueryTextListener(textChangeListener);
	    return super.onCreateOptionsMenu(menu);
		 }
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
