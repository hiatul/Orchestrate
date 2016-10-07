package com.example.eventmanagement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class ModifyEvent extends AdminBaseActivity implements OnClickListener {
	String subject = "Modified Event";
    String body = "";
    String to="";
	EditText editTextEventName,editTextDescription,editTextShortDescription,editTextDate,
	editTextVenue,editTextOrganizers,editTextPhone,editTextPrize;
	Button btnCreateEvent;
	LoginDataBaseAdapter loginDataBaseAdapter;
	CreateEvent createEvent;
	long id;
	ArrayList<String> listItems;
	private DatePickerDialog datePicker;
	private SimpleDateFormat dateFormatter;
	Spinner spin1;
	String item="";
	ArrayList<String> list = new ArrayList<String>();
	ArrayAdapter<String> dataAdapter;
	String id1 = "";
	String event_name="";
	 String event_desc="";
	 String event_short="";
	 String event_org="";
	 String event_prize="";
	 String event_venue="";
	 String event_contact="";
	 String event_date="";
	 private String[] navMenuTitles;
		private TypedArray navMenuIcons;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.modify_event);
		 navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items); // load
			// titles
			// from
			// strings.xml

	navMenuIcons = getResources()
	.obtainTypedArray(R.array.nav_drawer_icons);// load icons from
	// strings.xml

	set(navMenuTitles, navMenuIcons);
		loginDataBaseAdapter=new LoginDataBaseAdapter(this);
		
		dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
 
		editTextEventName=(EditText)findViewById(R.id.editTextEventName);
		editTextDescription=(EditText)findViewById(R.id.editDescription);
		editTextShortDescription=(EditText)findViewById(R.id.editTextShortDescription);
		spin1 = (Spinner) findViewById(R.id.societySpin);
		editTextDate=(EditText) findViewById(R.id.editTextDate);
		editTextVenue=(EditText) findViewById(R.id.editTextVenue);
		editTextOrganizers=(EditText) findViewById(R.id.editTextOrganizer);
		editTextPhone=(EditText) findViewById(R.id.editTextContacts);
		editTextPrize=(EditText) findViewById(R.id.editTextPrize);
		btnCreateEvent=(Button)findViewById(R.id.buttonCreateEvent);
		editTextEventName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.edit, 0, 0, 0);
		editTextDescription.setCompoundDrawablesWithIntrinsicBounds(R.drawable.edit, 0, 0, 0);
		editTextShortDescription.setCompoundDrawablesWithIntrinsicBounds(R.drawable.edit, 0, 0, 0);
		editTextOrganizers.setCompoundDrawablesWithIntrinsicBounds(R.drawable.organiser, 0, 0, 0);
		editTextDate.setCompoundDrawablesWithIntrinsicBounds(R.drawable.date, 0, 0, 0);
		editTextPhone.setCompoundDrawablesWithIntrinsicBounds(R.drawable.phone, 0, 0, 0);
		editTextPrize.setCompoundDrawablesWithIntrinsicBounds(R.drawable.prize, 0, 0, 0);
		editTextVenue.setCompoundDrawablesWithIntrinsicBounds(R.drawable.location, 0, 0, 0);
		
		
		list.add("Choose Society");
		list.add("SCIE");
        list.add("ISTE");
        list.add("SAE");
        list.add("LUG");
        list.add("ACE");
        list.add("GOOGLE");
        list.add("CULTURAL COMMITEE");
        dataAdapter = new ArrayAdapter<String>(ModifyEvent.this,android.R.layout.simple_list_item_1,list);
        spin1.setAdapter(dataAdapter);
        spin1.setOnItemSelectedListener(new OnItemSelectedListener(){
        	@Override
        	public void onItemSelected(AdapterView<?>arg0,View
        			arg1, int arg2, long arg3){
        		 item = dataAdapter.getItem(arg2);
        		//Toast.makeText(UpdateProfile.this,"Date selected is"	+item, Toast.LENGTH_LONG).show();
        	}
        	@Override
        	public void onNothingSelected(AdapterView<?>arg0){
        		
        	}
        });
		  Bundle extras = getIntent().getExtras();
		   if(extras !=null)
		      {
		  id1 = extras.getString("db1_id");
		// Toast.makeText(getApplicationContext(), id1, Toast.LENGTH_LONG).show(); 
		  loginDataBaseAdapter=new LoginDataBaseAdapter(this);
		 if(id1!=null){
			 loginDataBaseAdapter.open();
			Cursor rs = loginDataBaseAdapter.getData(id1); 
			if(rs.moveToFirst()){
				do{
					
					event_name = rs.getString(1);
					event_desc = rs.getString(2);
					event_short = rs.getString(3);
					event_date = rs.getString(5);
					event_venue = rs.getString(6);
					event_prize = rs.getString(7);
					event_org = rs.getString(8);
					event_contact = rs.getString(9);
				}while(rs.moveToNext());
			}
			
		//	Toast.makeText(getApplicationContext(), event_name, Toast.LENGTH_LONG).show();
			editTextEventName.setText(event_name);
			editTextDescription.setText(event_desc);
			editTextShortDescription.setText(event_short);
			editTextDate.setText(event_date);
			editTextVenue.setText(event_venue);
			editTextOrganizers.setText(event_org);
			editTextPhone.setText(event_contact);
			editTextPrize.setText(event_prize);
		      }
		      }
		   loginDataBaseAdapter=loginDataBaseAdapter.open();
		btnCreateEvent.setOnClickListener(new View.OnClickListener() {
			 
			public void onClick(View v) {
				listItems = new ArrayList<String>();
				String event_name=editTextEventName.getText().toString();
				String description=editTextDescription.getText().toString();
				String short_desc=editTextShortDescription.getText().toString();
				String society = item;
				String contacts=editTextPhone.getText().toString();
				String date=editTextDate.getText().toString();
				String venue=editTextVenue.getText().toString();
				String prize=editTextPrize.getText().toString();
				String organizers=editTextOrganizers.getText().toString();
				body = "EVENTNAME = " + event_name + "\n" + "DATE = " + date + "\n" + "Society = " + society + "\n"
						 + "VENUE = " + venue + "\n" + "For More Details see Event Notice Page";
				
				  
				if(event_name.equals("")||description.equals("")||short_desc.equals("")||date.equals("")||venue.equals("")||organizers.equals("")||contacts.equals("")||prize.equals(""))
				{
						Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_LONG).show();
						return;
				}
				else
				{	
					id = Long.parseLong(id1);
					// Save the Data in Database
				    loginDataBaseAdapter.updateEvent(id, event_name, description, short_desc, society, date, venue, prize, organizers, contacts);
				    Intent intent=new Intent(getApplicationContext(),CreateEvent.class);
					startActivity(intent);
					finish();
				    Toast.makeText(getApplicationContext(), "Event Successfully Updated", Toast.LENGTH_LONG).show();
				    List<Registration> emails = loginDataBaseAdapter.getAllEmail(); 
					 for (Registration cn : emails) {
				            to = cn.getEmail();}
				                // Writing Contacts to log
				           Toast.makeText(getApplicationContext(),to, Toast.LENGTH_LONG).show();
				            Intent email = new Intent(Intent.ACTION_SEND);  
				           // email.putExtra(Intent.EXTRA_EMAIL, new String[]{ to}); 
				            email.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{ to}); 

				            email.putExtra(Intent.EXTRA_SUBJECT, subject);  
				            email.putExtra(Intent.EXTRA_TEXT, body);  
				 
				            //need this to prompts email client only  
				            email.setType("message/rfc822");  
				 
				            startActivity(Intent.createChooser(email, "Choose an Email client :")); 
					 
				    
				}
				
				addItems();
			}
			
		});
		findViewsById();
		 setDateTimeField();
		}
	public void addItems(){
	 String society = item;
     String event_name=editTextEventName.getText().toString();
     String short_desc=editTextShortDescription.getText().toString();
     String date=editTextDate.getText().toString();
	listItems.add(event_name);
	listItems.add(short_desc);
	listItems.add(date);
	listItems.add(society);
	}
	 
	private void findViewsById() {
		editTextDate=(EditText) findViewById(R.id.editTextDate);  
		editTextDate.setInputType(InputType.TYPE_NULL);
		
	}
	private void setDateTimeField() {
		editTextDate.setOnClickListener(this);
		Calendar newCalendar = Calendar.getInstance();
        datePicker = new DatePickerDialog(this, new OnDateSetListener() {
 
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                editTextDate.setText(dateFormatter.format(newDate.getTime()));
            }
 
        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
	}
	@Override
	public void onClick(View view) {
		 if(view == editTextDate) {
	            datePicker.show();
		
	}
	}

}
