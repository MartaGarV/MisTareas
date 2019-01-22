package com.example.marta.mistareasitt;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
    }

    public void crearCuenta (View view){

        Toast toast = Toast.makeText(this, "Funcionalidad no implementada", Toast.LENGTH_LONG);
        toast.show();
    }

    public void login (View view){

        TextInputEditText usuario = (TextInputEditText) findViewById(R.id.cajaUser);
        TextInputEditText pass = (TextInputEditText) findViewById(R.id.cajaPass);

        if(usuario.getText().toString().equals("itt") && pass.getText().toString().equals("123")){

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }else{
            Toast toast = Toast.makeText(this, "Credenciales inválidas", Toast.LENGTH_LONG);
            toast.show();
        }


    }
}
