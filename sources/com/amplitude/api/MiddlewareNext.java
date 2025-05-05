package com.amplitude.api;

public interface MiddlewareNext {
    void run(MiddlewarePayload middlewarePayload);
}
