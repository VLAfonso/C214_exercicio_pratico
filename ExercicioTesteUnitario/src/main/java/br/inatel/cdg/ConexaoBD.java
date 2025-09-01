package br.inatel.cdg;

import java.sql.*;

public class ConexaoBD {
    // Informações para conectar ao bd
    private String url;
    private String user;
    private String password;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public ConexaoBD(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    // Conexão com o bd
    public void conectar() throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
    }

    // Criação do statement
    public void criarStatement() throws SQLException {
        if (connection == null)
            throw new SQLException();

        statement = connection.createStatement();
    }

    // Fazer um select das n primeiras linhas do dataset
    public void select(String tabela, Integer qtd) throws SQLException {
        if (statement == null)
            throw new SQLException();

        String query = "SELECT * FROM "+ tabela +" LIMIT "+String.valueOf(qtd);
        resultSet = statement.executeQuery(query);
    }

    // Mostrar resultados
    public void mostrarResultados() throws SQLException {
        if (resultSet == null)
            throw new SQLException();

        ResultSetMetaData metaData = resultSet.getMetaData(); // pegar informações do dataset
        int columnCount = metaData.getColumnCount(); // pegar num de colunas do dataset

        // Percorrer colunas e mostrar dados
        while (resultSet.next()){
            for (int i = 1; i <= columnCount; i++) {
                System.out.println(metaData.getColumnName(i) + " = " + resultSet.getInt(i));
            }
            System.out.println(" ");
        }
    }

    // Encerrar conexão com o bd
    public void encerrar() throws SQLException {
        if(resultSet != null)
            resultSet.close();

        if(statement != null)
            statement.close();

        if(connection != null)
            connection.close();
    }

}
