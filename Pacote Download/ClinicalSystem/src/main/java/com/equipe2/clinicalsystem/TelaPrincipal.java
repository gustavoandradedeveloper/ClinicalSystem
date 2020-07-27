/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equipe2.clinicalsystem;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 *
 * @author cce
 */
public class TelaPrincipal extends Application {
    private static Stage stage;//---> uma janela
    @Override
    public void start(Stage stage) throws Exception{
       
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/telaPrincipal.fxml"));//---> carrega FXML

            Scene scene = new Scene(root);//---> Coloca o fxml em uma cena
            scene.getStylesheets().add("/styles/Styles.css");
            stage.initStyle(StageStyle.DECORATED.UNDECORATED);
            stage.setMaximized(true);
            stage.setResizable(false);
            stage.setScene(scene);//---> coloca a cena em uma janela

            stage.show();//---> abre a janela 
            setStage(stage);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados verifique se o seu servidor está ativo", "ERRO", 0);
            
        }
    }

    
    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        TelaPrincipal.stage = stage;
    }
    
}
