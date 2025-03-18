package com.gerenciador_de_tarefas.model.dao;

import java.util.List;

import com.gerenciador_de_tarefas.model.entities.Tarefa;

public interface TarefaDao {

	void insereTarefa(String id, Tarefa tarefa) throws Exception;

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
}
