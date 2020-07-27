/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equipe2.clinicalsystem.util;

/**
 *
 * @author cce
 */
public class ValidaLogin {

    public boolean ValidarLogin(String senha1, String senha2) {

        if ((senha1.equals("")) || (senha2.equals(""))) {
            return false;
        } else {
            if (senha1.equals(senha2)) {
                return true;
            } else {
                return false;
            }

        }

    }

}
