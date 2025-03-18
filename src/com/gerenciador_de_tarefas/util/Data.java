package com.gerenciador_de_tarefas.util;

import com.gerenciador_de_tarefas.model.entities.Tarefa;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Data {
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(Tarefa.FORMATO_DATA);

	public static boolean isFormatoValido(String dataString) {
		return parseData(dataString) != null;
	}

	public static boolean isDataPassada(String dataString) {
		LocalDate data = parseData(dataString);
		return data != null && data.isBefore(LocalDate.now());
	}

	private static LocalDate parseData(String dataString) {
		try {
			return LocalDate.parse(dataString, FORMATTER);
		} catch (DateTimeParseException e) {
			return null;
		}
	}
}
