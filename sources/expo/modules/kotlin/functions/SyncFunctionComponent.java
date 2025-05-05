package expo.modules.kotlin.functions;

import com.facebook.react.bridge.ReadableArray;
import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.exception.CodedException;
import expo.modules.kotlin.exception.FunctionCallException;
import expo.modules.kotlin.exception.UnexpectedException;
import expo.modules.kotlin.jni.ExpectedType;
import expo.modules.kotlin.jni.JNIFunctionBody;
import expo.modules.kotlin.jni.JavaScriptModuleObject;
import expo.modules.kotlin.types.AnyType;
import expo.modules.kotlin.types.JSTypeConverter;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001BJ\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012-\u0010\u0007\u001a)\u0012\u001d\u0012\u001b\u0012\b\b\u0001\u0012\u0004\u0018\u00010\t0\u0005¢\u0006\f\b\n\u0012\b\b\u0002\u0012\u0004\b\b(\u000b\u0012\u0006\u0012\u0004\u0018\u00010\t0\b¢\u0006\u0002\u0010\fJ\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0013\u001a\u0004\u0018\u00010\t2\u0006\u0010\u000b\u001a\u00020\u0014J)\u0010\u0013\u001a\u0004\u0018\u00010\t2\u000e\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u00052\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\u0002\u0010\u0015J\u001f\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00032\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0000¢\u0006\u0002\b\u0019R5\u0010\u0007\u001a)\u0012\u001d\u0012\u001b\u0012\b\b\u0001\u0012\u0004\u0018\u00010\t0\u0005¢\u0006\f\b\n\u0012\b\b\u0002\u0012\u0004\b\b(\u000b\u0012\u0006\u0012\u0004\u0018\u00010\t0\bX\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lexpo/modules/kotlin/functions/SyncFunctionComponent;", "Lexpo/modules/kotlin/functions/AnyFunction;", "name", "", "desiredArgsTypes", "", "Lexpo/modules/kotlin/types/AnyType;", "body", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "args", "(Ljava/lang/String;[Lexpo/modules/kotlin/types/AnyType;Lkotlin/jvm/functions/Function1;)V", "attachToJSObject", "", "appContext", "Lexpo/modules/kotlin/AppContext;", "jsObject", "Lexpo/modules/kotlin/jni/JavaScriptModuleObject;", "call", "Lcom/facebook/react/bridge/ReadableArray;", "([Ljava/lang/Object;Lexpo/modules/kotlin/AppContext;)Ljava/lang/Object;", "getJNIFunctionBody", "Lexpo/modules/kotlin/jni/JNIFunctionBody;", "moduleName", "getJNIFunctionBody$expo_modules_core_release", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: SyncFunctionComponent.kt */
public final class SyncFunctionComponent extends AnyFunction {
    private final Function1<Object[], Object> body;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SyncFunctionComponent(String str, AnyType[] anyTypeArr, Function1<? super Object[], ? extends Object> function1) {
        super(str, anyTypeArr);
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(anyTypeArr, "desiredArgsTypes");
        Intrinsics.checkNotNullParameter(function1, TtmlNode.TAG_BODY);
        this.body = function1;
    }

    public final Object call(ReadableArray readableArray) throws CodedException {
        Intrinsics.checkNotNullParameter(readableArray, "args");
        return this.body.invoke(convertArgs(readableArray));
    }

    public static /* synthetic */ Object call$default(SyncFunctionComponent syncFunctionComponent, Object[] objArr, AppContext appContext, int i, Object obj) {
        if ((i & 2) != 0) {
            appContext = null;
        }
        return syncFunctionComponent.call(objArr, appContext);
    }

    public final Object call(Object[] objArr, AppContext appContext) {
        Intrinsics.checkNotNullParameter(objArr, "args");
        return this.body.invoke(convertArgs(objArr, appContext));
    }

    public final JNIFunctionBody getJNIFunctionBody$expo_modules_core_release(String str, AppContext appContext) {
        Intrinsics.checkNotNullParameter(str, "moduleName");
        return new SyncFunctionComponent$$ExternalSyntheticLambda0(this, str, appContext);
    }

    /* access modifiers changed from: private */
    public static final Object getJNIFunctionBody$lambda$2(SyncFunctionComponent syncFunctionComponent, String str, AppContext appContext, Object[] objArr) {
        CodedException codedException;
        Intrinsics.checkNotNullParameter(syncFunctionComponent, "this$0");
        Intrinsics.checkNotNullParameter(str, "$moduleName");
        Intrinsics.checkNotNullParameter(objArr, "args");
        try {
            return JSTypeConverter.convertToJSValue$default(JSTypeConverter.INSTANCE, syncFunctionComponent.call(objArr, appContext), (JSTypeConverter.ContainerProvider) null, 2, (Object) null);
        } catch (Throwable th) {
            if (th instanceof CodedException) {
                codedException = th;
            } else if (th instanceof expo.modules.core.errors.CodedException) {
                String code = th.getCode();
                Intrinsics.checkNotNullExpressionValue(code, "getCode(...)");
                codedException = new CodedException(code, th.getMessage(), th.getCause());
            } else {
                codedException = new UnexpectedException((Throwable) th);
            }
            throw new FunctionCallException(syncFunctionComponent.getName(), str, codedException);
        }
    }

    public void attachToJSObject(AppContext appContext, JavaScriptModuleObject javaScriptModuleObject) {
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        Intrinsics.checkNotNullParameter(javaScriptModuleObject, "jsObject");
        javaScriptModuleObject.registerSyncFunction(getName(), getTakesOwner$expo_modules_core_release(), (ExpectedType[]) getCppRequiredTypes().toArray(new ExpectedType[0]), getJNIFunctionBody$expo_modules_core_release(javaScriptModuleObject.getName(), appContext));
    }
}
