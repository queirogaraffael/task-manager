package com.gerenciador_de_tarefas.enums;

public enum StatusTarefa {

    EXECUTADA(0, "Executada"),
    NAO_EXECUTADA(1, "Não executada");

    private final int codigo;
    private final String status;

    StatusTarefa(int codigo, String status) {
        this.codigo = codigo;
        this.status = status;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getStatus() {
        return status;
    }

    public static StatusTarefa porCodigo(int codigo) {
        for (StatusTarefa status : StatusTarefa.values()) {
            if (status.getCodigo() == codigo) {
                return status;
            }
        }
        throw new IllegalArgumentException("Código de status inválido: " + codigo);
    }

    @Override
    public String toString() {
        return status;
    }
}

