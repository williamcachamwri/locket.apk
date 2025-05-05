package expo.modules.blur.enums;

import expo.modules.devlauncher.launcher.manifest.DevLauncherUserInterface;
import expo.modules.kotlin.types.Enumerable;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0017\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002B\u000f\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!¨\u0006\""}, d2 = {"Lexpo/modules/blur/enums/TintStyle;", "", "Lexpo/modules/kotlin/types/Enumerable;", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "toBlurEffect", "", "blurRadius", "", "toColorInt", "DEFAULT", "EXTRA_LIGHT", "LIGHT", "DARK", "REGULAR", "PROMINENT", "SYSTEM_ULTRA_THIN_MATERIAL", "SYSTEM_THIN_MATERIAL", "SYSTEM_MATERIAL", "SYSTEM_THICK_MATERIAL", "SYSTEM_CHROME_MATERIAL", "SYSTEM_ULTRA_THIN_MATERIAL_LIGHT", "SYSTEM_THICK_MATERIAL_LIGHT", "SYSTEM_THIN_MATERIAL_LIGHT", "SYSTEM_MATERIAL_LIGHT", "SYSTEM_CHROME_MATERIAL_LIGHT", "SYSTEM_ULTRA_THIN_MATERIAL_DARK", "SYSTEM_THIN_MATERIAL_DARK", "SYSTEM_MATERIAL_DARK", "SYSTEM_THICK_MATERIAL_DARK", "SYSTEM_CHROME_MATERIAL_DARK", "expo-blur_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: TintStyle.kt */
public enum TintStyle implements Enumerable {
    DEFAULT("default"),
    EXTRA_LIGHT("extraLight"),
    LIGHT(DevLauncherUserInterface.LIGHT),
    DARK(DevLauncherUserInterface.DARK),
    REGULAR("regular"),
    PROMINENT("prominent"),
    SYSTEM_ULTRA_THIN_MATERIAL("systemUltraThinMaterial"),
    SYSTEM_THIN_MATERIAL("systemThinMaterial"),
    SYSTEM_MATERIAL("systemMaterial"),
    SYSTEM_THICK_MATERIAL("systemThickMaterial"),
    SYSTEM_CHROME_MATERIAL("systemChromeMaterial"),
    SYSTEM_ULTRA_THIN_MATERIAL_LIGHT("systemUltraThinMaterialLight"),
    SYSTEM_THICK_MATERIAL_LIGHT("systemThickMaterialLight"),
    SYSTEM_THIN_MATERIAL_LIGHT("systemThinMaterialLight"),
    SYSTEM_MATERIAL_LIGHT("systemMaterialLight"),
    SYSTEM_CHROME_MATERIAL_LIGHT("systemChromeMaterialLight"),
    SYSTEM_ULTRA_THIN_MATERIAL_DARK("systemUltraThinMaterialDark"),
    SYSTEM_THIN_MATERIAL_DARK("systemThinMaterialDark"),
    SYSTEM_MATERIAL_DARK("systemMaterialDark"),
    SYSTEM_THICK_MATERIAL_DARK("systemThickMaterialDark"),
    SYSTEM_CHROME_MATERIAL_DARK("systemChromeMaterialDark");
    
    private final String value;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: TintStyle.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        /* JADX WARNING: Can't wrap try/catch for region: R(45:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|41|42|43|45) */
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
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x008c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x0096 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x00a0 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x00aa */
        /* JADX WARNING: Missing exception handler attribute for start block: B:37:0x00b4 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:39:0x00be */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:41:0x00c8 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002b */
        static {
            /*
                expo.modules.blur.enums.TintStyle[] r0 = expo.modules.blur.enums.TintStyle.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                expo.modules.blur.enums.TintStyle r1 = expo.modules.blur.enums.TintStyle.EXTRA_LIGHT     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                expo.modules.blur.enums.TintStyle r1 = expo.modules.blur.enums.TintStyle.LIGHT     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                expo.modules.blur.enums.TintStyle r1 = expo.modules.blur.enums.TintStyle.SYSTEM_MATERIAL_LIGHT     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                expo.modules.blur.enums.TintStyle r1 = expo.modules.blur.enums.TintStyle.SYSTEM_ULTRA_THIN_MATERIAL_LIGHT     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                expo.modules.blur.enums.TintStyle r1 = expo.modules.blur.enums.TintStyle.SYSTEM_THICK_MATERIAL_LIGHT     // Catch:{ NoSuchFieldError -> 0x0034 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0034 }
            L_0x0034:
                expo.modules.blur.enums.TintStyle r1 = expo.modules.blur.enums.TintStyle.PROMINENT     // Catch:{ NoSuchFieldError -> 0x003d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003d }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003d }
            L_0x003d:
                expo.modules.blur.enums.TintStyle r1 = expo.modules.blur.enums.TintStyle.DEFAULT     // Catch:{ NoSuchFieldError -> 0x0046 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0046 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0046 }
            L_0x0046:
                expo.modules.blur.enums.TintStyle r1 = expo.modules.blur.enums.TintStyle.SYSTEM_MATERIAL     // Catch:{ NoSuchFieldError -> 0x0050 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0050 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0050 }
            L_0x0050:
                expo.modules.blur.enums.TintStyle r1 = expo.modules.blur.enums.TintStyle.DARK     // Catch:{ NoSuchFieldError -> 0x005a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x005a }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x005a }
            L_0x005a:
                expo.modules.blur.enums.TintStyle r1 = expo.modules.blur.enums.TintStyle.SYSTEM_MATERIAL_DARK     // Catch:{ NoSuchFieldError -> 0x0064 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0064 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0064 }
            L_0x0064:
                expo.modules.blur.enums.TintStyle r1 = expo.modules.blur.enums.TintStyle.REGULAR     // Catch:{ NoSuchFieldError -> 0x006e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006e }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006e }
            L_0x006e:
                expo.modules.blur.enums.TintStyle r1 = expo.modules.blur.enums.TintStyle.SYSTEM_ULTRA_THIN_MATERIAL     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                expo.modules.blur.enums.TintStyle r1 = expo.modules.blur.enums.TintStyle.SYSTEM_THICK_MATERIAL     // Catch:{ NoSuchFieldError -> 0x0082 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0082 }
                r2 = 13
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0082 }
            L_0x0082:
                expo.modules.blur.enums.TintStyle r1 = expo.modules.blur.enums.TintStyle.SYSTEM_CHROME_MATERIAL     // Catch:{ NoSuchFieldError -> 0x008c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x008c }
                r2 = 14
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x008c }
            L_0x008c:
                expo.modules.blur.enums.TintStyle r1 = expo.modules.blur.enums.TintStyle.SYSTEM_CHROME_MATERIAL_LIGHT     // Catch:{ NoSuchFieldError -> 0x0096 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0096 }
                r2 = 15
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0096 }
            L_0x0096:
                expo.modules.blur.enums.TintStyle r1 = expo.modules.blur.enums.TintStyle.SYSTEM_THICK_MATERIAL_DARK     // Catch:{ NoSuchFieldError -> 0x00a0 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a0 }
                r2 = 16
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00a0 }
            L_0x00a0:
                expo.modules.blur.enums.TintStyle r1 = expo.modules.blur.enums.TintStyle.SYSTEM_THIN_MATERIAL_LIGHT     // Catch:{ NoSuchFieldError -> 0x00aa }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00aa }
                r2 = 17
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00aa }
            L_0x00aa:
                expo.modules.blur.enums.TintStyle r1 = expo.modules.blur.enums.TintStyle.SYSTEM_THIN_MATERIAL_DARK     // Catch:{ NoSuchFieldError -> 0x00b4 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00b4 }
                r2 = 18
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00b4 }
            L_0x00b4:
                expo.modules.blur.enums.TintStyle r1 = expo.modules.blur.enums.TintStyle.SYSTEM_ULTRA_THIN_MATERIAL_DARK     // Catch:{ NoSuchFieldError -> 0x00be }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00be }
                r2 = 19
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00be }
            L_0x00be:
                expo.modules.blur.enums.TintStyle r1 = expo.modules.blur.enums.TintStyle.SYSTEM_CHROME_MATERIAL_DARK     // Catch:{ NoSuchFieldError -> 0x00c8 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00c8 }
                r2 = 20
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00c8 }
            L_0x00c8:
                expo.modules.blur.enums.TintStyle r1 = expo.modules.blur.enums.TintStyle.SYSTEM_THIN_MATERIAL     // Catch:{ NoSuchFieldError -> 0x00d2 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00d2 }
                r2 = 21
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00d2 }
            L_0x00d2:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: expo.modules.blur.enums.TintStyle.WhenMappings.<clinit>():void");
        }
    }

    public static EnumEntries<TintStyle> getEntries() {
        return $ENTRIES;
    }

    private TintStyle(String str) {
        this.value = str;
    }

    public final String getValue() {
        return this.value;
    }

    static {
        TintStyle[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries((E[]) (Enum[]) $values);
    }

    public final int toBlurEffect(float f) {
        switch (WhenMappings.$EnumSwitchMapping$0[ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                return LIGHT.toColorInt(f);
            case 6:
            case 7:
            case 8:
                return DEFAULT.toColorInt(f);
            case 9:
            case 10:
                return DARK.toColorInt(f);
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
                return toColorInt(f);
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        return (((((int) r0) << 24) + 16711680) + androidx.core.view.MotionEventCompat.ACTION_POINTER_INDEX_MASK) + 255;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        return (((((int) r0) << 24) + 13041664) + 50944) + 199;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        return (((((int) (r0 * r2)) << 24) + 2424832) + 9472) + 37;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int toColorInt(float r17) {
        /*
            r16 = this;
            r0 = 100
            float r0 = (float) r0
            float r0 = r17 / r0
            int[] r1 = expo.modules.blur.enums.TintStyle.WhenMappings.$EnumSwitchMapping$0
            int r2 = r16.ordinal()
            r1 = r1[r2]
            r2 = 2
            r3 = 4605200834963974390(0x3fe8f5c28f5c28f6, double:0.78)
            r5 = 255(0xff, float:3.57E-43)
            if (r1 == r2) goto L_0x00dd
            r2 = 9
            if (r1 == r2) goto L_0x00c9
            r2 = 50944(0xc700, float:7.1388E-41)
            r6 = 13041664(0xc70000, float:1.8275264E-38)
            r7 = 4604930618986332160(0x3fe8000000000000, double:0.75)
            r9 = 4601597955262077993(0x3fdc28f5c28f5c29, double:0.44)
            r11 = 2424832(0x250000, float:3.397913E-39)
            r12 = 4606912202822375178(0x3fef0a3d70a3d70a, double:0.97)
            r14 = 65280(0xff00, float:9.1477E-41)
            r15 = 16711680(0xff0000, float:2.3418052E-38)
            switch(r1) {
                case 11: goto L_0x00b3;
                case 12: goto L_0x00a2;
                case 13: goto L_0x0091;
                case 14: goto L_0x008c;
                case 15: goto L_0x0087;
                case 16: goto L_0x0075;
                case 17: goto L_0x0068;
                case 18: goto L_0x005f;
                case 19: goto L_0x0056;
                case 20: goto L_0x0047;
                case 21: goto L_0x0042;
                default: goto L_0x0036;
            }
        L_0x0036:
            float r1 = (float) r5
            float r1 = r1 * r0
            double r0 = (double) r1
            double r0 = r0 * r9
        L_0x003a:
            int r0 = (int) r0
            int r0 = r0 << 24
            int r0 = r0 + r15
            int r0 = r0 + r14
            int r0 = r0 + r5
            goto L_0x00ed
        L_0x0042:
            float r1 = (float) r5
            float r1 = r1 * r0
            double r0 = (double) r1
            double r0 = r0 * r12
            goto L_0x006c
        L_0x0047:
            float r1 = (float) r5
            float r1 = r1 * r0
            double r0 = (double) r1
            double r0 = r0 * r7
            int r0 = (int) r0
            int r0 = r0 << 24
            int r0 = r0 + 0
            int r0 = r0 + 0
            int r0 = r0 + 0
            goto L_0x00ed
        L_0x0056:
            float r1 = (float) r5
            float r1 = r1 * r0
            double r0 = (double) r1
            r2 = 4603129179135383962(0x3fe199999999999a, double:0.55)
            goto L_0x007d
        L_0x005f:
            float r1 = (float) r5
            float r1 = r1 * r0
            double r0 = (double) r1
            r2 = 4604480259023595110(0x3fe6666666666666, double:0.7)
            goto L_0x007d
        L_0x0068:
            float r1 = (float) r5
            float r1 = r1 * r0
            double r0 = (double) r1
            double r0 = r0 * r3
        L_0x006c:
            int r0 = (int) r0
            int r0 = r0 << 24
            int r0 = r0 + r6
            int r0 = r0 + r2
            int r0 = r0 + 199
            goto L_0x00ed
        L_0x0075:
            float r1 = (float) r5
            float r1 = r1 * r0
            double r0 = (double) r1
            r2 = 4606281698874543309(0x3feccccccccccccd, double:0.9)
        L_0x007d:
            double r0 = r0 * r2
            int r0 = (int) r0
            int r0 = r0 << 24
            int r0 = r0 + r11
            int r0 = r0 + 9472
            int r0 = r0 + 37
            goto L_0x00ed
        L_0x0087:
            float r1 = (float) r5
            float r1 = r1 * r0
            double r0 = (double) r1
            double r0 = r0 * r12
            goto L_0x003a
        L_0x008c:
            float r1 = (float) r5
            float r1 = r1 * r0
            double r0 = (double) r1
            double r0 = r0 * r7
            goto L_0x003a
        L_0x0091:
            float r1 = (float) r5
            float r1 = r1 * r0
            double r0 = (double) r1
            double r0 = r0 * r12
            int r0 = (int) r0
            int r0 = r0 << 24
            r1 = 10027008(0x990000, float:1.4050831E-38)
            int r0 = r0 + r1
            r1 = 39168(0x9900, float:5.4886E-41)
            int r0 = r0 + r1
            int r0 = r0 + 153
            goto L_0x00ed
        L_0x00a2:
            float r1 = (float) r5
            float r1 = r1 * r0
            double r0 = (double) r1
            double r0 = r0 * r9
            int r0 = (int) r0
            int r0 = r0 << 24
            r1 = 12517376(0xbf0000, float:1.754058E-38)
            int r0 = r0 + r1
            r1 = 48896(0xbf00, float:6.8518E-41)
            int r0 = r0 + r1
            int r0 = r0 + 191
            goto L_0x00ed
        L_0x00b3:
            float r1 = (float) r5
            float r1 = r1 * r0
            double r0 = (double) r1
            r2 = 4605561122934164029(0x3fea3d70a3d70a3d, double:0.82)
            double r0 = r0 * r2
            int r0 = (int) r0
            int r0 = r0 << 24
            r1 = 11730944(0xb30000, float:1.6438554E-38)
            int r0 = r0 + r1
            r1 = 45824(0xb300, float:6.4213E-41)
            int r0 = r0 + r1
            int r0 = r0 + 179
            goto L_0x00ed
        L_0x00c9:
            float r1 = (float) r5
            float r1 = r1 * r0
            double r0 = (double) r1
            r2 = 4604390187031047700(0x3fe6147ae147ae14, double:0.69)
            double r0 = r0 * r2
            int r0 = (int) r0
            int r0 = r0 << 24
            r1 = 1638400(0x190000, float:2.295887E-39)
            int r0 = r0 + r1
            int r0 = r0 + 6400
            int r0 = r0 + 25
            goto L_0x00ed
        L_0x00dd:
            float r1 = (float) r5
            float r1 = r1 * r0
            double r0 = (double) r1
            double r0 = r0 * r3
            int r0 = (int) r0
            int r0 = r0 << 24
            r1 = 16318464(0xf90000, float:2.2867039E-38)
            int r0 = r0 + r1
            r1 = 63744(0xf900, float:8.9324E-41)
            int r0 = r0 + r1
            int r0 = r0 + 249
        L_0x00ed:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.blur.enums.TintStyle.toColorInt(float):int");
    }
}
