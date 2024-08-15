/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto_10.dto;

/**
 *
 * @author gusta
 */
public class CompradorDTO {

    private String nome_com, logradouro_com, bairro_com, cidade_com;
    private String estado_com, cep_com, cpf_com, rg_com, celular_com;
    private int id_com, numero_com;

    public int getNumero_com() {
        return numero_com;
    }

    public void setNumero_com(int numero_com) {
        this.numero_com = numero_com;
    }

   
    public int getId_com() {
        return id_com;
    }

    public void setId_com(int id_com) {
        this.id_com = id_com;
    }

    public String getNome_com() {
        return nome_com;
    }

    public void setNome_com(String nome_com) {
        this.nome_com = nome_com;
    }

    public String getLogradouro_com() {
        return logradouro_com;
    }

    public void setLogradouro_com(String logradouro_com) {
        this.logradouro_com = logradouro_com;
    }

    public String getBairro_com() {
        return bairro_com;
    }

    public void setBairro_com(String bairro_com) {
        this.bairro_com = bairro_com;
    }

    public String getCidade_com() {
        return cidade_com;
    }

    public void setCidade_com(String cidade_com) {
        this.cidade_com = cidade_com;
    }

    public String getEstado_com() {
        return estado_com;
    }

    public void setEstado_com(String estado_com) {
        this.estado_com = estado_com;
    }

    public String getCep_com() {
        return cep_com;
    }

    public void setCep_com(String cep_com) {
        this.cep_com = cep_com;
    }

    public String getCpf_com() {
        return cpf_com;
    }

    public void setCpf_com(String cpf_com) {
        this.cpf_com = cpf_com;
    }

    public String getRg_com() {
        return rg_com;
    }

    public void setRg_com(String rg_com) {
        this.rg_com = rg_com;
    }

    public String getCelular_com() {
        return celular_com;
    }

    public void setCelular_com(String celular_com) {
        this.celular_com = celular_com;
    }

}
