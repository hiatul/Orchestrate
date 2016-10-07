package com.example.eventmanagement;

import java.io.UnsupportedEncodingException;
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
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotPassword extends Activity{
	EditText email,pass,conPass;
	Button btncon,btncancel;
	LoginDataBaseAdapter db;
	private static final String usernameEmail = "escribir.karan@gmail.com";
    private static final String passwordEmail = "karan_7393";
    String subject = "Reset Password";
    String body = "";

	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.forgot_password);
		email = (EditText) findViewById(R.id.EmailToConfirm);
		pass = (EditText) findViewById(R.id.PasswordToUpdate);
		conPass = (EditText) findViewById(R.id.ConfirmPasswordToUpdate);
		btncon = (Button) findViewById(R.id.btnConfirmPass);
		btncancel = (Button) findViewById(R.id.btnCancel);
		btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
	    });
		SharedPreferences user=PreferenceManager.getDefaultSharedPreferences(ForgotPassword.this);
		String abc=user.getString("user_email","hello");
		 db=new LoginDataBaseAdapter(this);
		 db.open();
		 btncon.setOnClickListener(new View.OnClickListener() {
			 
				public void onClick(View v) {
					String userEmail=email.getText().toString();
					String password=pass.getText().toString();
					String confirmPassword=conPass.getText().toString();
					if (!isValidEmail(userEmail)) {
						email.setError("Invalid Email");
					}
					body = "You Have Successfully Updated Your Password" + "\n" + "Your new Password is: "  + password;	
					sendMail(userEmail, subject, body);
					if(password.equals("")||confirmPassword.equals("")||userEmail.equals(""))
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
						if(isValidEmail(userEmail)){
											    // Save the Data in Database
					    db.forgotPassword(password, userEmail);
					    Toast.makeText(getApplicationContext(), "Account Successfully Updated ", Toast.LENGTH_LONG).show();
					  Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
					  startActivity(intent);
					  finish();
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
	        Toast.makeText(ForgotPassword.this,email, Toast.LENGTH_LONG).show();
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
}
