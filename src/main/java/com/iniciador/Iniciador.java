package com.iniciador;

import com.iniciador.utils.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class Iniciador {
    private String clientId;
    private String clientSecret;
    private String environment;
    private Map<String, Object> paymentPayload;

    public Iniciador(String clientId, String clientSecret, String environment) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.environment = setEnvironment(environment);
        this.paymentPayload = new HashMap<>();
    }

    private String setEnvironment(String environment) {
        switch (environment) {
            case "dev":
                return "https://consumer.dev.inic.dev/v1";
            case "sandbox":
                return "https://consumer.sandbox.inic.dev/v1";
            case "staging":
                return "https://consumer.staging.inic.dev/v1";
            case "prod":
                return "https://consumer.u4c-iniciador.com.br/v1";
            default:
                throw new IllegalArgumentException("Something went wrong, verify environment value.");
        }
    }

    public AuthOutput auth() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(environment + "/auth"))
                .POST(HttpRequest.BodyPublishers.ofString(
                        String.format("{\"clientId\":\"%s\",\"clientSecret\":\"%s\"}", clientId, clientSecret)))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String responseBody = response.body();
        AuthOutput parsedResponse = new AuthOutput(responseBody);
        return parsedResponse;
    }

    public String authInterface() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(environment + "/auth/interface"))
                .POST(HttpRequest.BodyPublishers.ofString(
                        String.format("{\"clientId\":\"%s\",\"clientSecret\":\"%s\"}", clientId, clientSecret)))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String responseBody = response.body();
        return responseBody.toString();
    }

    public static void main(String[] args) {
        Iniciador iniciador = new Iniciador("c82700f8-f0bf-4cce-9068-a2fd6991ee9b",
                "sB#C8ybhJEN63RjBz6Kpd8NUywHkKzXN$d&Zr3j4", "dev");

        try {
            String authOutput = iniciador.authInterface();
            System.out.println("Access Token: " + authOutput);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}