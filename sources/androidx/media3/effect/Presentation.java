package androidx.media3.effect;

import android.graphics.Matrix;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Size;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

public final class Presentation implements MatrixTransformation {
    private static final float ASPECT_RATIO_UNSET = -1.0f;
    public static final int LAYOUT_SCALE_TO_FIT = 0;
    public static final int LAYOUT_SCALE_TO_FIT_WITH_CROP = 1;
    public static final int LAYOUT_STRETCH_TO_FIT = 2;
    private final int layout;
    private float outputHeight;
    private float outputWidth;
    private float requestedAspectRatio;
    private final int requestedHeightPixels;
    private final int requestedWidthPixels;
    private Matrix transformationMatrix;

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Layout {
    }

    private static void checkLayout(int i) {
        boolean z = true;
        if (!(i == 0 || i == 1 || i == 2)) {
            z = false;
        }
        Assertions.checkArgument(z, "invalid layout " + i);
    }

    public static Presentation createForAspectRatio(float f, int i) {
        Assertions.checkArgument(f > 0.0f, "aspect ratio " + f + " must be positive");
        checkLayout(i);
        return new Presentation(-1, -1, f, i);
    }

    public static Presentation createForHeight(int i) {
        return new Presentation(-1, i, ASPECT_RATIO_UNSET, 0);
    }

    public static Presentation createForWidthAndHeight(int i, int i2, int i3) {
        boolean z = true;
        Assertions.checkArgument(i > 0, "width " + i + " must be positive");
        if (i2 <= 0) {
            z = false;
        }
        Assertions.checkArgument(z, "height " + i2 + " must be positive");
        checkLayout(i3);
        return new Presentation(i, i2, ASPECT_RATIO_UNSET, i3);
    }

    private Presentation(int i, int i2, float f, int i3) {
        Assertions.checkArgument(f == ASPECT_RATIO_UNSET || i == -1, "width and aspect ratio should not both be set");
        this.requestedWidthPixels = i;
        this.requestedHeightPixels = i2;
        this.requestedAspectRatio = f;
        this.layout = i3;
        this.outputWidth = ASPECT_RATIO_UNSET;
        this.outputHeight = ASPECT_RATIO_UNSET;
        this.transformationMatrix = new Matrix();
    }

    public Size configure(int i, int i2) {
        int i3;
        boolean z = true;
        Assertions.checkArgument(i > 0, "inputWidth must be positive");
        if (i2 <= 0) {
            z = false;
        }
        Assertions.checkArgument(z, "inputHeight must be positive");
        this.transformationMatrix = new Matrix();
        this.outputWidth = (float) i;
        this.outputHeight = (float) i2;
        int i4 = this.requestedWidthPixels;
        if (!(i4 == -1 || (i3 = this.requestedHeightPixels) == -1)) {
            this.requestedAspectRatio = ((float) i4) / ((float) i3);
        }
        if (this.requestedAspectRatio != ASPECT_RATIO_UNSET) {
            applyAspectRatio();
        }
        int i5 = this.requestedHeightPixels;
        if (i5 != -1) {
            int i6 = this.requestedWidthPixels;
            if (i6 != -1) {
                this.outputWidth = (float) i6;
            } else {
                this.outputWidth = (((float) i5) * this.outputWidth) / this.outputHeight;
            }
            this.outputHeight = (float) i5;
        }
        return new Size(Math.round(this.outputWidth), Math.round(this.outputHeight));
    }

    public Matrix getMatrix(long j) {
        return (Matrix) Assertions.checkStateNotNull(this.transformationMatrix, "configure must be called first");
    }

    public boolean isNoOp(int i, int i2) {
        configure(i, i2);
        return ((Matrix) Assertions.checkStateNotNull(this.transformationMatrix)).isIdentity() && i == Math.round(this.outputWidth) && i2 == Math.round(this.outputHeight);
    }

    @RequiresNonNull({"transformationMatrix"})
    private void applyAspectRatio() {
        float f = this.outputWidth;
        float f2 = this.outputHeight;
        float f3 = f / f2;
        int i = this.layout;
        if (i == 0) {
            float f4 = this.requestedAspectRatio;
            if (f4 > f3) {
                this.transformationMatrix.setScale(f3 / f4, 1.0f);
                this.outputWidth = this.outputHeight * this.requestedAspectRatio;
                return;
            }
            this.transformationMatrix.setScale(1.0f, f4 / f3);
            this.outputHeight = this.outputWidth / this.requestedAspectRatio;
        } else if (i == 1) {
            float f5 = this.requestedAspectRatio;
            if (f5 > f3) {
                this.transformationMatrix.setScale(1.0f, f5 / f3);
                this.outputHeight = this.outputWidth / this.requestedAspectRatio;
                return;
            }
            this.transformationMatrix.setScale(f3 / f5, 1.0f);
            this.outputWidth = this.outputHeight * this.requestedAspectRatio;
        } else if (i == 2) {
            float f6 = this.requestedAspectRatio;
            if (f6 > f3) {
                this.outputWidth = f2 * f6;
            } else {
                this.outputHeight = f / f6;
            }
        }
    }
}
