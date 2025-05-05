package com.amplitude.api;

public interface Middleware {
    void run(MiddlewarePayload middlewarePayload, MiddlewareNext middlewareNext);
}
