package com.example.eventmanagement;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Scie extends BaseActivity {
	
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;
    TextView tv1,tv2;
    ImageView im;
    public static final String PREFS_NAME = "LoginPrefs";

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scie);
		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items_users); // load
		// titles
		// from
		// strings.xml

navMenuIcons = getResources()
.obtainTypedArray(R.array.nav_drawer_icons);// load icons from
// strings.xml

set(navMenuTitles, navMenuIcons);
tv1 = (TextView) findViewById(R.id.scie_heading);
tv2 = (TextView) findViewById(R.id.textScie);
im = (ImageView) findViewById(R.id.scie_image);
	}
}
