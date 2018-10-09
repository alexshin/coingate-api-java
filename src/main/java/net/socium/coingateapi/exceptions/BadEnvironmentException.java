package net.socium.coingateapi.exceptions;

public class BadEnvironmentException extends BadRequestException {
    BadEnvironmentException(ApiError error) {
        super(error);
    }
}
