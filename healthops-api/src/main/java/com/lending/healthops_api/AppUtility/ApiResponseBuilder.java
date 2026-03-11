package com.lending.healthops_api.AppUtility;

public class ApiResponseBuilder {

    private ApiResponseBuilder() {
        // Prevent object creation
    }

    public static <T> ApiResponse<T> success(
            String messageId,
            String message,
            T data) {

        return new ApiResponse<>(messageId, message, data);
    }

    public static <T> ApiResponse<T> error(
            String messageId,
            String message) {

        return new ApiResponse<>(messageId, message, null);
    }

}
