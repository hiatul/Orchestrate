package com.example.eventmanagement;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity  extends Activity {
	EditText editTextUserName,editTextPassword;
	Button btn_login,btn_cancel;
	TextView tv;
	public static final String PREFS_NAME = "LoginPrefs";
	LoginDataBaseAdapter loginDataBaseAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		 
		loginDataBaseAdapter=new LoginDataBaseAdapter(this);
		loginDataBaseAdapter=loginDataBaseAdapter.open();
		editTextUserName=(EditText) findViewById(R.id.UserNameToLogin);
	    editTextPassword=(EditText) findViewById(R.id.PasswordToLogin);
	    tv = (TextView) findViewById(R.id.forgotPassword);
	    btn_cancel = (Button) findViewById(R.id.btnCancel);
		btn_login = (Button) findViewById(R.id.btnLogin);
		    
		btn_cancel.setOnClickListener(new View.OnClickListener() {
		                @Override
		                public void onClick(View v) {
		                    finish();
		                }
				    });
	   btn_login.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			String userName=editTextUserName.getText().toString();
		    String password=editTextPassword.getText().toString();
		    //String storedPassword=loginDataBaseAdapter.getSinlgeEntry(userName);
		    
		    try{  
                if(userName.length() > 0 && password.length() >0)  
                {  
                    LoginDataBaseAdapter dbUser = new LoginDataBaseAdapter(LoginActivity.this);  
                    dbUser.open();  
                    
                      
                    if(dbUser.Login(userName, password))  
                    {  
                    	SharedPreferences user=PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                        user.edit().putString("user_name", userName).commit();
                    	SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString("logged", "logged");
                        editor.commit();
                        Toast.makeText(LoginActivity.this,"Successfully Logged In", Toast.LENGTH_LONG).show();
                        
                        Intent intent=new Intent(getApplicationContext(),UserJoinEvent.class);
                        
                        intent.putExtra("u_name", userName);
						startActivity(intent);
						finish();

                    }else{  
                        Toast.makeText(LoginActivity.this,"Invalid Username/Password", Toast.LENGTH_LONG).show();  
                    }  
                    dbUser.close();  
                }  
                  
            }catch(Exception e)  
            {  
                Toast.makeText(LoginActivity.this,e.getMessage(), Toast.LENGTH_LONG).show();  
            }  

		}
	});
	   tv.setOnClickListener(new View.OnClickListener(){
	       public void onClick(View v){
	             Intent intent = new Intent(LoginActivity.this, ForgotPassword.class);
	             startActivity(intent);
	       }
	});
					    
		}
        
        
	}


