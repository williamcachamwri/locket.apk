package expo.modules.devlauncher.koin;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.qualifier.Qualifier;
import org.koin.mp.KoinPlatformTools;

@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001aR\u0010\u0000\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\u0016\b\n\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nj\u0004\u0018\u0001`\fH\bø\u0001\u0000\u0002\u0007\n\u0005\b20\u0001¨\u0006\r"}, d2 = {"optInject", "Lkotlin/Lazy;", "T", "", "Lexpo/modules/devlauncher/koin/DevLauncherKoinComponent;", "qualifier", "Lorg/koin/core/qualifier/Qualifier;", "mode", "Lkotlin/LazyThreadSafetyMode;", "parameters", "Lkotlin/Function0;", "Lorg/koin/core/parameter/ParametersHolder;", "Lorg/koin/core/parameter/ParametersDefinition;", "expo-dev-launcher_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevLauncherKoinComponent.kt */
public final class DevLauncherKoinComponentKt {
    public static /* synthetic */ Lazy optInject$default(DevLauncherKoinComponent devLauncherKoinComponent, Qualifier qualifier, LazyThreadSafetyMode lazyThreadSafetyMode, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            qualifier = null;
        }
        if ((i & 2) != 0) {
            lazyThreadSafetyMode = KoinPlatformTools.INSTANCE.defaultLazyMode();
        }
        if ((i & 4) != 0) {
            function0 = null;
        }
        Intrinsics.checkNotNullParameter(devLauncherKoinComponent, "<this>");
        Intrinsics.checkNotNullParameter(lazyThreadSafetyMode, "mode");
        Intrinsics.needClassReification();
        return LazyKt.lazy(lazyThreadSafetyMode, new DevLauncherKoinComponentKt$optInject$1(devLauncherKoinComponent, qualifier, function0));
    }

    public static final /* synthetic */ <T> Lazy<T> optInject(DevLauncherKoinComponent devLauncherKoinComponent, Qualifier qualifier, LazyThreadSafetyMode lazyThreadSafetyMode, Function0<? extends ParametersHolder> function0) {
        Intrinsics.checkNotNullParameter(devLauncherKoinComponent, "<this>");
        Intrinsics.checkNotNullParameter(lazyThreadSafetyMode, "mode");
        Intrinsics.needClassReification();
        return LazyKt.lazy(lazyThreadSafetyMode, new DevLauncherKoinComponentKt$optInject$1(devLauncherKoinComponent, qualifier, function0));
    }
}
