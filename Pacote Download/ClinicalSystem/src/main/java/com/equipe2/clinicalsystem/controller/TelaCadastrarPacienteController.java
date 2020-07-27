/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equipe2.clinicalsystem.controller;

import com.equipe2.clinicalsystem.dao.CrudGenericoDao;
import com.equipe2.clinicalsystem.model.Paciente;
import com.equipe2.clinicalsystem.util.Alerta;
import com.equipe2.clinicalsystem.util.UF;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.LocalDate;
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
public class TelaCadastrarPacienteController implements Initializable, Icadastro {

    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtNome;
    private TextField txtRg;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtNumeroSus;
    @FXML
    private TextField txtTelefone;
    @FXML
    private TableView<Paciente> tableView;
    @FXML
    private TextField txtPesquisa;
    @FXML
    private JFXTextField txtCpf;
    @FXML
    private JFXTextField txtCidade;
    @FXML
    private JFXTextField txtBairro;
    @FXML
    private JFXDatePicker dpNascimento;
    @FXML
    private JFXComboBox<String> cbEstado;
    @FXML
    private JFXTextField txtRua;

    //estou passsando o tipo da classe exemplo<Cliente>/<medico>/<consulta> conforme está na minha classe crudGenericoDao
    private CrudGenericoDao<Paciente> dao = new CrudGenericoDao();
    private ObservableList<Paciente> observableList = FXCollections.observableArrayList();
    private List<Paciente> lista;
    private Paciente objetoSelecionado = new Paciente();
    private TelaPrincipalController objPrincipalController = new TelaPrincipalController();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbEstado.setItems(UF.gerarUF());
        criarColunasTabelas();

        atualizarTabela();

        //---> Ao iniciar o programa desejo que o primeiro registro da tabela ja fique preenchido no formulario
        setCamposFormulario();

    }

    @FXML
    private void novoRegistro(ActionEvent event) {
        limparCamposFormulario();
    }

    @FXML
    private void salvarRegistro(ActionEvent event) {

        Paciente objeto = new Paciente();
        objeto.setNome(txtNome.getText());
        objeto.setCpf(txtCpf.getText());
        objeto.setNumSus(txtNumeroSus.getText());
        objeto.setEstado(cbEstado.getValue());
        objeto.setCidade(txtCidade.getText());
        objeto.setBairro(txtBairro.getText());
        objeto.setRua(txtRua.getText());
        objeto.setTelefone(txtTelefone.getText());
        objeto.setEmail(txtEmail.getText());
        LocalDate dataNascimento = dpNascimento.getValue();
        objeto.setDataNascimento(dataNascimento);

        if (dao.salvar(objeto)) {
            Alerta.msgInformacao("Registro salvo com sucesso");
        } else {
            Alerta.msgInformacao("Ocorreu um erro ao salvar o registro, verifique se o banco de dados está ativo");
        }
        atualizarTabela();
        limparCamposFormulario();
        objPrincipalController.atualizarTabela();
    }

    @FXML
    private void alterarRegistro(ActionEvent event) {

        try {
            Paciente objeto = new Paciente();

            if (objetoSelecionado != null) {
                objeto.setCodigo(objetoSelecionado.getCodigo());
            }
            objeto.setNome(txtNome.getText());
            objeto.setCpf(txtCpf.getText());
            objeto.setNumSus(txtNumeroSus.getText());
            objeto.setEstado(cbEstado.getValue());
            objeto.setCidade(txtCidade.getText());
            objeto.setBairro(txtBairro.getText());
            objeto.setRua(txtRua.getText());
            objeto.setTelefone(txtTelefone.getText());
            objeto.setEmail(txtEmail.getText());
            LocalDate dataNascimento = dpNascimento.getValue();
            objeto.setDataNascimento(dataNascimento);

            dao.salvar(objeto);
            atualizarTabela();
            Alerta.msgInformacao("Registro Alterado com sucesso");
        } catch (Exception e) {
            Alerta.msgInformacao("Ocorreu um erro ao alterar o registro, verifique se o banco de dados está ativo");
        }

        limparCamposFormulario();
    }

    @FXML
    private void excluirRegistro(ActionEvent event) {
        try {
            if (Alerta.msgConfirmaExclusao(txtNome.getText())) {
                dao.excluir(objetoSelecionado);
                limparCamposFormulario();
                atualizarTabela();
                Alerta.msgInformacao("Registro Excluído com sucesso");
            }
        } catch (Exception e) {
            Alerta.msgInformacao("Ocorreu um erro ao excluir o registro, verifique se o banco de dados está ativo");

        }

    }

    @Override
    public void criarColunasTabelas() {
        TableColumn<Paciente, Integer> colunaCodigo = new TableColumn<>("Código");
        //---> DETERMINAR O VALOR DA LARGURA DA COLUNA APENAS DESSE CAMPO CODIGO
        //colunaCodigo.setMinWidth(40);
        //colunaCodigo.setMaxWidth(40);

        TableColumn<Paciente, String> colunaNome = new TableColumn<>("Nome");
        TableColumn<Paciente, String> colunaCpf = new TableColumn<>("CPF");
        TableColumn<Paciente, String> colunaDataNasc = new TableColumn<>("Data Nascimento");
        TableColumn<Paciente, String> colunaNumSus = new TableColumn<>("Num SUS");
        TableColumn<Paciente, String> colunaEstado = new TableColumn<>("Estado");
        TableColumn<Paciente, String> colunaCidade = new TableColumn<>("Cidade");
        TableColumn<Paciente, String> colunaRua = new TableColumn<>("Rua");
        TableColumn<Paciente, String> colunaBairro = new TableColumn<>("Bairro");
        TableColumn<Paciente, String> colunaEmail = new TableColumn<>("Email");
        TableColumn<Paciente, String> colunaTelefone = new TableColumn<>("Telefone");

        //---> DETERMINAR UM VALOR DE LARGURA IGUAL PARA TODAS AS COLUNAS DA TABELA
        //tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.getColumns().addAll(colunaCodigo, colunaNome, colunaCpf, colunaNumSus, colunaDataNasc, colunaEstado, colunaCidade, colunaRua, colunaBairro, colunaEmail, colunaTelefone);

        colunaCodigo.setCellValueFactory(new PropertyValueFactory("codigo"));
        colunaNome.setCellValueFactory(new PropertyValueFactory("nome"));
        colunaCpf.setCellValueFactory(new PropertyValueFactory("cpf"));
        colunaDataNasc.setCellValueFactory(new PropertyValueFactory("dataNascimento"));
        colunaNumSus.setCellValueFactory(new PropertyValueFactory("numSus"));
        colunaEstado.setCellValueFactory(new PropertyValueFactory("estado"));
        colunaCidade.setCellValueFactory(new PropertyValueFactory("cidade"));
        colunaRua.setCellValueFactory(new PropertyValueFactory("rua"));
        colunaBairro.setCellValueFactory(new PropertyValueFactory("bairro"));
        colunaEmail.setCellValueFactory(new PropertyValueFactory("email"));
        colunaTelefone.setCellValueFactory(new PropertyValueFactory("telefone"));

    }

    @Override
    public void atualizarTabela() {
        observableList.clear();
        //tenho que colocar aqui a classe que vai ser ultilizada
        lista = dao.consultar(txtPesquisa.getText(), "Paciente");

        for (Paciente t : lista) {
            observableList.add(t);

        }

        tableView.getItems().setAll(observableList);
        tableView.getSelectionModel().selectFirst();

    }

    //---> Esse método vai ter a acão de quando clicarmos/selecionarmos um registro da tabela esse metodo vai atualizar o formulario acima para poder editar o usuarios 
    @Override
    public void setCamposFormulario() {
        if (!tableView.getItems().isEmpty()) {
            objetoSelecionado = tableView.getItems().get(tableView.getSelectionModel().getSelectedIndex());
        }
        //--> aqui vamos preencher os campos que queremos que seja preenchido ao selecionar o registro na tabela
        txtCodigo.setText(Integer.toString(objetoSelecionado.getCodigo()));
        txtNome.setText(objetoSelecionado.getNome());
        txtCpf.setText(objetoSelecionado.getCpf());
        txtNumeroSus.setText(objetoSelecionado.getNumSus());
        dpNascimento.setValue(objetoSelecionado.getDataNascimento());
        cbEstado.setValue(objetoSelecionado.getEstado());
        txtCidade.setText(objetoSelecionado.getCidade());
        txtRua.setText(objetoSelecionado.getRua());
        txtBairro.setText(objetoSelecionado.getBairro());
        txtEmail.setText(objetoSelecionado.getEmail());
        txtTelefone.setText(objetoSelecionado.getTelefone());

    }

    @Override
    public void limparCamposFormulario() {
        //-----> esse objetoSelecionado guarda a linha da tabela selecionada ou seja pega toda as informacoes e guarda aqui dentro
        //---->pq como codigo é a chave primaria se ele for com algum valor o dao so vai alterar o objeto, agora se ele for vazio, o dao vai entender que é pra cria um novo registro 
        //---> se eu estou incluindo algo , o objeto não pode ter nenhuma informacao
        objetoSelecionado = null;

        txtCodigo.clear();
        txtNome.clear();
        //---> repozisionar o curso do mouse no campo nome
        txtNome.requestFocus();

        txtCpf.clear();
        txtNumeroSus.clear();
        txtCidade.clear();
        txtRua.clear();
        txtEmail.clear();
        txtBairro.clear();
        txtTelefone.clear();

    }

    //------> Ao clica no registro vai preenchendo o formulario acima
    @FXML
    private void clicarTabela(MouseEvent event) {
        setCamposFormulario();
    }

    //--->movendo os registro da tabela com a seta do teclado
    @FXML
    private void moverTabela(KeyEvent event) {
        setCamposFormulario();
    }

    @FXML
    private void filtrarRegistro(KeyEvent event) {
        atualizarTabela();
    }

}
