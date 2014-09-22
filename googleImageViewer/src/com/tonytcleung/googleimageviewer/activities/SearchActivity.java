package com.tonytcleung.googleimageviewer.activities;

import java.util.ArrayList;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.tonytcleung.googleimageviewer.R;
import com.tonytcleung.googleimageviewer.adapters.ImageResultAdapter;
import com.tonytcleung.googleimageviewer.models.ImageResult;


public class SearchActivity extends Activity {

	public static final String INTENT_KEY						= 	"thumbURL";
	
	private static final int GOOGLE_IMAGE_SEARCH_URL_PAGE_SIZE 	=	8;
	private static final String GOOGLE_IMAGE_SEARCH_URL_VERSION =	"1.0";
	private static final String	GOOGLE_IMAGE_SEARCH_URL 		=	"https://ajax.googleapis.com/ajax/services/search/images?v=" + GOOGLE_IMAGE_SEARCH_URL_VERSION + "&rsz=" + Integer.valueOf(GOOGLE_IMAGE_SEARCH_URL_PAGE_SIZE).toString() + "&q=";

	private EditText 				etQuery;
	private GridView				gvResults;
	private ArrayList<ImageResult>	imageResults;
	private ImageResultAdapter		aImageResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setupViews();
        
        imageResults	= new ArrayList<ImageResult>();
        // link adapter to arraylist and imageview
        aImageResults	= new ImageResultAdapter(this, imageResults);
        gvResults.setAdapter(aImageResults);
    }

    /**
     * map the views
     */
    private void setupViews() {
    	etQuery		= (EditText) findViewById(R.id.etQuerry);
    	gvResults	= (GridView) findViewById(R.id.gvResults);
    	gvResults.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// launch the image display activity
				// create an intent
				Intent intent			= new Intent(SearchActivity.this,  ImageDisplayActivity.class);
				// get the image result to display
				ImageResult imageResult	= imageResults.get(position);
				// pass imageResult to the intent
				intent.putExtra(INTENT_KEY, imageResult);
				// launch the new activity
				startActivity(intent);
				
			}
		});
	}


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_setting, menu);
        return true;
    }
    

    /**
     * when the button is clicked perform a search
     * @param view
     */
    public void onImageSearch(View view) {
     	String query						= etQuery.getText().toString();
    	Toast.makeText(this, query, Toast.LENGTH_SHORT).show();
    	
        AsyncHttpClient client				= new AsyncHttpClient();
        String searchURL					= GOOGLE_IMAGE_SEARCH_URL + query;
        client.get(searchURL, new JsonHttpResponseHandler(){

			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
 				JSONArray imageResultsJson	= null;
 				
 				// grab the list of imageResult JSON objects
 				try {
					imageResultsJson		= response.getJSONObject("responseData").getJSONArray("results");
					// when you update the adapter, it will modify the underlying data
					aImageResults.clear(); // clear on new search, do not clear on paging
					aImageResults.addAll(ImageResult.ImageResultsArrayList(imageResultsJson));
					
				} catch (JSONException e) {
					e.printStackTrace();
				}
				Log.d("DEBUG", imageResults.toString());
 				
			}
        	
        });
    }

    /**
     * show the settings activity
     */
    public void onShowSettings(MenuItem menuItem) {
    	Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
    }
}
