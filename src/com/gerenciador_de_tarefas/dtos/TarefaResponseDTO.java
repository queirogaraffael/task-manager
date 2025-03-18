package com.gerenciador_de_tarefas.dtos;

public class TarefaResponseDTO {

    private String id;
    private String titulo;
    private String descricao;
    private String dataCriacao;
    private String dataConclusao;
    private String status;

    public TarefaResponseDTO(String id, String titulo, String descricao, String dataCriacao, String dataConclusao, String status) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.dataConclusao = dataConclusao;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public String getDataConclusao() {
        return dataConclusao;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        String statusFormatado = status.equalsIgnoreCase("EXECUTADA") ? "Executada" : "Não Executada";
        String dataConclusaoFormatada = (dataConclusao == null || dataConclusao.isEmpty()) ? "Ainda não concluída" : dataConclusao;

        return "Título: " + titulo +
                "\nDescrição: " + descricao +
                "\nData de Criação: " + dataCriacao +
                "\nData de Conclusão: " + dataConclusaoFormatada +
                "\nStatus: " + statusFormatado +
                "\n";
    }

}
