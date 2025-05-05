package expo.modules.videothumbnails;

import expo.modules.kotlin.records.Field;
import expo.modules.kotlin.records.Record;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u000bJ\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u000bJ0\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÖ\u0001J\t\u0010\u001c\u001a\u00020\u0003HÖ\u0001R \u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0004¢\u0006\u0010\n\u0002\u0010\f\u0012\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000bR\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\t\u001a\u0004\b\u000e\u0010\u000fR \u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0004¢\u0006\u0010\n\u0002\u0010\f\u0012\u0004\b\u0010\u0010\t\u001a\u0004\b\u0011\u0010\u000b¨\u0006\u001d"}, d2 = {"Lexpo/modules/videothumbnails/VideoThumbnailResult;", "Lexpo/modules/kotlin/records/Record;", "uri", "", "width", "", "height", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;)V", "getHeight$annotations", "()V", "getHeight", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getUri$annotations", "getUri", "()Ljava/lang/String;", "getWidth$annotations", "getWidth", "component1", "component2", "component3", "copy", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;)Lexpo/modules/videothumbnails/VideoThumbnailResult;", "equals", "", "other", "", "hashCode", "toString", "expo-video-thumbnails_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoThumbnailOptions.kt */
public final class VideoThumbnailResult implements Record {
    private final Integer height;
    private final String uri;
    private final Integer width;

    public static /* synthetic */ VideoThumbnailResult copy$default(VideoThumbnailResult videoThumbnailResult, String str, Integer num, Integer num2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = videoThumbnailResult.uri;
        }
        if ((i & 2) != 0) {
            num = videoThumbnailResult.width;
        }
        if ((i & 4) != 0) {
            num2 = videoThumbnailResult.height;
        }
        return videoThumbnailResult.copy(str, num, num2);
    }

    @Field
    public static /* synthetic */ void getHeight$annotations() {
    }

    @Field
    public static /* synthetic */ void getUri$annotations() {
    }

    @Field
    public static /* synthetic */ void getWidth$annotations() {
    }

    public final String component1() {
        return this.uri;
    }

    public final Integer component2() {
        return this.width;
    }

    public final Integer component3() {
        return this.height;
    }

    public final VideoThumbnailResult copy(String str, Integer num, Integer num2) {
        Intrinsics.checkNotNullParameter(str, "uri");
        return new VideoThumbnailResult(str, num, num2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VideoThumbnailResult)) {
            return false;
        }
        VideoThumbnailResult videoThumbnailResult = (VideoThumbnailResult) obj;
        return Intrinsics.areEqual((Object) this.uri, (Object) videoThumbnailResult.uri) && Intrinsics.areEqual((Object) this.width, (Object) videoThumbnailResult.width) && Intrinsics.areEqual((Object) this.height, (Object) videoThumbnailResult.height);
    }

    public int hashCode() {
        int hashCode = this.uri.hashCode() * 31;
        Integer num = this.width;
        int i = 0;
        int hashCode2 = (hashCode + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.height;
        if (num2 != null) {
            i = num2.hashCode();
        }
        return hashCode2 + i;
    }

    public String toString() {
        String str = this.uri;
        Integer num = this.width;
        return "VideoThumbnailResult(uri=" + str + ", width=" + num + ", height=" + this.height + ")";
    }

    public VideoThumbnailResult(String str, Integer num, Integer num2) {
        Intrinsics.checkNotNullParameter(str, "uri");
        this.uri = str;
        this.width = num;
        this.height = num2;
    }

    public final String getUri() {
        return this.uri;
    }

    public final Integer getWidth() {
        return this.width;
    }

    public final Integer getHeight() {
        return this.height;
    }
}
