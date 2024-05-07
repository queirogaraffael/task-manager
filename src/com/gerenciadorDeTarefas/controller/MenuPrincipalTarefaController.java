package com.gerenciadorDeTarefas.controller;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;

import com.gerenciadorDeTarefas.constantes.ConstantesMenuPrincipal;
import com.gerenciadorDeTarefas.entities.Tarefa;
import com.gerenciadorDeTarefas.util.ManipulacaoData;
import com.gerenciadorDeTarefas.view.GerenciadorTarefasView;

public class MenuPrincipalTarefaController {

	private Set<Tarefa> tarefas;

	public MenuPrincipalTarefaController() {
		tarefas = new HashSet<>();
	}

	public void exibirMenuPrincipal() {

		int opcaoMenuPrincipal = 0;

		do {

			try {

				opcaoMenuPrincipal = Integer
						.parseInt(JOptionPane.showInputDialog(GerenciadorTarefasView.menuPrincipalView()));

				switch (opcaoMenuPrincipal) {

				case (ConstantesMenuPrincipal.ADICIONAR):
					adicionaTarefa(tarefas);
					break;

				case (ConstantesMenuPrincipal.VISUALIZARCONCLUIDA):
					// implementar
					break;

				case (ConstantesMenuPrincipal.VISUALIZARNAOCONCLUIDA):
					// implementar
					break;

				case (ConstantesMenuPrincipal.MARCARCONCLUIDA):
					// implementar
					break;

				case (ConstantesMenuPrincipal.DESMARCARCONCLUIDA):
					// implementar
					break;

				case (ConstantesMenuPrincipal.MODIFICAR):
					// implementar
					break;

				case (ConstantesMenuPrincipal.ORDERNAR):
					// implementar
					break;

				case (ConstantesMenuPrincipal.REMOVER):
					// implementar
					break;

				case (ConstantesMenuPrincipal.SAIR):
					// implementar
					break;

				}

			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Entre com um valor valor numerico associado a uma opcao!");
			}

		} while (opcaoMenuPrincipal != ConstantesMenuPrincipal.SAIR);

	}

	private void adicionaTarefa(Set<Tarefa> tarefas2) {

		String titulo = JOptionPane.showInputDialog("Titulo da tarefa: ");
		String descricao = JOptionPane.showInputDialog("Descricao da tarefa: ");

		Object[] opcoes = { "Sim", "Nao" };

		int opcaoData = JOptionPane.showOptionDialog(null, "Deseja adicionar data a tarefa ?\n1. Sim\n2. Nao", "Data",
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

		Tarefa tarefa = new Tarefa();
		tarefa.setTitulo(titulo);
		tarefa.setDescricao(descricao);
		tarefa.setStatusConcluida(false);

		if (opcaoData == 1) {
			String formatoData = "dd/MM/yyyy";
			String dataString = JOptionPane.showInputDialog("Digite uma data no formato dd/MM/yyyy");
			boolean formatoAprovado = ManipulacaoData.verificaFormatoData(dataString, formatoData);

			while (!formatoAprovado) {
				dataString = JOptionPane.showInputDialog("Digite uma data no formato dd/MM/yyyy");
			}
			LocalDate localDate = ManipulacaoData.retornaLocalDate(dataString);
			tarefa.setData(localDate);

		}

		tarefas2.add(tarefa);

	}

}