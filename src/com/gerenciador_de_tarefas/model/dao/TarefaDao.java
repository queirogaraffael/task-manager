package com.gerenciador_de_tarefas.model.dao;

import com.gerenciador_de_tarefas.dtos.TarefaResponseDTO;
import com.gerenciador_de_tarefas.dtos.TarefaVisualizationDTO;
import com.gerenciador_de_tarefas.model.entities.Tarefa;

import java.util.List;

public interface TarefaDao {

	void insereTarefa(Tarefa tarefa) throws Exception;

	boolean haTarefaComMesmoTitulo(String tituloTarefa);

	List<TarefaVisualizationDTO> retornaTarefasExecutadas();

	List<TarefaVisualizationDTO> retornaTarefaNaoExecutadas();

	List<TarefaVisualizationDTO> retornaTarefas();

	TarefaResponseDTO retornaTarefaPeloTitulo(String titulo);

	/*
	String retornaIdTituloStatusDataTarefas() throws Exception;

	Tarefa retornaTarefaPeloId(String id) throws Exception;

	List<Tarefa> retornaTarefasNaoConcluidas() throws Exception;

	List<Tarefa> retornaTarefasConcluidas() throws Exception;

	void marcaTarefaComoConcluidaPeloId(String id) throws Exception;

	void dermarcaTarefaComoConcluidaPeloId(String id) throws Exception;

	void modificaDescricaoTarefaPeloId(Tarefa tarefa, String novaDescricao) throws Exception;

	void modificaDataTarefaPeloId(Tarefa tarefa, String novaData) throws Exception;

	void deleteTarefa(String id) throws Exception;

	Long retornaNumeroDeTarefas() throws Exception;

	boolean verificaSeJaTemId(String id) throws Exception;

	void marcaComoConcluidaPelaData() throws Exception;

	 */
}
