package net.socium.coingateapi.exceptions;

public class CredentialsMissingException extends BadRequestException {
    CredentialsMissingException(ApiError error) {
        super(error);
    }
}
