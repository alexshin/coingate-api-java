package net.socium.coingateapi.exceptions;

public class ApiErrorMessage implements ApiError {
    private String message = "";
    private Integer httpStatus = 400;
    private ApiErrorType errorType;

    public ApiErrorMessage(String message) {
        this.message = message;
        this.httpStatus = 400;
    }

    public ApiErrorMessage(String message, Integer httpStatus) {
        this(message);
        this.httpStatus = httpStatus;
    }

    public ApiErrorMessage(String message, Integer httpStatus, ApiErrorType errorType) {
        this(message, httpStatus);
        this.errorType = errorType;
    }

    public String getMessage() {
        return message;
    }


    public Integer getHttpStatus() {
        return httpStatus;
    }

    public ApiErrorType getErrorType() {
        return errorType;
    }
}