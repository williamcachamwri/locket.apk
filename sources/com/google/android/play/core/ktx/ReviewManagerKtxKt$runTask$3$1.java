package com.google.android.play.core.ktx;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "T", "it", "", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* compiled from: com.google.android.play:review-ktx@@2.0.0 */
final class ReviewManagerKtxKt$runTask$3$1 extends Lambda implements Function1<Throwable, Unit> {
    final /* synthetic */ Function0<Unit> $onCanceled;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ReviewManagerKtxKt$runTask$3$1(Function0<Unit> function0) {
        super(1);
        this.$onCanceled = function0;
    }

    public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Throwable th) {
        this.$onCanceled.invoke();
    }
}
