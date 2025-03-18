package com.gerenciador_de_tarefas.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.JOptionPane;

import com.gerenciador_de_tarefas.model.entities.Tarefa;

public class Data {

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public static boolean verificaFormatoData(String stringData) {
		try {
			LocalDate.parse(stringData, formatter);

			return true;
		} catch (DateTimeParseException erro) {
			return false;
		}

	}


	public static boolean dataJaPassou(String dataString) {
		try {
			LocalDate data = LocalDate.parse(dataString, formatter);
			LocalDate hoje = LocalDate.now();
			return data.isBefore(hoje);
		} catch (DateTimeParseException e) {
			return false;
		}
	}

}
