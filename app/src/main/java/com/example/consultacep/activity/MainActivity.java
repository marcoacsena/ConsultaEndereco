package com.example.consultacep.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.consultacep.R;
import com.example.consultacep.api.CepApi;
import com.example.consultacep.api.RetrofitClientInstance;
import com.example.consultacep.model.Cep;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    
    private TextInputEditText cep;
    private Button btnBucarCep;
    private TextView tvResultado;
    private ProgressBar progressBar;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        cep = findViewById(R.id.tieCep);
        btnBucarCep = findViewById(R.id.btnBuscarCep);
        tvResultado = findViewById(R.id.tvResultado);
        progressBar = findViewById(R.id.progressBar);

        btnBucarCep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cepDigitado = cep.getText().toString();

                if(!cepDigitado.isEmpty()){
                    obterCep(cepDigitado);
                    progressBar.setVisibility(View.VISIBLE);

                }else Toast.makeText(MainActivity.this, "Campo CEP vazio!", Toast.LENGTH_SHORT).show();
                

            }
        });
    }

    private void obterCep(String cepDigitado) {

        CepApi cepApi = RetrofitClientInstance.getRetrofitInstance().create(CepApi.class);
        Call<Cep> call = cepApi.getCep(cepDigitado);

        call.enqueue(new Callback<Cep>() {
            @Override
            public void onResponse(Call<Cep> call, Response<Cep> response) {
                if(response.isSuccessful()){


                    Cep cep = response.body();

                    String logradouro;
                    logradouro = "- Logradouro: " +cep.getLogradouro() +"\n";

                    tvResultado.setText(logradouro +"- Complemento: " +cep.getComplemento() +"\n"
                            + "- Bairro: " +cep.getBairro() +"\n" + "- Cidade: " +cep.getCidade() +"\n" +"- Cep.: " +cep.getCep());

                    tvResultado.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(Call<Cep> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Não foi possível obter o cep!", Toast.LENGTH_SHORT).show();

            }
        });


    }
}
