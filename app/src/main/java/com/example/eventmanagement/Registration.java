package com.example.eventmanagement;

public class Registration {
	int _id;
	String _name;
	String _phone_number;
	String _email;
	String _gender;
	String _password;
	String _branch;
	
	// Empty constructor
	public Registration(){
		
	}
	// constructor
	public Registration(int id, String name, String branch, String email, String _phone_number, String password, String gender){
		this._id = id;
		this._name = name;
		this._phone_number = _phone_number;
		this._email=email;
		this._branch=branch;
		this._gender=gender;
		this._password=password;
	}
	
	// constructor
	public Registration(String name, String _phone_number){
		this._name = name;
		this._phone_number = _phone_number;
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
		return this._name;
	}
	
	// setting name
	public void setName(String name){
		this._name = name;
	}
	public String getBranch(){
		return this._branch;
	}
	
	// setting phone number
	public void setBranch(String branch){
		this._branch = branch;
	}
	// getting phone number
	public String getEmail(){
		return this._email;
	}
	
	// setting phone number
	public void setEmail(String email){
		this._email = email;
	}
	
	public String getPhoneNumber(){
		return this._phone_number;
	}
	
	// setting phone number
	public void setPhoneNumber(String phone_number){
		this._phone_number = phone_number;
	}
	public String getPassword(){
		return this._password;
	}
	
	// setting phone number
	public void setPassword(String password){
		this._password = password;
	}
	public String getGender(){
		return this._gender;
	}
	
	// setting phone number
	public void setGender(String gender){
		this._gender = gender;
	}
}
