
package com.equipe2.clinicalsystem.controller;

import com.equipe2.clinicalsystem.dao.CrudGenericoDao;
import com.equipe2.clinicalsystem.model.Consulta;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author cce
 */
public class TelaConsultasController implements Initializable {
@FXML
    private TableView<Consulta> tableViewConsulta;

    //---> tudo relacionado a tabela Consulta
    private CrudGenericoDao<Consulta> daoConsulta = new CrudGenericoDao();
    private ObservableList<Consulta> observableListConsulta = FXCollections.observableArrayList();
    private List<Consulta> listaConsulta;
    private Consulta objetoSelecionadoConsulta = new Consulta();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        criarColunasTabelasConsulta();
        atualizarTabelaConsulta();
    }    
    
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

    public void atualizarTabelaConsulta() {
        observableListConsulta.clear();
        listaConsulta = daoConsulta.consultarDa("Consulta");

        for (Consulta c : listaConsulta) {
            observableListConsulta.add(c);

        }

        tableViewConsulta.getItems().setAll(observableListConsulta);
        tableViewConsulta.getSelectionModel().selectFirst();

    }

}
