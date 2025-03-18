package com.gerenciador_de_tarefas.controller;

import java.util.List;

import javax.swing.JOptionPane;

import com.gerenciador_de_tarefas.MongoDB.MongoDBConnection;
import com.gerenciador_de_tarefas.commons.constantes.ConstantesMenuPrincipal;
import com.gerenciador_de_tarefas.commons.constantes.ConstantesOpcaoModificarTarefa;
import com.gerenciador_de_tarefas.util.ComparadorData;
import com.gerenciador_de_tarefas.util.Data;
import com.gerenciador_de_tarefas.model.dao.DaoFactory;
import com.gerenciador_de_tarefas.model.dao.TarefaDao;
import com.gerenciador_de_tarefas.model.entities.Tarefa;
import com.gerenciador_de_tarefas.ui.GerenciadorTarefasUI;

public class MenuPrincipalTarefaController {

	private TarefaDao tarefaDao;

	public MenuPrincipalTarefaController() throws Exception {
		this.tarefaDao = DaoFactory.createMedicoDao();

	}

	public void exibiMenuPrincipal() {

		String opcaoMenuPrincipal = "";

		do {

			try {
				opcaoMenuPrincipal = GerenciadorTarefasUI.menuPrincipalView();

				switch (opcaoMenuPrincipal) {

				case (ConstantesMenuPrincipal.ADICIONAR):
					adicionaTarefa();
					break;

				case (ConstantesMenuPrincipal.VISUALIZA_ID_TITULO_DATA_STATUS):
					visualizaIdTituloDataStatusTarefas();
					break;

				case (ConstantesMenuPrincipal.VISUALIZAR_TAREFA_ID):
					visualizaTarefaPeloId();
					break;

				case (ConstantesMenuPrincipal.VISUALIZAR_NAO_CONCLUIDA):
					visualizaTarefasNaoConcluidas();
					break;

				case (ConstantesMenuPrincipal.VISUALIZAR_CONCLUIDA):
					visualizaTarefasConcluidas();
					break;

				case (ConstantesMenuPrincipal.MARCAR_CONCLUIDA):
					marcaTarefaComoConcluida();
					break;

				case (ConstantesMenuPrincipal.MARCAR_CONCLUIDA_PELA_DATA):
					marcaComoConcluidaPelaData();
					break;

				case (ConstantesMenuPrincipal.DESMARCAR_CONCLUIDA):
					desmarcaTarefaComoConcluida();
					break;

				case (ConstantesMenuPrincipal.MODIFICAR):
					modificaTarefa();
					break;

				case (ConstantesMenuPrincipal.REMOVER):
					removeTarefa();
					break;

				case (ConstantesMenuPrincipal.SAIR):
					MongoDBConnection.close();

				}

			} catch (Exception erro) {
				JOptionPane.showMessageDialog(null, "Erro: " + erro.getMessage());
			}

		} while (!opcaoMenuPrincipal.equals(ConstantesMenuPrincipal.SAIR));

	}

	private void adicionaTarefa() throws Exception {

		String idTarefa = JOptionPane.showInputDialog("Digite o ID da tarefa: ");

		if (tarefaDao.verificaSeJaTemId(idTarefa)) {
			JOptionPane.showMessageDialog(null, "Id ja cadastrado. Tente com outro!");
		} else {

			String titulo = JOptionPane.showInputDialog("Titulo da tarefa: ");
			String descricao = JOptionPane.showInputDialog("Descricao da tarefa: ");

			Tarefa tarefa = new Tarefa();
			tarefa.setTitulo(titulo);
			tarefa.setDescricao(descricao);
			tarefa.setStatus(false);

			Data.solicitarDataValida(tarefa);

			tarefaDao.insereTarefa(idTarefa, tarefa);

			JOptionPane.showMessageDialog(null, "Tarefa adicionada com sucesso!");

		}

	}

	private void visualizaIdTituloDataStatusTarefas() throws Exception {

		if (tarefaDao.retornaNumeroDeTarefas() == 0) {
			JOptionPane.showMessageDialog(null, "Adicione primeiro tarefas!");
		} else {
			String relatorio = tarefaDao.retornaIdTituloStatusDataTarefas();

			JOptionPane.showMessageDialog(null, relatorio);
		}

	}

	private void visualizaTarefaPeloId() throws Exception {

		if (tarefaDao.retornaNumeroDeTarefas() == 0) {
			JOptionPane.showMessageDialog(null, "Adicione primeiro tarefas!");
		} else {
			String idTarefa = JOptionPane.showInputDialog("Digite o ID da tarefa: ");

			Tarefa tarefa = tarefaDao.retornaTarefaPeloId(idTarefa);

			if (tarefa == null) {
				JOptionPane.showMessageDialog(null, "Tarefa nao encontrada!");
			} else {

				JOptionPane.showMessageDialog(null, tarefa);

			}
		}

	}

	private void visualizaTarefasNaoConcluidas() throws Exception {

		if (tarefaDao.retornaNumeroDeTarefas() == 0) {
			JOptionPane.showMessageDialog(null, "Adicione primeiro tarefas!");
		} else {
			List<Tarefa> tarefasNaoConcluidas = tarefaDao.retornaTarefasNaoConcluidas();

			if (tarefasNaoConcluidas.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Lista de tarefas nao concluidas vazia.");
			} else {

				tarefasNaoConcluidas.sort(new ComparadorData());

				String relatorioTarefasNaoConcluidas = GeraRelatorioTarefas.geraRelatorioTarefas(tarefasNaoConcluidas);

				JOptionPane.showMessageDialog(null, relatorioTarefasNaoConcluidas);
			}
		}

	}

	private void visualizaTarefasConcluidas() throws Exception {

		if (tarefaDao.retornaNumeroDeTarefas() == 0) {
			JOptionPane.showMessageDialog(null, "Adicione primeiro tarefas!");
		} else {
			List<Tarefa> tarefasConcluidas = tarefaDao.retornaTarefasConcluidas();

			if (tarefasConcluidas.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Lista de tarefas concluidas vazia.");
			} else {
				tarefasConcluidas.sort(new ComparadorData());

				String relatorioTarefasConcluidas = GeraRelatorioTarefas.geraRelatorioTarefas(tarefasConcluidas);

				JOptionPane.showMessageDialog(null, relatorioTarefasConcluidas);
			}
		}

	}

	private void marcaTarefaComoConcluida() throws Exception {

		if (tarefaDao.retornaNumeroDeTarefas() == 0) {
			JOptionPane.showMessageDialog(null, "Adicione primeiro tarefas!");
		} else {
			String idTarefa = JOptionPane.showInputDialog("Digite o ID da tarefa: ");

			Tarefa tarefa = tarefaDao.retornaTarefaPeloId(idTarefa);

			if (tarefa == null) {
				JOptionPane.showMessageDialog(null, "Tarefa nao encontrada!");
			} else {

				tarefaDao.marcaTarefaComoConcluidaPeloId(idTarefa);
				JOptionPane.showMessageDialog(null, "Tarefa marcada como concluida com sucesso!");

			}
		}

	}

	private void marcaComoConcluidaPelaData() throws Exception {

		if (tarefaDao.retornaNumeroDeTarefas() == 0) {
			JOptionPane.showMessageDialog(null, "Adicione primeiro tarefas!");
		} else {
			tarefaDao.marcaComoConcluidaPelaData();
			JOptionPane.showMessageDialog(null, "Tarefas marcadas como concluida com sucesso!");
		}

	}

	private void desmarcaTarefaComoConcluida() throws Exception {

		if (tarefaDao.retornaNumeroDeTarefas() == 0) {
			JOptionPane.showMessageDialog(null, "Adicione primeiro tarefas!");
		} else {
			String idTarefa = JOptionPane.showInputDialog("Digite o ID da tarefa: ");

			Tarefa tarefa = tarefaDao.retornaTarefaPeloId(idTarefa);

			if (tarefa == null) {
				JOptionPane.showMessageDialog(null, "Tarefa nao encontrada!");
			} else {

				tarefaDao.dermarcaTarefaComoConcluidaPeloId(idTarefa);
				JOptionPane.showMessageDialog(null, "Tarefa desmarcada como conluida!");

			}
		}

	}

	public void modificaTarefa() throws Exception {

		if (tarefaDao.retornaNumeroDeTarefas() == 0) {
			JOptionPane.showMessageDialog(null, "Adicione primeiro tarefas para poder modificar!");
		} else {
			Object[] opcoes = { "Descricao", "Data", "Voltar para o menu principal" };

			int opcaoModificar = JOptionPane.showOptionDialog(null, "Deseja modificar qual campo ?", "Modificar",
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

			if (opcaoModificar != ConstantesOpcaoModificarTarefa.VOLTAR) {

				String idTarefa = JOptionPane.showInputDialog("Digite o ID da tarefa: ");

				Tarefa tarefaParaModificacao = tarefaDao.retornaTarefaPeloId(idTarefa);

				if (tarefaParaModificacao != null) {

					if (opcaoModificar == ConstantesOpcaoModificarTarefa.DESCRICAO) {

						String novaDescricao = JOptionPane.showInputDialog("Digite a nova descricao da tarefa: ");
						tarefaDao.modificaDescricaoTarefaPeloId(tarefaParaModificacao, novaDescricao);

					} else if (opcaoModificar == ConstantesOpcaoModificarTarefa.DATA) {
						Data.solicitarDataValida(tarefaParaModificacao);
						tarefaDao.modificaDataTarefaPeloId(tarefaParaModificacao,
								tarefaParaModificacao.getDataString());

					}
					JOptionPane.showMessageDialog(null, "Modificacao feita com sucesso!");

				} else {
					JOptionPane.showMessageDialog(null, "Tarefa nao encontrada para modificacao.");

				}

			}
		}

	}

	private void removeTarefa() throws Exception {

		if (tarefaDao.retornaNumeroDeTarefas() == 0) {
			JOptionPane.showMessageDialog(null, "Adicione primeiro tarefas para poder remover!");
		} else {
			String idTarefaParaRemover = JOptionPane.showInputDialog("Digite o ID da tarefa que voce deseja remover: ");
			tarefaDao.deleteTarefa(idTarefaParaRemover);

			JOptionPane.showMessageDialog(null, "Tarefa removida com sucesso!");
		}

	}

}