package expo.modules.camera.next.records;

import expo.modules.kotlin.records.Field;
import expo.modules.kotlin.records.Record;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u000eJ*\u0010\u0012\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001¢\u0006\u0002\u0010\u0013J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001R\"\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000bR \u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006X\u0004¢\u0006\u0010\n\u0002\u0010\u000f\u0012\u0004\b\f\u0010\t\u001a\u0004\b\r\u0010\u000e¨\u0006\u001c"}, d2 = {"Lexpo/modules/camera/next/records/BarcodeSettings;", "Lexpo/modules/kotlin/records/Record;", "barcodeTypes", "", "Lexpo/modules/camera/next/records/BarcodeType;", "interval", "", "(Ljava/util/List;Ljava/lang/Double;)V", "getBarcodeTypes$annotations", "()V", "getBarcodeTypes", "()Ljava/util/List;", "getInterval$annotations", "getInterval", "()Ljava/lang/Double;", "Ljava/lang/Double;", "component1", "component2", "copy", "(Ljava/util/List;Ljava/lang/Double;)Lexpo/modules/camera/next/records/BarcodeSettings;", "equals", "", "other", "", "hashCode", "", "toString", "", "expo-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CameraRecords.kt */
public final class BarcodeSettings implements Record {
    private final List<BarcodeType> barcodeTypes;
    private final Double interval;

    public static /* synthetic */ BarcodeSettings copy$default(BarcodeSettings barcodeSettings, List<BarcodeType> list, Double d, int i, Object obj) {
        if ((i & 1) != 0) {
            list = barcodeSettings.barcodeTypes;
        }
        if ((i & 2) != 0) {
            d = barcodeSettings.interval;
        }
        return barcodeSettings.copy(list, d);
    }

    @Field
    public static /* synthetic */ void getBarcodeTypes$annotations() {
    }

    @Field
    public static /* synthetic */ void getInterval$annotations() {
    }

    public final List<BarcodeType> component1() {
        return this.barcodeTypes;
    }

    public final Double component2() {
        return this.interval;
    }

    public final BarcodeSettings copy(List<? extends BarcodeType> list, Double d) {
        Intrinsics.checkNotNullParameter(list, "barcodeTypes");
        return new BarcodeSettings(list, d);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BarcodeSettings)) {
            return false;
        }
        BarcodeSettings barcodeSettings = (BarcodeSettings) obj;
        return Intrinsics.areEqual((Object) this.barcodeTypes, (Object) barcodeSettings.barcodeTypes) && Intrinsics.areEqual((Object) this.interval, (Object) barcodeSettings.interval);
    }

    public int hashCode() {
        int hashCode = this.barcodeTypes.hashCode() * 31;
        Double d = this.interval;
        return hashCode + (d == null ? 0 : d.hashCode());
    }

    public String toString() {
        List<BarcodeType> list = this.barcodeTypes;
        return "BarcodeSettings(barcodeTypes=" + list + ", interval=" + this.interval + ")";
    }

    public BarcodeSettings(List<? extends BarcodeType> list, Double d) {
        Intrinsics.checkNotNullParameter(list, "barcodeTypes");
        this.barcodeTypes = list;
        this.interval = d;
    }

    public final List<BarcodeType> getBarcodeTypes() {
        return this.barcodeTypes;
    }

    public final Double getInterval() {
        return this.interval;
    }
}
