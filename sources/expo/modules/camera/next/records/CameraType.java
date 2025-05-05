package expo.modules.camera.next.records;

import androidx.camera.core.CameraSelector;
import expo.modules.kotlin.types.Enumerable;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002B\u000f\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0006\u0010\b\u001a\u00020\tJ\u0006\u0010\n\u001a\u00020\u000bR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\fj\u0002\b\r¨\u0006\u000e"}, d2 = {"Lexpo/modules/camera/next/records/CameraType;", "", "Lexpo/modules/kotlin/types/Enumerable;", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "mapToCharacteristic", "", "mapToSelector", "Landroidx/camera/core/CameraSelector;", "FRONT", "BACK", "expo-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CameraRecords.kt */
public enum CameraType implements Enumerable {
    FRONT("front"),
    BACK("back");
    
    private final String value;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: CameraRecords.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        static {
            /*
                expo.modules.camera.next.records.CameraType[] r0 = expo.modules.camera.next.records.CameraType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                expo.modules.camera.next.records.CameraType r1 = expo.modules.camera.next.records.CameraType.FRONT     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                expo.modules.camera.next.records.CameraType r1 = expo.modules.camera.next.records.CameraType.BACK     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: expo.modules.camera.next.records.CameraType.WhenMappings.<clinit>():void");
        }
    }

    public static EnumEntries<CameraType> getEntries() {
        return $ENTRIES;
    }

    private CameraType(String str) {
        this.value = str;
    }

    public final String getValue() {
        return this.value;
    }

    static {
        CameraType[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries((E[]) (Enum[]) $values);
    }

    public final CameraSelector mapToSelector() {
        CameraSelector cameraSelector;
        int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (i == 1) {
            cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA;
        } else if (i == 2) {
            cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA;
        } else {
            throw new NoWhenBranchMatchedException();
        }
        Intrinsics.checkNotNull(cameraSelector);
        return cameraSelector;
    }

    public final int mapToCharacteristic() {
        int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (i == 1) {
            return 0;
        }
        if (i == 2) {
            return 1;
        }
        throw new NoWhenBranchMatchedException();
    }
}
