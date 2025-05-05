package expo.modules.manifests.core;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006J\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lexpo/modules/manifests/core/BareManifest;", "Lexpo/modules/manifests/core/BaseLegacyManifest;", "json", "Lorg/json/JSONObject;", "(Lorg/json/JSONObject;)V", "getCommitTimeLong", "", "getID", "", "expo-manifests_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: BareManifest.kt */
public final class BareManifest extends BaseLegacyManifest {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BareManifest(JSONObject jSONObject) {
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

    public final long getCommitTimeLong() throws JSONException {
        Long l;
        JSONObject json = getJson();
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Long.class);
        if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
            String string = json.getString("commitTime");
            if (string != null) {
                l = (Long) string;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Long");
            }
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            l = (Long) Double.valueOf(json.getDouble("commitTime"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
            l = (Long) Integer.valueOf(json.getInt("commitTime"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
            l = Long.valueOf(json.getLong("commitTime"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
            l = (Long) Boolean.valueOf(json.getBoolean("commitTime"));
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONArray.class))) {
            JSONArray jSONArray = json.getJSONArray("commitTime");
            if (jSONArray != null) {
                l = (Long) jSONArray;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Long");
            }
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(JSONObject.class))) {
            JSONObject jSONObject = json.getJSONObject("commitTime");
            if (jSONObject != null) {
                l = (Long) jSONObject;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Long");
            }
        } else {
            Object obj = json.get("commitTime");
            if (obj != null) {
                l = (Long) obj;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Long");
            }
        }
        return l.longValue();
    }
}
