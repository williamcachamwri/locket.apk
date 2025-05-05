package expo.modules.filesystem;

import expo.modules.kotlin.records.Field;
import expo.modules.kotlin.records.Record;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B#\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u000eJ0\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÖ\u0001J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000bR \u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0004¢\u0006\u0010\n\u0002\u0010\u000f\u0012\u0004\b\f\u0010\t\u001a\u0004\b\r\u0010\u000eR \u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0004¢\u0006\u0010\n\u0002\u0010\u000f\u0012\u0004\b\u0010\u0010\t\u001a\u0004\b\u0011\u0010\u000e¨\u0006\u001e"}, d2 = {"Lexpo/modules/filesystem/ReadingOptions;", "Lexpo/modules/kotlin/records/Record;", "encoding", "Lexpo/modules/filesystem/EncodingType;", "position", "", "length", "(Lexpo/modules/filesystem/EncodingType;Ljava/lang/Integer;Ljava/lang/Integer;)V", "getEncoding$annotations", "()V", "getEncoding", "()Lexpo/modules/filesystem/EncodingType;", "getLength$annotations", "getLength", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getPosition$annotations", "getPosition", "component1", "component2", "component3", "copy", "(Lexpo/modules/filesystem/EncodingType;Ljava/lang/Integer;Ljava/lang/Integer;)Lexpo/modules/filesystem/ReadingOptions;", "equals", "", "other", "", "hashCode", "toString", "", "expo-file-system_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FileSystemRecords.kt */
public final class ReadingOptions implements Record {
    private final EncodingType encoding;
    private final Integer length;
    private final Integer position;

    public static /* synthetic */ ReadingOptions copy$default(ReadingOptions readingOptions, EncodingType encodingType, Integer num, Integer num2, int i, Object obj) {
        if ((i & 1) != 0) {
            encodingType = readingOptions.encoding;
        }
        if ((i & 2) != 0) {
            num = readingOptions.position;
        }
        if ((i & 4) != 0) {
            num2 = readingOptions.length;
        }
        return readingOptions.copy(encodingType, num, num2);
    }

    @Field
    public static /* synthetic */ void getEncoding$annotations() {
    }

    @Field
    public static /* synthetic */ void getLength$annotations() {
    }

    @Field
    public static /* synthetic */ void getPosition$annotations() {
    }

    public final EncodingType component1() {
        return this.encoding;
    }

    public final Integer component2() {
        return this.position;
    }

    public final Integer component3() {
        return this.length;
    }

    public final ReadingOptions copy(EncodingType encodingType, Integer num, Integer num2) {
        Intrinsics.checkNotNullParameter(encodingType, "encoding");
        return new ReadingOptions(encodingType, num, num2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ReadingOptions)) {
            return false;
        }
        ReadingOptions readingOptions = (ReadingOptions) obj;
        return this.encoding == readingOptions.encoding && Intrinsics.areEqual((Object) this.position, (Object) readingOptions.position) && Intrinsics.areEqual((Object) this.length, (Object) readingOptions.length);
    }

    public int hashCode() {
        int hashCode = this.encoding.hashCode() * 31;
        Integer num = this.position;
        int i = 0;
        int hashCode2 = (hashCode + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.length;
        if (num2 != null) {
            i = num2.hashCode();
        }
        return hashCode2 + i;
    }

    public String toString() {
        EncodingType encodingType = this.encoding;
        Integer num = this.position;
        return "ReadingOptions(encoding=" + encodingType + ", position=" + num + ", length=" + this.length + ")";
    }

    public ReadingOptions(EncodingType encodingType, Integer num, Integer num2) {
        Intrinsics.checkNotNullParameter(encodingType, "encoding");
        this.encoding = encodingType;
        this.position = num;
        this.length = num2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ReadingOptions(EncodingType encodingType, Integer num, Integer num2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? EncodingType.UTF8 : encodingType, num, num2);
    }

    public final EncodingType getEncoding() {
        return this.encoding;
    }

    public final Integer getPosition() {
        return this.position;
    }

    public final Integer getLength() {
        return this.length;
    }
}
