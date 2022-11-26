package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import Model.Notas;
import Utils.Apis;
import Utils.NotasServices;
import adapter.NotasAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotasActivity extends AppCompatActivity {
    NotasServices service;
    ArrayAdapter<Notas> adapter;
    ListView listView;
    List<Notas> listNotas;
    FloatingActionButton btnAdd;
    ArrayList<Notas> posiciones;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);

        listView =  (ListView) findViewById(R.id.notas_list);
        btnAdd=(FloatingActionButton)findViewById(R.id.AddNotas);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NotasActivity.this, createNotasActivity.class);
                startActivity(intent);
            }
        });

        listNotas();
    }

    public void listNotas(){
        service = Apis.getNotasService();
        Call<List<Notas>> call = service.getNotas();
        call.enqueue(
                new Callback<List<Notas>>() {
                    @Override
                    public void onResponse(Call<List<Notas>> call, Response<List<Notas>> response) {
                        listNotas = response.body();
                        adapter = new  NotasAdapter(NotasActivity.this,R.layout.activity_notas_items,listNotas);
                        listView.setAdapter(adapter);
                        listView.setOnItemClickListener(
                                new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                        AppCompatImageView b = view.findViewById(R.id.btnDelete);
                                        AppCompatImageView update = view.findViewById(R.id.btnUpdate);

                                        b.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                Notas n = listNotas.get(i);
                                                delete(n.getId(),i);
                                            }
                                        });

                                        update.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                Intent intent =new Intent(NotasActivity.this,updateNotas.class);
                                                Notas n = listNotas.get(i);
                                                intent.putExtra("id", n.getId());
                                                startActivity(intent);
                                            }
                                        });
                                    }
                                }
                        );
                    }

                    @Override
                    public void onFailure(Call<List<Notas>> call, Throwable t) {

                    }
                }
        );
    }

    public void delete(int id, int position){
        service = Apis.getNotasService();
        Call<Notas> call = service.deleteNotas(id);
        call.enqueue(
                new Callback<Notas>() {
                    @Override
                    public void onResponse(Call<Notas> call, Response<Notas> response) {
                        if (response.isSuccessful()) {
                            listNotas.remove(position);
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<Notas> call, Throwable t) {

                    }
                }
        );
    }

}