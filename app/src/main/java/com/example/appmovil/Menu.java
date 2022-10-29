package com.example.appmovil;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Menu extends AppCompatActivity {

    ListView lista;
    ArrayAdapter arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        lista = findViewById(R.id.listMenu);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            String valor = extras.getString("extra");
            if(valor.equals("app")){
                arrayAdapter = new AppAdapter(this);
            }else {
                arrayAdapter = new CategoryAdapter(this);
            }
            lista.setAdapter(arrayAdapter);
        }


    }
}