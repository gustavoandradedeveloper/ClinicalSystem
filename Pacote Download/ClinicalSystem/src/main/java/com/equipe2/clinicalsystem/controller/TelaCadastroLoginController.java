package com.equipe2.clinicalsystem.controller;

import com.equipe2.clinicalsystem.TelaCadastroLogin;
import com.equipe2.clinicalsystem.TelaCadastroLoginAtendente;
import com.equipe2.clinicalsystem.TelaLogin;
import com.equipe2.clinicalsystem.dao.ComboBoxGenericoDao;
import com.equipe2.clinicalsystem.dao.CrudGenericoDao;
import com.equipe2.clinicalsystem.model.Atendente;
import com.equipe2.clinicalsystem.model.Login;
import com.equipe2.clinicalsystem.util.Alerta;
import com.equipe2.clinicalsystem.util.Criptografia;
import com.equipe2.clinicalsystem.util.ValidaLogin;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class TelaCadastroLoginController implements Initializable {

    private TableView<Atendente> tableView;
    @FXML
    private JFXPasswordField txtSenha;
    private JFXTextField txtNomeAtendente;
    private JFXTextField txtPesquisarPorNome;
    @FXML
    private JFXButton btncadastrarLogin;
    @FXML
    private JFXPasswordField txtSenhaConfirmada;
    @FXML
    private Button btnVoltarTelaLogin;
    @FXML
    private JFXComboBox<Atendente> cbAtendente;

    private ComboBoxGenericoDao<Atendente> comboBoxAtendente = new ComboBoxGenericoDao();
    private TelaCadastroLoginAtendente objtelaCadastroLoginAtendente = new TelaCadastroLoginAtendente();
    private TelaLogin objTelaLogin = new TelaLogin();
    private CrudGenericoDao<Login> dao = new CrudGenericoDao();

    private ValidaLogin objValidaLogin = new ValidaLogin();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbAtendente.setItems(comboBoxAtendente.comboBox("Atendente"));
    }

    @FXML
    private void cadastrarLogin(ActionEvent event) {

        boolean respValidacaoSenha = objValidaLogin.ValidarLogin(txtSenha.getText(), txtSenhaConfirmada.getText());
        try {

            if (respValidacaoSenha == true) {
                String senha = Criptografia.criptografia(txtSenha.getText());
                Login login = new Login();
                login.setSenha(senha);
                login.setAtendente(cbAtendente.getSelectionModel().getSelectedItem());

                if (dao.salvar(login)) {
                    Alerta.msgInformacao("login criado com sucesso");
                    TelaCadastroLogin.getStage().close();
                    objTelaLogin.start(new Stage());

                } else {
                    Alerta.msgInformacao("Ocorreu um erro ao cadastrar o login, verifique se todos dados foram inserido");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Verifique se sua senha foi informada corretamente", "ERRO", 0);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "verifique se o banco de dados está ativo", "ERRO", 0);

        }

    }

    @FXML
    private void abrirCadastroAtendenteLogin(ActionEvent event) throws Exception {
        try {
            objtelaCadastroLoginAtendente.start(new Stage());
            TelaCadastroLogin.getStage().close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "verifique se o banco de dados está ativo", "ERRO", 0);

        }

    }

    @FXML
    private void voltarTelaLogin(ActionEvent event) throws Exception {
        try {
            objTelaLogin.start(new Stage());
            TelaCadastroLogin.getStage().close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "verifique se o banco de dados está ativo", "ERRO", 0);

        }

    }

}
