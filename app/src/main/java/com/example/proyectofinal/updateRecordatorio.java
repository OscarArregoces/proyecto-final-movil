package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import Model.Notas;
import Model.Recordatorio;
import Utils.Apis;
import Utils.NotasServices;
import Utils.RecordatorioService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class updateRecordatorio extends AppCompatActivity {
    RecordatorioService service;


    EditText txtnombre;
    EditText txtdescription;
    CheckBox txtestado;
    TextView txttvSelectDate;
    TextView txtetSelectDate;

    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_recordatorio);


        Bundle extras = getIntent().getExtras();
        final int Id = extras.getInt("id");
        final String nombre = extras.getString("nombre");
        final String description = extras.getString("description");
        final Boolean estado = extras.getBoolean("estado");
        final String fecha_inicio = extras.getString("fecha_inicio");
        final String fecha_final = extras.getString("fecha_final");

        txtnombre=(EditText)findViewById(R.id.nombre);
        txtdescription=(EditText)findViewById(R.id.description);
        txtestado= (CheckBox) findViewById(R.id.estado);
        txttvSelectDate= (TextView) findViewById(R.id.tvSelectDate);
        txtetSelectDate= (TextView) findViewById(R.id.etSelectDate);


        btnSave=(Button)findViewById(R.id.OnSave);

        txtnombre.setText(nombre);
        txtdescription.setText(description);
        txttvSelectDate.setText(fecha_inicio);
        txtetSelectDate.setText(fecha_final);

        if(estado){
            txtestado.setChecked(true);
        }else {
            txtestado.setChecked(false);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Recordatorio recordatorio = new Recordatorio();

                recordatorio.setDescripcion(txtdescription.getText().toString());
                recordatorio.setNombre(txtnombre.getText().toString());
                recordatorio.setFecha_inicio(txttvSelectDate.getText().toString());
                recordatorio.setFecha_final(txtetSelectDate.getText().toString());

                if(txtestado.isChecked()){
                    recordatorio.setEstado(true);
                }else {
                    recordatorio.setEstado(false);
                }
                updateRecordatorio(recordatorio,Id);
            }
        });

        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        txttvSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog dialog = new DatePickerDialog(updateRecordatorio.this, new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        month = month+1;

                        String date = year+"-"+ month+"-" +dayOfMonth;
                        txttvSelectDate.setText(date);

                    }
                },year, month,day);

                dialog.show();

            }
        });

        txtetSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog dialog = new DatePickerDialog(updateRecordatorio.this, new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        month = month+1;

                        String date = year+"-"+ month+"-" +dayOfMonth;
                        txtetSelectDate.setText(date);

                    }
                },year, month,day);

                dialog.show();

            }
        });
    }


    public void updateRecordatorio(Recordatorio p, int id){
        service= Apis.getRecordatorioService();
        Call<Recordatorio> call=service.updateRecordatorio(p,id);
        call.enqueue(new Callback<Recordatorio>() {
            @Override
            public void onResponse(Call<Recordatorio> call, Response<Recordatorio> response) {
                if(response.isSuccessful()){
                    Toast.makeText(updateRecordatorio.this,"Se Actualizó con éxito",Toast.LENGTH_LONG).show();
                    Intent intent =new Intent(updateRecordatorio.this, recordatorio.class);
                    startActivity(intent);
                }
            }
            @Override
            public void onFailure(Call<Recordatorio> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
    }
}