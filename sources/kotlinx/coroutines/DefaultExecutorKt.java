package kotlinx.coroutines;

import kotlin.Metadata;
import kotlinx.coroutines.internal.SystemPropsKt;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\b\u0010\b\u001a\u00020\u0003H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000\"\u001c\u0010\u0002\u001a\u00020\u00038\u0000X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\t"}, d2 = {"defaultMainDelayOptIn", "", "DefaultDelay", "Lkotlinx/coroutines/Delay;", "getDefaultDelay$annotations", "()V", "getDefaultDelay", "()Lkotlinx/coroutines/Delay;", "initializeDefaultDelay", "kotlinx-coroutines-core"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* compiled from: DefaultExecutor.kt */
public final class DefaultExecutorKt {
    private static final Delay DefaultDelay = initializeDefaultDelay();
    private static final boolean defaultMainDelayOptIn = SystemPropsKt.systemProp("kotlinx.coroutines.main.delay", false);

    public static /* synthetic */ void getDefaultDelay$annotations() {
    }

    public static final Delay getDefaultDelay() {
        return DefaultDelay;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0015, code lost:
        if (r1 == false) goto L_0x0017;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final kotlinx.coroutines.Delay initializeDefaultDelay() {
        /*
            boolean r0 = defaultMainDelayOptIn
            if (r0 != 0) goto L_0x0009
            kotlinx.coroutines.DefaultExecutor r0 = kotlinx.coroutines.DefaultExecutor.INSTANCE
            kotlinx.coroutines.Delay r0 = (kotlinx.coroutines.Delay) r0
            return r0
        L_0x0009:
            kotlinx.coroutines.MainCoroutineDispatcher r0 = kotlinx.coroutines.Dispatchers.getMain()
            boolean r1 = kotlinx.coroutines.internal.MainDispatchersKt.isMissing(r0)
            if (r1 != 0) goto L_0x0017
            boolean r1 = r0 instanceof kotlinx.coroutines.Delay
            if (r1 != 0) goto L_0x0019
        L_0x0017:
            kotlinx.coroutines.DefaultExecutor r0 = kotlinx.coroutines.DefaultExecutor.INSTANCE
        L_0x0019:
            kotlinx.coroutines.Delay r0 = (kotlinx.coroutines.Delay) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.DefaultExecutorKt.initializeDefaultDelay():kotlinx.coroutines.Delay");
    }
}
