package kotlinx.serialization.json;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.ExperimentalSerializationApi;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0001¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005J\u0016\u0010\t\u001a\u00020\u00072\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u000bH\u0007J\b\u0010\f\u001a\u00020\rH\u0001R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lkotlinx/serialization/json/JsonArrayBuilder;", "", "()V", "content", "", "Lkotlinx/serialization/json/JsonElement;", "add", "", "element", "addAll", "elements", "", "build", "Lkotlinx/serialization/json/JsonArray;", "kotlinx-serialization-json"}, k = 1, mv = {1, 8, 0}, xi = 48)
@JsonDslMarker
/* compiled from: JsonElementBuilders.kt */
public final class JsonArrayBuilder {
    private final List<JsonElement> content = new ArrayList();

    public final boolean add(JsonElement jsonElement) {
        Intrinsics.checkNotNullParameter(jsonElement, "element");
        this.content.add(jsonElement);
        return true;
    }

    @ExperimentalSerializationApi
    public final boolean addAll(Collection<? extends JsonElement> collection) {
        Intrinsics.checkNotNullParameter(collection, "elements");
        return this.content.addAll(collection);
    }

    public final JsonArray build() {
        return new JsonArray(this.content);
    }
}
