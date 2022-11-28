package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class dashboard extends AppCompatActivity {

    Button btnNotas;
    Button btnRecordatorio;
    Button btnSalir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        btnNotas = (Button) findViewById(R.id.btnNotas);
        btnRecordatorio = (Button) findViewById(R.id.btnRecordatorio);
        btnSalir = (Button) findViewById(R.id.btnLogOut);
        Bundle extra = getIntent().getExtras();
        final Integer id = extra.getInt("id");

        System.out.println("Dashboard activity : " +id);


        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnNotas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard.this, NotasActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });

        btnRecordatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard.this,  recordatorio.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });


    }

}