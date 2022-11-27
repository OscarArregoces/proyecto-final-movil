import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.proyectofinal.NotasActivity;
import com.example.proyectofinal.R;
import com.example.proyectofinal.recordatorio;

import java.util.List;


import Model.Recordatorio;

public class RecordatorioAdapter extends ArrayAdapter {

    private Context context;
    private List<Recordatorio>recordatorio;

    public RecordatorioAdapter(@NonNull Context context, int resource, @NonNull List<Recordatorio> objects) {
        super(context, resource);

        this.context = context;
        this.recordatorio = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView=layoutInflater.inflate(R.layout.activity_recordatorio_items,parent,false);

        TextView txtNombre=(TextView) rowView.findViewById(R.id.nombre);
        TextView txtDescription=(TextView) rowView.findViewById(R.id.description);
        TextView txtEstado=(TextView) rowView.findViewById(R.id.estado);
        TextView txtFechaInicio=(TextView) rowView.findViewById(R.id.txtFechaInicio);
        TextView txtFechaFinal=(TextView) rowView.findViewById(R.id.txtFechaFinal);


        txtNombre.setText(String.format("NOMBRE:%s", recordatorio.get(position).getNombre()));
        txtDescription.setText(String.format("DESCRIPTION:%s", recordatorio.get(position).getDescripcion()));
        txtEstado.setText(String.format("ESTADO:%s", recordatorio.get(position).getEstado()));
        txtFechaInicio.setText(String.format("FECHA_INICIO:%s", recordatorio.get(position).getFecha_inicio()));
        txtFechaFinal.setText(String.format("FECHA_FINAL:%s", recordatorio.get(position).getFecha_final()));


        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, com.example.proyectofinal.recordatorio.class);
                intent.putExtra("id",String.valueOf(recordatorio.get(position).getId()));
                context.startActivity(intent);
            }
        });

        return rowView;
    }



}
