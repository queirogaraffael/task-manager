package com.gerenciador_de_tarefas.MongoDB;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class MongoDBConnection {

	private static final String CONNECTION_STRING = "mongodb://localhost:27017";
	private static MongoClient mongoClient;

	private MongoDBConnection() {
	}

	public static MongoClient getMongoClient() {
		if (mongoClient == null) {
			try {
				mongoClient = MongoClients.create(new ConnectionString(CONNECTION_STRING));
			} catch (MongoClientException erro) {
				System.err.println("Erro ao conectar ao MongoDB: " + erro.getMessage());
			}
		}
		return mongoClient;
	}

	public static void close() {
		if (mongoClient != null) {
			try {
				mongoClient.close();
				mongoClient = null;
			} catch (MongoClientException erro) {
				System.err.println("Erro ao fechar conexão com o MongoDB: " + erro.getMessage());
			}
		}
	}
}
