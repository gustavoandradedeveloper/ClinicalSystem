/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equipe2.clinicalsystem.util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author cce
 */
public class Turno {

    private static ObservableList<String> obsList;

    public static ObservableList gerarTurno() {
        obsList = FXCollections.observableArrayList(
                "Manhã",
                "Tarde"
        );
        return obsList;
    }
}
