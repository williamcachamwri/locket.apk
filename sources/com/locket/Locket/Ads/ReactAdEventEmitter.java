package com.locket.Locket.Ads;

import android.util.Log;
import com.adsbynimbus.NimbusError;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.locket.Locket.CdnGlideModelLoader$$ExternalSyntheticBackport0;
import java.util.AbstractMap;
import java.util.Map;

public final class ReactAdEventEmitter {
    public static final Map<String, Object> EXPORTED_EVENTS = CdnGlideModelLoader$$ExternalSyntheticBackport0.m(new Map.Entry[]{new AbstractMap.SimpleEntry("onAdRendered", MapBuilder.of("registrationName", "onAdRendered")), new AbstractMap.SimpleEntry("onAdReady", MapBuilder.of("registrationName", "onAdReady")), new AbstractMap.SimpleEntry("onAdError", MapBuilder.of("registrationName", "onAdError")), new AbstractMap.SimpleEntry("onAdImpression", MapBuilder.of("registrationName", "onAdImpression")), new AbstractMap.SimpleEntry("onAdClick", MapBuilder.of("registrationName", "onAdClick"))});
    private static final String TAG = "ReactAdEventEmitter";
    private final ReactContext reactContext;

    public ReactAdEventEmitter(ReactContext reactContext2) {
        this.reactContext = reactContext2;
    }

    public void emitRendered(int i) {
        send(i, "onAdRendered", payload("Ad is rendered."));
    }

    public void emitReady(int i) {
        send(i, "onAdReady", payload("Ad is rendered with non‑zero size."));
    }

    public void emitImpression(int i) {
        send(i, "onAdImpression", payload("Ad impression recorded"));
    }

    public void emitClick(int i) {
        send(i, "onAdClick", payload("Ad click recorded"));
    }

    public void emitError(int i, String str, NimbusError.ErrorType errorType) {
        WritableMap payload = payload(str);
        payload.putString("errorType", errorType.toString());
        send(i, "onAdError", payload);
    }

    private void send(int i, String str, WritableMap writableMap) {
        try {
            ((RCTEventEmitter) this.reactContext.getJSModule(RCTEventEmitter.class)).receiveEvent(i, str, writableMap);
        } catch (Exception e) {
            Log.d(TAG, "Failed to send event " + str, e);
        }
    }

    private static WritableMap payload(String str) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("message", str);
        return createMap;
    }
}
