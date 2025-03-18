package com.gerenciador_de_tarefas.ui;

import javax.swing.*;

public class GerenciadorTarefasUI {

    public static String exibirMenuGerenciadorDeTarefas() {
        Object[] opcoesMenu = {"Adicionar", "Visualizar Tarefa(s)", "Marcar como concluida",
                "Marcar tarefas como concluídas pela data", "Desmarcar como concluída",
                "Modificar", "Remover", "Sair"};

        Object opcaoSelecionada = JOptionPane.showInputDialog(null, "Escolha uma opcao", "Gerenciador de Tarefas",
                JOptionPane.INFORMATION_MESSAGE, null, opcoesMenu, opcoesMenu[0]);

        if (opcaoSelecionada != null) {
            return opcaoSelecionada.toString();
        }
        return "";
    }


}
