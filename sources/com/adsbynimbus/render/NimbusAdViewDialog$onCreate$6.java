package com.adsbynimbus.render;

import android.widget.ProgressBar;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "com.adsbynimbus.render.NimbusAdViewDialog$onCreate$6", f = "NimbusAdViewDialog.kt", i = {}, l = {170}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: NimbusAdViewDialog.kt */
final class NimbusAdViewDialog$onCreate$6 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ NimbusAdViewDialog this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    NimbusAdViewDialog$onCreate$6(NimbusAdViewDialog nimbusAdViewDialog, Continuation<? super NimbusAdViewDialog$onCreate$6> continuation) {
        super(2, continuation);
        this.this$0 = nimbusAdViewDialog;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new NimbusAdViewDialog$onCreate$6(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((NimbusAdViewDialog$onCreate$6) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        boolean z = true;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (DelayKt.delay(this.this$0.getLoadTimeout$render_release(), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ProgressBar progressBar = this.this$0.progressBar;
        if (progressBar == null || progressBar.getVisibility() != 0) {
            z = false;
        }
        if (z) {
            this.this$0.makeDismissible();
        }
        return Unit.INSTANCE;
    }
}
