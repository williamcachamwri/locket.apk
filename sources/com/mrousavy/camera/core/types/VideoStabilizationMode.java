package com.mrousavy.camera.core.types;

import com.facebook.hermes.intl.Constants;
import com.mrousavy.camera.core.InvalidTypeScriptUnionError;
import com.mrousavy.camera.core.types.JSUnionValue;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DebugKt;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0002\u0018\u0000 \u00132\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002:\u0001\u0013B\u000f\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0000R\u0014\u0010\u0006\u001a\u00020\u00078BX\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bj\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012¨\u0006\u0014"}, d2 = {"Lcom/mrousavy/camera/core/types/VideoStabilizationMode;", "", "Lcom/mrousavy/camera/core/types/JSUnionValue;", "unionValue", "", "(Ljava/lang/String;ILjava/lang/String;)V", "score", "", "getScore", "()I", "getUnionValue", "()Ljava/lang/String;", "isAtLeast", "", "mode", "OFF", "STANDARD", "CINEMATIC", "CINEMATIC_EXTENDED", "Companion", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoStabilizationMode.kt */
public enum VideoStabilizationMode implements JSUnionValue {
    OFF(DebugKt.DEBUG_PROPERTY_VALUE_OFF),
    STANDARD(Constants.COLLATION_STANDARD),
    CINEMATIC("cinematic"),
    CINEMATIC_EXTENDED("cinematic-extended");
    
    public static final Companion Companion = null;
    private final String unionValue;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: VideoStabilizationMode.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        /* JADX WARNING: Can't wrap try/catch for region: R(11:0|1|2|3|4|5|6|7|8|9|11) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        static {
            /*
                com.mrousavy.camera.core.types.VideoStabilizationMode[] r0 = com.mrousavy.camera.core.types.VideoStabilizationMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.mrousavy.camera.core.types.VideoStabilizationMode r1 = com.mrousavy.camera.core.types.VideoStabilizationMode.OFF     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.mrousavy.camera.core.types.VideoStabilizationMode r1 = com.mrousavy.camera.core.types.VideoStabilizationMode.STANDARD     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.mrousavy.camera.core.types.VideoStabilizationMode r1 = com.mrousavy.camera.core.types.VideoStabilizationMode.CINEMATIC     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                com.mrousavy.camera.core.types.VideoStabilizationMode r1 = com.mrousavy.camera.core.types.VideoStabilizationMode.CINEMATIC_EXTENDED     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mrousavy.camera.core.types.VideoStabilizationMode.WhenMappings.<clinit>():void");
        }
    }

    public static EnumEntries<VideoStabilizationMode> getEntries() {
        return $ENTRIES;
    }

    private VideoStabilizationMode(String str) {
        this.unionValue = str;
    }

    public String getUnionValue() {
        return this.unionValue;
    }

    static {
        VideoStabilizationMode[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries((E[]) (Enum[]) $values);
        Companion = new Companion((DefaultConstructorMarker) null);
    }

    private final int getScore() {
        int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (i == 1) {
            return 0;
        }
        if (i == 2) {
            return 1;
        }
        if (i == 3) {
            return 2;
        }
        if (i == 4) {
            return 3;
        }
        throw new NoWhenBranchMatchedException();
    }

    public final boolean isAtLeast(VideoStabilizationMode videoStabilizationMode) {
        Intrinsics.checkNotNullParameter(videoStabilizationMode, "mode");
        return getScore() >= videoStabilizationMode.getScore();
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/mrousavy/camera/core/types/VideoStabilizationMode$Companion;", "Lcom/mrousavy/camera/core/types/JSUnionValue$Companion;", "Lcom/mrousavy/camera/core/types/VideoStabilizationMode;", "()V", "fromUnionValue", "unionValue", "", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: VideoStabilizationMode.kt */
    public static final class Companion implements JSUnionValue.Companion<VideoStabilizationMode> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public VideoStabilizationMode fromUnionValue(String str) {
            if (str != null) {
                switch (str.hashCode()) {
                    case -1348796151:
                        if (str.equals("cinematic-extended")) {
                            return VideoStabilizationMode.CINEMATIC_EXTENDED;
                        }
                        break;
                    case 109935:
                        if (str.equals(DebugKt.DEBUG_PROPERTY_VALUE_OFF)) {
                            return VideoStabilizationMode.OFF;
                        }
                        break;
                    case 3005871:
                        if (str.equals("auto")) {
                            return VideoStabilizationMode.OFF;
                        }
                        break;
                    case 1312628413:
                        if (str.equals(Constants.COLLATION_STANDARD)) {
                            return VideoStabilizationMode.STANDARD;
                        }
                        break;
                    case 1598495741:
                        if (str.equals("cinematic")) {
                            return VideoStabilizationMode.CINEMATIC;
                        }
                        break;
                }
            }
            throw new InvalidTypeScriptUnionError("videoStabilizationMode", str);
        }
    }
}
