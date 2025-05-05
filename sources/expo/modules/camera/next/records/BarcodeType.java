package expo.modules.camera.next.records;

import expo.modules.kotlin.types.Enumerable;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u000e\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002B\u000f\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0006\u0010\b\u001a\u00020\tR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016¨\u0006\u0017"}, d2 = {"Lexpo/modules/camera/next/records/BarcodeType;", "", "Lexpo/modules/kotlin/types/Enumerable;", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "mapToBarcode", "", "AZTEC", "EAN13", "EAN8", "QR", "PDF417", "UPCE", "DATAMATRIX", "CODE39", "CODE93", "ITF14", "CODABAR", "CODE128", "UPCA", "expo-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CameraRecords.kt */
public enum BarcodeType implements Enumerable {
    AZTEC("aztec"),
    EAN13("ean13"),
    EAN8("ean8"),
    QR("qr"),
    PDF417("pdf417"),
    UPCE("upc_e"),
    DATAMATRIX("datamatrix"),
    CODE39("code39"),
    CODE93("code93"),
    ITF14("itf14"),
    CODABAR("codabar"),
    CODE128("code128"),
    UPCA("upc_a");
    
    private final String value;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: CameraRecords.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        /* JADX WARNING: Can't wrap try/catch for region: R(28:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|(2:25|26)|27|29) */
        /* JADX WARNING: Can't wrap try/catch for region: R(29:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|29) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0034 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x003d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0046 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0050 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x005a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0064 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x006e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002b */
        static {
            /*
                expo.modules.camera.next.records.BarcodeType[] r0 = expo.modules.camera.next.records.BarcodeType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                expo.modules.camera.next.records.BarcodeType r1 = expo.modules.camera.next.records.BarcodeType.AZTEC     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                expo.modules.camera.next.records.BarcodeType r1 = expo.modules.camera.next.records.BarcodeType.EAN13     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                expo.modules.camera.next.records.BarcodeType r1 = expo.modules.camera.next.records.BarcodeType.EAN8     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                expo.modules.camera.next.records.BarcodeType r1 = expo.modules.camera.next.records.BarcodeType.QR     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                expo.modules.camera.next.records.BarcodeType r1 = expo.modules.camera.next.records.BarcodeType.PDF417     // Catch:{ NoSuchFieldError -> 0x0034 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0034 }
            L_0x0034:
                expo.modules.camera.next.records.BarcodeType r1 = expo.modules.camera.next.records.BarcodeType.UPCE     // Catch:{ NoSuchFieldError -> 0x003d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003d }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003d }
            L_0x003d:
                expo.modules.camera.next.records.BarcodeType r1 = expo.modules.camera.next.records.BarcodeType.DATAMATRIX     // Catch:{ NoSuchFieldError -> 0x0046 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0046 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0046 }
            L_0x0046:
                expo.modules.camera.next.records.BarcodeType r1 = expo.modules.camera.next.records.BarcodeType.CODE39     // Catch:{ NoSuchFieldError -> 0x0050 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0050 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0050 }
            L_0x0050:
                expo.modules.camera.next.records.BarcodeType r1 = expo.modules.camera.next.records.BarcodeType.CODE93     // Catch:{ NoSuchFieldError -> 0x005a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x005a }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x005a }
            L_0x005a:
                expo.modules.camera.next.records.BarcodeType r1 = expo.modules.camera.next.records.BarcodeType.ITF14     // Catch:{ NoSuchFieldError -> 0x0064 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0064 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0064 }
            L_0x0064:
                expo.modules.camera.next.records.BarcodeType r1 = expo.modules.camera.next.records.BarcodeType.CODABAR     // Catch:{ NoSuchFieldError -> 0x006e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006e }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006e }
            L_0x006e:
                expo.modules.camera.next.records.BarcodeType r1 = expo.modules.camera.next.records.BarcodeType.CODE128     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                expo.modules.camera.next.records.BarcodeType r1 = expo.modules.camera.next.records.BarcodeType.UPCA     // Catch:{ NoSuchFieldError -> 0x0082 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0082 }
                r2 = 13
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0082 }
            L_0x0082:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: expo.modules.camera.next.records.BarcodeType.WhenMappings.<clinit>():void");
        }
    }

    public static EnumEntries<BarcodeType> getEntries() {
        return $ENTRIES;
    }

    private BarcodeType(String str) {
        this.value = str;
    }

    public final String getValue() {
        return this.value;
    }

    static {
        BarcodeType[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries((E[]) (Enum[]) $values);
    }

    public final int mapToBarcode() {
        switch (WhenMappings.$EnumSwitchMapping$0[ordinal()]) {
            case 1:
                return 4096;
            case 2:
                return 32;
            case 3:
                return 64;
            case 4:
                return 256;
            case 5:
                return 2048;
            case 6:
                return 1024;
            case 7:
                return 16;
            case 8:
                return 2;
            case 9:
                return 4;
            case 10:
                return 128;
            case 11:
                return 8;
            case 12:
                return 1;
            case 13:
                return 512;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
