package net.socium.coingateapi.exceptions;

public class BadCredentialsException extends ApiException {
    BadCredentialsException(ApiError error) {
        super(error);
    }
}
