package com.google.firebase.functions;

public class HttpsCallableResult {
    private final Object data;

    HttpsCallableResult(Object obj) {
        this.data = obj;
    }

    public Object getData() {
        return this.data;
    }
}
