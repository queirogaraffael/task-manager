package com.gerenciador_de_tarefas.dtos;

import java.time.LocalDate;

public class TarefaVisualizationDTO {
    private String titulo;
    private LocalDate dataConclusao;

    public TarefaVisualizationDTO(String titulo, LocalDate dataConclusao) {
        this.titulo = titulo;
        this.dataConclusao = dataConclusao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(LocalDate dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    @Override
    public String toString() {
        return titulo + " - " + dataConclusao;
    }
}
