package com.facebook.react.shell;

import com.facebook.fbreact.specs.NativeAccessibilityInfoSpec;
import com.facebook.fbreact.specs.NativeAnimatedModuleSpec;
import com.facebook.fbreact.specs.NativeAppStateSpec;
import com.facebook.fbreact.specs.NativeAppearanceSpec;
import com.facebook.fbreact.specs.NativeBlobModuleSpec;
import com.facebook.fbreact.specs.NativeClipboardSpec;
import com.facebook.fbreact.specs.NativeDevLoadingViewSpec;
import com.facebook.fbreact.specs.NativeDevToolsSettingsManagerSpec;
import com.facebook.fbreact.specs.NativeDialogManagerAndroidSpec;
import com.facebook.fbreact.specs.NativeFileReaderModuleSpec;
import com.facebook.fbreact.specs.NativeI18nManagerSpec;
import com.facebook.fbreact.specs.NativeImageLoaderAndroidSpec;
import com.facebook.fbreact.specs.NativeImageStoreAndroidSpec;
import com.facebook.fbreact.specs.NativeIntentAndroidSpec;
import com.facebook.fbreact.specs.NativeNetworkingAndroidSpec;
import com.facebook.fbreact.specs.NativePermissionsAndroidSpec;
import com.facebook.fbreact.specs.NativeShareModuleSpec;
import com.facebook.fbreact.specs.NativeSoundManagerSpec;
import com.facebook.fbreact.specs.NativeToastAndroidSpec;
import com.facebook.fbreact.specs.NativeVibrationSpec;
import com.facebook.fbreact.specs.NativeWebSocketModuleSpec;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.react.TurboReactPackage;
import com.facebook.react.ViewManagerOnDemandReactPackage;
import com.facebook.react.animated.NativeAnimatedModule;
import com.facebook.react.bridge.ModuleSpec;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.module.model.ReactModuleInfoProvider;
import com.facebook.react.modules.accessibilityinfo.AccessibilityInfoModule;
import com.facebook.react.modules.appearance.AppearanceModule;
import com.facebook.react.modules.appstate.AppStateModule;
import com.facebook.react.modules.blob.BlobModule;
import com.facebook.react.modules.blob.FileReaderModule;
import com.facebook.react.modules.camera.ImageStoreManager;
import com.facebook.react.modules.clipboard.ClipboardModule;
import com.facebook.react.modules.devloading.DevLoadingModule;
import com.facebook.react.modules.devtoolssettings.DevToolsSettingsManagerModule;
import com.facebook.react.modules.dialog.DialogModule;
import com.facebook.react.modules.fresco.FrescoModule;
import com.facebook.react.modules.i18nmanager.I18nManagerModule;
import com.facebook.react.modules.image.ImageLoaderModule;
import com.facebook.react.modules.intent.IntentModule;
import com.facebook.react.modules.network.NetworkingModule;
import com.facebook.react.modules.permissions.PermissionsModule;
import com.facebook.react.modules.share.ShareModule;
import com.facebook.react.modules.sound.SoundManagerModule;
import com.facebook.react.modules.statusbar.StatusBarModule;
import com.facebook.react.modules.toast.ToastModule;
import com.facebook.react.modules.vibration.VibrationModule;
import com.facebook.react.modules.websocket.WebSocketModule;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.views.drawer.ReactDrawerLayoutManager;
import com.facebook.react.views.image.ReactImageManager;
import com.facebook.react.views.modal.ReactModalHostManager;
import com.facebook.react.views.progressbar.ReactProgressBarViewManager;
import com.facebook.react.views.scroll.ReactHorizontalScrollContainerViewManager;
import com.facebook.react.views.scroll.ReactHorizontalScrollViewManager;
import com.facebook.react.views.scroll.ReactScrollViewManager;
import com.facebook.react.views.swiperefresh.SwipeRefreshLayoutManager;
import com.facebook.react.views.switchview.ReactSwitchManager;
import com.facebook.react.views.text.ReactRawTextManager;
import com.facebook.react.views.text.ReactTextViewManager;
import com.facebook.react.views.text.ReactVirtualTextViewManager;
import com.facebook.react.views.text.frescosupport.FrescoBasedReactTextInlineImageViewManager;
import com.facebook.react.views.textinput.ReactTextInputManager;
import com.facebook.react.views.unimplementedview.ReactUnimplementedViewManager;
import com.facebook.react.views.view.ReactViewManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Provider;

public class MainReactPackage extends TurboReactPackage implements ViewManagerOnDemandReactPackage {
    private MainPackageConfig mConfig;
    private Map<String, ModuleSpec> mViewManagers;

    static /* synthetic */ Map lambda$getReactModuleInfoProvider$0(Map map) {
        return map;
    }

    public MainReactPackage() {
    }

    public MainReactPackage(MainPackageConfig mainPackageConfig) {
        this.mConfig = mainPackageConfig;
    }

    public NativeModule getModule(String str, ReactApplicationContext reactApplicationContext) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -2115067288:
                if (str.equals(NativeToastAndroidSpec.NAME)) {
                    c = 0;
                    break;
                }
                break;
            case -1962922905:
                if (str.equals(NativeImageStoreAndroidSpec.NAME)) {
                    c = 1;
                    break;
                }
                break;
            case -1850625090:
                if (str.equals(NativeSoundManagerSpec.NAME)) {
                    c = 2;
                    break;
                }
                break;
            case -1654566518:
                if (str.equals(NativeDialogManagerAndroidSpec.NAME)) {
                    c = 3;
                    break;
                }
                break;
            case -1344126773:
                if (str.equals(NativeFileReaderModuleSpec.NAME)) {
                    c = 4;
                    break;
                }
                break;
            case -1062061717:
                if (str.equals(NativePermissionsAndroidSpec.NAME)) {
                    c = 5;
                    break;
                }
                break;
            case -657277650:
                if (str.equals(NativeImageLoaderAndroidSpec.NAME)) {
                    c = 6;
                    break;
                }
                break;
            case -570370161:
                if (str.equals(NativeI18nManagerSpec.NAME)) {
                    c = 7;
                    break;
                }
                break;
            case -504784764:
                if (str.equals(NativeAppearanceSpec.NAME)) {
                    c = 8;
                    break;
                }
                break;
            case -457866500:
                if (str.equals(NativeAccessibilityInfoSpec.NAME)) {
                    c = 9;
                    break;
                }
                break;
            case -382654004:
                if (str.equals("StatusBarManager")) {
                    c = 10;
                    break;
                }
                break;
            case -254310125:
                if (str.equals(NativeWebSocketModuleSpec.NAME)) {
                    c = 11;
                    break;
                }
                break;
            case -99249460:
                if (str.equals(NativeDevLoadingViewSpec.NAME)) {
                    c = 12;
                    break;
                }
                break;
            case 163245714:
                if (str.equals(FrescoModule.NAME)) {
                    c = 13;
                    break;
                }
                break;
            case 403570038:
                if (str.equals(NativeClipboardSpec.NAME)) {
                    c = 14;
                    break;
                }
                break;
            case 443276868:
                if (str.equals(NativeDevToolsSettingsManagerSpec.NAME)) {
                    c = 15;
                    break;
                }
                break;
            case 563961875:
                if (str.equals(NativeIntentAndroidSpec.NAME)) {
                    c = 16;
                    break;
                }
                break;
            case 1221389072:
                if (str.equals(NativeAppStateSpec.NAME)) {
                    c = 17;
                    break;
                }
                break;
            case 1515242260:
                if (str.equals(NativeNetworkingAndroidSpec.NAME)) {
                    c = 18;
                    break;
                }
                break;
            case 1547941001:
                if (str.equals(NativeBlobModuleSpec.NAME)) {
                    c = 19;
                    break;
                }
                break;
            case 1555425035:
                if (str.equals(NativeShareModuleSpec.NAME)) {
                    c = 20;
                    break;
                }
                break;
            case 1721274886:
                if (str.equals(NativeAnimatedModuleSpec.NAME)) {
                    c = 21;
                    break;
                }
                break;
            case 1922110066:
                if (str.equals(NativeVibrationSpec.NAME)) {
                    c = 22;
                    break;
                }
                break;
        }
        ImagePipelineConfig imagePipelineConfig = null;
        switch (c) {
            case 0:
                return new ToastModule(reactApplicationContext);
            case 1:
                return new ImageStoreManager(reactApplicationContext);
            case 2:
                return new SoundManagerModule(reactApplicationContext);
            case 3:
                return new DialogModule(reactApplicationContext);
            case 4:
                return new FileReaderModule(reactApplicationContext);
            case 5:
                return new PermissionsModule(reactApplicationContext);
            case 6:
                return new ImageLoaderModule(reactApplicationContext);
            case 7:
                return new I18nManagerModule(reactApplicationContext);
            case 8:
                return new AppearanceModule(reactApplicationContext);
            case 9:
                return new AccessibilityInfoModule(reactApplicationContext);
            case 10:
                return new StatusBarModule(reactApplicationContext);
            case 11:
                return new WebSocketModule(reactApplicationContext);
            case 12:
                return new DevLoadingModule(reactApplicationContext);
            case 13:
                MainPackageConfig mainPackageConfig = this.mConfig;
                if (mainPackageConfig != null) {
                    imagePipelineConfig = mainPackageConfig.getFrescoConfig();
                }
                return new FrescoModule(reactApplicationContext, true, imagePipelineConfig);
            case 14:
                return new ClipboardModule(reactApplicationContext);
            case 15:
                return new DevToolsSettingsManagerModule(reactApplicationContext);
            case 16:
                return new IntentModule(reactApplicationContext);
            case 17:
                return new AppStateModule(reactApplicationContext);
            case 18:
                return new NetworkingModule(reactApplicationContext);
            case 19:
                return new BlobModule(reactApplicationContext);
            case 20:
                return new ShareModule(reactApplicationContext);
            case 21:
                return new NativeAnimatedModule(reactApplicationContext);
            case 22:
                return new VibrationModule(reactApplicationContext);
            default:
                return null;
        }
    }

    public List<ViewManager> createViewManagers(ReactApplicationContext reactApplicationContext) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new ReactDrawerLayoutManager());
        arrayList.add(new ReactHorizontalScrollViewManager());
        arrayList.add(new ReactHorizontalScrollContainerViewManager());
        arrayList.add(new ReactProgressBarViewManager());
        arrayList.add(new ReactScrollViewManager());
        arrayList.add(new ReactSwitchManager());
        arrayList.add(new SwipeRefreshLayoutManager());
        arrayList.add(new FrescoBasedReactTextInlineImageViewManager());
        arrayList.add(new ReactImageManager());
        arrayList.add(new ReactModalHostManager());
        arrayList.add(new ReactRawTextManager());
        arrayList.add(new ReactTextInputManager());
        arrayList.add(new ReactTextViewManager());
        arrayList.add(new ReactViewManager());
        arrayList.add(new ReactVirtualTextViewManager());
        arrayList.add(new ReactUnimplementedViewManager());
        return arrayList;
    }

    private static void appendMap(Map<String, ModuleSpec> map, String str, Provider<? extends NativeModule> provider) {
        map.put(str, ModuleSpec.viewManagerSpec(provider));
    }

    public Map<String, ModuleSpec> getViewManagersMap() {
        if (this.mViewManagers == null) {
            HashMap hashMap = new HashMap();
            appendMap(hashMap, ReactDrawerLayoutManager.REACT_CLASS, new MainReactPackage$$ExternalSyntheticLambda0());
            appendMap(hashMap, ReactHorizontalScrollViewManager.REACT_CLASS, new MainReactPackage$$ExternalSyntheticLambda14());
            appendMap(hashMap, ReactHorizontalScrollContainerViewManager.REACT_CLASS, new MainReactPackage$$ExternalSyntheticLambda15());
            appendMap(hashMap, ReactProgressBarViewManager.REACT_CLASS, new MainReactPackage$$ExternalSyntheticLambda16());
            appendMap(hashMap, ReactScrollViewManager.REACT_CLASS, new MainReactPackage$$ExternalSyntheticLambda1());
            appendMap(hashMap, ReactSwitchManager.REACT_CLASS, new MainReactPackage$$ExternalSyntheticLambda2());
            appendMap(hashMap, SwipeRefreshLayoutManager.REACT_CLASS, new MainReactPackage$$ExternalSyntheticLambda3());
            appendMap(hashMap, FrescoBasedReactTextInlineImageViewManager.REACT_CLASS, new MainReactPackage$$ExternalSyntheticLambda4());
            appendMap(hashMap, ReactImageManager.REACT_CLASS, new MainReactPackage$$ExternalSyntheticLambda5());
            appendMap(hashMap, ReactModalHostManager.REACT_CLASS, new MainReactPackage$$ExternalSyntheticLambda6());
            appendMap(hashMap, ReactRawTextManager.REACT_CLASS, new MainReactPackage$$ExternalSyntheticLambda8());
            appendMap(hashMap, ReactTextInputManager.REACT_CLASS, new MainReactPackage$$ExternalSyntheticLambda9());
            appendMap(hashMap, ReactTextViewManager.REACT_CLASS, new MainReactPackage$$ExternalSyntheticLambda10());
            appendMap(hashMap, "RCTView", new MainReactPackage$$ExternalSyntheticLambda11());
            appendMap(hashMap, ReactVirtualTextViewManager.REACT_CLASS, new MainReactPackage$$ExternalSyntheticLambda12());
            appendMap(hashMap, ReactUnimplementedViewManager.REACT_CLASS, new MainReactPackage$$ExternalSyntheticLambda13());
            this.mViewManagers = hashMap;
        }
        return this.mViewManagers;
    }

    public List<ModuleSpec> getViewManagers(ReactApplicationContext reactApplicationContext) {
        return new ArrayList(getViewManagersMap().values());
    }

    public Collection<String> getViewManagerNames(ReactApplicationContext reactApplicationContext) {
        return getViewManagersMap().keySet();
    }

    public ViewManager createViewManager(ReactApplicationContext reactApplicationContext, String str) {
        ModuleSpec moduleSpec = getViewManagersMap().get(str);
        if (moduleSpec != null) {
            return (ViewManager) moduleSpec.getProvider().get();
        }
        return null;
    }

    public ReactModuleInfoProvider getReactModuleInfoProvider() {
        try {
            return (ReactModuleInfoProvider) Class.forName("com.facebook.react.shell.MainReactPackage$$ReactModuleInfoProvider").newInstance();
        } catch (ClassNotFoundException unused) {
            Class[] clsArr = {AccessibilityInfoModule.class, AppearanceModule.class, AppStateModule.class, BlobModule.class, DevLoadingModule.class, FileReaderModule.class, ClipboardModule.class, DialogModule.class, FrescoModule.class, I18nManagerModule.class, ImageLoaderModule.class, ImageStoreManager.class, IntentModule.class, NativeAnimatedModule.class, NetworkingModule.class, PermissionsModule.class, DevToolsSettingsManagerModule.class, ShareModule.class, StatusBarModule.class, SoundManagerModule.class, ToastModule.class, VibrationModule.class, WebSocketModule.class};
            HashMap hashMap = new HashMap();
            for (int i = 0; i < 23; i++) {
                Class cls = clsArr[i];
                ReactModule reactModule = (ReactModule) cls.getAnnotation(ReactModule.class);
                if (reactModule != null) {
                    hashMap.put(reactModule.name(), new ReactModuleInfo(reactModule.name(), cls.getName(), reactModule.canOverrideExistingModule(), reactModule.needsEagerInit(), reactModule.isCxxModule(), TurboModule.class.isAssignableFrom(cls)));
                }
            }
            return new MainReactPackage$$ExternalSyntheticLambda7(hashMap);
        } catch (InstantiationException e) {
            throw new RuntimeException("No ReactModuleInfoProvider for CoreModulesPackage$$ReactModuleInfoProvider", e);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("No ReactModuleInfoProvider for CoreModulesPackage$$ReactModuleInfoProvider", e2);
        }
    }
}
