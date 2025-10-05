package br.inatel.cdg;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ConexaoBDTest {
    // Informações para conectar ao bd
    private String url = System.getenv("URL");
    private String user = System.getenv("USER");
    private String password = System.getenv("USER_PASSWORD");

    @Mock
    private Connection connectionMock;
    @Mock
    private Statement statementMock;
    @Mock
    private ResultSet resultSetMock;
    @Mock
    private ResultSetMetaData metaDataMock;

    // Testes de conexão
    @Test
    public void testeConexaoValida() throws SQLException {
        // Conexão com o bd
        ConexaoBD conexaobd = new ConexaoBD(url, user, password, connectionMock, statementMock, resultSetMock, metaDataMock);

        // Simulação de conexão válida
        Mockito.when(connectionMock.isValid(2)).thenReturn(true);

        boolean statusConexao = conexaobd.conectar();

        assertTrue(statusConexao);
    }
    @Test (expected = SQLException.class)
    public void testeURLInvalida() throws SQLException {
        url = "urlInvalida";

        // Simulação de url inválida
        Mockito.when(connectionMock.isValid(2)).thenThrow(new SQLException());

        // Conexão com o bd
        ConexaoBD conexaobd = new ConexaoBD(url, user, password, connectionMock, statementMock, resultSetMock, metaDataMock);
        conexaobd.conectar();
    }
    @Test (expected = SQLSyntaxErrorException.class)
    public void testeDatabaseInvalido() throws SQLException {
        url = "jdbc:mysql://localhost:3306/databaseInvalido";

        // Simulação de database inválida
        Mockito.when(connectionMock.isValid(2)).thenThrow(new SQLSyntaxErrorException());

        // Conexão com o bd
        ConexaoBD conexaobd = new ConexaoBD(url, user, password, connectionMock, statementMock, resultSetMock, metaDataMock);
        conexaobd.conectar();
    }
    @Test (expected = SQLException.class)
    public void testeUserInvalido() throws SQLException {
        // Informações para conectar ao bd
        user = "userInvalido";

        // Simulação de user inválido
        Mockito.when(connectionMock.isValid(2)).thenThrow(new SQLException());

        // Conexão com o bd
        ConexaoBD conexaobd = new ConexaoBD(url, user, password, connectionMock, statementMock, resultSetMock, metaDataMock);
        conexaobd.conectar();
    }
    @Test (expected = SQLException.class)
    public void testePasswordInvalida() throws SQLException {
        password = "senhaInvalida";

        // Simulação de senha inválida
        Mockito.when(connectionMock.isValid(2)).thenThrow(new SQLException());

        // Conexão com o bd
        ConexaoBD conexaobd = new ConexaoBD(url, user, password, connectionMock, statementMock, resultSetMock, metaDataMock);
        conexaobd.conectar();
    }



    // Testes da criação do statement
    @Test
    public void testeCriarStatement() throws SQLException {
        // Conexão com o bd
        ConexaoBD conexaobd = new ConexaoBD(url, user, password, connectionMock, statementMock, resultSetMock, metaDataMock);

        // Simulação de conexão válida
        Mockito.when(connectionMock.isValid(2)).thenReturn(true);

        conexaobd.conectar();

        // Simulação de statement válido
        Mockito.when(connectionMock.createStatement()).thenReturn(statementMock);

        // Criação do statement
        boolean statusStatement = conexaobd.criarStatement();

        assertTrue(statusStatement);
    }
    @Test (expected = SQLException.class)
    public void testeStatementSemConectar() throws SQLException {
        connectionMock = null; //simular não criar conexão antes

        // Conexão com o bd
        ConexaoBD conexaobd = new ConexaoBD(url, user, password, connectionMock, statementMock, resultSetMock, metaDataMock);

        // Criação do statement
        conexaobd.criarStatement();
    }


    // Testes do select
    @Test
    public void testSelect() throws SQLException {
        // Conexão com o bd
        ConexaoBD conexaobd = new ConexaoBD(url, user, password, connectionMock, statementMock, resultSetMock, metaDataMock);

        // Simulação de conexão válida
        Mockito.when(connectionMock.isValid(2)).thenReturn(true);

        conexaobd.conectar();

        // Simulação de statement válido
        Mockito.when(connectionMock.createStatement()).thenReturn(statementMock);

        // Criação do statement
        conexaobd.criarStatement();

        // Simulação de query válida
        Mockito.when(statementMock.executeQuery(Mockito.anyString())).thenReturn(resultSetMock);

        // Fazer um select das 5 primeiras linhas do dataset
        boolean statusSelect = conexaobd.select("germancredit", 5);

        assertTrue(statusSelect);
    }
    @Test (expected = SQLException.class)
    public void testSelectSemStatement() throws SQLException {
        statementMock = null; //simular não criar statment

        // Conexão com o bd
        ConexaoBD conexaobd = new ConexaoBD(url, user, password, connectionMock, statementMock, resultSetMock, metaDataMock);

        // Simulação de conexão válida
        Mockito.when(connectionMock.isValid(2)).thenReturn(true);

        conexaobd.conectar();

        // Fazer um select das 5 primeiras linhas do dataset
        conexaobd.select("germancredit", 5);
    }
    @Test (expected = SQLSyntaxErrorException.class)
    public void testSelectTabelaInvalida() throws SQLException {
        // Conexão com o bd
        ConexaoBD conexaobd = new ConexaoBD(url, user, password, connectionMock, statementMock, resultSetMock, metaDataMock);

        // Simulação de conexão válida
        Mockito.when(connectionMock.isValid(2)).thenReturn(true);

        conexaobd.conectar();

        // Simulação de statement válido
        Mockito.when(connectionMock.createStatement()).thenReturn(statementMock);

        // Criação do statement
        conexaobd.criarStatement();

        // Simulação de tabela inválida
        Mockito.when(statementMock.executeQuery(Mockito.anyString())).thenThrow(new SQLSyntaxErrorException());

        // Fazer um select das 5 primeiras linhas do dataset
        conexaobd.select("tabelaInvalida", 5);
    }

    @Test (expected = SQLSyntaxErrorException.class)
    public void testSelectQtdInvalida() throws SQLException {
        // Conexão com o bd
        ConexaoBD conexaobd = new ConexaoBD(url, user, password, connectionMock, statementMock, resultSetMock, metaDataMock);

        // Simulação de conexão válida
        Mockito.when(connectionMock.isValid(2)).thenReturn(true);

        conexaobd.conectar();

        // Simulação de statement válido
        Mockito.when(connectionMock.createStatement()).thenReturn(statementMock);

        // Criação do statement
        conexaobd.criarStatement();

        // Simulação de quantidade inválida
        Mockito.when(statementMock.executeQuery(Mockito.anyString())).thenThrow(new SQLSyntaxErrorException());

        // Fazer um select das 5 primeiras linhas do dataset
        conexaobd.select("germancredit", -2);
    }

    @Test(expected = SQLSyntaxErrorException.class)
    public void testSelectQtdNull() throws SQLException{
        // Conexão com o bd
        ConexaoBD conexaobd = new ConexaoBD(url, user, password, connectionMock, statementMock, resultSetMock, metaDataMock);

        // Simulação de conexão válida
        Mockito.when(connectionMock.isValid(2)).thenReturn(true);

        conexaobd.conectar();

        // Simulação de statement válido
        Mockito.when(connectionMock.createStatement()).thenReturn(statementMock);

        // Criação do statement
        conexaobd.criarStatement();

        // Simulação de tabela inválida
        Mockito.when(statementMock.executeQuery(Mockito.anyString())).thenThrow(new SQLSyntaxErrorException());

        // Fazer um select das 0 primeiras linhas do dataset
        conexaobd.select("germancredit", null);
    }

    //Testes para seleção de dados dos indivíduos com maiores créditos
    @Test
    public void testOkayAnalyzeByCredit() throws SQLException{
        // Conexão com o bd
        ConexaoBD conexaobd = new ConexaoBD(url, user, password, connectionMock, statementMock, resultSetMock, metaDataMock);

        // Simulação de conexão válida
        Mockito.when(connectionMock.isValid(2)).thenReturn(true);

        conexaobd.conectar();

        // Simulação de statement válido
        Mockito.when(connectionMock.createStatement()).thenReturn(statementMock);

        // Criação do statement
        conexaobd.criarStatement();

        // Simulação de query válida
        Mockito.when(statementMock.executeQuery(Mockito.anyString())).thenReturn(resultSetMock);

        // Ordenar as idades de acordo com os maiores créditos do dataset
        boolean statusAnalyze = conexaobd.analyzeByCredit("germancredit", "alter"); //idade

        assertTrue(statusAnalyze);
    }

    @Test
    public void testWrongVariableAnalyzeByCredit() throws SQLException{
        // Conexão com o bd
        ConexaoBD conexaobd = new ConexaoBD(url, user, password, connectionMock, statementMock, resultSetMock, metaDataMock);

        // Simulação de conexão válida
        Mockito.when(connectionMock.isValid(2)).thenReturn(true);

        conexaobd.conectar();

        // Simulação de statement válido
        Mockito.when(connectionMock.createStatement()).thenReturn(statementMock);

        // Criação do statement
        conexaobd.criarStatement();

        // Simulação de query inválida
        Mockito.when(statementMock.executeQuery(Mockito.anyString())).thenThrow(new SQLSyntaxErrorException());

        // Ordenar as idades de acordo com os maiores créditos do dataset
        boolean statusAnalyze = conexaobd.analyzeByCredit("germancredit", "duration"); //duração do crédito (lauzfeit é o correto)

        assertFalse(statusAnalyze);
    }

    @Test(expected = SQLException.class)
    public void testNoStatementAnalyzeByCredit() throws SQLException{
        statementMock = null; //simular não criar statment

        // Conexão com o bd
        ConexaoBD conexaobd = new ConexaoBD(url, user, password, connectionMock, statementMock, resultSetMock, metaDataMock);

        // Simulação de conexão válida
        Mockito.when(connectionMock.isValid(2)).thenReturn(true);

        conexaobd.conectar();

        // Ordenar as idades de acordo com os maiores créditos do dataset
        boolean statusAnalyze = conexaobd.analyzeByCredit("germancredit", "duration"); //duração do crédito (lauzfeit é o correto)
    }

    // Testes de percorrer resultado
    @Test
    public void testePercorrerResultado() throws SQLException {
        // Simulação da primeira linha do dataset e suas colunas
        String[] colunas = {
                "id", "laufkont", "laufzeit", "moral", "verw", "hoehe", "sparkont", "beszeit",
                "rate", "famges", "buerge", "wohnzeit", "verm", "alter", "weitkred", "wohn",
                "bishkred", "beruf", "pers", "telef", "gastarb", "kredit"
        };

        Object[] valores = {
                1, 1, 18, 4, 2, 1049, 1, 2, 4, 2, 1, 4, 2, 21, 3, 1,
                1, 3, 2, 1, 2, 1
        };

        // Conexão com o bd
        ConexaoBD conexaobd = new ConexaoBD(url, user, password, connectionMock, statementMock, resultSetMock, metaDataMock);

        // Simulação de conexão válida
        Mockito.when(connectionMock.isValid(2)).thenReturn(true);

        conexaobd.conectar();

        // Simulação de statement válido
        Mockito.when(connectionMock.createStatement()).thenReturn(statementMock);

        // Criação do statement
        conexaobd.criarStatement();

        // Simulação de query válida
        Mockito.when(statementMock.executeQuery(Mockito.anyString())).thenReturn(resultSetMock);

        // Fazer um select das 5 primeiras linhas do dataset
        conexaobd.select("germancredit", 1);

        // Simulação de informações do dataset válido
        Mockito.when(resultSetMock.getMetaData()).thenReturn(metaDataMock);
        Mockito.when(resultSetMock.next()).thenReturn(true, false);
        Mockito.when(metaDataMock.getColumnCount()).thenReturn(colunas.length);

        // Resultado esperado
        List<Map<String, Object>> resultadoEsperado = new ArrayList<>();
        Map<String, Object> linha = new LinkedHashMap<>();

        // Adicionar valores das colunas no resultado esperado e no mock de metadata
        for (int i = 0; i < colunas.length; i++) {
            Mockito.when(metaDataMock.getColumnName(i + 1)).thenReturn(colunas[i]);
            Mockito.when(resultSetMock.getObject(i + 1)).thenReturn(valores[i]);
            linha.put(colunas[i], valores[i]);
        }
        resultadoEsperado.add(linha);

        // Mostrar resultados
        List<Map<String, Object>> resultados = conexaobd.percorrerResultados();

        assertEquals(resultadoEsperado, resultados);
    }
    @Test (expected = SQLException.class)
    public void testePercorrerSemResultSet() throws SQLException {
        resultSetMock = null; //simular não criar resultSet

        // Conexão com o bd
        ConexaoBD conexaobd = new ConexaoBD(url, user, password, connectionMock, statementMock, resultSetMock, metaDataMock);

        // Simulação de conexão válida
        Mockito.when(connectionMock.isValid(2)).thenReturn(true);

        conexaobd.conectar();

        // Simulação de statement válido
        Mockito.when(connectionMock.createStatement()).thenReturn(statementMock);

        // Criação do statement
        conexaobd.criarStatement();

        // Percorrer resultados
        conexaobd.percorrerResultados();
    }

    @Test(expected = SQLException.class)
    public void testePercorrerSemStatementSet() throws SQLException{
        // Conexão com o bd
        ConexaoBD conexaobd = new ConexaoBD(url, user, password, connectionMock, statementMock, resultSetMock, metaDataMock);

        // Simulação de conexão válida
        Mockito.when(connectionMock.isValid(2)).thenReturn(true);

        conexaobd.conectar();

        // Simular statment não criado
        Mockito.when(statementMock.isClosed()).thenReturn(true);

        // Tentar percorrer resultados sem statement (statement.isClosed())
        conexaobd.percorrerResultados();
    }

    // Teste de encerrar conexão
    @Test
    public void testeEncerrar() throws SQLException {
        // Conexão com o bd
        ConexaoBD conexaobd = new ConexaoBD(url, user, password, connectionMock, statementMock, resultSetMock, metaDataMock);

        // Simulação de conexões abertas
        Mockito.when(resultSetMock.isClosed()).thenReturn(false);
        Mockito.when(statementMock.isClosed()).thenReturn(false);
        Mockito.when(connectionMock.isClosed()).thenReturn(false);

        // Simular fechar conexão
        Mockito.doNothing().when(resultSetMock).close();
        Mockito.doNothing().when(statementMock).close();
        Mockito.doNothing().when(connectionMock).close();

        // Encerrar conexão
        boolean statusEncerramento = conexaobd.encerrar();

        assertTrue(statusEncerramento);
    }
    @Test
    public void testeEncerrarSemCriacao() throws SQLException {
        // Conexão com o bd
        ConexaoBD conexaobd = new ConexaoBD(url, user, password);

        // Encerrar conexão
        boolean statusEncerramento = conexaobd.encerrar();

        assertTrue(statusEncerramento);
    }
}
