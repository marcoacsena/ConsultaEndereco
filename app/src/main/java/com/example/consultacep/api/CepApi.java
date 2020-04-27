package com.example.consultacep.api;

import com.example.consultacep.model.Cep;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CepApi {

    @GET("{CEP}/json/")
    Call<Cep> getCep(@Path("CEP") String CEP);
}
