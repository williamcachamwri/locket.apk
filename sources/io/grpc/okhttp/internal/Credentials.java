package io.grpc.okhttp.internal;

import java.io.UnsupportedEncodingException;
import okio.ByteString;
import org.apache.commons.codec.CharEncoding;

public final class Credentials {
    private Credentials() {
    }

    public static String basic(String str, String str2) {
        try {
            return "Basic " + ByteString.of((str + ":" + str2).getBytes(CharEncoding.ISO_8859_1)).base64();
        } catch (UnsupportedEncodingException unused) {
            throw new AssertionError();
        }
    }
}
