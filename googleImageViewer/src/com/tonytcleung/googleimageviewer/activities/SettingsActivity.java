package com.tonytcleung.googleimageviewer.activities;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.tonytcleung.googleimageviewer.R;
import com.tonytcleung.googleimageviewer.models.SearchSettings;

public class SettingsActivity extends Activity {
	public SearchSettings		settings;
	
	private Spinner				spImageSize;
	private Spinner 			spColorFilter;
	private Spinner 			spImageType;
	private EditText			etSiteFilter;
	
	private ArrayList<String>	imageSizeArray;
	private ArrayList<String> 	colorFilterArray;
	private ArrayList<String> 	imageTypeArray;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
		settings	= (SearchSettings) getIntent().getParcelableExtra(SearchActivity.INTENT_SETTINGS);
		
		setupViews();
		populateSpinners();
		loadSettings();
	}
	
	/**
	 * save the settings to persistence
	 * @param view
	 */
	public void saveSettings(View view) {
		Toast.makeText(this, "Save", Toast.LENGTH_SHORT).show();
		
		// retrieve values from spinners and editText
		String imageSize		= spImageSize.getSelectedItem().toString();
		String colorFilter		= spColorFilter.getSelectedItem().toString();
		String imageType		= spImageType.getSelectedItem().toString();
		String siteFilter		= etSiteFilter.getText().toString();
		
		// save selected fields to settings
		settings.imageSize		= imageSize;
		settings.colorFilter	= colorFilter;
		settings.imageType		= imageType;
		settings.siteFilter		= siteFilter;

		// send settings back in intent
		Intent intent			= new Intent();
		intent.putExtra(SearchActivity.INTENT_SETTINGS, settings);

		setResult(RESULT_OK, intent);
		finish();
	}
	/**
	 * setup the spinners and edit texts
	 */
	private void setupViews() {
    	spImageSize		= (Spinner) findViewById(R.id.spImageSize);
    	spColorFilter	= (Spinner) findViewById(R.id.spColorFilter);
    	spImageType		= (Spinner) findViewById(R.id.spImageType);
    	etSiteFilter	= (EditText) findViewById(R.id.etSiteFilter);
	}
	
	/**
	 * setup the array list and set the spinner adapters for each array
	 */
	private void populateSpinners() {
		imageSizeArray		= SearchSettings.getImageSizeOptions();
		colorFilterArray	= SearchSettings.getColorFilterOptions();
		imageTypeArray		= SearchSettings.getImageTypeOptions();

		spImageSize.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, imageSizeArray));
		spColorFilter.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, colorFilterArray));
		spImageType.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, imageTypeArray));
	}
	
	
	/**
	 * load settings from sharedPreferences and set the spinners to it
	 */
	private void loadSettings() {
		spImageSize.setSelection(imageSizeArray.indexOf(settings.imageSize));
		spColorFilter.setSelection(colorFilterArray.indexOf(settings.colorFilter));
		spImageType.setSelection(imageTypeArray.indexOf(settings.imageType));
		etSiteFilter.setText(settings.siteFilter);
	}
}
