package expo.modules.devlauncher.launcher;

import android.app.Application;
import androidx.media3.common.MimeTypes;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import expo.modules.devlauncher.DevLauncherControllerKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0014J\b\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, d2 = {"Lexpo/modules/devlauncher/launcher/DevLauncherClientHost;", "Lcom/facebook/react/ReactNativeHost;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "getPackages", "", "Lcom/facebook/react/ReactPackage;", "getUseDeveloperSupport", "", "expo-dev-launcher_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevLauncherClientHost.kt */
public final class DevLauncherClientHost extends ReactNativeHost {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DevLauncherClientHost(Application application) {
        super(application);
        Intrinsics.checkNotNullParameter(application, MimeTypes.BASE_TYPE_APPLICATION);
    }

    /* access modifiers changed from: protected */
    public List<ReactPackage> getPackages() {
        throw new IllegalStateException(DevLauncherControllerKt.DEV_LAUNCHER_IS_NOT_AVAILABLE);
    }

    public boolean getUseDeveloperSupport() {
        throw new IllegalStateException(DevLauncherControllerKt.DEV_LAUNCHER_IS_NOT_AVAILABLE);
    }
}
