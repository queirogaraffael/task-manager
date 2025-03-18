package com.gerenciador_de_tarefas.util;

import java.util.Comparator;

import com.gerenciador_de_tarefas.model.entities.Tarefa;

public class ComparadorData implements Comparator<Tarefa> {

	@Override
	public int compare(Tarefa o1, Tarefa o2) {
		if (o1.getData() == null && o2.getData() == null) {
			return 0;
		} else if (o1.getData() == null) {
			return 1;
		} else if (o2.getData() == null) {
			return -1;
		} else {
			return o1.getData().compareTo(o2.getData());
		}
	}
}
