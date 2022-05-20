package com.cibertec.semana04.service;

import com.cibertec.semana04.entity.Editorial;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EditorialService {

    @GET("editorial/porNombre/{nombre}")
    public Call<List<Editorial>> listaEditoriPorNombre(@Path("nombre")String nombre);



}
