package com.gerenciador_de_tarefas.dtos;

import com.gerenciador_de_tarefas.enums.StatusTarefa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TarefaCreateDTO {
    private String titulo;
    private String descricao;
    private LocalDate data;
    private StatusTarefa statusTarefa;

}
