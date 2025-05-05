package com.adsbynimbus.internal;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "com.adsbynimbus.internal.PlatformKt$initializeSdk$3", f = "Platform.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: Platform.kt */
final class PlatformKt$initializeSdk$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;

    PlatformKt$initializeSdk$3(Continuation<? super PlatformKt$initializeSdk$3> continuation) {
        super(2, continuation);
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        PlatformKt$initializeSdk$3 platformKt$initializeSdk$3 = new PlatformKt$initializeSdk$3(continuation);
        platformKt$initializeSdk$3.L$0 = obj;
        return platformKt$initializeSdk$3;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PlatformKt$initializeSdk$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object obj2;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            Ref.IntRef intRef = new Ref.IntRef();
            while (intRef.element < 3) {
                try {
                    Result.Companion companion = Result.Companion;
                    Platform platform = Platform.INSTANCE;
                    AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(PlatformKt.getApplication());
                    Intrinsics.checkNotNullExpressionValue(advertisingIdInfo, "getAdvertisingIdInfo(application)");
                    Platform.adInfo = advertisingIdInfo;
                    obj2 = Result.m2444constructorimpl(Unit.INSTANCE);
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.Companion;
                    obj2 = Result.m2444constructorimpl(ResultKt.createFailure(th));
                }
                if (Result.m2447exceptionOrNullimpl(obj2) != null) {
                    intRef.element++;
                }
                if (Result.m2451isSuccessimpl(obj2)) {
                    Unit unit = (Unit) obj2;
                    intRef.element = 3;
                }
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
