package androidx.media3.common.util;

import java.util.concurrent.ThreadFactory;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Util$$ExternalSyntheticLambda3 implements ThreadFactory {
    public final /* synthetic */ String f$0;

    public /* synthetic */ Util$$ExternalSyntheticLambda3(String str) {
        this.f$0 = str;
    }

    public final Thread newThread(Runnable runnable) {
        return Util.lambda$newSingleThreadExecutor$3(this.f$0, runnable);
    }
}
