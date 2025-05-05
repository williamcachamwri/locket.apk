package expo.modules.filesystem;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import expo.modules.kotlin.records.Field;
import expo.modules.kotlin.records.Record;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0006\u0010\u0007\u001a\u0004\b\b\u0010\tR\u001c\u0010\u0004\u001a\u00020\u00038\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\n\u0010\u0007\u001a\u0004\b\u000b\u0010\t¨\u0006\u0016"}, d2 = {"Lexpo/modules/filesystem/RelocatingOptions;", "Lexpo/modules/kotlin/records/Record;", "from", "", "to", "(Ljava/lang/String;Ljava/lang/String;)V", "getFrom$annotations", "()V", "getFrom", "()Ljava/lang/String;", "getTo$annotations", "getTo", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "expo-file-system_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FileSystemRecords.kt */
public final class RelocatingOptions implements Record {
    private final String from;
    private final String to;

    public static /* synthetic */ RelocatingOptions copy$default(RelocatingOptions relocatingOptions, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = relocatingOptions.from;
        }
        if ((i & 2) != 0) {
            str2 = relocatingOptions.to;
        }
        return relocatingOptions.copy(str, str2);
    }

    @Field
    public static /* synthetic */ void getFrom$annotations() {
    }

    @Field
    public static /* synthetic */ void getTo$annotations() {
    }

    public final String component1() {
        return this.from;
    }

    public final String component2() {
        return this.to;
    }

    public final RelocatingOptions copy(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "from");
        Intrinsics.checkNotNullParameter(str2, TypedValues.TransitionType.S_TO);
        return new RelocatingOptions(str, str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RelocatingOptions)) {
            return false;
        }
        RelocatingOptions relocatingOptions = (RelocatingOptions) obj;
        return Intrinsics.areEqual((Object) this.from, (Object) relocatingOptions.from) && Intrinsics.areEqual((Object) this.to, (Object) relocatingOptions.to);
    }

    public int hashCode() {
        return (this.from.hashCode() * 31) + this.to.hashCode();
    }

    public String toString() {
        String str = this.from;
        return "RelocatingOptions(from=" + str + ", to=" + this.to + ")";
    }

    public RelocatingOptions(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "from");
        Intrinsics.checkNotNullParameter(str2, TypedValues.TransitionType.S_TO);
        this.from = str;
        this.to = str2;
    }

    public final String getFrom() {
        return this.from;
    }

    public final String getTo() {
        return this.to;
    }
}
