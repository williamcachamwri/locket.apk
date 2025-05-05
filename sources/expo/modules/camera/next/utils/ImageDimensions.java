package expo.modules.camera.next.utils;

import com.facebook.imagepipeline.common.RotationOptions;
import expo.modules.camera.next.records.CameraType;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B+\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u0014\u001a\u00020\u0003HÂ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÂ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0007HÆ\u0003J1\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u000f2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000f8BX\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0010R\u000e\u0010\u0004\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\rR\u0011\u0010\u0012\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\r¨\u0006\u001e"}, d2 = {"Lexpo/modules/camera/next/utils/ImageDimensions;", "", "mWidth", "", "mHeight", "rotation", "facing", "Lexpo/modules/camera/next/records/CameraType;", "(IIILexpo/modules/camera/next/records/CameraType;)V", "getFacing", "()Lexpo/modules/camera/next/records/CameraType;", "height", "getHeight", "()I", "isLandscape", "", "()Z", "getRotation", "width", "getWidth", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "toString", "", "expo-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ImageDimensions.kt */
public final class ImageDimensions {
    private final CameraType facing;
    private final int mHeight;
    private final int mWidth;
    private final int rotation;

    public ImageDimensions(int i, int i2) {
        this(i, i2, 0, (CameraType) null, 12, (DefaultConstructorMarker) null);
    }

    public ImageDimensions(int i, int i2, int i3) {
        this(i, i2, i3, (CameraType) null, 8, (DefaultConstructorMarker) null);
    }

    private final int component1() {
        return this.mWidth;
    }

    private final int component2() {
        return this.mHeight;
    }

    public static /* synthetic */ ImageDimensions copy$default(ImageDimensions imageDimensions, int i, int i2, int i3, CameraType cameraType, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i = imageDimensions.mWidth;
        }
        if ((i4 & 2) != 0) {
            i2 = imageDimensions.mHeight;
        }
        if ((i4 & 4) != 0) {
            i3 = imageDimensions.rotation;
        }
        if ((i4 & 8) != 0) {
            cameraType = imageDimensions.facing;
        }
        return imageDimensions.copy(i, i2, i3, cameraType);
    }

    public final int component3() {
        return this.rotation;
    }

    public final CameraType component4() {
        return this.facing;
    }

    public final ImageDimensions copy(int i, int i2, int i3, CameraType cameraType) {
        Intrinsics.checkNotNullParameter(cameraType, "facing");
        return new ImageDimensions(i, i2, i3, cameraType);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ImageDimensions)) {
            return false;
        }
        ImageDimensions imageDimensions = (ImageDimensions) obj;
        return this.mWidth == imageDimensions.mWidth && this.mHeight == imageDimensions.mHeight && this.rotation == imageDimensions.rotation && this.facing == imageDimensions.facing;
    }

    public int hashCode() {
        return (((((Integer.hashCode(this.mWidth) * 31) + Integer.hashCode(this.mHeight)) * 31) + Integer.hashCode(this.rotation)) * 31) + this.facing.hashCode();
    }

    public String toString() {
        int i = this.mWidth;
        int i2 = this.mHeight;
        int i3 = this.rotation;
        return "ImageDimensions(mWidth=" + i + ", mHeight=" + i2 + ", rotation=" + i3 + ", facing=" + this.facing + ")";
    }

    public ImageDimensions(int i, int i2, int i3, CameraType cameraType) {
        Intrinsics.checkNotNullParameter(cameraType, "facing");
        this.mWidth = i;
        this.mHeight = i2;
        this.rotation = i3;
        this.facing = cameraType;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ImageDimensions(int i, int i2, int i3, CameraType cameraType, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, (i4 & 4) != 0 ? 0 : i3, (i4 & 8) != 0 ? CameraType.BACK : cameraType);
    }

    public final CameraType getFacing() {
        return this.facing;
    }

    public final int getRotation() {
        return this.rotation;
    }

    private final boolean isLandscape() {
        return this.rotation % RotationOptions.ROTATE_180 == 90;
    }

    public final int getWidth() {
        if (isLandscape()) {
            return this.mHeight;
        }
        return this.mWidth;
    }

    public final int getHeight() {
        if (isLandscape()) {
            return this.mWidth;
        }
        return this.mHeight;
    }
}
