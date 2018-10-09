package net.socium.coingateapi.exceptions;

public class AccountBlockedException extends UnauthorizedException {
    AccountBlockedException(ApiError error) {
        super(error);
    }
}
