package com.mrousavy.camera.core;

import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.common.InputImage;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\u0010\u0000\u001a\u00020\u00012*\u0010\u0002\u001a&\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u0004 \u0005*\u0012\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u0004\u0018\u00010\u00060\u0003H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "barcodes", "", "Lcom/google/mlkit/vision/barcode/common/Barcode;", "kotlin.jvm.PlatformType", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: CodeScannerPipeline.kt */
final class CodeScannerPipeline$analyze$1 extends Lambda implements Function1<List<Barcode>, Unit> {
    final /* synthetic */ InputImage $inputImage;
    final /* synthetic */ CodeScannerPipeline this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CodeScannerPipeline$analyze$1(CodeScannerPipeline codeScannerPipeline, InputImage inputImage) {
        super(1);
        this.this$0 = codeScannerPipeline;
        this.$inputImage = inputImage;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((List<Barcode>) (List) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(List<Barcode> list) {
        Intrinsics.checkNotNull(list);
        if (!list.isEmpty()) {
            this.this$0.getCallback().onCodeScanned(list, new CodeScannerFrame(this.$inputImage.getWidth(), this.$inputImage.getHeight()));
        }
    }
}
