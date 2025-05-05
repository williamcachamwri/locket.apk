package expo.modules.camera.next;

import androidx.core.app.NotificationCompat;
import expo.modules.camera.BarcodeScannedEvent;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "event", "Lexpo/modules/camera/BarcodeScannedEvent;", "invoke", "(Lexpo/modules/camera/BarcodeScannedEvent;)Ljava/lang/Short;"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ExpoCameraView.kt */
final class ExpoCameraView$onBarcodeScanned$2 extends Lambda implements Function1<BarcodeScannedEvent, Short> {
    public static final ExpoCameraView$onBarcodeScanned$2 INSTANCE = new ExpoCameraView$onBarcodeScanned$2();

    ExpoCameraView$onBarcodeScanned$2() {
        super(1);
    }

    public final Short invoke(BarcodeScannedEvent barcodeScannedEvent) {
        Intrinsics.checkNotNullParameter(barcodeScannedEvent, NotificationCompat.CATEGORY_EVENT);
        return Short.valueOf((short) (barcodeScannedEvent.getData().hashCode() % 32767));
    }
}
