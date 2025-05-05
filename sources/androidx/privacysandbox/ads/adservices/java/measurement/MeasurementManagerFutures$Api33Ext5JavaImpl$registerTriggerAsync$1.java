package androidx.privacysandbox.ads.adservices.java.measurement;

import android.net.Uri;
import androidx.privacysandbox.ads.adservices.java.measurement.MeasurementManagerFutures;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.privacysandbox.ads.adservices.java.measurement.MeasurementManagerFutures$Api33Ext5JavaImpl$registerTriggerAsync$1", f = "MeasurementManagerFutures.kt", i = {}, l = {141}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MeasurementManagerFutures.kt */
final class MeasurementManagerFutures$Api33Ext5JavaImpl$registerTriggerAsync$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Uri $trigger;
    int label;
    final /* synthetic */ MeasurementManagerFutures.Api33Ext5JavaImpl this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MeasurementManagerFutures$Api33Ext5JavaImpl$registerTriggerAsync$1(MeasurementManagerFutures.Api33Ext5JavaImpl api33Ext5JavaImpl, Uri uri, Continuation<? super MeasurementManagerFutures$Api33Ext5JavaImpl$registerTriggerAsync$1> continuation) {
        super(2, continuation);
        this.this$0 = api33Ext5JavaImpl;
        this.$trigger = uri;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MeasurementManagerFutures$Api33Ext5JavaImpl$registerTriggerAsync$1(this.this$0, this.$trigger, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MeasurementManagerFutures$Api33Ext5JavaImpl$registerTriggerAsync$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (this.this$0.mMeasurementManager.registerTrigger(this.$trigger, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
