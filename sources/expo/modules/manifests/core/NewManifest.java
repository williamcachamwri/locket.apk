package expo.modules.manifests.core;

import io.sentry.SentryBaseEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\n\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\n\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\b\u0010\t\u001a\u00020\u0006H\u0016J\u0006\u0010\n\u001a\u00020\u0006J\n\u0010\u000b\u001a\u0004\u0018\u00010\u0006H\u0016J\n\u0010\f\u001a\u0004\u0018\u00010\u0003H\u0016J\n\u0010\r\u001a\u0004\u0018\u00010\u0003H\u0016J\n\u0010\u000e\u001a\u0004\u0018\u00010\u0006H\u0016J\n\u0010\u000f\u001a\u0004\u0018\u00010\u0003H\u0002J\u0006\u0010\u0010\u001a\u00020\u0006J\u0006\u0010\u0011\u001a\u00020\u0003J\u0006\u0010\u0012\u001a\u00020\u0006J\n\u0010\u0013\u001a\u0004\u0018\u00010\u0006H\u0003J\b\u0010\u0014\u001a\u00020\u0006H\u0016J\n\u0010\u0015\u001a\u0004\u0018\u00010\u0006H\u0016J\n\u0010\u0016\u001a\u0004\u0018\u00010\u0006H\u0016¨\u0006\u0017"}, d2 = {"Lexpo/modules/manifests/core/NewManifest;", "Lexpo/modules/manifests/core/Manifest;", "json", "Lorg/json/JSONObject;", "(Lorg/json/JSONObject;)V", "getAppKey", "", "getAssets", "Lorg/json/JSONArray;", "getBundleURL", "getCreatedAt", "getEASProjectID", "getExpoClientConfigRootObject", "getExpoGoConfigRootObject", "getExpoGoSDKVersion", "getExtra", "getID", "getLaunchAsset", "getRuntimeVersion", "getSDKVersionFromRuntimeVersion", "getScopeKey", "getSlug", "getStableLegacyID", "expo-manifests_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: NewManifest.kt */
public final class NewManifest extends Manifest {
    public String getAppKey() {
        return null;
    }

    public String getSlug() {
        return null;
    }

    public String getStableLegacyID() {
        return null;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NewManifest(JSONObject jSONObject) {
        super(jSONObject);
        Intrinsics.checkNotNullParameter(jSONObject, "json");
    }

    public final String getID() throws JSONException {
        JSONObject json = getJson();
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
        if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
            String string = json.getString("id");
            if (string != null) {
                return string;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            return (String) Double.valueOf(json.getDouble("id"));
        } else {
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                return (String) Integer.valueOf(json.getInt("id"));
            }
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                return (String) Long.valueOf(json.getLong("id"));
            }
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                return (String) Boolean.valueOf(json.getBoolean("id"));
            }
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = json.getJSONArray("id");
                if (jSONArray != null) {
                    return (String) jSONArray;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                JSONObject jSONObject = json.getJSONObject("id");
                if (jSONObject != null) {
                    return (String) jSONObject;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            } else {
                Object obj = json.get("id");
                if (obj != null) {
                    return (String) obj;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
        }
    }

    public String getScopeKey() throws JSONException {
        JSONObject jSONObject;
        JSONObject json = getJson();
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(JSONObject.class);
        if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
            String string = json.getString(SentryBaseEvent.JsonKeys.EXTRA);
            if (string != null) {
                jSONObject = (JSONObject) string;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
            }
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            jSONObject = (JSONObject) Double.valueOf(json.getDouble(SentryBaseEvent.JsonKeys.EXTRA));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
            jSONObject = (JSONObject) Integer.valueOf(json.getInt(SentryBaseEvent.JsonKeys.EXTRA));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
            jSONObject = (JSONObject) Long.valueOf(json.getLong(SentryBaseEvent.JsonKeys.EXTRA));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
            jSONObject = (JSONObject) Boolean.valueOf(json.getBoolean(SentryBaseEvent.JsonKeys.EXTRA));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONArray.class))) {
            JSONArray jSONArray = json.getJSONArray(SentryBaseEvent.JsonKeys.EXTRA);
            if (jSONArray != null) {
                jSONObject = (JSONObject) jSONArray;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
            }
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONObject.class))) {
            jSONObject = json.getJSONObject(SentryBaseEvent.JsonKeys.EXTRA);
            if (jSONObject == null) {
                throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
            }
        } else {
            Object obj = json.get(SentryBaseEvent.JsonKeys.EXTRA);
            if (obj != null) {
                jSONObject = (JSONObject) obj;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
            }
        }
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(String.class);
        if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
            String string2 = jSONObject.getString("scopeKey");
            if (string2 != null) {
                return string2;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            return (String) Double.valueOf(jSONObject.getDouble("scopeKey"));
        } else {
            if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                return (String) Integer.valueOf(jSONObject.getInt("scopeKey"));
            }
            if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                return (String) Long.valueOf(jSONObject.getLong("scopeKey"));
            }
            if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                return (String) Boolean.valueOf(jSONObject.getBoolean("scopeKey"));
            }
            if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray2 = jSONObject.getJSONArray("scopeKey");
                if (jSONArray2 != null) {
                    return (String) jSONArray2;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("scopeKey");
                if (jSONObject2 != null) {
                    return (String) jSONObject2;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            } else {
                Object obj2 = jSONObject.get("scopeKey");
                if (obj2 != null) {
                    return (String) obj2;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
        }
    }

    public String getEASProjectID() {
        JSONObject jSONObject;
        String str;
        JSONObject extra = getExtra();
        if (extra != null) {
            if (!extra.has("eas")) {
                jSONObject = null;
            } else {
                KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(JSONObject.class);
                if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
                    String string = extra.getString("eas");
                    if (string != null) {
                        jSONObject = (JSONObject) string;
                    } else {
                        throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                    }
                } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                    jSONObject = (JSONObject) Double.valueOf(extra.getDouble("eas"));
                } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                    jSONObject = (JSONObject) Integer.valueOf(extra.getInt("eas"));
                } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                    jSONObject = (JSONObject) Long.valueOf(extra.getLong("eas"));
                } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                    jSONObject = (JSONObject) Boolean.valueOf(extra.getBoolean("eas"));
                } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                    JSONArray jSONArray = extra.getJSONArray("eas");
                    if (jSONArray != null) {
                        jSONObject = (JSONObject) jSONArray;
                    } else {
                        throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                    }
                } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                    jSONObject = extra.getJSONObject("eas");
                    if (jSONObject == null) {
                        throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                    }
                } else {
                    Object obj = extra.get("eas");
                    if (obj != null) {
                        jSONObject = (JSONObject) obj;
                    } else {
                        throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                    }
                }
            }
            if (jSONObject == null || !jSONObject.has("projectId")) {
                return null;
            }
            KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(String.class);
            if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
                str = jSONObject.getString("projectId");
                if (str == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                }
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                str = (String) Double.valueOf(jSONObject.getDouble("projectId"));
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                str = (String) Integer.valueOf(jSONObject.getInt("projectId"));
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                str = (String) Long.valueOf(jSONObject.getLong("projectId"));
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                str = (String) Boolean.valueOf(jSONObject.getBoolean("projectId"));
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray2 = jSONObject.getJSONArray("projectId");
                if (jSONArray2 != null) {
                    str = (String) jSONArray2;
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                }
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass2, (Object) Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("projectId");
                if (jSONObject2 != null) {
                    str = (String) jSONObject2;
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                }
            } else {
                Object obj2 = jSONObject.get("projectId");
                if (obj2 != null) {
                    str = (String) obj2;
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                }
            }
            return str;
        }
        return null;
    }

    public final String getRuntimeVersion() throws JSONException {
        JSONObject json = getJson();
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

    public String getBundleURL() throws JSONException {
        JSONObject launchAsset = getLaunchAsset();
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
        if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
            String string = launchAsset.getString("url");
            if (string != null) {
                return string;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            return (String) Double.valueOf(launchAsset.getDouble("url"));
        } else {
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                return (String) Integer.valueOf(launchAsset.getInt("url"));
            }
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                return (String) Long.valueOf(launchAsset.getLong("url"));
            }
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                return (String) Boolean.valueOf(launchAsset.getBoolean("url"));
            }
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = launchAsset.getJSONArray("url");
                if (jSONArray != null) {
                    return (String) jSONArray;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                JSONObject jSONObject = launchAsset.getJSONObject("url");
                if (jSONObject != null) {
                    return (String) jSONObject;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            } else {
                Object obj = launchAsset.get("url");
                if (obj != null) {
                    return (String) obj;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
        }
    }

    @Deprecated(message = "exposdk:... runtime version is deprecated")
    private final String getSDKVersionFromRuntimeVersion() {
        String runtimeVersion = getRuntimeVersion();
        if (Intrinsics.areEqual((Object) runtimeVersion, (Object) "exposdk:UNVERSIONED")) {
            return "UNVERSIONED";
        }
        Pattern compile = Pattern.compile("^exposdk:(\\d+\\.\\d+\\.\\d+)$");
        Intrinsics.checkNotNullExpressionValue(compile, "compile(...)");
        Matcher matcher = compile.matcher(runtimeVersion);
        Intrinsics.checkNotNullExpressionValue(matcher, "matcher(...)");
        if (!matcher.find()) {
            return null;
        }
        String group = matcher.group(1);
        Intrinsics.checkNotNull(group);
        return group;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r0 = r0.getString(com.google.firebase.remoteconfig.RemoteConfigConstants.RequestFieldKey.SDK_VERSION);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getExpoGoSDKVersion() {
        /*
            r2 = this;
            org.json.JSONObject r0 = r2.getExpoClientConfigRootObject()
            if (r0 == 0) goto L_0x000e
            java.lang.String r1 = "sdkVersion"
            java.lang.String r0 = r0.getString(r1)
            if (r0 != 0) goto L_0x0012
        L_0x000e:
            java.lang.String r0 = r2.getSDKVersionFromRuntimeVersion()
        L_0x0012:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.manifests.core.NewManifest.getExpoGoSDKVersion():java.lang.String");
    }

    public final JSONObject getLaunchAsset() throws JSONException {
        JSONObject json = getJson();
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(JSONObject.class);
        if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
            String string = json.getString("launchAsset");
            if (string != null) {
                return (JSONObject) string;
            }
            throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            return (JSONObject) Double.valueOf(json.getDouble("launchAsset"));
        } else {
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                return (JSONObject) Integer.valueOf(json.getInt("launchAsset"));
            }
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                return (JSONObject) Long.valueOf(json.getLong("launchAsset"));
            }
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                return (JSONObject) Boolean.valueOf(json.getBoolean("launchAsset"));
            }
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = json.getJSONArray("launchAsset");
                if (jSONArray != null) {
                    return (JSONObject) jSONArray;
                }
                throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                JSONObject jSONObject = json.getJSONObject("launchAsset");
                if (jSONObject != null) {
                    return jSONObject;
                }
                throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
            } else {
                Object obj = json.get("launchAsset");
                if (obj != null) {
                    return (JSONObject) obj;
                }
                throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
            }
        }
    }

    public JSONArray getAssets() {
        JSONObject json = getJson();
        if (!json.has("assets")) {
            return null;
        }
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(JSONArray.class);
        if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
            String string = json.getString("assets");
            if (string != null) {
                return (JSONArray) string;
            }
            throw new NullPointerException("null cannot be cast to non-null type org.json.JSONArray");
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            return (JSONArray) Double.valueOf(json.getDouble("assets"));
        } else {
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                return (JSONArray) Integer.valueOf(json.getInt("assets"));
            }
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                return (JSONArray) Long.valueOf(json.getLong("assets"));
            }
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                return (JSONArray) Boolean.valueOf(json.getBoolean("assets"));
            }
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = json.getJSONArray("assets");
                if (jSONArray != null) {
                    return jSONArray;
                }
                throw new NullPointerException("null cannot be cast to non-null type org.json.JSONArray");
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                JSONObject jSONObject = json.getJSONObject("assets");
                if (jSONObject != null) {
                    return (JSONArray) jSONObject;
                }
                throw new NullPointerException("null cannot be cast to non-null type org.json.JSONArray");
            } else {
                Object obj = json.get("assets");
                if (obj != null) {
                    return (JSONArray) obj;
                }
                throw new NullPointerException("null cannot be cast to non-null type org.json.JSONArray");
            }
        }
    }

    public final String getCreatedAt() throws JSONException {
        JSONObject json = getJson();
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
        if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
            String string = json.getString("createdAt");
            if (string != null) {
                return string;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            return (String) Double.valueOf(json.getDouble("createdAt"));
        } else {
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                return (String) Integer.valueOf(json.getInt("createdAt"));
            }
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                return (String) Long.valueOf(json.getLong("createdAt"));
            }
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                return (String) Boolean.valueOf(json.getBoolean("createdAt"));
            }
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = json.getJSONArray("createdAt");
                if (jSONArray != null) {
                    return (String) jSONArray;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                JSONObject jSONObject = json.getJSONObject("createdAt");
                if (jSONObject != null) {
                    return (String) jSONObject;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            } else {
                Object obj = json.get("createdAt");
                if (obj != null) {
                    return (String) obj;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
        }
    }

    public JSONObject getExpoGoConfigRootObject() {
        JSONObject jSONObject;
        JSONObject extra = getExtra();
        if (extra == null || !extra.has("expoGo")) {
            return null;
        }
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(JSONObject.class);
        if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
            String string = extra.getString("expoGo");
            if (string != null) {
                jSONObject = (JSONObject) string;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
            }
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            jSONObject = (JSONObject) Double.valueOf(extra.getDouble("expoGo"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
            jSONObject = (JSONObject) Integer.valueOf(extra.getInt("expoGo"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
            jSONObject = (JSONObject) Long.valueOf(extra.getLong("expoGo"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
            jSONObject = (JSONObject) Boolean.valueOf(extra.getBoolean("expoGo"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONArray.class))) {
            JSONArray jSONArray = extra.getJSONArray("expoGo");
            if (jSONArray != null) {
                jSONObject = (JSONObject) jSONArray;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
            }
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONObject.class))) {
            jSONObject = extra.getJSONObject("expoGo");
            if (jSONObject == null) {
                throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
            }
        } else {
            Object obj = extra.get("expoGo");
            if (obj != null) {
                jSONObject = (JSONObject) obj;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
            }
        }
        return jSONObject;
    }

    public JSONObject getExpoClientConfigRootObject() {
        JSONObject jSONObject;
        JSONObject extra = getExtra();
        if (extra == null || !extra.has("expoClient")) {
            return null;
        }
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(JSONObject.class);
        if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
            String string = extra.getString("expoClient");
            if (string != null) {
                jSONObject = (JSONObject) string;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
            }
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            jSONObject = (JSONObject) Double.valueOf(extra.getDouble("expoClient"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
            jSONObject = (JSONObject) Integer.valueOf(extra.getInt("expoClient"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
            jSONObject = (JSONObject) Long.valueOf(extra.getLong("expoClient"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
            jSONObject = (JSONObject) Boolean.valueOf(extra.getBoolean("expoClient"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONArray.class))) {
            JSONArray jSONArray = extra.getJSONArray("expoClient");
            if (jSONArray != null) {
                jSONObject = (JSONObject) jSONArray;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
            }
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONObject.class))) {
            jSONObject = extra.getJSONObject("expoClient");
            if (jSONObject == null) {
                throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
            }
        } else {
            Object obj = extra.get("expoClient");
            if (obj != null) {
                jSONObject = (JSONObject) obj;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
            }
        }
        return jSONObject;
    }

    private final JSONObject getExtra() {
        JSONObject json = getJson();
        if (!json.has(SentryBaseEvent.JsonKeys.EXTRA)) {
            return null;
        }
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(JSONObject.class);
        if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
            String string = json.getString(SentryBaseEvent.JsonKeys.EXTRA);
            if (string != null) {
                return (JSONObject) string;
            }
            throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            return (JSONObject) Double.valueOf(json.getDouble(SentryBaseEvent.JsonKeys.EXTRA));
        } else {
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                return (JSONObject) Integer.valueOf(json.getInt(SentryBaseEvent.JsonKeys.EXTRA));
            }
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                return (JSONObject) Long.valueOf(json.getLong(SentryBaseEvent.JsonKeys.EXTRA));
            }
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                return (JSONObject) Boolean.valueOf(json.getBoolean(SentryBaseEvent.JsonKeys.EXTRA));
            }
            if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = json.getJSONArray(SentryBaseEvent.JsonKeys.EXTRA);
                if (jSONArray != null) {
                    return (JSONObject) jSONArray;
                }
                throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
            } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                JSONObject jSONObject = json.getJSONObject(SentryBaseEvent.JsonKeys.EXTRA);
                if (jSONObject != null) {
                    return jSONObject;
                }
                throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
            } else {
                Object obj = json.get(SentryBaseEvent.JsonKeys.EXTRA);
                if (obj != null) {
                    return (JSONObject) obj;
                }
                throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
            }
        }
    }
}
