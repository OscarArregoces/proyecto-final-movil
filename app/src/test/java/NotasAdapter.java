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

import java.util.List;

import Model.Notas;

public class NotasAdapter extends ArrayAdapter {

    private Context context;
    private List<Notas>notas;

    public NotasAdapter(@NonNull Context context, int resource, @NonNull List<Notas> objects) {
        super(context, resource);
        this.context = context;
        this.notas = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView=layoutInflater.inflate(R.layout.activity_notas_items,parent,false);

        TextView txtNombre=(TextView) rowView.findViewById(R.id.nombre);
        TextView txtDescription=(TextView) rowView.findViewById(R.id.description);
        TextView txtEstado=(TextView) rowView.findViewById(R.id.description);


        txtNombre.setText(String.format("NOMBRE:%s", notas.get(position).getNombre()));
        txtDescription.setText(String.format("DESCRIPTION:%s", notas.get(position).getDescripcion()));
        txtEstado.setText(String.format("ESTADO:%s", notas.get(position).getEstado()));


        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, NotasActivity.class);
                intent.putExtra("id",String.valueOf(notas.get(position).getId()));
                context.startActivity(intent);
            }
        });

        return rowView;
    }



}
