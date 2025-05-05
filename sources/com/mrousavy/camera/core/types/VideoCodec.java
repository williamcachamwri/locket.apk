package com.mrousavy.camera.core.types;

import com.mrousavy.camera.core.types.JSUnionValue;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\b\u0002\u0018\u0000 \f2\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002:\u0001\fB\u000f\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0006\u0010\b\u001a\u00020\tR\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\nj\u0002\b\u000b¨\u0006\r"}, d2 = {"Lcom/mrousavy/camera/core/types/VideoCodec;", "", "Lcom/mrousavy/camera/core/types/JSUnionValue;", "unionValue", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getUnionValue", "()Ljava/lang/String;", "toVideoEncoder", "", "H264", "H265", "Companion", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoCodec.kt */
public enum VideoCodec implements JSUnionValue {
    H264("h264"),
    H265("h265");
    
    public static final Companion Companion = null;
    private final String unionValue;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: VideoCodec.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        static {
            /*
                com.mrousavy.camera.core.types.VideoCodec[] r0 = com.mrousavy.camera.core.types.VideoCodec.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.mrousavy.camera.core.types.VideoCodec r1 = com.mrousavy.camera.core.types.VideoCodec.H264     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.mrousavy.camera.core.types.VideoCodec r1 = com.mrousavy.camera.core.types.VideoCodec.H265     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mrousavy.camera.core.types.VideoCodec.WhenMappings.<clinit>():void");
        }
    }

    public static EnumEntries<VideoCodec> getEntries() {
        return $ENTRIES;
    }

    private VideoCodec(String str) {
        this.unionValue = str;
    }

    public String getUnionValue() {
        return this.unionValue;
    }

    static {
        VideoCodec[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries((E[]) (Enum[]) $values);
        Companion = new Companion((DefaultConstructorMarker) null);
    }

    public final int toVideoEncoder() {
        int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (i == 1) {
            return 2;
        }
        if (i == 2) {
            return 5;
        }
        throw new NoWhenBranchMatchedException();
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/mrousavy/camera/core/types/VideoCodec$Companion;", "Lcom/mrousavy/camera/core/types/JSUnionValue$Companion;", "Lcom/mrousavy/camera/core/types/VideoCodec;", "()V", "fromUnionValue", "unionValue", "", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: VideoCodec.kt */
    public static final class Companion implements JSUnionValue.Companion<VideoCodec> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public VideoCodec fromUnionValue(String str) {
            if (Intrinsics.areEqual((Object) str, (Object) "h264")) {
                return VideoCodec.H264;
            }
            if (Intrinsics.areEqual((Object) str, (Object) "h265")) {
                return VideoCodec.H265;
            }
            return VideoCodec.H264;
        }
    }
}
