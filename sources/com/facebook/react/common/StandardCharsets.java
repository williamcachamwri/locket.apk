package com.facebook.react.common;

import java.nio.charset.Charset;
import org.apache.commons.codec.CharEncoding;

@Deprecated(forRemoval = true, since = "Deprecated class since v0.73.0, please use java.nio.charset.StandardCharsets instead.")
public final class StandardCharsets {
    public static final Charset UTF_16 = Charset.forName(CharEncoding.UTF_16);
    public static final Charset UTF_16BE = Charset.forName(CharEncoding.UTF_16BE);
    public static final Charset UTF_16LE = Charset.forName(CharEncoding.UTF_16LE);
    public static final Charset UTF_8 = Charset.forName("UTF-8");

    private StandardCharsets() {
    }
}
