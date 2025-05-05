package com.adsbynimbus.request;

import com.adsbynimbus.internal.Logger;
import java.net.HttpURLConnection;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "com.adsbynimbus.request.WinLossExtension$notifyTracker$1", f = "WinLossExtension.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: WinLossExtension.kt */
final class WinLossExtension$notifyTracker$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $name;
    final /* synthetic */ String $url;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    WinLossExtension$notifyTracker$1(String str, String str2, Continuation<? super WinLossExtension$notifyTracker$1> continuation) {
        super(2, continuation);
        this.$name = str;
        this.$url = str2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        WinLossExtension$notifyTracker$1 winLossExtension$notifyTracker$1 = new WinLossExtension$notifyTracker$1(this.$name, this.$url, continuation);
        winLossExtension$notifyTracker$1.L$0 = obj;
        return winLossExtension$notifyTracker$1;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((WinLossExtension$notifyTracker$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "com.adsbynimbus.request.WinLossExtension$notifyTracker$1$1", f = "WinLossExtension.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.adsbynimbus.request.WinLossExtension$notifyTracker$1$1  reason: invalid class name */
    /* compiled from: WinLossExtension.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 r0 = new AnonymousClass1(str, str2, continuation);
            r0.L$0 = obj;
            return r0;
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Integer num;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                String str = str2;
                try {
                    Result.Companion companion = Result.Companion;
                    HttpURLConnection invoke = WinLossExtension.getConnectionProvider().invoke(str);
                    invoke.setConnectTimeout(5000);
                    num = Result.m2444constructorimpl(Boxing.boxInt(invoke.getResponseCode()));
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.Companion;
                    num = Result.m2444constructorimpl(ResultKt.createFailure(th));
                }
                boolean z = false;
                Integer boxInt = Boxing.boxInt(0);
                if (Result.m2450isFailureimpl(num)) {
                    num = boxInt;
                }
                int intValue = ((Number) num).intValue();
                if (200 <= intValue && intValue < 400) {
                    z = true;
                }
                if (z) {
                    Logger.log(2, "Successfully fired " + str + " event tracker [" + str2 + AbstractJsonLexerKt.END_LIST);
                } else {
                    Logger.log(5, "Error firing " + str + " event tracker [" + str2 + AbstractJsonLexerKt.END_LIST);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            final String str = this.$name;
            final String str2 = this.$url;
            Job unused = BuildersKt__Builders_commonKt.launch$default((CoroutineScope) this.L$0, Dispatchers.getIO(), (CoroutineStart) null, new AnonymousClass1((Continuation<? super AnonymousClass1>) null), 2, (Object) null);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
