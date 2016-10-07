package com.example.eventmanagement;

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
import android.widget.TextView;
import android.widget.Toast;

public class Profile extends BaseActivity {
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;

	TextView name,branch,email,phone,password,gender,name1,branch1,email1,phone1,pass1,gender1;
	Button back,update;
	 LoginDataBaseAdapter DBAdapter;
	 String user_name="";
	 String user_branch="";
	 String user_email="";
	 String user_phone="";
	 String user_password="";
	 String user_gender="";
	 String Name="";
	 
	 protected void onCreate(Bundle savedInstanceState) {
		    super.onCreate(savedInstanceState);
		    setContentView(R.layout.profile);
		    navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items_users); // load
			// titles
			// from
			// strings.xml

	navMenuIcons = getResources()
	.obtainTypedArray(R.array.nav_drawer_icons);// load icons from
	// strings.xml

	set(navMenuTitles, navMenuIcons);
		    name = (TextView) findViewById(R.id.Username);
		    branch = (TextView) findViewById(R.id.textBranch);
		    email = (TextView) findViewById(R.id.textEmailid);
		    password = (TextView) findViewById(R.id.textPassword);
		    phone = (TextView) findViewById(R.id.textPhone);
		    gender = (TextView) findViewById(R.id.textGender);
		    name1 = (TextView) findViewById(R.id.UserNameLabel);
		    branch1 = (TextView) findViewById(R.id.branchLabel);
		    email1 = (TextView) findViewById(R.id.emailLabel);
		    phone1 = (TextView) findViewById(R.id.phoneLabel);
		    pass1 = (TextView) findViewById(R.id.passwordLabel);
		    gender1 = (TextView) findViewById(R.id.genderLabel);
		    update = (Button) findViewById(R.id.UpdateButton);
		    back = (Button) findViewById(R.id.Backbutton);
		    back.setOnClickListener(new View.OnClickListener() {

		    	 
				public void onClick(View v) {
					AlertDialog.Builder alert_dialog=new AlertDialog.Builder(Profile.this);
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
			Intent i = new Intent(Profile.this,UserJoinEvent.class);
			startActivity(i);
		}
	});
	alert_dialog.show();
				}
		});
		    update.setOnClickListener(new View.OnClickListener() {

		    	 
				public void onClick(View v) {
				 Intent intent = new Intent(getApplicationContext(),UpdateProfile.class);
				 startActivity(intent);
				 finish();
					
				}
		});
		    SharedPreferences user=PreferenceManager.getDefaultSharedPreferences(Profile.this);
			String abc=user.getString("user_name","hello");
		//	Toast.makeText(getApplicationContext(), abc, Toast.LENGTH_LONG).show();
			 user.edit().putString("user_name", abc).commit();
			DBAdapter = new LoginDataBaseAdapter(this);
			 DBAdapter.open();
			 Cursor cursor = DBAdapter.getAllUserDetails(abc);
			 if(cursor.moveToFirst()){
					do{
						
						user_name = cursor.getString(1);
						user_branch = cursor.getString(2);
						user_email = cursor.getString(3);
						user_phone = cursor.getString(4);
						user_password = cursor.getString(5);
						user_gender = cursor.getString(6);
					}while(cursor.moveToNext());
			 }
			 DBAdapter.close();
				name.setText(user_name);
				branch.setText(user_branch);
				email.setText(user_email);
				phone.setText(user_phone);
				password.setText(user_password);
				gender.setText(user_gender);
				
			 
	 }
	
}
