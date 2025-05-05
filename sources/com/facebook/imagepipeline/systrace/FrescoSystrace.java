package com.facebook.imagepipeline.systrace;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001:\u0003\u0018\u0019\u001aB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0007J\u0010\u0010\u000e\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\rH\u0007J\b\u0010\u000f\u001a\u00020\u000bH\u0007J\b\u0010\u0010\u001a\u00020\u0011H\u0007J\u0012\u0010\u0012\u001a\u00020\u000b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0007J-\u0010\u0013\u001a\u0002H\u0014\"\u0004\b\u0000\u0010\u00142\u0006\u0010\f\u001a\u00020\r2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00140\u0016H\bø\u0001\u0000¢\u0006\u0002\u0010\u0017R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\u00068BX\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t\u0002\u0007\n\u0005\b20\u0001¨\u0006\u001b"}, d2 = {"Lcom/facebook/imagepipeline/systrace/FrescoSystrace;", "", "()V", "NO_OP_ARGS_BUILDER", "Lcom/facebook/imagepipeline/systrace/FrescoSystrace$ArgsBuilder;", "_instance", "Lcom/facebook/imagepipeline/systrace/FrescoSystrace$Systrace;", "instance", "getInstance", "()Lcom/facebook/imagepipeline/systrace/FrescoSystrace$Systrace;", "beginSection", "", "name", "", "beginSectionWithArgs", "endSection", "isTracing", "", "provide", "traceSection", "T", "block", "Lkotlin/Function0;", "(Ljava/lang/String;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "ArgsBuilder", "NoOpArgsBuilder", "Systrace", "imagepipeline-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: FrescoSystrace.kt */
public final class FrescoSystrace {
    public static final FrescoSystrace INSTANCE = new FrescoSystrace();
    public static final ArgsBuilder NO_OP_ARGS_BUILDER = new NoOpArgsBuilder();
    private static Systrace _instance;

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\u0010\b\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0001H&J\u0018\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0018\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0007H&J\u0018\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&¨\u0006\u000b"}, d2 = {"Lcom/facebook/imagepipeline/systrace/FrescoSystrace$ArgsBuilder;", "", "arg", "key", "", "value", "", "", "", "flush", "", "imagepipeline-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: FrescoSystrace.kt */
    public interface ArgsBuilder {
        ArgsBuilder arg(String str, double d);

        ArgsBuilder arg(String str, int i);

        ArgsBuilder arg(String str, long j);

        ArgsBuilder arg(String str, Object obj);

        void flush();
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\b\u001a\u00020\u0003H&J\b\u0010\t\u001a\u00020\nH&¨\u0006\u000b"}, d2 = {"Lcom/facebook/imagepipeline/systrace/FrescoSystrace$Systrace;", "", "beginSection", "", "name", "", "beginSectionWithArgs", "Lcom/facebook/imagepipeline/systrace/FrescoSystrace$ArgsBuilder;", "endSection", "isTracing", "", "imagepipeline-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: FrescoSystrace.kt */
    public interface Systrace {
        void beginSection(String str);

        ArgsBuilder beginSectionWithArgs(String str);

        void endSection();

        boolean isTracing();
    }

    private FrescoSystrace() {
    }

    @JvmStatic
    public static final void provide(Systrace systrace) {
        _instance = systrace;
    }

    @JvmStatic
    public static final void beginSection(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        INSTANCE.getInstance().beginSection(str);
    }

    @JvmStatic
    public static final ArgsBuilder beginSectionWithArgs(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        return INSTANCE.getInstance().beginSectionWithArgs(str);
    }

    @JvmStatic
    public static final void endSection() {
        INSTANCE.getInstance().endSection();
    }

    public final <T> T traceSection(String str, Function0<? extends T> function0) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(function0, "block");
        if (!isTracing()) {
            return function0.invoke();
        }
        beginSection(str);
        try {
            return function0.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            endSection();
            InlineMarker.finallyEnd(1);
        }
    }

    @JvmStatic
    public static final boolean isTracing() {
        return INSTANCE.getInstance().isTracing();
    }

    private final Systrace getInstance() {
        DefaultFrescoSystrace defaultFrescoSystrace;
        Systrace systrace = _instance;
        if (systrace != null) {
            return systrace;
        }
        synchronized (FrescoSystrace.class) {
            defaultFrescoSystrace = new DefaultFrescoSystrace();
            _instance = defaultFrescoSystrace;
        }
        return defaultFrescoSystrace;
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0002\u0010\u0006\n\u0002\u0010\b\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\bH\u0016J\u0018\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\tH\u0016J\u0018\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, d2 = {"Lcom/facebook/imagepipeline/systrace/FrescoSystrace$NoOpArgsBuilder;", "Lcom/facebook/imagepipeline/systrace/FrescoSystrace$ArgsBuilder;", "()V", "arg", "key", "", "value", "", "", "", "", "flush", "", "imagepipeline-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: FrescoSystrace.kt */
    private static final class NoOpArgsBuilder implements ArgsBuilder {
        public void flush() {
        }

        public ArgsBuilder arg(String str, Object obj) {
            Intrinsics.checkNotNullParameter(str, "key");
            Intrinsics.checkNotNullParameter(obj, "value");
            return this;
        }

        public ArgsBuilder arg(String str, int i) {
            Intrinsics.checkNotNullParameter(str, "key");
            return this;
        }

        public ArgsBuilder arg(String str, long j) {
            Intrinsics.checkNotNullParameter(str, "key");
            return this;
        }

        public ArgsBuilder arg(String str, double d) {
            Intrinsics.checkNotNullParameter(str, "key");
            return this;
        }
    }
}
