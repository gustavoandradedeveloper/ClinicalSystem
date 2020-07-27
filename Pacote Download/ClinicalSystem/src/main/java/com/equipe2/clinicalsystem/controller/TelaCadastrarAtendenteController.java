/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equipe2.clinicalsystem.controller;

import com.equipe2.clinicalsystem.dao.CrudGenericoDao;
import com.equipe2.clinicalsystem.model.Atendente;
import com.equipe2.clinicalsystem.util.Alerta;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author cce
 */
public class TelaCadastrarAtendenteController implements Initializable, Icadastro {

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
    private TableView<Atendente> tableView;
    @FXML
    private TextField txtPesquisa;

    private CrudGenericoDao<Atendente> dao = new CrudGenericoDao();
    private ObservableList<Atendente> observableList = FXCollections.observableArrayList();
    private List<Atendente> lista;
    private Atendente objetoSelecionado = new Atendente();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        criarColunasTabelas();
        atualizarTabela();
        setCamposFormulario();
    }

    @FXML
    private void novoRegistro(ActionEvent event) {
        limparCamposFormulario();
    }

    @FXML
    private void salvarRegistro(ActionEvent event) {
        Atendente objeto = new Atendente();
        objeto.setNome(txtNome.getText());
        objeto.setCpf(txtCpf.getText());
        objeto.setEmail(txtEmail.getText());
        objeto.setTelefone(txtTelefone.getText());

        if (dao.salvar(objeto)) {
            Alerta.msgInformacao("Registro salvo com sucesso");
        } else {
            Alerta.msgInformacao("Ocorreu um erro ao salvar o registro, verifique se o banco de dados está ativo");
        }
        limparCamposFormulario();
        atualizarTabela();
    }

    @FXML
    private void alterarRegistro(ActionEvent event) {
        try {
            Atendente objeto = new Atendente();

            if (objetoSelecionado != null) {
                objeto.setCodigo(objetoSelecionado.getCodigo());
            }
            objeto.setNome(txtNome.getText());
            objeto.setCpf(txtCpf.getText());
            objeto.setEmail(txtEmail.getText());
            objeto.setTelefone(txtTelefone.getText());
            dao.salvar(objeto);
            atualizarTabela();
            Alerta.msgInformacao("Registro Alterado com sucesso");
            limparCamposFormulario();
        } catch (Exception e) {
            Alerta.msgInformacao("Ocorreu um erro ao alterar o registro, verifique se o banco de dados está ativo");

        }

    }

    @FXML
    private void excluirRegistro(ActionEvent event) {
        try {
            if (Alerta.msgConfirmaExclusao(txtNome.getText())) {
                dao.excluir(objetoSelecionado);
                limparCamposFormulario();
                atualizarTabela();
                Alerta.msgInformacao("Registro Excluido com sucesso");
            }
        } catch (Exception e) {
            Alerta.msgInformacao("Ocorreu um erro ao excluir o registro, verifique se o banco de dados está ativo");

        }

    }

    @FXML
    private void clicarTabela(MouseEvent event) {
        setCamposFormulario();
    }

    @FXML
    private void moverTabela(KeyEvent event) {
        setCamposFormulario();
    }

    @FXML
    private void filtrarRegistro(KeyEvent event) {
        atualizarTabela();
    }

    @Override
    public void criarColunasTabelas() {
        TableColumn<Atendente, Integer> colunaCodigo = new TableColumn<>("Código");
        TableColumn<Atendente, String> colunaNome = new TableColumn<>("Nome");
        TableColumn<Atendente, String> colunaCpf = new TableColumn<>("CPF");
        TableColumn<Atendente, String> colunaEmail = new TableColumn<>("Email");
        TableColumn<Atendente, String> colunaTelefone = new TableColumn<>("Telefone");

        tableView.getColumns().addAll(colunaCodigo, colunaNome, colunaCpf, colunaEmail, colunaTelefone);

        colunaCodigo.setCellValueFactory(new PropertyValueFactory("codigo"));
        colunaNome.setCellValueFactory(new PropertyValueFactory("nome"));
        colunaCpf.setCellValueFactory(new PropertyValueFactory("cpf"));
        colunaEmail.setCellValueFactory(new PropertyValueFactory("email"));
        colunaTelefone.setCellValueFactory(new PropertyValueFactory("telefone"));
    }

    @Override
    public void atualizarTabela() {
        observableList.clear();
        //tenho que colocar aqui a classe que vai ser ultilizada
        lista = dao.consultar(txtPesquisa.getText(), "Atendente");

        for (Atendente a : lista) {
            observableList.add(a);

        }

        tableView.getItems().setAll(observableList);
        tableView.getSelectionModel().selectFirst();
    }

    @Override
    public void setCamposFormulario() {
        if (!tableView.getItems().isEmpty()) {
            objetoSelecionado = tableView.getItems().get(tableView.getSelectionModel().getSelectedIndex());
            //--> aqui vamos preencher os campos que queremos que seja preenchido ao selecionar o registro na tabela
            txtCodigo.setText(Integer.toString(objetoSelecionado.getCodigo()));
            txtNome.setText(objetoSelecionado.getNome());
            txtCpf.setText(objetoSelecionado.getCpf());
            txtEmail.setText(objetoSelecionado.getEmail());
            txtTelefone.setText(objetoSelecionado.getTelefone());
        }
    }

    @Override
    public void limparCamposFormulario() {
        objetoSelecionado = null;

        txtCodigo.clear();
        txtNome.clear();
        //---> repozisionar o curso do mouse no campo nome
        txtNome.requestFocus();
        txtCpf.clear();
        txtEmail.clear();
        txtTelefone.clear();
    }

}
