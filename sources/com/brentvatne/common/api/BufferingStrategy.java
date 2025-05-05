package com.brentvatne.common.api;

import com.brentvatne.common.toolbox.DebugLog;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0002\u0003\u0004B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0005"}, d2 = {"Lcom/brentvatne/common/api/BufferingStrategy;", "", "()V", "BufferingStrategyEnum", "Companion", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: BufferingStrategy.kt */
public final class BufferingStrategy {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = "BufferingStrategy";

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/brentvatne/common/api/BufferingStrategy$BufferingStrategyEnum;", "", "(Ljava/lang/String;I)V", "Default", "DisableBuffering", "DependingOnMemory", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: BufferingStrategy.kt */
    public enum BufferingStrategyEnum {
        Default,
        DisableBuffering,
        DependingOnMemory;

        public static EnumEntries<BufferingStrategyEnum> getEntries() {
            return $ENTRIES;
        }

        static {
            BufferingStrategyEnum[] $values;
            $ENTRIES = EnumEntriesKt.enumEntries((E[]) (Enum[]) $values);
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/brentvatne/common/api/BufferingStrategy$Companion;", "", "()V", "TAG", "", "parse", "Lcom/brentvatne/common/api/BufferingStrategy$BufferingStrategyEnum;", "src", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: BufferingStrategy.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final BufferingStrategyEnum parse(String str) {
            if (str == null) {
                return BufferingStrategyEnum.Default;
            }
            try {
                return BufferingStrategyEnum.valueOf(str);
            } catch (Exception unused) {
                DebugLog.e(BufferingStrategy.TAG, "cannot parse buffering strategy " + str);
                return BufferingStrategyEnum.Default;
            }
        }
    }
}
