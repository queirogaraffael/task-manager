package com.gerenciador_de_tarefas.ui;

import com.gerenciador_de_tarefas.dtos.TarefaVisualizationDTO;

import java.util.List;

public class SelecionaTarefa {

    public static String selecionaTituloTarefa(List<TarefaVisualizationDTO> tarefas) {
        Object[] opcoes = converterFuncionarioParaArray(tarefas);

        String tarefaSelecionado = ExibirDTOsUI.exibirTarefasDTOsView(opcoes);

        return tarefaSelecionado.split(" - ")[0].trim();
    }


    private static Object[] converterFuncionarioParaArray(List<TarefaVisualizationDTO> tarefaVisualizationDTOS) {
        return tarefaVisualizationDTOS.stream()
                .map(TarefaVisualizationDTO::toString)
                .toArray(Object[]::new);
    }

}
