package az.javidan.msproduct.exception;

public interface ExceptionConstants {

    String UNEXPECTED_EXCEPTION_CODE = "UNEXPECTED_EXCEPTION";

    String UNEXPECTED_EXCEPTION_MESSAGE = "Unexpected exception occurred";

    String PRODUCT_NOT_FOUND_CODE = "PRODUCT_NOT_FOUND";

    String PRODUCT_NOT_FOUND_MESSAGE = "Product not found with this id: %s";

    String METHOD_NOT_ALLOWED_CODE = "METHOD_NOT_ALLOWED";
    String METHOD_NOT_ALLOWED_MESSAGE = "This HTTP method is not allowed";
}