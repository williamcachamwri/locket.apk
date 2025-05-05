package androidx.media3.extractor.mp4;

import androidx.media3.extractor.SniffFailure;
import com.google.common.primitives.ImmutableIntArray;

public final class UnsupportedBrandsSniffFailure implements SniffFailure {
    public final ImmutableIntArray compatibleBrands;
    public final int majorBrand;

    public UnsupportedBrandsSniffFailure(int i, int[] iArr) {
        ImmutableIntArray immutableIntArray;
        this.majorBrand = i;
        if (iArr != null) {
            immutableIntArray = ImmutableIntArray.copyOf(iArr);
        } else {
            immutableIntArray = ImmutableIntArray.of();
        }
        this.compatibleBrands = immutableIntArray;
    }
}
