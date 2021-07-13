package com.example.earthquake;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EarthquakeArrayAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeArrayAdapter(@NonNull Context context,  @NonNull List<Earthquake> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View item=convertView;
        if(item==null)
        {
            item= LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item,parent,false);
        }

        Earthquake e= getItem(position);
        TextView t1=item.findViewById(R.id.mag);
        GradientDrawable magCircle= (GradientDrawable)t1.getBackground();
        magCircle.setColor(getMagnitudeColor(e.getMagnitude()));
        t1.setText(formatMagnitude(e.getMagnitude()));
        TextView t2=item.findViewById(R.id.locS);
        t2.setText(secondaryLoc(e.getLocation()));
        TextView t3 =item.findViewById(R.id.locP);
        t3.setText(primaryLoc(e.getLocation()));
        long time=e.getDate();
        Date d= new Date(time);
        TextView date = (TextView)item.findViewById(R.id.date);
        date.setText(formatDate(d));
        TextView t = (TextView)item.findViewById(R.id.t);
        t.setText(formatTime(d));

        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String u = e.getUrl();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(u));
                getContext().startActivity(i);
            }
        });


        return item;
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
    private String primaryLoc(String location)
    {
        int index=location.indexOf("of");
        if (index == -1)
            return location;
        return location.substring(index+3);
    }

    private String secondaryLoc(String location)
    {
        int index=location.indexOf("of");
        if (index == -1)
            return "Near of";
        return location.substring(0, index+2);
    }
    /**
     * Return the formatted magnitude string showing 1 decimal place (i.e. "3.2")
     * from a decimal magnitude value.
     */
    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
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
}
