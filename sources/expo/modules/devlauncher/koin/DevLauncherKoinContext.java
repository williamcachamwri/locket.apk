package expo.modules.devlauncher.koin;

import kotlin.Metadata;
import org.koin.core.KoinApplication;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\b\u001a\u00020\tR\u0011\u0010\u0003\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lexpo/modules/devlauncher/koin/DevLauncherKoinContext;", "", "()V", "app", "Lorg/koin/core/KoinApplication;", "getApp", "()Lorg/koin/core/KoinApplication;", "internalApp", "reinitialize", "", "expo-dev-launcher_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevLauncherKoinApp.kt */
public final class DevLauncherKoinContext {
    public static final DevLauncherKoinContext INSTANCE = new DevLauncherKoinContext();
    private static KoinApplication internalApp = ((KoinApplication) DevLauncherKoinAppKt.koinAppFactory.invoke());

    private DevLauncherKoinContext() {
    }

    public final void reinitialize() {
        internalApp.close();
        internalApp = (KoinApplication) DevLauncherKoinAppKt.koinAppFactory.invoke();
    }

    public final KoinApplication getApp() {
        return internalApp;
    }
}
