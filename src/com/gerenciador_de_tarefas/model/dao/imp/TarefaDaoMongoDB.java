package com.gerenciador_de_tarefas.model.dao.imp;

import com.gerenciador_de_tarefas.dtos.TarefaResponseDTO;
import com.gerenciador_de_tarefas.dtos.TarefaVisualizationDTO;
import com.gerenciador_de_tarefas.enums.StatusTarefa;
import com.gerenciador_de_tarefas.model.dao.TarefaDao;
import com.gerenciador_de_tarefas.model.entities.Tarefa;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TarefaDaoMongoDB implements TarefaDao {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Tarefa.FORMATO_DATA);
    private final MongoCollection<Document> collection;

    public TarefaDaoMongoDB(MongoClient mongoClient) {
        MongoDatabase database = mongoClient.getDatabase("app_tarefas");
        this.collection = database.getCollection("tarefas");
    }

    @Override
    public void insereTarefa(Tarefa tarefa) {
        if (tarefa == null) {
            throw new IllegalArgumentException("A tarefa não pode ser nula.");
        }

        Document doc = new Document()
                .append("titulo", tarefa.getTitulo())
                .append("descricao", tarefa.getDescricao())
                .append("dataCriacao", tarefa.getDataCriacao() != null ? tarefa.getDataCriacao().format(formatter) : null)
                .append("dataConclusao", tarefa.getDataConclusao() != null ? tarefa.getDataConclusao().format(formatter) : null)
                .append("status", tarefa.getStatusTarefa() != null ? tarefa.getStatusTarefa().name() : null);

        try {
            collection.insertOne(doc);
        } catch (MongoException e) {
            throw new RuntimeException("Erro ao inserir tarefa no MongoDB", e);
        }
    }

    @Override
    public boolean haTarefaComMesmoTitulo(String tituloTarefa) {
        return collection.find(Filters.eq("titulo", tituloTarefa)).first() != null;
    }

    @Override
    public List<TarefaVisualizationDTO> retornaTarefasExecutadas() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Tarefa.FORMATO_DATA);
        List<TarefaVisualizationDTO> tarefasExecutadas = new ArrayList<>();

        for (Document doc : collection.find()) {
            String statusTexto = doc.getString("status");
            StatusTarefa statusTarefa = StatusTarefa.valueOf(statusTexto);

            if (statusTarefa == StatusTarefa.EXECUTADA) {
                String titulo = doc.getString("titulo");

                LocalDate dataConclusao = null;
                if (doc.containsKey("dataConclusao") && doc.get("dataConclusao") != null) {
                    dataConclusao = LocalDate.parse(doc.getString("dataConclusao"), formatter);
                }

                tarefasExecutadas.add(new TarefaVisualizationDTO(titulo, dataConclusao));
            }
        }
        return tarefasExecutadas;
    }

    @Override
    public List<TarefaVisualizationDTO> retornaTarefaNaoExecutadas() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Tarefa.FORMATO_DATA);
        List<TarefaVisualizationDTO> tarefasNaoExecutadas = new ArrayList<>();

        for (Document doc : collection.find()) {
            String statusTexto = doc.getString("status");
            StatusTarefa statusTarefa = StatusTarefa.valueOf(statusTexto);

            if (statusTarefa == StatusTarefa.NAO_EXECUTADA) {
                String titulo = doc.getString("titulo");

                LocalDate dataConclusao = null;
                if (doc.containsKey("dataConclusao") && doc.get("dataConclusao") != null) {
                    dataConclusao = LocalDate.parse(doc.getString("dataConclusao"), formatter);
                }

                tarefasNaoExecutadas.add(new TarefaVisualizationDTO(titulo, dataConclusao));
            }
        }
        return tarefasNaoExecutadas;
    }


    @Override
    public List<TarefaVisualizationDTO> retornaTarefas() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Tarefa.FORMATO_DATA);
        List<TarefaVisualizationDTO> tarefas = new ArrayList<>();

        for (Document doc : collection.find()) {
            String titulo = doc.getString("titulo");

            LocalDate dataConclusao = null;
            if (doc.containsKey("dataConclusao") && doc.get("dataConclusao") != null) {
                dataConclusao = LocalDate.parse(doc.getString("dataConclusao"), formatter);
            }
            tarefas.add(new TarefaVisualizationDTO(titulo, dataConclusao));
        }
        return tarefas;
    }

    @Override
    public TarefaResponseDTO retornaTarefaPeloTitulo(String titulo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Tarefa.FORMATO_DATA);

        Document doc = collection.find(Filters.eq("titulo", titulo)).first();

        if (doc != null) {
            return new TarefaResponseDTO(
                    doc.getObjectId("_id").toString(),
                    doc.getString("titulo"),
                    doc.getString("descricao"),
                    doc.getString("dataCriacao"),
                    doc.getString("dataConclusao"),
                    doc.getString("status")
            );
        }
        return null;
    }

	/*
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
	public void marcaComoConcluidaPelaData() throws Exception {
		for (Document doc : collection.find()) {

			if (Data.dataJaPassou(doc.getString("data"))) {

				Document filterDocument = new Document("_id", doc.getString("_id"));
				Document updateDoc = new Document("$set", new Document("status", true));
				collection.updateOne(filterDocument, updateDoc);
			}
		}

	} */

}
