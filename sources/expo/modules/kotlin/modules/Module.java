package expo.modules.kotlin.modules;

import android.os.Bundle;
import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.events.EventEmitter;
import expo.modules.kotlin.providers.AppContextProvider;
import expo.modules.kotlin.types.Enumerable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KFunction;
import kotlin.reflect.KParameter;
import kotlin.reflect.KProperty1;
import kotlin.reflect.full.KClasses;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J)\u0010\u001a\u001a\u00020\u001b\"\u0012\b\u0000\u0010\u001c*\u00020\u001d*\b\u0012\u0004\u0012\u0002H\u001c0\u001e2\u0006\u0010\u001f\u001a\u0002H\u001cH\u0002¢\u0006\u0002\u0010 J\b\u0010!\u001a\u00020\"H&J3\u0010#\u001a\u00020$\"\u0012\b\u0000\u0010\u001c*\u00020\u001d*\b\u0012\u0004\u0012\u0002H\u001c0\u001e2\u0006\u0010%\u001a\u0002H\u001c2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010'¢\u0006\u0002\u0010(JA\u0010#\u001a\u00020$\"\u0012\b\u0000\u0010\u001c*\u00020\u001d*\b\u0012\u0004\u0012\u0002H\u001c0\u001e2\u0006\u0010%\u001a\u0002H\u001c2\u0018\b\u0002\u0010&\u001a\u0012\u0012\u0004\u0012\u00020\u001b\u0012\u0006\u0012\u0004\u0018\u00010*\u0018\u00010)¢\u0006\u0002\u0010+J\u001a\u0010#\u001a\u00020$2\u0006\u0010,\u001a\u00020\u001b2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010'J$\u0010#\u001a\u00020$2\u0006\u0010,\u001a\u00020\u001b2\u0014\u0010&\u001a\u0010\u0012\u0004\u0012\u00020\u001b\u0012\u0006\u0012\u0004\u0018\u00010*0)R\"\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u00048VX\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0007R*\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r8\u0000@\u0000X.¢\u0006\u0014\n\u0000\u0012\u0004\b\u000f\u0010\u0002\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001d\u0010\u0014\u001a\u0004\u0018\u00010\u00158BX\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u0016\u0010\u0017¨\u0006-"}, d2 = {"Lexpo/modules/kotlin/modules/Module;", "Lexpo/modules/kotlin/providers/AppContextProvider;", "()V", "_appContext", "Lexpo/modules/kotlin/AppContext;", "get_appContext$expo_modules_core_release$annotations", "get_appContext$expo_modules_core_release", "()Lexpo/modules/kotlin/AppContext;", "set_appContext$expo_modules_core_release", "(Lexpo/modules/kotlin/AppContext;)V", "appContext", "getAppContext", "coroutineScopeDelegate", "Lkotlin/Lazy;", "Lkotlinx/coroutines/CoroutineScope;", "getCoroutineScopeDelegate$annotations", "getCoroutineScopeDelegate", "()Lkotlin/Lazy;", "setCoroutineScopeDelegate", "(Lkotlin/Lazy;)V", "moduleEventEmitter", "Lexpo/modules/kotlin/events/EventEmitter;", "getModuleEventEmitter", "()Lexpo/modules/kotlin/events/EventEmitter;", "moduleEventEmitter$delegate", "Lkotlin/Lazy;", "convertEnumToString", "", "T", "Lexpo/modules/kotlin/types/Enumerable;", "", "enumValue", "(Ljava/lang/Enum;)Ljava/lang/String;", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "sendEvent", "", "enum", "body", "Landroid/os/Bundle;", "(Ljava/lang/Enum;Landroid/os/Bundle;)V", "", "", "(Ljava/lang/Enum;Ljava/util/Map;)V", "name", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: Module.kt */
public abstract class Module implements AppContextProvider {
    private AppContext _appContext;
    public Lazy<? extends CoroutineScope> coroutineScopeDelegate;
    private final Lazy moduleEventEmitter$delegate = LazyKt.lazy(new Module$moduleEventEmitter$2(this));

    public static /* synthetic */ void getCoroutineScopeDelegate$annotations() {
    }

    public static /* synthetic */ void get_appContext$expo_modules_core_release$annotations() {
    }

    public abstract ModuleDefinitionData definition();

    public final AppContext get_appContext$expo_modules_core_release() {
        return this._appContext;
    }

    public final void set_appContext$expo_modules_core_release(AppContext appContext) {
        this._appContext = appContext;
    }

    public AppContext getAppContext() {
        AppContext appContext = this._appContext;
        if (appContext != null) {
            return appContext;
        }
        throw new IllegalArgumentException("The module wasn't created! You can't access the app context.".toString());
    }

    private final EventEmitter getModuleEventEmitter() {
        return (EventEmitter) this.moduleEventEmitter$delegate.getValue();
    }

    public final Lazy<CoroutineScope> getCoroutineScopeDelegate() {
        Lazy<? extends CoroutineScope> lazy = this.coroutineScopeDelegate;
        if (lazy != null) {
            return lazy;
        }
        Intrinsics.throwUninitializedPropertyAccessException("coroutineScopeDelegate");
        return null;
    }

    public final void setCoroutineScopeDelegate(Lazy<? extends CoroutineScope> lazy) {
        Intrinsics.checkNotNullParameter(lazy, "<set-?>");
        this.coroutineScopeDelegate = lazy;
    }

    public static /* synthetic */ void sendEvent$default(Module module, String str, Bundle bundle, int i, Object obj) {
        if (obj == null) {
            if ((i & 2) != 0) {
                bundle = Bundle.EMPTY;
            }
            module.sendEvent(str, bundle);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendEvent");
    }

    public final void sendEvent(String str, Bundle bundle) {
        Intrinsics.checkNotNullParameter(str, "name");
        EventEmitter moduleEventEmitter = getModuleEventEmitter();
        if (moduleEventEmitter != null) {
            moduleEventEmitter.emit(str, bundle);
        }
    }

    public final void sendEvent(String str, Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(map, TtmlNode.TAG_BODY);
        EventEmitter moduleEventEmitter = getModuleEventEmitter();
        if (moduleEventEmitter != null) {
            moduleEventEmitter.emit(str, (Map<?, ?>) map);
        }
    }

    public static /* synthetic */ void sendEvent$default(Module module, Enum enumR, Bundle bundle, int i, Object obj) {
        if (obj == null) {
            if ((i & 2) != 0) {
                bundle = Bundle.EMPTY;
            }
            module.sendEvent(enumR, bundle);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendEvent");
    }

    public final <T extends Enum<T> & Enumerable> void sendEvent(T t, Bundle bundle) {
        Intrinsics.checkNotNullParameter(t, "enum");
        EventEmitter moduleEventEmitter = getModuleEventEmitter();
        if (moduleEventEmitter != null) {
            moduleEventEmitter.emit(convertEnumToString(t), bundle);
        }
    }

    public static /* synthetic */ void sendEvent$default(Module module, Enum enumR, Map map, int i, Object obj) {
        if (obj == null) {
            if ((i & 2) != 0) {
                map = null;
            }
            module.sendEvent(enumR, (Map<String, ? extends Object>) map);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendEvent");
    }

    public final <T extends Enum<T> & Enumerable> void sendEvent(T t, Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(t, "enum");
        EventEmitter moduleEventEmitter = getModuleEventEmitter();
        if (moduleEventEmitter != null) {
            moduleEventEmitter.emit(convertEnumToString(t), (Map<?, ?>) map);
        }
    }

    private final <T extends Enum<T> & Enumerable> String convertEnumToString(T t) {
        Object obj;
        List<KParameter> parameters;
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(t.getClass());
        KFunction primaryConstructor = KClasses.getPrimaryConstructor(orCreateKotlinClass);
        boolean z = false;
        if (!(primaryConstructor == null || (parameters = primaryConstructor.getParameters()) == null || parameters.size() != 1)) {
            z = true;
        }
        if (!z) {
            return t.name();
        }
        String name = ((KParameter) CollectionsKt.first(primaryConstructor.getParameters())).getName();
        Iterator it = KClasses.getDeclaredMemberProperties(orCreateKotlinClass).iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (Intrinsics.areEqual((Object) ((KProperty1) obj).getName(), (Object) name)) {
                break;
            }
        }
        KProperty1 kProperty1 = (KProperty1) obj;
        if (kProperty1 == null) {
            throw new IllegalArgumentException(("Cannot find a property for " + name + " parameter").toString());
        } else if (Intrinsics.areEqual((Object) kProperty1.getReturnType().getClassifier(), (Object) Reflection.getOrCreateKotlinClass(String.class))) {
            return (String) kProperty1.get(t);
        } else {
            throw new IllegalArgumentException("The enum parameter has to be a string.".toString());
        }
    }
}
