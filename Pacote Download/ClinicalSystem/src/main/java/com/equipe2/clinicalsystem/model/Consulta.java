package com.equipe2.clinicalsystem.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "consulta")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_consulta", nullable = false)
    private int codigo;

    @Column(name = "data_atendimento", nullable = false)
    private LocalDate dataAtendimento;

    @Column(name = "valor_consulta", length = 100, nullable = false)
    private String valor;

    @Column(name = "turno_consulta", length = 100, nullable = false)
    private String turno;

    @Column(name = "dtConsulta_consulta", nullable = false)
    private LocalDate dtConsulta;

    @ManyToOne
    @JoinColumn(name = "cod_med", nullable = false)
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "cod_atend", nullable = false)
    private Atendente atendente;

    @ManyToOne
    @JoinColumn(name = "cod_pac", nullable = false)
    private Paciente paciente;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Atendente getAtendente() {
        return atendente;
    }

    public void setAtendente(Atendente atendente) {
        this.atendente = atendente;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public LocalDate getDataAtendimento() {
        return dataAtendimento;
    }

    public void setDataAtendimento(LocalDate dataAtendimento) {
        this.dataAtendimento = dataAtendimento;
    }

    public LocalDate getDtConsulta() {
        return dtConsulta;
    }

    public void setDtConsulta(LocalDate dtConsulta) {
        this.dtConsulta = dtConsulta;
    }

    
    @Override
    public String toString() {
        return "Consulta{" + "paciente=" + paciente.getNome() + '}';
    }

}
