package com.gerenciador_de_tarefas.service;

import com.gerenciador_de_tarefas.model.dao.TarefaDao;

public class TarefaService {

    private final TarefaDao tarefaDao;

    public TarefaService(TarefaDao tarefaDao) {
        this.tarefaDao = tarefaDao;
    }



}
