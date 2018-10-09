package net.socium.coingateapi.exceptions;

public class UnauthorizedException extends ApiException {
    UnauthorizedException(ApiError error) {
        super(error);
    }
}
