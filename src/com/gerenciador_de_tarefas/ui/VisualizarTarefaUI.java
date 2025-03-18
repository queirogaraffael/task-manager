package com.gerenciador_de_tarefas.ui;

import com.gerenciador_de_tarefas.dtos.TarefaResponseDTO;

import javax.swing.*;

public class VisualizarTarefaUI {

    private static Object[] opcoesVisualizar = {"Concluidas", "Nao Concluidas", "Todas", "Voltar"};

    public static int exibirMenuVisualizarTarefas() {
        return JOptionPane.showOptionDialog(null, "Escolha uma opcao", "Visualizar Tarefas",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoesVisualizar, opcoesVisualizar[0]);
    }

    public static void exibirTarefaSelecionada(TarefaResponseDTO tarefa) {
        String mensagem = "Detalhes da Tarefa Selecionada:\n\n" + tarefa;
        JOptionPane.showMessageDialog(null, mensagem, "Tarefa Selecionada", JOptionPane.INFORMATION_MESSAGE);
    }


}
