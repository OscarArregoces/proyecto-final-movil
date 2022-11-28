package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.example.proyectofinal.R;

import java.util.List;


import Model.Recordatorio;
import Utils.Apis;
import Utils.NotasServices;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.example.proyectofinal.NotasActivity;

public class RecordatorioAdapter extends ArrayAdapter<Recordatorio> {
    AppCompatImageView b;
    NotasActivity service;




    public RecordatorioAdapter(Context context, int layout,List<Recordatorio> recordatorioList){
        super(context,0,recordatorioList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Recordatorio recordatorio = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_recordatorio_items,parent,false);
        }

        TextView txtNombre = convertView.findViewById(R.id.txtNombre);
        TextView txtDescription = convertView.findViewById(R.id.txtDescription);
        TextView txtFechaInicio = convertView.findViewById(R.id.txtFechaInicio);
        TextView txtFechaFinal = convertView.findViewById(R.id.txtFechaFinal);
        TextView txtEstado = convertView.findViewById(R.id.txtEstado);
        TextView txtPropietario = convertView.findViewById(R.id.txtPropietario);



        txtNombre.setText(recordatorio.getNombre());
        txtDescription.setText(recordatorio.getDescripcion());
        txtFechaInicio.setText(recordatorio.getFecha_inicio());
        txtFechaFinal.setText(recordatorio.getFecha_final());
        txtPropietario.setText(recordatorio.getPropietario());

        if( recordatorio.getEstado() == false){
            txtEstado.setText("Inactivo");
        }else{
            txtEstado.setText("Activo");
        }



        return convertView;
    }


}
