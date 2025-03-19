package com.gerenciador_de_tarefas.controller;

import com.gerenciador_de_tarefas.MongoDB.MongoDBConnection;
import com.gerenciador_de_tarefas.commons.constantes.ConstantesMenuPrincipal;
import com.gerenciador_de_tarefas.dtos.TarefaCreateDTO;
import com.gerenciador_de_tarefas.dtos.TarefaResponseDTO;
import com.gerenciador_de_tarefas.dtos.TarefaVisualizationDTO;
import com.gerenciador_de_tarefas.enums.StatusTarefa;
import com.gerenciador_de_tarefas.service.TarefaService;
import com.gerenciador_de_tarefas.ui.*;
import com.gerenciador_de_tarefas.util.ComparadorData;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MenuPrincipalTarefaController {

    private TarefaService tarefaService;

    public MenuPrincipalTarefaController(TarefaService tarefaService) throws Exception {
        this.tarefaService = tarefaService;
    }

    public void exibiMenuPrincipal() {

        String opcaoMenuPrincipal = "";

        do {

            try {
                opcaoMenuPrincipal = GerenciadorTarefasUI.exibirMenuGerenciadorDeTarefas();

                switch (opcaoMenuPrincipal) {

                    case (ConstantesMenuPrincipal.ADICIONAR):
                        adicionaTarefa();
                        break;

                    case (ConstantesMenuPrincipal.VISUALIZAR_TAREFA):
                        visualizarTarefa();
                        break;

                    case (ConstantesMenuPrincipal.MARCAR_CONCLUIDA):
                        marcarComoConcluida();
                        break;

                    case (ConstantesMenuPrincipal.DESMARCAR_CONCLUIDA):
                        desmarcarComoConcluida();
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
                AlertasTarefa.alertaErro(erro);
            }

        } while (!opcaoMenuPrincipal.equals(ConstantesMenuPrincipal.SAIR));

    }

    private void removeTarefa() throws Exception {
        String tituloTarefa = SelecionaTarefa.selecionaTituloTarefa(tarefaService.retornaTarefas());

        if (tituloTarefa == null) {
            return;
        }

        tarefaService.deleteTarefa(tituloTarefa);

        AlertasTarefa.alertaTarefaRemovida();

    }

    private void modificaTarefa() {
        int opcaoModificar = ModificarTarefaUI.opcaoEditar();

        if (opcaoModificar == 2) {
            return;
        }

        String tituloTarefa = SelecionaTarefa.selecionaTituloTarefa(tarefaService.retornaTarefas());

        if (tituloTarefa == null) {
            return;
        }

        try {
            if (opcaoModificar == 0) {
                String novaDescricao = LerDadosTarefaUI.lerDescricaoTarefa();
                tarefaService.modificaDescricaoTarefaPeloTitulo(tituloTarefa, novaDescricao);
            } else if (opcaoModificar == 1) {
                LocalDate novaData = DataUI.solicitarDataValida();
                tarefaService.modificaDataTarefaPeloTitulo(tituloTarefa, novaData);
            }

            AlertasTarefa.alertaTarefaModificada();
        } catch (Exception e) {
            AlertasTarefa.alertaErro(e);
        }

    }

    private void desmarcarComoConcluida() {
        String tituloTarefa = SelecionaTarefa.selecionaTituloTarefa(tarefaService.retornaTarefasExecutadas());

        try {
            tarefaService.desmarcaTarefaComoConcluidaPeloTitulo(tituloTarefa);
            AlertasTarefa.alertaTarefaDesmarcada();
        } catch (Exception e) {
            AlertasTarefa.alertaErro(e);
        }
    }

    private void adicionaTarefa() throws Exception {

        String titulo = LerDadosTarefaUI.lerTituloTarefa();

        if (tarefaService.haTarefaComMesmoTitulo(titulo)) {
            AlertasTarefa.alertaTarefaComMesmoTitulo();
            return;
        }

        String descricao = LerDadosTarefaUI.lerDescricaoTarefa();

        TarefaCreateDTO tarefa = new TarefaCreateDTO();

        tarefa.setTitulo(titulo);
        tarefa.setDescricao(descricao);

        LocalDate dataConclusao = DataUI.solicitarDataValida();

        tarefa.setDataCriacao(LocalDate.now());
        tarefa.setDataConclusao(dataConclusao);

        tarefa.setStatusTarefa(StatusTarefa.NAO_EXECUTADA);

        tarefaService.insereTarefa(tarefa);

        AlertasTarefa.alertaTarefaAdicionada();


    }

    private void visualizarTarefa() throws Exception {
        int opcaoVisualizarTarefa = VisualizarTarefaUI.exibirMenuVisualizarTarefas();
        List<TarefaVisualizationDTO> tarefas = new ArrayList<>();

        switch (opcaoVisualizarTarefa) {
            case 0:
                tarefas = tarefaService.retornaTarefasExecutadas();
                break;
            case 1:
                tarefas = tarefaService.retornaTarefasNaoExecutadas();
                break;
            case 2:
                tarefas = tarefaService.retornaTarefas();
                break;
            case 3:
                return;
        }

        if (tarefas.isEmpty()) {
            AlertasTarefa.alertaListaVazia();
            return;
        }

        tarefas.sort(new ComparadorData());

        String tituloTarefaSelecionada = SelecionaTarefa.selecionaTituloTarefa(tarefas);
        TarefaResponseDTO tarefaSelecionada = tarefaService.retornaTarefaPeloTitulo(tituloTarefaSelecionada);


        VisualizarTarefaUI.exibirTarefaSelecionada(tarefaSelecionada);

    }


    private void marcarComoConcluida() {
        String tituloTarefa = SelecionaTarefa.selecionaTituloTarefa(tarefaService.retornaTarefasNaoExecutadas());

            try {
                tarefaService.marcaTarefaComoConcluidaPeloTitulo(tituloTarefa);
                AlertasTarefa.alertaTarefaConcluida();
            } catch (Exception e) {
                AlertasTarefa.alertaErro(e);
            }


    }




    /*


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
            Object[] opcoes = {"Descricao", "Data", "Voltar para o menu principal"};

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

     */

}