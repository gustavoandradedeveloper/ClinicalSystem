package com.equipe2.clinicalsystem.util;

import java.util.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author cce
 */
public class UF {

    private static ObservableList<String> obsList;

    public static ObservableList gerarUF() {
        obsList = FXCollections.observableArrayList(
                "MG",
                "SP",
                "PE",
                "RJ"
        );
        return obsList;
    }
}
