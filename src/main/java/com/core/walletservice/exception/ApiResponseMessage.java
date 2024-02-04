package com.core.walletservice.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ApiResponseMessage<T> {
    private T data;
    private Error error;
}
