package kotlinx.coroutines.debug.internal;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.debug.internal.DebugProbesImpl;

@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
/* compiled from: DebugProbesImpl.kt */
public final class DebugProbesImpl$dumpDebuggerInfo$$inlined$dumpCoroutinesInfoImpl$1 implements Function1<DebugProbesImpl.CoroutineOwner<?>, DebuggerInfo> {
    public final DebuggerInfo invoke(DebugProbesImpl.CoroutineOwner<?> coroutineOwner) {
        CoroutineContext context;
        if (!DebugProbesImpl.INSTANCE.isFinished(coroutineOwner) && (context = coroutineOwner.info.getContext()) != null) {
            return new DebuggerInfo(coroutineOwner.info, context);
        }
        return null;
    }
}
