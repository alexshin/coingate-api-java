package net.socium.coingateapi.exceptions;

public class OrderNotFoundException extends NotFoundException {
    OrderNotFoundException(ApiError error) {
        super(error);
    }
}
