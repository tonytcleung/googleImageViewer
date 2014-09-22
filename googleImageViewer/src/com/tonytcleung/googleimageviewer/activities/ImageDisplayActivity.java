package com.tonytcleung.googleimageviewer.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.tonytcleung.googleimageviewer.R;
import com.tonytcleung.googleimageviewer.models.ImageResult;

public class ImageDisplayActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_display);
		// pull out the imageResult from the intent
		ImageResult imageResult		= (ImageResult) getIntent().getParcelableExtra(SearchActivity.INTENT_IMAGE_RESULT);
		// find the image view
		ImageView ivImageResult	= (ImageView) findViewById(R.id.ivImageResult);
		// load the image url into the imageview using picasso
		Picasso.with(this).load(imageResult.fullURL).into(ivImageResult);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.image_display, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
