package com.example.eventmanagement;

public class AdminLogin {
	int _id;
	String _admin_name;
	String _admin_pass;
	// Empty constructor
	public AdminLogin(){
		
	}
	// constructor
	public AdminLogin(int id, String name, String pass){
		this._id = id;
		this._admin_name = name;
		this._admin_pass = pass;
	}
	
	// constructor
	public AdminLogin(String name, String pass){
		this._admin_name = name;
		this._admin_pass = pass;
	}
	// getting ID
	public int getID(){
		return this._id;
	}
	
	// setting id
	public void setID(int id){
		this._id = id;
	}
	
	// getting name
	public String getName(){
		return this._admin_name;
	}
	
	// setting name
	public void setName(String name){
		this._admin_name = name;
	}
	public String getPass(){
		return this._admin_pass;
	}
	
	// setting phone number
	public void setPass(String pass){
		this._admin_pass = pass;
	}

}
