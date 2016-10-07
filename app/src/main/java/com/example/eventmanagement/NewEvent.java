package com.example.eventmanagement;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
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
import android.text.InputType;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import org.w3c.dom.Notation;

public class NewEvent  extends AdminBaseActivity implements OnClickListener {
    String subject = "New Event";
    String body = "";
    String to="";
    ImageView testimage;
	EditText editTextEventName,editTextDescription,editTextShortDescription, editTextDate,
	editTextVenue,editTextOrganizers,editTextPhone,editTextPrize;
	Spinner spin1;
	String item="";
	byte[] imageName;
	int imageId;
	Bitmap theImage;
	ArrayList<String> list = new ArrayList<String>();
	ArrayAdapter<String> dataAdapter;
	Button btnCreateEvent,btnImage;
	LoginDataBaseAdapter loginDataBaseAdapter;
	CreateEvent createEvent;
	ArrayList<String> listItems;
	private DatePickerDialog datePicker;
	private SimpleDateFormat dateFormatter;
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;
    private static int RESULT_LOAD_IMAGE = 1;
    ArrayList<Image> imageArry = new ArrayList<Image>();
	ContactImageAdapter imageAdapter;
    String picturePath="";   
    ListView dataList;
    String event_name="";
    Images image_one;
    String temp="";
    String [] emailList;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_event);
		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items); // load
		// titles
		// from
		// strings.xml

	navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);// load icons from
	// strings.xml

	set(navMenuTitles, navMenuIcons);
		loginDataBaseAdapter=new LoginDataBaseAdapter(this);
		loginDataBaseAdapter=loginDataBaseAdapter.open();
		dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
 
		editTextEventName=(EditText)findViewById(R.id.editTextEventName);
		editTextDescription=(EditText)findViewById(R.id.editDescription);
		editTextShortDescription=(EditText)findViewById(R.id.editTextShortDescription);
		spin1 = (Spinner) findViewById(R.id.societySpinner);
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
        dataAdapter = new ArrayAdapter<String>(NewEvent.this,R.layout.selected_item,list);
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
        
		btnImage=(Button)findViewById(R.id.buttonImage);
		btnImage.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				//dialog.show();
				// TODO Auto-generated method stub
			//	Image image_One = new Image(BitmapFactory.decodeResource(
				//		getResources(), R.drawable.mars), 0);
				//loginDataBaseAdapter.insertImage(image_One);
				//loginDataBaseAdapter.insertImage(byteArray);
				Intent i = new Intent(
						Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
						startActivityForResult(i, RESULT_LOAD_IMAGE);
				//Toast.makeText(getApplicationContext(), "Image Successfully Uploaded ", Toast.LENGTH_LONG).show();

			//	loginDataBaseAdapter.close();	
				
			}
		});
		btnCreateEvent.setOnClickListener(new View.OnClickListener() {
			 
			public void onClick(View v) {
				listItems = new ArrayList<String>();
				event_name=editTextEventName.getText().toString();
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
				//sendMail(userEmail, subject, body);
				
				  
				if(event_name.equals("")||description.equals("")||short_desc.equals("")||date.equals("")||venue.equals("")||organizers.equals("")||contacts.equals("")||prize.equals(""))
				{
						Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_LONG).show();
						return;
				}
				else
				{					    // Save the Data in Database
				    loginDataBaseAdapter.insertValues(event_name, description, short_desc, society, date, venue, prize, organizers, contacts);
				    SharedPreferences user=PreferenceManager.getDefaultSharedPreferences(NewEvent.this);
                    user.edit().putString("user_event", event_name).commit();
                    Images image_One = new Images(BitmapFactory.decodeFile(picturePath),event_name );
	   				loginDataBaseAdapter.insertImgDetails(image_One);
	   				SharedPreferences evnt=PreferenceManager.getDefaultSharedPreferences(NewEvent.this);
                    evnt.edit().putString("event_image", temp).commit();
				    Intent intent=new Intent(getApplicationContext(),CreateEvent.class);
			        intent.putExtra("image",picturePath);
			        intent.putExtra("e_name", event_name);
					startActivity(intent);
					finish();
					List<Registration> emails = loginDataBaseAdapter.getAllEmail(); 
					 for (Registration cn : emails) {
				            to = cn.getEmail();
				            }
					 emailList = to.split(",");
				          // Toast.makeText(getApplicationContext(),to, Toast.LENGTH_LONG).show();
				            Intent email = new Intent(Intent.ACTION_SEND);  
				           // email.putExtra(Intent.EXTRA_EMAIL, new String[]{ to}); 
				           // email.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{ to}); 
				            email.putExtra(android.content.Intent.EXTRA_EMAIL, emailList); 

				            email.putExtra(Intent.EXTRA_SUBJECT, subject);  
				            email.putExtra(Intent.EXTRA_TEXT, body);  
				 
				            //need this to prompts email client only  
				            email.setType("message/rfc822");  
				 
				            startActivity(Intent.createChooser(email, "Choose an Email client :")); 
					 
				      //  Log.d("Name: ", log);
				    
				    Toast.makeText(getApplicationContext(), "Event Successfully Created ", Toast.LENGTH_LONG).show();
				    
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
	 @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        super.onActivityResult(requestCode, resultCode, data);
	         
	        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
	            Uri selectedImage = data.getData();
	            String[] filePathColumn = { MediaStore.Images.Media.DATA };
	 
	            Cursor cursor = getContentResolver().query(selectedImage,
	                    filePathColumn, null, null, null);
						cursor.moveToFirst();
	 
	            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
	            picturePath = cursor.getString(columnIndex);
	         //  cursor.close();
	             
	             testimage = (ImageView) findViewById(R.id.imgView);
	           testimage.setImageBitmap(BitmapFactory.decodeFile(picturePath));
	          
	   			// Images image_One = new Images(BitmapFactory.decodeFile(picturePath),event_name );
				  // 				loginDataBaseAdapter.insertImgDetails(image_One);
				   				
	           // Intent i = new Intent();
	           //i.putExtra("image",picturePath);
	           //startActivity(i);
	             SharedPreferences user=PreferenceManager.getDefaultSharedPreferences(NewEvent.this);
                 user.edit().putString("user_image", picturePath).commit();
	           // Bitmap yourSelectedImage = BitmapFactory.decodeFile(picturePath);

	            //testimage.setImageBitmap(yourSelectedImage);
	          /*   ByteArrayOutputStream stream = new ByteArrayOutputStream();
	             image_one.compress(Bitmap.CompressFormat.PNG, 100, stream);
	             byte[] b = stream.toByteArray();
	             temp = Base64.encodeToString(b, Base64.DEFAULT);*/
	             
	        }
	     
	     
	    }
	}


