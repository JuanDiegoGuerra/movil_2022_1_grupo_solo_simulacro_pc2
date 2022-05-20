package com.cibertec.semana04;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.cibertec.semana04.adapater.EditorialAdapter;
import com.cibertec.semana04.entity.Editorial;
import com.cibertec.semana04.service.EditorialService;
import com.cibertec.semana04.util.ConnectionRest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    EditText txtNombre;
    Button btnFiltrar;

    //Listview y Adaptador
    ListView lstEditorial;
    List<Editorial> data = new ArrayList<Editorial>();
    EditorialAdapter adaptador;

    //COnexion
    EditorialService api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNombre = findViewById(R.id.txtRegEdiNombre);
        btnFiltrar = findViewById(R.id.btnRegEdiEnviar);

        //construir el listview y adaptador
        lstEditorial = findViewById(R.id.lstConsultaEditorial);
        adaptador = new EditorialAdapter(this,R.layout.activity_editorial_item, data);
        lstEditorial.setAdapter(adaptador);

        api = ConnectionRest.getConnecion().create(EditorialService.class);

        btnFiltrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String filtro = txtNombre.getText().toString();
                consulta(filtro);
            }
        });

    }

    public void consulta(String filtro){
        Call<List<Editorial>> call = api.listaEditoriPorNombre(filtro);
        call.enqueue(new Callback<List<Editorial>>() {
            @Override
            public void onResponse(Call<List<Editorial>> call, Response<List<Editorial>> response) {
                if (response.isSuccessful()){
                    List<Editorial> lstEditorial = response.body();
                    mensajeAlert("Llegaron " + lstEditorial.size() + " elementos ") ;
                    data.clear();
                    data.addAll(lstEditorial);
                    adaptador.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<List<Editorial>> call, Throwable t) {
                mensajeAlert("ERROR -> Error en la respuesta" + t.getMessage());
            }
        });
    }

    public void mensajeAlert(String msg){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage(msg);
        alertDialog.setCancelable(true);
        alertDialog.show();
    }


}