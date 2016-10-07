package com.example.eventmanagement;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignIn extends Activity implements View.OnClickListener {

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String PREFS_NAME = "LoginPrefs";
    SharedPreferences sharedpreferences;
    LoginDataBaseAdapter loginDataBaseAdapter;
    String user="";
//    String userName;
    int counter = 0;
    String a[]={};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        loginDataBaseAdapter = new LoginDataBaseAdapter(this);

        //if(loginDataBaseAdapter.getAdminCount()==0)
        loginDataBaseAdapter.insertentries(new AdminLogin("a", "d"));

        if(loginDataBaseAdapter.getAdminCount()<0){
            loginDataBaseAdapter.insertentries(new AdminLogin("a","d"));
        }
        else{

        }


        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        final EditText editTextUserName=(EditText)findViewById(R.id.editTextUserNameToLogin);
        final EditText editTextPassword=(EditText)findViewById(R.id.editTextPasswordToLogin);
        final TextView tv = (TextView)findViewById(R.id.forgotPassword);
        tv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(SignIn.this, ForgotPassword.class);
                startActivity(intent);
                finish();
            }
        });
        Button admin_login = (Button) findViewById(R.id.btnadmin);
        admin_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(SignIn.this);
                dialog.setContentView(R.layout.admin_signup);
                dialog.setTitle("Login");
                Button btn_cancel = (Button) dialog.findViewById(R.id.btnCancel);
                Button btn_login = (Button) dialog.findViewById(R.id.btnLogin);
                dialog.show();
                final EditText editTextUserName=(EditText)dialog.findViewById(R.id.editTextUserNameToLogin);
                final EditText editTextPassword=(EditText)dialog.findViewById(R.id.editTextPasswordToLogin);
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
                                LoginDataBaseAdapter dbUser = new LoginDataBaseAdapter(SignIn.this);
                                dbUser.open();


                                if(dbUser.AdminLogin(userName, password))
                                {
                                    //loginDataBaseAdapter.getAdminCount();

                                    SharedPreferences.Editor editor = sharedpreferences.edit();
                                    editor.putString("Name", userName);
                                    editor.putString("Password", password);
                                    editor.commit();
                                    Toast.makeText(SignIn.this, "Successfully Logged In", Toast.LENGTH_LONG).show();
                                    Intent intent=new Intent(getApplicationContext(),CreateEvent.class);
                                    startActivity(intent);
                                    finish();
                                }else{
                                    Toast.makeText(SignIn.this, "Invalid Username/Password", Toast.LENGTH_LONG).show();
                                }
                                dbUser.close();
                            }

                        }catch(Exception e)
                        {
                            Toast.makeText(SignIn.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }

                    }
                });

            }
        });

        Button btn_login = (Button) findViewById(R.id.btn_sign_in);
        btn_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String userName = editTextUserName.getText().toString();
                String password = editTextPassword.getText().toString();
                //String storedPassword=loginDataBaseAdapter.getSinlgeEntry(userName);

                try {
                    if (userName.length() > 0 && password.length() > 0) {
                        LoginDataBaseAdapter dbUser = new LoginDataBaseAdapter(SignIn.this);
                        dbUser.open();


                        if (dbUser.Login(userName, password)) {
                            SharedPreferences.Editor editor = sharedpreferences.edit();

                            editor.putString("Name", userName);
                            editor.putString("Password", password);
                            editor.commit();
	                        	/*SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
	                            SharedPreferences.Editor editor = settings.edit();
	                            editor.putString("logged", "logged");
	                            editor.commit();*/
                            Toast.makeText(SignIn.this, "Successfully Logged In", Toast.LENGTH_LONG).show();

                            SharedPreferences user = PreferenceManager.getDefaultSharedPreferences(SignIn.this);
                            user.edit().putString("user_name", userName).commit();
                            //Toast.makeText(Home.this,Boolean.user_name, Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), UserJoinEvent.class);
                            intent.putExtra("u_name", userName);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(SignIn.this, "Invalid Username/Password", Toast.LENGTH_LONG).show();
                        }
                        dbUser.close();
                    }

                } catch (Exception e) {
                    Toast.makeText(SignIn.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        });





        findViewById(R.id.tv_cancel).setOnClickListener(this);
        findViewById(R.id.tv_sign_up).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tv_cancel:
                startActivity(new Intent(this, Home_Screen.class));
                finish();
                break;
            case R.id.tv_sign_up:
                startActivity(new Intent(this, SignUp.class));
                finish();
                break;
        }

    }

}
