package expo.modules.splashscreen;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\r"}, d2 = {"Lexpo/modules/splashscreen/SplashScreenView;", "Landroid/widget/RelativeLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "imageView", "Landroid/widget/ImageView;", "getImageView", "()Landroid/widget/ImageView;", "configureImageViewResizeMode", "", "resizeMode", "Lexpo/modules/splashscreen/SplashScreenImageResizeMode;", "expo-splash-screen_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: SplashScreenView.kt */
public final class SplashScreenView extends RelativeLayout {
    private final ImageView imageView;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: SplashScreenView.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|2|3|4|5|6|7|9) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        static {
            /*
                expo.modules.splashscreen.SplashScreenImageResizeMode[] r0 = expo.modules.splashscreen.SplashScreenImageResizeMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                expo.modules.splashscreen.SplashScreenImageResizeMode r1 = expo.modules.splashscreen.SplashScreenImageResizeMode.NATIVE     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                expo.modules.splashscreen.SplashScreenImageResizeMode r1 = expo.modules.splashscreen.SplashScreenImageResizeMode.CONTAIN     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                expo.modules.splashscreen.SplashScreenImageResizeMode r1 = expo.modules.splashscreen.SplashScreenImageResizeMode.COVER     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: expo.modules.splashscreen.SplashScreenView.WhenMappings.<clinit>():void");
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SplashScreenView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        ImageView imageView2 = new ImageView(context);
        imageView2.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        this.imageView = imageView2;
        setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        addView(imageView2);
    }

    public final ImageView getImageView() {
        return this.imageView;
    }

    public final void configureImageViewResizeMode(SplashScreenImageResizeMode splashScreenImageResizeMode) {
        Intrinsics.checkNotNullParameter(splashScreenImageResizeMode, ViewProps.RESIZE_MODE);
        this.imageView.setScaleType(splashScreenImageResizeMode.getScaleType());
        if (WhenMappings.$EnumSwitchMapping$0[splashScreenImageResizeMode.ordinal()] == 2) {
            this.imageView.setAdjustViewBounds(true);
        }
    }
}
