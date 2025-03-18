package com.gerenciador_de_tarefas.view;

import com.gerenciador_de_tarefas.model.entities.Tarefa;
import com.gerenciador_de_tarefas.util.Data;

import javax.swing.*;
import java.time.LocalDate;

public class DataViews {

    public static void solicitarDataValida(Tarefa tarefa) {
        Object[] opcoes = { "Sim", "Nao" };

        int opcaoData = JOptionPane.showOptionDialog(null, "Deseja adicionar uma data especifica ?", "Data",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

        if (opcaoData == 0) {
            String dataString = JOptionPane.showInputDialog("Digite uma data no formato dd/MM/yyyy");
            boolean formatoAprovado = Data.verificaFormatoData(dataString);

            while (!formatoAprovado) {
                JOptionPane.showMessageDialog(null, "Formato da data incorreto. Tente novamente!");
                dataString = JOptionPane.showInputDialog("Digite uma data no formato dd/MM/yyyy");
                formatoAprovado = Data.verificaFormatoData(dataString);
            }
            tarefa.setData(dataString);

        } else {
            tarefa.setData(LocalDate.now().format(formatter));
        }
    }
}
