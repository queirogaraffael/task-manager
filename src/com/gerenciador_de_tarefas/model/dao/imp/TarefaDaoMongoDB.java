package com.gerenciador_de_tarefas.model.dao.imp;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.gerenciador_de_tarefas.util.Data;
import com.gerenciador_de_tarefas.model.dao.TarefaDao;
import com.gerenciador_de_tarefas.model.entities.Tarefa;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class TarefaDaoMongoDB implements TarefaDao {
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private final MongoCollection<Document> collection;

	public TarefaDaoMongoDB(MongoClient mongoClient) throws Exception {
		MongoDatabase database = mongoClient.getDatabase("app_tarefas");
		this.collection = database.getCollection("tarefas");
	}

	@Override
	public void insereTarefa(String id, Tarefa tarefa) throws Exception {
		Document doc = new Document("_id", id).append("titulo", tarefa.getTitulo())
				.append("descricao", tarefa.getDescricao()).append("data", tarefa.getData().format(formatter))
				.append("status", tarefa.getStatus());
		collection.insertOne(doc);
	}

	@Override
	public String retornaIdTituloStatusDataTarefas() throws Exception {
		StringBuilder sb = new StringBuilder();

		for (Document doc : collection.find()) {

			Tarefa tarefa = new Tarefa(doc.getString("_id"), doc.getString("titulo"), null, doc.getString("data"),
					doc.getBoolean("status"));

			sb.append("Id: " + tarefa.getId() + ", Titulo: " + tarefa.getTitulo() + ", Data: "
					+ tarefa.getData().format(formatter) + ", Status: " + tarefa.retornaStatusFormatado()).append("\n");

		}
		return sb.toString();

	}

	@Override
	public Tarefa retornaTarefaPeloId(String id) throws Exception {

		for (Document doc : collection.find()) {

			if (doc.getString("_id").equals(id)) {

				Tarefa tarefa = new Tarefa(doc.getString("_id"), doc.getString("titulo"), doc.getString("descricao"),
						doc.getString("data"), doc.getBoolean("status"));

				return tarefa;
			}
		}

		return null;
	}

	@Override
	public List<Tarefa> retornaTarefasNaoConcluidas() throws Exception {
		List<Tarefa> tarefas = new ArrayList<>();
		for (Document doc : collection.find()) {

			if (doc.getBoolean("status") == false) {

				tarefas.add(new Tarefa(doc.getString("_id"), doc.getString("titulo"), doc.getString("descricao"),
						doc.getString("data"), doc.getBoolean("status")));
			}
		}

		return tarefas;
	}

	@Override
	public List<Tarefa> retornaTarefasConcluidas() throws Exception {
		List<Tarefa> tarefas = new ArrayList<>();
		for (Document doc : collection.find()) {

			if (doc.getBoolean("status") == true) {
				tarefas.add(new Tarefa(doc.getString("_id"), doc.getString("titulo"), doc.getString("descricao"),
						doc.getString("data"), doc.getBoolean("status")));
			}

		}
		return tarefas;
	}

	@Override
	public void marcaTarefaComoConcluidaPeloId(String id) throws Exception {
		Document filterDocument = new Document("_id", id);
		Document updateDoc = new Document("$set", new Document("status", true));
		collection.updateOne(filterDocument, updateDoc);

	}

	@Override
	public void dermarcaTarefaComoConcluidaPeloId(String id) throws Exception {
		Document filterDocument = new Document("_id", id);
		Document updateDoc = new Document("$set", new Document("status", false));
		collection.updateOne(filterDocument, updateDoc);

	}

	@Override
	public void modificaDescricaoTarefaPeloId(Tarefa tarefa, String novaDescricao) throws Exception {
		Document filterDocument = new Document("_id", tarefa.getId());
		Document updateDoc = new Document("$set", new Document("descricao", novaDescricao));
		collection.updateOne(filterDocument, updateDoc);
	}

	@Override
	public void modificaDataTarefaPeloId(Tarefa tarefa, String novaData) throws Exception {

		Document filterDocument = new Document("_id", tarefa.getId());

		Document updateDoc = new Document("$set", new Document("data", novaData));
		collection.updateOne(filterDocument, updateDoc);

	}

	@Override
	public void deleteTarefa(String id) throws Exception {
		Document filterDocument = new Document("_id", id);
		collection.deleteOne(filterDocument);

	}

	@Override
	public Long retornaNumeroDeTarefas() throws Exception {
		return collection.countDocuments();
	}

	@Override
	public boolean verificaSeJaTemId(String id) throws Exception {
		Document existingDoc = collection.find(Filters.eq("_id", id)).first();
		return existingDoc != null;
	}

	@Override
	public void marcaComoConcluidaPelaData() throws Exception {
		for (Document doc : collection.find()) {

			if (Data.dataJaPassou(doc.getString("data"))) {

				Document filterDocument = new Document("_id", doc.getString("_id"));
				Document updateDoc = new Document("$set", new Document("status", true));
				collection.updateOne(filterDocument, updateDoc);
			}
		}

	}

}
