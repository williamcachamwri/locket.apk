package expo.modules.manifests.core;

import kotlin.Metadata;
import org.json.JSONException;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lexpo/modules/manifests/core/InternalJSONMutator;", "", "updateJSON", "", "json", "Lorg/json/JSONObject;", "expo-manifests_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: Manifest.kt */
public interface InternalJSONMutator {
    void updateJSON(JSONObject jSONObject) throws JSONException;
}
