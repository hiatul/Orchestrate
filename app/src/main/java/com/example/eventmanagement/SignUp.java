package com.example.eventmanagement;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

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


public class SignUp extends Activity implements View.OnClickListener {

    private static final String usernameEmail = "escribir.karan@gmail.com";
    private static final String passwordEmail = "karan_7393";
    String subject = "Sign Up";
    String body = "";
    String usergender="";
    String gender = "";
    ArrayList<String> list = new ArrayList<String>();
    ArrayAdapter<String> dataAdapter;
    Spinner spin1;
    String item="";
    EditText editTextUserName,editTextPassword,editTextConfirmPassword,editTextBranch,
            editTextEmail,editTextPhone;
    Button btnCreateAccount;
    RadioButton radioButton,radioButton1;
    RadioGroup radioGender;
    int r_id;
    private Pattern pattern;
    private Matcher matcher;
    private static final String USERNAME_PATTERN = "^[A-Za-z0-9_-]{3,15}$";


    public static final String PREFS_NAME = "LoginPrefs";
    LoginDataBaseAdapter loginDataBaseAdapter;
    String regEx =
            "^(([w-]+.)+[w-]+|([a-zA-Z]{1}|[w-]{2,}))@"
                    +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9]).([0-1]?"
                    +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])."
                    +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9]).([0-1]?"
                    +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                    +"([a-zA-Z]+[w-]+.)+[a-zA-Z]{2,4})$";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();

        editTextUserName=(EditText)findViewById(R.id.editTextUserName);
        editTextPassword=(EditText)findViewById(R.id.editTextPassword);
        editTextConfirmPassword=(EditText)findViewById(R.id.editTextConfirmPassword);
        editTextEmail=(EditText) findViewById(R.id.editTextEmail);
        editTextPhone=(EditText) findViewById(R.id.editTextPhone);
        radioButton=(RadioButton) findViewById(R.id.radioButton);
        radioButton1=(RadioButton) findViewById(R.id.radioButton1);
        radioGender=(RadioGroup)   findViewById(R.id.radioGender);
        btnCreateAccount=(Button)findViewById(R.id.btn_sign_up);
//        editTextUserName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.person, 0, 0, 0);
//        editTextPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.password, 0, 0, 0);
//        editTextConfirmPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.password, 0, 0, 0);
//        editTextEmail.setCompoundDrawablesWithIntrinsicBounds(R.drawable.mail, 0, 0, 0);
//        editTextPhone.setCompoundDrawablesWithIntrinsicBounds(R.drawable.phone, 0, 0, 0);
        spin1 = (Spinner) findViewById(R.id.spinner1);
        list.add("Department-");
        list.add("CE");
        list.add("CSE");
        list.add("EE");
        list.add("ECE");
        list.add("IT");
        list.add("ME");
        list.add("PE");
        list.add("MCA");
        dataAdapter = new ArrayAdapter<String>(SignUp.this,R.layout.selected_item,list);
        spin1.setAdapter(dataAdapter);
        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View
                    arg1, int arg2, long arg3) {
                item = dataAdapter.getItem(arg2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        findViewById(R.id.iv_back).setOnClickListener(this);
        findViewById(R.id.ll_sign_in).setOnClickListener(this);
        btnCreateAccount.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                startActivity(new Intent(this, Home_Screen.class));
                finish();
                break;
            case R.id.ll_sign_in:
                startActivity(new Intent(this, SignIn.class));
                finish();
                break;
            case R.id.btn_sign_up:
                String userName=editTextUserName.getText().toString();
                //String userBranch=editTextBranch.getText().toString();
                String userBranch = item;
                String userEmail=editTextEmail.getText().toString();
                String userPhone=editTextPhone.getText().toString();
                String password=editTextPassword.getText().toString();
                String confirmPassword=editTextConfirmPassword.getText().toString();
                String storedUser = loginDataBaseAdapter.Exist(userName);
                String storedEmail = loginDataBaseAdapter.EmailExist(userEmail);
                body = "You Have Successfully Signed Up" + "\n" + "USERNAME = " + userName + "\n" + "PASSWORD = " + password;
                sendMail(userEmail, subject, body);
                r_id=radioGender.getCheckedRadioButtonId();
                if(r_id==radioButton.getId())
                {
                    gender="Male";
                }
                else
                {
                    gender="Female";
                }

                if (!isValidEmail(userEmail)) {
                    editTextEmail.setError("Invalid Email");
                }
                if (!isValidPhone(userPhone)) {
                    editTextPhone.setError("Invalid Phone No.");
                }
                if(!validate(userName)){
                    editTextUserName.setError("Inavlid Username");
                }
                //Matcher matcherObj = Pattern.compile(regEx).matcher(userEmail);
                if(userName.equals(storedUser)){
                    Toast.makeText(getApplicationContext(), "username already exists", Toast.LENGTH_LONG).show();
                    return;
                }
                if(userEmail.equals(storedEmail)){
                    Toast.makeText(getApplicationContext(), "email already exists", Toast.LENGTH_LONG).show();
                    return;
                }

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

						/* if (matcherObj.matches()) {
					        	Toast.makeText(v.getContext(), userEmail+" is valid", Toast.LENGTH_SHORT).show();
					        } else {
					        	Toast.makeText(v.getContext(), userEmail+" is InValid", Toast.LENGTH_SHORT).show();
					        }*/
                                // Save the Data in Database

                                loginDataBaseAdapter.insertEntry(userName, password,userBranch,userEmail,userPhone,gender);
                                SharedPreferences user= PreferenceManager.getDefaultSharedPreferences(SignUp.this);
                                user.edit().putString("user_name", userName).commit();
                                SharedPreferences useremail= PreferenceManager.getDefaultSharedPreferences(SignUp.this);
                                useremail.edit().putString("user_email", userEmail).commit();

                                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                                intent.putExtra("u_name", userName);
                                intent.putExtra("u_email", userEmail);
                                startActivity(intent);
                                finish();
                                Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }
        break;

        }

    }
    public boolean validate(final String username){
        pattern = Pattern.compile(USERNAME_PATTERN);
        matcher = pattern.matcher(username);
        return matcher.matches();
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
        message.setFrom(new InternetAddress("escribir.karan@gmail.com"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
        // message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("aashitadutta@gmail.com"));

        message.setSubject(subject);
        message.setText(messageBody);
        return message;
    }
    private Session createSessionObject() {
        Properties properties = new Properties();
		        /*
		        properties.put("mail.smtp.auth", "true");
		        properties.put("mail.smtp.starttls.enable", "true");
		        properties.put("mail.smtp.host", "smtp.gmail.com");
		        properties.put("mail.smtp.port", "465");*/
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


}
