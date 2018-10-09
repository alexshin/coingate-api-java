package net.socium.coingateapi.exceptions;

public class ApiException extends Exception {

    private ApiError error;

    ApiException(ApiError error) {
        this.error = error;
    }

    public ApiError getError() {
        return error;
    }
}
