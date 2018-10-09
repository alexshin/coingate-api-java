package net.socium.coingateapi.exceptions;

public class UnprocessableEntityException extends ApiException {
    UnprocessableEntityException(ApiError error) {
        super(error);
    }
}
