package com.example.eventmanagement;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AdminLogout extends BaseActivity {
	
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;
    Button b1;
    TextView tv;
    public static final String PREFS_NAME = "LoginPrefs";

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.logout);
		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items); // load
		// titles
		// from
		// strings.xml

navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons_admin);// load icons from
// strings.xml

set(navMenuTitles, navMenuIcons);
b1 = (Button) findViewById(R.id.buttonLogout);
tv = (TextView) findViewById(R.id.textViewLogout);
b1.setOnClickListener(new OnClickListener(){

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		logout(v);
	}
	
});

	}	
	public  void logout(View v){
		/*SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.remove("logged");
        editor.commit();*/
		  SharedPreferences sharedpreferences = getSharedPreferences(SignIn.MyPREFERENCES, Context.MODE_PRIVATE);
	      SharedPreferences.Editor editor = sharedpreferences.edit();
	      editor.clear();
	      editor.commit();
	      Toast.makeText(getApplicationContext(), "You are successfully logged out", Toast.LENGTH_LONG).show();
	      Intent i = new Intent(AdminLogout.this, SignIn.class);
	      startActivity(i);
	      finish();
	   }
	   
	 
}
