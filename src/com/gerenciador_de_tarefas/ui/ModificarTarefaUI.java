package com.gerenciador_de_tarefas.ui;

import javax.swing.*;

public class ModificarTarefaUI {

    private static Object[] opcoes = {"Descricao", "Data", "Voltar"};

    public static int opcaoEditar() {
        return JOptionPane.showOptionDialog(null, "Escolha uma opcao para modificar: ", "Modificar",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
    }
}