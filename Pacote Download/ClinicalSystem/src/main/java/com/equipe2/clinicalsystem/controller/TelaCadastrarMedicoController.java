/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equipe2.clinicalsystem.controller;

import com.equipe2.clinicalsystem.dao.CrudGenericoDao;
import com.equipe2.clinicalsystem.model.Medico;
import com.equipe2.clinicalsystem.util.Alerta;
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
public class TelaCadastrarMedicoController implements Initializable, Icadastro {

    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtEspecialidade;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtTelefone;
    @FXML
    private TableView<Medico> tableView;

    private CrudGenericoDao<Medico> dao = new CrudGenericoDao();
    private ObservableList<Medico> observableList = FXCollections.observableArrayList();
    private List<Medico> lista;
    private Medico objetoSelecionado = new Medico();

    @FXML
    private TextField txtPesquisa;
    @FXML
    private TextField txtCrm;

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
        //---> classe modelo que está sendo representada
        Medico objeto = new Medico();
        objeto.setNome(txtNome.getText());
        objeto.setCrm(txtCrm.getText());
        objeto.setEspecialidade(txtEspecialidade.getText());
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
            Medico objeto = new Medico();

            //---> verifica se o objeto selecionado possuir um codigo se posuir so altera caso contrario salta
            if (objetoSelecionado != null) {
                objeto.setCodigo(objetoSelecionado.getCodigo());
            }
            objeto.setNome(txtNome.getText());
            objeto.setCrm(txtCrm.getText());
            objeto.setEspecialidade(txtEspecialidade.getText());
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

    @Override
    public void criarColunasTabelas() {
        TableColumn<Medico, Integer> colunaCodigo = new TableColumn<>("Código");

        TableColumn<Medico, String> colunaNome = new TableColumn<>("Nome");
        TableColumn<Medico, String> colunaCrm = new TableColumn<>("CRM");
        TableColumn<Medico, String> colunaEspecialidade = new TableColumn<>("Especialidade");
        TableColumn<Medico, String> colunaEmail = new TableColumn<>("Email");
        TableColumn<Medico, String> colunaTelefone = new TableColumn<>("Telefone");

        tableView.getColumns().addAll(colunaCodigo, colunaNome, colunaCrm, colunaEspecialidade, colunaEmail, colunaTelefone);

        colunaCodigo.setCellValueFactory(new PropertyValueFactory("codigo"));
        colunaNome.setCellValueFactory(new PropertyValueFactory("nome"));
        colunaCrm.setCellValueFactory(new PropertyValueFactory("crm"));
        colunaEspecialidade.setCellValueFactory(new PropertyValueFactory("especialidade"));
        colunaEmail.setCellValueFactory(new PropertyValueFactory("email"));
        colunaTelefone.setCellValueFactory(new PropertyValueFactory("telefone"));

    }

    @Override
    public void atualizarTabela() {
        observableList.clear();
        //tenho que colocar aqui a classe que vai ser ultilizada
        lista = dao.consultar(txtPesquisa.getText(), "Medico");

        for (Medico t : lista) {
            observableList.add(t);

        }

        tableView.getItems().setAll(observableList);
        tableView.getSelectionModel().selectFirst();
    }

    @Override
    public void setCamposFormulario() {
        //---> quando nao for vazio vai preencher o objeto selecionado
        if (!tableView.getItems().isEmpty()) {
            objetoSelecionado = tableView.getItems().get(tableView.getSelectionModel().getSelectedIndex());
            //--> aqui vamos preencher os campos que queremos que seja preenchido ao selecionar o registro na tabela
            txtCodigo.setText(Integer.toString(objetoSelecionado.getCodigo()));
            txtNome.setText(objetoSelecionado.getNome());
            txtCrm.setText(objetoSelecionado.getCrm());
            txtEspecialidade.setText(objetoSelecionado.getEspecialidade());
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

        txtCrm.clear();
        txtEspecialidade.clear();
        txtEmail.clear();
        txtTelefone.clear();
    }

    @FXML
    private void filtrarRegistro(KeyEvent event) {
        atualizarTabela();
    }

}
