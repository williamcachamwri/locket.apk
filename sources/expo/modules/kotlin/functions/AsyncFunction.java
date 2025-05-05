package expo.modules.kotlin.functions;

import android.view.View;
import com.facebook.react.bridge.ReadableArray;
import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.exception.CodedException;
import expo.modules.kotlin.jni.ExpectedType;
import expo.modules.kotlin.jni.JavaScriptModuleObject;
import expo.modules.kotlin.jni.PromiseImpl;
import expo.modules.kotlin.types.AnyType;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.reflect.KClassifier;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J$\u0010\u000e\u001a\u00020\t2\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u001d\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H ¢\u0006\u0002\b\u0016J/\u0010\u0015\u001a\u00020\t2\u000e\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00170\u00052\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\n\u001a\u00020\u000bH ¢\u0006\u0004\b\u0016\u0010\u0018J\u001e\u0010\u0019\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\t0\u001bH\u0002¨\u0006\u001c"}, d2 = {"Lexpo/modules/kotlin/functions/AsyncFunction;", "Lexpo/modules/kotlin/functions/BaseAsyncFunctionComponent;", "name", "", "desiredArgsTypes", "", "Lexpo/modules/kotlin/types/AnyType;", "(Ljava/lang/String;[Lexpo/modules/kotlin/types/AnyType;)V", "attachToJSObject", "", "appContext", "Lexpo/modules/kotlin/AppContext;", "jsObject", "Lexpo/modules/kotlin/jni/JavaScriptModuleObject;", "call", "holder", "Lexpo/modules/kotlin/ModuleHolder;", "args", "Lcom/facebook/react/bridge/ReadableArray;", "promise", "Lexpo/modules/kotlin/Promise;", "callUserImplementation", "callUserImplementation$expo_modules_core_release", "", "([Ljava/lang/Object;Lexpo/modules/kotlin/Promise;Lexpo/modules/kotlin/AppContext;)V", "dispatchOnQueue", "block", "Lkotlin/Function0;", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: AsyncFunction.kt */
public abstract class AsyncFunction extends BaseAsyncFunctionComponent {

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: AsyncFunction.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        static {
            /*
                expo.modules.kotlin.functions.Queues[] r0 = expo.modules.kotlin.functions.Queues.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                expo.modules.kotlin.functions.Queues r1 = expo.modules.kotlin.functions.Queues.MAIN     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                expo.modules.kotlin.functions.Queues r1 = expo.modules.kotlin.functions.Queues.DEFAULT     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: expo.modules.kotlin.functions.AsyncFunction.WhenMappings.<clinit>():void");
        }
    }

    public abstract void callUserImplementation$expo_modules_core_release(ReadableArray readableArray, Promise promise) throws CodedException;

    public abstract void callUserImplementation$expo_modules_core_release(Object[] objArr, Promise promise, AppContext appContext);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AsyncFunction(String str, AnyType[] anyTypeArr) {
        super(str, anyTypeArr);
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(anyTypeArr, "desiredArgsTypes");
    }

    /* JADX WARNING: type inference failed for: r11v0, types: [expo.modules.kotlin.ModuleHolder, expo.modules.kotlin.ModuleHolder<?>, java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void call(expo.modules.kotlin.ModuleHolder<?> r11, com.facebook.react.bridge.ReadableArray r12, expo.modules.kotlin.Promise r13) {
        /*
            r10 = this;
            java.lang.String r0 = "holder"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
            java.lang.String r0 = "args"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r0)
            java.lang.String r0 = "promise"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r0)
            expo.modules.kotlin.functions.Queues r0 = r10.getQueue()
            int[] r1 = expo.modules.kotlin.functions.AsyncFunction.WhenMappings.$EnumSwitchMapping$0
            int r0 = r0.ordinal()
            r0 = r1[r0]
            r1 = 1
            if (r0 == r1) goto L_0x0029
            r1 = 2
            if (r0 != r1) goto L_0x0023
            r0 = 0
            goto L_0x0035
        L_0x0023:
            kotlin.NoWhenBranchMatchedException r11 = new kotlin.NoWhenBranchMatchedException
            r11.<init>()
            throw r11
        L_0x0029:
            expo.modules.kotlin.modules.Module r0 = r11.getModule()
            expo.modules.kotlin.AppContext r0 = r0.getAppContext()
            kotlinx.coroutines.CoroutineScope r0 = r0.getMainQueue()
        L_0x0035:
            r1 = r0
            if (r1 != 0) goto L_0x003c
            r10.callUserImplementation$expo_modules_core_release(r12, r13)
            goto L_0x0051
        L_0x003c:
            r2 = 0
            r3 = 0
            expo.modules.kotlin.functions.AsyncFunction$call$1 r0 = new expo.modules.kotlin.functions.AsyncFunction$call$1
            r9 = 0
            r4 = r0
            r5 = r13
            r6 = r10
            r7 = r11
            r8 = r12
            r4.<init>(r5, r6, r7, r8, r9)
            r4 = r0
            kotlin.jvm.functions.Function2 r4 = (kotlin.jvm.functions.Function2) r4
            r5 = 3
            r6 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r1, r2, r3, r4, r5, r6)
        L_0x0051:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.kotlin.functions.AsyncFunction.call(expo.modules.kotlin.ModuleHolder, com.facebook.react.bridge.ReadableArray, expo.modules.kotlin.Promise):void");
    }

    public void attachToJSObject(AppContext appContext, JavaScriptModuleObject javaScriptModuleObject) {
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        Intrinsics.checkNotNullParameter(javaScriptModuleObject, "jsObject");
        WeakReference<AppContext> appContextHolder$expo_modules_core_release = appContext.getJsiInterop$expo_modules_core_release().getAppContextHolder$expo_modules_core_release();
        String name = javaScriptModuleObject.getName();
        String name2 = getName();
        boolean takesOwner$expo_modules_core_release = getTakesOwner$expo_modules_core_release();
        AnyType[] desiredArgsTypes = getDesiredArgsTypes();
        Collection arrayList = new ArrayList(desiredArgsTypes.length);
        for (AnyType cppRequiredTypes : desiredArgsTypes) {
            arrayList.add(cppRequiredTypes.getCppRequiredTypes());
        }
        javaScriptModuleObject.registerAsyncFunction(name2, takesOwner$expo_modules_core_release, (ExpectedType[]) ((List) arrayList).toArray(new ExpectedType[0]), new AsyncFunction$$ExternalSyntheticLambda0(appContextHolder$expo_modules_core_release, name, this, appContext));
    }

    /* access modifiers changed from: private */
    public static final void attachToJSObject$lambda$1(WeakReference weakReference, String str, AsyncFunction asyncFunction, AppContext appContext, Object[] objArr, PromiseImpl promiseImpl) {
        Intrinsics.checkNotNullParameter(weakReference, "$appContextHolder");
        Intrinsics.checkNotNullParameter(str, "$moduleName");
        Intrinsics.checkNotNullParameter(asyncFunction, "this$0");
        Intrinsics.checkNotNullParameter(appContext, "$appContext");
        Intrinsics.checkNotNullParameter(objArr, "args");
        Intrinsics.checkNotNullParameter(promiseImpl, "promiseImpl");
        asyncFunction.dispatchOnQueue(appContext, new AsyncFunction$attachToJSObject$2$functionBody$1(promiseImpl, asyncFunction, str, objArr, appContext));
    }

    private final void dispatchOnQueue(AppContext appContext, Function0<Unit> function0) {
        boolean z;
        int i = WhenMappings.$EnumSwitchMapping$0[getQueue().ordinal()];
        boolean z2 = true;
        if (i == 1) {
            AnyType[] desiredArgsTypes = getDesiredArgsTypes();
            int length = desiredArgsTypes.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    z2 = false;
                    break;
                }
                KClassifier classifier = desiredArgsTypes[i2].getKType().getClassifier();
                KClass kClass = classifier instanceof KClass ? (KClass) classifier : null;
                if (kClass == null) {
                    z = false;
                } else {
                    z = View.class.isAssignableFrom(JvmClassMappingKt.getJavaClass(kClass));
                }
                if (z) {
                    break;
                }
                i2++;
            }
            if (z2) {
                appContext.dispatchOnMainUsingUIManager$expo_modules_core_release(function0);
            } else {
                Job unused = BuildersKt__Builders_commonKt.launch$default(appContext.getMainQueue(), (CoroutineContext) null, (CoroutineStart) null, new AsyncFunction$dispatchOnQueue$3(function0, (Continuation<? super AsyncFunction$dispatchOnQueue$3>) null), 3, (Object) null);
            }
        } else if (i == 2) {
            Job unused2 = BuildersKt__Builders_commonKt.launch$default(appContext.getModulesQueue(), (CoroutineContext) null, (CoroutineStart) null, new AsyncFunction$dispatchOnQueue$1(function0, (Continuation<? super AsyncFunction$dispatchOnQueue$1>) null), 3, (Object) null);
        }
    }
}
