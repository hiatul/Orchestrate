package com.example.eventmanagement;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class AboutSociety extends BaseActivity {
	
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;
    TextView tv1,tv2;
    ImageView im;
    Button b1;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about_society);
		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items_users); // load
		// titles
		// from
		// strings.xml

      navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);// load icons from
// strings.xml

   set(navMenuTitles, navMenuIcons);
   tv1 = (TextView) findViewById(R.id.aboutSociety);
   tv2 = (TextView) findViewById(R.id.textAboutSociety);
   im = (ImageView) findViewById(R.id.gne_image);
   b1 = (Button) findViewById(R.id.ButtonSociety);
   b1.setOnClickListener(new OnClickListener(){

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent i = new Intent(AboutSociety.this,SocietyList.class);
	    startActivity(i);
	}
	
});

	}

}
