package com.example.eventmanagement;

import android.graphics.Bitmap;

public class Images {
	private Bitmap bmp;
	private String name;
	private int id;

	public Images(Bitmap b, String n) {
		bmp = b;
		name = n;
		//id = k;
	}
	public Images(Bitmap b){
		bmp = b;
	}

	public Bitmap getBitmap() {
		return bmp;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return id;
	}

}

