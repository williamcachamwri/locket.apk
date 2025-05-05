package com.locket.Locket;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import com.locket.Locket.Ads.NimbusAdsModule;
import com.locket.Locket.Ads.NimbusAdsViewManager;
import com.locket.Locket.Analytics.AnalyticsModule;
import com.locket.Locket.Modules.ChangeIconModule;
import java.util.ArrayList;
import java.util.List;

public class LocketPackage implements ReactPackage {
    public List<NativeModule> createNativeModules(ReactApplicationContext reactApplicationContext) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new AnalyticsModule(reactApplicationContext));
        arrayList.add(new AppUpdateModule(reactApplicationContext));
        arrayList.add(new CountryCodeModule(reactApplicationContext));
        arrayList.add(new NotificationModule(reactApplicationContext));
        arrayList.add(new SharedStorage(reactApplicationContext));
        arrayList.add(new SocialAppsModule(reactApplicationContext));
        arrayList.add(new WidgetPinner(reactApplicationContext));
        arrayList.add(new NimbusAdsModule(reactApplicationContext));
        arrayList.add(new VideoTransformer(reactApplicationContext));
        arrayList.add(new ChangeIconModule(reactApplicationContext));
        return arrayList;
    }

    public List<ViewManager> createViewManagers(ReactApplicationContext reactApplicationContext) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new NimbusAdsViewManager());
        return arrayList;
    }
}
