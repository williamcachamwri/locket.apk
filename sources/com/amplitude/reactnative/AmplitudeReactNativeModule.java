package com.amplitude.reactnative;

import com.amplitude.api.Amplitude;
import com.amplitude.api.AmplitudeClient;
import com.amplitude.api.AmplitudeLogCallback;
import com.amplitude.api.AmplitudeServerZone;
import com.amplitude.api.Constants;
import com.amplitude.api.IngestionMetadata;
import com.amplitude.api.Plan;
import com.amplitude.api.Revenue;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.google.firebase.analytics.FirebaseAnalytics;
import io.sentry.protocol.ViewHierarchyNode;
import org.json.JSONException;
import org.json.JSONObject;

@ReactModule(name = "AmplitudeReactNative")
public class AmplitudeReactNativeModule extends ReactContextBaseJavaModule {
    public static final String NAME = "AmplitudeReactNative";
    /* access modifiers changed from: private */
    public final ReactApplicationContext reactContext;

    @ReactMethod
    public void addListener(String str) {
    }

    public String getName() {
        return NAME;
    }

    @ReactMethod
    public void removeListeners(Integer num) {
    }

    public AmplitudeReactNativeModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.reactContext = reactApplicationContext;
    }

    @ReactMethod
    public void initialize(String str, String str2, Promise promise) {
        Amplitude.getInstance(str).initialize(this.reactContext, str2);
        promise.resolve(true);
    }

    @ReactMethod
    public void logEvent(String str, String str2, Promise promise) {
        AmplitudeClient instance = Amplitude.getInstance(str);
        synchronized (instance) {
            instance.logEvent(str2);
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void logEventWithProperties(String str, String str2, ReadableMap readableMap, Promise promise) throws JSONException {
        JSONObject convertMapToJson = ReactNativeHelper.convertMapToJson(readableMap);
        AmplitudeClient instance = Amplitude.getInstance(str);
        synchronized (instance) {
            instance.logEvent(str2, convertMapToJson);
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void enableCoppaControl(String str, Promise promise) {
        AmplitudeClient instance = Amplitude.getInstance(str);
        synchronized (instance) {
            instance.enableCoppaControl();
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void disableCoppaControl(String str, Promise promise) {
        AmplitudeClient instance = Amplitude.getInstance(str);
        synchronized (instance) {
            instance.disableCoppaControl();
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void regenerateDeviceId(String str, Promise promise) {
        AmplitudeClient instance = Amplitude.getInstance(str);
        synchronized (instance) {
            instance.regenerateDeviceId();
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void setDeviceId(String str, String str2, Promise promise) {
        AmplitudeClient instance = Amplitude.getInstance(str);
        synchronized (instance) {
            instance.setDeviceId(str2);
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void getDeviceId(String str, Promise promise) {
        AmplitudeClient instance = Amplitude.getInstance(str);
        synchronized (instance) {
            promise.resolve(instance.getDeviceId());
        }
    }

    @ReactMethod
    public void setAdvertisingIdForDeviceId(String str, Promise promise) {
        AmplitudeClient instance = Amplitude.getInstance(str);
        synchronized (instance) {
            instance.useAdvertisingIdForDeviceId();
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void setAppSetIdForDeviceId(String str, Promise promise) {
        AmplitudeClient instance = Amplitude.getInstance(str);
        synchronized (instance) {
            instance.useAppSetIdForDeviceId();
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void setOptOut(String str, boolean z, Promise promise) {
        AmplitudeClient instance = Amplitude.getInstance(str);
        synchronized (instance) {
            instance.setOptOut(z);
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void setLibraryName(String str, String str2, Promise promise) {
        AmplitudeClient instance = Amplitude.getInstance(str);
        synchronized (instance) {
            instance.setLibraryName(str2);
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void setLibraryVersion(String str, String str2, Promise promise) {
        AmplitudeClient instance = Amplitude.getInstance(str);
        synchronized (instance) {
            instance.setLibraryVersion(str2);
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void trackingSessionEvents(String str, boolean z, Promise promise) {
        AmplitudeClient instance = Amplitude.getInstance(str);
        synchronized (instance) {
            instance.trackSessionEvents(z);
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void setUseDynamicConfig(String str, boolean z, Promise promise) {
        AmplitudeClient instance = Amplitude.getInstance(str);
        synchronized (instance) {
            instance.setUseDynamicConfig(z);
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void setUserId(String str, String str2, Promise promise) {
        AmplitudeClient instance = Amplitude.getInstance(str);
        synchronized (instance) {
            instance.setUserId(str2);
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void setServerUrl(String str, String str2, Promise promise) {
        AmplitudeClient instance = Amplitude.getInstance(str);
        synchronized (instance) {
            instance.setServerUrl(str2);
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void logRevenueV2(String str, ReadableMap readableMap, Promise promise) throws JSONException {
        JSONObject convertMapToJson = ReactNativeHelper.convertMapToJson(readableMap);
        AmplitudeClient instance = Amplitude.getInstance(str);
        synchronized (instance) {
            instance.logRevenueV2(createRevenue(convertMapToJson));
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void identify(String str, ReadableMap readableMap, Promise promise) throws JSONException {
        JSONObject convertMapToJson = ReactNativeHelper.convertMapToJson(readableMap);
        AmplitudeClient instance = Amplitude.getInstance(str);
        synchronized (instance) {
            instance.identify(createIdentify(convertMapToJson));
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void groupIdentify(String str, String str2, String str3, ReadableMap readableMap, Promise promise) throws JSONException {
        JSONObject convertMapToJson = ReactNativeHelper.convertMapToJson(readableMap);
        AmplitudeClient instance = Amplitude.getInstance(str);
        synchronized (instance) {
            instance.groupIdentify(str2, str3, createIdentify(convertMapToJson));
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void setUserProperties(String str, ReadableMap readableMap, Promise promise) throws JSONException {
        JSONObject convertMapToJson = ReactNativeHelper.convertMapToJson(readableMap);
        AmplitudeClient instance = Amplitude.getInstance(str);
        synchronized (instance) {
            instance.setUserProperties(convertMapToJson);
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void clearUserProperties(String str, Promise promise) {
        AmplitudeClient instance = Amplitude.getInstance(str);
        synchronized (instance) {
            instance.clearUserProperties();
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void setGroup(String str, String str2, String str3, Promise promise) {
        AmplitudeClient instance = Amplitude.getInstance(str);
        synchronized (instance) {
            instance.setGroup(str2, str3);
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void uploadEvents(String str, Promise promise) {
        AmplitudeClient instance = Amplitude.getInstance(str);
        synchronized (instance) {
            instance.uploadEvents();
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void getSessionId(String str, Promise promise) {
        AmplitudeClient instance = Amplitude.getInstance(str);
        synchronized (instance) {
            promise.resolve(Double.valueOf((double) instance.getSessionId()));
        }
    }

    @ReactMethod
    public void setMinTimeBetweenSessionsMillis(String str, double d, Promise promise) {
        AmplitudeClient instance = Amplitude.getInstance(str);
        synchronized (instance) {
            instance.setMinTimeBetweenSessionsMillis((long) d);
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void setServerZone(String str, String str2, boolean z, Promise promise) {
        AmplitudeClient instance = Amplitude.getInstance(str);
        synchronized (instance) {
            instance.setServerZone(str2.equals("EU") ? AmplitudeServerZone.EU : AmplitudeServerZone.US, z);
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void setEventUploadMaxBatchSize(String str, int i, Promise promise) {
        AmplitudeClient instance = Amplitude.getInstance(str);
        synchronized (instance) {
            instance.setEventUploadMaxBatchSize(i);
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void setEventUploadPeriodMillis(String str, int i, Promise promise) {
        AmplitudeClient instance = Amplitude.getInstance(str);
        synchronized (instance) {
            instance.setEventUploadPeriodMillis(i);
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void setEventUploadThreshold(String str, int i, Promise promise) {
        AmplitudeClient instance = Amplitude.getInstance(str);
        synchronized (instance) {
            instance.setEventUploadThreshold(i);
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void setPlan(String str, ReadableMap readableMap, Promise promise) {
        Plan plan = new Plan();
        if (readableMap.hasKey(Constants.AMP_PLAN_BRANCH)) {
            plan.setBranch(readableMap.getString(Constants.AMP_PLAN_BRANCH));
        }
        if (readableMap.hasKey("source")) {
            plan.setSource(readableMap.getString("source"));
        }
        if (readableMap.hasKey("version")) {
            plan.setVersion(readableMap.getString("version"));
        }
        if (readableMap.hasKey(Constants.AMP_PLAN_VERSION_ID)) {
            plan.setVersionId(readableMap.getString(Constants.AMP_PLAN_VERSION_ID));
        }
        AmplitudeClient instance = Amplitude.getInstance(str);
        synchronized (instance) {
            instance.setPlan(plan);
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void setIngestionMetadata(String str, ReadableMap readableMap, Promise promise) {
        IngestionMetadata ingestionMetadata = new IngestionMetadata();
        if (readableMap.hasKey("sourceName")) {
            ingestionMetadata.setSourceName(readableMap.getString("sourceName"));
        }
        if (readableMap.hasKey("sourceVersion")) {
            ingestionMetadata.setSourceVersion(readableMap.getString("sourceVersion"));
        }
        AmplitudeClient instance = Amplitude.getInstance(str);
        synchronized (instance) {
            instance.setIngestionMetadata(ingestionMetadata);
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void enableLogging(String str, Boolean bool, Promise promise) {
        AmplitudeClient instance = Amplitude.getInstance(str);
        synchronized (instance) {
            instance.enableLogging(bool.booleanValue());
            if (bool.booleanValue()) {
                instance.setLogCallback(new AmplitudeLogCallback() {
                    public void onError(String str, String str2) {
                        WritableNativeMap writableNativeMap = new WritableNativeMap();
                        writableNativeMap.putString(ViewHierarchyNode.JsonKeys.TAG, str);
                        writableNativeMap.putString("message", str2);
                        ((DeviceEventManagerModule.RCTDeviceEventEmitter) AmplitudeReactNativeModule.this.reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("AmplitudeLogError", writableNativeMap);
                    }
                });
            } else {
                instance.setLogCallback((AmplitudeLogCallback) null);
            }
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void setLogLevel(String str, Integer num, Promise promise) {
        AmplitudeClient instance = Amplitude.getInstance(str);
        synchronized (instance) {
            instance.setLogLevel(num.intValue());
            promise.resolve(true);
        }
    }

    private Revenue createRevenue(JSONObject jSONObject) {
        Revenue revenue = new Revenue();
        try {
            if (jSONObject.has("productId")) {
                revenue.setProductId(jSONObject.getString("productId"));
            }
            if (jSONObject.has(FirebaseAnalytics.Param.PRICE)) {
                revenue.setPrice(jSONObject.getDouble(FirebaseAnalytics.Param.PRICE));
            }
            if (jSONObject.has("quantity")) {
                revenue.setQuantity(jSONObject.getInt("quantity"));
            } else {
                revenue.setQuantity(1);
            }
            if (jSONObject.has("revenueType")) {
                revenue.setRevenueType(jSONObject.getString("revenueType"));
            }
            if (jSONObject.has("receipt") && jSONObject.has("receiptSignature")) {
                revenue.setReceipt(jSONObject.getString("receipt"), jSONObject.getString("receiptSignature"));
            }
            if (jSONObject.has("eventProperties")) {
                revenue.setEventProperties(jSONObject.getJSONObject("eventProperties"));
            }
        } catch (JSONException unused) {
        }
        return revenue;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:100:0x01c9, code lost:
        if ((r3.get(r5) instanceof java.lang.Integer) == false) goto L_0x01d3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x01cb, code lost:
        r0.preInsert(r5, r3.getInt(r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x01d9, code lost:
        if ((r3.get(r5) instanceof java.lang.Long) == false) goto L_0x01e3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x01db, code lost:
        r0.preInsert(r5, r3.getLong(r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x01e9, code lost:
        if ((r3.get(r5) instanceof java.lang.String) == false) goto L_0x01f3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x01eb, code lost:
        r0.preInsert(r5, r3.getString(r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x01f9, code lost:
        if ((r3.get(r5) instanceof org.json.JSONObject) == false) goto L_0x0203;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x01fb, code lost:
        r0.preInsert(r5, r3.getJSONObject(r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x0209, code lost:
        if ((r3.get(r5) instanceof org.json.JSONArray) == false) goto L_0x0213;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x020b, code lost:
        r0.preInsert(r5, r3.getJSONArray(r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x0219, code lost:
        if ((r3.get(r5) instanceof java.lang.Boolean) == false) goto L_0x0222;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x021b, code lost:
        r0.preInsert(r5, r3.getBoolean(r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x0228, code lost:
        if ((r3.get(r5) instanceof java.lang.Double) == false) goto L_0x0232;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x022a, code lost:
        r0.postInsert(r5, r3.getDouble(r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x0238, code lost:
        if ((r3.get(r5) instanceof java.lang.Integer) == false) goto L_0x0242;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x023a, code lost:
        r0.postInsert(r5, r3.getInt(r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x0248, code lost:
        if ((r3.get(r5) instanceof java.lang.Long) == false) goto L_0x0252;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x024a, code lost:
        r0.postInsert(r5, r3.getLong(r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x0258, code lost:
        if ((r3.get(r5) instanceof java.lang.String) == false) goto L_0x0262;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:0x025a, code lost:
        r0.postInsert(r5, r3.getString(r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x0268, code lost:
        if ((r3.get(r5) instanceof org.json.JSONObject) == false) goto L_0x0272;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:131:0x026a, code lost:
        r0.postInsert(r5, r3.getJSONObject(r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x0278, code lost:
        if ((r3.get(r5) instanceof org.json.JSONArray) == false) goto L_0x0282;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:134:0x027a, code lost:
        r0.postInsert(r5, r3.getJSONArray(r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:136:0x0288, code lost:
        if ((r3.get(r5) instanceof java.lang.Boolean) == false) goto L_0x0291;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x028a, code lost:
        r0.postInsert(r5, r3.getBoolean(r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x0297, code lost:
        if ((r3.get(r5) instanceof java.lang.Double) == false) goto L_0x02a1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:0x0299, code lost:
        r0.set(r5, r3.getDouble(r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x02a7, code lost:
        if ((r3.get(r5) instanceof java.lang.Integer) == false) goto L_0x02b1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:143:0x02a9, code lost:
        r0.set(r5, r3.getInt(r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:145:0x02b7, code lost:
        if ((r3.get(r5) instanceof java.lang.Long) == false) goto L_0x02c1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x02b9, code lost:
        r0.set(r5, r3.getLong(r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:148:0x02c7, code lost:
        if ((r3.get(r5) instanceof java.lang.String) == false) goto L_0x02d1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:149:0x02c9, code lost:
        r0.set(r5, r3.getString(r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:151:0x02d7, code lost:
        if ((r3.get(r5) instanceof org.json.JSONObject) == false) goto L_0x02e1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:152:0x02d9, code lost:
        r0.set(r5, r3.getJSONObject(r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x02e7, code lost:
        if ((r3.get(r5) instanceof org.json.JSONArray) == false) goto L_0x02f1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x02e9, code lost:
        r0.set(r5, r3.getJSONArray(r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:157:0x02f7, code lost:
        if ((r3.get(r5) instanceof java.lang.Boolean) == false) goto L_0x0300;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x02f9, code lost:
        r0.set(r5, r3.getBoolean(r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:160:0x0306, code lost:
        if ((r3.get(r5) instanceof java.lang.Double) == false) goto L_0x0310;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:0x0308, code lost:
        r0.setOnce(r5, r3.getDouble(r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:163:0x0316, code lost:
        if ((r3.get(r5) instanceof java.lang.Integer) == false) goto L_0x0320;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:164:0x0318, code lost:
        r0.setOnce(r5, r3.getInt(r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:166:0x0326, code lost:
        if ((r3.get(r5) instanceof java.lang.Long) == false) goto L_0x0330;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:167:0x0328, code lost:
        r0.setOnce(r5, r3.getLong(r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:169:0x0336, code lost:
        if ((r3.get(r5) instanceof java.lang.String) == false) goto L_0x0340;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:170:0x0338, code lost:
        r0.setOnce(r5, r3.getString(r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:172:0x0346, code lost:
        if ((r3.get(r5) instanceof org.json.JSONObject) == false) goto L_0x0350;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:173:0x0348, code lost:
        r0.setOnce(r5, r3.getJSONObject(r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:175:0x0356, code lost:
        if ((r3.get(r5) instanceof org.json.JSONArray) == false) goto L_0x0360;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:176:0x0358, code lost:
        r0.setOnce(r5, r3.getJSONArray(r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:178:0x0366, code lost:
        if ((r3.get(r5) instanceof java.lang.Boolean) == false) goto L_0x036f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:179:0x0368, code lost:
        r0.setOnce(r5, r3.getBoolean(r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:180:0x036f, code lost:
        r0.unset(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00db, code lost:
        if ((r3.get(r5) instanceof java.lang.Double) == false) goto L_0x00e5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00dd, code lost:
        r0.append(r5, r3.getDouble(r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00eb, code lost:
        if ((r3.get(r5) instanceof java.lang.Integer) == false) goto L_0x00f5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00ed, code lost:
        r0.append(r5, r3.getInt(r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00fb, code lost:
        if ((r3.get(r5) instanceof java.lang.Long) == false) goto L_0x0105;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00fd, code lost:
        r0.append(r5, r3.getLong(r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x010b, code lost:
        if ((r3.get(r5) instanceof java.lang.String) == false) goto L_0x0115;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x010d, code lost:
        r0.append(r5, r3.getString(r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x011b, code lost:
        if ((r3.get(r5) instanceof org.json.JSONObject) == false) goto L_0x0125;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x011d, code lost:
        r0.append(r5, r3.getJSONObject(r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x012b, code lost:
        if ((r3.get(r5) instanceof org.json.JSONArray) == false) goto L_0x0135;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x012d, code lost:
        r0.append(r5, r3.getJSONArray(r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x013b, code lost:
        if ((r3.get(r5) instanceof java.lang.Boolean) == false) goto L_0x0144;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x013d, code lost:
        r0.append(r5, r3.getBoolean(r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x014a, code lost:
        if ((r3.get(r5) instanceof java.lang.Double) == false) goto L_0x0154;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x014c, code lost:
        r0.prepend(r5, r3.getDouble(r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x015a, code lost:
        if ((r3.get(r5) instanceof java.lang.Integer) == false) goto L_0x0164;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x015c, code lost:
        r0.prepend(r5, r3.getInt(r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x016a, code lost:
        if ((r3.get(r5) instanceof java.lang.Long) == false) goto L_0x0174;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x016c, code lost:
        r0.prepend(r5, r3.getLong(r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x017a, code lost:
        if ((r3.get(r5) instanceof java.lang.String) == false) goto L_0x0184;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x017c, code lost:
        r0.prepend(r5, r3.getString(r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x018a, code lost:
        if ((r3.get(r5) instanceof org.json.JSONObject) == false) goto L_0x0194;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x018c, code lost:
        r0.prepend(r5, r3.getJSONObject(r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x019a, code lost:
        if ((r3.get(r5) instanceof org.json.JSONArray) == false) goto L_0x01a4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x019c, code lost:
        r0.prepend(r5, r3.getJSONArray(r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x01aa, code lost:
        if ((r3.get(r5) instanceof java.lang.Boolean) == false) goto L_0x01b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x01ac, code lost:
        r0.prepend(r5, r3.getBoolean(r5));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x01b9, code lost:
        if ((r3.get(r5) instanceof java.lang.Double) == false) goto L_0x01c3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x01bb, code lost:
        r0.preInsert(r5, r3.getDouble(r5));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.amplitude.api.Identify createIdentify(org.json.JSONObject r9) {
        /*
            r8 = this;
            com.amplitude.api.Identify r0 = new com.amplitude.api.Identify
            r0.<init>()
            java.util.Iterator r1 = r9.keys()
        L_0x0009:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0374
            java.lang.Object r2 = r1.next()
            java.lang.String r2 = (java.lang.String) r2
            org.json.JSONObject r3 = r9.getJSONObject(r2)     // Catch:{ JSONException -> 0x0009 }
            java.util.Iterator r4 = r3.keys()     // Catch:{ JSONException -> 0x0009 }
        L_0x001d:
            boolean r5 = r4.hasNext()     // Catch:{ JSONException -> 0x0009 }
            if (r5 == 0) goto L_0x0009
            java.lang.Object r5 = r4.next()     // Catch:{ JSONException -> 0x0009 }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ JSONException -> 0x0009 }
            int r6 = r2.hashCode()     // Catch:{ JSONException -> 0x0009 }
            switch(r6) {
                case -2001704214: goto L_0x0077;
                case -600472680: goto L_0x006d;
                case 1168893: goto L_0x0063;
                case 1186238: goto L_0x0059;
                case 301505887: goto L_0x004f;
                case 474293310: goto L_0x0045;
                case 1061581469: goto L_0x003b;
                case 1142092165: goto L_0x0031;
                default: goto L_0x0030;
            }     // Catch:{ JSONException -> 0x0009 }
        L_0x0030:
            goto L_0x0081
        L_0x0031:
            java.lang.String r6 = "$unset"
            boolean r6 = r2.equals(r6)     // Catch:{ JSONException -> 0x0009 }
            if (r6 == 0) goto L_0x0081
            r6 = 7
            goto L_0x0082
        L_0x003b:
            java.lang.String r6 = "$postInsert"
            boolean r6 = r2.equals(r6)     // Catch:{ JSONException -> 0x0009 }
            if (r6 == 0) goto L_0x0081
            r6 = 4
            goto L_0x0082
        L_0x0045:
            java.lang.String r6 = "$append"
            boolean r6 = r2.equals(r6)     // Catch:{ JSONException -> 0x0009 }
            if (r6 == 0) goto L_0x0081
            r6 = 1
            goto L_0x0082
        L_0x004f:
            java.lang.String r6 = "$setOnce"
            boolean r6 = r2.equals(r6)     // Catch:{ JSONException -> 0x0009 }
            if (r6 == 0) goto L_0x0081
            r6 = 6
            goto L_0x0082
        L_0x0059:
            java.lang.String r6 = "$set"
            boolean r6 = r2.equals(r6)     // Catch:{ JSONException -> 0x0009 }
            if (r6 == 0) goto L_0x0081
            r6 = 5
            goto L_0x0082
        L_0x0063:
            java.lang.String r6 = "$add"
            boolean r6 = r2.equals(r6)     // Catch:{ JSONException -> 0x0009 }
            if (r6 == 0) goto L_0x0081
            r6 = 0
            goto L_0x0082
        L_0x006d:
            java.lang.String r6 = "$preInsert"
            boolean r6 = r2.equals(r6)     // Catch:{ JSONException -> 0x0009 }
            if (r6 == 0) goto L_0x0081
            r6 = 3
            goto L_0x0082
        L_0x0077:
            java.lang.String r6 = "$prepend"
            boolean r6 = r2.equals(r6)     // Catch:{ JSONException -> 0x0009 }
            if (r6 == 0) goto L_0x0081
            r6 = 2
            goto L_0x0082
        L_0x0081:
            r6 = -1
        L_0x0082:
            switch(r6) {
                case 0: goto L_0x0086;
                case 1: goto L_0x00d5;
                case 2: goto L_0x0144;
                case 3: goto L_0x01b3;
                case 4: goto L_0x0222;
                case 5: goto L_0x0291;
                case 6: goto L_0x0300;
                case 7: goto L_0x036f;
                default: goto L_0x0085;
            }     // Catch:{ JSONException -> 0x0009 }
        L_0x0085:
            goto L_0x001d
        L_0x0086:
            java.lang.Object r6 = r3.get(r5)     // Catch:{ JSONException -> 0x0009 }
            boolean r6 = r6 instanceof java.lang.Double     // Catch:{ JSONException -> 0x0009 }
            if (r6 == 0) goto L_0x0096
            double r6 = r3.getDouble(r5)     // Catch:{ JSONException -> 0x0009 }
            r0.add((java.lang.String) r5, (double) r6)     // Catch:{ JSONException -> 0x0009 }
            goto L_0x00d5
        L_0x0096:
            java.lang.Object r6 = r3.get(r5)     // Catch:{ JSONException -> 0x0009 }
            boolean r6 = r6 instanceof java.lang.Integer     // Catch:{ JSONException -> 0x0009 }
            if (r6 == 0) goto L_0x00a6
            int r6 = r3.getInt(r5)     // Catch:{ JSONException -> 0x0009 }
            r0.add((java.lang.String) r5, (int) r6)     // Catch:{ JSONException -> 0x0009 }
            goto L_0x00d5
        L_0x00a6:
            java.lang.Object r6 = r3.get(r5)     // Catch:{ JSONException -> 0x0009 }
            boolean r6 = r6 instanceof java.lang.Long     // Catch:{ JSONException -> 0x0009 }
            if (r6 == 0) goto L_0x00b6
            long r6 = r3.getLong(r5)     // Catch:{ JSONException -> 0x0009 }
            r0.add((java.lang.String) r5, (long) r6)     // Catch:{ JSONException -> 0x0009 }
            goto L_0x00d5
        L_0x00b6:
            java.lang.Object r6 = r3.get(r5)     // Catch:{ JSONException -> 0x0009 }
            boolean r6 = r6 instanceof java.lang.String     // Catch:{ JSONException -> 0x0009 }
            if (r6 == 0) goto L_0x00c6
            java.lang.String r6 = r3.getString(r5)     // Catch:{ JSONException -> 0x0009 }
            r0.add((java.lang.String) r5, (java.lang.String) r6)     // Catch:{ JSONException -> 0x0009 }
            goto L_0x00d5
        L_0x00c6:
            java.lang.Object r6 = r3.get(r5)     // Catch:{ JSONException -> 0x0009 }
            boolean r6 = r6 instanceof org.json.JSONObject     // Catch:{ JSONException -> 0x0009 }
            if (r6 == 0) goto L_0x00d5
            org.json.JSONObject r6 = r3.getJSONObject(r5)     // Catch:{ JSONException -> 0x0009 }
            r0.add((java.lang.String) r5, (org.json.JSONObject) r6)     // Catch:{ JSONException -> 0x0009 }
        L_0x00d5:
            java.lang.Object r6 = r3.get(r5)     // Catch:{ JSONException -> 0x0009 }
            boolean r6 = r6 instanceof java.lang.Double     // Catch:{ JSONException -> 0x0009 }
            if (r6 == 0) goto L_0x00e5
            double r6 = r3.getDouble(r5)     // Catch:{ JSONException -> 0x0009 }
            r0.append((java.lang.String) r5, (double) r6)     // Catch:{ JSONException -> 0x0009 }
            goto L_0x0144
        L_0x00e5:
            java.lang.Object r6 = r3.get(r5)     // Catch:{ JSONException -> 0x0009 }
            boolean r6 = r6 instanceof java.lang.Integer     // Catch:{ JSONException -> 0x0009 }
            if (r6 == 0) goto L_0x00f5
            int r6 = r3.getInt(r5)     // Catch:{ JSONException -> 0x0009 }
            r0.append((java.lang.String) r5, (int) r6)     // Catch:{ JSONException -> 0x0009 }
            goto L_0x0144
        L_0x00f5:
            java.lang.Object r6 = r3.get(r5)     // Catch:{ JSONException -> 0x0009 }
            boolean r6 = r6 instanceof java.lang.Long     // Catch:{ JSONException -> 0x0009 }
            if (r6 == 0) goto L_0x0105
            long r6 = r3.getLong(r5)     // Catch:{ JSONException -> 0x0009 }
            r0.append((java.lang.String) r5, (long) r6)     // Catch:{ JSONException -> 0x0009 }
            goto L_0x0144
        L_0x0105:
            java.lang.Object r6 = r3.get(r5)     // Catch:{ JSONException -> 0x0009 }
            boolean r6 = r6 instanceof java.lang.String     // Catch:{ JSONException -> 0x0009 }
            if (r6 == 0) goto L_0x0115
            java.lang.String r6 = r3.getString(r5)     // Catch:{ JSONException -> 0x0009 }
            r0.append((java.lang.String) r5, (java.lang.String) r6)     // Catch:{ JSONException -> 0x0009 }
            goto L_0x0144
        L_0x0115:
            java.lang.Object r6 = r3.get(r5)     // Catch:{ JSONException -> 0x0009 }
            boolean r6 = r6 instanceof org.json.JSONObject     // Catch:{ JSONException -> 0x0009 }
            if (r6 == 0) goto L_0x0125
            org.json.JSONObject r6 = r3.getJSONObject(r5)     // Catch:{ JSONException -> 0x0009 }
            r0.append((java.lang.String) r5, (org.json.JSONObject) r6)     // Catch:{ JSONException -> 0x0009 }
            goto L_0x0144
        L_0x0125:
            java.lang.Object r6 = r3.get(r5)     // Catch:{ JSONException -> 0x0009 }
            boolean r6 = r6 instanceof org.json.JSONArray     // Catch:{ JSONException -> 0x0009 }
            if (r6 == 0) goto L_0x0135
            org.json.JSONArray r6 = r3.getJSONArray(r5)     // Catch:{ JSONException -> 0x0009 }
            r0.append((java.lang.String) r5, (org.json.JSONArray) r6)     // Catch:{ JSONException -> 0x0009 }
            goto L_0x0144
        L_0x0135:
            java.lang.Object r6 = r3.get(r5)     // Catch:{ JSONException -> 0x0009 }
            boolean r6 = r6 instanceof java.lang.Boolean     // Catch:{ JSONException -> 0x0009 }
            if (r6 == 0) goto L_0x0144
            boolean r6 = r3.getBoolean(r5)     // Catch:{ JSONException -> 0x0009 }
            r0.append((java.lang.String) r5, (boolean) r6)     // Catch:{ JSONException -> 0x0009 }
        L_0x0144:
            java.lang.Object r6 = r3.get(r5)     // Catch:{ JSONException -> 0x0009 }
            boolean r6 = r6 instanceof java.lang.Double     // Catch:{ JSONException -> 0x0009 }
            if (r6 == 0) goto L_0x0154
            double r6 = r3.getDouble(r5)     // Catch:{ JSONException -> 0x0009 }
            r0.prepend((java.lang.String) r5, (double) r6)     // Catch:{ JSONException -> 0x0009 }
            goto L_0x01b3
        L_0x0154:
            java.lang.Object r6 = r3.get(r5)     // Catch:{ JSONException -> 0x0009 }
            boolean r6 = r6 instanceof java.lang.Integer     // Catch:{ JSONException -> 0x0009 }
            if (r6 == 0) goto L_0x0164
            int r6 = r3.getInt(r5)     // Catch:{ JSONException -> 0x0009 }
            r0.prepend((java.lang.String) r5, (int) r6)     // Catch:{ JSONException -> 0x0009 }
            goto L_0x01b3
        L_0x0164:
            java.lang.Object r6 = r3.get(r5)     // Catch:{ JSONException -> 0x0009 }
            boolean r6 = r6 instanceof java.lang.Long     // Catch:{ JSONException -> 0x0009 }
            if (r6 == 0) goto L_0x0174
            long r6 = r3.getLong(r5)     // Catch:{ JSONException -> 0x0009 }
            r0.prepend((java.lang.String) r5, (long) r6)     // Catch:{ JSONException -> 0x0009 }
            goto L_0x01b3
        L_0x0174:
            java.lang.Object r6 = r3.get(r5)     // Catch:{ JSONException -> 0x0009 }
            boolean r6 = r6 instanceof java.lang.String     // Catch:{ JSONException -> 0x0009 }
            if (r6 == 0) goto L_0x0184
            java.lang.String r6 = r3.getString(r5)     // Catch:{ JSONException -> 0x0009 }
            r0.prepend((java.lang.String) r5, (java.lang.String) r6)     // Catch:{ JSONException -> 0x0009 }
            goto L_0x01b3
        L_0x0184:
            java.lang.Object r6 = r3.get(r5)     // Catch:{ JSONException -> 0x0009 }
            boolean r6 = r6 instanceof org.json.JSONObject     // Catch:{ JSONException -> 0x0009 }
            if (r6 == 0) goto L_0x0194
            org.json.JSONObject r6 = r3.getJSONObject(r5)     // Catch:{ JSONException -> 0x0009 }
            r0.prepend((java.lang.String) r5, (org.json.JSONObject) r6)     // Catch:{ JSONException -> 0x0009 }
            goto L_0x01b3
        L_0x0194:
            java.lang.Object r6 = r3.get(r5)     // Catch:{ JSONException -> 0x0009 }
            boolean r6 = r6 instanceof org.json.JSONArray     // Catch:{ JSONException -> 0x0009 }
            if (r6 == 0) goto L_0x01a4
            org.json.JSONArray r6 = r3.getJSONArray(r5)     // Catch:{ JSONException -> 0x0009 }
            r0.prepend((java.lang.String) r5, (org.json.JSONArray) r6)     // Catch:{ JSONException -> 0x0009 }
            goto L_0x01b3
        L_0x01a4:
            java.lang.Object r6 = r3.get(r5)     // Catch:{ JSONException -> 0x0009 }
            boolean r6 = r6 instanceof java.lang.Boolean     // Catch:{ JSONException -> 0x0009 }
            if (r6 == 0) goto L_0x01b3
            boolean r6 = r3.getBoolean(r5)     // Catch:{ JSONException -> 0x0009 }
            r0.prepend((java.lang.String) r5, (boolean) r6)     // Catch:{ JSONException -> 0x0009 }
        L_0x01b3:
            java.lang.Object r6 = r3.get(r5)     // Catch:{ JSONException -> 0x0009 }
            boolean r6 = r6 instanceof java.lang.Double     // Catch:{ JSONException -> 0x0009 }
            if (r6 == 0) goto L_0x01c3
            double r6 = r3.getDouble(r5)     // Catch:{ JSONException -> 0x0009 }
            r0.preInsert((java.lang.String) r5, (double) r6)     // Catch:{ JSONException -> 0x0009 }
            goto L_0x0222
        L_0x01c3:
            java.lang.Object r6 = r3.get(r5)     // Catch:{ JSONException -> 0x0009 }
            boolean r6 = r6 instanceof java.lang.Integer     // Catch:{ JSONException -> 0x0009 }
            if (r6 == 0) goto L_0x01d3
            int r6 = r3.getInt(r5)     // Catch:{ JSONException -> 0x0009 }
            r0.preInsert((java.lang.String) r5, (int) r6)     // Catch:{ JSONException -> 0x0009 }
            goto L_0x0222
        L_0x01d3:
            java.lang.Object r6 = r3.get(r5)     // Catch:{ JSONException -> 0x0009 }
            boolean r6 = r6 instanceof java.lang.Long     // Catch:{ JSONException -> 0x0009 }
            if (r6 == 0) goto L_0x01e3
            long r6 = r3.getLong(r5)     // Catch:{ JSONException -> 0x0009 }
            r0.preInsert((java.lang.String) r5, (long) r6)     // Catch:{ JSONException -> 0x0009 }
            goto L_0x0222
        L_0x01e3:
            java.lang.Object r6 = r3.get(r5)     // Catch:{ JSONException -> 0x0009 }
            boolean r6 = r6 instanceof java.lang.String     // Catch:{ JSONException -> 0x0009 }
            if (r6 == 0) goto L_0x01f3
            java.lang.String r6 = r3.getString(r5)     // Catch:{ JSONException -> 0x0009 }
            r0.preInsert((java.lang.String) r5, (java.lang.String) r6)     // Catch:{ JSONException -> 0x0009 }
            goto L_0x0222
        L_0x01f3:
            java.lang.Object r6 = r3.get(r5)     // Catch:{ JSONException -> 0x0009 }
            boolean r6 = r6 instanceof org.json.JSONObject     // Catch:{ JSONException -> 0x0009 }
            if (r6 == 0) goto L_0x0203
            org.json.JSONObject r6 = r3.getJSONObject(r5)     // Catch:{ JSONException -> 0x0009 }
            r0.preInsert((java.lang.String) r5, (org.json.JSONObject) r6)     // Catch:{ JSONException -> 0x0009 }
            goto L_0x0222
        L_0x0203:
            java.lang.Object r6 = r3.get(r5)     // Catch:{ JSONException -> 0x0009 }
            boolean r6 = r6 instanceof org.json.JSONArray     // Catch:{ JSONException -> 0x0009 }
            if (r6 == 0) goto L_0x0213
            org.json.JSONArray r6 = r3.getJSONArray(r5)     // Catch:{ JSONException -> 0x0009 }
            r0.preInsert((java.lang.String) r5, (org.json.JSONArray) r6)     // Catch:{ JSONException -> 0x0009 }
            goto L_0x0222
        L_0x0213:
            java.lang.Object r6 = r3.get(r5)     // Catch:{ JSONException -> 0x0009 }
            boolean r6 = r6 instanceof java.lang.Boolean     // Catch:{ JSONException -> 0x0009 }
            if (r6 == 0) goto L_0x0222
            boolean r6 = r3.getBoolean(r5)     // Catch:{ JSONException -> 0x0009 }
            r0.preInsert((java.lang.String) r5, (boolean) r6)     // Catch:{ JSONException -> 0x0009 }
        L_0x0222:
            java.lang.Object r6 = r3.get(r5)     // Catch:{ JSONException -> 0x0009 }
            boolean r6 = r6 instanceof java.lang.Double     // Catch:{ JSONException -> 0x0009 }
            if (r6 == 0) goto L_0x0232
            double r6 = r3.getDouble(r5)     // Catch:{ JSONException -> 0x0009 }
            r0.postInsert((java.lang.String) r5, (double) r6)     // Catch:{ JSONException -> 0x0009 }
            goto L_0x0291
        L_0x0232:
            java.lang.Object r6 = r3.get(r5)     // Catch:{ JSONException -> 0x0009 }
            boolean r6 = r6 instanceof java.lang.Integer     // Catch:{ JSONException -> 0x0009 }
            if (r6 == 0) goto L_0x0242
            int r6 = r3.getInt(r5)     // Catch:{ JSONException -> 0x0009 }
            r0.postInsert((java.lang.String) r5, (int) r6)     // Catch:{ JSONException -> 0x0009 }
            goto L_0x0291
        L_0x0242:
            java.lang.Object r6 = r3.get(r5)     // Catch:{ JSONException -> 0x0009 }
            boolean r6 = r6 instanceof java.lang.Long     // Catch:{ JSONException -> 0x0009 }
            if (r6 == 0) goto L_0x0252
            long r6 = r3.getLong(r5)     // Catch:{ JSONException -> 0x0009 }
            r0.postInsert((java.lang.String) r5, (long) r6)     // Catch:{ JSONException -> 0x0009 }
            goto L_0x0291
        L_0x0252:
            java.lang.Object r6 = r3.get(r5)     // Catch:{ JSONException -> 0x0009 }
            boolean r6 = r6 instanceof java.lang.String     // Catch:{ JSONException -> 0x0009 }
            if (r6 == 0) goto L_0x0262
            java.lang.String r6 = r3.getString(r5)     // Catch:{ JSONException -> 0x0009 }
            r0.postInsert((java.lang.String) r5, (java.lang.String) r6)     // Catch:{ JSONException -> 0x0009 }
            goto L_0x0291
        L_0x0262:
            java.lang.Object r6 = r3.get(r5)     // Catch:{ JSONException -> 0x0009 }
            boolean r6 = r6 instanceof org.json.JSONObject     // Catch:{ JSONException -> 0x0009 }
            if (r6 == 0) goto L_0x0272
            org.json.JSONObject r6 = r3.getJSONObject(r5)     // Catch:{ JSONException -> 0x0009 }
            r0.postInsert((java.lang.String) r5, (org.json.JSONObject) r6)     // Catch:{ JSONException -> 0x0009 }
            goto L_0x0291
        L_0x0272:
            java.lang.Object r6 = r3.get(r5)     // Catch:{ JSONException -> 0x0009 }
            boolean r6 = r6 instanceof org.json.JSONArray     // Catch:{ JSONException -> 0x0009 }
            if (r6 == 0) goto L_0x0282
            org.json.JSONArray r6 = r3.getJSONArray(r5)     // Catch:{ JSONException -> 0x0009 }
            r0.postInsert((java.lang.String) r5, (org.json.JSONArray) r6)     // Catch:{ JSONException -> 0x0009 }
            goto L_0x0291
        L_0x0282:
            java.lang.Object r6 = r3.get(r5)     // Catch:{ JSONException -> 0x0009 }
            boolean r6 = r6 instanceof java.lang.Boolean     // Catch:{ JSONException -> 0x0009 }
            if (r6 == 0) goto L_0x0291
            boolean r6 = r3.getBoolean(r5)     // Catch:{ JSONException -> 0x0009 }
            r0.postInsert((java.lang.String) r5, (boolean) r6)     // Catch:{ JSONException -> 0x0009 }
        L_0x0291:
            java.lang.Object r6 = r3.get(r5)     // Catch:{ JSONException -> 0x0009 }
            boolean r6 = r6 instanceof java.lang.Double     // Catch:{ JSONException -> 0x0009 }
            if (r6 == 0) goto L_0x02a1
            double r6 = r3.getDouble(r5)     // Catch:{ JSONException -> 0x0009 }
            r0.set((java.lang.String) r5, (double) r6)     // Catch:{ JSONException -> 0x0009 }
            goto L_0x0300
        L_0x02a1:
            java.lang.Object r6 = r3.get(r5)     // Catch:{ JSONException -> 0x0009 }
            boolean r6 = r6 instanceof java.lang.Integer     // Catch:{ JSONException -> 0x0009 }
            if (r6 == 0) goto L_0x02b1
            int r6 = r3.getInt(r5)     // Catch:{ JSONException -> 0x0009 }
            r0.set((java.lang.String) r5, (int) r6)     // Catch:{ JSONException -> 0x0009 }
            goto L_0x0300
        L_0x02b1:
            java.lang.Object r6 = r3.get(r5)     // Catch:{ JSONException -> 0x0009 }
            boolean r6 = r6 instanceof java.lang.Long     // Catch:{ JSONException -> 0x0009 }
            if (r6 == 0) goto L_0x02c1
            long r6 = r3.getLong(r5)     // Catch:{ JSONException -> 0x0009 }
            r0.set((java.lang.String) r5, (long) r6)     // Catch:{ JSONException -> 0x0009 }
            goto L_0x0300
        L_0x02c1:
            java.lang.Object r6 = r3.get(r5)     // Catch:{ JSONException -> 0x0009 }
            boolean r6 = r6 instanceof java.lang.String     // Catch:{ JSONException -> 0x0009 }
            if (r6 == 0) goto L_0x02d1
            java.lang.String r6 = r3.getString(r5)     // Catch:{ JSONException -> 0x0009 }
            r0.set((java.lang.String) r5, (java.lang.String) r6)     // Catch:{ JSONException -> 0x0009 }
            goto L_0x0300
        L_0x02d1:
            java.lang.Object r6 = r3.get(r5)     // Catch:{ JSONException -> 0x0009 }
            boolean r6 = r6 instanceof org.json.JSONObject     // Catch:{ JSONException -> 0x0009 }
            if (r6 == 0) goto L_0x02e1
            org.json.JSONObject r6 = r3.getJSONObject(r5)     // Catch:{ JSONException -> 0x0009 }
            r0.set((java.lang.String) r5, (org.json.JSONObject) r6)     // Catch:{ JSONException -> 0x0009 }
            goto L_0x0300
        L_0x02e1:
            java.lang.Object r6 = r3.get(r5)     // Catch:{ JSONException -> 0x0009 }
            boolean r6 = r6 instanceof org.json.JSONArray     // Catch:{ JSONException -> 0x0009 }
            if (r6 == 0) goto L_0x02f1
            org.json.JSONArray r6 = r3.getJSONArray(r5)     // Catch:{ JSONException -> 0x0009 }
            r0.set((java.lang.String) r5, (org.json.JSONArray) r6)     // Catch:{ JSONException -> 0x0009 }
            goto L_0x0300
        L_0x02f1:
            java.lang.Object r6 = r3.get(r5)     // Catch:{ JSONException -> 0x0009 }
            boolean r6 = r6 instanceof java.lang.Boolean     // Catch:{ JSONException -> 0x0009 }
            if (r6 == 0) goto L_0x0300
            boolean r6 = r3.getBoolean(r5)     // Catch:{ JSONException -> 0x0009 }
            r0.set((java.lang.String) r5, (boolean) r6)     // Catch:{ JSONException -> 0x0009 }
        L_0x0300:
            java.lang.Object r6 = r3.get(r5)     // Catch:{ JSONException -> 0x0009 }
            boolean r6 = r6 instanceof java.lang.Double     // Catch:{ JSONException -> 0x0009 }
            if (r6 == 0) goto L_0x0310
            double r6 = r3.getDouble(r5)     // Catch:{ JSONException -> 0x0009 }
            r0.setOnce((java.lang.String) r5, (double) r6)     // Catch:{ JSONException -> 0x0009 }
            goto L_0x036f
        L_0x0310:
            java.lang.Object r6 = r3.get(r5)     // Catch:{ JSONException -> 0x0009 }
            boolean r6 = r6 instanceof java.lang.Integer     // Catch:{ JSONException -> 0x0009 }
            if (r6 == 0) goto L_0x0320
            int r6 = r3.getInt(r5)     // Catch:{ JSONException -> 0x0009 }
            r0.setOnce((java.lang.String) r5, (int) r6)     // Catch:{ JSONException -> 0x0009 }
            goto L_0x036f
        L_0x0320:
            java.lang.Object r6 = r3.get(r5)     // Catch:{ JSONException -> 0x0009 }
            boolean r6 = r6 instanceof java.lang.Long     // Catch:{ JSONException -> 0x0009 }
            if (r6 == 0) goto L_0x0330
            long r6 = r3.getLong(r5)     // Catch:{ JSONException -> 0x0009 }
            r0.setOnce((java.lang.String) r5, (long) r6)     // Catch:{ JSONException -> 0x0009 }
            goto L_0x036f
        L_0x0330:
            java.lang.Object r6 = r3.get(r5)     // Catch:{ JSONException -> 0x0009 }
            boolean r6 = r6 instanceof java.lang.String     // Catch:{ JSONException -> 0x0009 }
            if (r6 == 0) goto L_0x0340
            java.lang.String r6 = r3.getString(r5)     // Catch:{ JSONException -> 0x0009 }
            r0.setOnce((java.lang.String) r5, (java.lang.String) r6)     // Catch:{ JSONException -> 0x0009 }
            goto L_0x036f
        L_0x0340:
            java.lang.Object r6 = r3.get(r5)     // Catch:{ JSONException -> 0x0009 }
            boolean r6 = r6 instanceof org.json.JSONObject     // Catch:{ JSONException -> 0x0009 }
            if (r6 == 0) goto L_0x0350
            org.json.JSONObject r6 = r3.getJSONObject(r5)     // Catch:{ JSONException -> 0x0009 }
            r0.setOnce((java.lang.String) r5, (org.json.JSONObject) r6)     // Catch:{ JSONException -> 0x0009 }
            goto L_0x036f
        L_0x0350:
            java.lang.Object r6 = r3.get(r5)     // Catch:{ JSONException -> 0x0009 }
            boolean r6 = r6 instanceof org.json.JSONArray     // Catch:{ JSONException -> 0x0009 }
            if (r6 == 0) goto L_0x0360
            org.json.JSONArray r6 = r3.getJSONArray(r5)     // Catch:{ JSONException -> 0x0009 }
            r0.setOnce((java.lang.String) r5, (org.json.JSONArray) r6)     // Catch:{ JSONException -> 0x0009 }
            goto L_0x036f
        L_0x0360:
            java.lang.Object r6 = r3.get(r5)     // Catch:{ JSONException -> 0x0009 }
            boolean r6 = r6 instanceof java.lang.Boolean     // Catch:{ JSONException -> 0x0009 }
            if (r6 == 0) goto L_0x036f
            boolean r6 = r3.getBoolean(r5)     // Catch:{ JSONException -> 0x0009 }
            r0.setOnce((java.lang.String) r5, (boolean) r6)     // Catch:{ JSONException -> 0x0009 }
        L_0x036f:
            r0.unset(r5)     // Catch:{ JSONException -> 0x0009 }
            goto L_0x001d
        L_0x0374:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amplitude.reactnative.AmplitudeReactNativeModule.createIdentify(org.json.JSONObject):com.amplitude.api.Identify");
    }
}
