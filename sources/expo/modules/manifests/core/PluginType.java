package expo.modules.manifests.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u0000 \u00032\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lexpo/modules/manifests/core/PluginType;", "", "()V", "Companion", "WithProps", "WithoutProps", "Lexpo/modules/manifests/core/PluginType$WithProps;", "Lexpo/modules/manifests/core/PluginType$WithoutProps;", "expo-manifests_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: Manifest.kt */
public abstract class PluginType {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    public /* synthetic */ PluginType(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B)\u0012\"\u0010\u0002\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00060\u00050\u0003j\u0002`\u0007¢\u0006\u0002\u0010\bJ%\u0010\u000b\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00060\u00050\u0003j\u0002`\u0007HÆ\u0003J/\u0010\f\u001a\u00020\u00002$\b\u0002\u0010\u0002\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00060\u00050\u0003j\u0002`\u0007HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0006HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0004HÖ\u0001R-\u0010\u0002\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00060\u00050\u0003j\u0002`\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0013"}, d2 = {"Lexpo/modules/manifests/core/PluginType$WithProps;", "Lexpo/modules/manifests/core/PluginType;", "plugin", "Lkotlin/Pair;", "", "", "", "Lexpo/modules/manifests/core/PluginWithProps;", "(Lkotlin/Pair;)V", "getPlugin", "()Lkotlin/Pair;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "expo-manifests_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: Manifest.kt */
    public static final class WithProps extends PluginType {
        private final Pair<String, Map<String, Object>> plugin;

        public static /* synthetic */ WithProps copy$default(WithProps withProps, Pair<String, Map<String, Object>> pair, int i, Object obj) {
            if ((i & 1) != 0) {
                pair = withProps.plugin;
            }
            return withProps.copy(pair);
        }

        public final Pair<String, Map<String, Object>> component1() {
            return this.plugin;
        }

        public final WithProps copy(Pair<String, ? extends Map<String, ? extends Object>> pair) {
            Intrinsics.checkNotNullParameter(pair, "plugin");
            return new WithProps(pair);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof WithProps) && Intrinsics.areEqual((Object) this.plugin, (Object) ((WithProps) obj).plugin);
        }

        public int hashCode() {
            return this.plugin.hashCode();
        }

        public String toString() {
            return "WithProps(plugin=" + this.plugin + ")";
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public WithProps(Pair<String, ? extends Map<String, ? extends Object>> pair) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(pair, "plugin");
            this.plugin = pair;
        }

        public final Pair<String, Map<String, Object>> getPlugin() {
            return this.plugin;
        }
    }

    private PluginType() {
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0011\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¢\u0006\u0002\u0010\u0005J\r\u0010\b\u001a\u00060\u0003j\u0002`\u0004HÆ\u0003J\u0017\u0010\t\u001a\u00020\u00002\f\b\u0002\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0015\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lexpo/modules/manifests/core/PluginType$WithoutProps;", "Lexpo/modules/manifests/core/PluginType;", "plugin", "", "Lexpo/modules/manifests/core/PluginWithoutProps;", "(Ljava/lang/String;)V", "getPlugin", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "expo-manifests_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: Manifest.kt */
    public static final class WithoutProps extends PluginType {
        private final String plugin;

        public static /* synthetic */ WithoutProps copy$default(WithoutProps withoutProps, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = withoutProps.plugin;
            }
            return withoutProps.copy(str);
        }

        public final String component1() {
            return this.plugin;
        }

        public final WithoutProps copy(String str) {
            Intrinsics.checkNotNullParameter(str, "plugin");
            return new WithoutProps(str);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof WithoutProps) && Intrinsics.areEqual((Object) this.plugin, (Object) ((WithoutProps) obj).plugin);
        }

        public int hashCode() {
            return this.plugin.hashCode();
        }

        public String toString() {
            return "WithoutProps(plugin=" + this.plugin + ")";
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public WithoutProps(String str) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "plugin");
            this.plugin = str;
        }

        public final String getPlugin() {
            return this.plugin;
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u0007J\u0012\u0010\b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0001H\u0002¨\u0006\t"}, d2 = {"Lexpo/modules/manifests/core/PluginType$Companion;", "", "()V", "fromRawArrayValue", "", "Lexpo/modules/manifests/core/PluginType;", "value", "Lorg/json/JSONArray;", "fromRawValue", "expo-manifests_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: Manifest.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        private final PluginType fromRawValue(Object obj) throws IllegalArgumentException {
            if (obj instanceof JSONArray) {
                JSONArray jSONArray = (JSONArray) obj;
                if (jSONArray.length() != 0) {
                    Object obj2 = jSONArray.get(0);
                    String str = obj2 instanceof String ? (String) obj2 : null;
                    if (str == null) {
                        return null;
                    }
                    if (jSONArray.length() != 2) {
                        return new WithoutProps(str);
                    }
                    Object obj3 = jSONArray.get(1);
                    JSONObject jSONObject = obj3 instanceof JSONObject ? (JSONObject) obj3 : null;
                    if (jSONObject == null) {
                        return null;
                    }
                    return new WithProps(TuplesKt.to(str, JSONObjectExtensionKt.toMap(jSONObject)));
                }
                throw new IllegalArgumentException("Value for (key = plugins) has incorrect type");
            } else if (obj instanceof String) {
                return new WithoutProps((String) obj);
            } else {
                throw new IllegalArgumentException("Value for (key = plugins) has incorrect type");
            }
        }

        public final List<PluginType> fromRawArrayValue(JSONArray jSONArray) throws IllegalArgumentException {
            Intrinsics.checkNotNullParameter(jSONArray, "value");
            List<PluginType> arrayList = new ArrayList<>();
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                Companion companion = PluginType.Companion;
                Object obj = jSONArray.get(i);
                Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
                PluginType fromRawValue = companion.fromRawValue(obj);
                if (fromRawValue != null) {
                    arrayList.add(fromRawValue);
                }
            }
            return arrayList;
        }
    }
}
