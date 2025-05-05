package org.koin.mp;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.koin.core.KoinApplication;
import org.koin.core.logger.Level;
import org.koin.core.module.Module;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lorg/koin/core/KoinApplication;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: KoinPlatform.kt */
final class KoinPlatform$startKoin$1 extends Lambda implements Function1<KoinApplication, Unit> {
    final /* synthetic */ Level $level;
    final /* synthetic */ List<Module> $modules;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    KoinPlatform$startKoin$1(Level level, List<Module> list) {
        super(1);
        this.$level = level;
        this.$modules = list;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((KoinApplication) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(KoinApplication koinApplication) {
        Intrinsics.checkNotNullParameter(koinApplication, "$this$startKoin");
        koinApplication.logger(KoinPlatformTools.INSTANCE.defaultLogger(this.$level));
        koinApplication.modules(this.$modules);
    }
}
