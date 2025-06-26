package com.exemplo.model;

import java.time.LocalDateTime;

public class Tarefa {
    public int id;
    public String titulo;
    public String descricao;
    public boolean concluida;
    public String dataCriacao;

    public Tarefa() {
        this.dataCriacao = LocalDateTime.now().toString();
    }
}