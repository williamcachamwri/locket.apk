package expo.modules.splashscreen;

import android.widget.ImageView;
import io.sentry.protocol.SentryStackFrame;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0002\u0018\u0000 \f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\fB\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\r"}, d2 = {"Lexpo/modules/splashscreen/SplashScreenImageResizeMode;", "", "scaleType", "Landroid/widget/ImageView$ScaleType;", "resizeMode", "", "(Ljava/lang/String;ILandroid/widget/ImageView$ScaleType;Ljava/lang/String;)V", "getScaleType", "()Landroid/widget/ImageView$ScaleType;", "CONTAIN", "COVER", "NATIVE", "Companion", "expo-splash-screen_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: SplashScreenImageResizeMode.kt */
public enum SplashScreenImageResizeMode {
    CONTAIN(ImageView.ScaleType.FIT_CENTER, "contain"),
    COVER(ImageView.ScaleType.CENTER_CROP, "cover"),
    NATIVE(ImageView.ScaleType.CENTER, SentryStackFrame.JsonKeys.NATIVE);
    
    public static final Companion Companion = null;
    /* access modifiers changed from: private */
    public final String resizeMode;
    private final ImageView.ScaleType scaleType;

    @JvmStatic
    public static final SplashScreenImageResizeMode fromString(String str) {
        return Companion.fromString(str);
    }

    public static EnumEntries<SplashScreenImageResizeMode> getEntries() {
        return $ENTRIES;
    }

    private SplashScreenImageResizeMode(ImageView.ScaleType scaleType2, String str) {
        this.scaleType = scaleType2;
        this.resizeMode = str;
    }

    public final ImageView.ScaleType getScaleType() {
        return this.scaleType;
    }

    static {
        SplashScreenImageResizeMode[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries((E[]) (Enum[]) $values);
        Companion = new Companion((DefaultConstructorMarker) null);
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lexpo/modules/splashscreen/SplashScreenImageResizeMode$Companion;", "", "()V", "fromString", "Lexpo/modules/splashscreen/SplashScreenImageResizeMode;", "resizeMode", "", "expo-splash-screen_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: SplashScreenImageResizeMode.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final SplashScreenImageResizeMode fromString(String str) {
            for (SplashScreenImageResizeMode splashScreenImageResizeMode : SplashScreenImageResizeMode.values()) {
                if (Intrinsics.areEqual((Object) splashScreenImageResizeMode.resizeMode, (Object) str)) {
                    return splashScreenImageResizeMode;
                }
            }
            return null;
        }
    }
}
