# C214 - Exercício Prático
Repositório destinado ao exercício prático da disciplina C214 - Engenharia de Software.
O objetivo é aprimorar os conhecimentos na criação e manutenção de *pipelines* com GitHub Actions.

# :pencil: Descrição
O projeto realiza uma conexão com um banco de dados MySQL e apresenta as informações presentes nas 5 primeiras linhas do conjunto de dados South German Credit, obtido do [UC Irvine Machine Learning Repository](https://archive.ics.uci.edu/dataset/573/south+german+credit+update).

# :gear: Configuração e Execução
1. Clonar o repositório
```bash
git clone https://github.com/VLAfonso/C214_exercicio_pratico.git
cd C214_teste_unitario
```

2. Criar Banco de Dados  
Instalar MySQL Client
    - Linux:
    ```bash
    sudo apt-get update && sudo apt-get install -y mysql-client
    ```
    - Windows:  
        É necessário instalar pelo site oficial:
        - [MySQL Installer completo](https://dev.mysql.com/downloads/installer/)
        - ou apenas [MySQL Community Server](https://dev.mysql.com/downloads/mysql/).

Importar Schema SQL   
```bash
mysql -h 127.0.0.1 -P 3306 -uroot -p${ROOT_PASSWORD} < statlog-germancredit.sql
```   
> :pushpin: **Notas:**  Substitua *${ROOT_PASSWORD}* pela senha do usuário root configurada no seu MySQL.
    
3. Configurar Credenciais e URL do Banco de Dados

Defina variáveis de ambiente com as credenciais de acesso e a url do banco de dados.

```bash
setx URL "jdbc:mysql://localhost:3306/statlog"
setx USER "usuario"
setx USER_PASSWORD "senha"
```
> :pushpin: **Notas:**  Substitua *usuario* e *senha* pelas credenciais corretas do banco.

4. Abrir Projeto no IntelliJ  
- Abra o IntelliJ
- Vá em File > Open e selecione a pasta do projeto (`ExercicioTesteUnitario`).

5. Instalar Dependências  

No terminal do projeto:
```bash
cd ExercicioTesteUnitario
mvn package
```

6. Executar o Projeto

- No IntelliJ, abra a classe `Main.java` localizada em `src\main\java\org\example\Main.java`
- Clique no botão `Run` para iniciar a aplicação.

# :white_check_mark: Testes Unitários
Foram realizados 20 testes unitários neste projeto, sendo eles:
| # | Teste | Método | Descrição |
|----|------|---------|-----------|
| 1 | **testeConexaoValida()** | método `conectar()` | testa uma conexão válida; |
| 2 | **testeURLInvalida()** | método `conectar()` | testa uma conexão com uma URL inválida; |
| 3 | **testeDatabaseInvalido()** | método `conectar()` | testa uma conexão com um database inválido; |
| 4 | **testeUserInvalido()** | método `conectar()` | testa uma conexão com um user inválido; |
| 5 | **testePasswordInvalida()**  | método `conectar()`  | testa uma conexão com uma senha inválida; |
| 6 | **testeCriarStatement()**  | método `criarStatement()`  | testa um statement válido; |
| 7 | **testeStatementSemConectar()**  | método `criarStatement()`  | testa um statement sem ter criado uma conexão antes; |
| 8 | **testSelect()**  | método `select()`  | testa um select válido; |
| 9 | **testSelectSemStatement()**  | método `select()`  | testa um select sem ter criado um statement antes; |
| 10 | **testSelectTabelaInvalida()**  | método `select()`  | testa um select com uma tabela inválida; |
| 11 | **testSelectQtdInvalida()**  | método `select()`  | testa um select com uma quantidade inválida; |
| 12 | **testSelectQtdNull()**  | método `select()`  | testa um select com uma quantidade `null`; |
| 13 | **testOkayAnalyzeByCredit()**  | método `analyzeByCredit()`  | testa uma seleção de dados dos indvíduos com maiores créditos válida; |
| 14 | **testWrongVariableAnalyzeByCredit()**  | método `analyzeByCredit()`  | testa uma seleção de dados dos indvíduos com maiores créditos com variável inválida; |
| 15 | **testNoStatementAnalyzeByCredit()**  | método `analyzeByCredit()`  | testa uma seleção de dados dos indvíduos com maiores créditos sem ter criado um statement; |
| 16 | **testePercorrerResultado()**  | método `percorrerResultados()`  | testa percorrer os resultados; |
| 17 | **testePercorrerSemResultSet()**  | método `percorrerResultados()`  | testa percorrer os resultados sem ter criado um resultSet antes; |
| 18 | **testePercorrerSemStatementSet()**  | método `percorrerResultados()`  | testa percorrer os resultados sem ter criado um statement antes; |
| 19 | **testeEncerrar()**  | método `encerrar()`  | testa encerrar conexões já criadas; |
| 20 | **testeEncerrarSemCriacao()**  | método `encerrar()`  | testa encerrar sem ter conexões criadas; |


# :robot: Integração Contínua (CI)

Esse projeto possui um pipeline, por meio do GitHub Actions, configurado para a automação de configuração do banco de dados, build, testes, verificação de qualidade do código e notificações. Seu acionamento é feito ao realizar `push` ou `pull request` na branch `main`.

Etapas:
- **setup-db** - configura o banco de dados, inicializando o MySQL e importando o schema `statlog-germancredit.sql`
- **build** - realiza o build do projeto com o Maven
- **test** - executa os testes unitários e gera um relatório de testes
- **quality** - verifica a qualidade do código e gera um relatório detalhando violações
- **notification** - envia uma notificação por e-mail informando que o pipeline rodou corretamente
> :pushpin: **Notas:**  As etapas **test** e **quality** são executadas em paralelo.


# :busts_in_silhouette: Colaboradores
[Lanna Correia e Silva](https://github.com/LannaCeS)  
[Virgínia Letícia](https://github.com/VLAfonso)

## :scroll: Licença
Este projeto está licenciado sob a MIT License.
