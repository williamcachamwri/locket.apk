package expo.modules.manifests.core;

import com.amplitude.api.DeviceInfo;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import expo.modules.manifests.core.PluginType;
import expo.modules.notifications.service.NotificationsService;
import io.sentry.protocol.Device;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010$\n\u0002\b\u000f\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u0000 @2\u00020\u0001:\u0001@B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\r\u001a\u0004\u0018\u00010\u0006J\b\u0010\u000e\u001a\u0004\u0018\u00010\u0006J\b\u0010\u000f\u001a\u0004\u0018\u00010\u0006J\b\u0010\u0010\u001a\u0004\u0018\u00010\u0003J\b\u0010\u0011\u001a\u0004\u0018\u00010\u0006J\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003J\b\u0010\u0013\u001a\u0004\u0018\u00010\u0003J\b\u0010\u0014\u001a\u0004\u0018\u00010\u0006J\n\u0010\u0015\u001a\u0004\u0018\u00010\u0006H&J\n\u0010\u0016\u001a\u0004\u0018\u00010\u0017H&J\b\u0010\u0018\u001a\u00020\u0006H&J\u0006\u0010\u0019\u001a\u00020\u0006J\n\u0010\u001a\u001a\u0004\u0018\u00010\u0006H&J\n\u0010\u001b\u001a\u0004\u0018\u00010\u0003H&J\n\u0010\u001c\u001a\u0004\u0018\u00010\u0003H&J\n\u0010\u001d\u001a\u0004\u0018\u00010\u0006H&J\u0006\u0010\u001e\u001a\u00020\u0006J\u0006\u0010\u001f\u001a\u00020\u0006J\u0006\u0010 \u001a\u00020!J\b\u0010\"\u001a\u0004\u0018\u00010\u0006J\b\u0010#\u001a\u0004\u0018\u00010\u0006J\b\u0010$\u001a\u00020\u0006H\u0007J\u0006\u0010%\u001a\u00020\u0006J\b\u0010&\u001a\u0004\u0018\u00010\u0003J\b\u0010'\u001a\u0004\u0018\u00010\u0006J\b\u0010(\u001a\u0004\u0018\u00010\u0003J\b\u0010)\u001a\u0004\u0018\u00010\u0006J\u001c\u0010*\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0001\u0018\u00010+2\u0006\u0010,\u001a\u00020\u0006J\b\u0010-\u001a\u0004\u0018\u00010\u0006J\b\u0010.\u001a\u00020\u0003H\u0007J\u0006\u0010/\u001a\u00020\u0006J\b\u00100\u001a\u0004\u0018\u00010\u0003J\b\u00101\u001a\u00020\u0006H&J\n\u00102\u001a\u0004\u0018\u00010\u0006H&J\n\u00103\u001a\u0004\u0018\u00010\u0006H'J\b\u00104\u001a\u0004\u0018\u00010\u0003J\b\u00105\u001a\u0004\u0018\u00010\u0006J\u0006\u00106\u001a\u00020!J\u0006\u00107\u001a\u00020!J\u0006\u00108\u001a\u00020!J\u0006\u00109\u001a\u00020!J\u0010\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020=H\u0007J\u0006\u0010>\u001a\u00020!J\b\u0010?\u001a\u00020\u0006H\u0017R\u001b\u0010\u0005\u001a\u00020\u00068FX\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006A"}, d2 = {"Lexpo/modules/manifests/core/Manifest;", "", "json", "Lorg/json/JSONObject;", "(Lorg/json/JSONObject;)V", "jsEngine", "", "getJsEngine", "()Ljava/lang/String;", "jsEngine$delegate", "Lkotlin/Lazy;", "getJson", "()Lorg/json/JSONObject;", "getAndroidBackgroundColor", "getAndroidGoogleServicesFile", "getAndroidKeyboardLayoutMode", "getAndroidNavigationBarOptions", "getAndroidPackageName", "getAndroidSplashInfo", "getAndroidStatusBarOptions", "getAndroidUserInterfaceStyle", "getAppKey", "getAssets", "Lorg/json/JSONArray;", "getBundleURL", "getDebuggerHost", "getEASProjectID", "getExpoClientConfigRootObject", "getExpoGoConfigRootObject", "getExpoGoSDKVersion", "getFacebookAppId", "getFacebookApplicationName", "getFacebookAutoInitEnabled", "", "getHostUri", "getIconUrl", "getLegacyID", "getMainModuleName", "getMetadata", "getName", "getNotificationPreferences", "getOrientation", "getPluginProperties", "", "packageName", "getPrimaryColor", "getRawJson", "getRevisionId", "getRootSplashInfo", "getScopeKey", "getSlug", "getStableLegacyID", "getUpdatesInfo", "getVersion", "isDevelopmentMode", "isDevelopmentSilentLaunch", "isUsingDeveloperTool", "isVerified", "mutateInternalJSONInPlace", "", "internalJSONMutator", "Lexpo/modules/manifests/core/InternalJSONMutator;", "shouldUseNextNotificationsApi", "toString", "Companion", "expo-manifests_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: Manifest.kt */
public abstract class Manifest {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final Lazy jsEngine$delegate = LazyKt.lazy(new Manifest$jsEngine$2(this));
    private final JSONObject json;

    @JvmStatic
    public static final Manifest fromManifestJson(JSONObject jSONObject) {
        return Companion.fromManifestJson(jSONObject);
    }

    public abstract String getAppKey();

    public abstract JSONArray getAssets();

    public abstract String getBundleURL() throws JSONException;

    public abstract String getEASProjectID();

    public abstract JSONObject getExpoClientConfigRootObject();

    public abstract JSONObject getExpoGoConfigRootObject();

    public abstract String getExpoGoSDKVersion();

    public abstract String getScopeKey() throws JSONException;

    public abstract String getSlug();

    @Deprecated(message = "Prefer scopeKey or projectId depending on use case")
    public abstract String getStableLegacyID();

    public Manifest(JSONObject jSONObject) {
        Intrinsics.checkNotNullParameter(jSONObject, "json");
        this.json = jSONObject;
    }

    /* access modifiers changed from: protected */
    public final JSONObject getJson() {
        return this.json;
    }

    @Deprecated(message = "Strive for manifests to be immutable")
    public final void mutateInternalJSONInPlace(InternalJSONMutator internalJSONMutator) throws JSONException {
        Intrinsics.checkNotNullParameter(internalJSONMutator, "internalJSONMutator");
        internalJSONMutator.updateJSON(this.json);
    }

    @Deprecated(message = "Prefer to use specific field getters")
    public final JSONObject getRawJson() {
        return this.json;
    }

    @Deprecated(message = "Prefer to use specific field getters")
    public String toString() {
        String jSONObject = getRawJson().toString();
        Intrinsics.checkNotNullExpressionValue(jSONObject, "toString(...)");
        return jSONObject;
    }

    @Deprecated(message = "Prefer scopeKey or projectId depending on use case")
    public final String getLegacyID() throws JSONException {
        JSONObject jSONObject = this.json;
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
        if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
            String string = jSONObject.getString("id");
            if (string != null) {
                return string;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            return (String) Double.valueOf(jSONObject.getDouble("id"));
        } else {
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                return (String) Integer.valueOf(jSONObject.getInt("id"));
            }
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                return (String) Long.valueOf(jSONObject.getLong("id"));
            }
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                return (String) Boolean.valueOf(jSONObject.getBoolean("id"));
            }
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = jSONObject.getJSONArray("id");
                if (jSONArray != null) {
                    return (String) jSONArray;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("id");
                if (jSONObject2 != null) {
                    return (String) jSONObject2;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            } else {
                Object obj = jSONObject.get("id");
                if (obj != null) {
                    return (String) obj;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
        }
    }

    public final String getRevisionId() throws JSONException {
        JSONObject expoClientConfigRootObject = getExpoClientConfigRootObject();
        Intrinsics.checkNotNull(expoClientConfigRootObject);
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
        if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
            String string = expoClientConfigRootObject.getString("revisionId");
            if (string != null) {
                return string;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            return (String) Double.valueOf(expoClientConfigRootObject.getDouble("revisionId"));
        } else {
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                return (String) Integer.valueOf(expoClientConfigRootObject.getInt("revisionId"));
            }
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                return (String) Long.valueOf(expoClientConfigRootObject.getLong("revisionId"));
            }
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                return (String) Boolean.valueOf(expoClientConfigRootObject.getBoolean("revisionId"));
            }
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = expoClientConfigRootObject.getJSONArray("revisionId");
                if (jSONArray != null) {
                    return (String) jSONArray;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                JSONObject jSONObject = expoClientConfigRootObject.getJSONObject("revisionId");
                if (jSONObject != null) {
                    return (String) jSONObject;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            } else {
                Object obj = expoClientConfigRootObject.get("revisionId");
                if (obj != null) {
                    return (String) obj;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
        }
    }

    public final JSONObject getMetadata() {
        JSONObject jSONObject = this.json;
        if (!jSONObject.has(TtmlNode.TAG_METADATA)) {
            return null;
        }
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(JSONObject.class);
        if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
            String string = jSONObject.getString(TtmlNode.TAG_METADATA);
            if (string != null) {
                return (JSONObject) string;
            }
            throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            return (JSONObject) Double.valueOf(jSONObject.getDouble(TtmlNode.TAG_METADATA));
        } else {
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                return (JSONObject) Integer.valueOf(jSONObject.getInt(TtmlNode.TAG_METADATA));
            }
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                return (JSONObject) Long.valueOf(jSONObject.getLong(TtmlNode.TAG_METADATA));
            }
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                return (JSONObject) Boolean.valueOf(jSONObject.getBoolean(TtmlNode.TAG_METADATA));
            }
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = jSONObject.getJSONArray(TtmlNode.TAG_METADATA);
                if (jSONArray != null) {
                    return (JSONObject) jSONArray;
                }
                throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                JSONObject jSONObject2 = jSONObject.getJSONObject(TtmlNode.TAG_METADATA);
                if (jSONObject2 != null) {
                    return jSONObject2;
                }
                throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
            } else {
                Object obj = jSONObject.get(TtmlNode.TAG_METADATA);
                if (obj != null) {
                    return (JSONObject) obj;
                }
                throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:103:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x01b5 A[Catch:{ JSONException -> 0x01be }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean isDevelopmentMode() {
        /*
            r7 = this;
            org.json.JSONObject r0 = r7.getExpoGoConfigRootObject()
            r1 = 0
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            java.lang.String r2 = "developer"
            boolean r2 = r0.has(r2)     // Catch:{ JSONException -> 0x01be }
            if (r2 == 0) goto L_0x01be
            java.lang.String r2 = "packagerOpts"
            boolean r3 = r0.has(r2)     // Catch:{ JSONException -> 0x01be }
            r4 = 0
            if (r3 != 0) goto L_0x001c
            r0 = r4
            goto L_0x00d9
        L_0x001c:
            java.lang.Class<org.json.JSONObject> r3 = org.json.JSONObject.class
            kotlin.reflect.KClass r3 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)     // Catch:{ JSONException -> 0x01be }
            java.lang.Class<java.lang.String> r5 = java.lang.String.class
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)     // Catch:{ JSONException -> 0x01be }
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r5)     // Catch:{ JSONException -> 0x01be }
            java.lang.String r6 = "null cannot be cast to non-null type org.json.JSONObject"
            if (r5 == 0) goto L_0x0040
            java.lang.String r0 = r0.getString(r2)     // Catch:{ JSONException -> 0x01be }
            if (r0 == 0) goto L_0x003a
            org.json.JSONObject r0 = (org.json.JSONObject) r0     // Catch:{ JSONException -> 0x01be }
            goto L_0x00d9
        L_0x003a:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException     // Catch:{ JSONException -> 0x01be }
            r0.<init>(r6)     // Catch:{ JSONException -> 0x01be }
            throw r0     // Catch:{ JSONException -> 0x01be }
        L_0x0040:
            java.lang.Class r5 = java.lang.Double.TYPE     // Catch:{ JSONException -> 0x01be }
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)     // Catch:{ JSONException -> 0x01be }
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r5)     // Catch:{ JSONException -> 0x01be }
            if (r5 == 0) goto L_0x0058
            double r2 = r0.getDouble(r2)     // Catch:{ JSONException -> 0x01be }
            java.lang.Double r0 = java.lang.Double.valueOf(r2)     // Catch:{ JSONException -> 0x01be }
            org.json.JSONObject r0 = (org.json.JSONObject) r0     // Catch:{ JSONException -> 0x01be }
            goto L_0x00d9
        L_0x0058:
            java.lang.Class r5 = java.lang.Integer.TYPE     // Catch:{ JSONException -> 0x01be }
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)     // Catch:{ JSONException -> 0x01be }
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r5)     // Catch:{ JSONException -> 0x01be }
            if (r5 == 0) goto L_0x006f
            int r0 = r0.getInt(r2)     // Catch:{ JSONException -> 0x01be }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ JSONException -> 0x01be }
            org.json.JSONObject r0 = (org.json.JSONObject) r0     // Catch:{ JSONException -> 0x01be }
            goto L_0x00d9
        L_0x006f:
            java.lang.Class r5 = java.lang.Long.TYPE     // Catch:{ JSONException -> 0x01be }
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)     // Catch:{ JSONException -> 0x01be }
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r5)     // Catch:{ JSONException -> 0x01be }
            if (r5 == 0) goto L_0x0086
            long r2 = r0.getLong(r2)     // Catch:{ JSONException -> 0x01be }
            java.lang.Long r0 = java.lang.Long.valueOf(r2)     // Catch:{ JSONException -> 0x01be }
            org.json.JSONObject r0 = (org.json.JSONObject) r0     // Catch:{ JSONException -> 0x01be }
            goto L_0x00d9
        L_0x0086:
            java.lang.Class r5 = java.lang.Boolean.TYPE     // Catch:{ JSONException -> 0x01be }
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)     // Catch:{ JSONException -> 0x01be }
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r5)     // Catch:{ JSONException -> 0x01be }
            if (r5 == 0) goto L_0x009d
            boolean r0 = r0.getBoolean(r2)     // Catch:{ JSONException -> 0x01be }
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)     // Catch:{ JSONException -> 0x01be }
            org.json.JSONObject r0 = (org.json.JSONObject) r0     // Catch:{ JSONException -> 0x01be }
            goto L_0x00d9
        L_0x009d:
            java.lang.Class<org.json.JSONArray> r5 = org.json.JSONArray.class
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)     // Catch:{ JSONException -> 0x01be }
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r5)     // Catch:{ JSONException -> 0x01be }
            if (r5 == 0) goto L_0x00b8
            org.json.JSONArray r0 = r0.getJSONArray(r2)     // Catch:{ JSONException -> 0x01be }
            if (r0 == 0) goto L_0x00b2
            org.json.JSONObject r0 = (org.json.JSONObject) r0     // Catch:{ JSONException -> 0x01be }
            goto L_0x00d9
        L_0x00b2:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException     // Catch:{ JSONException -> 0x01be }
            r0.<init>(r6)     // Catch:{ JSONException -> 0x01be }
            throw r0     // Catch:{ JSONException -> 0x01be }
        L_0x00b8:
            java.lang.Class<org.json.JSONObject> r5 = org.json.JSONObject.class
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)     // Catch:{ JSONException -> 0x01be }
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r5)     // Catch:{ JSONException -> 0x01be }
            if (r3 == 0) goto L_0x00d1
            org.json.JSONObject r0 = r0.getJSONObject(r2)     // Catch:{ JSONException -> 0x01be }
            if (r0 == 0) goto L_0x00cb
            goto L_0x00d9
        L_0x00cb:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException     // Catch:{ JSONException -> 0x01be }
            r0.<init>(r6)     // Catch:{ JSONException -> 0x01be }
            throw r0     // Catch:{ JSONException -> 0x01be }
        L_0x00d1:
            java.lang.Object r0 = r0.get(r2)     // Catch:{ JSONException -> 0x01be }
            if (r0 == 0) goto L_0x01b8
            org.json.JSONObject r0 = (org.json.JSONObject) r0     // Catch:{ JSONException -> 0x01be }
        L_0x00d9:
            if (r0 == 0) goto L_0x01b2
            java.lang.String r2 = "dev"
            boolean r3 = r0.has(r2)     // Catch:{ JSONException -> 0x01be }
            if (r3 != 0) goto L_0x00e5
            goto L_0x01a5
        L_0x00e5:
            java.lang.Class<java.lang.Boolean> r3 = java.lang.Boolean.class
            kotlin.reflect.KClass r3 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)     // Catch:{ JSONException -> 0x01be }
            java.lang.Class<java.lang.String> r4 = java.lang.String.class
            kotlin.reflect.KClass r4 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r4)     // Catch:{ JSONException -> 0x01be }
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r4)     // Catch:{ JSONException -> 0x01be }
            java.lang.String r5 = "null cannot be cast to non-null type kotlin.Boolean"
            if (r4 == 0) goto L_0x010a
            java.lang.String r0 = r0.getString(r2)     // Catch:{ JSONException -> 0x01be }
            if (r0 == 0) goto L_0x0104
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ JSONException -> 0x01be }
        L_0x0101:
            r4 = r0
            goto L_0x01a5
        L_0x0104:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException     // Catch:{ JSONException -> 0x01be }
            r0.<init>(r5)     // Catch:{ JSONException -> 0x01be }
            throw r0     // Catch:{ JSONException -> 0x01be }
        L_0x010a:
            java.lang.Class r4 = java.lang.Double.TYPE     // Catch:{ JSONException -> 0x01be }
            kotlin.reflect.KClass r4 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r4)     // Catch:{ JSONException -> 0x01be }
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r4)     // Catch:{ JSONException -> 0x01be }
            if (r4 == 0) goto L_0x0121
            double r2 = r0.getDouble(r2)     // Catch:{ JSONException -> 0x01be }
            java.lang.Double r0 = java.lang.Double.valueOf(r2)     // Catch:{ JSONException -> 0x01be }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ JSONException -> 0x01be }
            goto L_0x0101
        L_0x0121:
            java.lang.Class r4 = java.lang.Integer.TYPE     // Catch:{ JSONException -> 0x01be }
            kotlin.reflect.KClass r4 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r4)     // Catch:{ JSONException -> 0x01be }
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r4)     // Catch:{ JSONException -> 0x01be }
            if (r4 == 0) goto L_0x0138
            int r0 = r0.getInt(r2)     // Catch:{ JSONException -> 0x01be }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ JSONException -> 0x01be }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ JSONException -> 0x01be }
            goto L_0x0101
        L_0x0138:
            java.lang.Class r4 = java.lang.Long.TYPE     // Catch:{ JSONException -> 0x01be }
            kotlin.reflect.KClass r4 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r4)     // Catch:{ JSONException -> 0x01be }
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r4)     // Catch:{ JSONException -> 0x01be }
            if (r4 == 0) goto L_0x014f
            long r2 = r0.getLong(r2)     // Catch:{ JSONException -> 0x01be }
            java.lang.Long r0 = java.lang.Long.valueOf(r2)     // Catch:{ JSONException -> 0x01be }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ JSONException -> 0x01be }
            goto L_0x0101
        L_0x014f:
            java.lang.Class r4 = java.lang.Boolean.TYPE     // Catch:{ JSONException -> 0x01be }
            kotlin.reflect.KClass r4 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r4)     // Catch:{ JSONException -> 0x01be }
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r4)     // Catch:{ JSONException -> 0x01be }
            if (r4 == 0) goto L_0x0164
            boolean r0 = r0.getBoolean(r2)     // Catch:{ JSONException -> 0x01be }
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)     // Catch:{ JSONException -> 0x01be }
            goto L_0x0101
        L_0x0164:
            java.lang.Class<org.json.JSONArray> r4 = org.json.JSONArray.class
            kotlin.reflect.KClass r4 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r4)     // Catch:{ JSONException -> 0x01be }
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r4)     // Catch:{ JSONException -> 0x01be }
            if (r4 == 0) goto L_0x017f
            org.json.JSONArray r0 = r0.getJSONArray(r2)     // Catch:{ JSONException -> 0x01be }
            if (r0 == 0) goto L_0x0179
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ JSONException -> 0x01be }
            goto L_0x0101
        L_0x0179:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException     // Catch:{ JSONException -> 0x01be }
            r0.<init>(r5)     // Catch:{ JSONException -> 0x01be }
            throw r0     // Catch:{ JSONException -> 0x01be }
        L_0x017f:
            java.lang.Class<org.json.JSONObject> r4 = org.json.JSONObject.class
            kotlin.reflect.KClass r4 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r4)     // Catch:{ JSONException -> 0x01be }
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r4)     // Catch:{ JSONException -> 0x01be }
            if (r3 == 0) goto L_0x019b
            org.json.JSONObject r0 = r0.getJSONObject(r2)     // Catch:{ JSONException -> 0x01be }
            if (r0 == 0) goto L_0x0195
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ JSONException -> 0x01be }
            goto L_0x0101
        L_0x0195:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException     // Catch:{ JSONException -> 0x01be }
            r0.<init>(r5)     // Catch:{ JSONException -> 0x01be }
            throw r0     // Catch:{ JSONException -> 0x01be }
        L_0x019b:
            java.lang.Object r0 = r0.get(r2)     // Catch:{ JSONException -> 0x01be }
            if (r0 == 0) goto L_0x01ac
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ JSONException -> 0x01be }
            goto L_0x0101
        L_0x01a5:
            if (r4 == 0) goto L_0x01b2
            boolean r0 = r4.booleanValue()     // Catch:{ JSONException -> 0x01be }
            goto L_0x01b3
        L_0x01ac:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException     // Catch:{ JSONException -> 0x01be }
            r0.<init>(r5)     // Catch:{ JSONException -> 0x01be }
            throw r0     // Catch:{ JSONException -> 0x01be }
        L_0x01b2:
            r0 = r1
        L_0x01b3:
            if (r0 == 0) goto L_0x01be
            r0 = 1
            r1 = r0
            goto L_0x01be
        L_0x01b8:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException     // Catch:{ JSONException -> 0x01be }
            r0.<init>(r6)     // Catch:{ JSONException -> 0x01be }
            throw r0     // Catch:{ JSONException -> 0x01be }
        L_0x01be:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.manifests.core.Manifest.isDevelopmentMode():boolean");
    }

    public final boolean isDevelopmentSilentLaunch() {
        JSONObject jSONObject;
        Boolean bool;
        JSONObject expoGoConfigRootObject = getExpoGoConfigRootObject();
        if (expoGoConfigRootObject == null) {
            return false;
        }
        Boolean bool2 = null;
        if (!expoGoConfigRootObject.has("developmentClient")) {
            jSONObject = null;
        } else {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(JSONObject.class);
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
                String string = expoGoConfigRootObject.getString("developmentClient");
                if (string != null) {
                    jSONObject = (JSONObject) string;
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                }
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                jSONObject = (JSONObject) Double.valueOf(expoGoConfigRootObject.getDouble("developmentClient"));
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                jSONObject = (JSONObject) Integer.valueOf(expoGoConfigRootObject.getInt("developmentClient"));
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                jSONObject = (JSONObject) Long.valueOf(expoGoConfigRootObject.getLong("developmentClient"));
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                jSONObject = (JSONObject) Boolean.valueOf(expoGoConfigRootObject.getBoolean("developmentClient"));
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = expoGoConfigRootObject.getJSONArray("developmentClient");
                if (jSONArray != null) {
                    jSONObject = (JSONObject) jSONArray;
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                }
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                jSONObject = expoGoConfigRootObject.getJSONObject("developmentClient");
                if (jSONObject == null) {
                    throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                }
            } else {
                Object obj = expoGoConfigRootObject.get("developmentClient");
                if (obj != null) {
                    jSONObject = (JSONObject) obj;
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                }
            }
        }
        if (jSONObject == null) {
            return false;
        }
        if (jSONObject.has("silentLaunch")) {
            KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Boolean.class);
            if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
                String string2 = jSONObject.getString("silentLaunch");
                if (string2 != null) {
                    bool = (Boolean) string2;
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Boolean");
                }
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                bool = (Boolean) Double.valueOf(jSONObject.getDouble("silentLaunch"));
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                bool = (Boolean) Integer.valueOf(jSONObject.getInt("silentLaunch"));
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                bool = (Boolean) Long.valueOf(jSONObject.getLong("silentLaunch"));
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                bool = Boolean.valueOf(jSONObject.getBoolean("silentLaunch"));
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray2 = jSONObject.getJSONArray("silentLaunch");
                if (jSONArray2 != null) {
                    bool = (Boolean) jSONArray2;
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Boolean");
                }
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("silentLaunch");
                if (jSONObject2 != null) {
                    bool = (Boolean) jSONObject2;
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Boolean");
                }
            } else {
                Object obj2 = jSONObject.get("silentLaunch");
                if (obj2 != null) {
                    bool = (Boolean) obj2;
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Boolean");
                }
            }
            bool2 = bool;
        }
        if (bool2 != null) {
            return bool2.booleanValue();
        }
        return false;
    }

    public final boolean isUsingDeveloperTool() {
        JSONObject jSONObject;
        JSONObject expoGoConfigRootObject = getExpoGoConfigRootObject();
        if (expoGoConfigRootObject == null) {
            return false;
        }
        if (!expoGoConfigRootObject.has("developer")) {
            jSONObject = null;
        } else {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(JSONObject.class);
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
                String string = expoGoConfigRootObject.getString("developer");
                if (string != null) {
                    jSONObject = (JSONObject) string;
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                }
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                jSONObject = (JSONObject) Double.valueOf(expoGoConfigRootObject.getDouble("developer"));
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                jSONObject = (JSONObject) Integer.valueOf(expoGoConfigRootObject.getInt("developer"));
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                jSONObject = (JSONObject) Long.valueOf(expoGoConfigRootObject.getLong("developer"));
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                jSONObject = (JSONObject) Boolean.valueOf(expoGoConfigRootObject.getBoolean("developer"));
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = expoGoConfigRootObject.getJSONArray("developer");
                if (jSONArray != null) {
                    jSONObject = (JSONObject) jSONArray;
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                }
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                jSONObject = expoGoConfigRootObject.getJSONObject("developer");
                if (jSONObject == null) {
                    throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                }
            } else {
                Object obj = expoGoConfigRootObject.get("developer");
                if (obj != null) {
                    jSONObject = (JSONObject) obj;
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                }
            }
        }
        if (jSONObject != null) {
            return jSONObject.has("tool");
        }
        return false;
    }

    public final String getDebuggerHost() {
        JSONObject expoGoConfigRootObject = getExpoGoConfigRootObject();
        Intrinsics.checkNotNull(expoGoConfigRootObject);
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
        if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
            String string = expoGoConfigRootObject.getString("debuggerHost");
            if (string != null) {
                return string;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            return (String) Double.valueOf(expoGoConfigRootObject.getDouble("debuggerHost"));
        } else {
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                return (String) Integer.valueOf(expoGoConfigRootObject.getInt("debuggerHost"));
            }
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                return (String) Long.valueOf(expoGoConfigRootObject.getLong("debuggerHost"));
            }
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                return (String) Boolean.valueOf(expoGoConfigRootObject.getBoolean("debuggerHost"));
            }
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = expoGoConfigRootObject.getJSONArray("debuggerHost");
                if (jSONArray != null) {
                    return (String) jSONArray;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                JSONObject jSONObject = expoGoConfigRootObject.getJSONObject("debuggerHost");
                if (jSONObject != null) {
                    return (String) jSONObject;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            } else {
                Object obj = expoGoConfigRootObject.get("debuggerHost");
                if (obj != null) {
                    return (String) obj;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
        }
    }

    public final String getMainModuleName() {
        JSONObject expoGoConfigRootObject = getExpoGoConfigRootObject();
        Intrinsics.checkNotNull(expoGoConfigRootObject);
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
        if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
            String string = expoGoConfigRootObject.getString("mainModuleName");
            if (string != null) {
                return string;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            return (String) Double.valueOf(expoGoConfigRootObject.getDouble("mainModuleName"));
        } else {
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                return (String) Integer.valueOf(expoGoConfigRootObject.getInt("mainModuleName"));
            }
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                return (String) Long.valueOf(expoGoConfigRootObject.getLong("mainModuleName"));
            }
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                return (String) Boolean.valueOf(expoGoConfigRootObject.getBoolean("mainModuleName"));
            }
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = expoGoConfigRootObject.getJSONArray("mainModuleName");
                if (jSONArray != null) {
                    return (String) jSONArray;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                JSONObject jSONObject = expoGoConfigRootObject.getJSONObject("mainModuleName");
                if (jSONObject != null) {
                    return (String) jSONObject;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            } else {
                Object obj = expoGoConfigRootObject.get("mainModuleName");
                if (obj != null) {
                    return (String) obj;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
        }
    }

    public final String getHostUri() {
        String str;
        JSONObject expoClientConfigRootObject = getExpoClientConfigRootObject();
        if (expoClientConfigRootObject == null || !expoClientConfigRootObject.has("hostUri")) {
            return null;
        }
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
        if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
            str = expoClientConfigRootObject.getString("hostUri");
            if (str == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            str = (String) Double.valueOf(expoClientConfigRootObject.getDouble("hostUri"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
            str = (String) Integer.valueOf(expoClientConfigRootObject.getInt("hostUri"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
            str = (String) Long.valueOf(expoClientConfigRootObject.getLong("hostUri"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
            str = (String) Boolean.valueOf(expoClientConfigRootObject.getBoolean("hostUri"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONArray.class))) {
            JSONArray jSONArray = expoClientConfigRootObject.getJSONArray("hostUri");
            if (jSONArray != null) {
                str = (String) jSONArray;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONObject.class))) {
            JSONObject jSONObject = expoClientConfigRootObject.getJSONObject("hostUri");
            if (jSONObject != null) {
                str = (String) jSONObject;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
        } else {
            Object obj = expoClientConfigRootObject.get("hostUri");
            if (obj != null) {
                str = (String) obj;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
        }
        return str;
    }

    public final boolean isVerified() {
        Boolean bool;
        JSONObject jSONObject = this.json;
        if (!jSONObject.has("isVerified")) {
            bool = null;
        } else {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Boolean.class);
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
                String string = jSONObject.getString("isVerified");
                if (string != null) {
                    bool = (Boolean) string;
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Boolean");
                }
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                bool = (Boolean) Double.valueOf(jSONObject.getDouble("isVerified"));
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                bool = (Boolean) Integer.valueOf(jSONObject.getInt("isVerified"));
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                bool = (Boolean) Long.valueOf(jSONObject.getLong("isVerified"));
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                bool = Boolean.valueOf(jSONObject.getBoolean("isVerified"));
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = jSONObject.getJSONArray("isVerified");
                if (jSONArray != null) {
                    bool = (Boolean) jSONArray;
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Boolean");
                }
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("isVerified");
                if (jSONObject2 != null) {
                    bool = (Boolean) jSONObject2;
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Boolean");
                }
            } else {
                Object obj = jSONObject.get("isVerified");
                if (obj != null) {
                    bool = (Boolean) obj;
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Boolean");
                }
            }
        }
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    public final String getName() {
        String str;
        JSONObject expoClientConfigRootObject = getExpoClientConfigRootObject();
        if (expoClientConfigRootObject == null || !expoClientConfigRootObject.has("name")) {
            return null;
        }
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
        if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
            str = expoClientConfigRootObject.getString("name");
            if (str == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            str = (String) Double.valueOf(expoClientConfigRootObject.getDouble("name"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
            str = (String) Integer.valueOf(expoClientConfigRootObject.getInt("name"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
            str = (String) Long.valueOf(expoClientConfigRootObject.getLong("name"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
            str = (String) Boolean.valueOf(expoClientConfigRootObject.getBoolean("name"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONArray.class))) {
            JSONArray jSONArray = expoClientConfigRootObject.getJSONArray("name");
            if (jSONArray != null) {
                str = (String) jSONArray;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONObject.class))) {
            JSONObject jSONObject = expoClientConfigRootObject.getJSONObject("name");
            if (jSONObject != null) {
                str = (String) jSONObject;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
        } else {
            Object obj = expoClientConfigRootObject.get("name");
            if (obj != null) {
                str = (String) obj;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
        }
        return str;
    }

    public final String getVersion() {
        String str;
        JSONObject expoClientConfigRootObject = getExpoClientConfigRootObject();
        if (expoClientConfigRootObject == null || !expoClientConfigRootObject.has("version")) {
            return null;
        }
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
        if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
            str = expoClientConfigRootObject.getString("version");
            if (str == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            str = (String) Double.valueOf(expoClientConfigRootObject.getDouble("version"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
            str = (String) Integer.valueOf(expoClientConfigRootObject.getInt("version"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
            str = (String) Long.valueOf(expoClientConfigRootObject.getLong("version"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
            str = (String) Boolean.valueOf(expoClientConfigRootObject.getBoolean("version"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONArray.class))) {
            JSONArray jSONArray = expoClientConfigRootObject.getJSONArray("version");
            if (jSONArray != null) {
                str = (String) jSONArray;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONObject.class))) {
            JSONObject jSONObject = expoClientConfigRootObject.getJSONObject("version");
            if (jSONObject != null) {
                str = (String) jSONObject;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
        } else {
            Object obj = expoClientConfigRootObject.get("version");
            if (obj != null) {
                str = (String) obj;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
        }
        return str;
    }

    public final JSONObject getUpdatesInfo() {
        JSONObject jSONObject;
        JSONObject expoClientConfigRootObject = getExpoClientConfigRootObject();
        if (expoClientConfigRootObject == null || !expoClientConfigRootObject.has("updates")) {
            return null;
        }
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(JSONObject.class);
        if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
            String string = expoClientConfigRootObject.getString("updates");
            if (string != null) {
                jSONObject = (JSONObject) string;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
            }
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            jSONObject = (JSONObject) Double.valueOf(expoClientConfigRootObject.getDouble("updates"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
            jSONObject = (JSONObject) Integer.valueOf(expoClientConfigRootObject.getInt("updates"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
            jSONObject = (JSONObject) Long.valueOf(expoClientConfigRootObject.getLong("updates"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
            jSONObject = (JSONObject) Boolean.valueOf(expoClientConfigRootObject.getBoolean("updates"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONArray.class))) {
            JSONArray jSONArray = expoClientConfigRootObject.getJSONArray("updates");
            if (jSONArray != null) {
                jSONObject = (JSONObject) jSONArray;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
            }
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONObject.class))) {
            jSONObject = expoClientConfigRootObject.getJSONObject("updates");
            if (jSONObject == null) {
                throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
            }
        } else {
            Object obj = expoClientConfigRootObject.get("updates");
            if (obj != null) {
                jSONObject = (JSONObject) obj;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
            }
        }
        return jSONObject;
    }

    public final String getPrimaryColor() {
        String str;
        JSONObject expoClientConfigRootObject = getExpoClientConfigRootObject();
        if (expoClientConfigRootObject == null || !expoClientConfigRootObject.has("primaryColor")) {
            return null;
        }
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
        if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
            str = expoClientConfigRootObject.getString("primaryColor");
            if (str == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            str = (String) Double.valueOf(expoClientConfigRootObject.getDouble("primaryColor"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
            str = (String) Integer.valueOf(expoClientConfigRootObject.getInt("primaryColor"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
            str = (String) Long.valueOf(expoClientConfigRootObject.getLong("primaryColor"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
            str = (String) Boolean.valueOf(expoClientConfigRootObject.getBoolean("primaryColor"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONArray.class))) {
            JSONArray jSONArray = expoClientConfigRootObject.getJSONArray("primaryColor");
            if (jSONArray != null) {
                str = (String) jSONArray;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONObject.class))) {
            JSONObject jSONObject = expoClientConfigRootObject.getJSONObject("primaryColor");
            if (jSONObject != null) {
                str = (String) jSONObject;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
        } else {
            Object obj = expoClientConfigRootObject.get("primaryColor");
            if (obj != null) {
                str = (String) obj;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
        }
        return str;
    }

    public final String getOrientation() {
        String str;
        JSONObject expoClientConfigRootObject = getExpoClientConfigRootObject();
        if (expoClientConfigRootObject == null || !expoClientConfigRootObject.has(Device.JsonKeys.ORIENTATION)) {
            return null;
        }
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
        if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
            str = expoClientConfigRootObject.getString(Device.JsonKeys.ORIENTATION);
            if (str == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            str = (String) Double.valueOf(expoClientConfigRootObject.getDouble(Device.JsonKeys.ORIENTATION));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
            str = (String) Integer.valueOf(expoClientConfigRootObject.getInt(Device.JsonKeys.ORIENTATION));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
            str = (String) Long.valueOf(expoClientConfigRootObject.getLong(Device.JsonKeys.ORIENTATION));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
            str = (String) Boolean.valueOf(expoClientConfigRootObject.getBoolean(Device.JsonKeys.ORIENTATION));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONArray.class))) {
            JSONArray jSONArray = expoClientConfigRootObject.getJSONArray(Device.JsonKeys.ORIENTATION);
            if (jSONArray != null) {
                str = (String) jSONArray;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONObject.class))) {
            JSONObject jSONObject = expoClientConfigRootObject.getJSONObject(Device.JsonKeys.ORIENTATION);
            if (jSONObject != null) {
                str = (String) jSONObject;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
        } else {
            Object obj = expoClientConfigRootObject.get(Device.JsonKeys.ORIENTATION);
            if (obj != null) {
                str = (String) obj;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
        }
        return str;
    }

    public final String getAndroidKeyboardLayoutMode() {
        JSONObject jSONObject;
        String str;
        JSONObject expoClientConfigRootObject = getExpoClientConfigRootObject();
        if (expoClientConfigRootObject == null) {
            return null;
        }
        if (!expoClientConfigRootObject.has(DeviceInfo.OS_NAME)) {
            jSONObject = null;
        } else {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(JSONObject.class);
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
                String string = expoClientConfigRootObject.getString(DeviceInfo.OS_NAME);
                if (string != null) {
                    jSONObject = (JSONObject) string;
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                }
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                jSONObject = (JSONObject) Double.valueOf(expoClientConfigRootObject.getDouble(DeviceInfo.OS_NAME));
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                jSONObject = (JSONObject) Integer.valueOf(expoClientConfigRootObject.getInt(DeviceInfo.OS_NAME));
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                jSONObject = (JSONObject) Long.valueOf(expoClientConfigRootObject.getLong(DeviceInfo.OS_NAME));
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                jSONObject = (JSONObject) Boolean.valueOf(expoClientConfigRootObject.getBoolean(DeviceInfo.OS_NAME));
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = expoClientConfigRootObject.getJSONArray(DeviceInfo.OS_NAME);
                if (jSONArray != null) {
                    jSONObject = (JSONObject) jSONArray;
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                }
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                jSONObject = expoClientConfigRootObject.getJSONObject(DeviceInfo.OS_NAME);
                if (jSONObject == null) {
                    throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                }
            } else {
                Object obj = expoClientConfigRootObject.get(DeviceInfo.OS_NAME);
                if (obj != null) {
                    jSONObject = (JSONObject) obj;
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                }
            }
        }
        if (jSONObject == null || !jSONObject.has("softwareKeyboardLayoutMode")) {
            return null;
        }
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(String.class);
        if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
            str = jSONObject.getString("softwareKeyboardLayoutMode");
            if (str == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            str = (String) Double.valueOf(jSONObject.getDouble("softwareKeyboardLayoutMode"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
            str = (String) Integer.valueOf(jSONObject.getInt("softwareKeyboardLayoutMode"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
            str = (String) Long.valueOf(jSONObject.getLong("softwareKeyboardLayoutMode"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
            str = (String) Boolean.valueOf(jSONObject.getBoolean("softwareKeyboardLayoutMode"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(JSONArray.class))) {
            JSONArray jSONArray2 = jSONObject.getJSONArray("softwareKeyboardLayoutMode");
            if (jSONArray2 != null) {
                str = (String) jSONArray2;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(JSONObject.class))) {
            JSONObject jSONObject2 = jSONObject.getJSONObject("softwareKeyboardLayoutMode");
            if (jSONObject2 != null) {
                str = (String) jSONObject2;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
        } else {
            Object obj2 = jSONObject.get("softwareKeyboardLayoutMode");
            if (obj2 != null) {
                str = (String) obj2;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
        }
        return str;
    }

    public final String getAndroidUserInterfaceStyle() {
        String str;
        JSONObject jSONObject;
        JSONObject expoClientConfigRootObject = getExpoClientConfigRootObject();
        String str2 = null;
        if (expoClientConfigRootObject == null) {
            return null;
        }
        try {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(JSONObject.class);
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
                String string = expoClientConfigRootObject.getString(DeviceInfo.OS_NAME);
                if (string != null) {
                    jSONObject = (JSONObject) string;
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                }
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                jSONObject = (JSONObject) Double.valueOf(expoClientConfigRootObject.getDouble(DeviceInfo.OS_NAME));
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                jSONObject = (JSONObject) Integer.valueOf(expoClientConfigRootObject.getInt(DeviceInfo.OS_NAME));
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                jSONObject = (JSONObject) Long.valueOf(expoClientConfigRootObject.getLong(DeviceInfo.OS_NAME));
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                jSONObject = (JSONObject) Boolean.valueOf(expoClientConfigRootObject.getBoolean(DeviceInfo.OS_NAME));
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = expoClientConfigRootObject.getJSONArray(DeviceInfo.OS_NAME);
                if (jSONArray != null) {
                    jSONObject = (JSONObject) jSONArray;
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                }
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                jSONObject = expoClientConfigRootObject.getJSONObject(DeviceInfo.OS_NAME);
                if (jSONObject == null) {
                    throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                }
            } else {
                Object obj = expoClientConfigRootObject.get(DeviceInfo.OS_NAME);
                if (obj != null) {
                    jSONObject = (JSONObject) obj;
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                }
            }
            KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(String.class);
            if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
                String string2 = jSONObject.getString("userInterfaceStyle");
                if (string2 != null) {
                    return string2;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                return (String) Double.valueOf(jSONObject.getDouble("userInterfaceStyle"));
            } else {
                if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                    return (String) Integer.valueOf(jSONObject.getInt("userInterfaceStyle"));
                }
                if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                    return (String) Long.valueOf(jSONObject.getLong("userInterfaceStyle"));
                }
                if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                    return (String) Boolean.valueOf(jSONObject.getBoolean("userInterfaceStyle"));
                }
                if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                    JSONArray jSONArray2 = jSONObject.getJSONArray("userInterfaceStyle");
                    if (jSONArray2 != null) {
                        return (String) jSONArray2;
                    }
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                } else if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("userInterfaceStyle");
                    if (jSONObject2 != null) {
                        return (String) jSONObject2;
                    }
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                } else {
                    Object obj2 = jSONObject.get("userInterfaceStyle");
                    if (obj2 != null) {
                        return (String) obj2;
                    }
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                }
            }
        } catch (JSONException unused) {
            if (expoClientConfigRootObject.has("userInterfaceStyle")) {
                KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(String.class);
                if (Intrinsics.areEqual((Object) orCreateKotlinClass3, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
                    str = expoClientConfigRootObject.getString("userInterfaceStyle");
                    if (str == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                    }
                } else if (Intrinsics.areEqual((Object) orCreateKotlinClass3, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                    str = (String) Double.valueOf(expoClientConfigRootObject.getDouble("userInterfaceStyle"));
                } else if (Intrinsics.areEqual((Object) orCreateKotlinClass3, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                    str = (String) Integer.valueOf(expoClientConfigRootObject.getInt("userInterfaceStyle"));
                } else if (Intrinsics.areEqual((Object) orCreateKotlinClass3, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                    str = (String) Long.valueOf(expoClientConfigRootObject.getLong("userInterfaceStyle"));
                } else if (Intrinsics.areEqual((Object) orCreateKotlinClass3, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                    str = (String) Boolean.valueOf(expoClientConfigRootObject.getBoolean("userInterfaceStyle"));
                } else if (Intrinsics.areEqual((Object) orCreateKotlinClass3, (Object) Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                    JSONArray jSONArray3 = expoClientConfigRootObject.getJSONArray("userInterfaceStyle");
                    if (jSONArray3 != null) {
                        str = (String) jSONArray3;
                    } else {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                    }
                } else if (Intrinsics.areEqual((Object) orCreateKotlinClass3, (Object) Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                    JSONObject jSONObject3 = expoClientConfigRootObject.getJSONObject("userInterfaceStyle");
                    if (jSONObject3 != null) {
                        str = (String) jSONObject3;
                    } else {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                    }
                } else {
                    Object obj3 = expoClientConfigRootObject.get("userInterfaceStyle");
                    if (obj3 != null) {
                        str = (String) obj3;
                    } else {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                    }
                }
                str2 = str;
            }
            return str2;
        }
    }

    public final JSONObject getAndroidStatusBarOptions() {
        JSONObject jSONObject;
        JSONObject expoClientConfigRootObject = getExpoClientConfigRootObject();
        if (expoClientConfigRootObject == null || !expoClientConfigRootObject.has("androidStatusBar")) {
            return null;
        }
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(JSONObject.class);
        if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
            String string = expoClientConfigRootObject.getString("androidStatusBar");
            if (string != null) {
                jSONObject = (JSONObject) string;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
            }
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            jSONObject = (JSONObject) Double.valueOf(expoClientConfigRootObject.getDouble("androidStatusBar"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
            jSONObject = (JSONObject) Integer.valueOf(expoClientConfigRootObject.getInt("androidStatusBar"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
            jSONObject = (JSONObject) Long.valueOf(expoClientConfigRootObject.getLong("androidStatusBar"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
            jSONObject = (JSONObject) Boolean.valueOf(expoClientConfigRootObject.getBoolean("androidStatusBar"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONArray.class))) {
            JSONArray jSONArray = expoClientConfigRootObject.getJSONArray("androidStatusBar");
            if (jSONArray != null) {
                jSONObject = (JSONObject) jSONArray;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
            }
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONObject.class))) {
            jSONObject = expoClientConfigRootObject.getJSONObject("androidStatusBar");
            if (jSONObject == null) {
                throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
            }
        } else {
            Object obj = expoClientConfigRootObject.get("androidStatusBar");
            if (obj != null) {
                jSONObject = (JSONObject) obj;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
            }
        }
        return jSONObject;
    }

    public final String getAndroidBackgroundColor() {
        String str;
        JSONObject jSONObject;
        JSONObject expoClientConfigRootObject = getExpoClientConfigRootObject();
        String str2 = null;
        if (expoClientConfigRootObject == null) {
            return null;
        }
        try {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(JSONObject.class);
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
                String string = expoClientConfigRootObject.getString(DeviceInfo.OS_NAME);
                if (string != null) {
                    jSONObject = (JSONObject) string;
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                }
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                jSONObject = (JSONObject) Double.valueOf(expoClientConfigRootObject.getDouble(DeviceInfo.OS_NAME));
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                jSONObject = (JSONObject) Integer.valueOf(expoClientConfigRootObject.getInt(DeviceInfo.OS_NAME));
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                jSONObject = (JSONObject) Long.valueOf(expoClientConfigRootObject.getLong(DeviceInfo.OS_NAME));
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                jSONObject = (JSONObject) Boolean.valueOf(expoClientConfigRootObject.getBoolean(DeviceInfo.OS_NAME));
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = expoClientConfigRootObject.getJSONArray(DeviceInfo.OS_NAME);
                if (jSONArray != null) {
                    jSONObject = (JSONObject) jSONArray;
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                }
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                jSONObject = expoClientConfigRootObject.getJSONObject(DeviceInfo.OS_NAME);
                if (jSONObject == null) {
                    throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                }
            } else {
                Object obj = expoClientConfigRootObject.get(DeviceInfo.OS_NAME);
                if (obj != null) {
                    jSONObject = (JSONObject) obj;
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                }
            }
            KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(String.class);
            if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
                String string2 = jSONObject.getString("backgroundColor");
                if (string2 != null) {
                    return string2;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                return (String) Double.valueOf(jSONObject.getDouble("backgroundColor"));
            } else {
                if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                    return (String) Integer.valueOf(jSONObject.getInt("backgroundColor"));
                }
                if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                    return (String) Long.valueOf(jSONObject.getLong("backgroundColor"));
                }
                if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                    return (String) Boolean.valueOf(jSONObject.getBoolean("backgroundColor"));
                }
                if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                    JSONArray jSONArray2 = jSONObject.getJSONArray("backgroundColor");
                    if (jSONArray2 != null) {
                        return (String) jSONArray2;
                    }
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                } else if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("backgroundColor");
                    if (jSONObject2 != null) {
                        return (String) jSONObject2;
                    }
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                } else {
                    Object obj2 = jSONObject.get("backgroundColor");
                    if (obj2 != null) {
                        return (String) obj2;
                    }
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                }
            }
        } catch (JSONException unused) {
            if (expoClientConfigRootObject.has("backgroundColor")) {
                KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(String.class);
                if (Intrinsics.areEqual((Object) orCreateKotlinClass3, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
                    str = expoClientConfigRootObject.getString("backgroundColor");
                    if (str == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                    }
                } else if (Intrinsics.areEqual((Object) orCreateKotlinClass3, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                    str = (String) Double.valueOf(expoClientConfigRootObject.getDouble("backgroundColor"));
                } else if (Intrinsics.areEqual((Object) orCreateKotlinClass3, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                    str = (String) Integer.valueOf(expoClientConfigRootObject.getInt("backgroundColor"));
                } else if (Intrinsics.areEqual((Object) orCreateKotlinClass3, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                    str = (String) Long.valueOf(expoClientConfigRootObject.getLong("backgroundColor"));
                } else if (Intrinsics.areEqual((Object) orCreateKotlinClass3, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                    str = (String) Boolean.valueOf(expoClientConfigRootObject.getBoolean("backgroundColor"));
                } else if (Intrinsics.areEqual((Object) orCreateKotlinClass3, (Object) Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                    JSONArray jSONArray3 = expoClientConfigRootObject.getJSONArray("backgroundColor");
                    if (jSONArray3 != null) {
                        str = (String) jSONArray3;
                    } else {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                    }
                } else if (Intrinsics.areEqual((Object) orCreateKotlinClass3, (Object) Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                    JSONObject jSONObject3 = expoClientConfigRootObject.getJSONObject("backgroundColor");
                    if (jSONObject3 != null) {
                        str = (String) jSONObject3;
                    } else {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                    }
                } else {
                    Object obj3 = expoClientConfigRootObject.get("backgroundColor");
                    if (obj3 != null) {
                        str = (String) obj3;
                    } else {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                    }
                }
                str2 = str;
            }
            return str2;
        }
    }

    public final JSONObject getAndroidNavigationBarOptions() {
        JSONObject jSONObject;
        JSONObject expoClientConfigRootObject = getExpoClientConfigRootObject();
        if (expoClientConfigRootObject == null || !expoClientConfigRootObject.has("androidNavigationBar")) {
            return null;
        }
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(JSONObject.class);
        if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
            String string = expoClientConfigRootObject.getString("androidNavigationBar");
            if (string != null) {
                jSONObject = (JSONObject) string;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
            }
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            jSONObject = (JSONObject) Double.valueOf(expoClientConfigRootObject.getDouble("androidNavigationBar"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
            jSONObject = (JSONObject) Integer.valueOf(expoClientConfigRootObject.getInt("androidNavigationBar"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
            jSONObject = (JSONObject) Long.valueOf(expoClientConfigRootObject.getLong("androidNavigationBar"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
            jSONObject = (JSONObject) Boolean.valueOf(expoClientConfigRootObject.getBoolean("androidNavigationBar"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONArray.class))) {
            JSONArray jSONArray = expoClientConfigRootObject.getJSONArray("androidNavigationBar");
            if (jSONArray != null) {
                jSONObject = (JSONObject) jSONArray;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
            }
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONObject.class))) {
            jSONObject = expoClientConfigRootObject.getJSONObject("androidNavigationBar");
            if (jSONObject == null) {
                throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
            }
        } else {
            Object obj = expoClientConfigRootObject.get("androidNavigationBar");
            if (obj != null) {
                jSONObject = (JSONObject) obj;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
            }
        }
        return jSONObject;
    }

    public final String getJsEngine() {
        return (String) this.jsEngine$delegate.getValue();
    }

    public final String getIconUrl() {
        String str;
        JSONObject expoClientConfigRootObject = getExpoClientConfigRootObject();
        if (expoClientConfigRootObject == null || !expoClientConfigRootObject.has("iconUrl")) {
            return null;
        }
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
        if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
            str = expoClientConfigRootObject.getString("iconUrl");
            if (str == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            str = (String) Double.valueOf(expoClientConfigRootObject.getDouble("iconUrl"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
            str = (String) Integer.valueOf(expoClientConfigRootObject.getInt("iconUrl"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
            str = (String) Long.valueOf(expoClientConfigRootObject.getLong("iconUrl"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
            str = (String) Boolean.valueOf(expoClientConfigRootObject.getBoolean("iconUrl"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONArray.class))) {
            JSONArray jSONArray = expoClientConfigRootObject.getJSONArray("iconUrl");
            if (jSONArray != null) {
                str = (String) jSONArray;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONObject.class))) {
            JSONObject jSONObject = expoClientConfigRootObject.getJSONObject("iconUrl");
            if (jSONObject != null) {
                str = (String) jSONObject;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
        } else {
            Object obj = expoClientConfigRootObject.get("iconUrl");
            if (obj != null) {
                str = (String) obj;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
        }
        return str;
    }

    public final JSONObject getNotificationPreferences() {
        JSONObject jSONObject;
        JSONObject expoClientConfigRootObject = getExpoClientConfigRootObject();
        if (expoClientConfigRootObject == null || !expoClientConfigRootObject.has(NotificationsService.NOTIFICATION_KEY)) {
            return null;
        }
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(JSONObject.class);
        if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
            String string = expoClientConfigRootObject.getString(NotificationsService.NOTIFICATION_KEY);
            if (string != null) {
                jSONObject = (JSONObject) string;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
            }
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            jSONObject = (JSONObject) Double.valueOf(expoClientConfigRootObject.getDouble(NotificationsService.NOTIFICATION_KEY));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
            jSONObject = (JSONObject) Integer.valueOf(expoClientConfigRootObject.getInt(NotificationsService.NOTIFICATION_KEY));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
            jSONObject = (JSONObject) Long.valueOf(expoClientConfigRootObject.getLong(NotificationsService.NOTIFICATION_KEY));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
            jSONObject = (JSONObject) Boolean.valueOf(expoClientConfigRootObject.getBoolean(NotificationsService.NOTIFICATION_KEY));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONArray.class))) {
            JSONArray jSONArray = expoClientConfigRootObject.getJSONArray(NotificationsService.NOTIFICATION_KEY);
            if (jSONArray != null) {
                jSONObject = (JSONObject) jSONArray;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
            }
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONObject.class))) {
            jSONObject = expoClientConfigRootObject.getJSONObject(NotificationsService.NOTIFICATION_KEY);
            if (jSONObject == null) {
                throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
            }
        } else {
            Object obj = expoClientConfigRootObject.get(NotificationsService.NOTIFICATION_KEY);
            if (obj != null) {
                jSONObject = (JSONObject) obj;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
            }
        }
        return jSONObject;
    }

    public final JSONObject getAndroidSplashInfo() {
        JSONObject jSONObject;
        JSONObject jSONObject2;
        JSONObject expoClientConfigRootObject = getExpoClientConfigRootObject();
        if (expoClientConfigRootObject == null) {
            return null;
        }
        if (!expoClientConfigRootObject.has(DeviceInfo.OS_NAME)) {
            jSONObject = null;
        } else {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(JSONObject.class);
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
                String string = expoClientConfigRootObject.getString(DeviceInfo.OS_NAME);
                if (string != null) {
                    jSONObject = (JSONObject) string;
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                }
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                jSONObject = (JSONObject) Double.valueOf(expoClientConfigRootObject.getDouble(DeviceInfo.OS_NAME));
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                jSONObject = (JSONObject) Integer.valueOf(expoClientConfigRootObject.getInt(DeviceInfo.OS_NAME));
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                jSONObject = (JSONObject) Long.valueOf(expoClientConfigRootObject.getLong(DeviceInfo.OS_NAME));
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                jSONObject = (JSONObject) Boolean.valueOf(expoClientConfigRootObject.getBoolean(DeviceInfo.OS_NAME));
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = expoClientConfigRootObject.getJSONArray(DeviceInfo.OS_NAME);
                if (jSONArray != null) {
                    jSONObject = (JSONObject) jSONArray;
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                }
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                jSONObject = expoClientConfigRootObject.getJSONObject(DeviceInfo.OS_NAME);
                if (jSONObject == null) {
                    throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                }
            } else {
                Object obj = expoClientConfigRootObject.get(DeviceInfo.OS_NAME);
                if (obj != null) {
                    jSONObject = (JSONObject) obj;
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                }
            }
        }
        if (jSONObject == null || !jSONObject.has("splash")) {
            return null;
        }
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(JSONObject.class);
        if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
            String string2 = jSONObject.getString("splash");
            if (string2 != null) {
                jSONObject2 = (JSONObject) string2;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
            }
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            jSONObject2 = (JSONObject) Double.valueOf(jSONObject.getDouble("splash"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
            jSONObject2 = (JSONObject) Integer.valueOf(jSONObject.getInt("splash"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
            jSONObject2 = (JSONObject) Long.valueOf(jSONObject.getLong("splash"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
            jSONObject2 = (JSONObject) Boolean.valueOf(jSONObject.getBoolean("splash"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(JSONArray.class))) {
            JSONArray jSONArray2 = jSONObject.getJSONArray("splash");
            if (jSONArray2 != null) {
                jSONObject2 = (JSONObject) jSONArray2;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
            }
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(JSONObject.class))) {
            jSONObject2 = jSONObject.getJSONObject("splash");
            if (jSONObject2 == null) {
                throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
            }
        } else {
            Object obj2 = jSONObject.get("splash");
            if (obj2 != null) {
                jSONObject2 = (JSONObject) obj2;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
            }
        }
        return jSONObject2;
    }

    public final JSONObject getRootSplashInfo() {
        JSONObject jSONObject;
        JSONObject expoClientConfigRootObject = getExpoClientConfigRootObject();
        if (expoClientConfigRootObject == null || !expoClientConfigRootObject.has("splash")) {
            return null;
        }
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(JSONObject.class);
        if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
            String string = expoClientConfigRootObject.getString("splash");
            if (string != null) {
                jSONObject = (JSONObject) string;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
            }
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            jSONObject = (JSONObject) Double.valueOf(expoClientConfigRootObject.getDouble("splash"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
            jSONObject = (JSONObject) Integer.valueOf(expoClientConfigRootObject.getInt("splash"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
            jSONObject = (JSONObject) Long.valueOf(expoClientConfigRootObject.getLong("splash"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
            jSONObject = (JSONObject) Boolean.valueOf(expoClientConfigRootObject.getBoolean("splash"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONArray.class))) {
            JSONArray jSONArray = expoClientConfigRootObject.getJSONArray("splash");
            if (jSONArray != null) {
                jSONObject = (JSONObject) jSONArray;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
            }
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONObject.class))) {
            jSONObject = expoClientConfigRootObject.getJSONObject("splash");
            if (jSONObject == null) {
                throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
            }
        } else {
            Object obj = expoClientConfigRootObject.get("splash");
            if (obj != null) {
                jSONObject = (JSONObject) obj;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
            }
        }
        return jSONObject;
    }

    public final String getAndroidGoogleServicesFile() {
        JSONObject jSONObject;
        String str;
        JSONObject expoClientConfigRootObject = getExpoClientConfigRootObject();
        if (expoClientConfigRootObject == null) {
            return null;
        }
        if (!expoClientConfigRootObject.has(DeviceInfo.OS_NAME)) {
            jSONObject = null;
        } else {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(JSONObject.class);
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
                String string = expoClientConfigRootObject.getString(DeviceInfo.OS_NAME);
                if (string != null) {
                    jSONObject = (JSONObject) string;
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                }
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                jSONObject = (JSONObject) Double.valueOf(expoClientConfigRootObject.getDouble(DeviceInfo.OS_NAME));
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                jSONObject = (JSONObject) Integer.valueOf(expoClientConfigRootObject.getInt(DeviceInfo.OS_NAME));
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                jSONObject = (JSONObject) Long.valueOf(expoClientConfigRootObject.getLong(DeviceInfo.OS_NAME));
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                jSONObject = (JSONObject) Boolean.valueOf(expoClientConfigRootObject.getBoolean(DeviceInfo.OS_NAME));
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = expoClientConfigRootObject.getJSONArray(DeviceInfo.OS_NAME);
                if (jSONArray != null) {
                    jSONObject = (JSONObject) jSONArray;
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                }
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                jSONObject = expoClientConfigRootObject.getJSONObject(DeviceInfo.OS_NAME);
                if (jSONObject == null) {
                    throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                }
            } else {
                Object obj = expoClientConfigRootObject.get(DeviceInfo.OS_NAME);
                if (obj != null) {
                    jSONObject = (JSONObject) obj;
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                }
            }
        }
        if (jSONObject == null || !jSONObject.has("googleServicesFile")) {
            return null;
        }
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(String.class);
        if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
            str = jSONObject.getString("googleServicesFile");
            if (str == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            str = (String) Double.valueOf(jSONObject.getDouble("googleServicesFile"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
            str = (String) Integer.valueOf(jSONObject.getInt("googleServicesFile"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
            str = (String) Long.valueOf(jSONObject.getLong("googleServicesFile"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
            str = (String) Boolean.valueOf(jSONObject.getBoolean("googleServicesFile"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(JSONArray.class))) {
            JSONArray jSONArray2 = jSONObject.getJSONArray("googleServicesFile");
            if (jSONArray2 != null) {
                str = (String) jSONArray2;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(JSONObject.class))) {
            JSONObject jSONObject2 = jSONObject.getJSONObject("googleServicesFile");
            if (jSONObject2 != null) {
                str = (String) jSONObject2;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
        } else {
            Object obj2 = jSONObject.get("googleServicesFile");
            if (obj2 != null) {
                str = (String) obj2;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
        }
        return str;
    }

    public final String getAndroidPackageName() {
        JSONObject jSONObject;
        String str;
        JSONObject expoClientConfigRootObject = getExpoClientConfigRootObject();
        if (expoClientConfigRootObject == null) {
            return null;
        }
        if (!expoClientConfigRootObject.has(DeviceInfo.OS_NAME)) {
            jSONObject = null;
        } else {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(JSONObject.class);
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
                String string = expoClientConfigRootObject.getString(DeviceInfo.OS_NAME);
                if (string != null) {
                    jSONObject = (JSONObject) string;
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                }
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                jSONObject = (JSONObject) Double.valueOf(expoClientConfigRootObject.getDouble(DeviceInfo.OS_NAME));
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                jSONObject = (JSONObject) Integer.valueOf(expoClientConfigRootObject.getInt(DeviceInfo.OS_NAME));
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                jSONObject = (JSONObject) Long.valueOf(expoClientConfigRootObject.getLong(DeviceInfo.OS_NAME));
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                jSONObject = (JSONObject) Boolean.valueOf(expoClientConfigRootObject.getBoolean(DeviceInfo.OS_NAME));
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = expoClientConfigRootObject.getJSONArray(DeviceInfo.OS_NAME);
                if (jSONArray != null) {
                    jSONObject = (JSONObject) jSONArray;
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                }
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                jSONObject = expoClientConfigRootObject.getJSONObject(DeviceInfo.OS_NAME);
                if (jSONObject == null) {
                    throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                }
            } else {
                Object obj = expoClientConfigRootObject.get(DeviceInfo.OS_NAME);
                if (obj != null) {
                    jSONObject = (JSONObject) obj;
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                }
            }
        }
        if (jSONObject == null || !jSONObject.has(RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME)) {
            return null;
        }
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(String.class);
        if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
            str = jSONObject.getString(RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME);
            if (str == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            str = (String) Double.valueOf(jSONObject.getDouble(RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
            str = (String) Integer.valueOf(jSONObject.getInt(RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
            str = (String) Long.valueOf(jSONObject.getLong(RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
            str = (String) Boolean.valueOf(jSONObject.getBoolean(RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(JSONArray.class))) {
            JSONArray jSONArray2 = jSONObject.getJSONArray(RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME);
            if (jSONArray2 != null) {
                str = (String) jSONArray2;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(JSONObject.class))) {
            JSONObject jSONObject2 = jSONObject.getJSONObject(RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME);
            if (jSONObject2 != null) {
                str = (String) jSONObject2;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
        } else {
            Object obj2 = jSONObject.get(RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME);
            if (obj2 != null) {
                str = (String) obj2;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
        }
        return str;
    }

    public final boolean shouldUseNextNotificationsApi() {
        JSONObject jSONObject;
        Boolean bool;
        JSONObject expoClientConfigRootObject = getExpoClientConfigRootObject();
        if (expoClientConfigRootObject == null) {
            return false;
        }
        Boolean bool2 = null;
        if (!expoClientConfigRootObject.has(DeviceInfo.OS_NAME)) {
            jSONObject = null;
        } else {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(JSONObject.class);
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
                String string = expoClientConfigRootObject.getString(DeviceInfo.OS_NAME);
                if (string != null) {
                    jSONObject = (JSONObject) string;
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                }
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                jSONObject = (JSONObject) Double.valueOf(expoClientConfigRootObject.getDouble(DeviceInfo.OS_NAME));
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                jSONObject = (JSONObject) Integer.valueOf(expoClientConfigRootObject.getInt(DeviceInfo.OS_NAME));
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                jSONObject = (JSONObject) Long.valueOf(expoClientConfigRootObject.getLong(DeviceInfo.OS_NAME));
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                jSONObject = (JSONObject) Boolean.valueOf(expoClientConfigRootObject.getBoolean(DeviceInfo.OS_NAME));
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = expoClientConfigRootObject.getJSONArray(DeviceInfo.OS_NAME);
                if (jSONArray != null) {
                    jSONObject = (JSONObject) jSONArray;
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                }
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                jSONObject = expoClientConfigRootObject.getJSONObject(DeviceInfo.OS_NAME);
                if (jSONObject == null) {
                    throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                }
            } else {
                Object obj = expoClientConfigRootObject.get(DeviceInfo.OS_NAME);
                if (obj != null) {
                    jSONObject = (JSONObject) obj;
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                }
            }
        }
        if (jSONObject == null) {
            return false;
        }
        if (jSONObject.has("useNextNotificationsApi")) {
            KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Boolean.class);
            if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
                String string2 = jSONObject.getString("useNextNotificationsApi");
                if (string2 != null) {
                    bool = (Boolean) string2;
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Boolean");
                }
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                bool = (Boolean) Double.valueOf(jSONObject.getDouble("useNextNotificationsApi"));
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                bool = (Boolean) Integer.valueOf(jSONObject.getInt("useNextNotificationsApi"));
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                bool = (Boolean) Long.valueOf(jSONObject.getLong("useNextNotificationsApi"));
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                bool = Boolean.valueOf(jSONObject.getBoolean("useNextNotificationsApi"));
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray2 = jSONObject.getJSONArray("useNextNotificationsApi");
                if (jSONArray2 != null) {
                    bool = (Boolean) jSONArray2;
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Boolean");
                }
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("useNextNotificationsApi");
                if (jSONObject2 != null) {
                    bool = (Boolean) jSONObject2;
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Boolean");
                }
            } else {
                Object obj2 = jSONObject.get("useNextNotificationsApi");
                if (obj2 != null) {
                    bool = (Boolean) obj2;
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Boolean");
                }
            }
            bool2 = bool;
        }
        if (bool2 != null) {
            return bool2.booleanValue();
        }
        return false;
    }

    public final String getFacebookAppId() throws JSONException {
        JSONObject expoClientConfigRootObject = getExpoClientConfigRootObject();
        Intrinsics.checkNotNull(expoClientConfigRootObject);
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
        if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
            String string = expoClientConfigRootObject.getString("facebookAppId");
            if (string != null) {
                return string;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            return (String) Double.valueOf(expoClientConfigRootObject.getDouble("facebookAppId"));
        } else {
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                return (String) Integer.valueOf(expoClientConfigRootObject.getInt("facebookAppId"));
            }
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                return (String) Long.valueOf(expoClientConfigRootObject.getLong("facebookAppId"));
            }
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                return (String) Boolean.valueOf(expoClientConfigRootObject.getBoolean("facebookAppId"));
            }
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = expoClientConfigRootObject.getJSONArray("facebookAppId");
                if (jSONArray != null) {
                    return (String) jSONArray;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                JSONObject jSONObject = expoClientConfigRootObject.getJSONObject("facebookAppId");
                if (jSONObject != null) {
                    return (String) jSONObject;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            } else {
                Object obj = expoClientConfigRootObject.get("facebookAppId");
                if (obj != null) {
                    return (String) obj;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
        }
    }

    public final String getFacebookApplicationName() throws JSONException {
        JSONObject expoClientConfigRootObject = getExpoClientConfigRootObject();
        Intrinsics.checkNotNull(expoClientConfigRootObject);
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
        if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
            String string = expoClientConfigRootObject.getString("facebookDisplayName");
            if (string != null) {
                return string;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            return (String) Double.valueOf(expoClientConfigRootObject.getDouble("facebookDisplayName"));
        } else {
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                return (String) Integer.valueOf(expoClientConfigRootObject.getInt("facebookDisplayName"));
            }
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                return (String) Long.valueOf(expoClientConfigRootObject.getLong("facebookDisplayName"));
            }
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                return (String) Boolean.valueOf(expoClientConfigRootObject.getBoolean("facebookDisplayName"));
            }
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = expoClientConfigRootObject.getJSONArray("facebookDisplayName");
                if (jSONArray != null) {
                    return (String) jSONArray;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                JSONObject jSONObject = expoClientConfigRootObject.getJSONObject("facebookDisplayName");
                if (jSONObject != null) {
                    return (String) jSONObject;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            } else {
                Object obj = expoClientConfigRootObject.get("facebookDisplayName");
                if (obj != null) {
                    return (String) obj;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
        }
    }

    public final boolean getFacebookAutoInitEnabled() throws JSONException {
        Boolean bool;
        JSONObject expoClientConfigRootObject = getExpoClientConfigRootObject();
        Intrinsics.checkNotNull(expoClientConfigRootObject);
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Boolean.class);
        if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
            String string = expoClientConfigRootObject.getString("facebookAutoInitEnabled");
            if (string != null) {
                bool = (Boolean) string;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Boolean");
            }
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            bool = (Boolean) Double.valueOf(expoClientConfigRootObject.getDouble("facebookAutoInitEnabled"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
            bool = (Boolean) Integer.valueOf(expoClientConfigRootObject.getInt("facebookAutoInitEnabled"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
            bool = (Boolean) Long.valueOf(expoClientConfigRootObject.getLong("facebookAutoInitEnabled"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
            bool = Boolean.valueOf(expoClientConfigRootObject.getBoolean("facebookAutoInitEnabled"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONArray.class))) {
            JSONArray jSONArray = expoClientConfigRootObject.getJSONArray("facebookAutoInitEnabled");
            if (jSONArray != null) {
                bool = (Boolean) jSONArray;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Boolean");
            }
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONObject.class))) {
            JSONObject jSONObject = expoClientConfigRootObject.getJSONObject("facebookAutoInitEnabled");
            if (jSONObject != null) {
                bool = (Boolean) jSONObject;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Boolean");
            }
        } else {
            Object obj = expoClientConfigRootObject.get("facebookAutoInitEnabled");
            if (obj != null) {
                bool = (Boolean) obj;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Boolean");
            }
        }
        return bool.booleanValue();
    }

    public final Map<String, Object> getPluginProperties(String str) throws JSONException, IllegalArgumentException {
        JSONArray jSONArray;
        List<PluginType> fromRawArrayValue;
        Object obj;
        Pair<String, Map<String, Object>> plugin;
        Intrinsics.checkNotNullParameter(str, RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME);
        JSONObject expoClientConfigRootObject = getExpoClientConfigRootObject();
        if (expoClientConfigRootObject != null) {
            if (!expoClientConfigRootObject.has("plugins")) {
                jSONArray = null;
            } else {
                KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(JSONArray.class);
                if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
                    String string = expoClientConfigRootObject.getString("plugins");
                    if (string != null) {
                        jSONArray = (JSONArray) string;
                    } else {
                        throw new NullPointerException("null cannot be cast to non-null type org.json.JSONArray");
                    }
                } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                    jSONArray = (JSONArray) Double.valueOf(expoClientConfigRootObject.getDouble("plugins"));
                } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                    jSONArray = (JSONArray) Integer.valueOf(expoClientConfigRootObject.getInt("plugins"));
                } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                    jSONArray = (JSONArray) Long.valueOf(expoClientConfigRootObject.getLong("plugins"));
                } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                    jSONArray = (JSONArray) Boolean.valueOf(expoClientConfigRootObject.getBoolean("plugins"));
                } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                    jSONArray = expoClientConfigRootObject.getJSONArray("plugins");
                    if (jSONArray == null) {
                        throw new NullPointerException("null cannot be cast to non-null type org.json.JSONArray");
                    }
                } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                    JSONObject jSONObject = expoClientConfigRootObject.getJSONObject("plugins");
                    if (jSONObject != null) {
                        jSONArray = (JSONArray) jSONObject;
                    } else {
                        throw new NullPointerException("null cannot be cast to non-null type org.json.JSONArray");
                    }
                } else {
                    Object obj2 = expoClientConfigRootObject.get("plugins");
                    if (obj2 != null) {
                        jSONArray = (JSONArray) obj2;
                    } else {
                        throw new NullPointerException("null cannot be cast to non-null type org.json.JSONArray");
                    }
                }
            }
            if (jSONArray == null || (fromRawArrayValue = PluginType.Companion.fromRawArrayValue(jSONArray)) == null) {
                return null;
            }
            Collection arrayList = new ArrayList();
            for (Object next : fromRawArrayValue) {
                if (next instanceof PluginType.WithProps) {
                    arrayList.add(next);
                }
            }
            Iterator it = ((List) arrayList).iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                if (Intrinsics.areEqual((Object) ((PluginType.WithProps) obj).getPlugin().getFirst(), (Object) str)) {
                    break;
                }
            }
            PluginType.WithProps withProps = (PluginType.WithProps) obj;
            if (withProps == null || (plugin = withProps.getPlugin()) == null) {
                return null;
            }
            return plugin.getSecond();
        }
        return null;
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lexpo/modules/manifests/core/Manifest$Companion;", "", "()V", "fromManifestJson", "Lexpo/modules/manifests/core/Manifest;", "manifestJson", "Lorg/json/JSONObject;", "expo-manifests_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: Manifest.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Manifest fromManifestJson(JSONObject jSONObject) {
            Intrinsics.checkNotNullParameter(jSONObject, "manifestJson");
            if (jSONObject.has("releaseId")) {
                return new LegacyManifest(jSONObject);
            }
            if (jSONObject.has(TtmlNode.TAG_METADATA)) {
                return new NewManifest(jSONObject);
            }
            return new BareManifest(jSONObject);
        }
    }
}
