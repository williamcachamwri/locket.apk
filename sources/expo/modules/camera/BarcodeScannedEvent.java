package expo.modules.camera;

import android.os.Bundle;
import expo.modules.kotlin.records.Field;
import expo.modules.kotlin.records.Record;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0016\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\n0\tj\b\u0012\u0004\u0012\u00020\n`\u000b\u0012\u0006\u0010\f\u001a\u00020\n¢\u0006\u0002\u0010\rJ\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0005HÆ\u0003J\t\u0010!\u001a\u00020\u0005HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\u0019\u0010#\u001a\u0012\u0012\u0004\u0012\u00020\n0\tj\b\u0012\u0004\u0012\u00020\n`\u000bHÆ\u0003J\t\u0010$\u001a\u00020\nHÆ\u0003JU\u0010%\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00032\u0018\b\u0002\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\n0\tj\b\u0012\u0004\u0012\u00020\n`\u000b2\b\b\u0002\u0010\f\u001a\u00020\nHÆ\u0001J\u0013\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010)HÖ\u0003J\t\u0010*\u001a\u00020\u0003HÖ\u0001J\t\u0010+\u001a\u00020\u0005HÖ\u0001R\u001c\u0010\f\u001a\u00020\n8\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R,\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\n0\tj\b\u0012\u0004\u0012\u00020\n`\u000b8\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u000f\u001a\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0006\u001a\u00020\u00058\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0018\u0010\u000f\u001a\u0004\b\u0019\u0010\u0017R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001a\u0010\u000f\u001a\u0004\b\u001b\u0010\u001cR\u001c\u0010\u0007\u001a\u00020\u00038\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001d\u0010\u000f\u001a\u0004\b\u001e\u0010\u001c¨\u0006,"}, d2 = {"Lexpo/modules/camera/BarcodeScannedEvent;", "Lexpo/modules/kotlin/records/Record;", "target", "", "data", "", "raw", "type", "cornerPoints", "Ljava/util/ArrayList;", "Landroid/os/Bundle;", "Lkotlin/collections/ArrayList;", "boundingBox", "(ILjava/lang/String;Ljava/lang/String;ILjava/util/ArrayList;Landroid/os/Bundle;)V", "getBoundingBox$annotations", "()V", "getBoundingBox", "()Landroid/os/Bundle;", "getCornerPoints$annotations", "getCornerPoints", "()Ljava/util/ArrayList;", "getData$annotations", "getData", "()Ljava/lang/String;", "getRaw$annotations", "getRaw", "getTarget$annotations", "getTarget", "()I", "getType$annotations", "getType", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "", "hashCode", "toString", "expo-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: Events.kt */
public final class BarcodeScannedEvent implements Record {
    private final Bundle boundingBox;
    private final ArrayList<Bundle> cornerPoints;
    private final String data;
    private final String raw;
    private final int target;
    private final int type;

    public static /* synthetic */ BarcodeScannedEvent copy$default(BarcodeScannedEvent barcodeScannedEvent, int i, String str, String str2, int i2, ArrayList<Bundle> arrayList, Bundle bundle, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = barcodeScannedEvent.target;
        }
        if ((i3 & 2) != 0) {
            str = barcodeScannedEvent.data;
        }
        String str3 = str;
        if ((i3 & 4) != 0) {
            str2 = barcodeScannedEvent.raw;
        }
        String str4 = str2;
        if ((i3 & 8) != 0) {
            i2 = barcodeScannedEvent.type;
        }
        int i4 = i2;
        if ((i3 & 16) != 0) {
            arrayList = barcodeScannedEvent.cornerPoints;
        }
        ArrayList<Bundle> arrayList2 = arrayList;
        if ((i3 & 32) != 0) {
            bundle = barcodeScannedEvent.boundingBox;
        }
        return barcodeScannedEvent.copy(i, str3, str4, i4, arrayList2, bundle);
    }

    @Field
    public static /* synthetic */ void getBoundingBox$annotations() {
    }

    @Field
    public static /* synthetic */ void getCornerPoints$annotations() {
    }

    @Field
    public static /* synthetic */ void getData$annotations() {
    }

    @Field
    public static /* synthetic */ void getRaw$annotations() {
    }

    @Field
    public static /* synthetic */ void getTarget$annotations() {
    }

    @Field
    public static /* synthetic */ void getType$annotations() {
    }

    public final int component1() {
        return this.target;
    }

    public final String component2() {
        return this.data;
    }

    public final String component3() {
        return this.raw;
    }

    public final int component4() {
        return this.type;
    }

    public final ArrayList<Bundle> component5() {
        return this.cornerPoints;
    }

    public final Bundle component6() {
        return this.boundingBox;
    }

    public final BarcodeScannedEvent copy(int i, String str, String str2, int i2, ArrayList<Bundle> arrayList, Bundle bundle) {
        Intrinsics.checkNotNullParameter(str, "data");
        Intrinsics.checkNotNullParameter(str2, "raw");
        Intrinsics.checkNotNullParameter(arrayList, "cornerPoints");
        Intrinsics.checkNotNullParameter(bundle, "boundingBox");
        return new BarcodeScannedEvent(i, str, str2, i2, arrayList, bundle);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BarcodeScannedEvent)) {
            return false;
        }
        BarcodeScannedEvent barcodeScannedEvent = (BarcodeScannedEvent) obj;
        return this.target == barcodeScannedEvent.target && Intrinsics.areEqual((Object) this.data, (Object) barcodeScannedEvent.data) && Intrinsics.areEqual((Object) this.raw, (Object) barcodeScannedEvent.raw) && this.type == barcodeScannedEvent.type && Intrinsics.areEqual((Object) this.cornerPoints, (Object) barcodeScannedEvent.cornerPoints) && Intrinsics.areEqual((Object) this.boundingBox, (Object) barcodeScannedEvent.boundingBox);
    }

    public int hashCode() {
        return (((((((((Integer.hashCode(this.target) * 31) + this.data.hashCode()) * 31) + this.raw.hashCode()) * 31) + Integer.hashCode(this.type)) * 31) + this.cornerPoints.hashCode()) * 31) + this.boundingBox.hashCode();
    }

    public String toString() {
        int i = this.target;
        String str = this.data;
        String str2 = this.raw;
        int i2 = this.type;
        ArrayList<Bundle> arrayList = this.cornerPoints;
        return "BarcodeScannedEvent(target=" + i + ", data=" + str + ", raw=" + str2 + ", type=" + i2 + ", cornerPoints=" + arrayList + ", boundingBox=" + this.boundingBox + ")";
    }

    public BarcodeScannedEvent(int i, String str, String str2, int i2, ArrayList<Bundle> arrayList, Bundle bundle) {
        Intrinsics.checkNotNullParameter(str, "data");
        Intrinsics.checkNotNullParameter(str2, "raw");
        Intrinsics.checkNotNullParameter(arrayList, "cornerPoints");
        Intrinsics.checkNotNullParameter(bundle, "boundingBox");
        this.target = i;
        this.data = str;
        this.raw = str2;
        this.type = i2;
        this.cornerPoints = arrayList;
        this.boundingBox = bundle;
    }

    public final int getTarget() {
        return this.target;
    }

    public final String getData() {
        return this.data;
    }

    public final String getRaw() {
        return this.raw;
    }

    public final int getType() {
        return this.type;
    }

    public final ArrayList<Bundle> getCornerPoints() {
        return this.cornerPoints;
    }

    public final Bundle getBoundingBox() {
        return this.boundingBox;
    }
}
