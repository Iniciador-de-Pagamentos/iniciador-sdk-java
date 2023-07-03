package com.iniciador;

import com.google.gson.Gson;
import com.iniciador.dtos.*;

import org.modelmapper.ModelMapper;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Base64;

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

    private static String extractPaymentIDFromJWTPayload(String token) throws Exception {
        String[] parts = token.split("\\.");

        if (parts.length != 3) {
            throw new Exception("Invalid JWT token.");
        }

        byte[] payloadBytes = Base64.getUrlDecoder().decode(parts[1]);

        String payload = new String(payloadBytes);

        ObjectMapper objectMapper = new ObjectMapper();
        TokenData payloadData = objectMapper.readValue(payload, TokenData.class);

        String id = payloadData.getId();

        return id;
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

        Gson gson = new Gson();
        AuthOutput parsedResponse = gson.fromJson(responseBody, AuthOutput.class);

        return parsedResponse;
    }

    public AuthInterfaceOutput authInterface() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(environment + "/auth/interface"))
                .POST(HttpRequest.BodyPublishers.ofString(
                        String.format("{\"clientId\":\"%s\",\"clientSecret\":\"%s\"}", clientId, clientSecret)))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String responseBody = response.body();

        Gson gson = new Gson();
        AuthInterfaceOutput parsedResponse = gson.fromJson(responseBody, AuthInterfaceOutput.class);

        return parsedResponse;
    }

    public ParticipantFilterOutputDto participants(String accessToken, ParticipantFilterDto filters)
            throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        Map<String, String> filterParams = new HashMap<>();
        if (filters != null) {
            if (filters.getId() != null) {
                filterParams.put("id", filters.getId().toString());
            }
            if (filters.getName() != null) {
                filterParams.put("name", filters.getName());
            }
            // Add other filter fields if needed
        }

        String queryString = filterParams.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));

        String url = environment + "/participants";
        if (!queryString.isEmpty()) {
            url += "?" + queryString;
        }

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", "Bearer " + accessToken)
                .build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String responseBody = response.body();

        Gson gson = new Gson();
        ParticipantFilterOutputDto parsedResponse = gson.fromJson(responseBody, ParticipantFilterOutputDto.class);

        return parsedResponse;
    }

    public PaymentInitiationPayload sendPayment(String accessToken, PaymentInitiationPayload payload) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(environment + "/payments"))
                .POST(HttpRequest.BodyPublishers.ofString(payload))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + accessToken)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String responseBody = response.body();

        Gson gson = new Gson();
        PaymentInitiationPayload parsedResponse = gson.fromJson(responseBody, PaymentInitiationPayload.class);

        return parsedResponse;
    }

    public PaymentInitiationPayload getPayment(String accessToken) throws IOException, InterruptedException {
        String id = extractPaymentIDFromJWTPayload(accessToken)

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(environment + "/payments/" + id))
                .header("Authorization", "Bearer " + accessToken)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String responseBody = response.body();

        Gson gson = new Gson();
        PaymentInitiationPayload parsedResponse = gson.fromJson(responseBody, PaymentInitiationPayload.class);

        return parsedResponse;
    }

    public PaymentStatusPayload getPaymentStatus(String accessToken) throws IOException, InterruptedException {
        String id = extractPaymentIDFromJWTPayload(accessToken)

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(environment + "/payments/" + id + "/status"))
                .header("Authorization", "Bearer " + accessToken)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String responseBody = response.body();

        Gson gson = new Gson();
        PaymentStatusPayload parsedResponse = gson.fromJson(responseBody, PaymentStatusPayload.class);

        return parsedResponse;
    }
}