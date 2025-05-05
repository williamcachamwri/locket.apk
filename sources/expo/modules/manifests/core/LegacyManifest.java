package expo.modules.manifests.core;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\n\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006J\b\u0010\b\u001a\u0004\u0018\u00010\tJ\u0006\u0010\n\u001a\u00020\u0006J\b\u0010\u000b\u001a\u0004\u0018\u00010\u0006¨\u0006\f"}, d2 = {"Lexpo/modules/manifests/core/LegacyManifest;", "Lexpo/modules/manifests/core/BaseLegacyManifest;", "json", "Lorg/json/JSONObject;", "(Lorg/json/JSONObject;)V", "getAssetUrlOverride", "", "getBundleKey", "getBundledAssets", "Lorg/json/JSONArray;", "getReleaseId", "getRuntimeVersion", "expo-manifests_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: LegacyManifest.kt */
public class LegacyManifest extends BaseLegacyManifest {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LegacyManifest(JSONObject jSONObject) {
        super(jSONObject);
        Intrinsics.checkNotNullParameter(jSONObject, "json");
    }

    public final String getBundleKey() throws JSONException {
        JSONObject json = getJson();
        if (!json.has("bundleKey")) {
            return null;
        }
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
        if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
            String string = json.getString("bundleKey");
            if (string != null) {
                return string;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            return (String) Double.valueOf(json.getDouble("bundleKey"));
        } else {
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                return (String) Integer.valueOf(json.getInt("bundleKey"));
            }
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                return (String) Long.valueOf(json.getLong("bundleKey"));
            }
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                return (String) Boolean.valueOf(json.getBoolean("bundleKey"));
            }
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = json.getJSONArray("bundleKey");
                if (jSONArray != null) {
                    return (String) jSONArray;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                JSONObject jSONObject = json.getJSONObject("bundleKey");
                if (jSONObject != null) {
                    return (String) jSONObject;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            } else {
                Object obj = json.get("bundleKey");
                if (obj != null) {
                    return (String) obj;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
        }
    }

    public final String getReleaseId() throws JSONException {
        JSONObject json = getJson();
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
        if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
            String string = json.getString("releaseId");
            if (string != null) {
                return string;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            return (String) Double.valueOf(json.getDouble("releaseId"));
        } else {
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                return (String) Integer.valueOf(json.getInt("releaseId"));
            }
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                return (String) Long.valueOf(json.getLong("releaseId"));
            }
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                return (String) Boolean.valueOf(json.getBoolean("releaseId"));
            }
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = json.getJSONArray("releaseId");
                if (jSONArray != null) {
                    return (String) jSONArray;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                JSONObject jSONObject = json.getJSONObject("releaseId");
                if (jSONObject != null) {
                    return (String) jSONObject;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            } else {
                Object obj = json.get("releaseId");
                if (obj != null) {
                    return (String) obj;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
        }
    }

    public final String getRuntimeVersion() {
        JSONObject json = getJson();
        if (!json.has("runtimeVersion")) {
            return null;
        }
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
        if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
            String string = json.getString("runtimeVersion");
            if (string != null) {
                return string;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            return (String) Double.valueOf(json.getDouble("runtimeVersion"));
        } else {
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                return (String) Integer.valueOf(json.getInt("runtimeVersion"));
            }
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                return (String) Long.valueOf(json.getLong("runtimeVersion"));
            }
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                return (String) Boolean.valueOf(json.getBoolean("runtimeVersion"));
            }
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = json.getJSONArray("runtimeVersion");
                if (jSONArray != null) {
                    return (String) jSONArray;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                JSONObject jSONObject = json.getJSONObject("runtimeVersion");
                if (jSONObject != null) {
                    return (String) jSONObject;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            } else {
                Object obj = json.get("runtimeVersion");
                if (obj != null) {
                    return (String) obj;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
        }
    }

    public final JSONArray getBundledAssets() throws JSONException {
        JSONObject json = getJson();
        if (!json.has("bundledAssets")) {
            return null;
        }
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(JSONArray.class);
        if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
            String string = json.getString("bundledAssets");
            if (string != null) {
                return (JSONArray) string;
            }
            throw new NullPointerException("null cannot be cast to non-null type org.json.JSONArray");
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            return (JSONArray) Double.valueOf(json.getDouble("bundledAssets"));
        } else {
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                return (JSONArray) Integer.valueOf(json.getInt("bundledAssets"));
            }
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                return (JSONArray) Long.valueOf(json.getLong("bundledAssets"));
            }
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                return (JSONArray) Boolean.valueOf(json.getBoolean("bundledAssets"));
            }
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = json.getJSONArray("bundledAssets");
                if (jSONArray != null) {
                    return jSONArray;
                }
                throw new NullPointerException("null cannot be cast to non-null type org.json.JSONArray");
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                JSONObject jSONObject = json.getJSONObject("bundledAssets");
                if (jSONObject != null) {
                    return (JSONArray) jSONObject;
                }
                throw new NullPointerException("null cannot be cast to non-null type org.json.JSONArray");
            } else {
                Object obj = json.get("bundledAssets");
                if (obj != null) {
                    return (JSONArray) obj;
                }
                throw new NullPointerException("null cannot be cast to non-null type org.json.JSONArray");
            }
        }
    }

    public String getAssetUrlOverride() {
        JSONObject json = getJson();
        if (!json.has("assetUrlOverride")) {
            return null;
        }
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
        if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
            String string = json.getString("assetUrlOverride");
            if (string != null) {
                return string;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            return (String) Double.valueOf(json.getDouble("assetUrlOverride"));
        } else {
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                return (String) Integer.valueOf(json.getInt("assetUrlOverride"));
            }
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                return (String) Long.valueOf(json.getLong("assetUrlOverride"));
            }
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                return (String) Boolean.valueOf(json.getBoolean("assetUrlOverride"));
            }
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = json.getJSONArray("assetUrlOverride");
                if (jSONArray != null) {
                    return (String) jSONArray;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                JSONObject jSONObject = json.getJSONObject("assetUrlOverride");
                if (jSONObject != null) {
                    return (String) jSONObject;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            } else {
                Object obj = json.get("assetUrlOverride");
                if (obj != null) {
                    return (String) obj;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
        }
    }
}
