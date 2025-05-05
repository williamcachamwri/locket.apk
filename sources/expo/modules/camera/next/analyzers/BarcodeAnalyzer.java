package expo.modules.camera.next.analyzers;

import android.media.Image;
import android.util.Log;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageProxy;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.barcode.BarcodeScanner;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;
import com.google.mlkit.vision.barcode.BarcodeScanning;
import com.google.mlkit.vision.common.InputImage;
import expo.modules.camera.next.CameraViewHelper;
import expo.modules.camera.next.records.BarcodeType;
import expo.modules.camera.next.records.CameraType;
import expo.modules.interfaces.barcodescanner.BarCodeScannerResult;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\u0010\u000bJ\u0010\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u0016H\u0016R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0017"}, d2 = {"Lexpo/modules/camera/next/analyzers/BarcodeAnalyzer;", "Landroidx/camera/core/ImageAnalysis$Analyzer;", "lensFacing", "Lexpo/modules/camera/next/records/CameraType;", "formats", "", "Lexpo/modules/camera/next/records/BarcodeType;", "onComplete", "Lkotlin/Function1;", "Lexpo/modules/interfaces/barcodescanner/BarCodeScannerResult;", "", "(Lexpo/modules/camera/next/records/CameraType;Ljava/util/List;Lkotlin/jvm/functions/Function1;)V", "barcodeFormats", "", "barcodeScanner", "Lcom/google/mlkit/vision/barcode/BarcodeScanner;", "barcodeScannerOptions", "Lcom/google/mlkit/vision/barcode/BarcodeScannerOptions;", "getOnComplete", "()Lkotlin/jvm/functions/Function1;", "analyze", "imageProxy", "Landroidx/camera/core/ImageProxy;", "expo-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: BarcodeAnalyzer.kt */
public final class BarcodeAnalyzer implements ImageAnalysis.Analyzer {
    private final int barcodeFormats;
    private BarcodeScanner barcodeScanner;
    private BarcodeScannerOptions barcodeScannerOptions;
    private final CameraType lensFacing;
    private final Function1<BarCodeScannerResult, Unit> onComplete;

    public BarcodeAnalyzer(CameraType cameraType, List<? extends BarcodeType> list, Function1<? super BarCodeScannerResult, Unit> function1) {
        int i;
        Intrinsics.checkNotNullParameter(cameraType, "lensFacing");
        Intrinsics.checkNotNullParameter(list, "formats");
        Intrinsics.checkNotNullParameter(function1, "onComplete");
        this.lensFacing = cameraType;
        this.onComplete = function1;
        if (list.isEmpty()) {
            i = 0;
        } else {
            Iterable<BarcodeType> iterable = list;
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (BarcodeType mapToBarcode : iterable) {
                arrayList.add(Integer.valueOf(mapToBarcode.mapToBarcode()));
            }
            Iterator it = ((List) arrayList).iterator();
            if (it.hasNext()) {
                Object next = it.next();
                while (it.hasNext()) {
                    next = Integer.valueOf(((Number) next).intValue() | ((Number) it.next()).intValue());
                }
                i = ((Number) next).intValue();
            } else {
                throw new UnsupportedOperationException("Empty collection can't be reduced.");
            }
        }
        this.barcodeFormats = i;
        BarcodeScannerOptions build = new BarcodeScannerOptions.Builder().setBarcodeFormats(i, new int[0]).build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        this.barcodeScannerOptions = build;
        BarcodeScanner client = BarcodeScanning.getClient(build);
        Intrinsics.checkNotNullExpressionValue(client, "getClient(...)");
        this.barcodeScanner = client;
    }

    public final Function1<BarCodeScannerResult, Unit> getOnComplete() {
        return this.onComplete;
    }

    public void analyze(ImageProxy imageProxy) {
        Intrinsics.checkNotNullParameter(imageProxy, "imageProxy");
        Image image = imageProxy.getImage();
        if (image != null) {
            InputImage fromMediaImage = InputImage.fromMediaImage(image, CameraViewHelper.getCorrectCameraRotation(imageProxy.getImageInfo().getRotationDegrees(), this.lensFacing));
            Intrinsics.checkNotNullExpressionValue(fromMediaImage, "fromMediaImage(...)");
            this.barcodeScanner.process(fromMediaImage).addOnSuccessListener(new BarcodeAnalyzer$$ExternalSyntheticLambda0(new BarcodeAnalyzer$analyze$1(this, fromMediaImage))).addOnFailureListener(new BarcodeAnalyzer$$ExternalSyntheticLambda1()).addOnCompleteListener(new BarcodeAnalyzer$$ExternalSyntheticLambda2(imageProxy));
        }
    }

    /* access modifiers changed from: private */
    public static final void analyze$lambda$2(Function1 function1, Object obj) {
        Intrinsics.checkNotNullParameter(function1, "$tmp0");
        function1.invoke(obj);
    }

    /* access modifiers changed from: private */
    public static final void analyze$lambda$3(Exception exc) {
        String str;
        Intrinsics.checkNotNullParameter(exc, "it");
        Throwable cause = exc.getCause();
        if (cause == null || (str = cause.getMessage()) == null) {
            str = "Barcode scanning failed";
        }
        Log.d("SCANNER", str);
    }

    /* access modifiers changed from: private */
    public static final void analyze$lambda$4(ImageProxy imageProxy, Task task) {
        Intrinsics.checkNotNullParameter(imageProxy, "$imageProxy");
        Intrinsics.checkNotNullParameter(task, "it");
        imageProxy.close();
    }
}
