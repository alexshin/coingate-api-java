package net.socium.coingateapi.exceptions;

public class NotFoundException extends ApiException {
    public NotFoundException(ApiError error) {
        super(error);
    }
}
