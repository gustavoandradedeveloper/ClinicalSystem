package com.equipe2.clinicalsystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author cce
 */

@Entity
@Table(name = "atendente")
public class Atendente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_atend", nullable = false)
    private int codigo;
    
    @Column(name = "nome_atend", length = 100, nullable = false)
    private String nome;
   
    // unique = true serve para informar o banco que não pode haver dois registro com a mesma informacao  no bd
    @Column(name = "Cpf_atend", length = 100, nullable = false)
    private String cpf;

    @Column(name = "email_atend", length = 100, nullable = false)
    private String email;

    @Column(name = "telefone_atend", length = 100, nullable = false)
    private String telefone;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return  nome ;
    }
    
    
    public String toString1() {
        return  cpf ;
    }
    

}
