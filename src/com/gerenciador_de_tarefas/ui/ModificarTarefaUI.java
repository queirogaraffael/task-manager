package com.gerenciador_de_tarefas.ui;

import javax.swing.*;

public class ModificarTarefaUI {

    private static Object[] opcoesModificar = {"Descricao", "Data", "Voltar"};

    public static int opcaoEditar() {
        return JOptionPane.showOptionDialog(null, "Escolha uma opcao para modificar: ", "Modificar",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoesModificar, opcoesModificar[0]);
    }
}