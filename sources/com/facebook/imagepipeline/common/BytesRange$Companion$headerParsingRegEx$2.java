package com.facebook.imagepipeline.common;

import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Ljava/util/regex/Pattern;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: BytesRange.kt */
final class BytesRange$Companion$headerParsingRegEx$2 extends Lambda implements Function0<Pattern> {
    public static final BytesRange$Companion$headerParsingRegEx$2 INSTANCE = new BytesRange$Companion$headerParsingRegEx$2();

    BytesRange$Companion$headerParsingRegEx$2() {
        super(0);
    }

    public final Pattern invoke() {
        return Pattern.compile("[-/ ]");
    }
}
