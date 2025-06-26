package com.exemplo.clientes;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ClienteTarefa {
    public static void main(String[] args) throws Exception {
        criarTarefa();
        listarTarefas();
        buscarPorId(1);
        consultarStatus();
    }

    public static void criarTarefa() throws Exception {
        String json = """
            {"titulo": "Estudar Javalin", "descricao": "Ler docs", "concluida": false}
            """;

        HttpURLConnection conn = (HttpURLConnection) new URL("http://localhost:7000/tarefas").openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        try (OutputStream os = conn.getOutputStream()) {
            os.write(json.getBytes());
        }
        System.out.println("POST Status: " + conn.getResponseCode());
    }

    public static void listarTarefas() throws Exception {
        HttpURLConnection conn = (HttpURLConnection) new URL("http://localhost:7000/tarefas").openConnection();
        conn.setRequestMethod("GET");
        System.out.println("Tarefas: " + new Scanner(conn.getInputStream()).useDelimiter("\\A").next());
    }

    public static void buscarPorId(int id) throws Exception {
        HttpURLConnection conn = (HttpURLConnection) new URL("http://localhost:7000/tarefas/" + id).openConnection();
        conn.setRequestMethod("GET");
        int status = conn.getResponseCode();
        System.out.println("GET por ID (" + id + "): " + status);
        if (status == 200) {
            System.out.println("Resposta: " + new Scanner(conn.getInputStream()).useDelimiter("\\A").next());
        }
    }

    public static void consultarStatus() throws Exception {
        HttpURLConnection conn = (HttpURLConnection) new URL("http://localhost:7000/status").openConnection();
        conn.setRequestMethod("GET");
        System.out.println("Status: " + new Scanner(conn.getInputStream()).useDelimiter("\\A").next());
    }
}
