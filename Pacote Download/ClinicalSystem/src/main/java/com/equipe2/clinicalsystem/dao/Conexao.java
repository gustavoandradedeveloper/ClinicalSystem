package com.equipe2.clinicalsystem.dao;

import com.equipe2.clinicalsystem.model.Atendente;
import com.equipe2.clinicalsystem.model.Consulta;
import com.equipe2.clinicalsystem.model.Login;
import com.equipe2.clinicalsystem.model.Paciente;
import com.equipe2.clinicalsystem.model.Medico;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Conexao {

    //---> Session referente ao hibernate sessao de conexao de com bd
    private static SessionFactory conexao = null;
    private static Configuration configuracao;

    //---> constroi fabrica de sessao
    private static SessionFactory buildSessionFactory() {

        // --->objeto que armazena as configuracoes de conexao
        configuracao = new Configuration().configure();

        //----->  configura o acesso ao bd
        configuracao.setProperty("hibernate.connection.username", "root");
        configuracao.setProperty("hibernate.connection.password", "");

        //-----> indicando mapeamento das classe
        configuracao.addPackage("com.equipe2.clinicalsystem.model").addAnnotatedClass(Paciente.class);
        configuracao.addPackage("com.equipe2.clinicalsystem.model").addAnnotatedClass(Medico.class);
        configuracao.addPackage("com.equipe2.clinicalsystem.model").addAnnotatedClass(Atendente.class);
        configuracao.addPackage("com.equipe2.clinicalsystem.model").addAnnotatedClass(Consulta.class);
        configuracao.addPackage("com.equipe2.clinicalsystem.model").addAnnotatedClass(Login.class);

        //-->pega a conexao 
        conexao = configuracao.buildSessionFactory();

        //--> retornar a conexao pra quem chamou
        return conexao;
    }

    //---->pega uma conexao ou criar uma conexao 
    public static SessionFactory getSessionFactory() {
        if (conexao == null) {
            conexao = buildSessionFactory();
        }
        return conexao;
    }
}
