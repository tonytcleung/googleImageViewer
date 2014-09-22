package com.tonytcleung.googleimageviewer.models;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;

public class ImageResult implements Parcelable {
	
	public String	title;
	public String	fullURL;
	public String 	thumbURL;
	public float	width;
	public float	height;

	private static final String TITLE_JSON_KEY		= "title"; 
	private static final String	FULL_URL_JSON_KEY	= "url"; 
	private static final String	THUMB_URL_JSON_KEY	= "tbUrl"; 
	private static final String	WIDTH_JSON_KEY		= "width"; 
	private static final String	HEIGHT_JSON_KEY		= "height"; 
	
	/**
	 * return imageResult from JSONObject
	 * @param rawImageResult
	 */
	public  ImageResult(JSONObject json) {
		try {
			this.title		= json.getString(TITLE_JSON_KEY);
			this.fullURL	= json.getString(FULL_URL_JSON_KEY);
			this.thumbURL	= json.getString(THUMB_URL_JSON_KEY);
			this.width		= Float.valueOf(json.getString(WIDTH_JSON_KEY));
			this.height		= Float.valueOf(json.getString(HEIGHT_JSON_KEY));
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return;
	}
	
	/**
	 * return array list of imageResult from JSONArray
	 * @param array
	 * @return
	 */
	public static ArrayList<ImageResult> ImageResultsArrayList(JSONArray array) {
		ArrayList<ImageResult> results	= new ArrayList<ImageResult>();
		
		for (int i = 0; i < array.length(); i++) {
			try {
				results.add(new ImageResult(array.getJSONObject(i)));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}	
		return results;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeString(title);
		out.writeString(fullURL);
		out.writeString(thumbURL);
		out.writeFloat(width);
		out.writeFloat(height);	
	}
	
	/**
	 * static variable for parcelable 
	 */
	public static final Parcelable.Creator<ImageResult> CREATOR = new Parcelable.Creator<ImageResult>() {
		@Override
		public ImageResult createFromParcel(Parcel in) {
			return new ImageResult(in);
		}

		@Override
		public ImageResult[] newArray(int size) {
			return new ImageResult[size];
		}
	};
	
	/**
	 * constructer that is built using the parcel
	 * @param in
	 */
    private ImageResult(Parcel in) {
        title		= in.readString();
        fullURL		= in.readString();
        thumbURL	= in.readString();
        width		= in.readFloat();
        height		= in.readFloat();
    }
}
