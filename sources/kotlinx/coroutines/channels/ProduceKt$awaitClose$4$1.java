package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CancellableContinuation;

@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
/* compiled from: Produce.kt */
final class ProduceKt$awaitClose$4$1 implements Function1<Throwable, Unit> {
    final /* synthetic */ CancellableContinuation<Unit> $cont;

    ProduceKt$awaitClose$4$1(CancellableContinuation<? super Unit> cancellableContinuation) {
        this.$cont = cancellableContinuation;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Throwable th) {
        Result.Companion companion = Result.Companion;
        this.$cont.resumeWith(Result.m2444constructorimpl(Unit.INSTANCE));
    }
}
