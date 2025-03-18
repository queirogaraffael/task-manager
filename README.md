# Gerenciador de Tarefas
O Gerenciador de Tarefas é um aplicativo que permite adicionar, visualizar, modificar, marcar como concluídas e remover tarefas. O projeto utiliza o padrão MVC (Model-View-Controller) e DAO (Data Access Object) para manipulação de dados em um banco de dados MongoDB.

## Pré-requisitos

### Antes de utilizar o Gerenciador de Tarefas, certifique-se de ter instalado:
* Java Development Kit (JDK) para compilar e executar o código Java.
* Um servidor MongoDB configurado e em execução para armazenar as tarefas.

### Instalação, Execução e Configuração do Banco de Dados
* Clone este repositório ou baixe o código-fonte do Gerenciador de Tarefas.
* Certifique-se de ter o JDK instalado e configurado corretamente em seu sistema.
* Inicie o servidor MongoDB.
* Abra a classe MongoDBConnection localizada no pacote com.gerenciador_de_tarefas.MongoDB. Dentro desta classe, defina a URL de conexão com o MongoDB. No código fornecido, a URL de conexão está definida como mongodb://localhost:27017. Certifique-se de que esta URL corresponda à localização do seu servidor MongoDB.
* Para acessar o banco de dados e realizar operações, a classe TarefaDaoMongoDB localizada no pacote com.gerenciador_de_tarefas.model.dao.imp é responsável por interagir com o banco de dados MongoDB. Certifique-se de que os métodos nesta classe estejam configurados corretamente para atender às suas necessidades.

## Utilização
Ao iniciar o programa, uma interface de linha de comando será exibida com as seguintes opções de menu:
1. Adicionar Tarefa: Permite adicionar uma nova tarefa ao sistema.
2. Visualizar Resumo de Tarefas: Exibe um resumo de todas as tarefas cadastradas.
3. Retornar Tarefa pelo ID: Permite buscar uma tarefa específica pelo seu ID.
4. Visualizar Tarefas Não Concluídas: Exibe uma lista de todas as tarefas pendentes.
5. Visualizar Tarefas Concluídas: Exibe uma lista de todas as tarefas concluídas.
6. Marcar Tarefa como Concluída: Permite marcar uma tarefa como concluída.
7. Marcar Tarefas como Concluídas pela Data: Permite marcar tarefas como concluídas com base na data.
8. Desmarcar Tarefa como Concluída: Permite desmarcar uma tarefa previamente concluída.
9. Modificar Tarefa: Permite modificar os detalhes de uma tarefa existente.
10. Remover Tarefa: Remove uma tarefa do sistema.
11. Sair: Encerra o programa.

## Arquitetura
O projeto segue a arquitetura MVC:
* Model: Representado pelas classes de entidade (Tarefa) e pelas interfaces e classes de DAO (TarefaDao, TarefaDaoMongoDB).
* View: Representado pela classe GerenciadorTarefasView que lida com a interface do usuário.
* Controller: Representado pela classe MenuPrincipalTarefaController que contém a lógica de controle.

## Contribuição
Contribuições são bem-vindas! Sinta-se à vontade para enviar pull requests, relatar problemas ou propor novos recursos.

## Licença
Este projeto está licenciado sob a [Licença MIT](https://github.com/queirogaraffael/task-manager/blob/main/LICENSE).
