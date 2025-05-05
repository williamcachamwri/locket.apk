package com.facebook.soloader;

public class NoBaseApkException extends RuntimeException {
    public NoBaseApkException(Throwable th) {
        super(th);
    }

    public NoBaseApkException(String str, Throwable th) {
        super(str, th);
    }
}
