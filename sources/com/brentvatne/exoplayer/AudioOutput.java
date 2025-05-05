package com.brentvatne.exoplayer;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\b\b\u0002\u0018\u0000 \f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\fB\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\t\u001a\u00020\u0003H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bj\u0002\b\nj\u0002\b\u000b¨\u0006\r"}, d2 = {"Lcom/brentvatne/exoplayer/AudioOutput;", "", "outputName", "", "streamType", "", "(Ljava/lang/String;ILjava/lang/String;I)V", "getStreamType", "()I", "toString", "SPEAKER", "EARPIECE", "Companion", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: AudioOutput.kt */
public enum AudioOutput {
    SPEAKER("speaker", 3),
    EARPIECE("earpiece", 0);
    
    public static final Companion Companion = null;
    /* access modifiers changed from: private */
    public final String outputName;
    private final int streamType;

    @JvmStatic
    public static final AudioOutput get(String str) {
        return Companion.get(str);
    }

    public static EnumEntries<AudioOutput> getEntries() {
        return $ENTRIES;
    }

    private AudioOutput(String str, int i) {
        this.outputName = str;
        this.streamType = i;
    }

    public final int getStreamType() {
        return this.streamType;
    }

    static {
        AudioOutput[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries((E[]) (Enum[]) $values);
        Companion = new Companion((DefaultConstructorMarker) null);
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/brentvatne/exoplayer/AudioOutput$Companion;", "", "()V", "get", "Lcom/brentvatne/exoplayer/AudioOutput;", "name", "", "react-native-video_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: AudioOutput.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final AudioOutput get(String str) {
            Intrinsics.checkNotNullParameter(str, "name");
            for (AudioOutput audioOutput : AudioOutput.values()) {
                if (StringsKt.equals(audioOutput.outputName, str, true)) {
                    return audioOutput;
                }
            }
            return AudioOutput.SPEAKER;
        }
    }

    public String toString() {
        String simpleName = getClass().getSimpleName();
        String str = this.outputName;
        return simpleName + "(" + str + ", " + this.streamType + ")";
    }
}
