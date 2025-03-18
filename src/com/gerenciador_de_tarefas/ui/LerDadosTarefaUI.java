package com.gerenciador_de_tarefas.ui;

import javax.swing.*;

public class LerDadosTarefaUI {

    public static String lerTituloTarefa() {
        return JOptionPane.showInputDialog("Titulo da tarefa: ");
    }

    public static String lerDescricaoTarefa() {
        return JOptionPane.showInputDialog("Descricao da tarefa: ");
    }

}
