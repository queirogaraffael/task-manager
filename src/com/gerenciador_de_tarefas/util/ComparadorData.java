package com.gerenciador_de_tarefas.util;

import com.gerenciador_de_tarefas.dtos.TarefaVisualizationDTO;

import java.util.Comparator;

public class ComparadorData implements Comparator<TarefaVisualizationDTO> {

    @Override
    public int compare(TarefaVisualizationDTO o1, TarefaVisualizationDTO o2) {
        if (o1.getDataConclusao() == null && o2.getDataConclusao() == null) {
            return 0;
        } else if (o1.getDataConclusao() == null) {
            return 1;
        } else if (o2.getDataConclusao() == null) {
            return -1;
        } else {
            return o1.getDataConclusao().compareTo(o2.getDataConclusao());
        }
    }
}
