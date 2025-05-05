package com.google.recaptchaenterprisereactnative;

import android.app.Application;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReadableMap;
import com.google.android.recaptcha.Recaptcha;
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
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.google.recaptchaenterprisereactnative.RecaptchaEnterpriseReactNativeModule$initClient$1", f = "RecaptchaEnterpriseReactNativeModule.kt", i = {}, l = {70, 72}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: RecaptchaEnterpriseReactNativeModule.kt */
final class RecaptchaEnterpriseReactNativeModule$initClient$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ReadableMap $arguments;
    final /* synthetic */ Promise $promise;
    final /* synthetic */ String $siteKey;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ RecaptchaEnterpriseReactNativeModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RecaptchaEnterpriseReactNativeModule$initClient$1(ReadableMap readableMap, RecaptchaEnterpriseReactNativeModule recaptchaEnterpriseReactNativeModule, String str, Promise promise, Continuation<? super RecaptchaEnterpriseReactNativeModule$initClient$1> continuation) {
        super(2, continuation);
        this.$arguments = readableMap;
        this.this$0 = recaptchaEnterpriseReactNativeModule;
        this.$siteKey = str;
        this.$promise = promise;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        RecaptchaEnterpriseReactNativeModule$initClient$1 recaptchaEnterpriseReactNativeModule$initClient$1 = new RecaptchaEnterpriseReactNativeModule$initClient$1(this.$arguments, this.this$0, this.$siteKey, this.$promise, continuation);
        recaptchaEnterpriseReactNativeModule$initClient$1.L$0 = obj;
        return recaptchaEnterpriseReactNativeModule$initClient$1;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RecaptchaEnterpriseReactNativeModule$initClient$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object obj2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            ReadableMap readableMap = this.$arguments;
            RecaptchaEnterpriseReactNativeModule recaptchaEnterpriseReactNativeModule = this.this$0;
            String str = this.$siteKey;
            if (readableMap.hasKey(ProfilingTraceData.TRUNCATION_REASON_TIMEOUT)) {
                long j = (long) readableMap.getDouble(ProfilingTraceData.TRUNCATION_REASON_TIMEOUT);
                Recaptcha recaptcha = Recaptcha.INSTANCE;
                Application access$getApplication$p = recaptchaEnterpriseReactNativeModule.application;
                this.label = 1;
                obj2 = recaptcha.m2173getClientBWLJW6A(access$getApplication$p, str, j, this);
                if (obj2 == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                Recaptcha recaptcha2 = Recaptcha.INSTANCE;
                Application access$getApplication$p2 = recaptchaEnterpriseReactNativeModule.application;
                this.label = 2;
                obj2 = Recaptcha.m2172getClientBWLJW6A$default(recaptcha2, access$getApplication$p2, str, 0, this, 4, (Object) null);
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
        RecaptchaEnterpriseReactNativeModule recaptchaEnterpriseReactNativeModule2 = this.this$0;
        Promise promise = this.$promise;
        if (Result.m2451isSuccessimpl(obj2)) {
            recaptchaEnterpriseReactNativeModule2.recaptchaClient = (RecaptchaClient) obj2;
            promise.resolve((Object) null);
        }
        Promise promise2 = this.$promise;
        Throwable r13 = Result.m2447exceptionOrNullimpl(obj2);
        if (r13 != null) {
            promise2.reject(r13);
        }
        return Unit.INSTANCE;
    }
}
