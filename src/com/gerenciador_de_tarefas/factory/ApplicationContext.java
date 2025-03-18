package com.gerenciador_de_tarefas.factory;

import com.gerenciador_de_tarefas.controller.MenuPrincipalTarefaController;

public class ApplicationContext {

    private final ControllerFactory controllerFactory;

    public ApplicationContext() {
        this.controllerFactory = new ControllerFactory();
    }

    public MenuPrincipalTarefaController getMenuPrincipalController() throws Exception {
        return new MenuPrincipalTarefaController(controllerFactory.criaTarefaService());
    }
}
