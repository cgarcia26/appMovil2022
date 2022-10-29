package com.example.appmovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SingUp extends AppCompatActivity {

    EditText nombre, apellido, correo, password, passwordConf;

    Button registrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sing_up);

        nombre = findViewById(R.id.editTextFirstName);
        apellido = findViewById(R.id.editTextLastName);
        correo = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);
        passwordConf = findViewById(R.id.editTextPasswordConf);

        registrar = findViewById(R.id.buttonSingUp);

        registrar.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {

                  String nom = nombre.getText().toString();
                  String apl = apellido.getText().toString();
                  String cor = correo.getText().toString();
                  String pass = password.getText().toString();
                  String passC = passwordConf.getText().toString();

                  if(nom.equals("") && apl.equals("") && cor.equals("") && pass.equals("") && passC.equals("")){
                      nombre.setError("Nombre vacio");
                      apellido.setError("Apellido vacio");
                      correo.setError("Correo vacio");
                      password.setError("Contraseña vacia");
                      passwordConf.setError("Confirmación de contraseña vacia");
                  }

                  if(!cor.contains("@")){
                      correo.setError("El correo no contine @");
                  }

                  if(pass.equals(passC)){

                      User u1 = new User(nom, apl, cor, pass);
                      u1.save();
                      nombre.setText("");
                      apellido.setText("");
                      correo.setText("");
                      password.setText("");
                      passwordConf.setText("");

                  } else {
                      Toast.makeText(getApplicationContext(), "Contraseña no coincide", Toast.LENGTH_LONG).show();
                  }

                  Intent i = new Intent(SingUp.this, Home.class);
                  startActivity(i);

              }
        });



    }
}