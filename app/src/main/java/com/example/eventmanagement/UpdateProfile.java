package com.example.eventmanagement;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class UpdateProfile extends BaseActivity{
	EditText editTextUserName,editTextPassword,editTextConfirmPassword,editTextBranch,editTextEmail,editTextPhone;
	Button btnUpdateProfile;
    RadioButton radioButtonMale,radioButtonFemale;
    RadioGroup radioGender;
    Spinner spin1;
	String item="";
	ArrayList<String> list = new ArrayList<String>();
	ArrayAdapter<String> dataAdapter;
    int r_id;
	LoginDataBaseAdapter loginDataBaseAdapter;
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;
	String user_name="";
	String user_branch="";
	String user_email="";
	String user_phone="";
    String user_password="";
	String user_gender="";
    String id1 = "";
    String gender = "";
    private static final String usernameEmail = "escribir.karan@gmail.com";
    private static final String passwordEmail = "karan_7393";
    String subject = "Updated Profile";
    String body = "";
    private Pattern pattern;
	 private Matcher matcher;
    private static final String USERNAME_PATTERN = "^[a-z0-9_-]{3,15}$";
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.update_profile);
		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items_users); // load
		// titles
		// from
		// strings.xml

navMenuIcons = getResources()
.obtainTypedArray(R.array.nav_drawer_icons);// load icons from
// strings.xml

set(navMenuTitles, navMenuIcons);
		loginDataBaseAdapter=new LoginDataBaseAdapter(this);
		loginDataBaseAdapter=loginDataBaseAdapter.open();
 
		editTextUserName=(EditText)findViewById(R.id.TextUserName);
		editTextPassword=(EditText)findViewById(R.id.TextPassword);
		editTextConfirmPassword=(EditText)findViewById(R.id.TextConfirmPassword);
		//editTextBranch=(EditText) findViewById(R.id.TextBranch);
		spin1 = (Spinner) findViewById(R.id.branchSpinner);
		editTextEmail=(EditText) findViewById(R.id.TextEmail);
		editTextPhone=(EditText) findViewById(R.id.TextPhone);
		radioButtonMale=(RadioButton) findViewById(R.id.radioButtonMale);
		radioButtonFemale=(RadioButton) findViewById(R.id.radioButtonFemale);
		radioGender=(RadioGroup)   findViewById(R.id.radioGenders);
		btnUpdateProfile=(Button)findViewById(R.id.buttonUpdateProfile);
		editTextUserName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.person, 0, 0, 0);
		editTextPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.password, 0, 0, 0);
		editTextConfirmPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.password, 0, 0, 0);
		editTextEmail.setCompoundDrawablesWithIntrinsicBounds(R.drawable.mail, 0, 0, 0);
		editTextPhone.setCompoundDrawablesWithIntrinsicBounds(R.drawable.phone, 0, 0, 0);
		list.add("Choose Branch-");
		list.add("CE");
        list.add("CSE");
        list.add("EE");
        list.add("ECE");
        list.add("IT");
        list.add("ME");
        list.add("PE");
        list.add("MCA");
        dataAdapter = new ArrayAdapter<String>(UpdateProfile.this,android.R.layout.simple_list_item_1,list);
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
		SharedPreferences user=PreferenceManager.getDefaultSharedPreferences(UpdateProfile.this);
		String abc=user.getString("user_name","hello");
		  loginDataBaseAdapter=new LoginDataBaseAdapter(this);
		
			 loginDataBaseAdapter.open();
			Cursor rs = loginDataBaseAdapter.getAllUserDetails(abc); 
			if(rs.moveToFirst()){
				do{
					
					user_name = rs.getString(1);
					user_branch = rs.getString(2);
					user_email = rs.getString(3);
					user_phone = rs.getString(4);
					//user_password = rs.getString(5);
					//user_gender = rs.getString(6);
				}while(rs.moveToNext());
			}
					editTextUserName.setText(user_name);
					//spin1.setText(user_branch);
			//editTextBranch.setText(user_branch);
			editTextEmail.setText(user_email);
			editTextPhone.setText(user_phone);
			//editTextPassword.setText(user_password);
		      
		      
		   loginDataBaseAdapter=loginDataBaseAdapter.open();
		   btnUpdateProfile.setOnClickListener(new View.OnClickListener() {
				 
				public void onClick(View v) {
					// TODO Auto-generated method stub
               r_id=radioGender.getCheckedRadioButtonId();
                  if(r_id==radioButtonMale.getId())
                     {
	                    gender="Male";
                      }
                   else
                     {
	                     gender="Female";
                       }
					String userName=editTextUserName.getText().toString();
				//	String userBranch=editTextBranch.getText().toString();
					String userBranch = item;
					String userEmail=editTextEmail.getText().toString();
					String userPhone=editTextPhone.getText().toString();
					String password=editTextPassword.getText().toString();
					String confirmPassword=editTextConfirmPassword.getText().toString();
					String storedUser = loginDataBaseAdapter.Exist(userName);
					String storedEmail = loginDataBaseAdapter.EmailExist(userEmail);
					body = "You Have Successfully Updated Your Profile "+"\n" + "Your new Password is: "  + password + "\n" +
					"Your new username =" + userName;	
					sendMail(userEmail, subject, body);
					if (!isValidEmail(userEmail)) {
						editTextEmail.setError("Invalid Email");
					}
					if (!isValidPhone(userPhone)) {
						editTextPhone.setError("Invalid Phone No. Use +91 before No.");
					}
					if(!validate(userName)){
						editTextUserName.setError("Inavlid Username");
					}
					if(userName.equals(storedUser)){
						Toast.makeText(getApplicationContext(), "username already exists", Toast.LENGTH_LONG).show();
						return;
					}
					/*if(userEmail.equals(storedEmail)){
						Toast.makeText(getApplicationContext(), "email already exists", Toast.LENGTH_LONG).show();
						return;
					}*/
					  
					if(userName.equals("")||password.equals("")||confirmPassword.equals("")||userBranch.equals("")||userEmail.equals("")||userPhone.equals(""))
					{
							Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_LONG).show();
							return;
					}
					// check if both password matches
					if(!password.equals(confirmPassword))
					{
						Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
						return;
					}
					else
					{
						if(validate(userName)){
						if(isValidEmail(userEmail)){
							if(isValidPhone(userPhone)){
											    // Save the Data in Database
					    loginDataBaseAdapter.updateEntry(userName, password,userBranch,userEmail,userPhone,gender);
					    Toast.makeText(getApplicationContext(), "Account Successfully Updated ", Toast.LENGTH_LONG).show();
					  Intent intent = new Intent(getApplicationContext(),Profile.class);
					  startActivity(intent);
					  finish();
							}
						}
						}
					}
					
					
					    }
				
			});
		   }
	private void sendMail(String email, String subject, String messageBody) {
        Session session = createSessionObject();
       
        try {
            Message message = createMessage(email, subject, messageBody, session);
            new SendMailTask().execute(message);
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
	 private Message createMessage(String email, String subject, String messageBody, Session session) throws MessagingException, UnsupportedEncodingException {
	        Message message = new MimeMessage(session);
	       // Toast.makeText(UpdateProfile.this,email, Toast.LENGTH_LONG).show();
	        message.setFrom(new InternetAddress("escribir.karan@gmail.com"));
	       message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
	      // message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("aashitadutta@gmail.com"));

	        message.setSubject(subject);
	        message.setText(messageBody);
	        return message;
	    }
	 private Session createSessionObject() {
	        Properties properties = new Properties();
	        properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.socketFactory.port", "465");
            properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.port", "465");


	        return Session.getInstance(properties, new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(usernameEmail, passwordEmail);
	            }
	        });
	    }

	    private class SendMailTask extends AsyncTask<Message, Void, Void> {
	    // private ProgressDialog progressDialog;

	      @Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	    //   progressDialog = ProgressDialog.show(SignUpActivity.this, "Please wait", "Sending mail", true, false);
	        }

	        @Override
	        protected void onPostExecute(Void aVoid) {
	            super.onPostExecute(aVoid);
	   //      progressDialog.dismiss();
	        }

	        @Override
	        protected Void doInBackground(Message... messages) {
	            try {
	                Transport.send(messages[0]);
	            } catch (MessagingException e) {
	                e.printStackTrace();
	            }
	            return null;
	        }
	    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	private boolean isValidEmail(String email) {
		String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	private boolean isValidPhone(CharSequence target){
		 
	        if (target == null) {
	            return false;
	        } else {
	            if (target.length() != 10) {
	                return false;
	            } else {
	                return android.util.Patterns.PHONE.matcher(target).matches();
	            }
	        }
		
	}
	 public boolean validate(final String username){
		 pattern = Pattern.compile(USERNAME_PATTERN);
		 matcher = pattern.matcher(username);
		  return matcher.matches();
	 }
	}

