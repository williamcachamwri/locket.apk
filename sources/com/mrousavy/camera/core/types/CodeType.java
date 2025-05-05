package com.mrousavy.camera.core.types;

import com.mrousavy.camera.core.CodeTypeNotSupportedError;
import com.mrousavy.camera.core.InvalidTypeScriptUnionError;
import com.mrousavy.camera.core.types.JSUnionValue;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0010\b\u0002\u0018\u0000 \u00182\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002:\u0001\u0018B\u000f\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0006\u0010\b\u001a\u00020\tR\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017¨\u0006\u0019"}, d2 = {"Lcom/mrousavy/camera/core/types/CodeType;", "", "Lcom/mrousavy/camera/core/types/JSUnionValue;", "unionValue", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getUnionValue", "()Ljava/lang/String;", "toBarcodeType", "", "CODE_128", "CODE_39", "CODE_93", "CODABAR", "EAN_13", "EAN_8", "ITF", "UPC_E", "UPC_A", "QR", "PDF_417", "AZTEC", "DATA_MATRIX", "UNKNOWN", "Companion", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CodeType.kt */
public enum CodeType implements JSUnionValue {
    CODE_128("code-128"),
    CODE_39("code-39"),
    CODE_93("code-93"),
    CODABAR("codabar"),
    EAN_13("ean-13"),
    EAN_8("ean-8"),
    ITF("itf"),
    UPC_E("upc-e"),
    UPC_A("upc-a"),
    QR("qr"),
    PDF_417("pdf-417"),
    AZTEC("aztec"),
    DATA_MATRIX("data-matrix"),
    UNKNOWN("unknown");
    
    public static final Companion Companion = null;
    private final String unionValue;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: CodeType.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        /* JADX WARNING: Can't wrap try/catch for region: R(31:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|31) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0034 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x003d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0046 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0050 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x005a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0064 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x006e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0082 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002b */
        static {
            /*
                com.mrousavy.camera.core.types.CodeType[] r0 = com.mrousavy.camera.core.types.CodeType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.mrousavy.camera.core.types.CodeType r1 = com.mrousavy.camera.core.types.CodeType.CODE_128     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.mrousavy.camera.core.types.CodeType r1 = com.mrousavy.camera.core.types.CodeType.CODE_39     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.mrousavy.camera.core.types.CodeType r1 = com.mrousavy.camera.core.types.CodeType.CODE_93     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                com.mrousavy.camera.core.types.CodeType r1 = com.mrousavy.camera.core.types.CodeType.CODABAR     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                com.mrousavy.camera.core.types.CodeType r1 = com.mrousavy.camera.core.types.CodeType.EAN_13     // Catch:{ NoSuchFieldError -> 0x0034 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0034 }
            L_0x0034:
                com.mrousavy.camera.core.types.CodeType r1 = com.mrousavy.camera.core.types.CodeType.EAN_8     // Catch:{ NoSuchFieldError -> 0x003d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003d }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003d }
            L_0x003d:
                com.mrousavy.camera.core.types.CodeType r1 = com.mrousavy.camera.core.types.CodeType.ITF     // Catch:{ NoSuchFieldError -> 0x0046 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0046 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0046 }
            L_0x0046:
                com.mrousavy.camera.core.types.CodeType r1 = com.mrousavy.camera.core.types.CodeType.UPC_E     // Catch:{ NoSuchFieldError -> 0x0050 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0050 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0050 }
            L_0x0050:
                com.mrousavy.camera.core.types.CodeType r1 = com.mrousavy.camera.core.types.CodeType.UPC_A     // Catch:{ NoSuchFieldError -> 0x005a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x005a }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x005a }
            L_0x005a:
                com.mrousavy.camera.core.types.CodeType r1 = com.mrousavy.camera.core.types.CodeType.QR     // Catch:{ NoSuchFieldError -> 0x0064 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0064 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0064 }
            L_0x0064:
                com.mrousavy.camera.core.types.CodeType r1 = com.mrousavy.camera.core.types.CodeType.PDF_417     // Catch:{ NoSuchFieldError -> 0x006e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006e }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006e }
            L_0x006e:
                com.mrousavy.camera.core.types.CodeType r1 = com.mrousavy.camera.core.types.CodeType.AZTEC     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                com.mrousavy.camera.core.types.CodeType r1 = com.mrousavy.camera.core.types.CodeType.DATA_MATRIX     // Catch:{ NoSuchFieldError -> 0x0082 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0082 }
                r2 = 13
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0082 }
            L_0x0082:
                com.mrousavy.camera.core.types.CodeType r1 = com.mrousavy.camera.core.types.CodeType.UNKNOWN     // Catch:{ NoSuchFieldError -> 0x008c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x008c }
                r2 = 14
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x008c }
            L_0x008c:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mrousavy.camera.core.types.CodeType.WhenMappings.<clinit>():void");
        }
    }

    public static EnumEntries<CodeType> getEntries() {
        return $ENTRIES;
    }

    private CodeType(String str) {
        this.unionValue = str;
    }

    public String getUnionValue() {
        return this.unionValue;
    }

    static {
        CodeType[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries((E[]) (Enum[]) $values);
        Companion = new Companion((DefaultConstructorMarker) null);
    }

    public final int toBarcodeType() {
        switch (WhenMappings.$EnumSwitchMapping$0[ordinal()]) {
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 4;
            case 4:
                return 8;
            case 5:
                return 32;
            case 6:
                return 64;
            case 7:
                return 128;
            case 8:
                return 1024;
            case 9:
                return 512;
            case 10:
                return 256;
            case 11:
                return 2048;
            case 12:
                return 4096;
            case 13:
                return 16;
            case 14:
                throw new CodeTypeNotSupportedError(getUnionValue());
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006J\u0012\u0010\u0007\u001a\u00020\u00022\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016¨\u0006\n"}, d2 = {"Lcom/mrousavy/camera/core/types/CodeType$Companion;", "Lcom/mrousavy/camera/core/types/JSUnionValue$Companion;", "Lcom/mrousavy/camera/core/types/CodeType;", "()V", "fromBarcodeType", "barcodeType", "", "fromUnionValue", "unionValue", "", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: CodeType.kt */
    public static final class Companion implements JSUnionValue.Companion<CodeType> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final CodeType fromBarcodeType(int i) {
            if (i == 1) {
                return CodeType.CODE_128;
            }
            if (i == 2) {
                return CodeType.CODE_39;
            }
            switch (i) {
                case 4:
                    return CodeType.CODE_93;
                case 8:
                    return CodeType.CODABAR;
                case 16:
                    return CodeType.DATA_MATRIX;
                case 32:
                    return CodeType.EAN_13;
                case 64:
                    return CodeType.EAN_8;
                case 128:
                    return CodeType.ITF;
                case 256:
                    return CodeType.QR;
                case 512:
                    return CodeType.UPC_A;
                case 1024:
                    return CodeType.UPC_E;
                case 2048:
                    return CodeType.PDF_417;
                case 4096:
                    return CodeType.AZTEC;
                default:
                    return CodeType.UNKNOWN;
            }
        }

        public CodeType fromUnionValue(String str) {
            if (str != null) {
                switch (str.hashCode()) {
                    case -1310519683:
                        if (str.equals("ean-13")) {
                            return CodeType.EAN_13;
                        }
                        break;
                    case -869195177:
                        if (str.equals("code-128")) {
                            return CodeType.CODE_128;
                        }
                        break;
                    case -720296449:
                        if (str.equals("pdf-417")) {
                            return CodeType.PDF_417;
                        }
                        break;
                    case 3617:
                        if (str.equals("qr")) {
                            return CodeType.QR;
                        }
                        break;
                    case 104603:
                        if (str.equals("itf")) {
                            return CodeType.ITF;
                        }
                        break;
                    case 93330745:
                        if (str.equals("aztec")) {
                            return CodeType.AZTEC;
                        }
                        break;
                    case 96272509:
                        if (str.equals("ean-8")) {
                            return CodeType.EAN_8;
                        }
                        break;
                    case 111485180:
                        if (str.equals("upc-a")) {
                            return CodeType.UPC_A;
                        }
                        break;
                    case 111485184:
                        if (str.equals("upc-e")) {
                            return CodeType.UPC_E;
                        }
                        break;
                    case 941726090:
                        if (str.equals("codabar")) {
                            return CodeType.CODABAR;
                        }
                        break;
                    case 941792838:
                        if (str.equals("code-39")) {
                            return CodeType.CODE_39;
                        }
                        break;
                    case 941793018:
                        if (str.equals("code-93")) {
                            return CodeType.CODE_93;
                        }
                        break;
                    case 1350827844:
                        if (str.equals("data-matrix")) {
                            return CodeType.DATA_MATRIX;
                        }
                        break;
                }
            }
            if (str == null) {
                str = "(null)";
            }
            throw new InvalidTypeScriptUnionError("codeType", str);
        }
    }
}
