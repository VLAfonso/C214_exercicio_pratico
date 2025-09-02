package br.inatel.cdg;

import br.inatel.cdg.ConexaoBD;
import org.junit.Test;

import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;

public class ConexaoBDTest {
    // Testes de conexão
    @Test
    public void testeConexaoValida() throws SQLException {
        // Informações para conectar ao bd
        String url = "jdbc:mysql://localhost:3306/statlog";
        String user = "root";
        String password = "root";

        // Conexão com o bd
        ConexaoBD conexaobd = new ConexaoBD(url, user, password);
        conexaobd.conectar();
    }
    @Test (expected = SQLException.class)
    public void testeURLInvalida() throws SQLException {
        // Informações para conectar ao bd
        String url = "urlInvalida";
        String user = "root";
        String password = "root";

        // Conexão com o bd
        ConexaoBD conexaobd = new ConexaoBD(url, user, password);
        conexaobd.conectar();
    }
    @Test (expected = SQLSyntaxErrorException.class)
    public void testeDatabaseInvalido() throws SQLException {
        // Informações para conectar ao bd
        String url = "jdbc:mysql://localhost:3306/databaseInvalido";
        String user = "root";
        String password = "root";

        // Conexão com o bd
        ConexaoBD conexaobd = new ConexaoBD(url, user, password);
        conexaobd.conectar();
    }
    @Test (expected = SQLException.class)
    public void testeUserInvalido() throws SQLException {
        // Informações para conectar ao bd
        String url = "jdbc:mysql://localhost:3306/statlog";
        String user = "userInvalido";
        String password = "root";

        // Conexão com o bd
        ConexaoBD conexaobd = new ConexaoBD(url, user, password);
        conexaobd.conectar();
    }
    @Test (expected = SQLException.class)
    public void testePasswordInvalida() throws SQLException {
        // Informações para conectar ao bd
        String url = "jdbc:mysql://localhost:3306/statlog";
        String user = "root";
        String password = "senhaInvalida";

        // Conexão com o bd
        ConexaoBD conexaobd = new ConexaoBD(url, user, password);
        conexaobd.conectar();
    }



    // Testes da criação do statement
    @Test
    public void testeCriarStatement() throws SQLException {
        // Informações para conectar ao bd
        String url = "jdbc:mysql://localhost:3306/statlog";
        String user = "root";
        String password = "root";

        // Conexão com o bd
        ConexaoBD conexaobd = new ConexaoBD(url, user, password);
        conexaobd.conectar();

        // Criação do statement
        conexaobd.criarStatement();
    }
    @Test (expected = SQLException.class)
    public void testeStatementSemConectar() throws SQLException {
        // Informações para conectar ao bd
        String url = "jdbc:mysql://localhost:3306/statlog";
        String user = "root";
        String password = "root";

        // Conexão com o bd
        ConexaoBD conexaobd = new ConexaoBD(url, user, password);

        // Criação do statement
        conexaobd.criarStatement();
    }



    // Testes do select
    @Test
    public void testSelect() throws SQLException {
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
    }
    @Test (expected = SQLException.class)
    public void testSelectSemStatement() throws SQLException {
        // Informações para conectar ao bd
        String url = "jdbc:mysql://localhost:3306/statlog";
        String user = "root";
        String password = "root";

        // Conexão com o bd
        ConexaoBD conexaobd = new ConexaoBD(url, user, password);
        conexaobd.conectar();

        // Fazer um select das 5 primeiras linhas do dataset
        conexaobd.select("germancredit", 5);
    }
    @Test (expected = SQLSyntaxErrorException.class)
    public void testSelectTabelaInvalida() throws SQLException {
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
        conexaobd.select("tabelaInvalida", 5);
    }
    @Test (expected = SQLSyntaxErrorException.class)
    public void testSelectQtdInvalida() throws SQLException {
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
        conexaobd.select("germancredit", -2);
    }



    // Testes de mostrar resultado
    @Test
    public void testeMostrarResultado() throws SQLException {
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
    }
    @Test (expected = SQLException.class)
    public void testeMostrarSemResultSet() throws SQLException {
        // Informações para conectar ao bd
        String url = "jdbc:mysql://localhost:3306/statlog";
        String user = "root";
        String password = "root";

        // Conexão com o bd
        ConexaoBD conexaobd = new ConexaoBD(url, user, password);
        conexaobd.conectar();

        // Criação do statement
        conexaobd.criarStatement();

        // Mostrar resultados
        conexaobd.mostrarResultados();
    }



    // Teste de encerrar conexão
    @Test
    public void testEncerrar() throws SQLException {
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
    @Test
    public void testEncerrarSemCriacao() throws SQLException {
        // Informações para conectar ao bd
        String url = "jdbc:mysql://localhost:3306/statlog";
        String user = "root";
        String password = "root";

        // Conexão com o bd
        ConexaoBD conexaobd = new ConexaoBD(url, user, password);

        // Encerrar conexão
        conexaobd.encerrar();
    }
}
