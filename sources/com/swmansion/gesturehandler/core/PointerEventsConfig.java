package com.swmansion.gesturehandler.core;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/swmansion/gesturehandler/core/PointerEventsConfig;", "", "(Ljava/lang/String;I)V", "NONE", "BOX_NONE", "BOX_ONLY", "AUTO", "react-native-gesture-handler_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PointerEventsConfig.kt */
public enum PointerEventsConfig {
    NONE,
    BOX_NONE,
    BOX_ONLY,
    AUTO;

    public static EnumEntries<PointerEventsConfig> getEntries() {
        return $ENTRIES;
    }

    static {
        PointerEventsConfig[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries((E[]) (Enum[]) $values);
    }
}
