/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.equipe2.clinicalsystem.util;

import com.equipe2.clinicalsystem.TelaCadastroLogin;
import com.equipe2.clinicalsystem.model.Login;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.Stage;
import sun.security.provider.MD5;

/**
 *
 * @author cce
 */
public class Criptografia {

    public static String criptografia(String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte messageDisgest[] = md.digest(senha.getBytes("UTF-8"));

        StringBuilder sb = new StringBuilder();

        for (byte b : messageDisgest) {
            sb.append(String.format("%02x", 0xFF & b));

        }
        String senhaHex = sb.toString();
        return senhaHex;

    }

}
