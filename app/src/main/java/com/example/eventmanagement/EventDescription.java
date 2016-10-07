package com.example.eventmanagement;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class EventDescription extends AdminBaseActivity{
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;
	private static int RESULT_LOAD_IMAGE = 1;
	TextView name,date,short_desc,org,society,phone,desc,venue,prize,name1,date1,short1,org1,phone1,desc1,venue1,prize1,photo1,society1,heading;
	Button modify,back,delete;
	private static final int CAMERA_REQUEST = 1;
	private static final int PICK_FROM_GALLERY = 2;
	byte[] imageName;
	int imageId;
	Bitmap theImage;
	ImageView image;
	 LoginDataBaseAdapter DBAdapter;
	 Event selectedEvent;
	 String event_id="";
	 String event_name="";
	 String event_desc="";
	 String event_short="";
	 String event_org="";
	 String event_prize="";
	 String event_venue="";
	 String event_contact="";
	 String event_date="";
	 String event_society="";
	 String id1="";
	 Images image_one;
	
	 
	
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.description);
	    navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items); // load
		// titles
		// from
		// strings.xml

navMenuIcons = getResources()
.obtainTypedArray(R.array.nav_drawer_icons);// load icons from
// strings.xml

set(navMenuTitles, navMenuIcons);
DBAdapter = new LoginDataBaseAdapter(this);
	 DBAdapter.open();
     //   Image image_One = new Image(BitmapFactory.decodeResource(),getResource,R.drawable.mars, 0);
	    heading = (TextView) findViewById(R.id.titledescription);
	    name = (TextView) findViewById(R.id.textUser);
	    date = (TextView) findViewById(R.id.textCreatedAt);
	    short_desc = (TextView) findViewById(R.id.textText);
	    society = (TextView) findViewById(R.id.society);
	    society1 =(TextView) findViewById(R.id.eventSociety);
	    org = (TextView) findViewById(R.id.texthead);
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
	   // photo1 = (TextView) findViewById(R.id.textPhoto);
	    image = (ImageView) findViewById(R.id.photo);
	 //   SharedPreferences user=PreferenceManager.getDefaultSharedPreferences(EventDescription.this);
    	//String abc=user.getString("user_name","hello");
    	//image.setImageBitmap(BitmapFactory.decodeFile(picturePath),0);
	 /* Bundle extras1 = getIntent().getExtras();
		   
		   if(extras1 !=null)
		      {
		   String id2 = extras1.getString("image");
		   String id3 = extras1.getString("e_name");
    	// image.setImageBitmap(BitmapFactory.decodeFile(id2));
		   Toast.makeText(getApplicationContext(), id3, Toast.LENGTH_LONG).show();*/

    	
    	
	    
	    
	    modify = (Button) findViewById(R.id.button2);
	    delete = (Button) findViewById(R.id.button1);
	    back = (Button) findViewById(R.id.button3);
	    back.setOnClickListener(new View.OnClickListener() {

	    	 
			public void onClick(View v) {
				AlertDialog.Builder alert_dialog=new AlertDialog.Builder(EventDescription.this);
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
	    
	    modify.setOnClickListener(new View.OnClickListener() {

	    	 
			public void onClick(View v) {
			 
				onEventModification();
				
			}
	});
	    
	   Bundle extras = getIntent().getExtras();
	   
	   if(extras !=null)
	      {
	  id1 = extras.getString("db1_id");
	 
	// Toast.makeText(getApplicationContext(), id1, Toast.LENGTH_LONG).show(); 
	 DBAdapter = new LoginDataBaseAdapter(this);
	 if(id1!=null){
		 DBAdapter.open();
		Cursor rs = DBAdapter.getData(id1); 
		if(rs.moveToFirst()){
			do{
				event_id = rs.getString(0);
				event_name = rs.getString(1);
				event_desc = rs.getString(2);
				event_short = rs.getString(3);
				event_society = rs.getString(4);
				event_date = rs.getString(5);
				event_venue = rs.getString(6);
				event_prize = rs.getString(7);
				event_org = rs.getString(8);
				event_contact = rs.getString(9);
			}while(rs.moveToNext());
		}
		DBAdapter.close();
		name.setText(event_name);
		society.setText(event_society);
		desc.setText(event_desc);
		short_desc.setText(event_short);
		date.setText(event_date);
		venue.setText(event_venue);
		prize.setText(event_prize);
		org.setText(event_org);
		phone.setText(event_contact);
		
		DBAdapter.open();
		Images image_One = DBAdapter.retriveImgDetails(event_name);
		image.setImageBitmap(image_One.getBitmap());
	    
	 }
	   }
	  
	    
	   delete.setOnClickListener(new View.OnClickListener(){

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
		
			AlertDialog.Builder alert_dialog=new AlertDialog.Builder(EventDescription.this);
    		alert_dialog.setTitle("Are You Sure You Want To Delete");
    		alert_dialog.setNegativeButton("Yes",new DialogInterface.OnClickListener(){
    			@Override
    			public void onClick(DialogInterface dialog,int which){
    				DBAdapter.open();
    				long id2 = Long.parseLong(id1);
    				DBAdapter.deleteEvent(id2);
    				DBAdapter.close();
    				Intent intent = new Intent(getApplicationContext(),CreateEvent.class);
    				startActivity(intent);
    				
    			}
    		});
alert_dialog.setPositiveButton("No",new DialogInterface.OnClickListener() {

@Override
public void onClick(DialogInterface dialog, int which) {
	// TODO Auto-generated method stub
	
	dialog.dismiss();
}
});
alert_dialog.show();
			
		}
		   
		   
	   });
	   }
	public void onEventModification(){
		 Cursor cursor = DBAdapter.getData(id1);
		 // Cursor res = (Cursor) listView.getItemAtPosition(position);
		 
		 String EventName = cursor.getString(cursor.getColumnIndex("ID"));
		 
		 Intent intent = new Intent(getApplicationContext(),ModifyEvent.class);
		   
		// Toast.makeText(getApplicationContext(), EventName, Toast.LENGTH_LONG).show(); 
		  intent.putExtra("db1_id", EventName);
		
		   startActivity(intent);
			
	}
     }
	
	