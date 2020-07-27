package com.equipe2.clinicalsystem.dao;

import com.equipe2.clinicalsystem.model.Atendente;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

//---> quem chamar esse metodo deve passa a classe modelo que ela ta representando 
public class CrudGenericoDao<T> {

    public boolean salvar(T tipo) {
        try {
            //---> essa variavel recebe a classe de conexao em seguida chama o metodo para criar a conexão e abre a conexao
            Session session = Conexao.getSessionFactory().openSession();
            //--> inicia a transação
            session.beginTransaction();

            //--->indicar o que é para ser feito se é uma exclusao alteração ou se é para salvar 
            session.merge(tipo); //---> serve para salvar e alterar se no caso o objeto conter registro ele alterar se não ele salva um novo

            //---> pega a transação e grava no bd
            session.getTransaction().commit();
            //---> fecha a conexão
            session.close();
            return true;
        } catch (Exception erro) {
            System.out.println("ocorreu o erro ao salvar" + erro);
            //Arquivo de logs
            return false;
        }
    }

    public void excluir(T tipo) {
        try {
            Session session = Conexao.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(tipo);
            session.getTransaction().commit();
            session.close();
            System.out.println("Registro excluido com sucesso ");
        } catch (Exception erro) {
            System.out.println("ocorreu o erro ao Excluir " + erro);
        }
    }

    public List<T> consultar(String nome, String nomeClasse) {
        List<T> lista = new ArrayList();
        Session session = Conexao.getSessionFactory().openSession();
        session.beginTransaction();

        if (nome.length() == 0) {
            lista = session.createQuery(" from " + nomeClasse).getResultList();
        } else {
            // --> m = modelo de classe
            lista = session.createQuery("from " + nomeClasse + " m where m.nome like" + "'" + nome + "%'").getResultList();
        }

        session.getTransaction().commit();
        session.close();
        return lista;
    }

    public boolean verificarLogin(String nomeClasse1, String nomeClasse2, String cpf, String login) {

        boolean verificar = false;
        Session session = Conexao.getSessionFactory().openSession();
        session.beginTransaction();
        List<Atendente> listaAtendente = new ArrayList();

        listaAtendente = session.createQuery("from " + nomeClasse1 + " m, " + nomeClasse2 + " n where m.cpf =" + "'" + cpf + "' and n.senha=" + "'" + login + "'").getResultList();

        if (listaAtendente.isEmpty()) {
            verificar = false;
        } else {
            verificar = true;
        }

        session.getTransaction().commit();
        session.close();
        return verificar;
    }

    public List<T> consultarD(LocalDate dataConsulta, String nomeClasse) {
        List<T> lista = new ArrayList();
        Session session = Conexao.getSessionFactory().openSession();
        session.beginTransaction();

        lista = session.createQuery("from " + nomeClasse + " m where m.dtConsulta like" + "'" + dataConsulta + "%'").getResultList();

        session.getTransaction().commit();
        session.close();
        return lista;
    }
    
     public List<T> consultarDa(String nomeClasse) {
        List<T> lista = new ArrayList();
        Session session = Conexao.getSessionFactory().openSession();
        session.beginTransaction();

        lista = session.createQuery("from " + nomeClasse).getResultList();

        session.getTransaction().commit();
        session.close();
        return lista;
    }
}
