package com.gerenciador_de_tarefas.factory;

import com.gerenciador_de_tarefas.MongoDB.MongoDBConnection;
import com.gerenciador_de_tarefas.model.dao.TarefaDao;
import com.gerenciador_de_tarefas.model.dao.imp.TarefaDaoMongoDB;
import com.mongodb.client.MongoClient;

public class DaoFactory {

    public static TarefaDao createTarefaDao() throws Exception {
        return new TarefaDaoMongoDB(MongoDBConnection.getMongoClient());
    }

}