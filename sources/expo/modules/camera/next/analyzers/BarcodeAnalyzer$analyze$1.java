package expo.modules.camera.next.analyzers;

import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.common.InputImage;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\u0010\u0000\u001a\u00020\u00012*\u0010\u0002\u001a&\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u0004 \u0005*\u0012\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u0004\u0018\u00010\u00060\u0003H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "barcodes", "", "Lcom/google/mlkit/vision/barcode/common/Barcode;", "kotlin.jvm.PlatformType", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: BarcodeAnalyzer.kt */
final class BarcodeAnalyzer$analyze$1 extends Lambda implements Function1<List<Barcode>, Unit> {
    final /* synthetic */ InputImage $image;
    final /* synthetic */ BarcodeAnalyzer this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BarcodeAnalyzer$analyze$1(BarcodeAnalyzer barcodeAnalyzer, InputImage inputImage) {
        super(1);
        this.this$0 = barcodeAnalyzer;
        this.$image = inputImage;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((List<Barcode>) (List) obj);
        return Unit.INSTANCE;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0035  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void invoke(java.util.List<com.google.mlkit.vision.barcode.common.Barcode> r10) {
        /*
            r9 = this;
            boolean r0 = r10.isEmpty()
            if (r0 == 0) goto L_0x0007
            return
        L_0x0007:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r10)
            java.lang.Object r10 = kotlin.collections.CollectionsKt.first(r10)
            com.google.mlkit.vision.barcode.common.Barcode r10 = (com.google.mlkit.vision.barcode.common.Barcode) r10
            java.lang.String r0 = r10.getRawValue()
            if (r0 != 0) goto L_0x0026
            byte[] r0 = r10.getRawBytes()
            if (r0 == 0) goto L_0x0025
            java.lang.String r1 = new java.lang.String
            java.nio.charset.Charset r2 = kotlin.text.Charsets.UTF_8
            r1.<init>(r0, r2)
            r4 = r1
            goto L_0x0027
        L_0x0025:
            r0 = 0
        L_0x0026:
            r4 = r0
        L_0x0027:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r5 = r0
            java.util.List r5 = (java.util.List) r5
            android.graphics.Point[] r0 = r10.getCornerPoints()
            if (r0 == 0) goto L_0x005c
            int r1 = r0.length
            r2 = 0
            r3 = r2
        L_0x0038:
            if (r3 >= r1) goto L_0x005c
            r6 = r0[r3]
            r7 = 2
            java.lang.Integer[] r7 = new java.lang.Integer[r7]
            int r8 = r6.x
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            r7[r2] = r8
            int r6 = r6.y
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            r8 = 1
            r7[r8] = r6
            java.util.List r6 = kotlin.collections.CollectionsKt.listOf(r7)
            java.util.Collection r6 = (java.util.Collection) r6
            r5.addAll(r6)
            int r3 = r3 + 1
            goto L_0x0038
        L_0x005c:
            expo.modules.camera.next.analyzers.BarcodeAnalyzer r0 = r9.this$0
            kotlin.jvm.functions.Function1 r0 = r0.getOnComplete()
            expo.modules.interfaces.barcodescanner.BarCodeScannerResult r8 = new expo.modules.interfaces.barcodescanner.BarCodeScannerResult
            int r2 = r10.getFormat()
            java.lang.String r3 = r10.getDisplayValue()
            com.google.mlkit.vision.common.InputImage r10 = r9.$image
            int r6 = r10.getWidth()
            com.google.mlkit.vision.common.InputImage r10 = r9.$image
            int r7 = r10.getHeight()
            r1 = r8
            r1.<init>(r2, r3, r4, r5, r6, r7)
            r0.invoke(r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.camera.next.analyzers.BarcodeAnalyzer$analyze$1.invoke(java.util.List):void");
    }
}
