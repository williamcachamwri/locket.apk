package expo.modules.videothumbnails;

import expo.modules.kotlin.records.Field;
import expo.modules.kotlin.records.Record;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B/\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\tJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\u0015\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J3\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bHÖ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÖ\u0001J\t\u0010\u001d\u001a\u00020\bHÖ\u0001R(\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u00078\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u000b\u001a\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u000b\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001e"}, d2 = {"Lexpo/modules/videothumbnails/VideoThumbnailOptions;", "Lexpo/modules/kotlin/records/Record;", "quality", "", "time", "", "headers", "", "", "(DILjava/util/Map;)V", "getHeaders$annotations", "()V", "getHeaders", "()Ljava/util/Map;", "getQuality$annotations", "getQuality", "()D", "getTime$annotations", "getTime", "()I", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "toString", "expo-video-thumbnails_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoThumbnailOptions.kt */
public final class VideoThumbnailOptions implements Record {
    private final Map<String, String> headers;
    private final double quality;
    private final int time;

    public VideoThumbnailOptions() {
        this(0.0d, 0, (Map) null, 7, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ VideoThumbnailOptions copy$default(VideoThumbnailOptions videoThumbnailOptions, double d, int i, Map<String, String> map, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            d = videoThumbnailOptions.quality;
        }
        if ((i2 & 2) != 0) {
            i = videoThumbnailOptions.time;
        }
        if ((i2 & 4) != 0) {
            map = videoThumbnailOptions.headers;
        }
        return videoThumbnailOptions.copy(d, i, map);
    }

    @Field
    public static /* synthetic */ void getHeaders$annotations() {
    }

    @Field
    public static /* synthetic */ void getQuality$annotations() {
    }

    @Field
    public static /* synthetic */ void getTime$annotations() {
    }

    public final double component1() {
        return this.quality;
    }

    public final int component2() {
        return this.time;
    }

    public final Map<String, String> component3() {
        return this.headers;
    }

    public final VideoThumbnailOptions copy(double d, int i, Map<String, String> map) {
        Intrinsics.checkNotNullParameter(map, "headers");
        return new VideoThumbnailOptions(d, i, map);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VideoThumbnailOptions)) {
            return false;
        }
        VideoThumbnailOptions videoThumbnailOptions = (VideoThumbnailOptions) obj;
        return Double.compare(this.quality, videoThumbnailOptions.quality) == 0 && this.time == videoThumbnailOptions.time && Intrinsics.areEqual((Object) this.headers, (Object) videoThumbnailOptions.headers);
    }

    public int hashCode() {
        return (((Double.hashCode(this.quality) * 31) + Integer.hashCode(this.time)) * 31) + this.headers.hashCode();
    }

    public String toString() {
        double d = this.quality;
        int i = this.time;
        return "VideoThumbnailOptions(quality=" + d + ", time=" + i + ", headers=" + this.headers + ")";
    }

    public VideoThumbnailOptions(double d, int i, Map<String, String> map) {
        Intrinsics.checkNotNullParameter(map, "headers");
        this.quality = d;
        this.time = i;
        this.headers = map;
    }

    public final double getQuality() {
        return this.quality;
    }

    public final int getTime() {
        return this.time;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ VideoThumbnailOptions(double d, int i, Map map, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 1.0d : d, (i2 & 2) != 0 ? 0 : i, (i2 & 4) != 0 ? MapsKt.emptyMap() : map);
    }

    public final Map<String, String> getHeaders() {
        return this.headers;
    }
}
