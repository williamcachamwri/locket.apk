package expo.modules.adapters.react;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import expo.modules.core.ModuleRegistry;
import expo.modules.core.interfaces.Consumer;
import expo.modules.core.interfaces.InternalModule;
import expo.modules.core.interfaces.Package;
import expo.modules.core.interfaces.SingletonModule;
import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.CoreLoggerKt;
import expo.modules.kotlin.KotlinInteropModuleRegistry;
import expo.modules.kotlin.ModulesProvider;
import expo.modules.kotlin.views.ViewWrapperDelegateHolder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ModuleRegistryAdapter implements ReactPackage {
    private FabricComponentsRegistry mFabricComponentsRegistry = null;
    protected ReactModuleRegistryProvider mModuleRegistryProvider;
    protected ModulesProvider mModulesProvider;
    private NativeModulesProxy mModulesProxy;
    protected ReactAdapterPackage mReactAdapterPackage = new ReactAdapterPackage();
    private List<ViewWrapperDelegateHolder> mWrapperDelegateHolders = null;

    private void setModulesProxy(NativeModulesProxy nativeModulesProxy) {
        this.mModulesProxy = nativeModulesProxy;
        if (nativeModulesProxy != null) {
            nativeModulesProxy.getKotlinInteropModuleRegistry().setLegacyModulesProxy(this.mModulesProxy);
        }
    }

    public ModuleRegistryAdapter(List<Package> list) {
        this.mModuleRegistryProvider = new ReactModuleRegistryProvider(list, (List<SingletonModule>) null);
    }

    public ModuleRegistryAdapter(ReactModuleRegistryProvider reactModuleRegistryProvider) {
        this.mModuleRegistryProvider = reactModuleRegistryProvider;
    }

    public ModuleRegistryAdapter(ReactModuleRegistryProvider reactModuleRegistryProvider, ModulesProvider modulesProvider) {
        this.mModuleRegistryProvider = reactModuleRegistryProvider;
        this.mModulesProvider = modulesProvider;
    }

    public List<NativeModule> createNativeModules(ReactApplicationContext reactApplicationContext) {
        NativeModulesProxy orCreateNativeModulesProxy = getOrCreateNativeModulesProxy(reactApplicationContext, (ModuleRegistry) null);
        ModuleRegistry moduleRegistry = orCreateNativeModulesProxy.getModuleRegistry();
        for (InternalModule registerInternalModule : this.mReactAdapterPackage.createInternalModules(reactApplicationContext)) {
            moduleRegistry.registerInternalModule(registerInternalModule);
        }
        List<NativeModule> nativeModulesFromModuleRegistry = getNativeModulesFromModuleRegistry(reactApplicationContext, moduleRegistry, (Consumer<AppContext>) null);
        if (this.mWrapperDelegateHolders != null) {
            orCreateNativeModulesProxy.getKotlinInteropModuleRegistry().updateModuleHoldersInViewManagers(this.mWrapperDelegateHolders);
        }
        return nativeModulesFromModuleRegistry;
    }

    /* access modifiers changed from: protected */
    public List<NativeModule> getNativeModulesFromModuleRegistry(ReactApplicationContext reactApplicationContext, ModuleRegistry moduleRegistry, Consumer<AppContext> consumer) {
        ArrayList arrayList = new ArrayList(2);
        NativeModulesProxy orCreateNativeModulesProxy = getOrCreateNativeModulesProxy(reactApplicationContext, moduleRegistry);
        if (consumer != null) {
            consumer.apply(orCreateNativeModulesProxy.getKotlinInteropModuleRegistry().getAppContext());
        }
        arrayList.add(orCreateNativeModulesProxy);
        arrayList.add(new ModuleRegistryReadyNotifier(moduleRegistry));
        for (ReactPackage createNativeModules : ((ReactPackagesProvider) moduleRegistry.getModule(ReactPackagesProvider.class)).getReactPackages()) {
            arrayList.addAll(createNativeModules.createNativeModules(reactApplicationContext));
        }
        return arrayList;
    }

    public List<ViewManager> createViewManagers(ReactApplicationContext reactApplicationContext) {
        ArrayList arrayList = new ArrayList(this.mModuleRegistryProvider.getReactViewManagers(reactApplicationContext));
        KotlinInteropModuleRegistry kotlinInteropModuleRegistry = ((NativeModulesProxy) Objects.requireNonNull(getOrCreateNativeModulesProxy(reactApplicationContext, (ModuleRegistry) null))).getKotlinInteropModuleRegistry();
        List<ViewManager<?, ?>> exportViewManagers = kotlinInteropModuleRegistry.exportViewManagers();
        this.mWrapperDelegateHolders = kotlinInteropModuleRegistry.extractViewManagersDelegateHolders(exportViewManagers);
        arrayList.addAll(exportViewManagers);
        return arrayList;
    }

    private synchronized NativeModulesProxy getOrCreateNativeModulesProxy(ReactApplicationContext reactApplicationContext, ModuleRegistry moduleRegistry) {
        ModuleRegistry moduleRegistry2;
        NativeModulesProxy nativeModulesProxy = this.mModulesProxy;
        if (!(nativeModulesProxy == null || nativeModulesProxy.getReactContext() == reactApplicationContext)) {
            setModulesProxy((NativeModulesProxy) null);
        }
        if (this.mModulesProxy == null) {
            if (moduleRegistry != null) {
                moduleRegistry2 = moduleRegistry;
            } else {
                moduleRegistry2 = this.mModuleRegistryProvider.get(reactApplicationContext);
            }
            if (this.mModulesProvider != null) {
                setModulesProxy(new NativeModulesProxy(reactApplicationContext, moduleRegistry2, this.mModulesProvider));
            } else {
                setModulesProxy(new NativeModulesProxy(reactApplicationContext, moduleRegistry2));
            }
        }
        if (!(moduleRegistry == null || moduleRegistry == this.mModulesProxy.getModuleRegistry())) {
            CoreLoggerKt.getLogger().error("❌ NativeModuleProxy was configured with a different instance of the modules registry.", (Throwable) null);
        }
        return this.mModulesProxy;
    }
}
