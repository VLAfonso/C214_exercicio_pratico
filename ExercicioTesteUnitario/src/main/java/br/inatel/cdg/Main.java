package br.inatel.cdg;

import br.inatel.cdg.ConexaoBD;

import java.sql.SQLException;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        // Informações para conectar ao bd
        String url = "jdbc:mysql://localhost:3306/statlog";
        String user = "root";
        String password = "root";

        // Conexão com o bd
        ConexaoBD conexaobd = new ConexaoBD(url, user, password);
        conexaobd.conectar();

        // Criação do statement
        conexaobd.criarStatement();

        // Fazer um select das 5 primeiras linhas do dataset
        conexaobd.select("germancredit", 5);

        // Mostrar resultados
        conexaobd.mostrarResultados();

        // Encerrar conexão
        conexaobd.encerrar();
    }
}
