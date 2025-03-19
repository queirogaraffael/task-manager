# Gerenciador de Tarefas

O **Gerenciador de Tarefas** é um aplicativo que permite gerenciar tarefas de forma eficiente. Ele possibilita adicionar, visualizar, modificar, marcar como concluídas e remover tarefas. O projeto segue os padrões **MVC (Model-View-Controller)** e **DAO (Data Access Object)** para manipulação de dados armazenados em um banco de dados **MongoDB**, utilizando também o padrão **Singleton** para a gestão da conexão.

---

## 📌 Pré-requisitos

Antes de utilizar o **Gerenciador de Tarefas**, certifique-se de ter instalado:

- **Java Development Kit (JDK)** para compilar e executar o código Java.
- **MongoDB** configurado e em execução para armazenar as tarefas.

---

## 📥 Instalação e Configuração

1. Clone este repositório ou baixe o código-fonte do **Gerenciador de Tarefas**.
2. Certifique-se de ter o **JDK** instalado e corretamente configurado no sistema.
3. Inicie o servidor **MongoDB**.
4. Configure a conexão com o banco de dados:
   - Acesse a classe `MongoDBConnection` localizada no pacote `com.gerenciador_de_tarefas.MongoDB`.
   - Defina a URL de conexão com o MongoDB. O valor padrão é `mongodb://localhost:27017`. Verifique se corresponde à localização do seu servidor MongoDB.
5. A classe `TarefaDaoMongoDB`, localizada no pacote `com.gerenciador_de_tarefas.model.dao.imp`, gerencia as interações com o banco de dados. Certifique-se de que os métodos estão configurados corretamente conforme suas necessidades.

---

## 🚀 Utilização

Ao iniciar o programa, será exibida uma interface de linha de comando com as seguintes opções:

1. **Adicionar Tarefa** – Permite adicionar uma nova tarefa ao sistema.
2. **Visualizar Resumo de Tarefas** – Exibe um resumo das tarefas cadastradas, organizadas por data.
3. **Marcar Tarefa como Concluída** – Permite marcar uma tarefa como concluída.
4. **Desmarcar Tarefa como Concluída** – Permite desmarcar uma tarefa previamente concluída.
5. **Modificar Tarefa** – Permite modificar os detalhes de uma tarefa existente.
6. **Remover Tarefa** – Remove uma tarefa do sistema.
7. **Sair** – Encerra o programa.

---

## 🛠 Tecnologias Utilizadas

- **Java**
- **Swing** (Interface Gráfica)
- **MongoDB**

---

## 📌 Padrões de Projeto Utilizados

- **MVC (Model-View-Controller)** – Organização do código em camadas.
- **DAO (Data Access Object)** – Facilita o acesso e manipulação dos dados.
- **Singleton** – Gerenciamento eficiente da conexão com o MongoDB.

---

## 🏗 Arquitetura

O projeto segue a arquitetura **MVC**:

- **Model:** Representado pelas classes de entidade (`Tarefa`) e pelas interfaces e classes DAO (`TarefaDao`, `TarefaDaoMongoDB`).
- **View:** Representado pela classe `GerenciadorTarefasView`, responsável pela interface com o usuário.
- **Controller:** Representado pela classe `MenuPrincipalTarefaController`, que contém a lógica de controle.

---

## 🤝 Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para enviar pull requests, relatar problemas ou propor novos recursos.

---

## 📜 Licença

Este projeto está licenciado sob a [Licença MIT](https://github.com/queirogaraffael/task-manager/blob/main/LICENSE).
