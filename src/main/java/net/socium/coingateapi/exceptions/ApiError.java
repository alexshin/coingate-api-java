package net.socium.coingateapi.exceptions;

public interface ApiError {
    String getMessage();
    Integer getHttpStatus();
    ApiErrorType getErrorType();
}
