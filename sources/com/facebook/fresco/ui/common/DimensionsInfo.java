package com.facebook.fresco.ui.common;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0004\b\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\nHÆ\u0003JO\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001H\u0002J\b\u0010 \u001a\u00020\u0003H\u0016J\t\u0010!\u001a\u00020\nHÖ\u0001R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\r¨\u0006\""}, d2 = {"Lcom/facebook/fresco/ui/common/DimensionsInfo;", "", "viewportWidth", "", "viewportHeight", "encodedImageWidth", "encodedImageHeight", "decodedImageWidth", "decodedImageHeight", "scaleType", "", "(IIIIIILjava/lang/String;)V", "getDecodedImageHeight", "()I", "getDecodedImageWidth", "getEncodedImageHeight", "getEncodedImageWidth", "getScaleType", "()Ljava/lang/String;", "getViewportHeight", "getViewportWidth", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "ui-common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: DimensionsInfo.kt */
public final class DimensionsInfo {
    private final int decodedImageHeight;
    private final int decodedImageWidth;
    private final int encodedImageHeight;
    private final int encodedImageWidth;
    private final String scaleType;
    private final int viewportHeight;
    private final int viewportWidth;

    public static /* synthetic */ DimensionsInfo copy$default(DimensionsInfo dimensionsInfo, int i, int i2, int i3, int i4, int i5, int i6, String str, int i7, Object obj) {
        if ((i7 & 1) != 0) {
            i = dimensionsInfo.viewportWidth;
        }
        if ((i7 & 2) != 0) {
            i2 = dimensionsInfo.viewportHeight;
        }
        int i8 = i2;
        if ((i7 & 4) != 0) {
            i3 = dimensionsInfo.encodedImageWidth;
        }
        int i9 = i3;
        if ((i7 & 8) != 0) {
            i4 = dimensionsInfo.encodedImageHeight;
        }
        int i10 = i4;
        if ((i7 & 16) != 0) {
            i5 = dimensionsInfo.decodedImageWidth;
        }
        int i11 = i5;
        if ((i7 & 32) != 0) {
            i6 = dimensionsInfo.decodedImageHeight;
        }
        int i12 = i6;
        if ((i7 & 64) != 0) {
            str = dimensionsInfo.scaleType;
        }
        return dimensionsInfo.copy(i, i8, i9, i10, i11, i12, str);
    }

    public final int component1() {
        return this.viewportWidth;
    }

    public final int component2() {
        return this.viewportHeight;
    }

    public final int component3() {
        return this.encodedImageWidth;
    }

    public final int component4() {
        return this.encodedImageHeight;
    }

    public final int component5() {
        return this.decodedImageWidth;
    }

    public final int component6() {
        return this.decodedImageHeight;
    }

    public final String component7() {
        return this.scaleType;
    }

    public final DimensionsInfo copy(int i, int i2, int i3, int i4, int i5, int i6, String str) {
        String str2 = str;
        Intrinsics.checkNotNullParameter(str2, "scaleType");
        return new DimensionsInfo(i, i2, i3, i4, i5, i6, str2);
    }

    public String toString() {
        return "DimensionsInfo(viewportWidth=" + this.viewportWidth + ", viewportHeight=" + this.viewportHeight + ", encodedImageWidth=" + this.encodedImageWidth + ", encodedImageHeight=" + this.encodedImageHeight + ", decodedImageWidth=" + this.decodedImageWidth + ", decodedImageHeight=" + this.decodedImageHeight + ", scaleType=" + this.scaleType + ')';
    }

    public DimensionsInfo(int i, int i2, int i3, int i4, int i5, int i6, String str) {
        Intrinsics.checkNotNullParameter(str, "scaleType");
        this.viewportWidth = i;
        this.viewportHeight = i2;
        this.encodedImageWidth = i3;
        this.encodedImageHeight = i4;
        this.decodedImageWidth = i5;
        this.decodedImageHeight = i6;
        this.scaleType = str;
    }

    public final int getViewportWidth() {
        return this.viewportWidth;
    }

    public final int getViewportHeight() {
        return this.viewportHeight;
    }

    public final int getEncodedImageWidth() {
        return this.encodedImageWidth;
    }

    public final int getEncodedImageHeight() {
        return this.encodedImageHeight;
    }

    public final int getDecodedImageWidth() {
        return this.decodedImageWidth;
    }

    public final int getDecodedImageHeight() {
        return this.decodedImageHeight;
    }

    public final String getScaleType() {
        return this.scaleType;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!Intrinsics.areEqual((Object) getClass(), (Object) obj != null ? obj.getClass() : null)) {
            return false;
        }
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type com.facebook.fresco.ui.common.DimensionsInfo");
        DimensionsInfo dimensionsInfo = (DimensionsInfo) obj;
        if (this.viewportWidth == dimensionsInfo.viewportWidth && this.viewportHeight == dimensionsInfo.viewportHeight && this.encodedImageWidth == dimensionsInfo.encodedImageWidth && this.encodedImageHeight == dimensionsInfo.encodedImageHeight && this.decodedImageWidth == dimensionsInfo.decodedImageWidth && this.decodedImageHeight == dimensionsInfo.decodedImageHeight && Intrinsics.areEqual((Object) this.scaleType, (Object) dimensionsInfo.scaleType)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((this.viewportWidth * 31) + this.viewportHeight) * 31) + this.encodedImageWidth) * 31) + this.encodedImageHeight) * 31) + this.decodedImageWidth) * 31) + this.decodedImageHeight) * 31) + this.scaleType.hashCode();
    }
}
