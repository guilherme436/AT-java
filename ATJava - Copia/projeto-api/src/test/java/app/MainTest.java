package com.exemplo;

import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    @Test
    void testHelloEndpoint() {
        JavalinTest.test(App::main, (server, client) -> {
            var res = client.get("/hello");
            assertEquals(200, res.code());
            assertEquals("Hello, Javalin!", res.body().string());
        });
    }

    @Test
    void createTaskTest() {
        JavalinTest.test(App::main, (server, client) -> {
            var json = """
                {
                    "titulo": "Estudar",
                    "descricao": "Ler Javalin Docs",
                    "concluida": false
                }
                """;
            var res = client.post("/tarefas").body(json).execute();
            assertEquals(201, res.code());
        });
    }

    @Test
    void findTaskByIdTest() {
        JavalinTest.test(App::main, (server, client) -> {
            var json = """
                {
                    "titulo": "Comprar pão",
                    "descricao": "Ir à padaria",
                    "concluida": false
                }
                """;
            client.post("/tarefas").body(json).execute();
            var res = client.get("/tarefas/1");
            assertEquals(200, res.code());
            assertTrue(res.body().string().contains("Comprar pão"));
        });
    }

    @Test
    void listTasksTest() {
        JavalinTest.test(App::main, (server, client) -> {
            var json = """
                {
                    "titulo": "Fazer tarefa",
                    "descricao": "Programar API",
                    "concluida": true
                }
                """;
            client.post("/tarefas").body(json).execute();
            var res = client.get("/tarefas");
            assertEquals(200, res.code());
            assertTrue(res.body().string().contains("Fazer tarefa"));
        });
    }
}
