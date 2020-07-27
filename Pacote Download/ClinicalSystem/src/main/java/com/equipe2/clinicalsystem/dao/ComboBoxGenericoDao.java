/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equipe2.clinicalsystem.dao;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;

/**
 *
 * @author cce
 */
public class ComboBoxGenericoDao<T> {
    private ObservableList<T> obsList = FXCollections.observableArrayList();

    public ObservableList<T> comboBox(String nomeClasse) {
        List<T> lista = new ArrayList<>();
        Session session = Conexao.getSessionFactory().openSession();
        session.beginTransaction();
        lista = session.createQuery(" from "+nomeClasse).getResultList();
        session.getTransaction().commit();
        session.close();

        for (T t : lista) {
            obsList.add(t);
        }
        return obsList;
    }
}
