package com.gerenciador_de_tarefas.ui;

import javax.swing.*;

public class AlertasTarefa {

    public static void alertaTarefaComMesmoTitulo() {
        JOptionPane.showMessageDialog(null, "Ja existe uma tarefa com esse titulo");
    }

    public static void alertaTarefaAdicionada() {
        JOptionPane.showMessageDialog(null, "Tarefa adicionada com sucesso");
    }

    public static void alertaErro(Exception erro) {
        JOptionPane.showMessageDialog(null, "Erro: " + erro.getMessage());
    }

    public static void alertaListaVazia() {
        JOptionPane.showMessageDialog(null, "Lista vazia");
    }

    public static void alertaTarefaConcluida() {
        JOptionPane.showMessageDialog(null, "Tarefa concluida com sucesso");
    }

    public static void alertaTarefaDesmarcada() {
        JOptionPane.showMessageDialog(null, "Tarefa desmarcada com sucesso");
    }

    public static void alertaTarefaRemovida() {
        JOptionPane.showMessageDialog(null, "Tarefa removida com sucesso");
    }

    public static void alertaTarefaModificada() {
        JOptionPane.showMessageDialog(null, "Tarefa modificada com sucesso");
    }
}
