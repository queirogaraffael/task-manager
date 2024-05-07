package com.gerenciadorDeTarefas.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ManipulacaoData {

	public static boolean verificaFormatoData(String stringData, String formatoData) {
		try {
			DateTimeFormatter formato = DateTimeFormatter.ofPattern(formatoData);
			LocalDate.parse(stringData, formato);

			return true;
		} catch (DateTimeParseException e) {
			return false;
		}

	}

	public static LocalDate retornaLocalDate(String data) {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		return LocalDate.parse(data, formato);
	}

}