package com.example.earthquake;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

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
        t1.setText(""+e.getMagnitude());
        TextView t2=item.findViewById(R.id.loc);
        t2.setText(e.getLocation());
        TextView t3= item.findViewById(R.id.date);
        t3.setText(e.getDate());

        return item;
    }
}
