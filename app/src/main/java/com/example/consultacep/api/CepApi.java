package com.example.consultacep.api;

import com.example.consultacep.model.Cep;

import java.security.cert.CertificateParsingException;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CepApi {

    @GET("{CEP}/json")
    Call<HashMap<String,String>> getCep(@Path("CEP") String CEP);
}

