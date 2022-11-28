package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import Model.Notas;
import Model.Recordatorio;
import Utils.Apis;
import Utils.RecordatorioService;

import adapter.RecordatorioAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;




public class recordatorio extends AppCompatActivity {

    RecordatorioService service;
    FloatingActionButton btnAdd;

    List<Recordatorio> listRecordatorios;
    ArrayAdapter<Recordatorio> adapter;
    ListView listView;
    Button btnDashboard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recordatorio);

        listView =  (ListView) findViewById(R.id.recordatorio_list);
        btnAdd=(FloatingActionButton)findViewById(R.id.AddRecordatorio);
        btnDashboard=(Button) findViewById(R.id.dashboard);

        btnDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(recordatorio.this, dashboard.class);
                startActivity(intent);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(recordatorio.this, createRecordatorioActivity.class);
                startActivity(intent);
            }
        });

        listRecordatorios();
    }


    public void listRecordatorios(){
        service = Apis.getRecordatorioService();
        Call<List<Recordatorio>> call = service.getRecordatorio();
        call.enqueue(
                new Callback<List<Recordatorio>>() {
                    @Override
                    public void onResponse(Call<List<Recordatorio>> call, Response<List<Recordatorio>> response) {
                        listRecordatorios = response.body();
                        adapter = new RecordatorioAdapter(recordatorio.this,R.layout.activity_recordatorio_items,listRecordatorios);
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
                                                Recordatorio n = listRecordatorios.get(i);
                                                delete(n.getId(),i);
                                            }
                                        });

                                        update.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                Intent intent =new Intent(recordatorio.this, updateRecordatorio.class);
                                                Recordatorio n = listRecordatorios.get(i);
                                                intent.putExtra("id", n.getId());
                                                intent.putExtra("nombre", n.getNombre());
                                                intent.putExtra("description",n.getDescripcion());
                                                intent.putExtra("fecha_inicio",n.getFecha_inicio());
                                                intent.putExtra("fecha_final",n.getFecha_final());
                                                startActivity(intent);
                                            }
                                        });
                                    }
                                }
                        );
                    }

                    @Override
                    public void onFailure(Call<List<Recordatorio>> call, Throwable t) {

                    }
                }
        );
    }

    public void delete(int id, int position){
        service = Apis.getRecordatorioService();
        Call<Recordatorio> call = service.deleteRecordatorio(id);
        call.enqueue(
                new Callback<Recordatorio>() {
                    @Override
                    public void onResponse(Call<Recordatorio> call, Response<Recordatorio> response) {
                        if (response.isSuccessful()) {
                            listRecordatorios.remove(position);
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<Recordatorio> call, Throwable t) {

                    }
                }
        );
    }



}