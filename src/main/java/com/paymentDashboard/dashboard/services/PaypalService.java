package com.paymentDashboard.dashboard.services;

import com.paymentDashboard.dashboard.domain.PaypalOrders;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Base64;

import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.web.client.RestTemplate;


@Service
public class PaypalService {

    @Value("${paypal.client.id}")
    private String clientId;

    @Value("${paypal.client.secret}")
    private String clientSecret;

    @Value("${paypal.base-url}")
    private String baseUrl;

    public String getAccessToken() {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            String credentials = clientId + ":" + clientSecret;
            String base64Credentials = Base64.getEncoder().encodeToString(credentials.getBytes());

            StringEntity entity = new StringEntity("grant_type=client_credentials", ContentType.APPLICATION_FORM_URLENCODED);

            org.apache.http.client.methods.CloseableHttpResponse response = httpClient.execute(
                    RequestBuilder.post(baseUrl + "/v1/oauth2/token")
                            .addHeader(HttpHeaders.AUTHORIZATION, "Basic " + base64Credentials)
                            .addHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_FORM_URLENCODED.getMimeType())
                            .setEntity(entity)
                            .build()
            );

            // Check the response and extract the access token
            // (Note: you might want to use a JSON library for better parsing)
            if (response.getStatusLine().getStatusCode() == 200) {
                String responseBody = EntityUtils.toString(response.getEntity());
                JSONObject json = new JSONObject(responseBody);
                return json.getString("access_token");
            } else {
                // Handle error
                return null;
            }
        } catch (IOException e) {
            // Handle exception
            return null;
        }
    }

    public String createPayment(String accessToken, PaypalOrders paymentRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<PaypalOrders> requestEntity = new HttpEntity<>(paymentRequest, headers);
        RestTemplate restTemplate = null;
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                baseUrl + "/v2/checkout/orders",
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        if (responseEntity.getStatusCode() == HttpStatus.CREATED) {
            // Payment created successfully, handle the response
            String paymentId = responseEntity.getBody();
            return paymentId;
        } else {
            // Handle the error, e.g., log it or throw an exception
            return null;
        }
    }
}




