package com.gerenciador_de_tarefas.model.dao;

import com.gerenciador_de_tarefas.dtos.TarefaResponseDTO;
import com.gerenciador_de_tarefas.dtos.TarefaVisualizationDTO;
import com.gerenciador_de_tarefas.model.entities.Tarefa;

import java.time.LocalDate;
import java.util.List;

public interface TarefaDao {

	void insereTarefa(Tarefa tarefa) throws Exception;

	boolean haTarefaComMesmoTitulo(String tituloTarefa);

	List<TarefaVisualizationDTO> retornaTarefasExecutadas();

	List<TarefaVisualizationDTO> retornaTarefaNaoExecutadas();

	List<TarefaVisualizationDTO> retornaTarefas();

	TarefaResponseDTO retornaTarefaPeloTitulo(String titulo);

	void marcaTarefaComoConcluidaPeloTitulo(String titulo) throws Exception;

	void desmarcaTarefaComoConcluidaPeloTitulo(String tituloTarefa);

	void deleteTarefa(String titulo) throws Exception;

	void modificaDescricaoTarefaPeloTitulo(String tituloTarefa, String novaDescricao) throws Exception;

	void modificaDataTarefaPeloTitulo(String tituloTarefa, LocalDate novaData) throws Exception;
}
