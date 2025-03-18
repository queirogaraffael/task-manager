package com.gerenciador_de_tarefas;

import com.gerenciador_de_tarefas.controller.MenuPrincipalTarefaController;
import com.gerenciador_de_tarefas.factory.ApplicationContext;

public class Main {
	public static void main(String[] args) throws Exception {

		ApplicationContext context = new ApplicationContext();
		MenuPrincipalTarefaController controller = context.getMenuPrincipalController();

		controller.exibiMenuPrincipal();
	}
}
