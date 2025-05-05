package com.facebook.imagepipeline.decoder;

import com.facebook.imagepipeline.image.EncodedImage;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0004\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0019\b\u0016\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B#\b\u0016\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lcom/facebook/imagepipeline/decoder/DecodeException;", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "message", "", "encodedImage", "Lcom/facebook/imagepipeline/image/EncodedImage;", "(Ljava/lang/String;Lcom/facebook/imagepipeline/image/EncodedImage;)V", "t", "", "(Ljava/lang/String;Ljava/lang/Throwable;Lcom/facebook/imagepipeline/image/EncodedImage;)V", "getEncodedImage", "()Lcom/facebook/imagepipeline/image/EncodedImage;", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: DecodeException.kt */
public final class DecodeException extends RuntimeException {
    private final EncodedImage encodedImage;

    public final EncodedImage getEncodedImage() {
        return this.encodedImage;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DecodeException(String str, EncodedImage encodedImage2) {
        super(str);
        Intrinsics.checkNotNullParameter(encodedImage2, "encodedImage");
        this.encodedImage = encodedImage2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DecodeException(String str, Throwable th, EncodedImage encodedImage2) {
        super(str, th);
        Intrinsics.checkNotNullParameter(encodedImage2, "encodedImage");
        this.encodedImage = encodedImage2;
    }
}
