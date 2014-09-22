package com.tonytcleung.googleimageviewer.models;

import java.util.ArrayList;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Parcel;
import android.os.Parcelable;

public class SearchSettings implements Parcelable  {

	public static final String	NO_PREFERENCE_SELECTED		= "none";
	public static final String	SETTINGS_PREFERENCE_NAME	= "settingsPreference";
	public static final String	SETTINGS_IMAGE_SIZE			= "imageSize";
	public static final String	SETTINGS_COLOR_FILTER		= "colorFilter";
	public static final String	SETTINGS_IMAGE_TYPE			= "imageType";
	public static final String	SETTINGS_SITE_FILTER		= "siteFilter";
	
	public String	imageSize;
	public String	colorFilter;
	public String	imageType;
	public String	siteFilter;

	
	public SearchSettings() {
		super();
	}
	
	/**
	 * retrieve the search settings from sharedPreferences
	 * @return
	 */
    public static SearchSettings getSettingsFromSharedPreference(Context context) {

		SharedPreferences sharedPreferences	= context.getSharedPreferences(SETTINGS_PREFERENCE_NAME, 0);
		
		SearchSettings searchSettings		= new SearchSettings();
		
		searchSettings.imageSize			= sharedPreferences.getString(SETTINGS_IMAGE_SIZE, "");
		searchSettings.colorFilter			= sharedPreferences.getString(SETTINGS_COLOR_FILTER, "");
		searchSettings.imageType			= sharedPreferences.getString(SETTINGS_IMAGE_TYPE, "");
		searchSettings.siteFilter			= sharedPreferences.getString(SETTINGS_SITE_FILTER, "");
		
		return searchSettings;
    }
    
    /**
     * save the settings to shared preference
     */
    public void saveSettingsToSharedPreference(Context context)
    {
		// save selected fields to preference
		SharedPreferences settings		= context.getSharedPreferences(SETTINGS_PREFERENCE_NAME, 0);
		SharedPreferences.Editor editor	= settings.edit();
		editor.putString(SETTINGS_IMAGE_SIZE, imageSize);
		editor.putString(SETTINGS_COLOR_FILTER, colorFilter);
		editor.putString(SETTINGS_IMAGE_TYPE, imageType);
		editor.putString(SETTINGS_SITE_FILTER, siteFilter);
		editor.commit();
    }
    

	/**
	 * return if an image size filter is selected
	 * @return
	 */
	public boolean hasImageSizeFilter() {
		return !imageSize.equals(SearchSettings.NO_PREFERENCE_SELECTED);
	}
	
	/**
	 * return if an color filter is selected
	 * @return
	 */
	public boolean hasColorFilter() {
		return !colorFilter.equals(SearchSettings.NO_PREFERENCE_SELECTED);
	}
	
	/**
	 * return if an image type filter is selected
	 * @return
	 */
	public boolean hasImageTypeFilter() {
		return !imageType.equals(SearchSettings.NO_PREFERENCE_SELECTED);
	}
	
	/**
	 * return if an site filter is selected
	 * @return
	 */
	public boolean hasSiteFilter() {
		return !siteFilter.trim().equals("");
	}
	
    
    /**
     * return the list of options for image Size
     * @return
     */
    public static ArrayList<String> getImageSizeOptions() {
		ArrayList<String> imageSizeArray	= new ArrayList<String>();
		imageSizeArray.add(NO_PREFERENCE_SELECTED);
		imageSizeArray.add("icon");
		imageSizeArray.add("small");
		imageSizeArray.add("medium");
		imageSizeArray.add("large");
		imageSizeArray.add("xlarge");
		imageSizeArray.add("xxlarge");
		imageSizeArray.add("huge");
		
		return imageSizeArray;
    }
    
    /**
     * return the list of options for color filters
     * @return
     */
    public static ArrayList<String> getColorFilterOptions() {		
    	ArrayList<String> colorFilterArray	= new ArrayList<String>();
		colorFilterArray.add(NO_PREFERENCE_SELECTED);
		colorFilterArray.add("black");
		colorFilterArray.add("blue");
		colorFilterArray.add("brown");
		colorFilterArray.add("gray");
		colorFilterArray.add("green");
		colorFilterArray.add("orange");
		colorFilterArray.add("pink");
		colorFilterArray.add("purple");
		colorFilterArray.add("red");
		colorFilterArray.add("teal");
		colorFilterArray.add("white");
		colorFilterArray.add("yellow");
		
		return colorFilterArray; 
    }
    
    /**
     * return the list of options for image Size
     * @return
     */
    public static ArrayList<String> getImageTypeOptions() {
		ArrayList<String> imageTypeArray	= new ArrayList<String>();
		imageTypeArray.add(NO_PREFERENCE_SELECTED);
		imageTypeArray.add("face");
		imageTypeArray.add("photo");
		imageTypeArray.add("clipart");
		imageTypeArray.add("lineart");
		
		return imageTypeArray;
    }

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeString(imageSize);
		out.writeString(colorFilter);
		out.writeString(imageType);
		out.writeString(siteFilter);
	}
	
	/**
	 * static variable for parcelable 
	 */
	public static final Parcelable.Creator<SearchSettings> CREATOR = new Parcelable.Creator<SearchSettings>() {
		@Override
		public SearchSettings createFromParcel(Parcel in) {
			return new SearchSettings(in);
		}

		@Override
		public SearchSettings[] newArray(int size) {
			return new SearchSettings[size];
		}
	};
	
	/**
	 * constructer that is built using the parcel
	 * @param in
	 */
    private SearchSettings(Parcel in) {
    	imageSize	= in.readString();
    	colorFilter	= in.readString();
    	imageType	= in.readString();
    	siteFilter	= in.readString();
    }
}
