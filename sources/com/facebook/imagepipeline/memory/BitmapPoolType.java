package com.facebook.imagepipeline.memory;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0002\b\u0002\b\u0002\u0018\u0000 \u00022\u00020\u0001:\u0001\u0002B\u0000¨\u0006\u0003"}, d2 = {"Lcom/facebook/imagepipeline/memory/BitmapPoolType;", "", "Companion", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@Retention(RetentionPolicy.RUNTIME)
/* compiled from: BitmapPoolType.kt */
public @interface BitmapPoolType {
    public static final Companion Companion = Companion.$$INSTANCE;
    public static final String DEFAULT = "legacy";
    public static final String DUMMY = "dummy";
    public static final String DUMMY_WITH_TRACKING = "dummy_with_tracking";
    public static final String EXPERIMENTAL = "experimental";
    public static final String LEGACY = "legacy";
    public static final String LEGACY_DEFAULT_PARAMS = "legacy_default_params";

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/facebook/imagepipeline/memory/BitmapPoolType$Companion;", "", "()V", "DEFAULT", "", "DUMMY", "DUMMY_WITH_TRACKING", "EXPERIMENTAL", "LEGACY", "LEGACY_DEFAULT_PARAMS", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: BitmapPoolType.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        public static final String DEFAULT = "legacy";
        public static final String DUMMY = "dummy";
        public static final String DUMMY_WITH_TRACKING = "dummy_with_tracking";
        public static final String EXPERIMENTAL = "experimental";
        public static final String LEGACY = "legacy";
        public static final String LEGACY_DEFAULT_PARAMS = "legacy_default_params";

        private Companion() {
        }
    }
}
