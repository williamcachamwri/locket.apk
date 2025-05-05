package org.koin.mp;

import io.sentry.SentryEvent;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.koin.core.Koin;
import org.koin.core.KoinApplication;
import org.koin.core.context.DefaultContextExtKt;
import org.koin.core.logger.Level;
import org.koin.core.module.Module;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u001c\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bJ\u0006\u0010\f\u001a\u00020\u0006¨\u0006\r"}, d2 = {"Lorg/koin/mp/KoinPlatform;", "", "()V", "getKoin", "Lorg/koin/core/Koin;", "startKoin", "", "modules", "", "Lorg/koin/core/module/Module;", "level", "Lorg/koin/core/logger/Level;", "stopKoin", "koin-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: KoinPlatform.kt */
public final class KoinPlatform {
    public static final KoinPlatform INSTANCE = new KoinPlatform();

    private KoinPlatform() {
    }

    public final void startKoin(List<Module> list, Level level) {
        Intrinsics.checkNotNullParameter(list, SentryEvent.JsonKeys.MODULES);
        Intrinsics.checkNotNullParameter(level, "level");
        DefaultContextExtKt.startKoin((Function1<? super KoinApplication, Unit>) new KoinPlatform$startKoin$1(level, list));
    }

    public final void stopKoin() {
        DefaultContextExtKt.stopKoin();
    }

    public final Koin getKoin() {
        return KoinPlatformTools.INSTANCE.defaultContext().get();
    }
}
