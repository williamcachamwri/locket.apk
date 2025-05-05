package com.google.recaptchaenterprisereactnative;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReadableMap;
import com.google.android.recaptcha.RecaptchaAction;
import com.google.android.recaptcha.RecaptchaClient;
import io.sentry.ProfilingTraceData;
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
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.google.recaptchaenterprisereactnative.RecaptchaEnterpriseReactNativeModule$execute$1", f = "RecaptchaEnterpriseReactNativeModule.kt", i = {}, l = {98, 100}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: RecaptchaEnterpriseReactNativeModule.kt */
final class RecaptchaEnterpriseReactNativeModule$execute$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ RecaptchaAction $action;
    final /* synthetic */ ReadableMap $arguments;
    final /* synthetic */ Promise $promise;
    int label;
    final /* synthetic */ RecaptchaEnterpriseReactNativeModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RecaptchaEnterpriseReactNativeModule$execute$1(RecaptchaEnterpriseReactNativeModule recaptchaEnterpriseReactNativeModule, ReadableMap readableMap, RecaptchaAction recaptchaAction, Promise promise, Continuation<? super RecaptchaEnterpriseReactNativeModule$execute$1> continuation) {
        super(2, continuation);
        this.this$0 = recaptchaEnterpriseReactNativeModule;
        this.$arguments = readableMap;
        this.$action = recaptchaAction;
        this.$promise = promise;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RecaptchaEnterpriseReactNativeModule$execute$1(this.this$0, this.$arguments, this.$action, this.$promise, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RecaptchaEnterpriseReactNativeModule$execute$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object obj2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            RecaptchaClient access$getRecaptchaClient$p = this.this$0.recaptchaClient;
            if (access$getRecaptchaClient$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("recaptchaClient");
                access$getRecaptchaClient$p = null;
            }
            ReadableMap readableMap = this.$arguments;
            RecaptchaAction recaptchaAction = this.$action;
            if (readableMap.hasKey(ProfilingTraceData.TRUNCATION_REASON_TIMEOUT)) {
                this.label = 1;
                obj2 = access$getRecaptchaClient$p.m2174execute0E7RQCE(recaptchaAction, (long) readableMap.getDouble(ProfilingTraceData.TRUNCATION_REASON_TIMEOUT), this);
                if (obj2 == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                this.label = 2;
                obj2 = access$getRecaptchaClient$p.m2175executegIAlus(recaptchaAction, this);
                if (obj2 == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else if (i == 1 || i == 2) {
            ResultKt.throwOnFailure(obj);
            obj2 = ((Result) obj).m2453unboximpl();
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        Promise promise = this.$promise;
        if (Result.m2451isSuccessimpl(obj2)) {
            promise.resolve((String) obj2);
        }
        Promise promise2 = this.$promise;
        Throwable r8 = Result.m2447exceptionOrNullimpl(obj2);
        if (r8 != null) {
            promise2.reject(r8);
        }
        return Unit.INSTANCE;
    }
}
