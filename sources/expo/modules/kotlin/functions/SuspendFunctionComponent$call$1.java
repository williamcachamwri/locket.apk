package expo.modules.kotlin.functions;

import com.facebook.react.bridge.ReadableArray;
import expo.modules.kotlin.ModuleHolder;
import expo.modules.kotlin.Promise;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.kotlin.functions.SuspendFunctionComponent$call$1", f = "SuspendFunctionComponent.kt", i = {0}, l = {37}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
/* compiled from: SuspendFunctionComponent.kt */
final class SuspendFunctionComponent$call$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ReadableArray $args;
    final /* synthetic */ ModuleHolder<?> $holder;
    final /* synthetic */ Promise $promise;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    final /* synthetic */ SuspendFunctionComponent this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SuspendFunctionComponent$call$1(Promise promise, SuspendFunctionComponent suspendFunctionComponent, ModuleHolder<?> moduleHolder, ReadableArray readableArray, Continuation<? super SuspendFunctionComponent$call$1> continuation) {
        super(2, continuation);
        this.$promise = promise;
        this.this$0 = suspendFunctionComponent;
        this.$holder = moduleHolder;
        this.$args = readableArray;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SuspendFunctionComponent$call$1 suspendFunctionComponent$call$1 = new SuspendFunctionComponent$call$1(this.$promise, this.this$0, this.$holder, this.$args, continuation);
        suspendFunctionComponent$call$1.L$0 = obj;
        return suspendFunctionComponent$call$1;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SuspendFunctionComponent$call$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: expo.modules.core.errors.CodedException} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v10, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v5, resolved type: kotlinx.coroutines.CoroutineScope} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v12, resolved type: expo.modules.core.errors.CodedException} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            r8 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r8.label
            r2 = 1
            if (r1 == 0) goto L_0x0029
            if (r1 != r2) goto L_0x0021
            java.lang.Object r0 = r8.L$3
            expo.modules.kotlin.Promise r0 = (expo.modules.kotlin.Promise) r0
            java.lang.Object r1 = r8.L$2
            expo.modules.kotlin.ModuleHolder r1 = (expo.modules.kotlin.ModuleHolder) r1
            java.lang.Object r2 = r8.L$1
            expo.modules.kotlin.functions.SuspendFunctionComponent r2 = (expo.modules.kotlin.functions.SuspendFunctionComponent) r2
            java.lang.Object r3 = r8.L$0
            kotlinx.coroutines.CoroutineScope r3 = (kotlinx.coroutines.CoroutineScope) r3
            kotlin.ResultKt.throwOnFailure(r9)     // Catch:{ all -> 0x001f }
            goto L_0x0056
        L_0x001f:
            r9 = move-exception
            goto L_0x0065
        L_0x0021:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L_0x0029:
            kotlin.ResultKt.throwOnFailure(r9)
            java.lang.Object r9 = r8.L$0
            r3 = r9
            kotlinx.coroutines.CoroutineScope r3 = (kotlinx.coroutines.CoroutineScope) r3
            expo.modules.kotlin.functions.SuspendFunctionComponent r9 = r8.this$0     // Catch:{ CodedException -> 0x00b1, all -> 0x00a3 }
            expo.modules.kotlin.ModuleHolder<?> r1 = r8.$holder     // Catch:{ CodedException -> 0x00b1, all -> 0x00a3 }
            com.facebook.react.bridge.ReadableArray r4 = r8.$args     // Catch:{ CodedException -> 0x00b1, all -> 0x00a3 }
            expo.modules.kotlin.Promise r5 = r8.$promise     // Catch:{ CodedException -> 0x00b1, all -> 0x00a3 }
            kotlin.jvm.functions.Function3 r6 = r9.body     // Catch:{ all -> 0x0062 }
            java.lang.Object[] r4 = r9.convertArgs(r4)     // Catch:{ all -> 0x0062 }
            r8.L$0 = r3     // Catch:{ all -> 0x0062 }
            r8.L$1 = r9     // Catch:{ all -> 0x0062 }
            r8.L$2 = r1     // Catch:{ all -> 0x0062 }
            r8.L$3 = r5     // Catch:{ all -> 0x0062 }
            r8.label = r2     // Catch:{ all -> 0x0062 }
            java.lang.Object r2 = r6.invoke(r3, r4, r8)     // Catch:{ all -> 0x0062 }
            if (r2 != r0) goto L_0x0052
            return r0
        L_0x0052:
            r0 = r5
            r7 = r2
            r2 = r9
            r9 = r7
        L_0x0056:
            boolean r3 = kotlinx.coroutines.CoroutineScopeKt.isActive(r3)     // Catch:{ all -> 0x001f }
            if (r3 == 0) goto L_0x005f
            r0.resolve(r9)     // Catch:{ all -> 0x001f }
        L_0x005f:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x001f }
            goto L_0x00b7
        L_0x0062:
            r0 = move-exception
            r2 = r9
            r9 = r0
        L_0x0065:
            boolean r0 = r9 instanceof expo.modules.kotlin.exception.CodedException     // Catch:{ CodedException -> 0x00b1, all -> 0x00a3 }
            if (r0 != 0) goto L_0x0090
            boolean r0 = r9 instanceof expo.modules.core.errors.CodedException     // Catch:{ CodedException -> 0x00b1, all -> 0x00a3 }
            if (r0 == 0) goto L_0x0088
            expo.modules.kotlin.exception.CodedException r0 = new expo.modules.kotlin.exception.CodedException     // Catch:{ CodedException -> 0x00b1, all -> 0x00a3 }
            r3 = r9
            expo.modules.core.errors.CodedException r3 = (expo.modules.core.errors.CodedException) r3     // Catch:{ CodedException -> 0x00b1, all -> 0x00a3 }
            java.lang.String r3 = r3.getCode()     // Catch:{ CodedException -> 0x00b1, all -> 0x00a3 }
            java.lang.String r4 = "getCode(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r4)     // Catch:{ CodedException -> 0x00b1, all -> 0x00a3 }
            java.lang.String r4 = r9.getMessage()     // Catch:{ CodedException -> 0x00b1, all -> 0x00a3 }
            java.lang.Throwable r9 = r9.getCause()     // Catch:{ CodedException -> 0x00b1, all -> 0x00a3 }
            r0.<init>(r3, r4, r9)     // Catch:{ CodedException -> 0x00b1, all -> 0x00a3 }
            goto L_0x0093
        L_0x0088:
            expo.modules.kotlin.exception.UnexpectedException r0 = new expo.modules.kotlin.exception.UnexpectedException     // Catch:{ CodedException -> 0x00b1, all -> 0x00a3 }
            r0.<init>((java.lang.Throwable) r9)     // Catch:{ CodedException -> 0x00b1, all -> 0x00a3 }
            expo.modules.kotlin.exception.CodedException r0 = (expo.modules.kotlin.exception.CodedException) r0     // Catch:{ CodedException -> 0x00b1, all -> 0x00a3 }
            goto L_0x0093
        L_0x0090:
            r0 = r9
            expo.modules.kotlin.exception.CodedException r0 = (expo.modules.kotlin.exception.CodedException) r0     // Catch:{ CodedException -> 0x00b1, all -> 0x00a3 }
        L_0x0093:
            expo.modules.kotlin.exception.FunctionCallException r9 = new expo.modules.kotlin.exception.FunctionCallException     // Catch:{ CodedException -> 0x00b1, all -> 0x00a3 }
            java.lang.String r2 = r2.getName()     // Catch:{ CodedException -> 0x00b1, all -> 0x00a3 }
            java.lang.String r1 = r1.getName()     // Catch:{ CodedException -> 0x00b1, all -> 0x00a3 }
            r9.<init>(r2, r1, r0)     // Catch:{ CodedException -> 0x00b1, all -> 0x00a3 }
            java.lang.Throwable r9 = (java.lang.Throwable) r9     // Catch:{ CodedException -> 0x00b1, all -> 0x00a3 }
            throw r9     // Catch:{ CodedException -> 0x00b1, all -> 0x00a3 }
        L_0x00a3:
            r9 = move-exception
            expo.modules.kotlin.Promise r0 = r8.$promise
            expo.modules.kotlin.exception.UnexpectedException r1 = new expo.modules.kotlin.exception.UnexpectedException
            r1.<init>((java.lang.Throwable) r9)
            expo.modules.kotlin.exception.CodedException r1 = (expo.modules.kotlin.exception.CodedException) r1
            r0.reject(r1)
            goto L_0x00b7
        L_0x00b1:
            r9 = move-exception
            expo.modules.kotlin.Promise r0 = r8.$promise
            r0.reject(r9)
        L_0x00b7:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.kotlin.functions.SuspendFunctionComponent$call$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
