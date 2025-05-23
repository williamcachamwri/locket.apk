package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: _Sequences.kt */
/* synthetic */ class SequencesKt___SequencesKt$flatMapIndexed$2 extends FunctionReferenceImpl implements Function1<Sequence<? extends R>, Iterator<? extends R>> {
    public static final SequencesKt___SequencesKt$flatMapIndexed$2 INSTANCE = new SequencesKt___SequencesKt$flatMapIndexed$2();

    SequencesKt___SequencesKt$flatMapIndexed$2() {
        super(1, Sequence.class, "iterator", "iterator()Ljava/util/Iterator;", 0);
    }

    public final Iterator<R> invoke(Sequence<? extends R> sequence) {
        Intrinsics.checkNotNullParameter(sequence, "p0");
        return sequence.iterator();
    }
}
