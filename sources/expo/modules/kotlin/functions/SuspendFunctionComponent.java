package expo.modules.kotlin.functions;

import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.jni.ExpectedType;
import expo.modules.kotlin.jni.JavaScriptModuleObject;
import expo.modules.kotlin.jni.PromiseImpl;
import expo.modules.kotlin.types.AnyType;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001Be\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012H\u0010\u0007\u001aD\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u001d\u0012\u001b\u0012\b\b\u0001\u0012\u0004\u0018\u00010\n0\u0005¢\u0006\f\b\u000b\u0012\b\b\u0002\u0012\u0004\b\b(\f\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\r\u0012\u0006\u0012\u0004\u0018\u00010\n0\b¢\u0006\u0002\b\u000e¢\u0006\u0002\u0010\u000fJ\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J$\u0010\u0017\u001a\u00020\u00122\n\u0010\u0018\u001a\u0006\u0012\u0002\b\u00030\u00192\u0006\u0010\f\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016RR\u0010\u0007\u001aD\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u001d\u0012\u001b\u0012\b\b\u0001\u0012\u0004\u0018\u00010\n0\u0005¢\u0006\f\b\u000b\u0012\b\b\u0002\u0012\u0004\b\b(\f\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\r\u0012\u0006\u0012\u0004\u0018\u00010\n0\b¢\u0006\u0002\b\u000eX\u0004¢\u0006\u0004\n\u0002\u0010\u0010¨\u0006\u001d"}, d2 = {"Lexpo/modules/kotlin/functions/SuspendFunctionComponent;", "Lexpo/modules/kotlin/functions/BaseAsyncFunctionComponent;", "name", "", "desiredArgsTypes", "", "Lexpo/modules/kotlin/types/AnyType;", "body", "Lkotlin/Function3;", "Lkotlinx/coroutines/CoroutineScope;", "", "Lkotlin/ParameterName;", "args", "Lkotlin/coroutines/Continuation;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/String;[Lexpo/modules/kotlin/types/AnyType;Lkotlin/jvm/functions/Function3;)V", "Lkotlin/jvm/functions/Function3;", "attachToJSObject", "", "appContext", "Lexpo/modules/kotlin/AppContext;", "jsObject", "Lexpo/modules/kotlin/jni/JavaScriptModuleObject;", "call", "holder", "Lexpo/modules/kotlin/ModuleHolder;", "Lcom/facebook/react/bridge/ReadableArray;", "promise", "Lexpo/modules/kotlin/Promise;", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: SuspendFunctionComponent.kt */
public final class SuspendFunctionComponent extends BaseAsyncFunctionComponent {
    /* access modifiers changed from: private */
    public final Function3<CoroutineScope, Object[], Continuation<Object>, Object> body;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: SuspendFunctionComponent.kt */
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
            throw new UnsupportedOperationException("Method not decompiled: expo.modules.kotlin.functions.SuspendFunctionComponent.WhenMappings.<clinit>():void");
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SuspendFunctionComponent(String str, AnyType[] anyTypeArr, Function3<? super CoroutineScope, ? super Object[], ? super Continuation<Object>, ? extends Object> function3) {
        super(str, anyTypeArr);
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(anyTypeArr, "desiredArgsTypes");
        Intrinsics.checkNotNullParameter(function3, TtmlNode.TAG_BODY);
        this.body = function3;
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
            expo.modules.kotlin.modules.Module r0 = r11.getModule()
            expo.modules.kotlin.AppContext r0 = r0.getAppContext()
            expo.modules.kotlin.functions.Queues r1 = r10.getQueue()
            int[] r2 = expo.modules.kotlin.functions.SuspendFunctionComponent.WhenMappings.$EnumSwitchMapping$0
            int r1 = r1.ordinal()
            r1 = r2[r1]
            r2 = 1
            if (r1 == r2) goto L_0x0034
            r2 = 2
            if (r1 != r2) goto L_0x002e
            kotlinx.coroutines.CoroutineScope r0 = r0.getModulesQueue()
            goto L_0x0038
        L_0x002e:
            kotlin.NoWhenBranchMatchedException r11 = new kotlin.NoWhenBranchMatchedException
            r11.<init>()
            throw r11
        L_0x0034:
            kotlinx.coroutines.CoroutineScope r0 = r0.getMainQueue()
        L_0x0038:
            r1 = r0
            r2 = 0
            r3 = 0
            expo.modules.kotlin.functions.SuspendFunctionComponent$call$1 r0 = new expo.modules.kotlin.functions.SuspendFunctionComponent$call$1
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
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.kotlin.functions.SuspendFunctionComponent.call(expo.modules.kotlin.ModuleHolder, com.facebook.react.bridge.ReadableArray, expo.modules.kotlin.Promise):void");
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
        javaScriptModuleObject.registerAsyncFunction(name2, takesOwner$expo_modules_core_release, (ExpectedType[]) ((List) arrayList).toArray(new ExpectedType[0]), new SuspendFunctionComponent$$ExternalSyntheticLambda0(appContextHolder$expo_modules_core_release, name, this, appContext));
    }

    /* access modifiers changed from: private */
    public static final void attachToJSObject$lambda$1(WeakReference weakReference, String str, SuspendFunctionComponent suspendFunctionComponent, AppContext appContext, Object[] objArr, PromiseImpl promiseImpl) {
        CoroutineScope coroutineScope;
        Intrinsics.checkNotNullParameter(weakReference, "$appContextHolder");
        Intrinsics.checkNotNullParameter(str, "$moduleName");
        Intrinsics.checkNotNullParameter(suspendFunctionComponent, "this$0");
        Intrinsics.checkNotNullParameter(appContext, "$appContext");
        Intrinsics.checkNotNullParameter(objArr, "args");
        Intrinsics.checkNotNullParameter(promiseImpl, "promiseImpl");
        int i = WhenMappings.$EnumSwitchMapping$0[suspendFunctionComponent.getQueue().ordinal()];
        if (i == 1) {
            coroutineScope = appContext.getMainQueue();
        } else if (i == 2) {
            coroutineScope = appContext.getModulesQueue();
        } else {
            throw new NoWhenBranchMatchedException();
        }
        Job unused = BuildersKt__Builders_commonKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new SuspendFunctionComponent$attachToJSObject$2$1(promiseImpl, suspendFunctionComponent, str, objArr, (Continuation<? super SuspendFunctionComponent$attachToJSObject$2$1>) null), 3, (Object) null);
    }
}
