package com.mrousavy.camera.core.types;

import android.util.Size;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0019"}, d2 = {"Lcom/mrousavy/camera/core/types/Video;", "", "path", "", "durationMs", "", "size", "Landroid/util/Size;", "(Ljava/lang/String;JLandroid/util/Size;)V", "getDurationMs", "()J", "getPath", "()Ljava/lang/String;", "getSize", "()Landroid/util/Size;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: Video.kt */
public final class Video {
    private final long durationMs;
    private final String path;
    private final Size size;

    public static /* synthetic */ Video copy$default(Video video, String str, long j, Size size2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = video.path;
        }
        if ((i & 2) != 0) {
            j = video.durationMs;
        }
        if ((i & 4) != 0) {
            size2 = video.size;
        }
        return video.copy(str, j, size2);
    }

    public final String component1() {
        return this.path;
    }

    public final long component2() {
        return this.durationMs;
    }

    public final Size component3() {
        return this.size;
    }

    public final Video copy(String str, long j, Size size2) {
        Intrinsics.checkNotNullParameter(str, "path");
        Intrinsics.checkNotNullParameter(size2, "size");
        return new Video(str, j, size2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Video)) {
            return false;
        }
        Video video = (Video) obj;
        return Intrinsics.areEqual((Object) this.path, (Object) video.path) && this.durationMs == video.durationMs && Intrinsics.areEqual((Object) this.size, (Object) video.size);
    }

    public int hashCode() {
        return (((this.path.hashCode() * 31) + Long.hashCode(this.durationMs)) * 31) + this.size.hashCode();
    }

    public String toString() {
        String str = this.path;
        long j = this.durationMs;
        return "Video(path=" + str + ", durationMs=" + j + ", size=" + this.size + ")";
    }

    public Video(String str, long j, Size size2) {
        Intrinsics.checkNotNullParameter(str, "path");
        Intrinsics.checkNotNullParameter(size2, "size");
        this.path = str;
        this.durationMs = j;
        this.size = size2;
    }

    public final long getDurationMs() {
        return this.durationMs;
    }

    public final String getPath() {
        return this.path;
    }

    public final Size getSize() {
        return this.size;
    }
}
