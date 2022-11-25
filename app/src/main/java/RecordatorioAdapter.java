import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.List;

import Utils.Recordatorio;

public class RecordatorioAdapter extends ArrayAdapter {

    private Context context;
    private List<Recordatorio>recordatorio;

    public RecordatorioAdapter(@NonNull Context context, int resource, @NonNull List<Recordatorio> objects) {
        super(context, resource);

        this.context = context;
        this.recordatorio = objects;
    }
/*
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater layoutInflater = ( LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView=layoutInflater.inflate(R.layout.activity_main,parent,false);

    }
*/


}
