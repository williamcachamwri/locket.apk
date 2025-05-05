package kotlin.reflect.jvm.internal.impl.types.error;

import kotlin.enums.EnumEntriesKt;

/* compiled from: ErrorEntity.kt */
public enum ErrorEntity {
    ERROR_CLASS("<Error class: %s>"),
    ERROR_FUNCTION("<Error function>"),
    ERROR_SCOPE("<Error scope>"),
    ERROR_MODULE("<Error module>"),
    ERROR_PROPERTY("<Error property>"),
    ERROR_TYPE("[Error type: %s]"),
    PARENT_OF_ERROR_SCOPE("<Fake parent for error lexical scope>");
    
    private final String debugText;

    private ErrorEntity(String str) {
        this.debugText = str;
    }

    public final String getDebugText() {
        return this.debugText;
    }

    static {
        ErrorEntity[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries((E[]) (Enum[]) $values);
    }
}
