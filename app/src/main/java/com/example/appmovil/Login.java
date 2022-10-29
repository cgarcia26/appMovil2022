package com.example.appmovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Login extends AppCompatActivity {

    EditText usuario, clave;

    TextView forgetPass;

    Button login, registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        usuario = findViewById(R.id.editTextEmail);
        clave = findViewById(R.id.editTextPassword);

        forgetPass = findViewById(R.id.textForgetPassword);

        login = findViewById(R.id.buttonLogin);
        registrar = findViewById(R.id.buttonCreate);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = usuario.getText().toString();
                String pass = clave.getText().toString();

                List<User> list = User.find(User.class,"correo = '"+user+"'", null);

                if(list.size()<=0){
                    Toast.makeText(getApplicationContext(),"usuario no existe",Toast.LENGTH_LONG).show();
                }else {
                    User reg = list.get(0);
                    if(user.equals(reg.getCorreo()) && pass.equals(reg.getPassword())){
                        Intent i = new Intent(Login.this, Home.class);
                        startActivity(i);
                    }else {
                        Toast.makeText(getApplicationContext(), "Usuario y/o Contraseña es incorrecto", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this, SingUp.class);
                startActivity(i);
            }
        });

        forgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this, ForgetPassword.class);
                startActivity(i);
            }
        });

    }
}
