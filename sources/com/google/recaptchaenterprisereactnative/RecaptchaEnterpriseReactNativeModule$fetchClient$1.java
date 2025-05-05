package com.google.recaptchaenterprisereactnative;

import com.facebook.react.bridge.Promise;
import com.google.android.recaptcha.Recaptcha;
import com.google.android.recaptcha.RecaptchaClient;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "com.google.recaptchaenterprisereactnative.RecaptchaEnterpriseReactNativeModule$fetchClient$1", f = "RecaptchaEnterpriseReactNativeModule.kt", i = {}, l = {55}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: RecaptchaEnterpriseReactNativeModule.kt */
final class RecaptchaEnterpriseReactNativeModule$fetchClient$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Promise $promise;
    final /* synthetic */ String $siteKey;
    Object L$0;
    int label;
    final /* synthetic */ RecaptchaEnterpriseReactNativeModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RecaptchaEnterpriseReactNativeModule$fetchClient$1(RecaptchaEnterpriseReactNativeModule recaptchaEnterpriseReactNativeModule, String str, Promise promise, Continuation<? super RecaptchaEnterpriseReactNativeModule$fetchClient$1> continuation) {
        super(2, continuation);
        this.this$0 = recaptchaEnterpriseReactNativeModule;
        this.$siteKey = str;
        this.$promise = promise;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RecaptchaEnterpriseReactNativeModule$fetchClient$1(this.this$0, this.$siteKey, this.$promise, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RecaptchaEnterpriseReactNativeModule$fetchClient$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        RecaptchaEnterpriseReactNativeModule recaptchaEnterpriseReactNativeModule;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            RecaptchaEnterpriseReactNativeModule recaptchaEnterpriseReactNativeModule2 = this.this$0;
            this.L$0 = recaptchaEnterpriseReactNativeModule2;
            this.label = 1;
            Object fetchClient = Recaptcha.INSTANCE.fetchClient(this.this$0.application, this.$siteKey, this);
            if (fetchClient == coroutine_suspended) {
                return coroutine_suspended;
            }
            recaptchaEnterpriseReactNativeModule = recaptchaEnterpriseReactNativeModule2;
            obj = fetchClient;
        } else if (i == 1) {
            recaptchaEnterpriseReactNativeModule = (RecaptchaEnterpriseReactNativeModule) this.L$0;
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Exception e) {
                this.$promise.reject((Throwable) e);
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        recaptchaEnterpriseReactNativeModule.recaptchaClient = (RecaptchaClient) obj;
        this.$promise.resolve((Object) null);
        return Unit.INSTANCE;
    }
}
