package com.lending.healthops_api.AppUtility;

public class ApiResponseBuilder {

    private ApiResponseBuilder() {
    }

    public static <T> ApiResponse<T> success(
            String messageId,
            String message,
            T data) {

        return new ApiResponse<>(
                messageId,
                message,
                data
        );
    }
}
