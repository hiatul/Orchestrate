package com.example.eventmanagement;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class UserEventDescription extends BaseActivity{
	TextView name,date,short_desc,org,society,phone,desc,venue,prize,name1,date1,short1,org1,phone1,desc1,venue1,prize1,society1,title;
	ImageView image;
	Button join,back;
	 LoginDataBaseAdapter DBAdapter;
	 Event selectedEvent;
	 String event_name="";
	 String event_desc="";
	 String event_short="";
	 String event_org="";
	 String event_prize="";
	 String event_venue="";
	 String event_contact="";
	 String event_date="";
	 String event_society="";
	 String event_names = "";
	 String id1="";
	int id2;
	 String ename="";
	String UserName;
	 String user="";
	 String u_name="";
	long id;
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;
    Images image_One;
    String event_id="";
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.user_event_description);
	    navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items_users); // load
		// titles
		// from
		// strings.xml

	navMenuIcons = getResources()
	.obtainTypedArray(R.array.nav_drawer_icons);// load icons from
	// strings.xml

	set(navMenuTitles, navMenuIcons);
	    title=(TextView) findViewById(R.id.titledescription1);
	    name = (TextView) findViewById(R.id.textUser);
	    date = (TextView) findViewById(R.id.textCreatedAt);
	    short_desc = (TextView) findViewById(R.id.textText);
	    org = (TextView) findViewById(R.id.texthead);
	    society = (TextView) findViewById(R.id.society_label);
	    society1 = (TextView) findViewById(R.id.eventSociety_label);
	    phone = (TextView) findViewById(R.id.textContact);
	    desc = (TextView) findViewById(R.id.descText);
	    venue = (TextView) findViewById(R.id.textVenue);
	    prize = (TextView) findViewById(R.id.textPrize);
	    name1 = (TextView) findViewById(R.id.titleLabel);
	    date1 = (TextView) findViewById(R.id.dateLabel);
	    short1 = (TextView) findViewById(R.id.shortLabel);
	    org1 = (TextView) findViewById(R.id.orgLabel);
	    phone1 = (TextView) findViewById(R.id.contactLabel);
	    desc1 = (TextView) findViewById(R.id.descLabel);
	    venue1 = (TextView) findViewById(R.id.venueLabel);
	    prize1 = (TextView) findViewById(R.id.prizeLabel);
	    join = (Button) findViewById(R.id.Eventjoin);
	    image = (ImageView) findViewById(R.id.photo_user);
	   
	    back = (Button) findViewById(R.id.button3);
	    back.setOnClickListener(new View.OnClickListener() {

	    	 
			public void onClick(View v) {
				AlertDialog.Builder alert_dialog=new AlertDialog.Builder(UserEventDescription.this);
        		alert_dialog.setTitle("Confirmation...");
        		alert_dialog.setNegativeButton("Cancel",new DialogInterface.OnClickListener(){
        			@Override
        			public void onClick(DialogInterface dialog,int which){
        			}
        		});
alert_dialog.setPositiveButton("Leavecurrent",new DialogInterface.OnClickListener() {
	
	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		finish();
	}
});
alert_dialog.show();
			  //Intent intent = new Intent(getApplicationContext(),EventNavigation.class);
			// startActivity(intent);
			}
	});
	    
	    join.setOnClickListener(new View.OnClickListener() {

	    	 
			public void onClick(View v) {
			 
				onJoinUser();
				//onJoinEvent();
			}
	});
	    
	   Bundle extras = getIntent().getExtras();
	   
	   if(extras !=null)
	      {
		
	  id1 = extras.getString("db1_id");
	  u_name=extras.getString("u_name");
	  ename=extras.getString("e_name");
	 
	//Toast.makeText(getApplicationContext(), u_name, Toast.LENGTH_LONG).show(); 
	 DBAdapter = new LoginDataBaseAdapter(this);
	 if(id1!=null){
		 if(ename!=null){
		 DBAdapter.open();
		 Cursor c = DBAdapter.getEventData(ename);
		Cursor rs = DBAdapter.getData(id1); 
		if(rs.moveToFirst()){
			do{
				event_name = rs.getString(1);
				event_desc = rs.getString(2);
				event_short = rs.getString(3);
				event_society = rs.getString(4);
				event_date = rs.getString(5);
				event_venue = rs.getString(6);
				event_prize = rs.getString(7);
				event_org = rs.getString(8);
				event_contact = rs.getString(9);
				//image_One = DBAdapter.retriveImgDetails(event_name);
				//
			}while(rs.moveToNext());
		}
		DBAdapter.close();
		// Toast.makeText(getApplicationContext(),(CharSequence) image_One , Toast.LENGTH_LONG).show(); 
		name.setText(event_name);
		desc.setText(event_desc);
		society.setText(event_society);
		short_desc.setText(event_short);
		date.setText(event_date);
		venue.setText(event_venue);
		prize.setText(event_prize);
		org.setText(event_org);
		phone.setText(event_contact);
		DBAdapter.open();
		Images image_One = DBAdapter.retriveImgDetails(event_name);
		image.setImageBitmap(image_One.getBitmap());
		 //image.setImageBitmap(image_One.getBitmap()); 
	 }
	      }
	 
	// image.setImageBitmap(image_One.getBitmap()); 
	      }
	 }
	      
	public void onJoinUser(){
		DBAdapter = new LoginDataBaseAdapter(this);
		
			 
		DBAdapter.open();
		 
		    
		
			/*Cursor rs = DBAdapter.getUserName(UserName); 
			
			if(rs.getCount()>0)
				do{
					user = rs.getString(rs.getColumnIndex(LoginDataBaseAdapter.USERNAME));
				}while(rs.moveToNext());
	
		 Toast.makeText(getApplicationContext(), user, Toast.LENGTH_LONG).show(); 	*/  
	Intent intent = new Intent(getApplicationContext(),JoinEvent.class);
		 
		
		Cursor cs  = DBAdapter.getData(id1);
		String EventName = cs.getString(0);
		 
		//Toast.makeText(getApplicationContext(), EventName, Toast.LENGTH_LONG).show(); 
	intent.putExtra("user", u_name);
	intent.putExtra("eventid",EventName);
	
		startActivity(intent);
		
		  
		
			
	}
	
}
