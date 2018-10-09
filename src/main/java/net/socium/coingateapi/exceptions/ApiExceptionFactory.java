package net.socium.coingateapi.exceptions;

public class ApiExceptionFactory {

    public static ApiException createApiException(Integer httpStatus, String type, String message) {

        ApiErrorType errorType;
        ApiException exception;

        try {
            errorType = ApiErrorType.valueOf(type);
        }
        catch (IllegalArgumentException e) {
            errorType = null;
        }

        ApiError error = new ApiErrorMessage(message, httpStatus, errorType);


        switch (httpStatus) {
            case 400:
                exception = ApiExceptionFactory.createGenericException(error);
                break;
            case 401:
                exception = ApiExceptionFactory.createAuthorizationException(error);
                break;
            case 404:
                exception = ApiExceptionFactory.createNotFoundException(error);
                break;
            case 422:
                exception = ApiExceptionFactory.createUnprocessableException(error);
                break;
            case 429:
                exception = new RateLimitException(error);
                break;
            case 500:
                exception = new InternalServerException(error);
                break;
            case 504:
                exception = new InternalServerException(error);
                break;
            default:
                exception = new ApiException(error);
        }

        return exception;
    }

    private static ApiException createGenericException(ApiError error) {
        ApiException exception;
        switch (error.getErrorType()) {
            case CredentialsMissing:
                exception = new CredentialsMissingException(error);
                break;
            case BadEnvironment:
                exception = new BadEnvironmentException(error);
                break;
            default:
                exception = new BadRequestException(error);
        }

        return exception;
    }

    private static ApiException createAuthorizationException(ApiError error) {
        ApiException exception;
        switch (error.getErrorType()) {
            case BadCredentials:
                exception = new BadCredentialsException(error);
                break;
            case BadAuthToken:
                exception = new BadAuthTokenException(error);
                break;
            case AccountBlocked:
                exception = new AccountBlockedException(error);
                break;
            case IpAddressIsNotAllowed:
                exception = new IPAddressIsNotAllowedException(error);
                break;
            default:
                exception = new UnauthorizedException(error);
        }

        return exception;
    }

    private static ApiException createNotFoundException(ApiError error) {
        ApiException exception;
        switch (error.getErrorType()) {
            case PageNotFound:
                exception = new PageNotFoundException(error);
                break;
            case RecordNotFound:
                exception = new RecordNotFoundException(error);
                break;
            case OrderNotFound:
                exception = new OrderNotFoundException(error);
                break;
            default:
                exception = new NotFoundException(error);
        }

        return exception;
    }

    private static ApiException createUnprocessableException(ApiError error) {
        ApiException exception;
        switch (error.getErrorType()) {
            case OrderIsNotValid:
                exception = new OrderIsNotValidException(error);
                break;
            default:
                exception = new UnprocessableEntityException(error);
        }

        return exception;
    }

}
