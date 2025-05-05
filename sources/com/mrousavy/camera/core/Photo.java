package com.mrousavy.camera.core;

import com.mrousavy.camera.core.types.Orientation;
import io.sentry.protocol.Device;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0014\b\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\bHÆ\u0003J\t\u0010\u0018\u001a\u00020\nHÆ\u0003J;\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0013\u0010\u001a\u001a\u00020\n2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÖ\u0001J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\r¨\u0006\u001e"}, d2 = {"Lcom/mrousavy/camera/core/Photo;", "", "path", "", "width", "", "height", "orientation", "Lcom/mrousavy/camera/core/types/Orientation;", "isMirrored", "", "(Ljava/lang/String;IILcom/mrousavy/camera/core/types/Orientation;Z)V", "getHeight", "()I", "()Z", "getOrientation", "()Lcom/mrousavy/camera/core/types/Orientation;", "getPath", "()Ljava/lang/String;", "getWidth", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "toString", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: Photo.kt */
public final class Photo {
    private final int height;
    private final boolean isMirrored;
    private final Orientation orientation;
    private final String path;
    private final int width;

    public static /* synthetic */ Photo copy$default(Photo photo, String str, int i, int i2, Orientation orientation2, boolean z, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = photo.path;
        }
        if ((i3 & 2) != 0) {
            i = photo.width;
        }
        int i4 = i;
        if ((i3 & 4) != 0) {
            i2 = photo.height;
        }
        int i5 = i2;
        if ((i3 & 8) != 0) {
            orientation2 = photo.orientation;
        }
        Orientation orientation3 = orientation2;
        if ((i3 & 16) != 0) {
            z = photo.isMirrored;
        }
        return photo.copy(str, i4, i5, orientation3, z);
    }

    public final String component1() {
        return this.path;
    }

    public final int component2() {
        return this.width;
    }

    public final int component3() {
        return this.height;
    }

    public final Orientation component4() {
        return this.orientation;
    }

    public final boolean component5() {
        return this.isMirrored;
    }

    public final Photo copy(String str, int i, int i2, Orientation orientation2, boolean z) {
        Intrinsics.checkNotNullParameter(str, "path");
        Intrinsics.checkNotNullParameter(orientation2, Device.JsonKeys.ORIENTATION);
        return new Photo(str, i, i2, orientation2, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Photo)) {
            return false;
        }
        Photo photo = (Photo) obj;
        return Intrinsics.areEqual((Object) this.path, (Object) photo.path) && this.width == photo.width && this.height == photo.height && this.orientation == photo.orientation && this.isMirrored == photo.isMirrored;
    }

    public int hashCode() {
        return (((((((this.path.hashCode() * 31) + Integer.hashCode(this.width)) * 31) + Integer.hashCode(this.height)) * 31) + this.orientation.hashCode()) * 31) + Boolean.hashCode(this.isMirrored);
    }

    public String toString() {
        String str = this.path;
        int i = this.width;
        int i2 = this.height;
        Orientation orientation2 = this.orientation;
        return "Photo(path=" + str + ", width=" + i + ", height=" + i2 + ", orientation=" + orientation2 + ", isMirrored=" + this.isMirrored + ")";
    }

    public Photo(String str, int i, int i2, Orientation orientation2, boolean z) {
        Intrinsics.checkNotNullParameter(str, "path");
        Intrinsics.checkNotNullParameter(orientation2, Device.JsonKeys.ORIENTATION);
        this.path = str;
        this.width = i;
        this.height = i2;
        this.orientation = orientation2;
        this.isMirrored = z;
    }

    public final int getHeight() {
        return this.height;
    }

    public final Orientation getOrientation() {
        return this.orientation;
    }

    public final String getPath() {
        return this.path;
    }

    public final int getWidth() {
        return this.width;
    }

    public final boolean isMirrored() {
        return this.isMirrored;
    }
}
