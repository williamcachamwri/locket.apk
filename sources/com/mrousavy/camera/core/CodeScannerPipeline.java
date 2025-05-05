package com.mrousavy.camera.core;

import android.media.Image;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageProxy;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.barcode.BarcodeScanner;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;
import com.google.mlkit.vision.barcode.BarcodeScanning;
import com.google.mlkit.vision.common.InputImage;
import com.mrousavy.camera.core.CameraConfiguration;
import com.mrousavy.camera.core.CameraSession;
import com.mrousavy.camera.core.types.CodeType;
import io.sentry.android.core.SentryLogcatAdapter;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00132\u00020\u00012\u00020\u0002:\u0001\u0013B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0017J\b\u0010\u0012\u001a\u00020\u000fH\u0016R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/mrousavy/camera/core/CodeScannerPipeline;", "Ljava/io/Closeable;", "Landroidx/camera/core/ImageAnalysis$Analyzer;", "configuration", "Lcom/mrousavy/camera/core/CameraConfiguration$CodeScanner;", "callback", "Lcom/mrousavy/camera/core/CameraSession$Callback;", "(Lcom/mrousavy/camera/core/CameraConfiguration$CodeScanner;Lcom/mrousavy/camera/core/CameraSession$Callback;)V", "getCallback", "()Lcom/mrousavy/camera/core/CameraSession$Callback;", "getConfiguration", "()Lcom/mrousavy/camera/core/CameraConfiguration$CodeScanner;", "scanner", "Lcom/google/mlkit/vision/barcode/BarcodeScanner;", "analyze", "", "imageProxy", "Landroidx/camera/core/ImageProxy;", "close", "Companion", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CodeScannerPipeline.kt */
public final class CodeScannerPipeline implements Closeable, ImageAnalysis.Analyzer {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = "CodeScannerPipeline";
    private final CameraSession.Callback callback;
    private final CameraConfiguration.CodeScanner configuration;
    private final BarcodeScanner scanner;

    public CodeScannerPipeline(CameraConfiguration.CodeScanner codeScanner, CameraSession.Callback callback2) {
        Intrinsics.checkNotNullParameter(codeScanner, "configuration");
        Intrinsics.checkNotNullParameter(callback2, "callback");
        this.configuration = codeScanner;
        this.callback = callback2;
        Iterable<CodeType> codeTypes = codeScanner.getCodeTypes();
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(codeTypes, 10));
        for (CodeType barcodeType : codeTypes) {
            arrayList.add(Integer.valueOf(barcodeType.toBarcodeType()));
        }
        List list = (List) arrayList;
        BarcodeScannerOptions.Builder builder = new BarcodeScannerOptions.Builder();
        int intValue = ((Number) list.get(0)).intValue();
        int[] intArray = CollectionsKt.toIntArray(list);
        BarcodeScannerOptions build = builder.setBarcodeFormats(intValue, Arrays.copyOf(intArray, intArray.length)).build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        BarcodeScanner client = BarcodeScanning.getClient(build);
        Intrinsics.checkNotNullExpressionValue(client, "getClient(...)");
        this.scanner = client;
    }

    public final CameraSession.Callback getCallback() {
        return this.callback;
    }

    public final CameraConfiguration.CodeScanner getConfiguration() {
        return this.configuration;
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/mrousavy/camera/core/CodeScannerPipeline$Companion;", "", "()V", "TAG", "", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: CodeScannerPipeline.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public void analyze(ImageProxy imageProxy) {
        Intrinsics.checkNotNullParameter(imageProxy, "imageProxy");
        Image image = imageProxy.getImage();
        if (image != null) {
            try {
                InputImage fromMediaImage = InputImage.fromMediaImage(image, imageProxy.getImageInfo().getRotationDegrees());
                Intrinsics.checkNotNullExpressionValue(fromMediaImage, "fromMediaImage(...)");
                this.scanner.process(fromMediaImage).addOnSuccessListener(new CodeScannerPipeline$$ExternalSyntheticLambda0(new CodeScannerPipeline$analyze$1(this, fromMediaImage))).addOnFailureListener(new CodeScannerPipeline$$ExternalSyntheticLambda1(this)).addOnCompleteListener(new CodeScannerPipeline$$ExternalSyntheticLambda2(imageProxy));
            } catch (Throwable th) {
                SentryLogcatAdapter.e(TAG, "Failed to process Image!", th);
                imageProxy.close();
            }
        } else {
            throw new InvalidImageTypeError();
        }
    }

    /* access modifiers changed from: private */
    public static final void analyze$lambda$1(Function1 function1, Object obj) {
        Intrinsics.checkNotNullParameter(function1, "$tmp0");
        function1.invoke(obj);
    }

    /* access modifiers changed from: private */
    public static final void analyze$lambda$2(CodeScannerPipeline codeScannerPipeline, Exception exc) {
        Intrinsics.checkNotNullParameter(codeScannerPipeline, "this$0");
        Intrinsics.checkNotNullParameter(exc, "error");
        Throwable th = exc;
        SentryLogcatAdapter.e(TAG, "Failed to process Image!", th);
        codeScannerPipeline.callback.onError(th);
    }

    /* access modifiers changed from: private */
    public static final void analyze$lambda$3(ImageProxy imageProxy, Task task) {
        Intrinsics.checkNotNullParameter(imageProxy, "$imageProxy");
        Intrinsics.checkNotNullParameter(task, "it");
        imageProxy.close();
    }

    public void close() {
        this.scanner.close();
    }
}
