package com.gerenciador_de_tarefas.model.dao;

import com.gerenciador_de_tarefas.MongoDB.MongoDBConnection;
import com.gerenciador_de_tarefas.model.dao.imp.TarefaDaoMongoDB;

public class DaoFactory {

	public static TarefaDao createMedicoDao() throws Exception {

		return new TarefaDaoMongoDB(MongoDBConnection.getMongoClient());

	}

}
