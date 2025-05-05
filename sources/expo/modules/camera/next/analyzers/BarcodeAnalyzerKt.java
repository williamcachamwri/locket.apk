package expo.modules.camera.next.analyzers;

import androidx.camera.core.ImageProxy;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0002\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"toByteArray", "", "Ljava/nio/ByteBuffer;", "", "Landroidx/camera/core/ImageProxy$PlaneProxy;", "([Landroidx/camera/core/ImageProxy$PlaneProxy;)[B", "expo-camera_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: BarcodeAnalyzer.kt */
public final class BarcodeAnalyzerKt {
    private static final byte[] toByteArray(ByteBuffer byteBuffer) {
        byteBuffer.rewind();
        byte[] bArr = new byte[byteBuffer.remaining()];
        byteBuffer.get(bArr);
        return bArr;
    }

    public static final byte[] toByteArray(ImageProxy.PlaneProxy[] planeProxyArr) {
        Intrinsics.checkNotNullParameter(planeProxyArr, "<this>");
        List arrayList = new ArrayList();
        for (ImageProxy.PlaneProxy buffer : planeProxyArr) {
            ByteBuffer buffer2 = buffer.getBuffer();
            Intrinsics.checkNotNullExpressionValue(buffer2, "getBuffer(...)");
            arrayList.addAll(ArraysKt.toList(toByteArray(buffer2)));
        }
        return CollectionsKt.toByteArray(arrayList);
    }
}
