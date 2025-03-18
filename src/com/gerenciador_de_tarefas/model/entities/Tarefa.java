package com.gerenciador_de_tarefas.model.entities;

import com.gerenciador_de_tarefas.enums.StatusTarefa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tarefa {

    public static final String FORMATO_DATA = "dd/MM/yyyy";

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(FORMATO_DATA);

    private String titulo;
    private String descricao;
    private LocalDate dataCriacao;
    private LocalDate dataConclusao;
    private StatusTarefa statusTarefa;

}

