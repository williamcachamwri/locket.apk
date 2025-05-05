package expo.modules.camera.next;

import expo.modules.interfaces.barcodescanner.BarCodeScannerResult;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lexpo/modules/interfaces/barcodescanner/BarCodeScannerResult;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ExpoCameraView.kt */
final class ExpoCameraView$createImageAnalyzer$1$1 extends Lambda implements Function1<BarCodeScannerResult, Unit> {
    final /* synthetic */ ExpoCameraView this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ExpoCameraView$createImageAnalyzer$1$1(ExpoCameraView expoCameraView) {
        super(1);
        this.this$0 = expoCameraView;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((BarCodeScannerResult) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(BarCodeScannerResult barCodeScannerResult) {
        Intrinsics.checkNotNullParameter(barCodeScannerResult, "it");
        this.this$0.onBarcodeScanned(barCodeScannerResult);
    }
}
