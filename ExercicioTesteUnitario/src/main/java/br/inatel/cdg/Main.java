package br.inatel.cdg;

import br.inatel.cdg.ConexaoBD;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        // Informações para conectar ao bd
        String url = System.getenv("URL");
        String user = System.getenv("USER");
        String password = System.getenv("USER_PASSWORD");

        // Conexão com o bd
        ConexaoBD conexaobd = new ConexaoBD(url, user, password);
        conexaobd.conectar();

        // Criação do statement
        conexaobd.criarStatement();

        // Fazer um select das 5 primeiras linhas do dataset
        conexaobd.select("germancredit", 5);

        // Mostrar resultados
        conexaobd.percorrerResultados();

        // Encerrar conexão
        conexaobd.encerrar();
    }
}
