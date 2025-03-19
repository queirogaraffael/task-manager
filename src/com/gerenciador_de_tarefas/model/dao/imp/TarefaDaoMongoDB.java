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
import com.mongodb.client.result.UpdateResult;
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

    @Override
    public void marcaTarefaComoConcluidaPeloTitulo(String titulo) throws Exception {
        if (titulo == null || titulo.isEmpty()) {
            throw new IllegalArgumentException("O título da tarefa não pode ser nulo ou vazio.");
        }

        Document filterDocument = new Document("titulo", titulo);
        Document updateDoc = new Document("$set", new Document("status", StatusTarefa.EXECUTADA.name()));

        UpdateResult result = collection.updateOne(filterDocument, updateDoc);

        if (result.getModifiedCount() == 0) {
            throw new Exception("Nenhuma tarefa encontrada com o título fornecido ou a tarefa já estava concluída.");
        }
    }

    @Override
    public void desmarcaTarefaComoConcluidaPeloTitulo(String tituloTarefa) {
        if (tituloTarefa == null || tituloTarefa.isEmpty()) {
            throw new IllegalArgumentException("O título da tarefa não pode ser nulo ou vazio.");
        }

        Document filterDocument = new Document("titulo", tituloTarefa);
        Document updateDoc = new Document("$set", new Document("status", StatusTarefa.NAO_EXECUTADA.name()));

        UpdateResult result = collection.updateOne(filterDocument, updateDoc);

        if (result.getModifiedCount() == 0) {
            throw new RuntimeException("Nenhuma tarefa encontrada com o título fornecido ou a tarefa já estava não concluída.");
        }

    }

    @Override
    public void deleteTarefa(String titulo) throws Exception {
        if (titulo == null || titulo.isEmpty()) {
            throw new IllegalArgumentException("O título da tarefa não pode ser nulo ou vazio.");
        }

        Document filterDocument = new Document("titulo", titulo);
        collection.deleteOne(filterDocument);
    }

    @Override
    public void modificaDescricaoTarefaPeloTitulo(String tituloTarefa, String novaDescricao) throws Exception {
        if (tituloTarefa == null || tituloTarefa.isEmpty()) {
            throw new IllegalArgumentException("O título da tarefa não pode ser nulo ou vazio.");
        }

        Document filterDocument = new Document("titulo", tituloTarefa);
        Document updateDoc = new Document("$set", new Document("descricao", novaDescricao));

        UpdateResult result = collection.updateOne(filterDocument, updateDoc);

        if (result.getModifiedCount() == 0) {
            throw new Exception("Nenhuma tarefa encontrada com o título fornecido.");
        }
    }

    @Override
    public void modificaDataTarefaPeloTitulo(String tituloTarefa, LocalDate novaData) throws Exception {
        if (tituloTarefa == null || tituloTarefa.isEmpty()) {
            throw new IllegalArgumentException("O título da tarefa não pode ser nulo ou vazio.");
        }

        String dataFormatada = novaData.format(formatter);

        Document filterDocument = new Document("titulo", tituloTarefa);
        Document updateDoc = new Document("$set", new Document("dataConclusao", dataFormatada));

        UpdateResult result = collection.updateOne(filterDocument, updateDoc);

        if (result.getModifiedCount() == 0) {
            throw new Exception("Nenhuma tarefa encontrada com o título fornecido.");
        }
    }

}
