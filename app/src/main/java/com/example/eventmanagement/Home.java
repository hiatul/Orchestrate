package com.example.eventmanagement;


import android.app.Activity;
import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class Home extends Activity {
	

	int mFlipping = 0;
	Button btnsignin,btnsignup,btnadmin;
	Dialog dialog;
	LoginDataBaseAdapter loginDataBaseAdapter;
	String user="";
	String userName;
	int counter = 0;
	String a[]={};
	public static final String MyPREFERENCES = "MyPrefs" ;
	 public static final String PREFS_NAME = "LoginPrefs";
	 SharedPreferences sharedpreferences;
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        
        //if(loginDataBaseAdapter.getAdminCount()==0)
        loginDataBaseAdapter.insertentries(new AdminLogin("a","d"));
      /*  SharedPreferences prefs=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if(!prefs.contains("insertedInDB")){
        	
        	//loginDataBaseAdapter.insertentries(new AdminLogin("a","d"));
        
        	
        }
        else
        {
        //	loginDataBaseAdapter.insertentries(new AdminLogin("a","d"));
        	SharedPreferences.Editor editor = prefs.edit();
	        editor.putBoolean("insertedInDB", true);
	        editor.commit();
        	// Toast.makeText(Home.this,"Admin Password", Toast.LENGTH_LONG).show();  
        	
        }*/
        
        
        
       /* if(a.length==0)
        {
        	loginDataBaseAdapter.insertentries(new AdminLogin("a","d"));
        	a[0]="h1";
        	a[1]="bye";
        }*/
        //counter++;
      
       if(loginDataBaseAdapter.getAdminCount()<0){
    	   loginDataBaseAdapter.insertentries(new AdminLogin("a","d"));
       }
       else{
    	   
       }
       
        btnsignin = (Button) findViewById(R.id.btnsignin);
        btnsignup = (Button) findViewById(R.id.btnsignup);
        btnadmin = (Button)  findViewById(R.id.btnadmin);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        ViewFlipper flipper = (ViewFlipper) findViewById(R.id.flipper1);
        
        if(mFlipping==0){
        	flipper.startFlipping();
        	mFlipping=1;
        	
        }
        else{
        	flipper.stopFlipping();
        	mFlipping=0;
        }
      /*  SharedPreferences user=PreferenceManager.getDefaultSharedPreferences(Home.this);
    	String abc=user.getString("userNam", userName);
    	if(abc!=null){
    		  Toast.makeText(Home.this,abc, Toast.LENGTH_LONG).show();
    		Intent i = new Intent(Home.this, UserJoinEvent.class);
            startActivity(i);
    	}
    	else{
    		Intent i = new Intent(Home.this, Home.class);
            startActivity(i);
    	}*/
        btnsignup.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intentSignUP=new Intent(getApplicationContext(),SignUpActivity.class);
				startActivity(intentSignUP);
			}
        	});
        
        btnadmin.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Intent intentSignUP=new Intent(getApplicationContext(),Admin.class);
				//startActivity(intentSignUP);
				final Dialog dialog = new Dialog(Home.this);
	 			dialog.setContentView(R.layout.admin_signup);
	 		    dialog.setTitle("Login");
	 		    Button btn_cancel = (Button) dialog.findViewById(R.id.btnCancel);
	 		    Button btn_login = (Button) dialog.findViewById(R.id.btnLogin);
	 		    dialog.show();
	 		    final  EditText editTextUserName=(EditText)dialog.findViewById(R.id.editTextUserNameToLogin);
				final  EditText editTextPassword=(EditText)dialog.findViewById(R.id.editTextPasswordToLogin);
				btn_cancel.setOnClickListener(new View.OnClickListener() {
				                @Override
				                public void onClick(View v) {
				                    dialog.dismiss();
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
	                        LoginDataBaseAdapter dbUser = new LoginDataBaseAdapter(Home.this);  
	                        dbUser.open();  
	                        
	                          
	                        if(dbUser.AdminLogin(userName, password))  
	                        {  
	                        	 //loginDataBaseAdapter.getAdminCount();
	                        	 
                                SharedPreferences.Editor editor = sharedpreferences.edit();
	        		            editor.putString("Name", userName);
	        		            editor.putString("Password", password);
	        		            editor.commit();
	                        	Toast.makeText(Home.this,"Successfully Logged In", Toast.LENGTH_LONG).show();
	                            Intent intent=new Intent(getApplicationContext(),CreateEvent.class);
	       					 startActivity(intent);
	       					 finish();
	                        }else{  
	                            Toast.makeText(Home.this,"Invalid Username/Password", Toast.LENGTH_LONG).show();  
	                        }  
	                        dbUser.close();  
	                    }  
	                      
	                }catch(Exception e)  
	                {  
	                    Toast.makeText(Home.this,e.getMessage(), Toast.LENGTH_LONG).show();  
	                }  

				}
			});
						    
			}
		});
        
        btnsignin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
	 			final Dialog dialog = new Dialog(Home.this);
	 			dialog.setContentView(R.layout.login);
	 		    dialog.setTitle("Login");
	 		    Button btn_cancel = (Button) dialog.findViewById(R.id.btnCancel);
	 		    Button btn_login = (Button) dialog.findViewById(R.id.btnLogin);
	 		    dialog.show();
	 		    final  EditText editTextUserName=(EditText)dialog.findViewById(R.id.editTextUserNameToLogin);
				final  EditText editTextPassword=(EditText)dialog.findViewById(R.id.editTextPasswordToLogin);
				final TextView tv = (TextView)dialog.findViewById(R.id.forgotpassword);
				 tv.setOnClickListener(new View.OnClickListener(){
			 	       public void onClick(View v){
			 	             Intent intent = new Intent(Home.this, ForgotPassword.class);
			 	             startActivity(intent);
			 	             finish();
			 	       }
			 	});
				btn_cancel.setOnClickListener(new View.OnClickListener() {
				                @Override
				                public void onClick(View v) {
				                    dialog.dismiss();
				                }
						    });
			   btn_login.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
				    userName=editTextUserName.getText().toString();
				    String password=editTextPassword.getText().toString();
				    //String storedPassword=loginDataBaseAdapter.getSinlgeEntry(userName);
				    
				    try{  
	                    if(userName.length() > 0 && password.length() >0)  
	                    {  
	                        LoginDataBaseAdapter dbUser = new LoginDataBaseAdapter(Home.this);  
	                        dbUser.open();  
	                        
	                          
	                        if(dbUser.Login(userName, password))  
	                        {  
	                        	SharedPreferences.Editor editor = sharedpreferences.edit();
	        		            
	        		            editor.putString("Name", userName);
	        		            editor.putString("Password", password);
	        		            editor.commit();
	                        	/*SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
	                            SharedPreferences.Editor editor = settings.edit();
	                            editor.putString("logged", "logged");
	                            editor.commit();*/
	                            Toast.makeText(Home.this,"Successfully Logged In", Toast.LENGTH_LONG).show();
	                       
	                            SharedPreferences user=PreferenceManager.getDefaultSharedPreferences(Home.this);
	                            user.edit().putString("user_name", userName).commit();
	                            //Toast.makeText(Home.this,Boolean.user_name, Toast.LENGTH_LONG).show();  
	                            Intent intent=new Intent(getApplicationContext(),UserJoinEvent.class);
	                            intent.putExtra("u_name", userName);
	    						startActivity(intent);
	    						finish();
	                        }else{  
	                            Toast.makeText(Home.this,"Invalid Username/Password", Toast.LENGTH_LONG).show();  
	                        }  
	                        dbUser.close();  
	                    }  
	                      
	                }catch(Exception e)  
	                {  
	                    Toast.makeText(Home.this,e.getMessage(), Toast.LENGTH_LONG).show();  
	                }  

				}
			});
						    
			}
		});
       
	}
     
	/*@Override
	   public boolean onCreateOptionsMenu(Menu menu) {
	      // Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
	      SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
	        SearchView searchView = (SearchView) menu.findItem(R.id.action_search)
	                .getActionView();
	        searchView.setSearchableInfo(searchManager
	                .getSearchableInfo(getComponentName()));
	 
	        return super.onCreateOptionsMenu(menu);
	   }
	   */
	   @Override
	   public boolean onOptionsItemSelected(MenuItem item) {
	      // Handle action bar item clicks here. The action bar will
	      // automatically handle clicks on the Home/Up button, so long
	      // as you specify a parent activity in AndroidManifest.xml.
	      
		   switch (item.getItemId()) {
	        case R.id.action_settings:
	            // search action
	            return true;
	            default:
	      return super.onOptionsItemSelected(item);
	   }   	
	   }
}
