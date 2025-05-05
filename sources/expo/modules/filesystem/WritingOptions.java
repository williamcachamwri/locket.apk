package expo.modules.filesystem;

import expo.modules.kotlin.records.Field;
import expo.modules.kotlin.records.Record;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0013"}, d2 = {"Lexpo/modules/filesystem/WritingOptions;", "Lexpo/modules/kotlin/records/Record;", "encoding", "Lexpo/modules/filesystem/EncodingType;", "(Lexpo/modules/filesystem/EncodingType;)V", "getEncoding$annotations", "()V", "getEncoding", "()Lexpo/modules/filesystem/EncodingType;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "expo-file-system_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FileSystemRecords.kt */
public final class WritingOptions implements Record {
    private final EncodingType encoding;

    public WritingOptions() {
        this((EncodingType) null, 1, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ WritingOptions copy$default(WritingOptions writingOptions, EncodingType encodingType, int i, Object obj) {
        if ((i & 1) != 0) {
            encodingType = writingOptions.encoding;
        }
        return writingOptions.copy(encodingType);
    }

    @Field
    public static /* synthetic */ void getEncoding$annotations() {
    }

    public final EncodingType component1() {
        return this.encoding;
    }

    public final WritingOptions copy(EncodingType encodingType) {
        Intrinsics.checkNotNullParameter(encodingType, "encoding");
        return new WritingOptions(encodingType);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof WritingOptions) && this.encoding == ((WritingOptions) obj).encoding;
    }

    public int hashCode() {
        return this.encoding.hashCode();
    }

    public String toString() {
        return "WritingOptions(encoding=" + this.encoding + ")";
    }

    public WritingOptions(EncodingType encodingType) {
        Intrinsics.checkNotNullParameter(encodingType, "encoding");
        this.encoding = encodingType;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ WritingOptions(EncodingType encodingType, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? EncodingType.UTF8 : encodingType);
    }

    public final EncodingType getEncoding() {
        return this.encoding;
    }
}
