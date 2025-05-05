package com.adsbynimbus.request;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.media3.extractor.metadata.dvbsi.AppInfoTableDecoder;
import com.adsbynimbus.openrtb.enumerations.TitleLength;
import com.adsbynimbus.openrtb.request.Asset;
import com.adsbynimbus.openrtb.request.Format;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0002\u001a.\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u0004\u001a*\u0010\n\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\f\u001a\"\u0010\r\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0004\u001aQ\u0010\u000e\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u00042\b\b\u0002\u0010\u0013\u001a\u00020\u00042\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0015¢\u0006\u0002\u0010\u0016¨\u0006\u0017"}, d2 = {"data", "Lcom/adsbynimbus/openrtb/request/Asset;", "Lcom/adsbynimbus/openrtb/request/Asset$Companion;", "id", "", "required", "", "type", "", "length", "image", "format", "Lcom/adsbynimbus/openrtb/request/Format;", "title", "video", "mimes", "", "", "minduration", "maxduration", "protocols", "", "(Lcom/adsbynimbus/openrtb/request/Asset$Companion;IZ[Ljava/lang/String;II[B)Lcom/adsbynimbus/openrtb/request/Asset;", "request_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: AssetExtension.kt */
public final class AssetExtensionKt {
    public static final Asset title(Asset.Companion companion, int i, boolean z, int i2) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        return new Asset(i, RequestExtensions.getByteValue(z), (Map) null, new Asset.TitleObject(i2), (Asset.ImageObject) null, (Asset.VideoObject) null, (Asset.DataObject) null, (int) AppInfoTableDecoder.APPLICATION_INFORMATION_TABLE_ID, (DefaultConstructorMarker) null);
    }

    public static final Asset image(Asset.Companion companion, int i, boolean z, byte b, Format format) {
        Format format2 = format;
        Intrinsics.checkNotNullParameter(companion, "<this>");
        Intrinsics.checkNotNullParameter(format2, "format");
        return new Asset(i, RequestExtensions.getByteValue(z), (Map) null, (Asset.TitleObject) null, new Asset.ImageObject(b, Integer.valueOf(format2.w), Integer.valueOf(format2.h), (Integer) null, (Integer) null, 24, (DefaultConstructorMarker) null), (Asset.VideoObject) null, (Asset.DataObject) null, (int) AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ Asset video$default(Asset.Companion companion, int i, boolean z, String[] strArr, int i2, int i3, byte[] bArr, int i4, Object obj) {
        return video(companion, i, z, (i4 & 4) != 0 ? null : strArr, (i4 & 8) != 0 ? 1 : i2, (i4 & 16) != 0 ? 60 : i3, (i4 & 32) != 0 ? null : bArr);
    }

    public static final Asset video(Asset.Companion companion, int i, boolean z, String[] strArr, int i2, int i3, byte[] bArr) {
        Asset.Companion companion2 = companion;
        Intrinsics.checkNotNullParameter(companion, "<this>");
        String[] strArr2 = strArr;
        int i4 = i2;
        return new Asset(i, RequestExtensions.getByteValue(z), (Map) null, (Asset.TitleObject) null, (Asset.ImageObject) null, new Asset.VideoObject(strArr, i2, i3, bArr), (Asset.DataObject) null, 92, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ Asset data$default(Asset.Companion companion, int i, boolean z, byte b, int i2, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            b = 2;
        }
        if ((i3 & 8) != 0) {
            i2 = TitleLength.LONG;
        }
        return data(companion, i, z, b, i2);
    }

    public static final Asset data(Asset.Companion companion, int i, boolean z, byte b, int i2) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        return new Asset(i, RequestExtensions.getByteValue(z), (Map) null, (Asset.TitleObject) null, (Asset.ImageObject) null, (Asset.VideoObject) null, new Asset.DataObject(b, i2), 60, (DefaultConstructorMarker) null);
    }
}
