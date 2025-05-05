package expo.modules.clipboard;

import android.os.Bundle;
import androidx.core.os.BundleKt;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÖ\u0001J\u0006\u0010\u0015\u001a\u00020\u0016J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u0018"}, d2 = {"Lexpo/modules/clipboard/ImageResult;", "", "base64Image", "", "width", "", "height", "(Ljava/lang/String;II)V", "getBase64Image", "()Ljava/lang/String;", "getHeight", "()I", "getWidth", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toBundle", "Landroid/os/Bundle;", "toString", "expo-clipboard_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ClipboardImage.kt */
public final class ImageResult {
    private final String base64Image;
    private final int height;
    private final int width;

    public static /* synthetic */ ImageResult copy$default(ImageResult imageResult, String str, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = imageResult.base64Image;
        }
        if ((i3 & 2) != 0) {
            i = imageResult.width;
        }
        if ((i3 & 4) != 0) {
            i2 = imageResult.height;
        }
        return imageResult.copy(str, i, i2);
    }

    public final String component1() {
        return this.base64Image;
    }

    public final int component2() {
        return this.width;
    }

    public final int component3() {
        return this.height;
    }

    public final ImageResult copy(String str, int i, int i2) {
        Intrinsics.checkNotNullParameter(str, "base64Image");
        return new ImageResult(str, i, i2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ImageResult)) {
            return false;
        }
        ImageResult imageResult = (ImageResult) obj;
        return Intrinsics.areEqual((Object) this.base64Image, (Object) imageResult.base64Image) && this.width == imageResult.width && this.height == imageResult.height;
    }

    public int hashCode() {
        return (((this.base64Image.hashCode() * 31) + Integer.hashCode(this.width)) * 31) + Integer.hashCode(this.height);
    }

    public String toString() {
        String str = this.base64Image;
        int i = this.width;
        return "ImageResult(base64Image=" + str + ", width=" + i + ", height=" + this.height + ")";
    }

    public ImageResult(String str, int i, int i2) {
        Intrinsics.checkNotNullParameter(str, "base64Image");
        this.base64Image = str;
        this.width = i;
        this.height = i2;
    }

    public final String getBase64Image() {
        return this.base64Image;
    }

    public final int getWidth() {
        return this.width;
    }

    public final int getHeight() {
        return this.height;
    }

    public final Bundle toBundle() {
        return BundleKt.bundleOf(TuplesKt.to("data", this.base64Image), TuplesKt.to("size", BundleKt.bundleOf(TuplesKt.to("width", Integer.valueOf(this.width)), TuplesKt.to("height", Integer.valueOf(this.height)))));
    }
}
