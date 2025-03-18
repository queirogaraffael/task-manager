package com.gerenciador_de_tarefas.ui;

import javax.swing.*;

public class ExibirDTOsUI {

    public static String exibirTarefasDTOsView(Object[] opcoes) {
        Object opcaoSelecionada = JOptionPane.showInputDialog(
                null,
                "Escolha uma tarefa: ",
                "Tarefas",
                JOptionPane.INFORMATION_MESSAGE,
                null,
                opcoes,
                opcoes[0]
        );

        return opcaoSelecionada.toString();
    }
}

