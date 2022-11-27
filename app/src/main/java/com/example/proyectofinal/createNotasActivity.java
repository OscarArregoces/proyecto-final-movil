package com.example.proyectofinal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import Model.Notas;
import Utils.Apis;
import Utils.NotasServices;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class createNotasActivity extends AppCompatActivity {

    NotasServices service;
    EditText txtNombre;
    EditText txtDescription;
    CheckBox txtEstado;
    Button btnSave;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_activity_notas);

        txtNombre=(EditText)findViewById(R.id.nombre);
        txtDescription=(EditText)findViewById(R.id.description);
        txtEstado= (CheckBox) findViewById(R.id.estado);
        btnSave=(Button)findViewById(R.id.OnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notas notas=new Notas();
                notas.setNombre(txtNombre.getText().toString());
                notas.setDescripcion(txtDescription.getText().toString());
                if(txtEstado.isChecked()){
                    notas.setEstado(true);
                }else {
                    notas.setEstado(false);
                }
                if(txtNombre.length() == 0 || txtDescription.length() == 0){
                    Toast.makeText(createNotasActivity.this, "Todos los campos son necesarios ", Toast.LENGTH_LONG).show();
                }else{
                    addPersona(notas);
                }
            }
        });
    }

    public void addPersona(Notas notas) {
        service = Apis.getNotasService();

        Call<Notas> call = service.addNotas(notas);
        call.enqueue(new Callback<Notas>() {
            @Override
            public void onResponse(Call<Notas> call, Response<Notas> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(createNotasActivity.this, "Nota Guardada exitosamente", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(createNotasActivity.this, NotasActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(createNotasActivity.this, "Datos incorrectas", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Notas> call, Throwable t) {
                Log.e("Error:", t.getMessage());
            }
        });
    }
}
