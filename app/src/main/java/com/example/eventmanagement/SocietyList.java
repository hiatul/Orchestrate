package com.example.eventmanagement;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SocietyList extends BaseActivity {
  ListView listView;
  TextView tv;
  private String[] navMenuTitles;
  private TypedArray navMenuIcons;
  Context context;
  ArrayList societies;
  public static int [] societyImages={R.drawable.scie,R.drawable.iste,R.drawable.sae};
  public static String [] societyNameList={"SCIE","ISTE","SAE","LUG","ACE","GOOGLE","CULTURAL COMITTEE","POETRY AND FINE ARTS CLUB"};

@Override
public void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.society_list);
  
  navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items_users); // load
	// titles
	// from
	// strings.xml

navMenuIcons = getResources()
.obtainTypedArray(R.array.nav_drawer_icons);// load icons from
//strings.xml

set(navMenuTitles, navMenuIcons);
context=this;
tv=(TextView) findViewById(R.id.societyHeading);
listView=(ListView) findViewById(R.id.society_list_data);
listView.setAdapter(new CustomAdapter(this, societyNameList));

}
}
