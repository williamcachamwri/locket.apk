package expo.modules.camera.next;

import expo.modules.camera.next.records.VideoQuality;
import expo.modules.kotlin.records.Field;
import expo.modules.kotlin.records.Record;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B#\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0006HÆ\u0003J)\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000bR\u001c\u0010\u0004\u001a\u00020\u00038\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\t\u001a\u0004\b\r\u0010\u000bR\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\t\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001c"}, d2 = {"Lexpo/modules/camera/next/RecordingOptions;", "Lexpo/modules/kotlin/records/Record;", "maxDuration", "", "maxFileSize", "quality", "Lexpo/modules/camera/next/records/VideoQuality;", "(IILexpo/modules/camera/next/records/VideoQuality;)V", "getMaxDuration$annotations", "()V", "getMaxDuration", "()I", "getMaxFileSize$annotations", "getMaxFileSize", "getQuality$annotations", "getQuality", "()Lexpo/modules/camera/next/records/VideoQuality;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "toString", "", "expo-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: Options.kt */
public final class RecordingOptions implements Record {
    private final int maxDuration;
    private final int maxFileSize;
    private final VideoQuality quality;

    public static /* synthetic */ RecordingOptions copy$default(RecordingOptions recordingOptions, int i, int i2, VideoQuality videoQuality, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = recordingOptions.maxDuration;
        }
        if ((i3 & 2) != 0) {
            i2 = recordingOptions.maxFileSize;
        }
        if ((i3 & 4) != 0) {
            videoQuality = recordingOptions.quality;
        }
        return recordingOptions.copy(i, i2, videoQuality);
    }

    @Field
    public static /* synthetic */ void getMaxDuration$annotations() {
    }

    @Field
    public static /* synthetic */ void getMaxFileSize$annotations() {
    }

    @Field
    public static /* synthetic */ void getQuality$annotations() {
    }

    public final int component1() {
        return this.maxDuration;
    }

    public final int component2() {
        return this.maxFileSize;
    }

    public final VideoQuality component3() {
        return this.quality;
    }

    public final RecordingOptions copy(int i, int i2, VideoQuality videoQuality) {
        return new RecordingOptions(i, i2, videoQuality);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RecordingOptions)) {
            return false;
        }
        RecordingOptions recordingOptions = (RecordingOptions) obj;
        return this.maxDuration == recordingOptions.maxDuration && this.maxFileSize == recordingOptions.maxFileSize && this.quality == recordingOptions.quality;
    }

    public int hashCode() {
        int hashCode = ((Integer.hashCode(this.maxDuration) * 31) + Integer.hashCode(this.maxFileSize)) * 31;
        VideoQuality videoQuality = this.quality;
        return hashCode + (videoQuality == null ? 0 : videoQuality.hashCode());
    }

    public String toString() {
        int i = this.maxDuration;
        int i2 = this.maxFileSize;
        return "RecordingOptions(maxDuration=" + i + ", maxFileSize=" + i2 + ", quality=" + this.quality + ")";
    }

    public RecordingOptions(int i, int i2, VideoQuality videoQuality) {
        this.maxDuration = i;
        this.maxFileSize = i2;
        this.quality = videoQuality;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ RecordingOptions(int i, int i2, VideoQuality videoQuality, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0 : i, (i3 & 2) != 0 ? 0 : i2, videoQuality);
    }

    public final int getMaxDuration() {
        return this.maxDuration;
    }

    public final int getMaxFileSize() {
        return this.maxFileSize;
    }

    public final VideoQuality getQuality() {
        return this.quality;
    }
}
