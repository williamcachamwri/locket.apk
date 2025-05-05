package expo.modules.kotlin;

import android.view.View;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.util.JSStackTrace;
import expo.modules.kotlin.activityresult.AppContextActivityResultCaller;
import expo.modules.kotlin.events.BasicEventListener;
import expo.modules.kotlin.events.EventListener;
import expo.modules.kotlin.events.EventListenerWithPayload;
import expo.modules.kotlin.events.EventListenerWithSenderAndPayload;
import expo.modules.kotlin.events.EventName;
import expo.modules.kotlin.exception.CodedException;
import expo.modules.kotlin.exception.FunctionCallException;
import expo.modules.kotlin.exception.MethodNotFoundException;
import expo.modules.kotlin.exception.UnexpectedException;
import expo.modules.kotlin.functions.BaseAsyncFunctionComponent;
import expo.modules.kotlin.functions.SyncFunctionComponent;
import expo.modules.kotlin.jni.JavaScriptModuleObject;
import expo.modules.kotlin.modules.Module;
import expo.modules.kotlin.modules.ModuleDefinitionData;
import expo.modules.kotlin.views.ViewManagerDefinition;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00028\u0000¢\u0006\u0002\u0010\u0005J\u001e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dJ\u0018\u0010\u001e\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u001bJ\u000e\u0010\u001f\u001a\u00020\u00182\u0006\u0010 \u001a\u00020!J!\u0010\u001f\u001a\u00020\u0018\"\u0004\b\u0001\u0010\"2\u0006\u0010 \u001a\u00020!2\u0006\u0010#\u001a\u0002H\"¢\u0006\u0002\u0010$J/\u0010\u001f\u001a\u00020\u0018\"\u0004\b\u0001\u0010%\"\u0004\b\u0002\u0010\"2\u0006\u0010 \u001a\u00020!2\u0006\u0010&\u001a\u0002H%2\u0006\u0010#\u001a\u0002H\"¢\u0006\u0002\u0010'J\u0006\u0010(\u001a\u00020\u0018J\u0010\u0010)\u001a\f\u0012\u0006\b\u0001\u0012\u00020+\u0018\u00010*R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001b\u0010\n\u001a\u00020\u000b8FX\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0004\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0013\u001a\u00020\u00148F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016¨\u0006,"}, d2 = {"Lexpo/modules/kotlin/ModuleHolder;", "T", "Lexpo/modules/kotlin/modules/Module;", "", "module", "(Lexpo/modules/kotlin/modules/Module;)V", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "getDefinition", "()Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "jsObject", "Lexpo/modules/kotlin/jni/JavaScriptModuleObject;", "getJsObject", "()Lexpo/modules/kotlin/jni/JavaScriptModuleObject;", "jsObject$delegate", "Lkotlin/Lazy;", "getModule", "()Lexpo/modules/kotlin/modules/Module;", "Lexpo/modules/kotlin/modules/Module;", "name", "", "getName", "()Ljava/lang/String;", "call", "", "methodName", "args", "Lcom/facebook/react/bridge/ReadableArray;", "promise", "Lexpo/modules/kotlin/Promise;", "callSync", "post", "eventName", "Lexpo/modules/kotlin/events/EventName;", "Payload", "payload", "(Lexpo/modules/kotlin/events/EventName;Ljava/lang/Object;)V", "Sender", "sender", "(Lexpo/modules/kotlin/events/EventName;Ljava/lang/Object;Ljava/lang/Object;)V", "registerContracts", "viewClass", "Lkotlin/reflect/KClass;", "Landroid/view/View;", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ModuleHolder.kt */
public final class ModuleHolder<T extends Module> {
    private final ModuleDefinitionData definition;
    private final Lazy jsObject$delegate = LazyKt.lazy(new ModuleHolder$jsObject$2(this));
    private final T module;

    public ModuleHolder(T t) {
        Intrinsics.checkNotNullParameter(t, "module");
        this.module = t;
        this.definition = t.definition();
    }

    public final T getModule() {
        return this.module;
    }

    public final ModuleDefinitionData getDefinition() {
        return this.definition;
    }

    public final String getName() {
        return this.definition.getName();
    }

    public final JavaScriptModuleObject getJsObject() {
        return (JavaScriptModuleObject) this.jsObject$delegate.getValue();
    }

    public final void call(String str, ReadableArray readableArray, Promise promise) {
        CodedException codedException;
        Intrinsics.checkNotNullParameter(str, JSStackTrace.METHOD_NAME_KEY);
        Intrinsics.checkNotNullParameter(readableArray, "args");
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        try {
            BaseAsyncFunctionComponent baseAsyncFunctionComponent = this.definition.getAsyncFunctions().get(str);
            if (baseAsyncFunctionComponent != null) {
                baseAsyncFunctionComponent.call(this, readableArray, promise);
                Unit unit = Unit.INSTANCE;
                return;
            }
            throw new MethodNotFoundException();
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
            throw new FunctionCallException(str, this.definition.getName(), codedException);
        }
    }

    public final Object callSync(String str, ReadableArray readableArray) {
        Intrinsics.checkNotNullParameter(str, JSStackTrace.METHOD_NAME_KEY);
        Intrinsics.checkNotNullParameter(readableArray, "args");
        SyncFunctionComponent syncFunctionComponent = this.definition.getSyncFunctions().get(str);
        if (syncFunctionComponent != null) {
            return syncFunctionComponent.call(readableArray);
        }
        throw new MethodNotFoundException();
    }

    public final void post(EventName eventName) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        EventListener eventListener = this.definition.getEventListeners().get(eventName);
        if (eventListener != null) {
            BasicEventListener basicEventListener = eventListener instanceof BasicEventListener ? (BasicEventListener) eventListener : null;
            if (basicEventListener != null) {
                basicEventListener.call();
            }
        }
    }

    public final <Payload> void post(EventName eventName, Payload payload) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        EventListener eventListener = this.definition.getEventListeners().get(eventName);
        if (eventListener != null) {
            EventListenerWithPayload eventListenerWithPayload = eventListener instanceof EventListenerWithPayload ? (EventListenerWithPayload) eventListener : null;
            if (eventListenerWithPayload != null) {
                eventListenerWithPayload.call(payload);
            }
        }
    }

    public final <Sender, Payload> void post(EventName eventName, Sender sender, Payload payload) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        EventListener eventListener = this.definition.getEventListeners().get(eventName);
        if (eventListener != null) {
            EventListenerWithSenderAndPayload eventListenerWithSenderAndPayload = eventListener instanceof EventListenerWithSenderAndPayload ? (EventListenerWithSenderAndPayload) eventListener : null;
            if (eventListenerWithSenderAndPayload != null) {
                eventListenerWithSenderAndPayload.call(sender, payload);
            }
        }
    }

    public final void registerContracts() {
        Function2<AppContextActivityResultCaller, Continuation<? super Unit>, Object> registerContracts = this.definition.getRegisterContracts();
        if (registerContracts != null) {
            Job unused = BuildersKt__Builders_commonKt.launch$default(this.module.getAppContext().getMainQueue(), (CoroutineContext) null, (CoroutineStart) null, new ModuleHolder$registerContracts$1$1(registerContracts, this, (Continuation<? super ModuleHolder$registerContracts$1$1>) null), 3, (Object) null);
        }
    }

    public final KClass<? extends View> viewClass() {
        Class<? extends View> viewType$expo_modules_core_release;
        ViewManagerDefinition viewManagerDefinition = this.definition.getViewManagerDefinition();
        if (viewManagerDefinition == null || (viewType$expo_modules_core_release = viewManagerDefinition.getViewType$expo_modules_core_release()) == null) {
            return null;
        }
        return JvmClassMappingKt.getKotlinClass(viewType$expo_modules_core_release);
    }
}
