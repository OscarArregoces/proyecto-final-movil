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
import Utils.RecordatorioService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class createRecordatorioActivity extends AppCompatActivity {

    RecordatorioService service;
    private TextView tvSelectDate;   /*INICIO*/
    private TextView etSelectDate;   /*FINAL*/
    private Button onSave;
    private EditText txtNombre;
    private EditText txtDescription;
    private CheckBox txtEstado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recordatorio);

        tvSelectDate = findViewById(R.id.tvSelectDate);
        etSelectDate = findViewById(R.id.etSelectDate);
        txtEstado = findViewById(R.id.estado);
        txtNombre = findViewById(R.id.nombre);
        txtDescription = findViewById(R.id.description);
        onSave = findViewById(R.id.OnSave);

        Bundle extra = getIntent().getExtras();
        final Integer id = extra.getInt("id");

        onSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Recordatorio recordatorio=new Recordatorio();
                recordatorio.setNombre(txtNombre.getText().toString());
                recordatorio.setDescripcion(txtDescription.getText().toString());
                recordatorio.setFecha_inicio(tvSelectDate.getText().toString());
                recordatorio.setFecha_final(etSelectDate.getText().toString());
                recordatorio.setPropietario(id.toString());

                if(txtEstado.isChecked()){
                   recordatorio.setEstado(true);
                }else{
                    recordatorio.setEstado(false);
                }

                if(txtNombre.length() == 0 || txtDescription.length() == 0 || etSelectDate.length() == 0 || tvSelectDate.length() == 0){
                    Toast.makeText(createRecordatorioActivity.this, "Todos los campos son necesarios ", Toast.LENGTH_LONG).show();
                }else{
                    addRecordatorio(recordatorio, id);
                }


            }
        });

        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);



        tvSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog dialog = new DatePickerDialog(createRecordatorioActivity.this, new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        month = month+1;

                        String date = year+"-"+ month+"-" +dayOfMonth;
                        tvSelectDate.setText(date);

                    }
                },year, month,day);

                dialog.show();

            }
        });

        etSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog dialog = new DatePickerDialog(createRecordatorioActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        month = month+1;
                        String date = year+"-"+ month+"-" +dayOfMonth;
                        etSelectDate.setText(date);

                    }
                },year, month,day);
                dialog.show();

            }
        });

    }


    public void addRecordatorio(Recordatorio recordatorio, Integer id) {
        service = Apis.getRecordatorioService();

        Call<Recordatorio> call = service.addRecordatorio(recordatorio);
        call.enqueue(new Callback<Recordatorio>() {
            @Override
            public void onResponse(Call<Recordatorio> call, Response<Recordatorio> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(createRecordatorioActivity.this, "Recordatorio Guardado", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(createRecordatorioActivity.this, recordatorio.class);
                    intent.putExtra("id",id);
                    startActivity(intent);
                }else{
                    Toast.makeText(createRecordatorioActivity.this, "Datos incorrectas", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Recordatorio> call, Throwable t) {
                Log.e("Error:", t.getMessage());
            }
        });
    }


}