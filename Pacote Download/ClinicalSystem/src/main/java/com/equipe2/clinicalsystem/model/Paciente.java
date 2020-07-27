/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equipe2.clinicalsystem.model;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "paciente")
public class Paciente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_pac", nullable = false)
    private int codigo;

    @Column(name = "nome_pac", length = 100, nullable = false)
    private String nome;

    @Column(name = "cpf_pac", length = 100, nullable = false)
    private String cpf;
    
    @Column(name = "dtNasc_pac", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "numSus_pac", length = 100, nullable = false)
    private String numSus;

    @Column(name = "estado_pac", length = 2, nullable = false)
    private String estado;

    @Column(name = "cidade_pac", length = 100, nullable = false)
    private String cidade;

    @Column(name = "bairro_pac", length = 100, nullable = false)
    private String bairro;

    @Column(name = "rua_pac", length = 100, nullable = false)
    private String rua;

    @Column(name = "telefone_pac", length = 100, nullable = false)
    private String telefone;

    @Column(name = "email_pac", length = 100, nullable = false)
    private String email;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNumSus() {
        return numSus;
    }

    public void setNumSus(String numSus) {
        this.numSus = numSus;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

     @Override
    public String toString() {
        return  nome ;
    }
    
}
