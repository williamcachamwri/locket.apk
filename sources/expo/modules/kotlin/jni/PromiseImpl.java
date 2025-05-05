package expo.modules.kotlin.jni;

import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.util.JSStackTrace;
import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.exception.CodedException;
import expo.modules.kotlin.exception.PromiseAlreadySettledException;
import expo.modules.kotlin.types.JSTypeConverter;
import io.invertase.firebase.functions.UniversalFirebaseFunctionsModule;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u0017\u0010\u0013\u001a\u00020\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u0016H\bJ$\u0010\u0017\u001a\u00020\u00142\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\nJ$\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\n2\b\u0010\u001c\u001a\u0004\u0018\u00010\n2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\u0012\u0010\u001f\u001a\u00020\u00142\b\u0010 \u001a\u0004\u0018\u00010!H\u0016R\u0016\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0004\u001a\u00020\u00038\u0000X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u001e\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000f@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\""}, d2 = {"Lexpo/modules/kotlin/jni/PromiseImpl;", "Lexpo/modules/kotlin/Promise;", "resolveBlock", "Lexpo/modules/kotlin/jni/JavaCallback;", "rejectBlock", "(Lexpo/modules/kotlin/jni/JavaCallback;Lexpo/modules/kotlin/jni/JavaCallback;)V", "appContextHolder", "Ljava/lang/ref/WeakReference;", "Lexpo/modules/kotlin/AppContext;", "fullFunctionName", "", "getRejectBlock$expo_modules_core_release", "()Lexpo/modules/kotlin/jni/JavaCallback;", "getResolveBlock$expo_modules_core_release", "<set-?>", "", "wasSettled", "getWasSettled$expo_modules_core_release", "()Z", "checkIfWasSettled", "", "body", "Lkotlin/Function0;", "decorateWithDebugInformation", "moduleName", "functionName", "reject", "code", "message", "cause", "", "resolve", "value", "", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PromiseImpl.kt */
public final class PromiseImpl implements Promise {
    private WeakReference<AppContext> appContextHolder;
    private String fullFunctionName;
    private final JavaCallback rejectBlock;
    private final JavaCallback resolveBlock;
    private boolean wasSettled;

    public PromiseImpl(JavaCallback javaCallback, JavaCallback javaCallback2) {
        Intrinsics.checkNotNullParameter(javaCallback, "resolveBlock");
        Intrinsics.checkNotNullParameter(javaCallback2, "rejectBlock");
        this.resolveBlock = javaCallback;
        this.rejectBlock = javaCallback2;
    }

    public void reject(CodedException codedException) {
        Promise.DefaultImpls.reject(this, codedException);
    }

    public void resolve() {
        Promise.DefaultImpls.resolve(this);
    }

    public final JavaCallback getResolveBlock$expo_modules_core_release() {
        return this.resolveBlock;
    }

    public final JavaCallback getRejectBlock$expo_modules_core_release() {
        return this.rejectBlock;
    }

    public final boolean getWasSettled$expo_modules_core_release() {
        return this.wasSettled;
    }

    private final void checkIfWasSettled(Function0<Unit> function0) {
        AppContext appContext;
        if (this.wasSettled) {
            String str = this.fullFunctionName;
            if (str == null) {
                str = "unknown";
            }
            PromiseAlreadySettledException promiseAlreadySettledException = new PromiseAlreadySettledException(str);
            WeakReference<AppContext> weakReference = this.appContextHolder;
            if (!(weakReference == null || (appContext = (AppContext) weakReference.get()) == null)) {
                appContext.getErrorManager();
            }
            throw promiseAlreadySettledException;
        }
        function0.invoke();
        this.wasSettled = true;
    }

    public final void decorateWithDebugInformation(WeakReference<AppContext> weakReference, String str, String str2) {
        Intrinsics.checkNotNullParameter(weakReference, "appContextHolder");
        Intrinsics.checkNotNullParameter(str, "moduleName");
        Intrinsics.checkNotNullParameter(str2, "functionName");
        this.appContextHolder = weakReference;
        this.fullFunctionName = str + "." + str2;
    }

    public void resolve(Object obj) {
        AppContext appContext;
        if (this.wasSettled) {
            String str = this.fullFunctionName;
            if (str == null) {
                str = "unknown";
            }
            PromiseAlreadySettledException promiseAlreadySettledException = new PromiseAlreadySettledException(str);
            WeakReference<AppContext> weakReference = this.appContextHolder;
            if (!(weakReference == null || (appContext = (AppContext) weakReference.get()) == null)) {
                appContext.getErrorManager();
            }
            throw promiseAlreadySettledException;
        }
        this.resolveBlock.invoke(JSTypeConverter.convertToJSValue$default(JSTypeConverter.INSTANCE, obj, (JSTypeConverter.ContainerProvider) null, 2, (Object) null));
        this.wasSettled = true;
    }

    public void reject(String str, String str2, Throwable th) {
        AppContext appContext;
        Intrinsics.checkNotNullParameter(str, UniversalFirebaseFunctionsModule.CODE_KEY);
        if (this.wasSettled) {
            String str3 = this.fullFunctionName;
            if (str3 == null) {
                str3 = "unknown";
            }
            PromiseAlreadySettledException promiseAlreadySettledException = new PromiseAlreadySettledException(str3);
            WeakReference<AppContext> weakReference = this.appContextHolder;
            if (!(weakReference == null || (appContext = (AppContext) weakReference.get()) == null)) {
                appContext.getErrorManager();
            }
            throw promiseAlreadySettledException;
        }
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putString(UniversalFirebaseFunctionsModule.CODE_KEY, str);
        if (str2 != null) {
            writableNativeMap.putString("message", str2);
        } else if (th != null) {
            writableNativeMap.putString("message", th.getMessage());
        } else {
            writableNativeMap.putString("message", "Error not specified.");
        }
        writableNativeMap.putNull("userInfo");
        if (th != null) {
            StackTraceElement[] stackTrace = th.getStackTrace();
            Intrinsics.checkNotNullExpressionValue(stackTrace, "getStackTrace(...)");
            WritableNativeArray writableNativeArray = new WritableNativeArray();
            int i = 0;
            while (i < stackTrace.length && i < 50) {
                StackTraceElement stackTraceElement = stackTrace[i];
                WritableMap writableNativeMap2 = new WritableNativeMap();
                writableNativeMap2.putString("class", stackTraceElement.getClassName());
                writableNativeMap2.putString("file", stackTraceElement.getFileName());
                writableNativeMap2.putInt("lineNumber", stackTraceElement.getLineNumber());
                writableNativeMap2.putString(JSStackTrace.METHOD_NAME_KEY, stackTraceElement.getMethodName());
                writableNativeArray.pushMap(writableNativeMap2);
                i++;
            }
            writableNativeMap.putArray("nativeStackAndroid", writableNativeArray);
        } else {
            writableNativeMap.putArray("nativeStackAndroid", new WritableNativeArray());
        }
        this.rejectBlock.invoke((Object) writableNativeMap);
        this.wasSettled = true;
    }
}
