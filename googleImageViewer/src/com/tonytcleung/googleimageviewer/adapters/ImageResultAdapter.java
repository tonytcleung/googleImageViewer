package com.tonytcleung.googleimageviewer.adapters;

import java.util.List;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tonytcleung.googleimageviewer.R;
import com.tonytcleung.googleimageviewer.models.ImageResult;

public class ImageResultAdapter extends ArrayAdapter<ImageResult>{

	// view cache
	private static class ViewHolder {
		ImageView 	ivImage;
		TextView	tvTitle;
	}
	
	public ImageResultAdapter(Context context, List<ImageResult> images) {
		super(context, R.layout.item_image_result, images);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// take the data source
		// get data item
		ImageResult imageResult						= getItem(position);
		
    	// use view holder pattern
    	ViewHolder viewHolder;
        // Check if an existing view is being reused, 
    	// if it is new, inflate the view and set viewHolder as tag
        if (convertView == null) {
        	viewHolder								= new ViewHolder();
        	convertView 							= LayoutInflater.from(getContext()).inflate(R.layout.item_image_result, parent, false);
            viewHolder.ivImage						= (ImageView) convertView.findViewById(R.id.ivImage);
            viewHolder.tvTitle						= (TextView) convertView.findViewById(R.id.tvTitle);
            convertView.setTag(viewHolder);
        }
        // retrieve view holder from tag
        else {
        	viewHolder								= (ViewHolder) convertView.getTag();
        }
			
        // Populate the data into the template view using the data object
        viewHolder.tvTitle.setText(Html.fromHtml(imageResult.title));
        // reset the image from the recycled view
        viewHolder.ivImage.setImageResource(0);
        // ask for the photo to be added to the imageview based on the photo url
        // background: send network request from the url, download image bytes, convert into bitmamp, resizing the image, insert bitmap into the imageview
        // use icon as placeholder
        Picasso.with(getContext()).load(imageResult.thumbURL).into(viewHolder.ivImage);
        
        // Return the completed view to render on screen
        return convertView;
	}
	
	

}
