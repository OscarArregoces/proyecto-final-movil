package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Model.Usuario;
import Utils.Apis;
import Utils.UsuarioService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class register extends AppCompatActivity {

    UsuarioService service;

    EditText txtUsername;
    EditText txtEmail;
    EditText txtPassword;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtUsername=(EditText)findViewById(R.id.username);
        txtEmail=(EditText)findViewById(R.id.email);
        txtPassword=(EditText)findViewById(R.id.password);
        btnSave=(Button)findViewById(R.id.btnSave);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario user=new Usuario();
                user.setUsername(txtUsername.getText().toString());
                user.setEmail(txtEmail.getText().toString());
                user.setPassword(txtPassword.getText().toString());

                if(txtUsername.length() == 0 || txtEmail.length() == 0 || txtPassword.length() == 0){
                    Toast.makeText(register.this, "Todos los campos son necesarios ", Toast.LENGTH_LONG).show();
                }else{
                    registerUser(user);
                }
            }
        });
    }


    public void registerUser(Usuario user) {
        service = Apis.getUsuarioService();

        Call<Usuario> call = service.setRegister(user);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {

                if (response.isSuccessful()) {
                    Toast.makeText(register.this, "Registro exitoso", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(register.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(register.this, "Hubo un problema", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.e("Error:", t.getMessage());
            }
        });
    }
}