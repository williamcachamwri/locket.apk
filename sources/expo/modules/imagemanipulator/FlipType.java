package expo.modules.imagemanipulator;

import expo.modules.kotlin.types.Enumerable;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002B\u000f\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lexpo/modules/imagemanipulator/FlipType;", "", "Lexpo/modules/kotlin/types/Enumerable;", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "VERTICAL", "HORIZONTAL", "expo-image-manipulator_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ManipulationArguments.kt */
public enum FlipType implements Enumerable {
    VERTICAL("vertical"),
    HORIZONTAL("horizontal");
    
    private final String value;

    public static EnumEntries<FlipType> getEntries() {
        return $ENTRIES;
    }

    private FlipType(String str) {
        this.value = str;
    }

    public final String getValue() {
        return this.value;
    }

    static {
        FlipType[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries((E[]) (Enum[]) $values);
    }
}
