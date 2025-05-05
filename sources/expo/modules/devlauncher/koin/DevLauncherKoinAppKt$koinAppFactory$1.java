package expo.modules.devlauncher.koin;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.koin.core.KoinApplication;
import org.koin.dsl.KoinApplicationKt;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lorg/koin/core/KoinApplication;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevLauncherKoinApp.kt */
final class DevLauncherKoinAppKt$koinAppFactory$1 extends Lambda implements Function0<KoinApplication> {
    public static final DevLauncherKoinAppKt$koinAppFactory$1 INSTANCE = new DevLauncherKoinAppKt$koinAppFactory$1();

    DevLauncherKoinAppKt$koinAppFactory$1() {
        super(0);
    }

    public final KoinApplication invoke() {
        return KoinApplicationKt.koinApplication(AnonymousClass1.INSTANCE);
    }
}
