package com.example.eventmanagement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

public class LoginDataBaseAdapter {
	static final String DATABASE_NAME = "event.db";
	static final int DATABASE_VERSION = 1;
	public static final int NAME_COLUMN = 1;
	public static final String ID="ID";
	public static final String EVENT_NAME="EVENT_NAME";
	public static final String SHORT_DESCRIPTION="SHORT_DESCRIPTION";
	public static final String DATE="DATE";
	public static final String DESCRIPTION="DESCRIPTION";
	public static final String PRIZE ="PRIZE";
	public static final String ORGANIZERS="ORGANIZERS";
	public static final String VENUE ="VENUE";
	public static final String CONTACTS="CONTACTS";
	public static final String SOCIETY="SOCIETY";
	public static final String TABLE_NAME="TIMELINE";
	public static final String USERNAME="USERNAME";
	public static final String USERPHONE="USERPHONE";
	public static final String USEREMAIL="USEREMAIL";
	public static final String USERBRANCH="USERBRANCH";
	public static final String PASSWORD="PASSWORD";
	public static final String GENDER="GENDER";
	public static final String REG_ID="REG_ID";
	public static final String DATABASE_TABLE="REGISTRATION";
	public static final String USER_TABLE="USER_TABLE";
	public static final String USER_NAME="USER_NAME";
	public static final String USER_EVENT_NAME="USER_EVENT_NAME";
	public static final String USER_JOIN_DATE="USER_JOIN_DATE";
	public static final String USER_SOCIETY="USER_SOCIETY";
	public static final String IMAGE_TABLE="IMAGETABLE";
	public static final String EVENT_PHOTO="EVENT_PHOTO";
	public static final String KEY_ID = "ID";
	public static final String EVENT_NAME_IMAGE="EVENT_NAME_IMAGE";
	private static final String[] COLUMNS = { ID, EVENT_NAME, SHORT_DESCRIPTION, DESCRIPTION, DATE, PRIZE,ORGANIZERS, VENUE, CONTACTS };
    private static final String[] COLUMN = { REG_ID, USERNAME, USERBRANCH, USEREMAIL, USERPHONE, PASSWORD, GENDER };
	// SQL Statement to create a new database.
	static final String DATABASE_CREATE = "create table "+"REGISTRATION"+
	                             "( " +"REG_ID"+" integer primary key autoincrement,"+ "USERNAME  text,USERBRANCH text,USEREMAIL text,USERPHONE text,PASSWORD text, GENDER text); ";
	static final String NEW_DATABASE_CREATE = "create table " + "TIMELINE" 
	                             +"(" +"ID"+" integer primary key autoincrement,"+ "EVENT_NAME text,DESCRIPTION text,SHORT_DESCRIPTION text,SOCIETY text, DATE text, VENUE text,PRIZE text,ORGANIZERS text, CONTACTS text); ";
	static final String ADMIN_TABLE = "create table "+"ADMIN"+
            "( " +"ID"+" integer primary key,"+ "ADMINUSER  text,ADMINPASS text); ";
	static final String JOINT_TABLE = "create table "+"USER_TABLE"+
            "(" +"ID"+" integer primary key autoincrement,"+ "USER_NAME TEXT, USER_EVENT_NAME TEXT, USER_JOIN_DATE DATETIME, USER_SOCIETY TEXT, USER_PHONE TEXT ); "; 
	static final String IMAGE_TABLE_CREATE = "create table "+"IMAGETABLE"+
            "( " +"ID"+" integer primary key autoincrement,"+ "EVENT_NAME_IMAGE text not null, EVENT_PHOTO blob not null); ";
	
	//static final String IMAGE_TABLE_CREATE = "create table "+"IMAGETABLE"+
      //      "( " +"ID"+" integer primary key autoincrement,"+ "EVENT_NAME_IMAGE text not null, EVENT_PHOTO blob not null, EVENT_ID integer not null," + "FOREIGN KEY(EVENT_ID) REFERENCES TIMELINE(ID) ON DELETE CASCADE" + ");";
	// Variable to hold the database instance
	public  SQLiteDatabase db;
	// Context of the application using the database.
	private final Context context;
	// Database open/upgrade helper
	private DataBaseHelper dbHelper;
	public  LoginDataBaseAdapter(Context _context) 
	{
		context = _context;
		dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	public  LoginDataBaseAdapter open() throws SQLException 
	{
		db = dbHelper.getWritableDatabase();
		return this;
	}
	public void close() 
	{
		db.close();
	}

	public  SQLiteDatabase getDatabaseInstance()
	{
		return db;
	}
	public void insertEntry(String userName,String password,String userBranch,String userEmail,String userPhone, String gender )
	{
       ContentValues newValues = new ContentValues();
		// Assign values for each row.
		newValues.put("USERNAME", userName);
		newValues.put("PASSWORD",password);
        newValues.put("USERBRANCH",userBranch);
        newValues.put("USEREMAIL",userEmail);
        newValues.put("USERPHONE",userPhone);
        newValues.put("GENDER", gender);
		// Insert the row into your table
		db.insert("REGISTRATION", null, newValues);
	}
	public void  updateEntry(String userName,String password,String userBranch,String userEmail,String userPhone, String gender)
	{
		// Define the updated row content.
		ContentValues updatedValues = new ContentValues();
		// Assign values for each row.
		updatedValues.put("USERNAME", userName);
		updatedValues.put("PASSWORD",password);
		updatedValues.put("USERBRANCH",userBranch);
		updatedValues.put("USEREMAIL",userEmail);
		updatedValues.put("USERPHONE",userPhone);
		updatedValues.put("GENDER", gender);
        String where="USERNAME = ?";
	    db.update("REGISTRATION",updatedValues, where, new String[]{userName});			   
	}
	public void  forgotPassword(String password, String userEmail)
	{
		// Define the updated row content.
		ContentValues updatePassword = new ContentValues();
		// Assign values for each row.
		updatePassword.put("PASSWORD",password);
		updatePassword.put("USEREMAIL",userEmail);
        String where="USEREMAIL = ?";
	    db.update("REGISTRATION",updatePassword, where, new String[]{userEmail});			   
	}
	public int deleteEntry(String UserName)
	{
		//String id=String.valueOf(ID);
	    String where="USERNAME=?";
	    int numberOFEntriesDeleted= db.delete("REGISTRATION", where, new String[]{UserName}) ;
        return numberOFEntriesDeleted;
	}
	public void insertValues(String event_name, String description, String short_desc, String society, String date, String venue, String prize,String organizers, String contacts)
	{
		ContentValues eventValues = new ContentValues();
		eventValues.put("EVENT_NAME", event_name);
		eventValues.put("DESCRIPTION", description);
		eventValues.put("SHORT_DESCRIPTION", short_desc);
		eventValues.put("SOCIETY", society);
		eventValues.put("DATE", date);
		eventValues.put("VENUE", venue);
		eventValues.put("PRIZE", prize);
		eventValues.put("ORGANIZERS", organizers);
		eventValues.put("CONTACTS", contacts);
		db.insert("TIMELINE", null, eventValues);
	}
	public void insertentries(AdminLogin ad){
		//String[] args = {"1", "a", "d"}; // where 1 is the category id
	    //db.execSQL("INSERT OR REPLACE INTO ADMIN (ID, ADMINUSER, ADMINPASS) VALUES (?, ?, ?)", args);
		db = dbHelper.getWritableDatabase();
		ContentValues Values = new ContentValues();
		//userValues.put(ID, 1);
		Values.put("ADMINUSER", ad.getName());
		Values.put("ADMINPASS", ad.getPass());
		 db.insert("ADMIN", null, Values); 
		 db.close();
		/*String sql =
	            "INSERT INTO ADMIN (ID, ADMINUSER, ADMINPASS) VALUES(1,'a','d')" ;       
	                db.execSQL(sql);*/
	/*	
		String sql = "insert into " + "ADMIN" + " (" + "ID" + ", " + "ADMINUSER" + ", " + "ADMINPASS" + ") values (1, 'a', 'd');";
		try{
			Log.i("sql=", sql);
			db.execSQL(sql);
		}
		catch(SQLException e){
			Log.d("Error", "error in query");
	    	e.printStackTrace();
		}*/
	}
	public long getAdminCount() {
		/*String countQuery = "SELECT  * FROM " + "ADMIN";
		Cursor cursor = db.rawQuery(countQuery, null);
		if(cursor!=null)
		cursor.moveToFirst();
		if(cursor.getCount()>0)
		Log.i("Number of Records"," :: "+cursor.getCount());
		// return count
		return true;*/
		//long numRows = DatabaseUtils.queryNumEntries(db,"ADMIN");
		//return numRows;
		String sql = "SELECT COUNT(*) FROM " + "ADMIN";
	  //  SQLiteStatement statement = db.compileStatement(sql);
	  //  long count = statement.simpleQueryForLong();
	    //Log.i("Number of Records"," :: "+count);
	    //return count;
		SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        //cursor.close();
        System.out.println(cursor.getCount());
        Log.i("Number of Records"," :: "+cursor.getCount());
        // return count
        return cursor.getCount();
	    
		
	}
	public void insertImage(Image im)
	{
		ContentValues cv = new ContentValues();
		cv.put(EVENT_NAME_IMAGE, im._name);
		cv.put(EVENT_PHOTO,im._image);
		db.insert("IMAGETABLE", null, cv);
	}
	public Image retriveImage(int id) {
		/*Cursor cur = db.query(true, IMAGE_TABLE, new String[] { EVENT_PHOTO }, null, null, null, null, null, null);
		if (cur.moveToFirst()) {
			byte[] blob = cur.getBlob(cur.getColumnIndex(EVENT_PHOTO));
			cur.close();
			return new Image(Utility.getPhoto(blob), 0);
		}
		cur.close();
		return null;*/
		Cursor cursor = db.query("IMAGETABLE", new String[] { KEY_ID, EVENT_NAME_IMAGE,
				EVENT_PHOTO }, KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Image i = new Image(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1), cursor.getBlob(1));

		// return contact
		return i;

	}
	public List<Image> getAllImages() {
		List<Image> contactList = new ArrayList<Image>();
		// Select All Query
		String selectQuery = "SELECT  * FROM IMAGETABLE ORDER BY EVENT_NAME_IMAGE";

		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Image contact = new Image();
				contact.setID(Integer.parseInt(cursor.getString(0)));
				contact.setName(cursor.getString(1));
				contact.setImage(cursor.getBlob(2));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}
		// close inserting data from database
		db.close();
		// return contact list
		return contactList;

	}
	public void insertImgDetails(Images images) {
		ContentValues cv = new ContentValues();
		cv.put(EVENT_NAME_IMAGE, images.getName());
		cv.put(EVENT_PHOTO, Utility.getBytes(images.getBitmap()));
		db.insert(IMAGE_TABLE, null, cv);
	}

	public Images retriveImgDetails(String k){
	    Cursor cur = db.query(true, IMAGE_TABLE, new String[] { KEY_ID, EVENT_NAME_IMAGE,
				EVENT_PHOTO }, EVENT_NAME_IMAGE + "='" +k+"'", null, null, null, null, null);
		if( cur!=null) 
		cur.moveToFirst();
			String name = cur.getString(cur.getColumnIndex(EVENT_NAME_IMAGE));
			byte[] blob = cur.getBlob(cur.getColumnIndex(EVENT_PHOTO));
			//int age = cur.getInt(cur.getColumnIndex(EMP_AGE));
				
			return new Images(Utility.getPhoto(blob),name);	
	}
	public Images retriveImg(int id){
	    Cursor cur = db.query(true, IMAGE_TABLE, new String[] { KEY_ID, EVENT_NAME_IMAGE,
				EVENT_PHOTO }, KEY_ID + "='" +String.valueOf(id)+"'", null, null, null, null, null);
		if( cur!=null) 
		cur.moveToFirst();
			String name = cur.getString(cur.getColumnIndex(EVENT_NAME_IMAGE));
			byte[] blob = cur.getBlob(cur.getColumnIndex(EVENT_PHOTO));
			//int age = cur.getInt(cur.getColumnIndex(EMP_AGE));
				
			return new Images(Utility.getPhoto(blob),name);	
	}
	public void deleteImage(){
		
	}
	public void userJoin(String user_name,String event_name,String date,String phone, String society){
		ContentValues userValues = new ContentValues();
		userValues.put("USER_NAME", user_name);
		userValues.put("USER_EVENT_NAME", event_name);
		userValues.put("USER_SOCIETY", society);
		 userValues.put(USER_JOIN_DATE, date);
		 userValues.put("USER_PHONE", phone);
		 db.insert("USER_TABLE", null, userValues);
		
	}
	private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
	/*public String getSingleEntry(String userName)
	{
		Cursor cursor=db.query("REGISTRATION", null, " USERNAME=?", new String[]{userName}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
        	cursor.close();
        	return "NOT EXIST";
        }
	    cursor.moveToFirst();
		String password= cursor.getString(cursor.getColumnIndex("USERNAME"));
		cursor.close();
		return password;
	
    } */ 
  
	  public boolean Login(String userName, String password) throws SQLException   
	    {  
	        Cursor mCursor = db.rawQuery("SELECT * FROM " + "REGISTRATION" + " WHERE username=? AND password=?", new String[]{userName,password});  
	        if (mCursor != null) {             
	            if(mCursor.getCount() > 0)  
	            {  
	                return true;  
	            }  
	        }  
	     return false;  
	    }  
	  public boolean AdminLogin(String a, String d) throws SQLException   
	    {  
		 //boolean r=true;
	        Cursor mCursor = db.rawQuery("SELECT * FROM " + "ADMIN" + " WHERE ADMINUSER=? AND ADMINPASS=?", new String[]{a,d});  
	        if (mCursor != null) {             
	            if(mCursor.getCount() > 0)  
	            {  
	              // r=true;
	            	return true;
	            } 
	            
	        }  
	     /*   else
	        {
	        	r=false;
	        }
	     return r; */
	       return false;
	    }  
	  
	
	
	public boolean updateEvent(long rowId, String event_name, String description, String short_desc, String society, String date, String venue, String prize,String organizers, String contacts){
		ContentValues args = new ContentValues();
		args.put("EVENT_NAME", event_name);
		args.put("DESCRIPTION", description);
		args.put("SHORT_DESCRIPTION", short_desc);
		args.put("SOCIETY", society);
		args.put("DATE", date);
		args.put("VENUE", venue);
		args.put("PRIZE", prize);
		args.put("ORGANIZERS", organizers);
		args.put("CONTACTS", contacts);
		return db.update(TABLE_NAME, args, ID + "=" + rowId, null) > 0;
	}
	
	public boolean deleteEvent(long rowId)
	{
		return db.delete(TABLE_NAME, ID + "=" + rowId, null) > 0;
	}
	
	
	public Cursor getAllRows() {
		//String where = null;
		Cursor c =db.query(TABLE_NAME, new String[] { "rowid _id", EVENT_NAME, SHORT_DESCRIPTION, SOCIETY, DATE }, 
				null, null, null, null, null);
		if( c!= null) {
			c.moveToFirst();
		}
		return c;
	}
	public Cursor getAllEventRows() {
		//String where = null;
		Cursor c =db.query(TABLE_NAME, new String[] { "rowid _id", EVENT_NAME, SHORT_DESCRIPTION, SOCIETY, DATE }, 
				null, null, null, null, null);
		if( c!= null) {
			c.moveToFirst();
		}
		return c;
	}
	
	/*public Cursor getAllEventRows(String abc) {
		//String where = null;
		Cursor c =db.query(TABLE_NAME, new String[] { "rowid _id", EVENT_NAME, SHORT_DESCRIPTION, SOCIETY, DATE }, 
				ID + "=?",
				new String[] { String.valueOf(abc) }, null, null, null, null);
		if( c!= null) {
			c.moveToFirst();
		}
		return c;
	}*/

public Cursor getData(String id2){
    SQLiteDatabase db1 = dbHelper.getReadableDatabase();
    String query = "select * from "+TABLE_NAME+ " where ID = "+id2+"";
    Cursor res =  db1.rawQuery( query, null );
    if( res!= null) {
		res.moveToFirst();
	}
    
  /*  try{
   
while(!res.isAfterLast()){
	res.moveToNext();
}
//res.close();
    
   
    }catch(Exception e){
    	Log.d("Error", "error in cursor");
    	e.printStackTrace();
    }*/
	return res;
 }
public Cursor getEventData(String r){
    SQLiteDatabase db1 = dbHelper.getReadableDatabase();
    String query = "select * from "+TABLE_NAME+ " where EVENT_NAME " + "='" +r+"'";
    Cursor res =  db1.rawQuery( query, null );
    if( res!= null) {
		res.moveToFirst();
	}
	return res;
 }
public Cursor getUserName(String a){
	
    SQLiteDatabase db1 = dbHelper.getReadableDatabase();
  // String query = "select * from "+"REGISTRATION"+ " where REG_ID = "+a+"";
  //Cursor res = db1.query("REGISTRATION", new String[] { "USERNAME", "USERPHONE" },null, null, null, null, null);
    Cursor res = db1.query(true,"REGISTRATION", new String[] { "USERNAME", "USERPHONE" ,"USEREMAIL"}, "USERNAME" + "='" +a+"'", null, null, null, null,null);
   // Cursor res = db1.rawQuery("SELECT * FROM REGISTRATION WHERE USERNAME = ?", new String[] {a});
  // Cursor res =  db1.rawQuery( query, null );
    if( res!=null) {
	res.moveToFirst();
		
	}
    
    return res;
}


public Cursor getAllUsers() {
	//String where = null;
	Cursor c =db.query(USER_TABLE, new String[] { "rowid _id", USER_NAME, USER_EVENT_NAME, USER_JOIN_DATE, USER_SOCIETY }, 
		    null, null, null, null, null);
	if( c!= null) {
		c.moveToFirst();
	}
	return c;
}
public Cursor getAllUserDetails(String u){
	SQLiteDatabase db1 = dbHelper.getReadableDatabase();
	// Cursor res = db1.query(true,"REGISTRATION", new String[] { "REG_ID", "USERNAME", "USERBRANCH", "USEREMAIL", "USERPHONE", PASSWORD, GENDER}, REG_ID + "=" +u+"", null, null, null, null,null);
	 String query = "select * from "+DATABASE_TABLE+ " where USERNAME = '"+u+"'";
	    Cursor res =  db1.rawQuery( query, null ); 
	if( res!=null) {
			res.moveToFirst();
				
			}
		    
		    return res;
		}
public List<Registration> getAllEmail() {
    List<Registration> emailList = new ArrayList<Registration>();
    // Select All Query
    String selectQuery = "SELECT  * FROM " + DATABASE_TABLE;
 
    SQLiteDatabase db = dbHelper.getWritableDatabase();
    Cursor cursor = db.rawQuery(selectQuery, null);
 
    // looping through all rows and adding to list
    if (cursor.moveToFirst()) {
        do {
            Registration reg = new Registration();
            reg.setEmail(cursor.getString(3));
           
            // Adding contact to list
            emailList.add(reg);
        } while (cursor.moveToNext());
    }
 
    // return contact list
    return emailList;
}
public String Exist(String user) {        
    String username="";
    SQLiteDatabase db = dbHelper.getReadableDatabase();               

    try { 
        Cursor c = db.query(DATABASE_TABLE, null, USERNAME + "=?", new String[]{String.valueOf(user)},null, null, null);                                               

        if (c == null) {                        
            return username;                                   
        }
        else {    
            c.moveToFirst();               
            username = c.getString(c.getColumnIndex(USERNAME)); 
        }                           
    }

    catch(Exception e){
        e.printStackTrace();
    }

    return username; 
}
public String EmailExist(String email) {        
    String useremail="";
    SQLiteDatabase db = dbHelper.getReadableDatabase();               

    try { 
        Cursor c = db.query(DATABASE_TABLE, null, USEREMAIL + "=?", new String[]{String.valueOf(email)},null, null, null);                                               

        if (c == null) {                        
            return useremail;                                   
        }
        else {    
            c.moveToFirst();               
            useremail = c.getString(c.getColumnIndex(USEREMAIL)); 
        }                           
    }

    catch(Exception e){
        e.printStackTrace();
    }

    return useremail; 
}
}
