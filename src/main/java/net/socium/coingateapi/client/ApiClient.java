package net.socium.coingateapi.client;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequestWithBody;
import com.mashape.unirest.request.body.MultipartBody;
import net.socium.coingateapi.exceptions.*;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ApiClient {

    private EnvironmentType environment = EnvironmentType.sandbox;
    private String authToken = "";

    public ApiClient(EnvironmentType environment, String authToken) {
        if (environment != null) {
            this.environment = environment;
        }

        this.authToken = authToken;
    }

    public String getBaseUri() {
        if (environment == EnvironmentType.sandbox) {
            return "https://api-sandbox.coingate.com/v2";
        }

        return "https://api.coingate.com/v2";
    }

    public String getUri(String relativeUri) {
        return getBaseUri() + relativeUri;
    }

    private <T> HttpResponse<T> processResponse(HttpResponse<T> response) throws ApiException {
        if (response.getStatus() == 200) {
            return response;
        }

        throw ApiExceptionFactory.createApiException(response.getStatus(), "PageNotFound", "something wrong");
    }

    private Map<String, String> prepareHeaders() {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        headers.put("Authorization", "Token " + getAuthToken());
        headers.put("User-Agent", "CoinGate java api client");

        return headers;
    }

    public String testConnection() throws UnirestException, ApiException {
        HttpResponse<String> response = processResponse(
                Unirest
                        .get(getUri("/auth/test"))
                        .headers(prepareHeaders())
                        .asString()
        );

        return response.getBody();
    }

    public JSONObject ping() throws UnirestException, ApiException {
        HttpResponse<JsonNode> response = processResponse(
                Unirest
                        .get(getUri("/ping"))
                        .headers(prepareHeaders())
                        .asJson()
        );

        return response.getBody().getObject();
    }


    public JSONObject getExchangeRates() throws UnirestException, ApiException {
        HttpResponse<JsonNode> response = processResponse(
                Unirest
                        .get(getUri("/rates"))
                        .headers(prepareHeaders())
                        .asJson()
        );

        return response.getBody().getObject();
    }


    public BigDecimal getExchangeRate(String from, String to) throws UnirestException, ApiException {
        HttpResponse<String> response = processResponse(
                Unirest
                        .get(getUri("/rates/merchant/{from}/{to}"))
                        .routeParam("from", from.toUpperCase())
                        .routeParam("to", to.toUpperCase())
                        .headers(prepareHeaders())
                        .asString()
        );

        return new BigDecimal(response.getBody());
    }


    public JSONObject createOrder(
            Double priceAmount,
            String priceCurrency,
            String receiveCurrency,
            Map<String, String> extras
    ) throws UnirestException, ApiException {

        MultipartBody request = Unirest
                .post(getUri("/orders"))
                .headers(prepareHeaders())
                .field("price_amount", priceAmount)
                .field("price_currency", priceCurrency.toUpperCase())
                .field("receive_currency", receiveCurrency.toUpperCase());

        if (extras != null) {
            for (String key : extras.keySet()) {
                request.field(key, extras.get(key));
            }
        }



        HttpResponse<JsonNode> response = processResponse(request.asJson());

        return response.getBody().getObject();
    }

    public JSONObject createOrder(
            Double priceAmount,
            String priceCurrency,
            String receiveCurrency
    ) throws UnirestException, ApiException {
        return createOrder(priceAmount, priceCurrency, receiveCurrency, null);
    }


    public JSONObject checkout(Object orderId, String payCurrency) throws UnirestException, ApiException {
        HttpResponse<JsonNode> response = processResponse(
                Unirest
                        .post(getUri("/orders/{orderId}/checkout"))
                        .headers(prepareHeaders())
                        .routeParam("orderId", String.valueOf(orderId))
                        .field("pay_currency", payCurrency.toUpperCase())
                        .asJson()
        );

        return response.getBody().getObject();
    }


    public EnvironmentType getEnvironment() {
        return environment;
    }

    public String getAuthToken() {
        return authToken;
    }

}
