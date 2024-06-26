package com.gerenciadorDeTarefas.view;

import javax.swing.JOptionPane;

public class GerenciadorTarefasView {

	public static String menuPrincipalView() {

		Object[] opcoesMenu = { "Adicionar Tarefa", "Visualizar resumo tarefas", "Retornar tarefa pelo ID","Visualizar Tarefas nao concluidas",
				"Visualizar tarefas concluidas", "Marcar tarefa como concluida", "Marca tarefas como concluidas pela data" ,"Desmarcar tarefa como concluida",
				"Modificar tarefa", "Remover Tarefa", "Sair" };

		Object opcaoSelecionada = JOptionPane.showInputDialog(null, "Escolha uma opcao", "Mene Principal",
				JOptionPane.INFORMATION_MESSAGE, null, opcoesMenu, opcoesMenu[0]);

		if (opcaoSelecionada != null) {
			return opcaoSelecionada.toString();
		} else {
			return "";
		}

	}

}
