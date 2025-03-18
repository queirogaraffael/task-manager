package com.gerenciador_de_tarefas;

import com.gerenciador_de_tarefas.controller.MenuPrincipalTarefaController;

public class Main {
	public static void main(String[] args) throws Exception {

		MenuPrincipalTarefaController controller = new MenuPrincipalTarefaController();

		controller.exibiMenuPrincipal();
	}
}
