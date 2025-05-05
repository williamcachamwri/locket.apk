package expo.modules.manifests.core;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001*\u00020\u0004¨\u0006\u0005"}, d2 = {"toMap", "", "", "", "Lorg/json/JSONObject;", "expo-manifests_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: JSONObjectExtension.kt */
public final class JSONObjectExtensionKt {
    public static final Map<String, Object> toMap(JSONObject jSONObject) {
        Intrinsics.checkNotNullParameter(jSONObject, "<this>");
        Iterator<String> keys = jSONObject.keys();
        Intrinsics.checkNotNullExpressionValue(keys, "keys(...)");
        Sequence<T> asSequence = SequencesKt.asSequence(keys);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (T next : asSequence) {
            Map map = linkedHashMap;
            Object obj = jSONObject.get((String) next);
            if (obj instanceof JSONObject) {
                obj = toMap((JSONObject) obj);
            }
            map.put(next, obj);
        }
        return linkedHashMap;
    }
}
