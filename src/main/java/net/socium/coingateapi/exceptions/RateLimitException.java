package net.socium.coingateapi.exceptions;

public class RateLimitException extends ApiException {
    RateLimitException(ApiError error) {
        super(error);
    }
}
