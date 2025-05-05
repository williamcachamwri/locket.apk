package org.koin.core;

import io.sentry.SentryEvent;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.koin.core.logger.Level;
import org.koin.core.logger.Logger;
import org.koin.core.module.KoinApplicationDslMarker;
import org.koin.core.module.Module;
import org.koin.mp.KoinPlatformTimeTools;
import org.koin.mp.KoinPlatformTools;

@KoinApplicationDslMarker
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0007\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0004J\u0006\u0010\u000b\u001a\u00020\tJ\u0006\u0010\f\u001a\u00020\tJ\u0016\u0010\r\u001a\u00020\t2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0002J\u000e\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0012J\u001f\u0010\u000e\u001a\u00020\u00002\u0012\u0010\u000e\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00100\u0013\"\u00020\u0010¢\u0006\u0002\u0010\u0014J\u0014\u0010\u000e\u001a\u00020\u00002\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fJ\u000e\u0010\u000e\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u0010J\u0010\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0016\u001a\u00020\u0017J\u001a\u0010\u0018\u001a\u00020\u00002\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u00010\u001aJ\u001b\u0010\u001c\u001a\u00020\t2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0000¢\u0006\u0002\b\u001dJ\u0015\u0010\u001c\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\u0010H\u0000¢\u0006\u0002\b\u001dR\u000e\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006 "}, d2 = {"Lorg/koin/core/KoinApplication;", "", "()V", "allowOverride", "", "koin", "Lorg/koin/core/Koin;", "getKoin", "()Lorg/koin/core/Koin;", "", "override", "close", "createEagerInstances", "loadModules", "modules", "", "Lorg/koin/core/module/Module;", "logger", "Lorg/koin/core/logger/Logger;", "", "([Lorg/koin/core/module/Module;)Lorg/koin/core/KoinApplication;", "printLogger", "level", "Lorg/koin/core/logger/Level;", "properties", "values", "", "", "unloadModules", "unloadModules$koin_core", "module", "Companion", "koin-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: KoinApplication.kt */
public final class KoinApplication {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private boolean allowOverride;
    private final Koin koin;

    public /* synthetic */ KoinApplication(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private KoinApplication() {
        this.koin = new Koin();
        this.allowOverride = true;
    }

    public final Koin getKoin() {
        return this.koin;
    }

    public final KoinApplication modules(Module module) {
        Intrinsics.checkNotNullParameter(module, SentryEvent.JsonKeys.MODULES);
        return modules((List<Module>) CollectionsKt.listOf(module));
    }

    public final KoinApplication modules(Module... moduleArr) {
        Intrinsics.checkNotNullParameter(moduleArr, SentryEvent.JsonKeys.MODULES);
        return modules((List<Module>) ArraysKt.toList((T[]) moduleArr));
    }

    public final KoinApplication modules(List<Module> list) {
        Intrinsics.checkNotNullParameter(list, SentryEvent.JsonKeys.MODULES);
        if (this.koin.getLogger().isAt(Level.INFO)) {
            long timeInNanoSeconds = KoinPlatformTimeTools.INSTANCE.getTimeInNanoSeconds();
            loadModules(list);
            this.koin.getLogger().display(Level.INFO, "loaded " + this.koin.getInstanceRegistry().size() + " definitions in " + ((Number) new Pair(Unit.INSTANCE, Double.valueOf(((double) (KoinPlatformTimeTools.INSTANCE.getTimeInNanoSeconds() - timeInNanoSeconds)) / 1000000.0d)).getSecond()).doubleValue() + " ms");
        } else {
            loadModules(list);
        }
        return this;
    }

    public final void createEagerInstances() {
        this.koin.createEagerInstances();
    }

    public final void allowOverride(boolean z) {
        this.allowOverride = z;
    }

    private final void loadModules(List<Module> list) {
        this.koin.loadModules(list, this.allowOverride);
    }

    public final KoinApplication properties(Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(map, "values");
        this.koin.getPropertyRegistry().saveProperties(map);
        return this;
    }

    public final KoinApplication logger(Logger logger) {
        Intrinsics.checkNotNullParameter(logger, SentryEvent.JsonKeys.LOGGER);
        this.koin.setupLogger(logger);
        return this;
    }

    public static /* synthetic */ KoinApplication printLogger$default(KoinApplication koinApplication, Level level, int i, Object obj) {
        if ((i & 1) != 0) {
            level = Level.INFO;
        }
        return koinApplication.printLogger(level);
    }

    public final KoinApplication printLogger(Level level) {
        Intrinsics.checkNotNullParameter(level, "level");
        this.koin.setupLogger(KoinPlatformTools.INSTANCE.defaultLogger(level));
        return this;
    }

    public final void close() {
        this.koin.close();
    }

    public final void unloadModules$koin_core(List<Module> list) {
        Intrinsics.checkNotNullParameter(list, SentryEvent.JsonKeys.MODULES);
        this.koin.unloadModules(list);
    }

    public final void unloadModules$koin_core(Module module) {
        Intrinsics.checkNotNullParameter(module, "module");
        this.koin.unloadModules(CollectionsKt.listOf(module));
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lorg/koin/core/KoinApplication$Companion;", "", "()V", "init", "Lorg/koin/core/KoinApplication;", "koin-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: KoinApplication.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KoinApplication init() {
            return new KoinApplication((DefaultConstructorMarker) null);
        }
    }
}
