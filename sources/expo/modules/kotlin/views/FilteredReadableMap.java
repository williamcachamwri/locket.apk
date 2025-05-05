package expo.modules.kotlin.views;

import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import expo.modules.kotlin.FilteredIterator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010(\n\u0002\u0010&\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\u0010\u0006J\u0015\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\b\u0001\u0010\t\u001a\u00020\u0005H\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\b\u0001\u0010\t\u001a\u00020\u0005H\u0001J\u0013\u0010\f\u001a\u00020\r2\b\b\u0001\u0010\t\u001a\u00020\u0005H\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\b\u0001\u0010\t\u001a\u00020\u0005H\u0001J\u001a\u0010\u0010\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00130\u00120\u0011H\u0016J\u0013\u0010\u0014\u001a\u00020\u00152\b\b\u0001\u0010\t\u001a\u00020\u0005H\u0001J\u0015\u0010\u0016\u001a\u0004\u0018\u00010\u00012\b\b\u0001\u0010\t\u001a\u00020\u0005H\u0001J\u0015\u0010\u0017\u001a\u0004\u0018\u00010\u00052\b\b\u0001\u0010\t\u001a\u00020\u0005H\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\b\u0001\u0010\t\u001a\u00020\u0005H\u0001J\u0013\u0010\u001a\u001a\u00020\u000b2\b\b\u0001\u0010\t\u001a\u00020\u0005H\u0001J\u0013\u0010\u001b\u001a\u00020\u000b2\b\b\u0001\u0010\t\u001a\u00020\u0005H\u0001J\b\u0010\u001c\u001a\u00020\u001dH\u0016J%\u0010\u001e\u001a\u001e\u0012\f\u0012\n  *\u0004\u0018\u00010\u00050\u0005\u0012\f\u0012\n  *\u0004\u0018\u00010\u00130\u00130\u001fH\u0001R\u000e\u0010\u0002\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lexpo/modules/kotlin/views/FilteredReadableMap;", "Lcom/facebook/react/bridge/ReadableMap;", "backingMap", "filteredKeys", "", "", "(Lcom/facebook/react/bridge/ReadableMap;Ljava/util/List;)V", "getArray", "Lcom/facebook/react/bridge/ReadableArray;", "p0", "getBoolean", "", "getDouble", "", "getDynamic", "Lcom/facebook/react/bridge/Dynamic;", "getEntryIterator", "", "", "", "getInt", "", "getMap", "getString", "getType", "Lcom/facebook/react/bridge/ReadableType;", "hasKey", "isNull", "keySetIterator", "Lcom/facebook/react/bridge/ReadableMapKeySetIterator;", "toHashMap", "Ljava/util/HashMap;", "kotlin.jvm.PlatformType", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FilteredReadableMap.kt */
public final class FilteredReadableMap implements ReadableMap {
    private final ReadableMap backingMap;
    private final List<String> filteredKeys;

    public ReadableArray getArray(String str) {
        Intrinsics.checkNotNullParameter(str, "p0");
        return this.backingMap.getArray(str);
    }

    public boolean getBoolean(String str) {
        Intrinsics.checkNotNullParameter(str, "p0");
        return this.backingMap.getBoolean(str);
    }

    public double getDouble(String str) {
        Intrinsics.checkNotNullParameter(str, "p0");
        return this.backingMap.getDouble(str);
    }

    public Dynamic getDynamic(String str) {
        Intrinsics.checkNotNullParameter(str, "p0");
        return this.backingMap.getDynamic(str);
    }

    public int getInt(String str) {
        Intrinsics.checkNotNullParameter(str, "p0");
        return this.backingMap.getInt(str);
    }

    public ReadableMap getMap(String str) {
        Intrinsics.checkNotNullParameter(str, "p0");
        return this.backingMap.getMap(str);
    }

    public String getString(String str) {
        Intrinsics.checkNotNullParameter(str, "p0");
        return this.backingMap.getString(str);
    }

    public ReadableType getType(String str) {
        Intrinsics.checkNotNullParameter(str, "p0");
        return this.backingMap.getType(str);
    }

    public boolean hasKey(String str) {
        Intrinsics.checkNotNullParameter(str, "p0");
        return this.backingMap.hasKey(str);
    }

    public boolean isNull(String str) {
        Intrinsics.checkNotNullParameter(str, "p0");
        return this.backingMap.isNull(str);
    }

    public HashMap<String, Object> toHashMap() {
        return this.backingMap.toHashMap();
    }

    public FilteredReadableMap(ReadableMap readableMap, List<String> list) {
        Intrinsics.checkNotNullParameter(readableMap, "backingMap");
        Intrinsics.checkNotNullParameter(list, "filteredKeys");
        this.backingMap = readableMap;
        this.filteredKeys = list;
    }

    public Iterator<Map.Entry<String, Object>> getEntryIterator() {
        Iterator<Map.Entry<String, Object>> entryIterator = this.backingMap.getEntryIterator();
        Intrinsics.checkNotNullExpressionValue(entryIterator, "getEntryIterator(...)");
        return new FilteredIterator<>(entryIterator, new FilteredReadableMap$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: private */
    public static final boolean getEntryIterator$lambda$0(FilteredReadableMap filteredReadableMap, Map.Entry entry) {
        Intrinsics.checkNotNullParameter(filteredReadableMap, "this$0");
        return !filteredReadableMap.filteredKeys.contains(entry.getKey());
    }

    public ReadableMapKeySetIterator keySetIterator() {
        ReadableMapKeySetIterator keySetIterator = this.backingMap.keySetIterator();
        Intrinsics.checkNotNullExpressionValue(keySetIterator, "keySetIterator(...)");
        return new FilteredReadableMapKeySetIterator(keySetIterator, new FilteredReadableMap$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: private */
    public static final boolean keySetIterator$lambda$1(FilteredReadableMap filteredReadableMap, String str) {
        Intrinsics.checkNotNullParameter(filteredReadableMap, "this$0");
        Intrinsics.checkNotNullParameter(str, "it");
        return !filteredReadableMap.filteredKeys.contains(str);
    }
}
