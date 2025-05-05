package expo.modules.imagepicker;

import expo.modules.kotlin.types.Enumerable;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0002\u0018\u0000 \u000e2\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002:\u0001\u000eB\u000f\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0006\u0010\b\u001a\u00020\u0004J\u0006\u0010\t\u001a\u00020\u0004J\u0006\u0010\n\u001a\u00020\u0004R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\u000bj\u0002\b\fj\u0002\b\r¨\u0006\u000f"}, d2 = {"Lexpo/modules/imagepicker/MediaTypes;", "", "Lexpo/modules/kotlin/types/Enumerable;", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "toCameraIntentAction", "toFileExtension", "toMimeType", "IMAGES", "VIDEOS", "ALL", "Companion", "expo-image-picker_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ImagePickerOptions.kt */
public enum MediaTypes implements Enumerable {
    IMAGES("Images"),
    VIDEOS("Videos"),
    ALL("All");
    
    @Deprecated
    public static final String AllMimeType = "*/*";
    private static final Companion Companion = null;
    @Deprecated
    public static final String ImageAllMimeType = "image/*";
    @Deprecated
    public static final String VideoAllMimeType = "video/*";
    private final String value;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: ImagePickerOptions.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|2|3|4|5|6|7|9) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        static {
            /*
                expo.modules.imagepicker.MediaTypes[] r0 = expo.modules.imagepicker.MediaTypes.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                expo.modules.imagepicker.MediaTypes r1 = expo.modules.imagepicker.MediaTypes.IMAGES     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                expo.modules.imagepicker.MediaTypes r1 = expo.modules.imagepicker.MediaTypes.VIDEOS     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                expo.modules.imagepicker.MediaTypes r1 = expo.modules.imagepicker.MediaTypes.ALL     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: expo.modules.imagepicker.MediaTypes.WhenMappings.<clinit>():void");
        }
    }

    public static EnumEntries<MediaTypes> getEntries() {
        return $ENTRIES;
    }

    private MediaTypes(String str) {
        this.value = str;
    }

    public final String getValue() {
        return this.value;
    }

    static {
        MediaTypes[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries((E[]) (Enum[]) $values);
        Companion = new Companion((DefaultConstructorMarker) null);
    }

    public final String toMimeType() {
        int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (i == 1) {
            return ImageAllMimeType;
        }
        if (i == 2) {
            return VideoAllMimeType;
        }
        if (i == 3) {
            return AllMimeType;
        }
        throw new NoWhenBranchMatchedException();
    }

    public final String toFileExtension() {
        return WhenMappings.$EnumSwitchMapping$0[ordinal()] == 2 ? ".mp4" : ".jpeg";
    }

    public final String toCameraIntentAction() {
        return WhenMappings.$EnumSwitchMapping$0[ordinal()] == 2 ? "android.media.action.VIDEO_CAPTURE" : "android.media.action.IMAGE_CAPTURE";
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lexpo/modules/imagepicker/MediaTypes$Companion;", "", "()V", "AllMimeType", "", "ImageAllMimeType", "VideoAllMimeType", "expo-image-picker_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: ImagePickerOptions.kt */
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
