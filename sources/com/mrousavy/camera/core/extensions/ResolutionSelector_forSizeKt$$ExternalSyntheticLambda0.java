package com.mrousavy.camera.core.extensions;

import android.util.Size;
import androidx.camera.core.resolutionselector.ResolutionFilter;
import java.util.List;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ResolutionSelector_forSizeKt$$ExternalSyntheticLambda0 implements ResolutionFilter {
    public final /* synthetic */ Size f$0;

    public /* synthetic */ ResolutionSelector_forSizeKt$$ExternalSyntheticLambda0(Size size) {
        this.f$0 = size;
    }

    public final List filter(List list, int i) {
        return ResolutionSelector_forSizeKt.forSize$lambda$0(this.f$0, list, i);
    }
}
