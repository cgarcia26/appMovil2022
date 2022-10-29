package com.example.appmovil;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;

import java.util.List;

public class ForgetPassword extends AppCompatActivity {

    EditText nombre, apellido, correo, password;

    Button search, edit, delete, showHide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_password);


        nombre = findViewById(R.id.editTextFirstNameFP);
        apellido = findViewById(R.id.editTextLastNameFP);
        correo = findViewById(R.id.editTextEmailFP);
        password = findViewById(R.id.editTextPasswordFP);

        showHide = findViewById(R.id.buttonShowHide);
        search = findViewById(R.id.buttonSearch);
        edit = findViewById(R.id.buttonEdit);
        delete = findViewById(R.id.buttonDelete);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = correo.getText().toString();

                List<User> list = User.find(User.class,"correo = '"+user+"'", null);

                if(list.size()<=0){
                    Toast.makeText(getApplicationContext(),"usuario no existe",Toast.LENGTH_LONG).show();
                }else {
                    User registro = list.get(0);
                    nombre.setText(registro.getNombre());
                    apellido.setText(registro.getApellido());
                    password.setText(registro.getPassword());
                }

                showHide.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(showHide.getText().equals("Ver")) {
                            password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            showHide.setText("Ocultar");
                        }else {
                            password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            showHide.setText("Ver");
                        }
                    }
                });

            }
        });




        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = correo.getText().toString();

                List<User> list = User.find(User.class,"correo = '"+user+"'", null);

                if(list.size()<=0){
                    Toast.makeText(getApplicationContext(),"usuario no existe",Toast.LENGTH_LONG).show();
                }else {
                    User registro = list.get(0);
                    registro.setNombre(nombre.getText().toString());
                    registro.setApellido(apellido.getText().toString());
                    registro.setPassword(password.getText().toString());
                    registro.save();
                    Toast.makeText(getApplicationContext(), "usuario editado satisfactoriamente", Toast.LENGTH_LONG).show();
                }

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = correo.getText().toString();

                List<User> list = User.find(User.class,"correo = '"+user+"'",  null);

                if(list.size()<=0){
                    Toast.makeText(getApplicationContext(),"usuario no existe",Toast.LENGTH_LONG).show();
                }else {
                    User registro = list.get(0);
                    registro.delete();
                    Toast.makeText(getApplicationContext(), "usuario eliminado satisfactoriamente", Toast.LENGTH_LONG).show();

                }

            }
        });

    }
}