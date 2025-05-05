package com.adsbynimbus.render.internal;

import com.adsbynimbus.render.NimbusAdView;
import java.util.Map;
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
@DebugMetadata(c = "com.adsbynimbus.render.internal.ExposureTrackerKt$scheduleExposureCheck$1", f = "ExposureTracker.kt", i = {}, l = {146, 147}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: ExposureTracker.kt */
final class ExposureTrackerKt$scheduleExposureCheck$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ NimbusAdView $this_scheduleExposureCheck;
    final /* synthetic */ long $timeSinceLastReport;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ExposureTrackerKt$scheduleExposureCheck$1(long j, NimbusAdView nimbusAdView, Continuation<? super ExposureTrackerKt$scheduleExposureCheck$1> continuation) {
        super(2, continuation);
        this.$timeSinceLastReport = j;
        this.$this_scheduleExposureCheck = nimbusAdView;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ExposureTrackerKt$scheduleExposureCheck$1(this.$timeSinceLastReport, this.$this_scheduleExposureCheck, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ExposureTrackerKt$scheduleExposureCheck$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (DelayKt.delay(((long) 184) - this.$timeSinceLastReport, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else if (i == 2) {
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        this.label = 2;
        if (ExposureTrackerKt.calculateExposure$default(this.$this_scheduleExposureCheck, (Map) null, (Map) null, this, 3, (Object) null) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }
}
