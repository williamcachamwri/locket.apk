package com.facebook.imagepipeline.platform;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\t"}, d2 = {"Lcom/facebook/imagepipeline/platform/PlatformDecoderOptions;", "", "avoidPoolGet", "", "avoidPoolRelease", "(ZZ)V", "getAvoidPoolGet", "()Z", "getAvoidPoolRelease", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: PlatformDecoderOptions.kt */
public final class PlatformDecoderOptions {
    private final boolean avoidPoolGet;
    private final boolean avoidPoolRelease;

    public PlatformDecoderOptions() {
        this(false, false, 3, (DefaultConstructorMarker) null);
    }

    public PlatformDecoderOptions(boolean z, boolean z2) {
        this.avoidPoolGet = z;
        this.avoidPoolRelease = z2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ PlatformDecoderOptions(boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? false : z2);
    }

    public final boolean getAvoidPoolGet() {
        return this.avoidPoolGet;
    }

    public final boolean getAvoidPoolRelease() {
        return this.avoidPoolRelease;
    }
}
