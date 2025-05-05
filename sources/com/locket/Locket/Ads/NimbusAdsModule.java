package com.locket.Locket.Ads;

import com.adsbynimbus.Nimbus;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

public class NimbusAdsModule extends ReactContextBaseJavaModule {
    private final String apiKey;
    private final String publisherKey;
    private final ReactApplicationContext reactContext;

    public boolean canOverrideExistingModule() {
        return false;
    }

    public String getName() {
        return "Nimbus";
    }

    public NimbusAdsModule(ReactApplicationContext reactApplicationContext) {
        this(reactApplicationContext, "locketlabs-locketcamera", "71eea593-1522-4a57-80dd-34e1216193f1");
    }

    public NimbusAdsModule(ReactApplicationContext reactApplicationContext, String str, String str2) {
        this.reactContext = reactApplicationContext;
        this.publisherKey = str;
        this.apiKey = str2;
    }

    public void initialize() {
        Nimbus.initialize(this.reactContext, this.publisherKey, this.apiKey);
    }
}
