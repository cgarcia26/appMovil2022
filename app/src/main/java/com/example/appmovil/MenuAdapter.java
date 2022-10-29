package com.example.appmovil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuAdapter extends ArrayAdapter {

    String[] data;

    public MenuAdapter(Context context, String[] data){
        super(context, R.layout.menu_adapter, data);
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View listItemView;

        listItemView = null == convertView ? layoutInflater.inflate(
                R.layout.menu_adapter,
                parent,
                false): convertView;

        TextView titulo = listItemView.findViewById(R.id.texto);
        final ImageView imageView = listItemView.findViewById(R.id.imagen);

        titulo.setText(data[position]);
        if(position==0){
            imageView.setImageResource(R.drawable.list_app);
        }else{
            imageView.setImageResource(R.drawable.category);
        }


        return listItemView;
    }
}