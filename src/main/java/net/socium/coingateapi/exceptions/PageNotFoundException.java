package net.socium.coingateapi.exceptions;

public class PageNotFoundException extends NotFoundException {
    PageNotFoundException(ApiError error) {
        super(error);
    }
}
