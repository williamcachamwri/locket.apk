package kotlin.internal;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lkotlin/internal/RequireKotlinVersionKind;", "", "(Ljava/lang/String;I)V", "LANGUAGE_VERSION", "COMPILER_VERSION", "API_VERSION", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: Annotations.kt */
public enum RequireKotlinVersionKind {
    LANGUAGE_VERSION,
    COMPILER_VERSION,
    API_VERSION;

    public static EnumEntries<RequireKotlinVersionKind> getEntries() {
        return $ENTRIES;
    }

    static {
        RequireKotlinVersionKind[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries((E[]) (Enum[]) $values);
    }
}
