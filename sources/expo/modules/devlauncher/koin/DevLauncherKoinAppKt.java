package expo.modules.devlauncher.koin;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.koin.core.Koin;
import org.koin.core.KoinApplication;
import org.koin.core.module.Module;
import org.koin.dsl.ModuleDSLKt;

@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0006\u0010\t\u001a\u00020\n\"\u0011\u0010\u0000\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0011\u0010\u0004\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0003\"\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"DevLauncherBaseModule", "Lorg/koin/core/module/Module;", "getDevLauncherBaseModule", "()Lorg/koin/core/module/Module;", "DevLauncherTestModule", "getDevLauncherTestModule", "koinAppFactory", "Lkotlin/Function0;", "Lorg/koin/core/KoinApplication;", "devLauncherKoin", "Lorg/koin/core/Koin;", "expo-dev-launcher_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevLauncherKoinApp.kt */
public final class DevLauncherKoinAppKt {
    private static final Module DevLauncherBaseModule = ModuleDSLKt.module$default(false, DevLauncherKoinAppKt$DevLauncherBaseModule$1.INSTANCE, 1, (Object) null);
    private static final Module DevLauncherTestModule = ModuleDSLKt.module$default(false, DevLauncherKoinAppKt$DevLauncherTestModule$1.INSTANCE, 1, (Object) null);
    /* access modifiers changed from: private */
    public static final Function0<KoinApplication> koinAppFactory = DevLauncherKoinAppKt$koinAppFactory$1.INSTANCE;

    public static final Module getDevLauncherTestModule() {
        return DevLauncherTestModule;
    }

    public static final Module getDevLauncherBaseModule() {
        return DevLauncherBaseModule;
    }

    public static final Koin devLauncherKoin() {
        return DevLauncherKoinContext.INSTANCE.getApp().getKoin();
    }
}
