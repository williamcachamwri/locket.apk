package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lkotlinx/coroutines/flow/SharingCommand;", "", "<init>", "(Ljava/lang/String;I)V", "START", "STOP", "STOP_AND_RESET_REPLAY_CACHE", "kotlinx-coroutines-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: SharingStarted.kt */
public enum SharingCommand {
    START,
    STOP,
    STOP_AND_RESET_REPLAY_CACHE;

    public static EnumEntries<SharingCommand> getEntries() {
        return $ENTRIES;
    }

    static {
        SharingCommand[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries((E[]) (Enum[]) $values);
    }
}
