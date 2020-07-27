/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equipe2.clinicalsystem.controller;

import com.equipe2.clinicalsystem.TelaLogin;
import com.equipe2.clinicalsystem.TelaCadastroLogin;
import com.equipe2.clinicalsystem.TelaCadastroLoginAtendente;
import com.equipe2.clinicalsystem.TelaPrincipal;
import com.equipe2.clinicalsystem.dao.CrudGenericoDao;
import com.equipe2.clinicalsystem.model.Atendente;
import com.equipe2.clinicalsystem.util.Criptografia;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author cce
 */
public class TelaLoginController implements Initializable {

    @FXML
    private TextField txtCpf;
    @FXML
    private PasswordField txtSenha;
    @FXML
    private JFXButton btnLogin;
    @FXML
    private Button btnFechar;
    @FXML
    private JFXButton btnRealizarCadastro;

    CrudGenericoDao<Atendente> dao = new CrudGenericoDao<>();
    private Atendente objetoAt = new Atendente();
    TelaPrincipal objTelaPrincipal = new TelaPrincipal();

    TelaCadastroLoginAtendente objAT = new TelaCadastroLoginAtendente();
    TelaCadastroLogin objT = new TelaCadastroLogin();
    private List<Atendente> listaAtendente;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void btnEntra(ActionEvent event) throws IOException, Exception {
        try {
            String senhaHex = Criptografia.criptografia(txtSenha.getText());

            if (dao.verificarLogin("Atendente", "Login", txtCpf.getText(), senhaHex) == true) {
                objTelaPrincipal.start(new Stage());
                TelaLogin.getStage().close();
            } else {

                JOptionPane.showMessageDialog(null, "Verifique se seus dados estão corretos", "ERRO", 0);
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Verifique se o banco de dados está ativo", "ERRO", 0);
        }

    }

    @FXML
    private void fecharPrograma(ActionEvent event) {
        Platform.exit();
        System.exit(0);

    }

    @FXML
    private void realizarCadastroLogin(ActionEvent event) throws Exception {
        try {
            objT.start(new Stage());
            TelaLogin.getStage().close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Verifique se o banco de dados está ativo", "ERRO", 0);

        }

    }

}
