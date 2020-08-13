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

import java.util.HashMap;


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
        Call<HashMap<String, String>> call = cepApi.getCep(cepDigitado);

        call.enqueue(new Callback<HashMap<String, String>>() {
            @Override
            public void onResponse(Call<HashMap<String, String>> call, Response<HashMap<String, String>> response) {

                if(response.isSuccessful()){

                    //Cep cep = response.body();
//                    String logradouro;
//                    logradouro = "- Logradouro: " +cep.getLogradouro() +"\n";


                    HashMap<String, String> cep = response.body();

//                    tvResultado.setText("logradouro: " +cep.getLogradouro() +"\n" +"- Complemento: "
//                            +cep.getComplemento() +"\n" + "- Bairro: " +cep.getBairro() +"\n"
//                            + "- Cidade: " +cep.getCidade() +"\n" +"- Cep.: " +cep.getCep());

                    String endereco = "";

                    for(String item: cep.keySet()){
                        endereco+= "- " +item +": " +cep.get(item)  +"\n";

                    }

                    tvResultado.setText(endereco);

                    tvResultado.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);
                }else{

                    int codigoErro = response.code();

                        if(codigoErro == 404) {
                            Toast.makeText(MainActivity.this, "Código do erro: " + codigoErro
                                    +"." +" Site Via Cep não foi encontrado!",
                                    Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                }
            }

            @Override
            public void onFailure(Call<HashMap<String, String>> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Não foi possível obter o cep!", Toast.LENGTH_SHORT).show();

            }
        });


    }

    public void fazerNovaConsulta(View view) {

        cep.setText("");
    }
}
