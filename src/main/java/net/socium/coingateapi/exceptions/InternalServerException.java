package net.socium.coingateapi.exceptions;

public class InternalServerException extends ApiException {

    InternalServerException(ApiError error) {
        super(error);
    }
}
