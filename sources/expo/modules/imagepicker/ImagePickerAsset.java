package expo.modules.imagepicker;

import android.os.Bundle;
import expo.modules.kotlin.records.Field;
import expo.modules.kotlin.records.Record;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b%\b\u0000\u0018\u00002\u00020\u0001B\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\u0013R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u001e\u0010\u000e\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0018\u0010\u0015\u001a\u0004\b\u0019\u0010\u0017R \u0010\u0011\u001a\u0004\u0018\u00010\b8\u0006X\u0004¢\u0006\u0010\n\u0002\u0010\u001d\u0012\u0004\b\u001a\u0010\u0015\u001a\u0004\b\u001b\u0010\u001cR\u001e\u0010\u000f\u001a\u0004\u0018\u00010\u00108\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001e\u0010\u0015\u001a\u0004\b\u001f\u0010 R\u001e\u0010\n\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b!\u0010\u0015\u001a\u0004\b\"\u0010\u0017R \u0010\u000b\u001a\u0004\u0018\u00010\f8\u0006X\u0004¢\u0006\u0010\n\u0002\u0010&\u0012\u0004\b#\u0010\u0015\u001a\u0004\b$\u0010%R\u001c\u0010\t\u001a\u00020\b8\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b'\u0010\u0015\u001a\u0004\b(\u0010)R\u001e\u0010\r\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b*\u0010\u0015\u001a\u0004\b+\u0010\u0017R \u0010\u0012\u001a\u0004\u0018\u00010\b8\u0006X\u0004¢\u0006\u0010\n\u0002\u0010\u001d\u0012\u0004\b,\u0010\u0015\u001a\u0004\b-\u0010\u001cR\u001c\u0010\u0004\u001a\u00020\u00058\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b.\u0010\u0015\u001a\u0004\b/\u00100R\u001c\u0010\u0006\u001a\u00020\u00038\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b1\u0010\u0015\u001a\u0004\b2\u0010\u0017R\u001c\u0010\u0007\u001a\u00020\b8\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b3\u0010\u0015\u001a\u0004\b4\u0010)¨\u00065"}, d2 = {"Lexpo/modules/imagepicker/ImagePickerAsset;", "Lexpo/modules/kotlin/records/Record;", "assetId", "", "type", "Lexpo/modules/imagepicker/MediaType;", "uri", "width", "", "height", "fileName", "filesize", "", "mimeType", "base64", "exif", "Landroid/os/Bundle;", "duration", "rotation", "(Ljava/lang/String;Lexpo/modules/imagepicker/MediaType;Ljava/lang/String;IILjava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Landroid/os/Bundle;Ljava/lang/Integer;Ljava/lang/Integer;)V", "getAssetId$annotations", "()V", "getAssetId", "()Ljava/lang/String;", "getBase64$annotations", "getBase64", "getDuration$annotations", "getDuration", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getExif$annotations", "getExif", "()Landroid/os/Bundle;", "getFileName$annotations", "getFileName", "getFilesize$annotations", "getFilesize", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getHeight$annotations", "getHeight", "()I", "getMimeType$annotations", "getMimeType", "getRotation$annotations", "getRotation", "getType$annotations", "getType", "()Lexpo/modules/imagepicker/MediaType;", "getUri$annotations", "getUri", "getWidth$annotations", "getWidth", "expo-image-picker_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ImagePickerResponse.kt */
public final class ImagePickerAsset implements Record {
    private final String assetId;
    private final String base64;
    private final Integer duration;
    private final Bundle exif;
    private final String fileName;
    private final Long filesize;
    private final int height;
    private final String mimeType;
    private final Integer rotation;
    private final MediaType type;
    private final String uri;
    private final int width;

    public ImagePickerAsset() {
        this((String) null, (MediaType) null, (String) null, 0, 0, (String) null, (Long) null, (String) null, (String) null, (Bundle) null, (Integer) null, (Integer) null, 4095, (DefaultConstructorMarker) null);
    }

    @Field
    public static /* synthetic */ void getAssetId$annotations() {
    }

    @Field
    public static /* synthetic */ void getBase64$annotations() {
    }

    @Field
    public static /* synthetic */ void getDuration$annotations() {
    }

    @Field
    public static /* synthetic */ void getExif$annotations() {
    }

    @Field
    public static /* synthetic */ void getFileName$annotations() {
    }

    @Field
    public static /* synthetic */ void getFilesize$annotations() {
    }

    @Field
    public static /* synthetic */ void getHeight$annotations() {
    }

    @Field
    public static /* synthetic */ void getMimeType$annotations() {
    }

    @Field
    public static /* synthetic */ void getRotation$annotations() {
    }

    @Field
    public static /* synthetic */ void getType$annotations() {
    }

    @Field
    public static /* synthetic */ void getUri$annotations() {
    }

    @Field
    public static /* synthetic */ void getWidth$annotations() {
    }

    public ImagePickerAsset(String str, MediaType mediaType, String str2, int i, int i2, String str3, Long l, String str4, String str5, Bundle bundle, Integer num, Integer num2) {
        Intrinsics.checkNotNullParameter(mediaType, "type");
        Intrinsics.checkNotNullParameter(str2, "uri");
        this.assetId = str;
        this.type = mediaType;
        this.uri = str2;
        this.width = i;
        this.height = i2;
        this.fileName = str3;
        this.filesize = l;
        this.mimeType = str4;
        this.base64 = str5;
        this.exif = bundle;
        this.duration = num;
        this.rotation = num2;
    }

    public final String getAssetId() {
        return this.assetId;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ ImagePickerAsset(java.lang.String r14, expo.modules.imagepicker.MediaType r15, java.lang.String r16, int r17, int r18, java.lang.String r19, java.lang.Long r20, java.lang.String r21, java.lang.String r22, android.os.Bundle r23, java.lang.Integer r24, java.lang.Integer r25, int r26, kotlin.jvm.internal.DefaultConstructorMarker r27) {
        /*
            r13 = this;
            r0 = r26
            r1 = r0 & 1
            r2 = 0
            if (r1 == 0) goto L_0x0009
            r1 = r2
            goto L_0x000a
        L_0x0009:
            r1 = r14
        L_0x000a:
            r3 = r0 & 2
            if (r3 == 0) goto L_0x0011
            expo.modules.imagepicker.MediaType r3 = expo.modules.imagepicker.MediaType.IMAGE
            goto L_0x0012
        L_0x0011:
            r3 = r15
        L_0x0012:
            r4 = r0 & 4
            if (r4 == 0) goto L_0x0019
            java.lang.String r4 = ""
            goto L_0x001b
        L_0x0019:
            r4 = r16
        L_0x001b:
            r5 = r0 & 8
            r6 = 0
            if (r5 == 0) goto L_0x0022
            r5 = r6
            goto L_0x0024
        L_0x0022:
            r5 = r17
        L_0x0024:
            r7 = r0 & 16
            if (r7 == 0) goto L_0x0029
            goto L_0x002b
        L_0x0029:
            r6 = r18
        L_0x002b:
            r7 = r0 & 32
            if (r7 == 0) goto L_0x0031
            r7 = r2
            goto L_0x0033
        L_0x0031:
            r7 = r19
        L_0x0033:
            r8 = r0 & 64
            if (r8 == 0) goto L_0x0039
            r8 = r2
            goto L_0x003b
        L_0x0039:
            r8 = r20
        L_0x003b:
            r9 = r0 & 128(0x80, float:1.794E-43)
            if (r9 == 0) goto L_0x0041
            r9 = r2
            goto L_0x0043
        L_0x0041:
            r9 = r21
        L_0x0043:
            r10 = r0 & 256(0x100, float:3.59E-43)
            if (r10 == 0) goto L_0x0049
            r10 = r2
            goto L_0x004b
        L_0x0049:
            r10 = r22
        L_0x004b:
            r11 = r0 & 512(0x200, float:7.175E-43)
            if (r11 == 0) goto L_0x0051
            r11 = r2
            goto L_0x0053
        L_0x0051:
            r11 = r23
        L_0x0053:
            r12 = r0 & 1024(0x400, float:1.435E-42)
            if (r12 == 0) goto L_0x0059
            r12 = r2
            goto L_0x005b
        L_0x0059:
            r12 = r24
        L_0x005b:
            r0 = r0 & 2048(0x800, float:2.87E-42)
            if (r0 == 0) goto L_0x0060
            goto L_0x0062
        L_0x0060:
            r2 = r25
        L_0x0062:
            r14 = r13
            r15 = r1
            r16 = r3
            r17 = r4
            r18 = r5
            r19 = r6
            r20 = r7
            r21 = r8
            r22 = r9
            r23 = r10
            r24 = r11
            r25 = r12
            r26 = r2
            r14.<init>(r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.imagepicker.ImagePickerAsset.<init>(java.lang.String, expo.modules.imagepicker.MediaType, java.lang.String, int, int, java.lang.String, java.lang.Long, java.lang.String, java.lang.String, android.os.Bundle, java.lang.Integer, java.lang.Integer, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final MediaType getType() {
        return this.type;
    }

    public final String getUri() {
        return this.uri;
    }

    public final int getWidth() {
        return this.width;
    }

    public final int getHeight() {
        return this.height;
    }

    public final String getFileName() {
        return this.fileName;
    }

    public final Long getFilesize() {
        return this.filesize;
    }

    public final String getMimeType() {
        return this.mimeType;
    }

    public final String getBase64() {
        return this.base64;
    }

    public final Bundle getExif() {
        return this.exif;
    }

    public final Integer getDuration() {
        return this.duration;
    }

    public final Integer getRotation() {
        return this.rotation;
    }
}
