package com.example.eventmanagement;

import android.graphics.Bitmap;

public class Image {
	int _id;
	String _name;
	byte[] _image;

	// Empty constructor
	public Image() {

	}

	// constructor
	public Image(int keyId, String name, byte[] image) {
		this._id = keyId;
		this._name = name;
		this._image = image;

	}
	public Image(String name, byte[] image) {
		this._name = name;
		this._image = image;

	}
	public Image(int keyId) {
		this._id = keyId;

	}

	// getting ID
	public int getID() {
		return this._id;
	}

	// setting id
	public void setID(int keyId) {
		this._id = keyId;
	}

	// getting name
	public String getName() {
		return this._name;
	}

	// setting name
	public void setName(String name) {
		this._name = name;
	}

	
	public byte[] getImage() {
		return this._image;
	}

	
	public void setImage(byte[] image) {
		this._image = image;
	}
}
