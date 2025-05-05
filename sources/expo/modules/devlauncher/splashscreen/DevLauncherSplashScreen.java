package expo.modules.devlauncher.splashscreen;

import android.content.Context;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import expo.modules.devlauncher.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lexpo/modules/devlauncher/splashscreen/DevLauncherSplashScreen;", "Landroid/widget/RelativeLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "expo-dev-launcher_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevLauncherSplashScreen.kt */
public final class DevLauncherSplashScreen extends RelativeLayout {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DevLauncherSplashScreen(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        setBackgroundColor(-1);
        float applyDimension = TypedValue.applyDimension(1, 85.0f, context.getResources().getDisplayMetrics());
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(R.drawable._expodevclientcomponents_assets_logoicon);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) applyDimension, -2);
        layoutParams.addRule(13, -1);
        imageView.setLayoutParams(layoutParams);
        addView(imageView);
    }
}
