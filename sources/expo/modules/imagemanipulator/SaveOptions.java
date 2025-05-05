package expo.modules.imagemanipulator;

import android.graphics.Bitmap;
import android.os.Build;
import expo.modules.kotlin.records.Field;
import expo.modules.kotlin.records.Record;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B#\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0007HÆ\u0003J'\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u00032\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fHÖ\u0003J\t\u0010 \u001a\u00020!HÖ\u0001J\t\u0010\"\u001a\u00020#HÖ\u0001R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R$\u0010\u0006\u001a\u00020\u00078\u0006@\u0006X\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0014\u0010\n\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u0006$"}, d2 = {"Lexpo/modules/imagemanipulator/SaveOptions;", "Lexpo/modules/kotlin/records/Record;", "base64", "", "compress", "", "format", "Lexpo/modules/imagemanipulator/ImageFormat;", "(ZDLexpo/modules/imagemanipulator/ImageFormat;)V", "getBase64$annotations", "()V", "getBase64", "()Z", "getCompress$annotations", "getCompress", "()D", "compressFormat", "Landroid/graphics/Bitmap$CompressFormat;", "getCompressFormat", "()Landroid/graphics/Bitmap$CompressFormat;", "getFormat$annotations", "getFormat", "()Lexpo/modules/imagemanipulator/ImageFormat;", "setFormat", "(Lexpo/modules/imagemanipulator/ImageFormat;)V", "component1", "component2", "component3", "copy", "equals", "other", "", "hashCode", "", "toString", "", "expo-image-manipulator_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ManipulationArguments.kt */
public final class SaveOptions implements Record {
    private final boolean base64;
    private final double compress;
    private ImageFormat format;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: ManipulationArguments.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(11:0|1|2|3|4|5|6|7|8|9|11) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        static {
            /*
                expo.modules.imagemanipulator.ImageFormat[] r0 = expo.modules.imagemanipulator.ImageFormat.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                expo.modules.imagemanipulator.ImageFormat r1 = expo.modules.imagemanipulator.ImageFormat.JPEG     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                expo.modules.imagemanipulator.ImageFormat r1 = expo.modules.imagemanipulator.ImageFormat.JPG     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                expo.modules.imagemanipulator.ImageFormat r1 = expo.modules.imagemanipulator.ImageFormat.PNG     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                expo.modules.imagemanipulator.ImageFormat r1 = expo.modules.imagemanipulator.ImageFormat.WEBP     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: expo.modules.imagemanipulator.SaveOptions.WhenMappings.<clinit>():void");
        }
    }

    public SaveOptions() {
        this(false, 0.0d, (ImageFormat) null, 7, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ SaveOptions copy$default(SaveOptions saveOptions, boolean z, double d, ImageFormat imageFormat, int i, Object obj) {
        if ((i & 1) != 0) {
            z = saveOptions.base64;
        }
        if ((i & 2) != 0) {
            d = saveOptions.compress;
        }
        if ((i & 4) != 0) {
            imageFormat = saveOptions.format;
        }
        return saveOptions.copy(z, d, imageFormat);
    }

    @Field
    public static /* synthetic */ void getBase64$annotations() {
    }

    @Field
    public static /* synthetic */ void getCompress$annotations() {
    }

    @Field
    public static /* synthetic */ void getFormat$annotations() {
    }

    public final boolean component1() {
        return this.base64;
    }

    public final double component2() {
        return this.compress;
    }

    public final ImageFormat component3() {
        return this.format;
    }

    public final SaveOptions copy(boolean z, double d, ImageFormat imageFormat) {
        Intrinsics.checkNotNullParameter(imageFormat, "format");
        return new SaveOptions(z, d, imageFormat);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SaveOptions)) {
            return false;
        }
        SaveOptions saveOptions = (SaveOptions) obj;
        return this.base64 == saveOptions.base64 && Double.compare(this.compress, saveOptions.compress) == 0 && this.format == saveOptions.format;
    }

    public int hashCode() {
        return (((Boolean.hashCode(this.base64) * 31) + Double.hashCode(this.compress)) * 31) + this.format.hashCode();
    }

    public String toString() {
        boolean z = this.base64;
        double d = this.compress;
        return "SaveOptions(base64=" + z + ", compress=" + d + ", format=" + this.format + ")";
    }

    public SaveOptions(boolean z, double d, ImageFormat imageFormat) {
        Intrinsics.checkNotNullParameter(imageFormat, "format");
        this.base64 = z;
        this.compress = d;
        this.format = imageFormat;
    }

    public final boolean getBase64() {
        return this.base64;
    }

    public final double getCompress() {
        return this.compress;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ SaveOptions(boolean z, double d, ImageFormat imageFormat, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? 1.0d : d, (i & 4) != 0 ? ImageFormat.JPEG : imageFormat);
    }

    public final ImageFormat getFormat() {
        return this.format;
    }

    public final void setFormat(ImageFormat imageFormat) {
        Intrinsics.checkNotNullParameter(imageFormat, "<set-?>");
        this.format = imageFormat;
    }

    public final Bitmap.CompressFormat getCompressFormat() {
        int i = WhenMappings.$EnumSwitchMapping$0[this.format.ordinal()];
        if (i == 1 || i == 2) {
            return Bitmap.CompressFormat.JPEG;
        }
        if (i == 3) {
            return Bitmap.CompressFormat.PNG;
        }
        if (i != 4) {
            throw new NoWhenBranchMatchedException();
        } else if (Build.VERSION.SDK_INT >= 30) {
            return Bitmap.CompressFormat.WEBP_LOSSY;
        } else {
            return Bitmap.CompressFormat.WEBP;
        }
    }
}
