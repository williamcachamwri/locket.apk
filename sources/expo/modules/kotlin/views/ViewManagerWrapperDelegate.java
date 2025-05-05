package expo.modules.kotlin.views;

import android.content.Context;
import android.view.View;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.common.MapBuilder;
import expo.modules.kotlin.CoreLoggerKt;
import expo.modules.kotlin.ModuleHolder;
import expo.modules.kotlin.events.KModuleEventEmitterWrapperKt;
import expo.modules.kotlin.exception.CodedException;
import expo.modules.kotlin.exception.UnexpectedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0011\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cJ\u0014\u0010\u001d\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0011J\u000e\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001aJ\u000e\u0010!\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001aJ\u001c\u0010\"\u001a\b\u0012\u0004\u0012\u00020\r0#2\u0006\u0010 \u001a\u00020\u001a2\u0006\u0010$\u001a\u00020%R\u0014\u0010\u0005\u001a\u00020\u00068BX\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u001e\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\u0004R\u0011\u0010\f\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u001d\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00120\u00118F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0016\u0010\u0015\u001a\u0004\u0018\u00010\u00168@X\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018¨\u0006&"}, d2 = {"Lexpo/modules/kotlin/views/ViewManagerWrapperDelegate;", "", "moduleHolder", "Lexpo/modules/kotlin/ModuleHolder;", "(Lexpo/modules/kotlin/ModuleHolder;)V", "definition", "Lexpo/modules/kotlin/views/ViewManagerDefinition;", "getDefinition", "()Lexpo/modules/kotlin/views/ViewManagerDefinition;", "getModuleHolder$expo_modules_core_release", "()Lexpo/modules/kotlin/ModuleHolder;", "setModuleHolder$expo_modules_core_release", "name", "", "getName", "()Ljava/lang/String;", "props", "", "Lexpo/modules/kotlin/views/AnyViewProp;", "getProps", "()Ljava/util/Map;", "viewGroupDefinition", "Lexpo/modules/kotlin/views/ViewGroupDefinition;", "getViewGroupDefinition$expo_modules_core_release", "()Lexpo/modules/kotlin/views/ViewGroupDefinition;", "createView", "Landroid/view/View;", "context", "Landroid/content/Context;", "getExportedCustomDirectEventTypeConstants", "onDestroy", "", "view", "onViewDidUpdateProps", "updateProperties", "", "propsMap", "Lcom/facebook/react/bridge/ReadableMap;", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ViewManagerWrapperDelegate.kt */
public final class ViewManagerWrapperDelegate {
    private ModuleHolder<?> moduleHolder;

    public ViewManagerWrapperDelegate(ModuleHolder<?> moduleHolder2) {
        Intrinsics.checkNotNullParameter(moduleHolder2, "moduleHolder");
        this.moduleHolder = moduleHolder2;
    }

    public final ModuleHolder<?> getModuleHolder$expo_modules_core_release() {
        return this.moduleHolder;
    }

    public final void setModuleHolder$expo_modules_core_release(ModuleHolder<?> moduleHolder2) {
        Intrinsics.checkNotNullParameter(moduleHolder2, "<set-?>");
        this.moduleHolder = moduleHolder2;
    }

    private final ViewManagerDefinition getDefinition() {
        ViewManagerDefinition viewManagerDefinition = this.moduleHolder.getDefinition().getViewManagerDefinition();
        if (viewManagerDefinition != null) {
            return viewManagerDefinition;
        }
        throw new IllegalArgumentException("Required value was null.".toString());
    }

    public final ViewGroupDefinition getViewGroupDefinition$expo_modules_core_release() {
        return getDefinition().getViewGroupDefinition();
    }

    public final String getName() {
        return this.moduleHolder.getName();
    }

    public final Map<String, AnyViewProp> getProps() {
        return getDefinition().getProps$expo_modules_core_release();
    }

    public final View createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return getDefinition().createView(context, this.moduleHolder.getModule().getAppContext());
    }

    public final void onViewDidUpdateProps(View view) {
        CodedException codedException;
        CodedException codedException2;
        Intrinsics.checkNotNullParameter(view, "view");
        Function1<View, Unit> onViewDidUpdateProps = getDefinition().getOnViewDidUpdateProps();
        if (onViewDidUpdateProps != null) {
            try {
                onViewDidUpdateProps.invoke(view);
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                if (!ErrorViewKt.isErrorView(view)) {
                    if (th instanceof CodedException) {
                        codedException = (CodedException) th;
                    } else if (th instanceof expo.modules.core.errors.CodedException) {
                        String code = th.getCode();
                        Intrinsics.checkNotNullExpressionValue(code, "getCode(...)");
                        codedException = new CodedException(code, th.getMessage(), th.getCause());
                    } else {
                        codedException = new UnexpectedException((Throwable) th);
                    }
                    CoreLoggerKt.getLogger().error("❌ Error occurred when invoking 'onViewDidUpdateProps' on '" + view.getClass().getSimpleName() + "'", codedException);
                    getDefinition().handleException(view, codedException);
                }
            }
        }
    }

    public final List<String> updateProperties(View view, ReadableMap readableMap) {
        CodedException codedException;
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(readableMap, "propsMap");
        Map<String, AnyViewProp> props = getProps();
        List<String> arrayList = new ArrayList<>();
        ReadableMapKeySetIterator keySetIterator = readableMap.keySetIterator();
        Intrinsics.checkNotNullExpressionValue(keySetIterator, "keySetIterator(...)");
        while (keySetIterator.hasNextKey()) {
            String nextKey = keySetIterator.nextKey();
            AnyViewProp anyViewProp = props.get(nextKey);
            if (anyViewProp != null) {
                try {
                    Dynamic dynamic = readableMap.getDynamic(nextKey);
                    Intrinsics.checkNotNullExpressionValue(dynamic, "getDynamic(...)");
                    anyViewProp.set(dynamic, view, this.moduleHolder.getModule().get_appContext$expo_modules_core_release());
                } catch (Throwable th) {
                    Intrinsics.checkNotNull(nextKey);
                    arrayList.add(nextKey);
                    throw th;
                }
                Intrinsics.checkNotNull(nextKey);
                arrayList.add(nextKey);
            }
        }
        return arrayList;
    }

    public final void onDestroy(View view) {
        CodedException codedException;
        Intrinsics.checkNotNullParameter(view, "view");
        try {
            Function1<View, Unit> onViewDestroys = getDefinition().getOnViewDestroys();
            if (onViewDestroys != null) {
                onViewDestroys.invoke(view);
            }
        } catch (Throwable th) {
            if (!ErrorViewKt.isErrorView(view)) {
                if (th instanceof CodedException) {
                    codedException = (CodedException) th;
                } else if (th instanceof expo.modules.core.errors.CodedException) {
                    String code = th.getCode();
                    Intrinsics.checkNotNullExpressionValue(code, "getCode(...)");
                    codedException = new CodedException(code, th.getMessage(), th.getCause());
                } else {
                    codedException = new UnexpectedException((Throwable) th);
                }
                CoreLoggerKt.getLogger().error("❌ '" + view + "' wasn't able to destroy itself", codedException);
                getDefinition().handleException(view, codedException);
            }
        }
    }

    public final Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        String[] names;
        MapBuilder.Builder builder = MapBuilder.builder();
        CallbacksDefinition callbacksDefinition = getDefinition().getCallbacksDefinition();
        if (!(callbacksDefinition == null || (names = callbacksDefinition.getNames()) == null)) {
            for (String str : names) {
                builder.put(KModuleEventEmitterWrapperKt.normalizeEventName(str), MapBuilder.of("registrationName", str));
            }
        }
        return builder.build();
    }
}
