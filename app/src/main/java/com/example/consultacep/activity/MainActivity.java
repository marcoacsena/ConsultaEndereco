package com.example.consultacep.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        cep = findViewById(R.id.tieCep);
        btnBucarCep = findViewById(R.id.btnBuscarCep);
        tvResultado = findViewById(R.id.tvResultado);

        btnBucarCep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cepDigitado = cep.getText().toString();
                
                obterCep(cepDigitado);
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

                    tvResultado.setText("O bairro é: " +cep.getBairro());
                }
            }

            @Override
            public void onFailure(Call<Cep> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Não foi possível obter o cep!", Toast.LENGTH_SHORT).show();

            }
        });


    }
}
