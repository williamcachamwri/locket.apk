package com.adsbynimbus.render.internal;

import android.graphics.Rect;
import android.view.View;
import com.adsbynimbus.render.AdController;
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
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "com.adsbynimbus.render.internal.ExposureTrackerKt$updateExposure$2", f = "ExposureTracker.kt", i = {}, l = {245, 246}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: ExposureTracker.kt */
final class ExposureTrackerKt$updateExposure$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ int $exposedPercent;
    final /* synthetic */ Rect $exposedRect;
    final /* synthetic */ Map<View, Rect> $obstructions;
    final /* synthetic */ NimbusAdView $this_updateExposure;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ExposureTrackerKt$updateExposure$2(NimbusAdView nimbusAdView, Map<View, Rect> map, int i, Rect rect, Continuation<? super ExposureTrackerKt$updateExposure$2> continuation) {
        super(2, continuation);
        this.$this_updateExposure = nimbusAdView;
        this.$obstructions = map;
        this.$exposedPercent = i;
        this.$exposedRect = rect;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ExposureTrackerKt$updateExposure$2(this.$this_updateExposure, this.$obstructions, this.$exposedPercent, this.$exposedRect, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ExposureTrackerKt$updateExposure$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.$this_updateExposure.getObstructingViewCache$render_release().clear();
            this.$this_updateExposure.getObstructingViewCache$render_release().putAll(this.$obstructions);
            if (this.$exposedPercent != this.$this_updateExposure.getExposure() || !Intrinsics.areEqual((Object) this.$exposedRect, (Object) this.$this_updateExposure.getVisibleRect())) {
                this.$this_updateExposure.setExposure$render_release(this.$exposedPercent);
                Rect visibleRect = this.$this_updateExposure.getVisibleRect();
                visibleRect.set(this.$exposedRect);
                visibleRect.offset(this.$this_updateExposure.getOffset$render_release().x, this.$this_updateExposure.getOffset$render_release().y);
                AdController adController = this.$this_updateExposure.adController;
                if (adController != null) {
                    adController.dispatchExposureChange$render_release(this.$this_updateExposure.getExposure(), this.$this_updateExposure.getVisibleRect());
                }
            }
            this.$this_updateExposure.setLastReportTime$render_release(System.currentTimeMillis());
            if (this.$this_updateExposure.getNeedsExposureUpdate$render_release()) {
                this.label = 1;
                if (DelayKt.delay(184, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                this.$this_updateExposure.setExposureScheduled$render_release(false);
                return Unit.INSTANCE;
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
        if (ExposureTrackerKt.calculateExposure$default(this.$this_updateExposure, (Map) null, (Map) null, this, 3, (Object) null) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }
}
