package com.gerenciador_de_tarefas.ui;

import com.gerenciador_de_tarefas.model.entities.Tarefa;
import com.gerenciador_de_tarefas.util.Data;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataUI {

    public static LocalDate solicitarDataValida() {
        Object[] opcoes = { "Sim", "Não" };

        int opcaoData = JOptionPane.showOptionDialog(
                null,
                "Deseja adicionar uma data específica?",
                "Data",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoes,
                opcoes[0]
        );

        if (opcaoData == 0) {
            String dataString = solicitarDataUsuario();
            return LocalDate.parse(dataString, DateTimeFormatter.ofPattern(Tarefa.FORMATO_DATA));
        } else {
            return LocalDate.now();
        }
    }

    private static String solicitarDataUsuario() {
        String dataString;

        do {
            dataString = JOptionPane.showInputDialog("Digite uma data no formato " + Tarefa.FORMATO_DATA);

            if (!Data.isFormatoValido(dataString)) {
                JOptionPane.showMessageDialog(null, "Formato inválido! Por favor, use o formato " + Tarefa.FORMATO_DATA);
            }
        } while (!Data.isFormatoValido(dataString));

        return dataString;
    }
}


