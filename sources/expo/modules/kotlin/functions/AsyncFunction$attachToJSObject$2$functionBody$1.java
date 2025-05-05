package expo.modules.kotlin.functions;

import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.exception.CodedException;
import expo.modules.kotlin.exception.UnexpectedException;
import expo.modules.kotlin.jni.PromiseImpl;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: AsyncFunction.kt */
final class AsyncFunction$attachToJSObject$2$functionBody$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ AppContext $appContext;
    final /* synthetic */ Object[] $args;
    final /* synthetic */ String $moduleName;
    final /* synthetic */ PromiseImpl $promiseImpl;
    final /* synthetic */ AsyncFunction this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AsyncFunction$attachToJSObject$2$functionBody$1(PromiseImpl promiseImpl, AsyncFunction asyncFunction, String str, Object[] objArr, AppContext appContext) {
        super(0);
        this.$promiseImpl = promiseImpl;
        this.this$0 = asyncFunction;
        this.$moduleName = str;
        this.$args = objArr;
        this.$appContext = appContext;
    }

    public final void invoke() {
        CodedException codedException;
        AsyncFunction asyncFunction;
        String str;
        CodedException codedException2;
        try {
            asyncFunction = this.this$0;
            str = this.$moduleName;
            asyncFunction.callUserImplementation$expo_modules_core_release(this.$args, this.$promiseImpl, this.$appContext);
            Unit unit = Unit.INSTANCE;
        } catch (Throwable th) {
            if (!this.$promiseImpl.getWasSettled$expo_modules_core_release()) {
                PromiseImpl promiseImpl = this.$promiseImpl;
                if (th instanceof CodedException) {
                    codedException = (CodedException) th;
                } else if (th instanceof expo.modules.core.errors.CodedException) {
                    String code = th.getCode();
                    Intrinsics.checkNotNullExpressionValue(code, "getCode(...)");
                    codedException = new CodedException(code, th.getMessage(), th.getCause());
                } else {
                    codedException = new UnexpectedException((Throwable) th);
                }
                promiseImpl.reject(codedException);
                return;
            }
            throw th;
        }
    }
}
