package com.facebook.imagepipeline.transcoder;

import java.util.Arrays;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/facebook/imagepipeline/transcoder/ImageTranscodeResult;", "", "transcodeStatus", "", "(I)V", "getTranscodeStatus", "()I", "toString", "", "imagepipeline-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ImageTranscodeResult.kt */
public final class ImageTranscodeResult {
    private final int transcodeStatus;

    public ImageTranscodeResult(int i) {
        this.transcodeStatus = i;
    }

    public final int getTranscodeStatus() {
        return this.transcodeStatus;
    }

    public String toString() {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format((Locale) null, "Status: %d", Arrays.copyOf(new Object[]{Integer.valueOf(this.transcodeStatus)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        return format;
    }
}
