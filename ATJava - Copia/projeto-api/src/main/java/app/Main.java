package com.exemplo;

import com.exemplo.model.Tarefa;
import io.javalin.Javalin;

import java.time.Instant;
import java.util.*;

public class App {
    static List<Tarefa> tarefas = new ArrayList<>();
    static int idCounter = 1;

    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7000);

        app.get("/hello", ctx -> ctx.result("Hello, Javalin!"));
        app.get("/status", ctx -> ctx.json(Map.of("status", "ok", "timestamp", Instant.now().toString())));
        app.post("/echo", ctx -> ctx.json(ctx.bodyAsClass(Map.class)));
        app.get("/saudacao/{nome}", ctx -> {
            String nome = ctx.pathParam("nome");
            ctx.json(Map.of("mensagem", "Olá, " + nome + "!"));
        });

        app.post("/tarefas", ctx -> {
            Tarefa tarefa = ctx.bodyAsClass(Tarefa.class);
            tarefa.id = idCounter++;
            tarefas.add(tarefa);
            ctx.status(201).json(tarefa);
        });

        app.get("/tarefas", ctx -> ctx.json(tarefas));

        app.get("/tarefas/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Tarefa tarefa = tarefas.stream()
                    .filter(t -> t.id == id)
                    .findFirst()
                    .orElse(null);

            if (tarefa == null) {
                ctx.status(404);
            } else {
                ctx.json(tarefa);
            }
        });
    }
}
