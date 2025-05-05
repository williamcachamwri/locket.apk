package expo.modules.camera.next;

import expo.modules.kotlin.records.Field;
import expo.modules.kotlin.records.Record;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b(\b\b\u0018\u00002\u00020\u0001Be\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\u0016\b\u0002\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\b\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\f\u001a\u00020\u0005\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u000e¢\u0006\u0002\u0010\u0010J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\u0005HÆ\u0003J\t\u0010*\u001a\u00020\u0005HÆ\u0003J\u0017\u0010+\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\bHÆ\u0003J\t\u0010,\u001a\u00020\u0005HÆ\u0003J\t\u0010-\u001a\u00020\u0005HÆ\u0003J\u0010\u0010.\u001a\u0004\u0018\u00010\u000eHÆ\u0003¢\u0006\u0002\u0010\u001eJ\t\u0010/\u001a\u00020\u000eHÆ\u0003Jn\u00100\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\u0016\b\u0002\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\b2\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u00052\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000eHÆ\u0001¢\u0006\u0002\u00101J\u0013\u00102\u001a\u00020\u00052\b\u00103\u001a\u0004\u0018\u00010\nHÖ\u0003J\t\u00104\u001a\u00020\u000eHÖ\u0001J\t\u00105\u001a\u00020\tHÖ\u0001R*\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\b8\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u0012\u001a\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0006\u001a\u00020\u00058\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0018\u0010\u0012\u001a\u0004\b\u0019\u0010\u0017R\u001c\u0010\f\u001a\u00020\u00058\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001a\u0010\u0012\u001a\u0004\b\u001b\u0010\u0017R \u0010\r\u001a\u0004\u0018\u00010\u000e8\u0006X\u0004¢\u0006\u0010\n\u0002\u0010\u001f\u0012\u0004\b\u001c\u0010\u0012\u001a\u0004\b\u001d\u0010\u001eR\u001c\u0010\u000f\u001a\u00020\u000e8\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b \u0010\u0012\u001a\u0004\b!\u0010\"R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b#\u0010\u0012\u001a\u0004\b$\u0010%R\u001c\u0010\u000b\u001a\u00020\u00058\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b&\u0010\u0012\u001a\u0004\b'\u0010\u0017¨\u00066"}, d2 = {"Lexpo/modules/camera/next/PictureOptions;", "Lexpo/modules/kotlin/records/Record;", "quality", "", "base64", "", "exif", "additionalExif", "", "", "", "skipProcessing", "fastMode", "id", "", "maxDownsampling", "(DZZLjava/util/Map;ZZLjava/lang/Integer;I)V", "getAdditionalExif$annotations", "()V", "getAdditionalExif", "()Ljava/util/Map;", "getBase64$annotations", "getBase64", "()Z", "getExif$annotations", "getExif", "getFastMode$annotations", "getFastMode", "getId$annotations", "getId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getMaxDownsampling$annotations", "getMaxDownsampling", "()I", "getQuality$annotations", "getQuality", "()D", "getSkipProcessing$annotations", "getSkipProcessing", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(DZZLjava/util/Map;ZZLjava/lang/Integer;I)Lexpo/modules/camera/next/PictureOptions;", "equals", "other", "hashCode", "toString", "expo-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: Options.kt */
public final class PictureOptions implements Record {
    private final Map<String, Object> additionalExif;
    private final boolean base64;
    private final boolean exif;
    private final boolean fastMode;
    private final Integer id;
    private final int maxDownsampling;
    private final double quality;
    private final boolean skipProcessing;

    public PictureOptions() {
        this(0.0d, false, false, (Map) null, false, false, (Integer) null, 0, 255, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ PictureOptions copy$default(PictureOptions pictureOptions, double d, boolean z, boolean z2, Map map, boolean z3, boolean z4, Integer num, int i, int i2, Object obj) {
        PictureOptions pictureOptions2 = pictureOptions;
        int i3 = i2;
        return pictureOptions.copy((i3 & 1) != 0 ? pictureOptions2.quality : d, (i3 & 2) != 0 ? pictureOptions2.base64 : z, (i3 & 4) != 0 ? pictureOptions2.exif : z2, (i3 & 8) != 0 ? pictureOptions2.additionalExif : map, (i3 & 16) != 0 ? pictureOptions2.skipProcessing : z3, (i3 & 32) != 0 ? pictureOptions2.fastMode : z4, (i3 & 64) != 0 ? pictureOptions2.id : num, (i3 & 128) != 0 ? pictureOptions2.maxDownsampling : i);
    }

    @Field
    public static /* synthetic */ void getAdditionalExif$annotations() {
    }

    @Field
    public static /* synthetic */ void getBase64$annotations() {
    }

    @Field
    public static /* synthetic */ void getExif$annotations() {
    }

    @Field
    public static /* synthetic */ void getFastMode$annotations() {
    }

    @Field
    public static /* synthetic */ void getId$annotations() {
    }

    @Field
    public static /* synthetic */ void getMaxDownsampling$annotations() {
    }

    @Field
    public static /* synthetic */ void getQuality$annotations() {
    }

    @Field
    public static /* synthetic */ void getSkipProcessing$annotations() {
    }

    public final double component1() {
        return this.quality;
    }

    public final boolean component2() {
        return this.base64;
    }

    public final boolean component3() {
        return this.exif;
    }

    public final Map<String, Object> component4() {
        return this.additionalExif;
    }

    public final boolean component5() {
        return this.skipProcessing;
    }

    public final boolean component6() {
        return this.fastMode;
    }

    public final Integer component7() {
        return this.id;
    }

    public final int component8() {
        return this.maxDownsampling;
    }

    public final PictureOptions copy(double d, boolean z, boolean z2, Map<String, ? extends Object> map, boolean z3, boolean z4, Integer num, int i) {
        return new PictureOptions(d, z, z2, map, z3, z4, num, i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PictureOptions)) {
            return false;
        }
        PictureOptions pictureOptions = (PictureOptions) obj;
        return Double.compare(this.quality, pictureOptions.quality) == 0 && this.base64 == pictureOptions.base64 && this.exif == pictureOptions.exif && Intrinsics.areEqual((Object) this.additionalExif, (Object) pictureOptions.additionalExif) && this.skipProcessing == pictureOptions.skipProcessing && this.fastMode == pictureOptions.fastMode && Intrinsics.areEqual((Object) this.id, (Object) pictureOptions.id) && this.maxDownsampling == pictureOptions.maxDownsampling;
    }

    public int hashCode() {
        int hashCode = ((((Double.hashCode(this.quality) * 31) + Boolean.hashCode(this.base64)) * 31) + Boolean.hashCode(this.exif)) * 31;
        Map<String, Object> map = this.additionalExif;
        int i = 0;
        int hashCode2 = (((((hashCode + (map == null ? 0 : map.hashCode())) * 31) + Boolean.hashCode(this.skipProcessing)) * 31) + Boolean.hashCode(this.fastMode)) * 31;
        Integer num = this.id;
        if (num != null) {
            i = num.hashCode();
        }
        return ((hashCode2 + i) * 31) + Integer.hashCode(this.maxDownsampling);
    }

    public String toString() {
        double d = this.quality;
        boolean z = this.base64;
        boolean z2 = this.exif;
        Map<String, Object> map = this.additionalExif;
        boolean z3 = this.skipProcessing;
        boolean z4 = this.fastMode;
        Integer num = this.id;
        return "PictureOptions(quality=" + d + ", base64=" + z + ", exif=" + z2 + ", additionalExif=" + map + ", skipProcessing=" + z3 + ", fastMode=" + z4 + ", id=" + num + ", maxDownsampling=" + this.maxDownsampling + ")";
    }

    public PictureOptions(double d, boolean z, boolean z2, Map<String, ? extends Object> map, boolean z3, boolean z4, Integer num, int i) {
        this.quality = d;
        this.base64 = z;
        this.exif = z2;
        this.additionalExif = map;
        this.skipProcessing = z3;
        this.fastMode = z4;
        this.id = num;
        this.maxDownsampling = i;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ PictureOptions(double r11, boolean r13, boolean r14, java.util.Map r15, boolean r16, boolean r17, java.lang.Integer r18, int r19, int r20, kotlin.jvm.internal.DefaultConstructorMarker r21) {
        /*
            r10 = this;
            r0 = r20
            r1 = r0 & 1
            if (r1 == 0) goto L_0x0009
            r1 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            goto L_0x000a
        L_0x0009:
            r1 = r11
        L_0x000a:
            r3 = r0 & 2
            r4 = 0
            if (r3 == 0) goto L_0x0011
            r3 = r4
            goto L_0x0012
        L_0x0011:
            r3 = r13
        L_0x0012:
            r5 = r0 & 4
            if (r5 == 0) goto L_0x0018
            r5 = r4
            goto L_0x0019
        L_0x0018:
            r5 = r14
        L_0x0019:
            r6 = r0 & 8
            r7 = 0
            if (r6 == 0) goto L_0x0020
            r6 = r7
            goto L_0x0021
        L_0x0020:
            r6 = r15
        L_0x0021:
            r8 = r0 & 16
            if (r8 == 0) goto L_0x0027
            r8 = r4
            goto L_0x0029
        L_0x0027:
            r8 = r16
        L_0x0029:
            r9 = r0 & 32
            if (r9 == 0) goto L_0x002e
            goto L_0x0030
        L_0x002e:
            r4 = r17
        L_0x0030:
            r9 = r0 & 64
            if (r9 == 0) goto L_0x0035
            goto L_0x0037
        L_0x0035:
            r7 = r18
        L_0x0037:
            r0 = r0 & 128(0x80, float:1.794E-43)
            if (r0 == 0) goto L_0x003d
            r0 = 1
            goto L_0x003f
        L_0x003d:
            r0 = r19
        L_0x003f:
            r11 = r10
            r12 = r1
            r14 = r3
            r15 = r5
            r16 = r6
            r17 = r8
            r18 = r4
            r19 = r7
            r20 = r0
            r11.<init>(r12, r14, r15, r16, r17, r18, r19, r20)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.camera.next.PictureOptions.<init>(double, boolean, boolean, java.util.Map, boolean, boolean, java.lang.Integer, int, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final double getQuality() {
        return this.quality;
    }

    public final boolean getBase64() {
        return this.base64;
    }

    public final boolean getExif() {
        return this.exif;
    }

    public final Map<String, Object> getAdditionalExif() {
        return this.additionalExif;
    }

    public final boolean getSkipProcessing() {
        return this.skipProcessing;
    }

    public final boolean getFastMode() {
        return this.fastMode;
    }

    public final Integer getId() {
        return this.id;
    }

    public final int getMaxDownsampling() {
        return this.maxDownsampling;
    }
}
