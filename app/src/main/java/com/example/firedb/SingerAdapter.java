package com.example.firedb;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class SingerAdapter extends ArrayAdapter<Singer> {

    //variable
    private Activity context;
    private List<Singer> singerList;

    //constructor
    public SingerAdapter(Activity context, List<Singer> singerList) {
        super(context, R.layout.item_layout, singerList);
        this.context=context;
        this.singerList= singerList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater =context.getLayoutInflater();
        View view= inflater.inflate(R.layout.item_layout, null, true);

        TextView tvName=view.findViewById(R.id.textName_id);
        TextView tvGenre =view.findViewById(R.id.textGenre_id);

        Singer obj =singerList.get(position);
        tvName.setText(obj.getSingerName());
        tvGenre.setText(obj.getSingerGenre());

        return view;
    }
}
