import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.proyectofinal.R;

import java.util.List;

import Model.Usuario;

public class UsuarioAdapter extends ArrayAdapter {

    private Context context;
    private List<Usuario>usuarios;

    public UsuarioAdapter(@NonNull Context context, int resource, @NonNull List<Usuario> objects) {
        super(context, resource);

        this.context = context;
        this.usuarios = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater layoutInflater = ( LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView=layoutInflater.inflate(R.layout.activity_main,parent,false);

        TextView txtUsername=(TextView) rowView.findViewById(R.id.username);
        TextView txtPassword=(TextView) rowView.findViewById(R.id.password);

        txtUsername.setText(String.format("USERNAME:%s", usuarios.get(position).getUsername()));
        txtPassword.setText(String.format("PASSWORD:%s", usuarios.get(position).getPassword()));

        return rowView;
    }



}
