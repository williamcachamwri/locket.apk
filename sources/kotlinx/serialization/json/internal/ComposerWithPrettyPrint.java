package kotlinx.serialization.json.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.Json;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\nH\u0016J\b\u0010\f\u001a\u00020\nH\u0016J\b\u0010\r\u001a\u00020\nH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lkotlinx/serialization/json/internal/ComposerWithPrettyPrint;", "Lkotlinx/serialization/json/internal/Composer;", "writer", "Lkotlinx/serialization/json/internal/JsonWriter;", "json", "Lkotlinx/serialization/json/Json;", "(Lkotlinx/serialization/json/internal/JsonWriter;Lkotlinx/serialization/json/Json;)V", "level", "", "indent", "", "nextItem", "space", "unIndent", "kotlinx-serialization-json"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: Composers.kt */
public final class ComposerWithPrettyPrint extends Composer {
    private final Json json;
    private int level;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ComposerWithPrettyPrint(JsonWriter jsonWriter, Json json2) {
        super(jsonWriter);
        Intrinsics.checkNotNullParameter(jsonWriter, "writer");
        Intrinsics.checkNotNullParameter(json2, "json");
        this.json = json2;
    }

    public void indent() {
        setWritingFirst(true);
        this.level++;
    }

    public void unIndent() {
        this.level--;
    }

    public void nextItem() {
        setWritingFirst(false);
        print("\n");
        int i = this.level;
        for (int i2 = 0; i2 < i; i2++) {
            print(this.json.getConfiguration().getPrettyPrintIndent());
        }
    }

    public void space() {
        print(' ');
    }
}
