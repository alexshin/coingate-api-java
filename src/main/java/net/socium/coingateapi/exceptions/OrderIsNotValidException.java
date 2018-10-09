package net.socium.coingateapi.exceptions;

public class OrderIsNotValidException extends UnprocessableEntityException {
    OrderIsNotValidException(ApiError error) {
        super(error);
    }
}
