package com.example.proyectofinal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import Model.Notas;
import Utils.Apis;
import Utils.NotasServices;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class updateNotas extends AppCompatActivity {

    NotasServices service;
    EditText txtNombre;
    EditText txtDescription;
    CheckBox txtEstado;
    Button btnSave;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_notas);

        Bundle extras = getIntent().getExtras();
        final int Id = extras.getInt("id");
        final String nombre = extras.getString("nombre");
        final String description = extras.getString("description");
        final Boolean estado = extras.getBoolean("estado");

        txtNombre=(EditText)findViewById(R.id.nombreup);
        txtDescription=(EditText)findViewById(R.id.descriptionup);
        txtEstado= (CheckBox) findViewById(R.id.estadoup);
        btnSave=(Button)findViewById(R.id.OnUpdate);

        txtNombre.setText(nombre);
        txtDescription.setText(description);
        if(estado){
            txtEstado.setChecked(true);
        }else {
            txtEstado.setChecked(false);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Notas notas = new Notas();

                notas.setDescripcion(txtDescription.getText().toString());
                notas.setNombre(txtNombre.getText().toString());
                if(txtEstado.isChecked()){
                    notas.setEstado(true);
                }else {
                    notas.setEstado(false);
                }
                updateNotas(notas,Id);
            }
        });
    }

    public void updateNotas(Notas p, int id){
        service= Apis.getNotasService();
        Call<Notas> call=service.updateNotas(p,id);
        call.enqueue(new Callback<Notas>() {
            @Override
            public void onResponse(Call<Notas> call, Response<Notas> response) {
                if(response.isSuccessful()){
                    Toast.makeText(updateNotas.this,"Se Actualizó con éxito",Toast.LENGTH_LONG).show();
                    Intent intent =new Intent(updateNotas.this,NotasActivity.class);
                    startActivity(intent);
                }
            }
            @Override
            public void onFailure(Call<Notas> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
    }
}
