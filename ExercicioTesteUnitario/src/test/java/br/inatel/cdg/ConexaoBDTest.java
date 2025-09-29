package br.inatel.cdg;

import org.junit.Test;

import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

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
        boolean statusConexao = conexaobd.conectar();

        assertTrue(statusConexao);
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
        boolean statusStatement = conexaobd.criarStatement();

        assertTrue(statusStatement);
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
        boolean statusSelect = conexaobd.select("germancredit", 5);

        assertTrue(statusSelect);
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

    @Test(expected = SQLSyntaxErrorException.class)
    public void testSelectQtdNull() throws SQLException{
        // Informações para conectar ao bd
        String url = "jdbc:mysql://localhost:3306/statlog";
        String user = "root";
        String password = "root";

        // Conexão com o bd
        ConexaoBD conexaobd = new ConexaoBD(url, user, password);
        conexaobd.conectar();

        // Criação do statement
        conexaobd.criarStatement();

        // Fazer um select das 0 primeiras linhas do dataset
        conexaobd.select("germancredit", null);
    }

    //Testes para seleção de dados dos indivíduos com maiores créditos
    @Test
    public void testOkayAnalyzeByCredit() throws SQLException{
        // Informações para conectar ao bd
        String url = "jdbc:mysql://localhost:3306/statlog";
        String user = "root";
        String password = "root";

        // Conexão com o bd
        ConexaoBD conexaobd = new ConexaoBD(url, user, password);
        conexaobd.conectar();

        // Criação do statement
        conexaobd.criarStatement();

        // Ordenar as idades de acordo com os maiores créditos do dataset
        boolean statusAnalyze = conexaobd.analyzeByCredit("germancredit", "alter"); //idade

        assertTrue(statusAnalyze);
    }

    @Test
    public void testWrongVariableAnalyzeByCredit() throws SQLException{
        // Informações para conectar ao bd
        String url = "jdbc:mysql://localhost:3306/statlog";
        String user = "root";
        String password = "root";

        // Conexão com o bd
        ConexaoBD conexaobd = new ConexaoBD(url, user, password);
        conexaobd.conectar();

        // Criação do statement
        conexaobd.criarStatement();

        // Ordenar as idades de acordo com os maiores créditos do dataset
        boolean statusAnalyze = conexaobd.analyzeByCredit("germancredit", "duration"); //duração do crédito (lauzfeit é o correto)

        assertFalse(statusAnalyze);
    }

    @Test(expected = SQLException.class)
    public void testNoStatementAnalyzeByCredit() throws SQLException{
        // Informações para conectar ao bd
        String url = "jdbc:mysql://localhost:3306/statlog";
        String user = "root";
        String password = "root";

        // Conexão com o bd
        ConexaoBD conexaobd = new ConexaoBD(url, user, password);
        conexaobd.conectar();

        // Ordenar as idades de acordo com os maiores créditos do dataset
        boolean statusAnalyze = conexaobd.analyzeByCredit("germancredit", "duration"); //duração do crédito (lauzfeit é o correto)
    }

    // Testes de percorrer resultado
    @Test
    public void testePercorrerResultado() throws SQLException {
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
        conexaobd.select("germancredit", 1);

        // Mostrar resultados
        List<Map<String, Object>> resultados = conexaobd.percorrerResultados();

        // Resultado esperado
        List<Map<String, Object>> resultadoEsperado = new ArrayList<>();
        Map<String, Object> linha = new LinkedHashMap<>();
        linha.put("id", 1);
        linha.put("laufkont", 1);
        linha.put("laufzeit", 18);
        linha.put("moral", 4);
        linha.put("verw", 2);
        linha.put("hoehe", 1049);
        linha.put("sparkont", 1);
        linha.put("beszeit", 2);
        linha.put("rate", 4);
        linha.put("famges", 2);
        linha.put("buerge", 1);
        linha.put("wohnzeit", 4);
        linha.put("verm", 2);
        linha.put("alter", 21);
        linha.put("weitkred", 3);
        linha.put("wohn", 1);
        linha.put("bishkred", 1);
        linha.put("beruf", 3);
        linha.put("pers", 2);
        linha.put("telef", 1);
        linha.put("gastarb", 2);
        linha.put("kredit", 1);
        resultadoEsperado.add(linha);

        assertEquals(resultadoEsperado, resultados);
    }
    @Test (expected = SQLException.class)
    public void testePercorrerSemResultSet() throws SQLException {
        // Informações para conectar ao bd
        String url = "jdbc:mysql://localhost:3306/statlog";
        String user = "root";
        String password = "root";

        // Conexão com o bd
        ConexaoBD conexaobd = new ConexaoBD(url, user, password);
        conexaobd.conectar();

        // Criação do statement
        conexaobd.criarStatement();

        // Percorrer resultados
        conexaobd.percorrerResultados();
    }

    @Test(expected = SQLException.class)
    public void testePercorrerSemStatementSet() throws SQLException{
        // Informações para conectar ao bd
        String url = "jdbc:mysql://localhost:3306/statlog";
        String user = "root";
        String password = "root";

        // Conexão com o bd
        ConexaoBD conexaobd = new ConexaoBD(url, user, password);
        conexaobd.conectar();

        // Tentar percorrer resultados sem statement (statement.isClosed())
        conexaobd.percorrerResultados();
    }

    // Teste de encerrar conexão
    @Test
    public void testeEncerrar() throws SQLException {
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

        // Percorrer resultados
        conexaobd.percorrerResultados();

        // Encerrar conexão
        boolean statusEncerramento = conexaobd.encerrar();

        assertTrue(statusEncerramento);
    }
    @Test
    public void testeEncerrarSemCriacao() throws SQLException {
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
