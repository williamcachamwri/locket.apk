package expo.modules.kotlin.functions;

import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.jni.JNIAsyncFunctionBody;
import expo.modules.kotlin.jni.PromiseImpl;
import java.lang.ref.WeakReference;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AsyncFunction$$ExternalSyntheticLambda0 implements JNIAsyncFunctionBody {
    public final /* synthetic */ WeakReference f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ AsyncFunction f$2;
    public final /* synthetic */ AppContext f$3;

    public /* synthetic */ AsyncFunction$$ExternalSyntheticLambda0(WeakReference weakReference, String str, AsyncFunction asyncFunction, AppContext appContext) {
        this.f$0 = weakReference;
        this.f$1 = str;
        this.f$2 = asyncFunction;
        this.f$3 = appContext;
    }

    public final void invoke(Object[] objArr, PromiseImpl promiseImpl) {
        AsyncFunction.attachToJSObject$lambda$1(this.f$0, this.f$1, this.f$2, this.f$3, objArr, promiseImpl);
    }
}
