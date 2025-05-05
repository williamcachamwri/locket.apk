package com.facebook.react;

import com.facebook.react.bridge.ModuleHolder;
import com.facebook.react.bridge.ModuleSpec;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.module.model.ReactModuleInfoProvider;
import com.facebook.react.uimanager.ViewManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import javax.inject.Provider;

public abstract class TurboReactPackage implements ReactPackage {
    public abstract NativeModule getModule(String str, ReactApplicationContext reactApplicationContext);

    public abstract ReactModuleInfoProvider getReactModuleInfoProvider();

    public List<NativeModule> createNativeModules(ReactApplicationContext reactApplicationContext) {
        throw new UnsupportedOperationException("createNativeModules method is not supported. Use getModule() method instead.");
    }

    /* access modifiers changed from: package-private */
    public Iterable<ModuleHolder> getNativeModuleIterator(ReactApplicationContext reactApplicationContext) {
        return new TurboReactPackage$$ExternalSyntheticLambda0(this, getReactModuleInfoProvider().getReactModuleInfos().entrySet().iterator(), reactApplicationContext);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Iterator lambda$getNativeModuleIterator$0(final Iterator it, final ReactApplicationContext reactApplicationContext) {
        return new Iterator<ModuleHolder>() {
            Map.Entry<String, ReactModuleInfo> nextEntry = null;

            /* JADX WARNING: Removed duplicated region for block: B:2:0x0008  */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            private void findNext() {
                /*
                    r3 = this;
                L_0x0000:
                    java.util.Iterator r0 = r2
                    boolean r0 = r0.hasNext()
                    if (r0 == 0) goto L_0x0024
                    java.util.Iterator r0 = r2
                    java.lang.Object r0 = r0.next()
                    java.util.Map$Entry r0 = (java.util.Map.Entry) r0
                    java.lang.Object r1 = r0.getValue()
                    com.facebook.react.module.model.ReactModuleInfo r1 = (com.facebook.react.module.model.ReactModuleInfo) r1
                    boolean r2 = com.facebook.react.config.ReactFeatureFlags.useTurboModules
                    if (r2 == 0) goto L_0x0021
                    boolean r1 = r1.isTurboModule()
                    if (r1 == 0) goto L_0x0021
                    goto L_0x0000
                L_0x0021:
                    r3.nextEntry = r0
                    return
                L_0x0024:
                    r0 = 0
                    r3.nextEntry = r0
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.TurboReactPackage.AnonymousClass1.findNext():void");
            }

            public boolean hasNext() {
                if (this.nextEntry == null) {
                    findNext();
                }
                return this.nextEntry != null;
            }

            public ModuleHolder next() {
                if (this.nextEntry == null) {
                    findNext();
                }
                Map.Entry<String, ReactModuleInfo> entry = this.nextEntry;
                if (entry != null) {
                    findNext();
                    return new ModuleHolder(entry.getValue(), new ModuleHolderProvider(entry.getKey(), reactApplicationContext));
                }
                throw new NoSuchElementException("ModuleHolder not found");
            }

            public void remove() {
                throw new UnsupportedOperationException("Cannot remove native modules from the list");
            }
        };
    }

    /* access modifiers changed from: protected */
    public List<ModuleSpec> getViewManagers(ReactApplicationContext reactApplicationContext) {
        return Collections.emptyList();
    }

    public List<ViewManager> createViewManagers(ReactApplicationContext reactApplicationContext) {
        List<ModuleSpec> viewManagers = getViewManagers(reactApplicationContext);
        if (viewManagers == null || viewManagers.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (ModuleSpec provider : viewManagers) {
            arrayList.add((ViewManager) provider.getProvider().get());
        }
        return arrayList;
    }

    private class ModuleHolderProvider implements Provider<NativeModule> {
        private final String mName;
        private final ReactApplicationContext mReactContext;

        public ModuleHolderProvider(String str, ReactApplicationContext reactApplicationContext) {
            this.mName = str;
            this.mReactContext = reactApplicationContext;
        }

        public NativeModule get() {
            return TurboReactPackage.this.getModule(this.mName, this.mReactContext);
        }
    }
}
