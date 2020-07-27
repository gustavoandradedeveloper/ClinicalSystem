/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equipe2.clinicalsystem.controller;

import com.equipe2.clinicalsystem.TelaLogin;
import com.equipe2.clinicalsystem.TelaCadastroLogin;
import com.equipe2.clinicalsystem.TelaCadastroLoginAtendente;
import com.equipe2.clinicalsystem.dao.CrudGenericoDao;
import com.equipe2.clinicalsystem.model.Atendente;
import com.equipe2.clinicalsystem.util.Alerta;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author cce
 */
public class TelaCadastroLoginAtendenteController implements Initializable, Icadastro {

    @FXML
    private JFXTextField txtCodigo;
    @FXML
    private JFXTextField txtNome;
    @FXML
    private JFXTextField txtCpf;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXTextField txtTelefone;
    @FXML
    private JFXButton btnFinalizarCadastro;
    @FXML
    private JFXButton btnCancelarCadastro;

    private CrudGenericoDao<Atendente> dao = new CrudGenericoDao();
    private ObservableList<Atendente> observableList = FXCollections.observableArrayList();
    private List<Atendente> lista;
    private Atendente objetoSelecionado = new Atendente();
    TelaCadastroLogin objTelaCadastroLogin = new TelaCadastroLogin();
    TelaLogin objLogin = new TelaLogin();
    
    @FXML
    private Button btnVoltar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void finalizarCadastro(ActionEvent event) throws Exception {
        Atendente objeto = new Atendente();
        objeto.setNome(txtNome.getText());
        objeto.setCpf(txtCpf.getText());
        objeto.setEmail(txtEmail.getText());
        objeto.setTelefone(txtTelefone.getText());

        if (dao.salvar(objeto)) {
            Alerta.msgInformacao("cadastro criado com sucesso");
            objTelaCadastroLogin.start(new Stage());
            TelaCadastroLoginAtendente.getStage().close();
        } else {
            Alerta.msgInformacao("Ocorreu um erro ao salvar o registro");
        }

    }

    @FXML
    private void cancelarCadastro(ActionEvent event) {
        limparCamposFormulario();
    }

    @Override
    public void criarColunasTabelas() {
    }

    @Override
    public void atualizarTabela() {
    }

    @Override
    public void setCamposFormulario() {
    }

    @Override
    public void limparCamposFormulario() {
        txtCodigo.clear();
        txtNome.clear();
        //---> repozisionar o curso do mouse no campo nome
        txtNome.requestFocus();
        txtCpf.clear();
        txtEmail.clear();
        txtTelefone.clear();
    }

    @FXML
    private void voltaTelaLogin(ActionEvent event) throws Exception {
        objLogin.start(new Stage());
        TelaCadastroLoginAtendente.getStage().close();
    }

}
