package com.mrousavy.camera.core.types;

import com.mrousavy.camera.core.InvalidTypeScriptUnionError;
import com.mrousavy.camera.core.PixelFormatNotSupportedError;
import com.mrousavy.camera.core.types.JSUnionValue;
import com.mrousavy.camera.core.utils.ImageFormatUtils;
import io.sentry.android.core.SentryLogcatAdapter;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\b\u0002\u0018\u0000 \r2\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002:\u0001\rB\u000f\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0006\u0010\b\u001a\u00020\tR\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\u000e"}, d2 = {"Lcom/mrousavy/camera/core/types/PixelFormat;", "", "Lcom/mrousavy/camera/core/types/JSUnionValue;", "unionValue", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getUnionValue", "()Ljava/lang/String;", "toImageAnalysisFormat", "", "YUV", "RGB", "UNKNOWN", "Companion", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PixelFormat.kt */
public enum PixelFormat implements JSUnionValue {
    YUV("yuv"),
    RGB("rgb"),
    UNKNOWN("unknown");
    
    public static final Companion Companion = null;
    private static final String TAG = "PixelFormat";
    private final String unionValue;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: PixelFormat.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        static {
            /*
                com.mrousavy.camera.core.types.PixelFormat[] r0 = com.mrousavy.camera.core.types.PixelFormat.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.mrousavy.camera.core.types.PixelFormat r1 = com.mrousavy.camera.core.types.PixelFormat.YUV     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.mrousavy.camera.core.types.PixelFormat r1 = com.mrousavy.camera.core.types.PixelFormat.RGB     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mrousavy.camera.core.types.PixelFormat.WhenMappings.<clinit>():void");
        }
    }

    public static EnumEntries<PixelFormat> getEntries() {
        return $ENTRIES;
    }

    private PixelFormat(String str) {
        this.unionValue = str;
    }

    public String getUnionValue() {
        return this.unionValue;
    }

    static {
        PixelFormat[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries((E[]) (Enum[]) $values);
        Companion = new Companion((DefaultConstructorMarker) null);
    }

    public final int toImageAnalysisFormat() {
        int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        int i2 = 1;
        if (i != 1) {
            i2 = 2;
            if (i != 2) {
                throw new PixelFormatNotSupportedError(getUnionValue());
            }
        }
        return i2;
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\bJ\u0012\u0010\t\u001a\u00020\u00022\b\u0010\n\u001a\u0004\u0018\u00010\u0005H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/mrousavy/camera/core/types/PixelFormat$Companion;", "Lcom/mrousavy/camera/core/types/JSUnionValue$Companion;", "Lcom/mrousavy/camera/core/types/PixelFormat;", "()V", "TAG", "", "fromImageFormat", "imageFormat", "", "fromUnionValue", "unionValue", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: PixelFormat.kt */
    public static final class Companion implements JSUnionValue.Companion<PixelFormat> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final PixelFormat fromImageFormat(int i) {
            if (i == 1) {
                return PixelFormat.RGB;
            }
            if (i == 35) {
                return PixelFormat.YUV;
            }
            SentryLogcatAdapter.w(PixelFormat.TAG, "Unknown PixelFormat! " + ImageFormatUtils.Companion.imageFormatToString(i));
            return PixelFormat.UNKNOWN;
        }

        public PixelFormat fromUnionValue(String str) {
            if (str != null) {
                int hashCode = str.hashCode();
                if (hashCode != -284840886) {
                    if (hashCode != 112845) {
                        if (hashCode == 120026 && str.equals("yuv")) {
                            return PixelFormat.YUV;
                        }
                    } else if (str.equals("rgb")) {
                        return PixelFormat.RGB;
                    }
                } else if (str.equals("unknown")) {
                    return PixelFormat.UNKNOWN;
                }
            }
            throw new InvalidTypeScriptUnionError("pixelFormat", str);
        }
    }
}
