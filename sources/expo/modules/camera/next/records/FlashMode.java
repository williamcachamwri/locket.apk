package expo.modules.camera.next.records;

import expo.modules.kotlin.types.Enumerable;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlinx.coroutines.DebugKt;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002B\u000f\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0006\u0010\b\u001a\u00020\tR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\r"}, d2 = {"Lexpo/modules/camera/next/records/FlashMode;", "", "Lexpo/modules/kotlin/types/Enumerable;", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "mapToLens", "", "AUTO", "ON", "OFF", "expo-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CameraRecords.kt */
public enum FlashMode implements Enumerable {
    AUTO("auto"),
    ON("on"),
    OFF(DebugKt.DEBUG_PROPERTY_VALUE_OFF);
    
    private final String value;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: CameraRecords.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|2|3|4|5|6|7|9) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        static {
            /*
                expo.modules.camera.next.records.FlashMode[] r0 = expo.modules.camera.next.records.FlashMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                expo.modules.camera.next.records.FlashMode r1 = expo.modules.camera.next.records.FlashMode.AUTO     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                expo.modules.camera.next.records.FlashMode r1 = expo.modules.camera.next.records.FlashMode.OFF     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                expo.modules.camera.next.records.FlashMode r1 = expo.modules.camera.next.records.FlashMode.ON     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: expo.modules.camera.next.records.FlashMode.WhenMappings.<clinit>():void");
        }
    }

    public static EnumEntries<FlashMode> getEntries() {
        return $ENTRIES;
    }

    private FlashMode(String str) {
        this.value = str;
    }

    public final String getValue() {
        return this.value;
    }

    static {
        FlashMode[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries((E[]) (Enum[]) $values);
    }

    public final int mapToLens() {
        int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (i == 1) {
            return 0;
        }
        if (i == 2) {
            return 2;
        }
        if (i == 3) {
            return 1;
        }
        throw new NoWhenBranchMatchedException();
    }
}
