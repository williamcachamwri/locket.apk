package expo.modules.imagemanipulator;

import expo.modules.kotlin.records.Field;
import expo.modules.kotlin.records.Record;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\u0010\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\tJ\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\tJ&\u0010\u000f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u0010J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001R \u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\u0010\n\u0002\u0010\n\u0012\u0004\b\u0006\u0010\u0007\u001a\u0004\b\b\u0010\tR \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\u0010\n\u0002\u0010\n\u0012\u0004\b\u000b\u0010\u0007\u001a\u0004\b\f\u0010\t¨\u0006\u0019"}, d2 = {"Lexpo/modules/imagemanipulator/ResizeOptions;", "Lexpo/modules/kotlin/records/Record;", "width", "", "height", "(Ljava/lang/Double;Ljava/lang/Double;)V", "getHeight$annotations", "()V", "getHeight", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getWidth$annotations", "getWidth", "component1", "component2", "copy", "(Ljava/lang/Double;Ljava/lang/Double;)Lexpo/modules/imagemanipulator/ResizeOptions;", "equals", "", "other", "", "hashCode", "", "toString", "", "expo-image-manipulator_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ManipulationArguments.kt */
public final class ResizeOptions implements Record {
    private final Double height;
    private final Double width;

    public static /* synthetic */ ResizeOptions copy$default(ResizeOptions resizeOptions, Double d, Double d2, int i, Object obj) {
        if ((i & 1) != 0) {
            d = resizeOptions.width;
        }
        if ((i & 2) != 0) {
            d2 = resizeOptions.height;
        }
        return resizeOptions.copy(d, d2);
    }

    @Field
    public static /* synthetic */ void getHeight$annotations() {
    }

    @Field
    public static /* synthetic */ void getWidth$annotations() {
    }

    public final Double component1() {
        return this.width;
    }

    public final Double component2() {
        return this.height;
    }

    public final ResizeOptions copy(Double d, Double d2) {
        return new ResizeOptions(d, d2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ResizeOptions)) {
            return false;
        }
        ResizeOptions resizeOptions = (ResizeOptions) obj;
        return Intrinsics.areEqual((Object) this.width, (Object) resizeOptions.width) && Intrinsics.areEqual((Object) this.height, (Object) resizeOptions.height);
    }

    public int hashCode() {
        Double d = this.width;
        int i = 0;
        int hashCode = (d == null ? 0 : d.hashCode()) * 31;
        Double d2 = this.height;
        if (d2 != null) {
            i = d2.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        Double d = this.width;
        return "ResizeOptions(width=" + d + ", height=" + this.height + ")";
    }

    public ResizeOptions(Double d, Double d2) {
        this.width = d;
        this.height = d2;
    }

    public final Double getWidth() {
        return this.width;
    }

    public final Double getHeight() {
        return this.height;
    }
}
