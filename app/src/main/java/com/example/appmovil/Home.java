package com.example.appmovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Home extends AppCompatActivity {

    ListView lista;
    static final String[] titulos_menu = new String[]{"Listado de Apps", "Categorias"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        lista = findViewById(R.id.listview);

        ArrayAdapter arrayAdapter = new MenuAdapter(this, titulos_menu);

        lista.setAdapter(arrayAdapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent menu = new Intent(Home.this, Menu.class);
                if(position==0){
                    menu.putExtra("extra","app");
                }else{
                    menu.putExtra("extra","category");
                }
                startActivity(menu);
            }
        });
    }
}