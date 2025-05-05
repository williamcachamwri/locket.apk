package com.facebook.imagepipeline.common;

import com.facebook.common.util.HashCodeUtil;
import java.util.Arrays;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB+\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\bJ\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001H\u0002J\b\u0010\f\u001a\u00020\u0003H\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016R\u0010\u0010\u0004\u001a\u00020\u00038\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00068\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\u00068\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/facebook/imagepipeline/common/ResizeOptions;", "", "width", "", "height", "maxBitmapSize", "", "roundUpFraction", "(IIFF)V", "equals", "", "other", "hashCode", "toString", "", "Companion", "imagepipeline-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ResizeOptions.kt */
public final class ResizeOptions {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final float DEFAULT_ROUNDUP_FRACTION = 0.6666667f;
    public final int height;
    public final float maxBitmapSize;
    public final float roundUpFraction;
    public final int width;

    public ResizeOptions(int i, int i2) {
        this(i, i2, 0.0f, 0.0f, 12, (DefaultConstructorMarker) null);
    }

    public ResizeOptions(int i, int i2, float f) {
        this(i, i2, f, 0.0f, 8, (DefaultConstructorMarker) null);
    }

    @JvmStatic
    public static final ResizeOptions forDimensions(int i, int i2) {
        return Companion.forDimensions(i, i2);
    }

    @JvmStatic
    public static final ResizeOptions forSquareSize(int i) {
        return Companion.forSquareSize(i);
    }

    public ResizeOptions(int i, int i2, float f, float f2) {
        this.width = i;
        this.height = i2;
        this.maxBitmapSize = f;
        this.roundUpFraction = f2;
        boolean z = true;
        if (i > 0) {
            if (!(i2 <= 0 ? false : z)) {
                throw new IllegalStateException("Check failed.".toString());
            }
            return;
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ResizeOptions(int i, int i2, float f, float f2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, (i3 & 4) != 0 ? 2048.0f : f, (i3 & 8) != 0 ? 0.6666667f : f2);
    }

    public int hashCode() {
        return HashCodeUtil.hashCode(this.width, this.height);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ResizeOptions) {
            ResizeOptions resizeOptions = (ResizeOptions) obj;
            return this.width == resizeOptions.width && this.height == resizeOptions.height;
        }
    }

    public String toString() {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format((Locale) null, "%dx%d", Arrays.copyOf(new Object[]{Integer.valueOf(this.width), Integer.valueOf(this.height)}, 2));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        return format;
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0007J\u0012\u0010\n\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000b\u001a\u00020\bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/facebook/imagepipeline/common/ResizeOptions$Companion;", "", "()V", "DEFAULT_ROUNDUP_FRACTION", "", "forDimensions", "Lcom/facebook/imagepipeline/common/ResizeOptions;", "width", "", "height", "forSquareSize", "size", "imagepipeline-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ResizeOptions.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final ResizeOptions forDimensions(int i, int i2) {
            if (i <= 0 || i2 <= 0) {
                return null;
            }
            return new ResizeOptions(i, i2, 0.0f, 0.0f, 12, (DefaultConstructorMarker) null);
        }

        @JvmStatic
        public final ResizeOptions forSquareSize(int i) {
            if (i <= 0) {
                return null;
            }
            return new ResizeOptions(i, i, 0.0f, 0.0f, 12, (DefaultConstructorMarker) null);
        }
    }
}
