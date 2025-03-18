package com.gerenciador_de_tarefas.service;

import com.gerenciador_de_tarefas.dtos.TarefaCreateDTO;
import com.gerenciador_de_tarefas.model.dao.TarefaDao;
import com.gerenciador_de_tarefas.model.entities.Tarefa;

public class TarefaService {

    private final TarefaDao tarefaDao;

    public TarefaService(TarefaDao tarefaDao) {
        this.tarefaDao = tarefaDao;
    }

    public void insereTarefa(TarefaCreateDTO tarefaCreateDTO) throws Exception {
        Tarefa tarefa = new Tarefa(tarefaCreateDTO.getTitulo(), tarefaCreateDTO.getDescricao(), tarefaCreateDTO.getData(), tarefaCreateDTO.getStatusTarefa());
        tarefaDao.insereTarefa(tarefa);
    }

    public boolean haTarefaComMesmoTitulo(String tituloTarefa) {
        return tarefaDao.haTarefaComMesmoTitulo(tituloTarefa);
    }





}
