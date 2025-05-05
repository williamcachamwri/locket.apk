package expo.modules.imagemanipulator.actions;

import android.graphics.Bitmap;
import expo.modules.imagemanipulator.ResizeOptions;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lexpo/modules/imagemanipulator/actions/ResizeAction;", "Lexpo/modules/imagemanipulator/actions/Action;", "resizeOptions", "Lexpo/modules/imagemanipulator/ResizeOptions;", "(Lexpo/modules/imagemanipulator/ResizeOptions;)V", "run", "Landroid/graphics/Bitmap;", "bitmap", "expo-image-manipulator_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ResizeAction.kt */
public final class ResizeAction implements Action {
    private final ResizeOptions resizeOptions;

    public ResizeAction(ResizeOptions resizeOptions2) {
        Intrinsics.checkNotNullParameter(resizeOptions2, "resizeOptions");
        this.resizeOptions = resizeOptions2;
    }

    public Bitmap run(Bitmap bitmap) {
        int i;
        int i2;
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        Double width = this.resizeOptions.getWidth();
        Integer num = null;
        Integer valueOf = width != null ? Integer.valueOf((int) width.doubleValue()) : null;
        Double height = this.resizeOptions.getHeight();
        if (height != null) {
            num = Integer.valueOf((int) height.doubleValue());
        }
        float width2 = ((float) bitmap.getWidth()) / ((float) bitmap.getHeight());
        if (valueOf != null) {
            i = valueOf.intValue();
        } else {
            Double height2 = this.resizeOptions.getHeight();
            Intrinsics.checkNotNull(height2);
            i = (int) (height2.doubleValue() * ((double) width2));
        }
        if (num != null) {
            i2 = num.intValue();
        } else {
            Double width3 = this.resizeOptions.getWidth();
            Intrinsics.checkNotNull(width3);
            i2 = (int) (width3.doubleValue() / ((double) width2));
        }
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, i, i2, true);
        Intrinsics.checkNotNullExpressionValue(createScaledBitmap, "createScaledBitmap(...)");
        return createScaledBitmap;
    }
}
