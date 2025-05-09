package kotlin.reflect.jvm.internal.impl.builtins;

import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;

/* compiled from: UnsignedType.kt */
public enum UnsignedArrayType {
    UBYTEARRAY(r1),
    USHORTARRAY(r1),
    UINTARRAY(r1),
    ULONGARRAY(r1);
    
    private final ClassId classId;
    private final Name typeName;

    private UnsignedArrayType(ClassId classId2) {
        this.classId = classId2;
        Name shortClassName = classId2.getShortClassName();
        Intrinsics.checkNotNullExpressionValue(shortClassName, "getShortClassName(...)");
        this.typeName = shortClassName;
    }

    static {
        UnsignedArrayType[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries((E[]) (Enum[]) $values);
    }

    public final Name getTypeName() {
        return this.typeName;
    }
}
