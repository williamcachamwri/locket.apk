package expo.modules.kotlin.views;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.react.bridge.CatalystInstance;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactContext;
import expo.modules.adapters.react.NativeModulesProxy;
import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.defaultmodules.ErrorManagerModule;
import expo.modules.kotlin.exception.CodedException;
import expo.modules.kotlin.functions.BaseAsyncFunctionComponent;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0001\u0012\u0018\u0010\u0002\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0003\u0012\u000e\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\b\u0012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n\u0012\u0016\b\u0002\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013\u0012\u0016\b\u0002\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e\u0012\u000e\b\u0002\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016¢\u0006\u0002\u0010\u0018J\u0016\u0010(\u001a\u00020\u00062\u0006\u0010)\u001a\u00020\u00042\u0006\u0010*\u001a\u00020\u0005J\u0006\u0010+\u001a\u00020,J\u0016\u0010-\u001a\u00020\u000f2\u0006\u0010.\u001a\u00020\u00062\u0006\u0010/\u001a\u000200R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u001f\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u001f\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001eR \u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\nX\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0017\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0016¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001aR \u0010\u0002\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0003X\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0013¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u001c\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'¨\u00061"}, d2 = {"Lexpo/modules/kotlin/views/ViewManagerDefinition;", "", "viewFactory", "Lkotlin/Function2;", "Landroid/content/Context;", "Lexpo/modules/kotlin/AppContext;", "Landroid/view/View;", "viewType", "Ljava/lang/Class;", "props", "", "", "Lexpo/modules/kotlin/views/AnyViewProp;", "onViewDestroys", "Lkotlin/Function1;", "", "callbacksDefinition", "Lexpo/modules/kotlin/views/CallbacksDefinition;", "viewGroupDefinition", "Lexpo/modules/kotlin/views/ViewGroupDefinition;", "onViewDidUpdateProps", "asyncFunctions", "", "Lexpo/modules/kotlin/functions/BaseAsyncFunctionComponent;", "(Lkotlin/jvm/functions/Function2;Ljava/lang/Class;Ljava/util/Map;Lkotlin/jvm/functions/Function1;Lexpo/modules/kotlin/views/CallbacksDefinition;Lexpo/modules/kotlin/views/ViewGroupDefinition;Lkotlin/jvm/functions/Function1;Ljava/util/List;)V", "getAsyncFunctions", "()Ljava/util/List;", "getCallbacksDefinition", "()Lexpo/modules/kotlin/views/CallbacksDefinition;", "getOnViewDestroys", "()Lkotlin/jvm/functions/Function1;", "getOnViewDidUpdateProps", "getProps$expo_modules_core_release", "()Ljava/util/Map;", "propsNames", "getPropsNames", "getViewGroupDefinition", "()Lexpo/modules/kotlin/views/ViewGroupDefinition;", "getViewType$expo_modules_core_release", "()Ljava/lang/Class;", "createView", "context", "appContext", "getViewManagerType", "Lexpo/modules/kotlin/views/ViewManagerType;", "handleException", "view", "exception", "Lexpo/modules/kotlin/exception/CodedException;", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ViewManagerDefinition.kt */
public final class ViewManagerDefinition {
    private final List<BaseAsyncFunctionComponent> asyncFunctions;
    private final CallbacksDefinition callbacksDefinition;
    private final Function1<View, Unit> onViewDestroys;
    private final Function1<View, Unit> onViewDidUpdateProps;
    private final Map<String, AnyViewProp> props;
    private final List<String> propsNames;
    private final Function2<Context, AppContext, View> viewFactory;
    private final ViewGroupDefinition viewGroupDefinition;
    private final Class<? extends View> viewType;

    public ViewManagerDefinition(Function2<? super Context, ? super AppContext, ? extends View> function2, Class<? extends View> cls, Map<String, ? extends AnyViewProp> map, Function1<? super View, Unit> function1, CallbacksDefinition callbacksDefinition2, ViewGroupDefinition viewGroupDefinition2, Function1<? super View, Unit> function12, List<? extends BaseAsyncFunctionComponent> list) {
        Intrinsics.checkNotNullParameter(function2, "viewFactory");
        Intrinsics.checkNotNullParameter(cls, "viewType");
        Intrinsics.checkNotNullParameter(map, "props");
        Intrinsics.checkNotNullParameter(list, "asyncFunctions");
        this.viewFactory = function2;
        this.viewType = cls;
        this.props = map;
        this.onViewDestroys = function1;
        this.callbacksDefinition = callbacksDefinition2;
        this.viewGroupDefinition = viewGroupDefinition2;
        this.onViewDidUpdateProps = function12;
        this.asyncFunctions = list;
        this.propsNames = CollectionsKt.toList(map.keySet());
    }

    public final Class<? extends View> getViewType$expo_modules_core_release() {
        return this.viewType;
    }

    public final Map<String, AnyViewProp> getProps$expo_modules_core_release() {
        return this.props;
    }

    public final Function1<View, Unit> getOnViewDestroys() {
        return this.onViewDestroys;
    }

    public final CallbacksDefinition getCallbacksDefinition() {
        return this.callbacksDefinition;
    }

    public final ViewGroupDefinition getViewGroupDefinition() {
        return this.viewGroupDefinition;
    }

    public final Function1<View, Unit> getOnViewDidUpdateProps() {
        return this.onViewDidUpdateProps;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ ViewManagerDefinition(kotlin.jvm.functions.Function2 r13, java.lang.Class r14, java.util.Map r15, kotlin.jvm.functions.Function1 r16, expo.modules.kotlin.views.CallbacksDefinition r17, expo.modules.kotlin.views.ViewGroupDefinition r18, kotlin.jvm.functions.Function1 r19, java.util.List r20, int r21, kotlin.jvm.internal.DefaultConstructorMarker r22) {
        /*
            r12 = this;
            r0 = r21
            r1 = r0 & 8
            r2 = 0
            if (r1 == 0) goto L_0x0009
            r7 = r2
            goto L_0x000b
        L_0x0009:
            r7 = r16
        L_0x000b:
            r1 = r0 & 16
            if (r1 == 0) goto L_0x0011
            r8 = r2
            goto L_0x0013
        L_0x0011:
            r8 = r17
        L_0x0013:
            r1 = r0 & 32
            if (r1 == 0) goto L_0x0019
            r9 = r2
            goto L_0x001b
        L_0x0019:
            r9 = r18
        L_0x001b:
            r1 = r0 & 64
            if (r1 == 0) goto L_0x0021
            r10 = r2
            goto L_0x0023
        L_0x0021:
            r10 = r19
        L_0x0023:
            r0 = r0 & 128(0x80, float:1.794E-43)
            if (r0 == 0) goto L_0x002d
            java.util.List r0 = kotlin.collections.CollectionsKt.emptyList()
            r11 = r0
            goto L_0x002f
        L_0x002d:
            r11 = r20
        L_0x002f:
            r3 = r12
            r4 = r13
            r5 = r14
            r6 = r15
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.kotlin.views.ViewManagerDefinition.<init>(kotlin.jvm.functions.Function2, java.lang.Class, java.util.Map, kotlin.jvm.functions.Function1, expo.modules.kotlin.views.CallbacksDefinition, expo.modules.kotlin.views.ViewGroupDefinition, kotlin.jvm.functions.Function1, java.util.List, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final List<BaseAsyncFunctionComponent> getAsyncFunctions() {
        return this.asyncFunctions;
    }

    public final View createView(Context context, AppContext appContext) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        return this.viewFactory.invoke(context, appContext);
    }

    public final List<String> getPropsNames() {
        return this.propsNames;
    }

    public final ViewManagerType getViewManagerType() {
        if (ViewGroup.class.isAssignableFrom(this.viewType)) {
            return ViewManagerType.GROUP;
        }
        return ViewManagerType.SIMPLE;
    }

    public final void handleException(View view, CodedException codedException) {
        ErrorManagerModule errorManager;
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(codedException, "exception");
        Context context = view.getContext();
        NativeModulesProxy nativeModulesProxy = null;
        ReactContext reactContext = context instanceof ReactContext ? (ReactContext) context : null;
        if (reactContext != null) {
            CatalystInstance catalystInstance = reactContext.getCatalystInstance();
            NativeModule nativeModule = catalystInstance != null ? catalystInstance.getNativeModule("NativeUnimoduleProxy") : null;
            if (nativeModule instanceof NativeModulesProxy) {
                nativeModulesProxy = (NativeModulesProxy) nativeModule;
            }
            if (nativeModulesProxy != null && (errorManager = nativeModulesProxy.getKotlinInteropModuleRegistry().getAppContext().getErrorManager()) != null) {
                errorManager.reportExceptionToLogBox(codedException);
            }
        }
    }
}
