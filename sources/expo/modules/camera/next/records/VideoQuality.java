package expo.modules.camera.next.records;

import androidx.camera.video.Quality;
import expo.modules.kotlin.types.Enumerable;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002B\u000f\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0006\u0010\b\u001a\u00020\tR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000e¨\u0006\u000f"}, d2 = {"Lexpo/modules/camera/next/records/VideoQuality;", "", "Lexpo/modules/kotlin/types/Enumerable;", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "mapToQuality", "Landroidx/camera/video/Quality;", "VIDEO2160P", "VIDEO1080P", "VIDEO720P", "VIDEO480P", "VIDEO4X3", "expo-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CameraRecords.kt */
public enum VideoQuality implements Enumerable {
    VIDEO2160P("2160p"),
    VIDEO1080P("1080p"),
    VIDEO720P("720p"),
    VIDEO480P("480p"),
    VIDEO4X3("4:3");
    
    private final String value;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: CameraRecords.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        /* JADX WARNING: Can't wrap try/catch for region: R(13:0|1|2|3|4|5|6|7|8|9|10|11|13) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002b */
        static {
            /*
                expo.modules.camera.next.records.VideoQuality[] r0 = expo.modules.camera.next.records.VideoQuality.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                expo.modules.camera.next.records.VideoQuality r1 = expo.modules.camera.next.records.VideoQuality.VIDEO2160P     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                expo.modules.camera.next.records.VideoQuality r1 = expo.modules.camera.next.records.VideoQuality.VIDEO1080P     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                expo.modules.camera.next.records.VideoQuality r1 = expo.modules.camera.next.records.VideoQuality.VIDEO720P     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                expo.modules.camera.next.records.VideoQuality r1 = expo.modules.camera.next.records.VideoQuality.VIDEO480P     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                expo.modules.camera.next.records.VideoQuality r1 = expo.modules.camera.next.records.VideoQuality.VIDEO4X3     // Catch:{ NoSuchFieldError -> 0x0034 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0034 }
            L_0x0034:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: expo.modules.camera.next.records.VideoQuality.WhenMappings.<clinit>():void");
        }
    }

    public static EnumEntries<VideoQuality> getEntries() {
        return $ENTRIES;
    }

    private VideoQuality(String str) {
        this.value = str;
    }

    public final String getValue() {
        return this.value;
    }

    static {
        VideoQuality[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries((E[]) (Enum[]) $values);
    }

    public final Quality mapToQuality() {
        int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (i == 1) {
            Quality quality = Quality.UHD;
            Intrinsics.checkNotNullExpressionValue(quality, "UHD");
            return quality;
        } else if (i == 2) {
            Quality quality2 = Quality.FHD;
            Intrinsics.checkNotNullExpressionValue(quality2, "FHD");
            return quality2;
        } else if (i == 3) {
            Quality quality3 = Quality.HD;
            Intrinsics.checkNotNullExpressionValue(quality3, "HD");
            return quality3;
        } else if (i == 4) {
            Quality quality4 = Quality.SD;
            Intrinsics.checkNotNullExpressionValue(quality4, "SD");
            return quality4;
        } else if (i == 5) {
            Quality quality5 = Quality.LOWEST;
            Intrinsics.checkNotNullExpressionValue(quality5, "LOWEST");
            return quality5;
        } else {
            throw new NoWhenBranchMatchedException();
        }
    }
}
