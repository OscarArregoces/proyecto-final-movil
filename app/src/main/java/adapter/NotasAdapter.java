package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.example.proyectofinal.R;

import java.util.List;

import Model.Notas;
import Utils.Apis;
import Utils.NotasServices;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.example.proyectofinal.NotasActivity;

public class NotasAdapter extends ArrayAdapter<Notas> {

    public NotasAdapter(Context context, int layout,List<Notas> notasList){
        super(context,0,notasList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Notas notas = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_notas_items,parent,false);
        }

        TextView txtNombre = convertView.findViewById(R.id.txtNombre);
        TextView txtDescription = convertView.findViewById(R.id.txtDescription);
        TextView txtEstado = convertView.findViewById(R.id.txtEstado);
        TextView txtPropietario = convertView.findViewById(R.id.txtPropietario);

        txtNombre.setText(notas.getNombre());
        txtDescription.setText(notas.getDescripcion());
        txtPropietario.setText(notas.getPropietario());

        if(notas.getEstado()){
            txtEstado.setText("Activo");
        }else {
            txtEstado.setText("Inactivo");
        }

        return convertView;
    }


}
