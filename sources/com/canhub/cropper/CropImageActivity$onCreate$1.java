package com.canhub.cropper;

import com.canhub.cropper.CropImageActivity;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

@Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
/* compiled from: CropImageActivity.kt */
/* synthetic */ class CropImageActivity$onCreate$1 extends FunctionReferenceImpl implements Function1<CropImageActivity.Source, Unit> {
    CropImageActivity$onCreate$1(Object obj) {
        super(1, obj, CropImageActivity.class, "openSource", "openSource(Lcom/canhub/cropper/CropImageActivity$Source;)V", 0);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((CropImageActivity.Source) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(CropImageActivity.Source source) {
        Intrinsics.checkNotNullParameter(source, "p0");
        ((CropImageActivity) this.receiver).openSource(source);
    }
}
