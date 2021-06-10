package com.example.miwok.by.earthquakebyas;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class earthQuakeAdapter extends ArrayAdapter<earthquakes> {

    private static final String LOCATION_SEPARATOR = " of ";
    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context              The current context. Used to inflate the layout file.
     * @param earthquakesArrayList A List of AndroidFlavor objects to display in a list
     */
    public earthQuakeAdapter(Activity context, ArrayList<earthquakes> earthquakesArrayList) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, earthquakesArrayList);
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position    The position in the list of data that should be displayed in the
     *                    list item view.
     * @param convertView The recycled view to populate.
     * @param parent      The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.earthquake_item_view, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        earthquakes currentearthquakes = getItem(position);

        // Format the magnitude to show 1 decimal place
        String formattedMagnitude = formatMagnitude(currentearthquakes.getmMegnitude());
        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.magnitude);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        magnitudeTextView.setText(formattedMagnitude);

         // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudenBackgroundCircle = (GradientDrawable) magnitudeTextView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentearthquakes.getmMegnitude());

        // Set the color on the magnitude circle
        magnitudenBackgroundCircle.setColor(magnitudeColor);

        // find the full location detail
        String originalLocation = currentearthquakes.getmLocation();

        String frontLocation;
        String mainlocation;

        // check if string contains __of__


        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            frontLocation = parts[0] + LOCATION_SEPARATOR;
            mainlocation = parts[1];
        } else {
            frontLocation = getContext().getString(R.string.near_the);
            mainlocation = originalLocation;
        }


        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView mainlocationTextView = (TextView) listItemView.findViewById(R.id.mainlocation);
        TextView frontTextView = (TextView) listItemView.findViewById(R.id.frontlocation);

        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        mainlocationTextView.setText(mainlocation);
        frontTextView.setText(frontLocation);



        // Create a new Date object from the time in milliseconds of the earthquake
        Date dateObject = new Date(currentearthquakes.getmDateandTime());

        // Format the date string (i.e. "Mar 3, 1984")
        String formattedDate = formatDate(dateObject);


        // Find the TextView with view ID date
        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        // Format the date string (i.e. "Mar 3, 1984")
        String onlyDate = formatDate(dateObject);
        // Display the date of the current earthquake in that TextView

        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);

        dateTextView.setText(onlyDate);

        // Format the time string (i.e. "4:30PM")
        String onlyTime = formatTime(dateObject);

        TextView timeTextView = (TextView) listItemView.findViewById(R.id.time);

        // Display the time of the current earthquake in that TextView
        timeTextView.setText(onlyTime);

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;

    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    /**
     * Return the formatted magnitude string showing 1 decimal place (i.e. "3.2")
     * from a decimal magnitude value.
     */
    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }
    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}
