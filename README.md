# C214 - Teste Unitário
Repositório destinado à atividade de testes unitários da disciplina C214 - Engenharia de Software.
O objetivo é a capacitação no gerenciamento de repositórios, versionamento de código e resolução de conflitos.

# :pencil: Descrição
O projeto apresenta as informações presentes nas 5 primeiras linhas do conjunto de dados South German Credit, obtido do [UC Irvine Machine Learning Repository](https://archive.ics.uci.edu/dataset/573/south+german+credit+update).

# :gear: Configuração e Execução
1. Clonar o repositório
```bash
git clone https://github.com/VLAfonso/C214_Versionamento.git
cd C214_Versionamento
```
2. Criar Banco de Dados

[Executar](statlog-germancredit.sql) o arquivo `statlog-germancredit.sql` para criar e inserir os dados do conjunto de dados South German Credit.

3. Abrir no IntelliJ

Abrir o IntelliJ, acessar File > Open e escolha a pasta do projeto.

3. Configurar Credenciais

No arquivo `Main.java`, ajuste as as credenciais de conexão.
```Java
String user = "USUARIO";
String password = "SENHA";
```

4. Instalar Dependências
```bash
cd ExercicioDependencias
mvn package
```
5. Executar

No IntelliJ, acesse o arquivo da classe `Main.java` pelo caminho `src\main\java\org\example\Main.java` e clique no botão `Run`.

# :white_check_mark: Testes Unitários
Foram realizados 15 testes unitários neste projeto, sendo eles:
1. **testeConexaoValida()** - método `conectar()` - testa uma conexão válida;
2. **testeURLInvalida()** - método `conectar()` - testa uma conexão com uma URL inválida;
3. **testeDatabaseInvalido()** - método `conectar()` - testa uma conexão com um database inválido;
4. **testeUserInvalido()** - método `conectar()` - testa uma conexão com um user inválido;
5. **testePasswordInvalida()** - método `conectar()` - testa uma conexão com uma senha inválida;
6. **testeCriarStatement()** - método `criarStatement()` - testa um statement válido;
7. **testeStatementSemConectar()** - método `criarStatement()` - testa um statement sem ter criado uma conexão antes;
8. **testSelect()** - método `select()` - testa um select válido;
9. **testSelectSemStatement()** - método `select()` - testa um select sem ter criado um statement antes;
10. **testSelectTabelaInvalida()** - método `select()` - testa um select com uma tabela inválida;
11. **testSelectQtdInvalida()** - método `select()` - testa um select com uma quantidade inválida;
12. **testePercorrerResultado()** - método `percorrerResultados()` - testa percorrer os resultados;
13. **testePercorrerSemResultSet()** - método `percorrerResultados()` - testa percorrer os resultados sem ter criado um resultSet antes;
14. **testeEncerrar()** - método `encerrar()` - testa encerrar conexões já criadas;
15. **testeEncerrarSemCriacao()** - método `encerrar()` - testa encerrar sem ter conexões criadas;


# :busts_in_silhouette: Colaboradores
[Virgínia Letícia](https://github.com/VLAfonso)
