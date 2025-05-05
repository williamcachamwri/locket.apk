package com.mrousavy.camera.core.types;

import com.mrousavy.camera.core.types.JSUnionValue;
import expo.modules.devlauncher.launcher.manifest.DevLauncherOrientation;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0006\b\u0002\u0018\u0000 \u000f2\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002:\u0001\u000fB\u000f\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0006\u0010\b\u001a\u00020\u0000J\u0006\u0010\t\u001a\u00020\nR\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000e¨\u0006\u0010"}, d2 = {"Lcom/mrousavy/camera/core/types/Orientation;", "", "Lcom/mrousavy/camera/core/types/JSUnionValue;", "unionValue", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getUnionValue", "()Ljava/lang/String;", "reversed", "toSurfaceRotation", "", "PORTRAIT", "LANDSCAPE_RIGHT", "PORTRAIT_UPSIDE_DOWN", "LANDSCAPE_LEFT", "Companion", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: Orientation.kt */
public enum Orientation implements JSUnionValue {
    PORTRAIT(DevLauncherOrientation.PORTRAIT),
    LANDSCAPE_RIGHT("landscape-right"),
    PORTRAIT_UPSIDE_DOWN("portrait-upside-down"),
    LANDSCAPE_LEFT("landscape-left");
    
    public static final Companion Companion = null;
    private final String unionValue;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: Orientation.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        /* JADX WARNING: Can't wrap try/catch for region: R(11:0|1|2|3|4|5|6|7|8|9|11) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        static {
            /*
                com.mrousavy.camera.core.types.Orientation[] r0 = com.mrousavy.camera.core.types.Orientation.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.mrousavy.camera.core.types.Orientation r1 = com.mrousavy.camera.core.types.Orientation.PORTRAIT     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.mrousavy.camera.core.types.Orientation r1 = com.mrousavy.camera.core.types.Orientation.LANDSCAPE_LEFT     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.mrousavy.camera.core.types.Orientation r1 = com.mrousavy.camera.core.types.Orientation.PORTRAIT_UPSIDE_DOWN     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                com.mrousavy.camera.core.types.Orientation r1 = com.mrousavy.camera.core.types.Orientation.LANDSCAPE_RIGHT     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mrousavy.camera.core.types.Orientation.WhenMappings.<clinit>():void");
        }
    }

    public static EnumEntries<Orientation> getEntries() {
        return $ENTRIES;
    }

    private Orientation(String str) {
        this.unionValue = str;
    }

    public String getUnionValue() {
        return this.unionValue;
    }

    static {
        Orientation[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries((E[]) (Enum[]) $values);
        Companion = new Companion((DefaultConstructorMarker) null);
    }

    public final int toSurfaceRotation() {
        int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (i == 1) {
            return 0;
        }
        if (i == 2) {
            return 3;
        }
        if (i == 3) {
            return 2;
        }
        if (i == 4) {
            return 1;
        }
        throw new NoWhenBranchMatchedException();
    }

    public final Orientation reversed() {
        int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (i == 1) {
            return PORTRAIT;
        }
        if (i == 2) {
            return LANDSCAPE_RIGHT;
        }
        if (i == 3) {
            return PORTRAIT_UPSIDE_DOWN;
        }
        if (i == 4) {
            return LANDSCAPE_LEFT;
        }
        throw new NoWhenBranchMatchedException();
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0006J\u0012\u0010\t\u001a\u00020\u00022\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016¨\u0006\f"}, d2 = {"Lcom/mrousavy/camera/core/types/Orientation$Companion;", "Lcom/mrousavy/camera/core/types/JSUnionValue$Companion;", "Lcom/mrousavy/camera/core/types/Orientation;", "()V", "fromRotationDegrees", "rotationDegrees", "", "fromSurfaceRotation", "rotation", "fromUnionValue", "unionValue", "", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: Orientation.kt */
    public static final class Companion implements JSUnionValue.Companion<Orientation> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public Orientation fromUnionValue(String str) {
            if (str != null) {
                switch (str.hashCode()) {
                    case -1510435861:
                        if (str.equals("portrait-upside-down")) {
                            return Orientation.PORTRAIT_UPSIDE_DOWN;
                        }
                        break;
                    case 687313034:
                        if (str.equals("landscape-right")) {
                            return Orientation.LANDSCAPE_RIGHT;
                        }
                        break;
                    case 729267099:
                        if (str.equals(DevLauncherOrientation.PORTRAIT)) {
                            return Orientation.PORTRAIT;
                        }
                        break;
                    case 1684556761:
                        if (str.equals("landscape-left")) {
                            return Orientation.LANDSCAPE_LEFT;
                        }
                        break;
                }
            }
            return Orientation.PORTRAIT;
        }

        public final Orientation fromRotationDegrees(int i) {
            boolean z = true;
            if (45 <= i && i < 136) {
                return Orientation.LANDSCAPE_LEFT;
            }
            if (135 <= i && i < 226) {
                return Orientation.PORTRAIT_UPSIDE_DOWN;
            }
            if (225 > i || i >= 316) {
                z = false;
            }
            if (z) {
                return Orientation.LANDSCAPE_RIGHT;
            }
            return Orientation.PORTRAIT;
        }

        public final Orientation fromSurfaceRotation(int i) {
            if (i == 0) {
                return Orientation.PORTRAIT;
            }
            if (i == 1) {
                return Orientation.LANDSCAPE_RIGHT;
            }
            if (i == 2) {
                return Orientation.PORTRAIT_UPSIDE_DOWN;
            }
            if (i != 3) {
                return Orientation.PORTRAIT;
            }
            return Orientation.LANDSCAPE_LEFT;
        }
    }
}
