package net.socium.coingateapi.exceptions;

public class BadRequestException extends ApiException {
    BadRequestException(ApiError error) {
        super(error);
    }
}
