package net.socium.coingateapi.exceptions;

public class IPAddressIsNotAllowedException extends UnauthorizedException {
    IPAddressIsNotAllowedException(ApiError error) {
        super(error);
    }
}
