package br.inatel.cdg;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class ConexaoBD {
    // Informações para conectar ao bd
    private String url;
    private String user;
    private String password;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public ConexaoBD(final String url, final String user, final String password){
        this.url = url;
        this.user = user;
        this.password = password;
    }

    // Conexão com o bd
    public boolean conectar() throws SQLException {
        connection = DriverManager.getConnection(url, user, password);

        return (connection != null && connection.isValid(2));
    }

    // Criação do statement
    public boolean criarStatement() throws SQLException {
        if (connection == null || connection.isClosed()){
            throw new SQLException();
        }
        else {
            statement = connection.createStatement();
            return true;
        }
    }

    // Fazer um select das n primeiras linhas do dataset
    public boolean select(String tabela, Integer qtd) throws SQLException {
        if (statement == null || statement.isClosed() || connection.isClosed()){
            throw new SQLException();
        }
        else {
            String query = "SELECT * FROM "+ tabela +" LIMIT "+String.valueOf(qtd);
            resultSet = statement.executeQuery(query);
            return true;
        }
    }

    // Fazer um filtro para ordenar uma determinada informação das pessoas de acordo com os maiores créditos
    public boolean analyzeByCredit(String tabela, String variable) throws SQLException{
        if (statement == null || statement.isClosed() || connection.isClosed()){
            throw new SQLException();
        }
        else {
            try {
                String query = "SELECT `" + variable + "` FROM " + tabela + " ORDER BY kredit DESC";
                resultSet = statement.executeQuery(query);
                return true;
            }catch (SQLException e){
                return false;
            }
        }
    }

    // Percorrer resultados
    public List<Map<String, Object>> percorrerResultados() throws SQLException {
        if (resultSet == null || resultSet.isClosed() || statement.isClosed() || connection.isClosed()){
            throw new SQLException();
        }
        else {
            ResultSetMetaData metaData = resultSet.getMetaData(); // pegar informações do dataset
            int columnCount = metaData.getColumnCount(); // pegar num de colunas do dataset

            // Percorrer colunas e salvar dados
            List<Map<String, Object>> resultados = new ArrayList<>();
            while (resultSet.next()){
                Map<String, Object> linha = new LinkedHashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    linha.put(metaData.getColumnName(i), resultSet.getObject(i));
                }
                resultados.add(linha);
            }
            return resultados;
        }
    }

    // Encerrar conexão com o bd
    public boolean encerrar() throws SQLException {
        if(resultSet != null && !resultSet.isClosed()){
            resultSet.close();
        }

        if(statement != null && !statement.isClosed()){
            statement.close();
        }

        if(connection != null && !connection.isClosed()){
            connection.close();
        }

        return true;
    }

}
