package com.facebook.imagepipeline.cache;

import com.facebook.common.internal.Predicate;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AnimatedCache$$ExternalSyntheticLambda0 implements Predicate {
    public final /* synthetic */ String f$0;

    public /* synthetic */ AnimatedCache$$ExternalSyntheticLambda0(String str) {
        this.f$0 = str;
    }

    public final boolean apply(Object obj) {
        return AnimatedCache.removeAnimation$lambda$3(this.f$0, (String) obj);
    }
}
