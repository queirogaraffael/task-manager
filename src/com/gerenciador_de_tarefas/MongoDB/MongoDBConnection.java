package com.gerenciador_de_tarefas.MongoDB;

import javax.swing.JOptionPane;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class MongoDBConnection {
	private static final String connectionString = "mongodb://localhost:27017";
	private static MongoClient mongoClient;

	public static MongoClient getMongoClient() {
		if (mongoClient == null) {

			try {
				mongoClient = MongoClients.create(new ConnectionString(connectionString));

			} catch (MongoClientException erro) {
				JOptionPane.showMessageDialog(null, "Erro ao tentar se conectar com o servido MongoClient: " + erro);
			}

		}
		return mongoClient;

	}

	public static void close() {
		if (mongoClient != null) {
			try {
				mongoClient.close();
			} catch (MongoClientException erro) {
				JOptionPane.showMessageDialog(null, "Erro ao tentar fechar conexao com o MongoClient: " + erro);
			}

		}
	}

}
