package com.example.consultacep.model;

import com.google.gson.annotations.SerializedName;


public class Cep {

    //@SerializedName("logradouro")
    private String logradouro;
    //@SerializedName("complemento")
    private String complemento;
    //@SerializedName("bairro")
    private String bairro;
    //@SerializedName("localidade")
    private String cidade;
    //@SerializedName("uf")
    private String uF;
    //@SerializedName("cep")
    private String cep;

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getuF() {
        return uF;
    }

    public void setuF(String uF) {
        this.uF = uF;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
