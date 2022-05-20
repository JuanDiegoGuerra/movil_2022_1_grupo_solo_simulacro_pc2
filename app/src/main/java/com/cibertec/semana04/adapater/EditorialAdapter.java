package com.cibertec.semana04.adapater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cibertec.semana04.R;
import com.cibertec.semana04.entity.Editorial;

import java.util.List;

public class EditorialAdapter extends ArrayAdapter<Editorial>  {

    private Context context;
    private List<Editorial> lista;

    public EditorialAdapter(@NonNull Context context, int resource, @NonNull List<Editorial> lista) {
        super(context, resource, lista);
        this.context = context;
        this.lista = lista;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.activity_editorial_item, parent, false);

        final Editorial obj = lista.get(position);

        TextView txtId = row.findViewById(R.id.idItemIdEditorial);
        txtId.setText(String.valueOf(obj.getIdEditorial()));

        TextView txtNombre = row.findViewById(R.id.idItemNombEditorial);
        txtNombre.setText(String.valueOf(obj.getNombre()));

        TextView txtPais = row.findViewById(R.id.idItemPaisEditorial);
        txtPais.setText(String.valueOf(obj.getPais()));

        return row;
    }

}
