package com.gerenciador_de_tarefas.service;

import com.gerenciador_de_tarefas.dtos.TarefaCreateDTO;
import com.gerenciador_de_tarefas.dtos.TarefaResponseDTO;
import com.gerenciador_de_tarefas.dtos.TarefaVisualizationDTO;
import com.gerenciador_de_tarefas.model.dao.TarefaDao;
import com.gerenciador_de_tarefas.model.entities.Tarefa;

import java.time.LocalDate;
import java.util.List;

public class TarefaService {

    private final TarefaDao tarefaDao;

    public TarefaService(TarefaDao tarefaDao) {
        this.tarefaDao = tarefaDao;
    }

    public void insereTarefa(TarefaCreateDTO tarefaCreateDTO) throws Exception {
        Tarefa tarefa = new Tarefa(tarefaCreateDTO.getTitulo(), tarefaCreateDTO.getDescricao(), tarefaCreateDTO.getDataCriacao(),tarefaCreateDTO.getDataConclusao(), tarefaCreateDTO.getStatusTarefa());
        tarefaDao.insereTarefa(tarefa);
    }

    public boolean haTarefaComMesmoTitulo(String tituloTarefa) {
        return tarefaDao.haTarefaComMesmoTitulo(tituloTarefa);
    }

    public List<TarefaVisualizationDTO> retornaTarefasExecutadas() {
        return tarefaDao.retornaTarefasExecutadas();
    }

    public List<TarefaVisualizationDTO> retornaTarefasNaoExecutadas() {
        return tarefaDao.retornaTarefaNaoExecutadas();
    }

    public List<TarefaVisualizationDTO> retornaTarefas() {
        return tarefaDao.retornaTarefas();
    }

    public TarefaResponseDTO retornaTarefaPeloTitulo(String titulo) {
        return tarefaDao.retornaTarefaPeloTitulo(titulo);
    }

    public void marcaTarefaComoConcluidaPeloTitulo(String titulo) throws Exception {
        tarefaDao.marcaTarefaComoConcluidaPeloTitulo(titulo);
    }

    public void desmarcaTarefaComoConcluidaPeloTitulo(String tituloTarefa) {
        tarefaDao.desmarcaTarefaComoConcluidaPeloTitulo(tituloTarefa);
    }

    public void deleteTarefa(String titulo) throws Exception {
    	tarefaDao.deleteTarefa(titulo);
    }

    public void modificaDescricaoTarefaPeloTitulo(String tituloTarefa, String novaDescricao) throws Exception {
        tarefaDao.modificaDescricaoTarefaPeloTitulo(tituloTarefa, novaDescricao);
    }

    public void modificaDataTarefaPeloTitulo(String tituloTarefa, LocalDate novaData) throws Exception {
        tarefaDao.modificaDataTarefaPeloTitulo(tituloTarefa, novaData);
    }
}
