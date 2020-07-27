package com.equipe2.clinicalsystem.controller;

import com.equipe2.clinicalsystem.dao.ComboBoxGenericoDao;
import com.equipe2.clinicalsystem.dao.CrudGenericoDao;
import com.equipe2.clinicalsystem.model.Atendente;
import com.equipe2.clinicalsystem.model.Consulta;
import com.equipe2.clinicalsystem.model.Medico;
import com.equipe2.clinicalsystem.model.Paciente;
import com.equipe2.clinicalsystem.util.Alerta;
import com.equipe2.clinicalsystem.util.Turno;
import com.jfoenix.controls.JFXButton;
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

public class TelaAgendarConsultaController implements Initializable, Icadastro {

    @FXML
    private TextField txtPesquisa;
    @FXML
    private TableView<Paciente> tableViewListandoPaciente;
    @FXML
    private JFXTextField txtCodigo;
    @FXML
    private JFXComboBox<String> cbTurno;
    @FXML
    private JFXTextField txtValor;

    @FXML
    private JFXButton btnCancelarAgendamento;
    @FXML
    private JFXButton btnFinalizarAgendamento;
    @FXML
    private JFXTextField txtNomePac;
    @FXML
    private JFXComboBox<Medico> cbNomeMedico;
    private JFXComboBox<Paciente> cbNomePaciente;
    @FXML
    private JFXComboBox<Atendente> cbNomeAtendente;
    private JFXDatePicker dpDataVolta;

    private CrudGenericoDao<Paciente> daoPaciente = new CrudGenericoDao();
    private ObservableList<Paciente> observableLista = FXCollections.observableArrayList();
    private ObservableList<Consulta> observableList = FXCollections.observableArrayList();
    private List<Paciente> listaPacientes;
    private List<Consulta> lista;
    private Consulta objetoSelecionado = new Consulta();
    private Paciente objetoSelecionadopac = new Paciente();
    private CrudGenericoDao<Consulta> dao = new CrudGenericoDao<>();
    private ComboBoxGenericoDao<Paciente> comboBoxPaciente = new ComboBoxGenericoDao();
    private ComboBoxGenericoDao<Medico> comboBoxMedico = new ComboBoxGenericoDao();
    private ComboBoxGenericoDao<Atendente> comboBoxAtendente = new ComboBoxGenericoDao();
    @FXML
    private JFXDatePicker dpDataDaConsulta;
    @FXML
    private JFXDatePicker dpAtendimentoConsulta;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cbNomeAtendente.setItems(comboBoxAtendente.comboBox("Atendente"));
        cbNomeMedico.setItems(comboBoxMedico.comboBox("Medico"));
        cbTurno.setItems(Turno.gerarTurno());
        criarColunasTabelas();

        atualizarTabela();

        //---> Ao iniciar o programa desejo que o primeiro registro da tabela ja fique preenchido no formulario
        setCamposFormulario();

    }

    @FXML
    private void filtrarRegistro(KeyEvent event) {
        atualizarTabela();
    }

    @FXML
    private void moverTabela(KeyEvent event) {
        setCamposFormulario();
    }

    @FXML
    private void cancelarAgendamento(ActionEvent event) {
        limparCamposFormulario();
    }

    @FXML
    private void finalizarAgendamento(ActionEvent event) {

        Consulta consulta = new Consulta();
        consulta.setTurno(cbTurno.getValue());
        consulta.setValor(txtValor.getText());

        LocalDate dataAtendimento = dpAtendimentoConsulta.getValue();
        consulta.setDataAtendimento(dataAtendimento);

        LocalDate dataConsulta = dpDataDaConsulta.getValue();
        consulta.setDtConsulta(dataConsulta);

        consulta.setPaciente(objetoSelecionadopac);
        consulta.setAtendente(cbNomeAtendente.getSelectionModel().getSelectedItem());
        consulta.setMedico(cbNomeMedico.getSelectionModel().getSelectedItem());

        if (dao.salvar(consulta)) {
            Alerta.msgInformacao("Registro salvo com sucesso");
        } else {
            Alerta.msgInformacao("Ocorreu um erro ao salvar o registro");
        }
        atualizarTabela();
        limparCamposFormulario();

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
        tableViewListandoPaciente.getColumns().addAll(colunaCodigo, colunaNome, colunaCpf, colunaNumSus, colunaDataNasc, colunaEstado, colunaCidade, colunaRua, colunaBairro, colunaEmail, colunaTelefone);

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

        observableLista.clear();
        //tenho que colocar aqui a classe que vai ser ultilizada
        listaPacientes = daoPaciente.consultar(txtPesquisa.getText(), "Paciente");

        for (Paciente t : listaPacientes) {
            observableLista.add(t);

        }

        tableViewListandoPaciente.getItems().setAll(observableLista);
        tableViewListandoPaciente.getSelectionModel().selectFirst();

    }

    @Override
    public void setCamposFormulario() {
        if (!tableViewListandoPaciente.getItems().isEmpty()) {
            objetoSelecionadopac = tableViewListandoPaciente.getItems().get(tableViewListandoPaciente.getSelectionModel().getSelectedIndex());
            //--> aqui vamos preencher os campos que queremos que seja preenchido ao selecionar o registro na tabela
            txtCodigo.setText(Integer.toString(objetoSelecionadopac.getCodigo()));
            txtNomePac.setText(objetoSelecionadopac.toString());

        }

    }

    @Override
    public void limparCamposFormulario() {
        objetoSelecionado = null;

        txtCodigo.clear();
        txtNomePac.clear();
        //---> repozisionar o curso do mouse no campo nome
        txtNomePac.requestFocus();

    }

    @FXML
    private void clicarTabela(MouseEvent event) {
        setCamposFormulario();
    }


}
