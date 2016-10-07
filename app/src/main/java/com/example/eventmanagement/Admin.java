package com.example.eventmanagement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Admin extends Activity {
	EditText editTextUserName,editTextPassword;
	Button btn_login;
	LoginDataBaseAdapter loginDataBaseAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.admin_signup);
		 
		loginDataBaseAdapter=new LoginDataBaseAdapter(this);
		loginDataBaseAdapter=loginDataBaseAdapter.open();
		editTextUserName=(EditText) findViewById(R.id.editTextUserNameToLogin);
	    editTextPassword=(EditText) findViewById(R.id.editTextPasswordToLogin);
       // btn_login = (Button) findViewById(R.id.buttonLogin);
        btn_login.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String userName=editTextUserName.getText().toString();
			    String password=editTextPassword.getText().toString();
//				AdminLogin adminLogin = new AdminLogin();
//				adminLogin.setName(userName);
//				adminLogin.setPass(password);
			    try{  
                    if(userName.length() > 0 && password.length() >0)  
                    {  
                        LoginDataBaseAdapter dbUser = new LoginDataBaseAdapter(Admin.this);  
                        dbUser.open();  
                          
                        if(dbUser.AdminLogin(userName, password))  
                        {  
                            Toast.makeText(Admin.this,"Successfully Logged In", Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(getApplicationContext(),CreateEvent.class);
    					    startActivity(intent);
    					    finish();
                        }else{  
                            Toast.makeText(Admin.this,"Invalid Username/Password", Toast.LENGTH_LONG).show();  
                        }  
                        dbUser.close();  
                    }  
                      
                }catch(Exception e)  
                {  
                    Toast.makeText(Admin.this,e.getMessage(), Toast.LENGTH_LONG).show();  
                }  

			}
		});
					    
		}
        
        
	}

