package com.canhub.cropper;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lcom/canhub/cropper/CropImageContractOptions;", "invoke"}, k = 3, mv = {1, 7, 1}, xi = 48)
/* compiled from: CropImageContractOptions.kt */
final class CropImageContractOptionsKt$options$1 extends Lambda implements Function1<CropImageContractOptions, Unit> {
    public static final CropImageContractOptionsKt$options$1 INSTANCE = new CropImageContractOptionsKt$options$1();

    CropImageContractOptionsKt$options$1() {
        super(1);
    }

    public final void invoke(CropImageContractOptions cropImageContractOptions) {
        Intrinsics.checkNotNullParameter(cropImageContractOptions, "$this$null");
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((CropImageContractOptions) obj);
        return Unit.INSTANCE;
    }
}
