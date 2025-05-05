package coil.decode;

import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.SetsKt;

@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0016\u0010\b\u001a\u00020\u0004*\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0002H\u0000\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0004¢\u0006\u0002\n\u0000\"\u0018\u0010\u0003\u001a\u00020\u0004*\u00020\u00058@X\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0006\"\u0018\u0010\u0007\u001a\u00020\u0004*\u00020\u00058@X\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0006¨\u0006\u000b"}, d2 = {"RESPECT_PERFORMANCE_MIME_TYPES", "", "", "isRotated", "", "Lcoil/decode/ExifData;", "(Lcoil/decode/ExifData;)Z", "isSwapped", "supports", "Lcoil/decode/ExifOrientationPolicy;", "mimeType", "coil-base_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: ExifUtils.kt */
public final class ExifUtilsKt {
    private static final Set<String> RESPECT_PERFORMANCE_MIME_TYPES = SetsKt.setOf("image/jpeg", "image/webp", "image/heic", "image/heif");

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: ExifUtils.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|2|3|4|5|6|7|9) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        static {
            /*
                coil.decode.ExifOrientationPolicy[] r0 = coil.decode.ExifOrientationPolicy.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                coil.decode.ExifOrientationPolicy r1 = coil.decode.ExifOrientationPolicy.RESPECT_PERFORMANCE     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                coil.decode.ExifOrientationPolicy r1 = coil.decode.ExifOrientationPolicy.IGNORE     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                coil.decode.ExifOrientationPolicy r1 = coil.decode.ExifOrientationPolicy.RESPECT_ALL     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: coil.decode.ExifUtilsKt.WhenMappings.<clinit>():void");
        }
    }

    public static final boolean isSwapped(ExifData exifData) {
        return exifData.getRotationDegrees() == 90 || exifData.getRotationDegrees() == 270;
    }

    public static final boolean isRotated(ExifData exifData) {
        return exifData.getRotationDegrees() > 0;
    }

    public static final boolean supports(ExifOrientationPolicy exifOrientationPolicy, String str) {
        int i = WhenMappings.$EnumSwitchMapping$0[exifOrientationPolicy.ordinal()];
        if (i != 1) {
            if (i == 2) {
                return false;
            }
            if (i != 3) {
                throw new NoWhenBranchMatchedException();
            }
        } else if (str == null || !RESPECT_PERFORMANCE_MIME_TYPES.contains(str)) {
            return false;
        }
        return true;
    }
}
