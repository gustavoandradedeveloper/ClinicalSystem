/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equipe2.clinicalsystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author cce
 */
@Entity
@Table(name = "login")
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_login", nullable = false)
    private int codigo;

    @Column(name = "senha_login", length = 100, nullable = false)
    private String senha;

    @Column(name = "permissaoAcesso_login", length = 100, nullable = true)
    private String permissaoAcesso;

    @OneToOne
    @JoinColumn(name = "cod_atend", nullable = false)
    private Atendente atendente;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPermissaoAcesso() {
        return permissaoAcesso;
    }

    public void setPermissaoAcesso(String permissaoAcesso) {
        this.permissaoAcesso = permissaoAcesso;
    }

    public Atendente getAtendente() {
        return atendente;
    }

    public void setAtendente(Atendente atendente) {
        this.atendente = atendente;
    }

}
