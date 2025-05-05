package expo.modules.kotlin.functions;

import expo.modules.kotlin.jni.PromiseImpl;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.kotlin.functions.SuspendFunctionComponent$attachToJSObject$2$1", f = "SuspendFunctionComponent.kt", i = {0}, l = {76}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
/* compiled from: SuspendFunctionComponent.kt */
final class SuspendFunctionComponent$attachToJSObject$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Object[] $args;
    final /* synthetic */ String $moduleName;
    final /* synthetic */ PromiseImpl $promiseImpl;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    final /* synthetic */ SuspendFunctionComponent this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SuspendFunctionComponent$attachToJSObject$2$1(PromiseImpl promiseImpl, SuspendFunctionComponent suspendFunctionComponent, String str, Object[] objArr, Continuation<? super SuspendFunctionComponent$attachToJSObject$2$1> continuation) {
        super(2, continuation);
        this.$promiseImpl = promiseImpl;
        this.this$0 = suspendFunctionComponent;
        this.$moduleName = str;
        this.$args = objArr;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        SuspendFunctionComponent$attachToJSObject$2$1 suspendFunctionComponent$attachToJSObject$2$1 = new SuspendFunctionComponent$attachToJSObject$2$1(this.$promiseImpl, this.this$0, this.$moduleName, this.$args, continuation);
        suspendFunctionComponent$attachToJSObject$2$1.L$0 = obj;
        return suspendFunctionComponent$attachToJSObject$2$1;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SuspendFunctionComponent$attachToJSObject$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: expo.modules.core.errors.CodedException} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v0, resolved type: expo.modules.core.errors.CodedException} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v15, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v5, resolved type: kotlinx.coroutines.CoroutineScope} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v13, resolved type: expo.modules.core.errors.CodedException} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r13) {
        /*
            r12 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r12.label
            java.lang.String r2 = "getCode(...)"
            r3 = 1
            if (r1 == 0) goto L_0x002b
            if (r1 != r3) goto L_0x0023
            java.lang.Object r0 = r12.L$3
            expo.modules.kotlin.jni.PromiseImpl r0 = (expo.modules.kotlin.jni.PromiseImpl) r0
            java.lang.Object r1 = r12.L$2
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r3 = r12.L$1
            expo.modules.kotlin.functions.SuspendFunctionComponent r3 = (expo.modules.kotlin.functions.SuspendFunctionComponent) r3
            java.lang.Object r4 = r12.L$0
            kotlinx.coroutines.CoroutineScope r4 = (kotlinx.coroutines.CoroutineScope) r4
            kotlin.ResultKt.throwOnFailure(r13)     // Catch:{ all -> 0x0021 }
            goto L_0x005d
        L_0x0021:
            r13 = move-exception
            goto L_0x006d
        L_0x0023:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r0)
            throw r13
        L_0x002b:
            kotlin.ResultKt.throwOnFailure(r13)
            java.lang.Object r13 = r12.L$0
            r4 = r13
            kotlinx.coroutines.CoroutineScope r4 = (kotlinx.coroutines.CoroutineScope) r4
            expo.modules.kotlin.functions.SuspendFunctionComponent r13 = r12.this$0     // Catch:{ all -> 0x00a5 }
            java.lang.String r1 = r12.$moduleName     // Catch:{ all -> 0x00a5 }
            java.lang.Object[] r5 = r12.$args     // Catch:{ all -> 0x00a5 }
            expo.modules.kotlin.jni.PromiseImpl r6 = r12.$promiseImpl     // Catch:{ all -> 0x00a5 }
            kotlin.jvm.functions.Function3 r7 = r13.body     // Catch:{ all -> 0x006a }
            r8 = r13
            expo.modules.kotlin.functions.AnyFunction r8 = (expo.modules.kotlin.functions.AnyFunction) r8     // Catch:{ all -> 0x006a }
            r9 = 2
            r10 = 0
            java.lang.Object[] r5 = expo.modules.kotlin.functions.AnyFunction.convertArgs$default(r8, r5, r10, r9, r10)     // Catch:{ all -> 0x006a }
            r12.L$0 = r4     // Catch:{ all -> 0x006a }
            r12.L$1 = r13     // Catch:{ all -> 0x006a }
            r12.L$2 = r1     // Catch:{ all -> 0x006a }
            r12.L$3 = r6     // Catch:{ all -> 0x006a }
            r12.label = r3     // Catch:{ all -> 0x006a }
            java.lang.Object r3 = r7.invoke(r4, r5, r12)     // Catch:{ all -> 0x006a }
            if (r3 != r0) goto L_0x0059
            return r0
        L_0x0059:
            r0 = r6
            r11 = r3
            r3 = r13
            r13 = r11
        L_0x005d:
            boolean r4 = kotlinx.coroutines.CoroutineScopeKt.isActive(r4)     // Catch:{ all -> 0x0021 }
            if (r4 == 0) goto L_0x0066
            r0.resolve(r13)     // Catch:{ all -> 0x0021 }
        L_0x0066:
            kotlin.Unit r13 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0021 }
            goto L_0x00df
        L_0x006a:
            r0 = move-exception
            r3 = r13
            r13 = r0
        L_0x006d:
            boolean r0 = r13 instanceof expo.modules.kotlin.exception.CodedException     // Catch:{ all -> 0x00a5 }
            if (r0 != 0) goto L_0x0096
            boolean r0 = r13 instanceof expo.modules.core.errors.CodedException     // Catch:{ all -> 0x00a5 }
            if (r0 == 0) goto L_0x008e
            expo.modules.kotlin.exception.CodedException r0 = new expo.modules.kotlin.exception.CodedException     // Catch:{ all -> 0x00a5 }
            r4 = r13
            expo.modules.core.errors.CodedException r4 = (expo.modules.core.errors.CodedException) r4     // Catch:{ all -> 0x00a5 }
            java.lang.String r4 = r4.getCode()     // Catch:{ all -> 0x00a5 }
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r2)     // Catch:{ all -> 0x00a5 }
            java.lang.String r5 = r13.getMessage()     // Catch:{ all -> 0x00a5 }
            java.lang.Throwable r13 = r13.getCause()     // Catch:{ all -> 0x00a5 }
            r0.<init>(r4, r5, r13)     // Catch:{ all -> 0x00a5 }
            goto L_0x0099
        L_0x008e:
            expo.modules.kotlin.exception.UnexpectedException r0 = new expo.modules.kotlin.exception.UnexpectedException     // Catch:{ all -> 0x00a5 }
            r0.<init>((java.lang.Throwable) r13)     // Catch:{ all -> 0x00a5 }
            expo.modules.kotlin.exception.CodedException r0 = (expo.modules.kotlin.exception.CodedException) r0     // Catch:{ all -> 0x00a5 }
            goto L_0x0099
        L_0x0096:
            r0 = r13
            expo.modules.kotlin.exception.CodedException r0 = (expo.modules.kotlin.exception.CodedException) r0     // Catch:{ all -> 0x00a5 }
        L_0x0099:
            expo.modules.kotlin.exception.FunctionCallException r13 = new expo.modules.kotlin.exception.FunctionCallException     // Catch:{ all -> 0x00a5 }
            java.lang.String r3 = r3.getName()     // Catch:{ all -> 0x00a5 }
            r13.<init>(r3, r1, r0)     // Catch:{ all -> 0x00a5 }
            java.lang.Throwable r13 = (java.lang.Throwable) r13     // Catch:{ all -> 0x00a5 }
            throw r13     // Catch:{ all -> 0x00a5 }
        L_0x00a5:
            r13 = move-exception
            expo.modules.kotlin.jni.PromiseImpl r0 = r12.$promiseImpl
            boolean r0 = r0.getWasSettled$expo_modules_core_release()
            if (r0 != 0) goto L_0x00e2
            expo.modules.kotlin.jni.PromiseImpl r0 = r12.$promiseImpl
            boolean r1 = r13 instanceof expo.modules.kotlin.exception.CodedException
            if (r1 == 0) goto L_0x00b7
            expo.modules.kotlin.exception.CodedException r13 = (expo.modules.kotlin.exception.CodedException) r13
            goto L_0x00dc
        L_0x00b7:
            boolean r1 = r13 instanceof expo.modules.core.errors.CodedException
            if (r1 == 0) goto L_0x00d4
            expo.modules.kotlin.exception.CodedException r1 = new expo.modules.kotlin.exception.CodedException
            r3 = r13
            expo.modules.core.errors.CodedException r3 = (expo.modules.core.errors.CodedException) r3
            java.lang.String r3 = r3.getCode()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r2)
            java.lang.String r2 = r13.getMessage()
            java.lang.Throwable r13 = r13.getCause()
            r1.<init>(r3, r2, r13)
            r13 = r1
            goto L_0x00dc
        L_0x00d4:
            expo.modules.kotlin.exception.UnexpectedException r1 = new expo.modules.kotlin.exception.UnexpectedException
            r1.<init>((java.lang.Throwable) r13)
            r13 = r1
            expo.modules.kotlin.exception.CodedException r13 = (expo.modules.kotlin.exception.CodedException) r13
        L_0x00dc:
            r0.reject(r13)
        L_0x00df:
            kotlin.Unit r13 = kotlin.Unit.INSTANCE
            return r13
        L_0x00e2:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.kotlin.functions.SuspendFunctionComponent$attachToJSObject$2$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
