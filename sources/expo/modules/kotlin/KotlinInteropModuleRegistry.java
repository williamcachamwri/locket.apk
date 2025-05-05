package expo.modules.kotlin;

import androidx.tracing.Trace;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.ViewManager;
import expo.modules.adapters.react.NativeModulesProxy;
import expo.modules.core.ModuleRegistry;
import expo.modules.kotlin.defaultmodules.NativeModulesProxyModuleKt;
import expo.modules.kotlin.exception.CodedException;
import expo.modules.kotlin.exception.UnexpectedException;
import expo.modules.kotlin.functions.BaseAsyncFunctionComponent;
import expo.modules.kotlin.views.GroupViewManagerWrapper;
import expo.modules.kotlin.views.SimpleViewManagerWrapper;
import expo.modules.kotlin.views.ViewManagerDefinition;
import expo.modules.kotlin.views.ViewManagerWrapperDelegate;
import expo.modules.kotlin.views.ViewWrapperDelegateHolder;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\tJ&\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aJ\u0006\u0010\u001b\u001a\u00020\u0013Jb\u0010\u001c\u001a*\u0012\b\u0012\u00060\u0015j\u0002`\u001e\u0012\u001c\u0012\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u001dj\u0002` 0\u001f0\u001d22\b\u0002\u0010!\u001a,\u0012\u0004\u0012\u00020\u0015\u0012\u001c\u0012\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u001dj\u0002` 0\u001f\u0012\u0004\u0012\u00020\u00130\"J\u0014\u0010#\u001a\u0010\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030$0\u001fJ(\u0010%\u001a$\u0012\b\u0012\u00060\u0015j\u0002`\u001e\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u001dj\u0002`&0\u001dJ\"\u0010'\u001a\b\u0012\u0004\u0012\u00020(0\u001f2\u0014\u0010)\u001a\u0010\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030$0\u001fJ\u000e\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u0015J\u0006\u0010-\u001a\u00020\u0013J\u0006\u0010.\u001a\u00020\u0013J\u000e\u0010/\u001a\u00020\u00132\u0006\u00100\u001a\u000201J\u0014\u00102\u001a\u00020\u00132\f\u00103\u001a\b\u0012\u0004\u0012\u00020(0\u001fJ\u001e\u00104\u001a\u001a\u0012\u0004\u0012\u00020\u0015\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00010\u001d0\u001dR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000f8BX\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011¨\u00065"}, d2 = {"Lexpo/modules/kotlin/KotlinInteropModuleRegistry;", "", "modulesProvider", "Lexpo/modules/kotlin/ModulesProvider;", "legacyModuleRegistry", "Lexpo/modules/core/ModuleRegistry;", "reactContext", "Ljava/lang/ref/WeakReference;", "Lcom/facebook/react/bridge/ReactApplicationContext;", "(Lexpo/modules/kotlin/ModulesProvider;Lexpo/modules/core/ModuleRegistry;Ljava/lang/ref/WeakReference;)V", "appContext", "Lexpo/modules/kotlin/AppContext;", "getAppContext", "()Lexpo/modules/kotlin/AppContext;", "registry", "Lexpo/modules/kotlin/ModuleRegistry;", "getRegistry", "()Lexpo/modules/kotlin/ModuleRegistry;", "callMethod", "", "moduleName", "", "method", "arguments", "Lcom/facebook/react/bridge/ReadableArray;", "promise", "Lexpo/modules/kotlin/Promise;", "emitOnCreate", "exportMethods", "", "Lexpo/modules/kotlin/ModuleName;", "", "Lexpo/modules/kotlin/ModuleMethodInfo;", "exportKey", "Lkotlin/Function2;", "exportViewManagers", "Lcom/facebook/react/uimanager/ViewManager;", "exportedModulesConstants", "Lexpo/modules/kotlin/ModuleConstants;", "extractViewManagersDelegateHolders", "Lexpo/modules/kotlin/views/ViewWrapperDelegateHolder;", "viewManagers", "hasModule", "", "name", "installJSIInterop", "onDestroy", "setLegacyModulesProxy", "proxyModule", "Lexpo/modules/adapters/react/NativeModulesProxy;", "updateModuleHoldersInViewManagers", "viewWrapperHolders", "viewManagersMetadata", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: KotlinInteropModuleRegistry.kt */
public final class KotlinInteropModuleRegistry {
    private final AppContext appContext;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: KotlinInteropModuleRegistry.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        static {
            /*
                expo.modules.kotlin.views.ViewManagerType[] r0 = expo.modules.kotlin.views.ViewManagerType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                expo.modules.kotlin.views.ViewManagerType r1 = expo.modules.kotlin.views.ViewManagerType.SIMPLE     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                expo.modules.kotlin.views.ViewManagerType r1 = expo.modules.kotlin.views.ViewManagerType.GROUP     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: expo.modules.kotlin.KotlinInteropModuleRegistry.WhenMappings.<clinit>():void");
        }
    }

    public KotlinInteropModuleRegistry(ModulesProvider modulesProvider, ModuleRegistry moduleRegistry, WeakReference<ReactApplicationContext> weakReference) {
        Intrinsics.checkNotNullParameter(modulesProvider, "modulesProvider");
        Intrinsics.checkNotNullParameter(moduleRegistry, "legacyModuleRegistry");
        Intrinsics.checkNotNullParameter(weakReference, "reactContext");
        this.appContext = new AppContext(modulesProvider, moduleRegistry, weakReference);
    }

    public final AppContext getAppContext() {
        return this.appContext;
    }

    /* access modifiers changed from: private */
    public final ModuleRegistry getRegistry() {
        return this.appContext.getRegistry();
    }

    public final boolean hasModule(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        return getRegistry().hasModule(str);
    }

    public final void callMethod(String str, String str2, ReadableArray readableArray, Promise promise) {
        Intrinsics.checkNotNullParameter(str, "moduleName");
        Intrinsics.checkNotNullParameter(str2, "method");
        Intrinsics.checkNotNullParameter(readableArray, "arguments");
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        try {
            ModuleHolder<?> moduleHolder = getRegistry().getModuleHolder(str);
            if (moduleHolder != null) {
                moduleHolder.call(str2, readableArray, promise);
                return;
            }
            throw new IllegalArgumentException(("Trying to call '" + str2 + "' on the non-existing module '" + str + "'").toString());
        } catch (CodedException e) {
            promise.reject(e);
        } catch (Throwable th) {
            promise.reject(new UnexpectedException(th));
        }
    }

    public static /* synthetic */ Map exportMethods$default(KotlinInteropModuleRegistry kotlinInteropModuleRegistry, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            function2 = KotlinInteropModuleRegistry$exportMethods$1.INSTANCE;
        }
        return kotlinInteropModuleRegistry.exportMethods(function2);
    }

    public final void onDestroy() {
        this.appContext.onDestroy$expo_modules_core_release();
        CoreLoggerKt.getLogger().info("✅ KotlinInteropModuleRegistry was destroyed");
    }

    public final void installJSIInterop() {
        this.appContext.installJSIInterop();
    }

    public final void emitOnCreate() {
        this.appContext.onCreate();
    }

    public final void setLegacyModulesProxy(NativeModulesProxy nativeModulesProxy) {
        Intrinsics.checkNotNullParameter(nativeModulesProxy, "proxyModule");
        this.appContext.setLegacyModulesProxyHolder$expo_modules_core_release(new WeakReference(nativeModulesProxy));
    }

    public final Map<String, Map<String, Object>> exportedModulesConstants() {
        Trace.beginSection("[ExpoModulesCore] KotlinInteropModuleRegistry.exportedModulesConstants");
        try {
            Collection arrayList = new ArrayList();
            for (Object next : getRegistry()) {
                if (!Intrinsics.areEqual((Object) ((ModuleHolder) next).getName(), (Object) NativeModulesProxyModuleKt.NativeModulesProxyModuleName)) {
                    arrayList.add(next);
                }
            }
            Iterable<ModuleHolder> iterable = (List) arrayList;
            Map<String, Map<String, Object>> linkedHashMap = new LinkedHashMap<>(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(iterable, 10)), 16));
            for (ModuleHolder moduleHolder : iterable) {
                Pair<A, B> pair = TuplesKt.to(moduleHolder.getName(), moduleHolder.getDefinition().getConstantsProvider().invoke());
                linkedHashMap.put(pair.getFirst(), pair.getSecond());
            }
            return linkedHashMap;
        } finally {
            Trace.endSection();
        }
    }

    public final Map<String, List<Map<String, Object>>> exportMethods(Function2<? super String, ? super List<? extends Map<String, ? extends Object>>, Unit> function2) {
        Intrinsics.checkNotNullParameter(function2, "exportKey");
        Trace.beginSection("[ExpoModulesCore] KotlinInteropModuleRegistry.exportMethods");
        try {
            Iterable<ModuleHolder> access$getRegistry = getRegistry();
            Map<String, List<Map<String, Object>>> linkedHashMap = new LinkedHashMap<>(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(access$getRegistry, 10)), 16));
            for (ModuleHolder moduleHolder : access$getRegistry) {
                Map<String, BaseAsyncFunctionComponent> asyncFunctions = moduleHolder.getDefinition().getAsyncFunctions();
                Collection arrayList = new ArrayList(asyncFunctions.size());
                for (Map.Entry next : asyncFunctions.entrySet()) {
                    arrayList.add(MapsKt.mapOf(TuplesKt.to("name", (String) next.getKey()), TuplesKt.to("argumentsCount", Integer.valueOf(((BaseAsyncFunctionComponent) next.getValue()).getArgsCount$expo_modules_core_release()))));
                }
                List list = (List) arrayList;
                function2.invoke(moduleHolder.getName(), list);
                Pair pair = TuplesKt.to(moduleHolder.getName(), list);
                linkedHashMap.put(pair.getFirst(), pair.getSecond());
            }
            return linkedHashMap;
        } finally {
            Trace.endSection();
        }
    }

    public final List<ViewManager<?, ?>> exportViewManagers() {
        ViewManager viewManager;
        Trace.beginSection("[ExpoModulesCore] KotlinInteropModuleRegistry.exportViewManagers");
        try {
            Collection arrayList = new ArrayList();
            Iterator it = getRegistry().iterator();
            while (true) {
                boolean z = true;
                if (!it.hasNext()) {
                    break;
                }
                Object next = it.next();
                if (((ModuleHolder) next).getDefinition().getViewManagerDefinition() == null) {
                    z = false;
                }
                if (z) {
                    arrayList.add(next);
                }
            }
            Iterable<ModuleHolder> iterable = (List) arrayList;
            Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (ModuleHolder moduleHolder : iterable) {
                ViewManagerWrapperDelegate viewManagerWrapperDelegate = new ViewManagerWrapperDelegate(moduleHolder);
                ViewManagerDefinition viewManagerDefinition = moduleHolder.getDefinition().getViewManagerDefinition();
                Intrinsics.checkNotNull(viewManagerDefinition);
                int i = WhenMappings.$EnumSwitchMapping$0[viewManagerDefinition.getViewManagerType().ordinal()];
                if (i == 1) {
                    viewManager = new SimpleViewManagerWrapper(viewManagerWrapperDelegate);
                } else if (i == 2) {
                    viewManager = new GroupViewManagerWrapper(viewManagerWrapperDelegate);
                } else {
                    throw new NoWhenBranchMatchedException();
                }
                arrayList2.add(viewManager);
            }
            return (List) arrayList2;
        } finally {
            Trace.endSection();
        }
    }

    public final Map<String, Map<String, Object>> viewManagersMetadata() {
        List<String> list;
        Trace.beginSection("[ExpoModulesCore] KotlinInteropModuleRegistry.viewManagersMetadata");
        try {
            Collection arrayList = new ArrayList();
            for (Object next : getRegistry()) {
                if (((ModuleHolder) next).getDefinition().getViewManagerDefinition() != null) {
                    arrayList.add(next);
                }
            }
            Iterable<ModuleHolder> iterable = (List) arrayList;
            Map<String, Map<String, Object>> linkedHashMap = new LinkedHashMap<>(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(iterable, 10)), 16));
            for (ModuleHolder moduleHolder : iterable) {
                String name = moduleHolder.getName();
                ViewManagerDefinition viewManagerDefinition = moduleHolder.getDefinition().getViewManagerDefinition();
                if (viewManagerDefinition == null || (list = viewManagerDefinition.getPropsNames()) == null) {
                    list = CollectionsKt.emptyList();
                }
                Pair<A, B> pair = TuplesKt.to(name, MapsKt.mapOf(TuplesKt.to("propsNames", list)));
                linkedHashMap.put(pair.getFirst(), pair.getSecond());
            }
            return linkedHashMap;
        } finally {
            Trace.endSection();
        }
    }

    public final List<ViewWrapperDelegateHolder> extractViewManagersDelegateHolders(List<? extends ViewManager<?, ?>> list) {
        Intrinsics.checkNotNullParameter(list, "viewManagers");
        Trace.beginSection("[ExpoModulesCore] KotlinInteropModuleRegistry.extractViewManagersDelegateHolders");
        try {
            Collection arrayList = new ArrayList();
            for (Object next : list) {
                if (next instanceof ViewWrapperDelegateHolder) {
                    arrayList.add(next);
                }
            }
            return (List) arrayList;
        } finally {
            Trace.endSection();
        }
    }

    public final void updateModuleHoldersInViewManagers(List<? extends ViewWrapperDelegateHolder> list) {
        Intrinsics.checkNotNullParameter(list, "viewWrapperHolders");
        Trace.beginSection("[ExpoModulesCore] KotlinInteropModuleRegistry.updateModuleHoldersInViewManagers");
        try {
            Iterable<ViewWrapperDelegateHolder> iterable = list;
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (ViewWrapperDelegateHolder viewWrapperDelegate : iterable) {
                arrayList.add(viewWrapperDelegate.getViewWrapperDelegate());
            }
            for (ViewManagerWrapperDelegate viewManagerWrapperDelegate : (List) arrayList) {
                ModuleHolder<?> moduleHolder = getRegistry().getModuleHolder(viewManagerWrapperDelegate.getModuleHolder$expo_modules_core_release().getName());
                if (moduleHolder != null) {
                    viewManagerWrapperDelegate.setModuleHolder$expo_modules_core_release(moduleHolder);
                } else {
                    throw new IllegalArgumentException(("Cannot update the module holder for " + viewManagerWrapperDelegate.getModuleHolder$expo_modules_core_release().getName() + ".").toString());
                }
            }
            Unit unit = Unit.INSTANCE;
        } finally {
            Trace.endSection();
        }
    }
}
