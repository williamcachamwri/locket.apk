package com.facebook.react.runtime.internal.bolts;

public class UnobservedTaskException extends RuntimeException {
    public UnobservedTaskException(Throwable th) {
        super(th);
    }
}
