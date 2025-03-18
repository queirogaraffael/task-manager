package com.gerenciador_de_tarefas.factory;

import com.gerenciador_de_tarefas.model.dao.TarefaDao;
import com.gerenciador_de_tarefas.service.TarefaService;

public class ControllerFactory {

    private TarefaService tarefaService;

    public ControllerFactory() {
    }

    public TarefaService criaTarefaService() throws Exception {
        if(tarefaService == null){
            tarefaService = new TarefaService(DaoFactory.createTarefaDao());
        }

        return tarefaService;
    }

}
