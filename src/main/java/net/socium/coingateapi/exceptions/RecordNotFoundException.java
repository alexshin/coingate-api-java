package net.socium.coingateapi.exceptions;

public class RecordNotFoundException extends NotFoundException {
    RecordNotFoundException(ApiError error) {
        super(error);
    }
}
