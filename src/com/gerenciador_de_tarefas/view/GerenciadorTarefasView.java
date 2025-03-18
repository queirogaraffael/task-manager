package com.gerenciador_de_tarefas.view;

import javax.swing.*;

public class GerenciadorTarefasView {

    private static final String MESSAGE = "Escolha uma opcao: ";
    private static final String TITLE = "Gerenciador Tarefas";

    private static final Object[] OPCOES_MENU = {"Adicionar", "Visualizar Tarefa(s)", "Marcar como concluida", "Marca tarefas como concluidas pela data", "Desmarcar como concluida",
            "Modificar", "Remover", "Sair"};

    public static int menuPrincipalView() {
        return JOptionPane.showOptionDialog(
                null,
                MESSAGE,
                TITLE,
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                OPCOES_MENU,
                OPCOES_MENU[0]
        );

    }

}
