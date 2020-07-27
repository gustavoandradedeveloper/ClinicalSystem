package com.equipe2.clinicalsystem.controller;

import com.equipe2.clinicalsystem.dao.CrudGenericoDao;
import com.equipe2.clinicalsystem.model.Consulta;
import com.equipe2.clinicalsystem.model.Paciente;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

public class TelaPrincipalController implements Initializable, Icadastro {

    private Button btnConsulta;
    private Button btnCliente;
    private Button btnMedico;
    private Button btnHome;
    @FXML
    private Button btnClientes;
    @FXML
    private Button btnInicio;
    @FXML
    private Button btnConsultas;
    private Button btnAjuda;
    @FXML
    private AnchorPane anchorPaneInicio;
    @FXML
    private AnchorPane anchorPaneAjuda;
    @FXML
    private AnchorPane anchoPaneConsulta;
    @FXML
    private AnchorPane anchorPanePaciente;
    @FXML
    private TableView<Paciente> tableView;
    @FXML
    private MenuItem menuItemMedico;
    @FXML
    private MenuItem btnSair;
    @FXML
    private Button txtAddPaciente;
    @FXML
    private MenuItem menuItemPaciente;
    @FXML
    private TextField txtPesquisarPaciente;
    @FXML
    private JFXButton btnAtualizarTabelaPaciente;
    @FXML
    private MenuItem menuItemAtendente;
    @FXML
    private MenuItem menuItemAgendamento;
    @FXML
    private TableView<Consulta> tableViewConsulta;
    @FXML
    private Button btnAtualizarTabelaConsulta;
    @FXML
    private DatePicker dppesquisarConsultaPorData;

    //---> tudo relacionado a tabela paciente
    private CrudGenericoDao<Paciente> daoPac = new CrudGenericoDao();
    private ObservableList<Paciente> observableListPac = FXCollections.observableArrayList();
    private List<Paciente> listaPac;
    private Paciente objetoSelecionadoPac = new Paciente();

    //---> tudo relacionado a tabela Consulta
    private CrudGenericoDao<Consulta> daoConsulta = new CrudGenericoDao();
    private ObservableList<Consulta> observableListConsulta = FXCollections.observableArrayList();
    private List<Consulta> listaConsulta;
    private Consulta objetoSelecionadoConsulta = new Consulta();
    @FXML
    private MenuItem menuItemListrConsultas;

    public void initialize(URL url, ResourceBundle rb) {
        criarColunasTabelasConsulta();
        criarColunasTabelas();
        atualizarTabela();

    }

    public void start(Stage stage) throws Exception {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/telaLogin.fxml"));

            Scene scene = new Scene(root);
            scene.getStylesheets().add("/styles/Styles.css");
            stage.initStyle(StageStyle.DECORATED);
            stage.setMaximized(false);
            stage.setResizable(false);
            stage.setScene(scene);

            stage.show();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados verifique se o seu servidor está ativo", "ERRO", 0);
            Platform.exit();
            System.exit(0);
        }

    }

    @FXML
    private void exibirAnchorPane(ActionEvent event) {
        try {
            if (event.getSource() == btnInicio) {
                anchorPaneInicio.toFront();
                anchorPanePaciente.toBack();
                anchoPaneConsulta.toBack();
                anchorPaneAjuda.toBack();

            } else {
                if (event.getSource() == btnClientes) {
                    anchorPanePaciente.toFront();
                    anchoPaneConsulta.toBack();
                    anchorPaneAjuda.toBack();
                    anchorPaneInicio.toBack();
                    atualizarTabela();

                } else {
                    if (event.getSource() == btnConsultas) {
                        anchoPaneConsulta.toFront();
                        anchorPanePaciente.toBack();
                        anchorPaneAjuda.toBack();
                        anchorPaneInicio.toBack();

                    } else {
                        if (event.getSource() == btnAjuda) {
                            anchorPaneAjuda.toFront();
                            anchorPanePaciente.toBack();
                            anchoPaneConsulta.toBack();
                            anchorPaneInicio.toBack();

                        }
                    }
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados verifique se o seu servidor está ativo", "ERRO", 0);

        }

    }

    //---> iniciar todo o acesso aos botoes de menu
    @FXML
    private void acessarTelaCadastrarMedico(ActionEvent event) throws IOException {
        try {
            abrirFormulario("telaCadastrarMedico");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados verifique se o seu servidor está ativo", "ERRO", 0);
        }
    }

    @FXML
    private void acessarTelaCadastrarPaciente(ActionEvent event) {
        try {
            abrirFormulario("telaCadastrarPaciente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados verifique se o seu servidor está ativo", "ERRO", 0);
        }
    }

    @FXML
    private void acessarTelaCadastrarAtendente(ActionEvent event) throws IOException {
        abrirFormulario("telaCadastrarAtendente");
    }

    @FXML
    private void acessarTelaAgendarConsulta(ActionEvent event) throws IOException {
        try {
            abrirFormulario("telaAgendarConsulta");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados verifique se o seu servidor está ativo", "ERRO", 0);

        }

    }

    @FXML
    private void acessarTelaListarConsultas(ActionEvent event) {
        try {
            abrirFormulario("telaConsultas");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados verifique se o seu servidor está ativo", "ERRO", 0);

        }

    }
    //---> finaliza todo o acesso aos botoes de menu

    //-----> inicia toda parte relacionada a tabela paciente
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
        TableColumn<Paciente, String> colunaCidade = new TableColumn<>("Cidade");
        TableColumn<Paciente, String> colunaRua = new TableColumn<>("Rua");
        TableColumn<Paciente, String> colunaEstado = new TableColumn<>("Estado");
        TableColumn<Paciente, String> colunaEmail = new TableColumn<>("Email");
        TableColumn<Paciente, String> colunaTelefone = new TableColumn<>("Telefone");
        TableColumn<Paciente, String> colunaBairro = new TableColumn<>("Bairro");

        //---> DETERMINAR UM VALOR DE LARGURA IGUAL PARA TODAS AS COLUNAS DA TABELA
        //tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.getColumns().addAll(colunaCodigo, colunaNome, colunaCpf, colunaNumSus, colunaDataNasc, colunaCidade, colunaRua, colunaEstado, colunaEmail, colunaTelefone, colunaBairro);

        colunaCodigo.setCellValueFactory(new PropertyValueFactory("codigo"));
        colunaNome.setCellValueFactory(new PropertyValueFactory("nome"));
        colunaCpf.setCellValueFactory(new PropertyValueFactory("cpf"));
        colunaDataNasc.setCellValueFactory(new PropertyValueFactory("dataNascimento"));
        colunaNumSus.setCellValueFactory(new PropertyValueFactory("numSus"));
        colunaCidade.setCellValueFactory(new PropertyValueFactory("cidade"));
        colunaRua.setCellValueFactory(new PropertyValueFactory("rua"));
        colunaEstado.setCellValueFactory(new PropertyValueFactory("estado"));
        colunaEmail.setCellValueFactory(new PropertyValueFactory("email"));
        colunaTelefone.setCellValueFactory(new PropertyValueFactory("telefone"));
        colunaBairro.setCellValueFactory(new PropertyValueFactory("bairro"));
    }

    @Override
    public void atualizarTabela() {
        observableListPac.clear();
        //tenho que colocar aqui a classe que vai ser ultilizada
        listaPac = daoPac.consultar(txtPesquisarPaciente.getText(), "Paciente");

        for (Paciente t : listaPac) {
            observableListPac.add(t);

        }

        tableView.getItems().setAll(observableListPac);
        tableView.getSelectionModel().selectFirst();
    }

    @Override
    public void setCamposFormulario() {

    }

    @Override
    public void limparCamposFormulario() {
    }
    //-----> finalizar toda parte relacionada a tabela paciente

    //----> inicia os metodos da tabela e dos botoes
    @FXML
    private void moverTabelaPaciente(KeyEvent event) {
        setCamposFormulario();
    }

    @FXML
    private void atualizarTabelaPaciente(ActionEvent event) {
        atualizarTabela();
    }

    @FXML
    private void adicionarPaciente(ActionEvent event) throws IOException {
        abrirFormulario("telaCadastrarPaciente");
    }

    @FXML
    private void clicarTabelaPaciente(MouseEvent event) {
        setCamposFormulario();
    }

    @FXML
    private void filtrarRegistroPaciente(KeyEvent event) {
        atualizarTabela();
    }

    private void filtrarCliente(KeyEvent event) {
        atualizarTabela();
    }
    //----> finalizando os metodos da tabela e dos botoes

    //-----> inicia tudo relacionado a consulta
    public void criarColunasTabelasConsulta() {
        TableColumn<Consulta, Integer> colunaCodigo = new TableColumn<>("Código");
        //---> DETERMINAR O VALOR DA LARGURA DA COLUNA APENAS DESSE CAMPO CODIGO
        //colunaCodigo.setMinWidth(40);
        //colunaCodigo.setMaxWidth(40);

        TableColumn<Consulta, String> colunaDataConsulta = new TableColumn<>("Data da Consulta");
        TableColumn<Consulta, String> colunaDataAtendimento = new TableColumn<>("Data do Atendimento");
        TableColumn<Consulta, String> colunaPaciente = new TableColumn<>("Paciente ");
        TableColumn<Consulta, String> colunaMedico = new TableColumn<>("Médico");
        TableColumn<Consulta, String> colunaAtendente = new TableColumn<>("Atendente");
        TableColumn<Consulta, String> colunaTurno = new TableColumn<>("Turno");
        TableColumn<Consulta, String> colunaValor = new TableColumn<>("Valor");

        //---> DETERMINAR UM VALOR DE LARGURA IGUAL PARA TODAS AS COLUNAS DA TABELA
        tableViewConsulta.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableViewConsulta.getColumns().addAll(colunaCodigo, colunaDataConsulta, colunaDataAtendimento, colunaPaciente, colunaMedico, colunaAtendente, colunaTurno, colunaValor);

        colunaCodigo.setCellValueFactory(new PropertyValueFactory("codigo"));
        colunaDataConsulta.setCellValueFactory(new PropertyValueFactory("dtConsulta"));
        colunaDataAtendimento.setCellValueFactory(new PropertyValueFactory("dataAtendimento"));
        colunaPaciente.setCellValueFactory(new PropertyValueFactory("paciente"));
        colunaMedico.setCellValueFactory(new PropertyValueFactory("medico"));
        colunaAtendente.setCellValueFactory(new PropertyValueFactory("atendente"));
        colunaTurno.setCellValueFactory(new PropertyValueFactory("turno"));
        colunaValor.setCellValueFactory(new PropertyValueFactory("valor"));

    }

    @FXML
    public void atualizarTabelaConsulta() {
        observableListConsulta.clear();
        //tenho que colocar aqui a classe que vai ser ultilizada
        LocalDate dataConsultaO = dppesquisarConsultaPorData.getValue();
        listaConsulta = daoConsulta.consultarD(dataConsultaO, "Consulta");

        for (Consulta c : listaConsulta) {
            observableListConsulta.add(c);

        }

        tableViewConsulta.getItems().setAll(observableListConsulta);
        tableViewConsulta.getSelectionModel().selectFirst();

    }

    public void setCamposFormularioConsulta() {

    }

    public void limparCamposFormularioConsulta() {
    }

    //---> iniciar metodos dos botoes e tabela
    private void atualizarTabelaConsulta(ActionEvent event) {
        atualizarTabelaConsulta();

    }

    @FXML
    private void clicarTabelaConsulta(MouseEvent event) {
        setCamposFormularioConsulta();
    }

    @FXML
    private void moverTabelaConsulta(KeyEvent event) {
        setCamposFormularioConsulta();
    }

    private void filtrarRegistroConsulta(KeyEvent event) {
        atualizarTabelaConsulta();
    }

    //----> finalizar a parte de consulta
    public void abrirFormulario(String formulario) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/" + formulario + ".fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Formulário");
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();

    }

    //---> metodo para fechar o programa através do item de menu
    @FXML
    private void fecharPrograma(ActionEvent event) throws IOException {
        Platform.exit();
        System.exit(0);
    }

    private void filtrarRegistroConsultamouse(MouseEvent event) {
        atualizarTabelaConsulta();
    }

    @FXML
    private void fil(TouchEvent event) {
        atualizarTabelaConsulta();
    }

    @FXML
    private void atualizarTbConsulta(ActionEvent event) {
        atualizarTabelaConsulta();
    }

}
