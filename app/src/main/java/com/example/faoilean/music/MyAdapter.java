package com.example.faoilean.music;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Faoilean on 21/11/2017.
 */

/*
Music app
Student: Faoilean Fortune
Student Number: C15380201
Mobile Software Development*/

public class MyAdapter extends ArrayAdapter<String> {

    public MyAdapter(@NonNull Context context, String[] values) {
        super(context, R.layout.row_layout, values);
    }

    @NonNull
    @Override
    //array adapter used to connect the layout to the list
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater theInflater = LayoutInflater.from(getContext()); //put layout in correct view

        View theView = theInflater.inflate(R.layout.row_layout, parent, false);

        String choice = getItem(position);

        TextView theText = (TextView) theView.findViewById(R.id.textView1);

        theText.setText(choice);

        return theView;
    }
}
