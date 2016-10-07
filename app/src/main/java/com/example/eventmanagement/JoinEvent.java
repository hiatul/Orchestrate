package com.example.eventmanagement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class JoinEvent extends BaseActivity{
EditText textUserName,textEventName,textJoinDate,textUserPhone, textUserSociety;
Button btnJoin;
LoginDataBaseAdapter dbadapter;
String id1 = "";
String id2="";
String user_name="";
String user_phone="";
String event_name = "";
String user_email ="";
String user_society ="";
String date="";
String event="";
String user="";
String subject = "Join Event";
String body = "";
String to = "escribir.karan@gmail.com";
ArrayList<String> listItems;
private String[] navMenuTitles;
private TypedArray navMenuIcons;

@Override
protected void onCreate(Bundle savedInstanceState) 
{
	super.onCreate(savedInstanceState);
	setContentView(R.layout.join_event);
	navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items_users); // load
	// titles
	// from
	// strings.xml

navMenuIcons = getResources()
.obtainTypedArray(R.array.nav_drawer_icons);// load icons from
// strings.xml

set(navMenuTitles, navMenuIcons);
	dbadapter=new LoginDataBaseAdapter(this);
	textUserName = (EditText) findViewById(R.id.editUserName);
	textEventName = (EditText) findViewById(R.id.editTextEventName);
	textJoinDate = (EditText) findViewById(R.id.editTextDate);
	textUserPhone = (EditText) findViewById(R.id.editTextUserContact);
	textUserSociety = (EditText) findViewById(R.id.editUserSocietyJoin);
	btnJoin = (Button) findViewById(R.id.buttonJoinEvent);
	textUserName.setEnabled(false);
	textUserName.setFocusable(false);
	textUserName.setClickable(false);
	textEventName.setEnabled(false);
	textEventName.setFocusable(false);
	textEventName.setClickable(false);
	textUserPhone.setEnabled(false);
	textUserPhone.setFocusable(false);
	textUserPhone.setClickable(false);
	textJoinDate.setEnabled(false);
	textJoinDate.setFocusable(false);
	textJoinDate.setClickable(false);
	textUserSociety.setEnabled(false);
	textUserSociety.setFocusable(false);
	textUserSociety.setClickable(false);
	//Bundle extras = getIntent().getExtras();
	//   if(extras !=null)
	   //   {
	 // id1 = extras.getString("db2_id");
	//  id2 =  extras.getString("db1_id");
	 Intent intentObject = getIntent();
	     event = intentObject.getStringExtra("eventid");
	    user = intentObject.getStringExtra("user");
	//Toast.makeText(getApplicationContext(), event, Toast.LENGTH_LONG).show(); 
	//Toast.makeText(getApplicationContext(), user, Toast.LENGTH_LONG).show(); 
	dbadapter=new LoginDataBaseAdapter(this);
	 if(event!=null){
		 
		 dbadapter.open();
		Cursor rs = dbadapter.getData(event); 
		if(rs.moveToFirst()){
			do{
				
				event_name = rs.getString(1);
				user_society = rs.getString(4);
			}while(rs.moveToNext());
		}
		
		//Toast.makeText(getApplicationContext(), event_name, Toast.LENGTH_LONG).show();
		textEventName.setText(event_name);
		
	 }
if(user!=null){
	 dbadapter.open();
	Cursor rs = dbadapter.getUserName(user); 
		if(rs.moveToFirst()){
			do{
				
				user_name = rs.getString(rs.getColumnIndex("USERNAME"));
				user_phone = rs.getString(rs.getColumnIndex("USERPHONE"));
				user_email = rs.getString(rs.getColumnIndex("USEREMAIL"));
				   
				   
				  

				   
				   
			}while(rs.moveToNext());
		}
		 SimpleDateFormat dateF = new SimpleDateFormat("EEE, d MMM yyyy", Locale.getDefault());
		 date = dateF.format(Calendar.getInstance().getTime());
	//	Toast.makeText(getApplicationContext(), user_email, Toast.LENGTH_LONG).show();
				textUserName.setText(user_name);
				textUserPhone.setText(user_phone);
				textJoinDate.setText(date);
				textUserSociety.setText(user_society);
}
btnJoin.setOnClickListener(new View.OnClickListener() {
	 
	public void onClick(View v) {
		listItems = new ArrayList<String>();
		String user_name1=textUserName.getText().toString();
		String event_name1=textEventName.getText().toString();
		String date1=textJoinDate.getText().toString();
		String user_contact=textUserPhone.getText().toString();
		String user_society = textUserSociety.getText().toString();
		body = "USERNAME = " + user_name1 + "\n" + "EVENTNAME = " + event_name1 + "\n" + "SOCIETY = " + user_society + "\n" + "DATE OF JOINING = " + date1 + "\n" + "CONTACT = " + user_contact;
		//emailid.edit().putString("user_email", abc).commit();
		//Toast.makeText(getApplicationContext(), body, Toast.LENGTH_LONG).show();
	  
		
		  
	if(user_name1.equals("")||event_name1.equals("")||date1.equals("")||user_contact.equals(""))
		{
				Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_LONG).show();
				return;
		}
		else
		{					    // Save the Data in Database
		    dbadapter.userJoin(user_name1,event_name1,date1,user_contact,user_society);
		    //SharedPreferences user=PreferenceManager.getDefaultSharedPreferences(JoinEvent.this);
			//String abc1=user.getString("user_name","hello");
		    Intent intent=new Intent(getApplicationContext(),UserEvent.class);
			startActivity(intent);
			finish();
		    Toast.makeText(getApplicationContext(), "Event Successfully Joined ", Toast.LENGTH_LONG).show();
		    Intent email = new Intent(Intent.ACTION_SEND);  
            email.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});  
            email.putExtra(Intent.EXTRA_SUBJECT, subject);  
            email.putExtra(Intent.EXTRA_TEXT, body);  
 
            //need this to prompts email client only  
            email.setType("message/rfc822");  
            startActivity(Intent.createChooser(email, "Choose an Email client :")); 
		   
		    
		}
		
		
	}
	
});

}
	      
	 
	
	


}

