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

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private String id;
	private String titulo;
	private String descricao;
	private LocalDate data;
	private StatusTarefa statusTarefa;

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Tarefa {\n")
				.append("  Titulo: ").append(titulo).append("\n")
				.append("  Descricao: ").append(descricao).append("\n");

		if (data != null) {
			sb.append("  Data: ").append(data.format(FORMATTER)).append("\n");
		}

		sb.append("  Status: ").append(statusTarefa).append("\n")
				.append("}");

		return sb.toString();
	}
}

