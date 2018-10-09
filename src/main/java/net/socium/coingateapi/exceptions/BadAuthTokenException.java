package net.socium.coingateapi.exceptions;

public class BadAuthTokenException extends UnauthorizedException {
    BadAuthTokenException(ApiError error) {
        super(error);
    }
}
