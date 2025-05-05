package expo.modules.kotlin.viewevent;

import android.content.Context;
import android.view.View;
import com.facebook.react.bridge.CatalystInstance;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import expo.modules.adapters.react.NativeModulesProxy;
import expo.modules.core.logging.Logger;
import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.CoreLoggerKt;
import expo.modules.kotlin.ModuleHolder;
import expo.modules.kotlin.events.EventEmitter;
import expo.modules.kotlin.types.JSTypeConverter;
import expo.modules.kotlin.types.JSTypeConverterHelperKt;
import expo.modules.kotlin.views.CallbacksDefinition;
import expo.modules.kotlin.views.ViewManagerDefinition;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002BF\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012/\u0010\u0007\u001a+\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\t\u0012\b\b\u0003\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\bj\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\f¢\u0006\u0002\u0010\rJ\u0017\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010\u0013J\u0016\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010\u0016R7\u0010\u0007\u001a+\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\t\u0012\b\b\u0003\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\bj\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lexpo/modules/kotlin/viewevent/ViewEvent;", "T", "Lexpo/modules/kotlin/viewevent/ViewEventCallback;", "name", "", "view", "Landroid/view/View;", "coalescingKey", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "event", "", "Lexpo/modules/kotlin/viewevent/CoalescingKey;", "(Ljava/lang/String;Landroid/view/View;Lkotlin/jvm/functions/Function1;)V", "isValidated", "", "convertEventBody", "Lcom/facebook/react/bridge/WritableMap;", "arg", "(Ljava/lang/Object;)Lcom/facebook/react/bridge/WritableMap;", "invoke", "", "(Ljava/lang/Object;)V", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ViewEvent.kt */
public class ViewEvent<T> implements ViewEventCallback<T> {
    private final Function1<T, Short> coalescingKey;
    private boolean isValidated;
    private final String name;
    private final View view;

    public ViewEvent(String str, View view2, Function1<? super T, Short> function1) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(view2, "view");
        this.name = str;
        this.view = view2;
        this.coalescingKey = function1;
    }

    public void invoke(T t) {
        Context context = this.view.getContext();
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
        CatalystInstance catalystInstance = ((ReactContext) context).getCatalystInstance();
        Short sh = null;
        NativeModule nativeModule = catalystInstance != null ? catalystInstance.getNativeModule("NativeUnimoduleProxy") : null;
        NativeModulesProxy nativeModulesProxy = nativeModule instanceof NativeModulesProxy ? (NativeModulesProxy) nativeModule : null;
        if (nativeModulesProxy != null) {
            AppContext appContext = nativeModulesProxy.getKotlinInteropModuleRegistry().getAppContext();
            if (!this.isValidated) {
                ModuleHolder<?> moduleHolder = appContext.getRegistry().getModuleHolder(this.view.getClass());
                if (moduleHolder == null) {
                    Logger.warn$default(CoreLoggerKt.getLogger(), "⚠️ Cannot get module holder for " + this.view.getClass(), (Throwable) null, 2, (Object) null);
                    return;
                }
                ViewManagerDefinition viewManagerDefinition = moduleHolder.getDefinition().getViewManagerDefinition();
                CallbacksDefinition callbacksDefinition = viewManagerDefinition != null ? viewManagerDefinition.getCallbacksDefinition() : null;
                if (callbacksDefinition == null) {
                    Logger.warn$default(CoreLoggerKt.getLogger(), "⚠️ Cannot get callbacks for " + moduleHolder.getModule().getClass(), (Throwable) null, 2, (Object) null);
                    return;
                }
                String[] names = callbacksDefinition.getNames();
                int length = names.length;
                boolean z = false;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    } else if (Intrinsics.areEqual((Object) names[i], (Object) this.name)) {
                        z = true;
                        break;
                    } else {
                        i++;
                    }
                }
                if (!z) {
                    Logger.warn$default(CoreLoggerKt.getLogger(), "⚠️ Event " + this.name + " wasn't exported from " + moduleHolder.getModule().getClass(), (Throwable) null, 2, (Object) null);
                    return;
                }
                this.isValidated = true;
            }
            EventEmitter callbackInvoker$expo_modules_core_release = appContext.getCallbackInvoker$expo_modules_core_release();
            if (callbackInvoker$expo_modules_core_release != null) {
                int id = this.view.getId();
                String str = this.name;
                WritableMap convertEventBody = convertEventBody(t);
                Function1<T, Short> function1 = this.coalescingKey;
                if (function1 != null) {
                    sh = function1.invoke(t);
                }
                callbackInvoker$expo_modules_core_release.emit(id, str, convertEventBody, sh);
            }
        }
    }

    private final WritableMap convertEventBody(T t) {
        Object convertToJSValue$default = JSTypeConverter.convertToJSValue$default(JSTypeConverter.INSTANCE, t, (JSTypeConverter.ContainerProvider) null, 2, (Object) null);
        boolean z = true;
        if (!(convertToJSValue$default instanceof Unit) && convertToJSValue$default != null) {
            z = false;
        }
        if (z) {
            return null;
        }
        if (convertToJSValue$default instanceof WritableMap) {
            return (WritableMap) convertToJSValue$default;
        }
        WritableMap createMap = JSTypeConverter.DefaultContainerProvider.INSTANCE.createMap();
        JSTypeConverterHelperKt.putGeneric(createMap, "payload", convertToJSValue$default);
        return createMap;
    }
}
